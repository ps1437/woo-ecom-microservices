package com.syscho.wocom.carts;

import com.syscho.wocom.products.ProductEntity;
import com.syscho.wocom.products.ProductNotFoundException;
import com.syscho.wocom.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;


    private final ProductRepository productRepository;


    private final CartMapper cartMapper;

    public CartDTO getCartByUserId(Long userId) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        return cartMapper.toCartDTO(cartEntity);
    }

    public void addToCart(Long userId, Long productId, int quantity) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        CartItemEntity cartItemEntity = cartEntity.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItemEntity != null) {
            // If the product is already in the cart, update the quantity
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
        } else {
            // If the product is not in the cart, add a new cart item
            cartItemEntity = new CartItemEntity();
            cartItemEntity.setCart(cartEntity);
            cartItemEntity.setProduct(productEntity);
            cartItemEntity.setQuantity(quantity);
            cartEntity.getCartItems().add(cartItemEntity);
        }

        // Update the total price of the cart
        updateCartTotal(cartEntity);

        // Save the updated cart to the repository
        cartRepository.save(cartEntity);
    }

    private void updateCartTotal(CartEntity cartEntity) {
        double total = cartEntity.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cartEntity.setTotal(total);
    }

    public void removeFromCart(Long userId, Long productId) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        cartEntity.getCartItems().removeIf(item -> item.getProduct().getProductId().equals(productId));

        // Update the total price of the cart
        updateCartTotal(cartEntity);

        // Save the updated cart to the repository
        cartRepository.save(cartEntity);
    }

    public void clearCart(Long userId) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        cartEntity.getCartItems().clear();
        cartEntity.setTotal(0.0);

        // Save the updated cart to the repository
        cartRepository.save(cartEntity);
    }

}


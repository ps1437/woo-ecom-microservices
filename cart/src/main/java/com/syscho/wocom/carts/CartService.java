package com.syscho.wocom.carts;

import com.syscho.wocom.carts.dto.CartDTO;
import com.syscho.wocom.carts.dto.CartItemDTO;
import com.syscho.wocom.carts.mapper.CartItemMapper;
import com.syscho.wocom.carts.mapper.CartMapper;
import com.syscho.wocom.carts.products.ProductDTO;
import com.syscho.wocom.carts.products.ProductFeignClient;
import com.syscho.wocom.carts.products.ProductNotFoundException;
import com.syscho.wocom.carts.repo.CartEntity;
import com.syscho.wocom.carts.repo.CartItemEntity;
import com.syscho.wocom.carts.repo.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;


    private final ProductFeignClient productFeignClient;


    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public CartDTO getCartByUserId(Long userId) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        if (cartEntity != null && !CollectionUtils.isEmpty(cartEntity.getCartItems())) {
            List<Long> productIds = cartEntity.getCartItems().stream()
                    .map(CartItemEntity::getProductId)
                    .collect(Collectors.toList());

            List<ProductDTO> products = productFeignClient.getProductByIds(productIds);

            Map<Long, ProductDTO> productMap = products.stream()
                    .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));

            List<CartItemDTO> cartItems = cartEntity.getCartItems().stream()
                    .map(cartItemEntity -> {
                        ProductDTO productDTO = productMap.getOrDefault(cartItemEntity.getProductId(), null);
                        CartItemDTO cartItemDTO = cartItemMapper.toCartItemDTO(cartItemEntity);
                        cartItemDTO.setProduct(productDTO);
                        return cartItemDTO;
                    })
                    .collect(Collectors.toList());

            CartDTO cartDTO = cartMapper.toCartDTO(cartEntity);
            cartDTO.setCartItems(cartItems);

            return cartDTO;
        }


        return cartMapper.toCartDTO(cartEntity);
    }


    public void addToCart(Long userId, Long productId, int quantity) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        ProductDTO productDTO = Optional.ofNullable(productFeignClient.getProductById(productId))

                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        CartItemEntity cartItemEntity = cartEntity.getCartItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItemEntity != null) {
            // If the product is already in the cart, update the quantity
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
        } else {
            // If the product is not in the cart, add a new cart item
            cartItemEntity = new CartItemEntity();
            cartItemEntity.setCart(cartEntity);
            cartItemEntity.setProductId(cartItemEntity.getProductId());
            cartItemEntity.setQuantity(quantity);
            cartEntity.getCartItems().add(cartItemEntity);
        }

        // Update the total price of the cart
        updateCartTotal(cartEntity, productDTO);

        // Save the updated cart to the repository
        cartRepository.save(cartEntity);
    }

    private void updateCartTotal(CartEntity cartEntity, ProductDTO productDTO) {
        double total = cartEntity.getCartItems().stream()
                .mapToDouble(item -> productDTO.getPrice() * item.getQuantity())
                .sum();
        cartEntity.setTotal(total);
    }

    public void removeFromCart(Long userId, Long productId) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        cartEntity.getCartItems().removeIf(item -> item.getProductId().equals(productId));

        // Update the total price of the cart
        // updateCartTotal(cartEntity);

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


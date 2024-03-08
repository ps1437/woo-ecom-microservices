package com.syscho.wocom.carts.api;

import com.syscho.wocom.carts.CartService;
import com.syscho.wocom.carts.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public CartDTO getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}/add")
    public void addToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addToCart(userId, productId, quantity);
    }

    @PostMapping("/{userId}/remove")
    public void removeFromCart(@PathVariable Long userId, @RequestParam Long productId) {
        cartService.removeFromCart(userId, productId);
    }

    @PostMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }

}

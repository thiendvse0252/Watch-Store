/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private final Map<String, Integer> cart;

    public Map<String, Integer> getCart() {
        return cart;
    }

    public Cart() {
        cart = new HashMap<>();
    }

    public void addToCart(String tea, int quantity) {
        if (cart != null) {
            if (cart.containsKey(tea)) {
                quantity = cart.get(tea) + quantity;
            }
            cart.put(tea, quantity);
        }
    }

    public void removeFromCart(String tea) {
        if (cart != null) {
            if (cart.containsKey(tea)) {
                cart.remove(tea);
            }
        }
    }
    
}

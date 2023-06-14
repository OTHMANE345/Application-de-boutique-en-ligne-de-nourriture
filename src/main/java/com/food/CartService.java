package com.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private productRepository productRepository;



    public void addtoCart(Long id){
        product product=productRepository.findById(id).get();
        Cart cart=new Cart(product);
        cartRepository.save(cart);
    }
}

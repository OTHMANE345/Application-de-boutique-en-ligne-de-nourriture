package com.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller

public class productController {
    @Autowired
    private ProductService service;
    @Autowired
    private CartService CartService;
    @Autowired
    private CartRepository CartRepository;


    public productController(ProductService service) {
        this.service = service;
    }
    @GetMapping("/p")
    public String viewHomePage(Model model) {

        return "add_product";
    }
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String savep(@ModelAttribute("product") product product, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        service.saveProductWithImage(product, imageFile);
        return "add_product";
    }
    @Autowired
    private productRepository productRepository;
    @GetMapping("/k")
    public String getAllProducts(Model model) {
        List<product> productList =productRepository.findAll() ;
        model.addAttribute("listproduct", productList);
        return "product";
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(name = "id") Long id) {
        CartService.addtoCart(id);
        return "product";
    }
    @GetMapping("/Cart")
    public String viewCart(Model model) {
        List<Cart> cartList = CartRepository.findAll();
        model.addAttribute("cartList", cartList);
        return "Cart";
    }
    @GetMapping("/l")
    public String viewH(Model model) {

        return "home";
    }

}
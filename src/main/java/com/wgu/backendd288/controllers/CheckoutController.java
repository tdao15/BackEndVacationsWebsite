package com.wgu.backendd288.controllers;

import com.wgu.backendd288.services.CheckoutService;
import com.wgu.backendd288.services.Purchase;
import com.wgu.backendd288.services.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService theCheckoutService){
        this.checkoutService = theCheckoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        if (purchase.getCart() != null && purchase.getCart().getId() != null &&
                purchase.getCart().getId() == 0) {
            purchase.getCart().setId(null);
        }
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }
}

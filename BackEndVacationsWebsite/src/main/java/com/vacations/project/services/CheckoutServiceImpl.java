package com.vacations.project.services;

import com.vacations.project.dao.CartItemRepository;
import com.vacations.project.dao.CartRepository;
import com.vacations.project.dao.CustomerRepository;
import com.vacations.project.entities.Cart;
import com.vacations.project.entities.CartItem;
import com.vacations.project.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               CartRepository cartRepository,
                               CartItemRepository cartItemRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();

        if (cartItems != null && !cartItems.isEmpty()){
            Set<CartItem> cartItemsTarget = new HashSet<>();
            for (CartItem item : cartItems){
                item.setCart(cart);
                cartItemsTarget.add(item);
            }
            cart.setCartItem(cartItemsTarget);
        }


        cart.setStatus(StatusType.ordered);

        if (cart == null || cart.getCartItem() == null){
            orderTrackingNumber = "Error: Cart must have at least one item to purchase";
            return new PurchaseResponse(orderTrackingNumber);
        }

        cartRepository.save(cart);

        //Customer customer = purchase.getCustomer();
        //customer.add(cart);

        //customerRepository.save(customer);
        //breakpoint
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}

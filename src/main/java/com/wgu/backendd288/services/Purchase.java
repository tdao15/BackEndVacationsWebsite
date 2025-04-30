package com.wgu.backendd288.services;

import com.wgu.backendd288.entities.Cart;
import com.wgu.backendd288.entities.CartItem;
import com.wgu.backendd288.entities.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class Purchase {

    private Customer customer;

    private Cart cart;

    private Set<CartItem> cartItems;

}

package com.vacations.project.services;

import com.vacations.project.entities.Cart;
import com.vacations.project.entities.CartItem;
import com.vacations.project.entities.Customer;
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

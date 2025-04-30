package com.wgu.backendd288.entities;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.wgu.backendd288.entities.CartItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="carts")
@Data
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    @Column(name="package_price")
    private BigDecimal package_price;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusType status;


    @Column(name="party_size")
    private int party_size;

    @CreationTimestamp
    @Column(name="create_date")
    private Date create_date;

    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="cart")
    private Set<CartItem> cartItem = new HashSet<>();

    public void add(CartItem tempItem){

        if (tempItem != null){

            if (cartItem == null){
                cartItem = new HashSet<>();
            }

            cartItem.add(tempItem);
            tempItem.setCart(this);

        }

    }

}

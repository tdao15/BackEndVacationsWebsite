package com.vacations.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="carts")
@Data
@Getter
@Setter
@ToString(exclude = {"cartItem", "customer"})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="package_price")
    private BigDecimal package_price;

    @Column(name="party_size")
    private int party_size;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusType status;

    @CreationTimestamp
    @Column(name="create_date")
    private Date create_date;

    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="cart", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<CartItem> cartItem;

    public void add(CartItem tempItem){

        if (tempItem != null){

            if (cartItem == null){
                cartItem = new HashSet<>();
            }

            cartItem.add(tempItem);
            tempItem.setCart(this);

        }

    }

    @Override
    public boolean equals(Object object) {

        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        Cart tempCart = (Cart) object;
        return Objects.equals(id, tempCart.id);
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }

}

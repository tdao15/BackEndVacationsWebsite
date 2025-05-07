package com.vacations.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="customers")
@Data
@Getter
@Setter
@ToString(exclude = "carts")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @Column(name="customer_first_name", nullable = false)
    private String firstName;

    @Column(name="customer_last_name", nullable = false)
    private String lastName;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="postal_code", nullable = false)
    private String postal_code;

    @Column(name="phone", nullable = false)
    private String phone;

    @Column(name="create_date")
    private Date create_date;

    @Column(name="last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name="division_id")
    private Division division;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="customer", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Cart> carts;

    public void add(Cart tempCart){

        if (tempCart != null){

            if (carts == null){
                carts = new HashSet<>();
            }

            carts.add(tempCart);
            tempCart.setCustomer(this);
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

        Customer tempCustomer = (Customer) object;
        return Objects.equals(id, tempCustomer.id);
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }

}

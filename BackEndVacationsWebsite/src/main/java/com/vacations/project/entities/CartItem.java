package com.vacations.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="cart_items")
@Data
@Getter
@Setter
@ToString(exclude = "cart")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="vacation_id")
    private Vacation vacation;

    @ManyToMany
    @JoinTable(
            name="excursion_cartitem",
            joinColumns=@JoinColumn(name="cart_item_id"),
            inverseJoinColumns=@JoinColumn(name="excursion_id")
    )
    private Set<Excursion> excursions;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @CreationTimestamp
    @Column(name="create_date")
    private Date create_date;

    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    @Override
    public boolean equals(Object object) {

        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        CartItem tempCartItem = (CartItem) object;
        return Objects.equals(id, tempCartItem.id);
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }

}

package com.vacations.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="divisions")
@Data
@Getter
@Setter
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="division_id")
    private Long id;

    @Column(name="division")
    private String division_name;

    @CreationTimestamp
    @Column(name="create_date")
    private Date create_date;

    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id", nullable = false, updatable = false, insertable = false)
    private Country country;

    @Column(name="country_id")
    private long country_id;

    public void setCountry(Country country){
        this.setCountry_id(country.getId());
        this.country = country;
    }

    public Division(){
    }

    @Override
    public boolean equals(Object object) {

        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        Division tempDivision = (Division) object;
        return Objects.equals(id, tempDivision.id);
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }

}

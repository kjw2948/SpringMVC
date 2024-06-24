package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    private String zipcode;

}

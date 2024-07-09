package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 값타입은 변경 불가능하게 설계해야한다! --> JPA 스펙에서는 기본생성자가 필요함
@Getter
public class Address {
    private String zipcode;
    private String city;
    private String street;

    protected Address() {
    }

    public Address(String zipcode, String city, String street) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
    }
}

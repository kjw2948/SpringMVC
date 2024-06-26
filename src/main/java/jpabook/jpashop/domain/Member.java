package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue //자동 생성
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address adress;

    @OneToMany(mappedBy = "order_id")
    private List<Order> orders = new ArrayList<>();



}

package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded // 내장클래스
    private Address address;

    @Enumerated(EnumType.STRING)  // Enum 타입에서 설정은 무조건 String으로 해야함!!!
    private DeliveryStatus status; //READY, COMP
}

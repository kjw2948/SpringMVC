package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id") // access가 많은 곳에 fk를 두자!
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간


    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //연관관계 메서드 --> 연관관계 주인이면 set, 아니면 add   ==> 양방향일때 필요함 -> 보통 전체적인 로직을 컨트롤하는쪽에 있는 것이 좋음
    private void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}

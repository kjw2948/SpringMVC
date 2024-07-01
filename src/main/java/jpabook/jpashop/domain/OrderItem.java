package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.item.Item;
import lombok.Getter;

@Entity
@Getter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    private int orderPrice;
    private int count;


}

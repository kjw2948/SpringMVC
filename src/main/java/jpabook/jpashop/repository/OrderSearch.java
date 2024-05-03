package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;


    public void save(Order order) {

    }
    public Order findOne(Long id) {

    }
}

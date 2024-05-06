package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public  class OrderService{


    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //상품주문

    @Transactional // 데이터 변경
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.find(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());  // 바로 가져오는거 말고, 실제로 입력하는 걸로 해보기

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order); // cascade 때문에 자동으로 persist됨

        return order.getId();
    }


    // 주문 내역 조회
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }


    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}

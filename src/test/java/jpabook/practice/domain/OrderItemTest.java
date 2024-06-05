package jpabook.practice.domain;

import jpabook.practice.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {
    @Test
    public void test() {
        Item item = new Item("itemA", 1000, 10);
        OrderItem orderItem = OrderItem.createOrderItem(item, 1000, 10);

        orderItem.cancelOrder();
        int result = item.getStockQuantity();

        Assertions.assertThat(result).isEqualTo(10);
    }
}
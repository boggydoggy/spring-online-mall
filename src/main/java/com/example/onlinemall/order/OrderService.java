package com.example.onlinemall.order;

import com.example.onlinemall.discount.DiscountMethod;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}

package org.delivery.db.userorder.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {
    ORDER("주문"),
    CANCEL("취소"),
    ;

    private final String description;
}

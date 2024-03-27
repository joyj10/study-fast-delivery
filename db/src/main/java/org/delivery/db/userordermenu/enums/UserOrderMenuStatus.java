package org.delivery.db.userordermenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderMenuStatus {
    ORDER("주문"),
    CANCEL("취소"),
    ;

    private final String description;
}

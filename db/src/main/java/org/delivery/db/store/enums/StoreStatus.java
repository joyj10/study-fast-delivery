package org.delivery.db.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {
    WAITED("대기"),
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;
    private final String description;
}

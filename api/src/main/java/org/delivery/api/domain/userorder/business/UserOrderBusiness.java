package org.delivery.api.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Business
@Transactional(readOnly = true)
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderMenuService userOrderMenuService;

    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;

    // 1. 사용자, 메뉴 id
    // 2. userOrder 생성
    // 3. userOrderMenu 생성
    // 4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest body) {
        List<StoreMenuEntity> storeMenuEntityList = storeMenuService.getStoreMenuListWithThrow(body.getStoreMenuIdList());
        UserOrderEntity userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

        // 주문
        UserOrderEntity newUserOrderEntity = userOrderService.order(userOrderEntity);

        // 매핑
        List<UserOrderMenuEntity> userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> userOrderMenuConverter.toEntity(newUserOrderEntity, it))
                .toList();

        // 주문 내역 기록 남기기
        userOrderMenuEntityList.forEach(userOrderMenuService::order);

        return userOrderConverter.toResponse(newUserOrderEntity);
    }
}

package org.delivery.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Business
@Transactional(readOnly = true)
public class StoreMenuBusiness {
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    @Transactional
    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        //req -> entity -> save -> response
        StoreMenuEntity entity = storeMenuConverter.toEntity(request);
        StoreMenuEntity saveEntity = storeMenuService.register(entity);
        return storeMenuConverter.toResponse(saveEntity);
    }

    public List<StoreMenuResponse> search(Long storeId) {
        List<StoreMenuEntity> list = storeMenuService.getStoreMenuByStoreId(storeId);

        return list.stream()
                .map(storeMenuConverter::toResponse)
                .toList();
    }


}

package org.delivery.api.domain.store.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.comon.annotation.Business;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.enums.StoreCategory;

import java.util.List;

@RequiredArgsConstructor
@Business
public class StoreBusiness {
    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest storeRegisterRequest) {
        // req -> entity -> response
        StoreEntity storeEntity = storeConverter.toEntity(storeRegisterRequest);
        StoreEntity saveEntity = storeService.register(storeEntity);
        return storeConverter.toResponse(saveEntity);
    }

    public List<StoreResponse> searchCategory(StoreCategory storeCategory) {
        // entity list -> response list
        List<StoreEntity> storeEntities = storeService.searchByCategory(storeCategory);
        return storeEntities.stream()
                .map(storeConverter::toResponse)
                .toList();
    }
}

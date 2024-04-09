package org.delivery.storeadmin.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.common.error.ErrorCode;
import org.delivery.storeadmin.common.exception.StoreAdminException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreEntity getStoreWithThrow(String storeName) {
        Optional<StoreEntity> entity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(storeName, StoreStatus.REGISTERED);
        return entity.orElseThrow(() -> new StoreAdminException(ErrorCode.NULL_POINT));
    }


}

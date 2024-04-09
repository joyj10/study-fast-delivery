package org.delivery.storeadmin.domain.user.business;


import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.storeadmin.domain.store.service.StoreService;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreUserBusiness {
    private final StoreUserService storeUserService;
    private final StoreUserConverter storeUserConverter;

    private final StoreService storeService;

    @Transactional
    public StoreUserResponse register(StoreUserRegisterRequest request) {
        StoreEntity storeEntity = storeService.getStoreWithThrow(request.getStoreName());
        StoreUserEntity entity = storeUserConverter.toEntity(request, storeEntity);
        StoreUserEntity newEntity = storeUserService.register(entity);

        return storeUserConverter.toResponse(newEntity, storeEntity);
    }
}

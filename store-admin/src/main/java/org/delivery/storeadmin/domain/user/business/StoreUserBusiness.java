package org.delivery.storeadmin.domain.user.business;


import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.db.storeuser.StoreUserEntity;
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
    private final StoreRepository storeRepository; // TODO storeService 생성 후 이관

    @Transactional
    public StoreUserResponse register(StoreUserRegisterRequest request) {
        StoreEntity storeEntity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED)
                .orElseThrow();  //TODO NULL 일때 에러 체크 하도록 처리

        StoreUserEntity entity = storeUserConverter.toEntity(request, storeEntity);
        StoreUserEntity newEntity = storeUserService.register(entity);

        return storeUserConverter.toResponse(newEntity, storeEntity);
    }
}

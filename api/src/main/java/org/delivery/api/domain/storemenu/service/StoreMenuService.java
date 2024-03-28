package org.delivery.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.delivery.db.storemenu.enums.StoreMenuStatus.REGISTERED;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long id) {
        Optional<StoreMenuEntity> opt = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, REGISTERED);
        return opt.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuEntity> getStoreMenuListWithThrow(List<Long> idList) {
        return storeMenuRepository.findAllByIdInAndStatus(idList, REGISTERED);
    }


    public List<StoreMenuEntity> getStoreMenuByStoreId(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, REGISTERED);
    }

    public StoreMenuEntity register(StoreMenuEntity storeMenuEntity) {
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    it.setStatus(REGISTERED);
                    return storeMenuRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}

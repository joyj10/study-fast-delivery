package org.delivery.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.comon.error.ErrorCode;
import org.delivery.api.comon.exception.ApiException;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;

    // 유효한 스토어 가져오기
    public StoreEntity getStoreWithThrow(Long id) {
        Optional<StoreEntity> entity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED);
        return entity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));   // 이렇게 하면 디버깅 브레이크 포인트를 별도로 찍을 수 있음
    }

    // 스토어 등록
    @Transactional
    public StoreEntity register(StoreEntity storeEntity) {
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    it.setStar(0);
                    it.setStatus(StoreStatus.REGISTERED);
                    // TODO 등록 일시
                    return storeRepository.save(storeEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 카테고리로 스토어 검색
    public List<StoreEntity> searchByCategory(StoreCategory storeCategory) {
        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(
                StoreStatus.REGISTERED,
                storeCategory
        );
    }

    // 전체 스토어
    public List<StoreEntity> getRegisterStore() {
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}

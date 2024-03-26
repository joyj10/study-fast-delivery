package org.delivery.api.domain.storemenu.converter;

import org.delivery.api.comon.annotation.Converter;
import org.delivery.api.comon.error.ErrorCode;
import org.delivery.api.comon.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.db.storemenu.StoreMenuEntity;

import java.util.Optional;

@Converter
public class StoreMenuConverter {
    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it ->
                        StoreMenuEntity.builder()
                                .storeId(request.getStoreId())
                                .name(request.getName())
                                .amount(request.getAmount())
                                .thumbnailUrl(request.getThumbnailUrl())
                                .build()

                )
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity entity) {
        return Optional.ofNullable(entity)
                .map(it ->
                        StoreMenuResponse.builder()
                                .id(entity.getId())
                                .storeId(entity.getStoreId())
                                .name(entity.getName())
                                .amount(entity.getAmount())
                                .status(entity.getStatus())
                                .thumbnailUrl(entity.getThumbnailUrl())
                                .likeCount(entity.getLikeCount())
                                .sequence(entity.getSequence())
                                .build()

                )
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}

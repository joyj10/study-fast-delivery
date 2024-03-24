package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.comon.annotation.Business;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 사용자에 대한 가입처리 로직
     * 1. request -> entity
     * 2. entity -> save
     * 3. save Entity -> response
     * 4. response return
     */
    public UserResponse register(UserRegisterRequest request) {
        UserEntity entity = userConverter.toEntity(request);
        UserEntity saveEntity = userService.register(entity);
        return userConverter.toResponse(saveEntity);

        // 위 코드와 동일, 람다식으로 작성하는 경우 아래와 같이 작성 가능
       /* return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));*/
    }

    /**
     * 1. email, password 를 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. token 생성
     * 4. token response
     */
    public TokenResponse login(UserLoginRequest request) {
        UserEntity userEntity = userService.login(request.getEmail(), request.getPassword());
        return tokenBusiness.issueToken(userEntity);
    }
}

package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    /**
     * 로그인 로직
     *  1. 사용자가 로그인 시도를 하면 입력한 username 가지고 해당 코드 실행
     *  2. 입력한 이메일 기준으로 사용자 검색
     *  3. 값이 없는 경우 예외 발생
     *  4. 값이 있는 경우 값을 담아서 리턴
     *  5. 리턴된 UserDetails를 스프링 시큐리티가 패스워드를 꺼내서 사용자 입력 패스워드를 해시처리 한 다음 비교해서 로그인 처리
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StoreUserEntity storeUserEntity = storeUserService.getRegisterUser(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        StoreEntity storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(storeUserEntity.getStoreId(), StoreStatus.REGISTERED)
                                        .orElseThrow();

        return UserSession.builder()
                .userId(storeUserEntity.getId())
                .email(storeUserEntity.getEmail())
                .password(storeUserEntity.getPassword())
                .status(storeUserEntity.getStatus())
                .role(storeUserEntity.getRole())
                .registeredAt(storeUserEntity.getRegisteredAt())
                .unregisteredAt(storeUserEntity.getUnregisteredAt())
                .lastLoginAt(storeUserEntity.getLastLoginAt())
                .storeId(storeEntity.getId())
                .storeName(storeEntity.getName())
                .build();
    }
}

package org.delivery.db.userorder;

import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {
    // 특정 유저의 모든 주문
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> statusList);

    // 특정 주문
    Optional<UserOrderEntity> findByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);
    Optional<UserOrderEntity> findByIdAndUserId(Long id, Long userId);
}

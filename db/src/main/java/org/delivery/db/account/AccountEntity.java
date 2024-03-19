package org.delivery.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

@SuperBuilder   //부모가 가진 필드도 build에 포함할 수 있음
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)    //객체 비교 시 callSuper = true의 경우 상속 받은 부모 포함 비교
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

}

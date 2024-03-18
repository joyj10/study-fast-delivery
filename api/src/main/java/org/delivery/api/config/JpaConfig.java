package org.delivery.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.delivery.db")   //지정 패키지에 하위에 있는 @Entity 붙은 클래스를 모두 스캔하겠다는 것
@EnableJpaRepositories(basePackages = "org.delivery.db")    //지정 패키지에 하위에 있는 repository를 모두 가져오라는 것(패키지 명을 모두 동일하게 가져가면 자동 스캔하지만 실무에서 프로젝트마다 다르기 때문에 설정 방법을 알고 있는 것이 좋음)
public class JpaConfig {
}

package org.delivery.storeadmin.config.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    /**
     * ObjectMapper 커스텀
     * - 빈 등록시 빈의 이름은 메서드명 따름
     * - 스프링은 ObjectMapper 설정이 별도로 없으면 기본 설정 따라감
     * - 아래와 같이 설정 후 빈 등록 시 해당 설정으로 적용됨
     */
    @Bean
    public ObjectMapper objectMapper() {
        // ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // ObjectMapper 필요 모듈 등록
        objectMapper.registerModule(new Jdk8Module());  // jdk 8 버전 이 클래스(파싱 및 직렬화 등 메서드 포함)
        objectMapper.registerModule(new JavaTimeModule());  // local date

        // ObjectMapper 설정
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);   // 역직렬화 시 모르는 json field에 대해서는 무시하고 파싱한다는 설정(객체가 하지 않지 않더라도 예외 발생하지 않음)
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);    // 직렬화 시 비어있는 빈 무시

        // 날짜 관련 직렬화 설정
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);   // 날짜 관련 설정 disable

        // 스네이크 케이스 설정
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        return objectMapper;
    }
}

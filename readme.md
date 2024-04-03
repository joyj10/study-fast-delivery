# Study Project : Spring Boot + multi module

## memo
- 스프링 부트, 멀티 모듈 기반의 스터디 프로젝트
- 간단한 배달 백엔드 API 서버 개발

## skills
- Spring Boot
- JPA
- MySQL
- Rabbit MQ
- JWT


## module
- common : 공통 모듈
  - RabbitMQ 공통 메시지
- db : DB 연결 모듈(JPA)
  - entity, repository 등 기본 클래스 구현
- api : 기본 API 모듈
  - 회원, 가게, 메뉴, 주문 등 배달 기본 도메인 API 구현
- store-admin : 가게 어드민 API 모듈
  - 가게 유저, 주문 도메인 구현
  - MQ 통한 api 모듈과 통신

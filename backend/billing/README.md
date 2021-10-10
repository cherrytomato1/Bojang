# Billing Server

Pass-Through 로 결제 시스템 진행.

URL
- http://i5a508.p.ssafy.io:8083/

Swagger-ui
- http://i5a508.p.ssafy.io:8083/swagger-ui/

## Dependencies
- JDK 1.8
- Spring Boot 2.4.5
- Gradle 6.7
- Swagger 3.0.0

### Database
- Spring Data JPA
- MariaDB 10.3.30
- Hibernate
- QueryDSL 4.4


## 프로젝트 디렉토리 구조 
- billing/src 하위 구조

```bash
billing/src
├── main
│   ├── generated
│   │   └── com
│   │       └── ssafy
│   │           └── db
│   │               └── entity
│   │                   └── QBilling.java
│   ├── java
│   │   └── com
│   │       └── ssafy
│   │           ├── SsafyApplication.java
│   │           ├── api
│   │           │   ├── controller
│   │           │   │   └── BillingController.java
│   │           │   └── service
│   │           │       ├── BillingService.java
│   │           │       └── BillingServiceImpl.java
│   │           ├── common
│   │           │   ├── exception
│   │           │   │   └── handler
│   │           │   │       ├── AuthException.java
│   │           │   │       ├── BadRequestException.java
│   │           │   │       ├── FileDownloadException.java
│   │           │   │       ├── FileUploadException.java
│   │           │   │       ├── NotFoundHandler.java
│   │           │   │       └── ResourceNotFoundException.java
│   │           │   ├── model
│   │           │   │   └── response
│   │           │   │       └── BaseResponseBody.java
│   │           │   └── util
│   │           │       ├── JwtTokenUtil.java
│   │           │       ├── ResponseBodyWriteUtil.java
│   │           │       └── RestUtil.java
│   │           ├── config
│   │           │   ├── FileUploadConfig.java
│   │           │   ├── JpaConfig.java
│   │           │   └── SwaggerConfig.java
│   │           └── db
│   │               ├── entity
│   │               │   └── Billing.java
│   │               ├── repository
│   │               │   └── BillingRepository.java
│   │               └── util
│   │                   ├── IdGenerator.java
│   │                   └── OrderIdGenerator.java
│   └── resources
│       ├── application.properties
│       └── bojang.jks
└── test
    └── java
        └── com
            └── ssafy
                ├── SsafyApplicationTests.java
                └── common
                    └── util
                        └── RestUtilTest.java
```

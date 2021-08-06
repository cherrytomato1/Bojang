# Auth Server
URL
- http://i5a508.p.ssafy.io:8080/

Swagger-ui
- http://i5a508.p.ssafy.io:8080/swagger-ui/

> [Authorize] 에 발급받은 인증토큰으로 인증 후 API 테스트
>
> 값 : Bearer [인증토큰]

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

### Authentication
- Spring-boot OAuth2 Client
- jjwt 0.9.1

<img width="651" alt="swagger" src="https://user-images.githubusercontent.com/49678555/128445148-8792e5ff-f0d8-4ebe-ba36-7a52bad327e8.png">

## 시퀀스 다이어그램
<img width="843" alt="auth-sequence" src="https://user-images.githubusercontent.com/49678555/128441553-9b6555dc-edc6-4eea-a1c2-56ce50e9e531.png">



## 프로젝트 디렉토리 구조 
- auth/src/main 하위 구조

```bash
auth/src/main
├── generated
│   └── com
│       └── ssafy
│           └── db
│               └── entity
│                   ├── QAuthUser.java
│                   ├── QBankType.java
│                   ├── QUser.java
│                   └── QUserType.java
├── java
│   └── com
│       └── ssafy
│           ├── AuthApplication.java
│           ├── api       /* 사용자 인증 요청관련 컨트롤러, 서비스, 요청/응답 모델 */
│           │   ├── controller
│           │   │   └── UserController.java
│           │   ├── request
│           │   │   └── UserUpdatePatchRequest.java
│           │   ├── response
│           │   │   ├── AuthUserProfileGetResponse.java
│           │   │   ├── UserDeleteResponse.java
│           │   │   ├── UserGetResponse.java
│           │   │   ├── UserIdGetResponse.java
│           │   │   ├── UserIdVaidateResponse.java
│           │   │   └── UserUpdatePatchResponse.java
│           │   └── service
│           │       ├── UserDetailService.java
│           │       ├── UserDetailServiceImpl.java
│           │       ├── UserService.java
│           │       └── UserServiceImpl.java
│           ├── common    /* 유틸리티, DTO, 예외처리 관련 정의*/
│           │   ├── auth
│           │   ├── exception
│           │   │   └── handler
│           │   │       ├── BadRequestException.java
│           │   │       ├── NotFoundHandler.java
│           │   │       ├── OAuth2AuthenticationProcessingException.java
│           │   │       └── ResourceNotFoundException.java
│           │   ├── model
│           │   │   ├── UserTypeEnum.java
│           │   │   └── response
│           │   │       └── BaseResponseBody.java
│           │   └── util
│           │       ├── CookieUtils.java
│           │       ├── JwtTokenUtil.java
│           │       └── ResponseBodyWriteUtil.java
│           ├── config    /* WebMvc, JPA, Security, Swagger 설정 정의*/
│           │   ├── AppConfig.java
│           │   ├── JpaConfig.java
│           │   ├── SecurityConfig.java
│           │   ├── SwaggerConfig.java
│           │   └── WebMvcConfig.java
│           ├── db       /* JPA Entity, Repository 및 DB 유틸리티 */
│           │   ├── entity
│           │   │   ├── AuthUser.java
│           │   │   ├── BankType.java
│           │   │   ├── User.java
│           │   │   └── UserType.java
│           │   ├── repository
│           │   │   ├── AuthUserRepository.java
│           │   │   ├── BankTypeRepository.java
│           │   │   ├── UserRepository.java
│           │   │   └── UserTypeRepository.java
│           │   └── util
│           │       ├── IdGenerator.java
│           │       └── OrderIdGenerator.java
│           └── security    /* Security-OAuth2 엔드포인트 및 유틸리티 */
│               ├── CurrentUser.java
│               ├── OAuthProvider.java
│               ├── RestAuthenticationEntryPoint.java
│               ├── TokenAuthenticationFilter.java
│               ├── TokenProvider.java
│               ├── UserPrincipal.java
│               └── oauth2
│                   ├── CustomOAuth2UserService.java
│                   ├── HttpCookieOAuth2AuthorizationRequestRepository.java
│                   ├── OAuth2AuthenticationFailureHandler.java
│                   ├── OAuth2AuthenticationSuccessHandler.java
│                   └── user
│                       ├── GoogleOAuth2UserInfo.java
│                       ├── KakaoOAuth2UserInfo.java
│                       ├── OAuth2UserInfo.java
│                       └── OAuth2UserInfoFactory.java
└── resources
    ├── application.properties  /* App 설정 */
    └── application.yml         /* OAuth 및 토큰 설정 */

```

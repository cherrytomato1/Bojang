# Store Server
URL
- http://i5a508.p.ssafy.io:8081/

Swagger-ui
- http://i5a508.p.ssafy.io:8081/swagger-ui/

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


## 프로젝트 디렉토리 구조 
- store/src/main 하위 구조

```bash
store/src
├── main
│   ├── generated
│   │   └── com
│   │       └── ssafy
│   │           └── db
│   │               └── entity
│   │                   ├── QBankType.java
│   │                   ├── QBasket.java
│   │                   ├── QFavoriteStore.java
│   │                   ├── QItem.java
│   │                   ├── QItemType.java
│   │                   ├── QMarket.java
│   │                   ├── QOrderInfo.java
│   │                   ├── QOrderItem.java
│   │                   ├── QOrderStatus.java
│   │                   ├── QPayType.java
│   │                   ├── QSales.java
│   │                   ├── QStore.java
│   │                   ├── QStoreLive.java
│   │                   ├── QStoreType.java
│   │                   ├── QUser.java
│   │                   └── QUserType.java
│   ├── java
│   │   └── com
│   │       └── ssafy
│   │           ├── SsafyApplication.java
│   │           ├── api
│   │           │   ├── controller
│   │           │   │   ├── FavoriteController.java
│   │           │   │   ├── LiveController.java
│   │           │   │   ├── MarketController.java
│   │           │   │   ├── SalesController.java
│   │           │   │   └── StoreController.java
│   │           │   ├── request
│   │           │   │   ├── chart
│   │           │   │   │   └── SalesGetRequest.java
│   │           │   │   ├── favorite
│   │           │   │   │   └── FavoriteRegisterPostReq.java
│   │           │   │   ├── sales
│   │           │   │   │   └── SalesUpdatePutRequest.java
│   │           │   │   └── store
│   │           │   │       ├── StoreCategoryGetRequest.java
│   │           │   │       └── StoreCommentPostRequest.java
│   │           │   ├── response
│   │           │   │   ├── favorite
│   │           │   │   │   ├── FavoriteListGetResponse.java
│   │           │   │   │   ├── FavoriteRegisterPostResponse.java
│   │           │   │   │   └── FavoriteSearchGetResponse.java
│   │           │   │   ├── live
│   │           │   │   │   ├── StoreLiveGetResponse.java
│   │           │   │   │   └── StoreLiveListGetResponse.java
│   │           │   │   ├── market
│   │           │   │   │   └── MarketGetResponse.java
│   │           │   │   ├── sales
│   │           │   │   │   ├── SalesGetListResponse.java
│   │           │   │   │   ├── SalesGetResponse.java
│   │           │   │   │   └── SalesUpdateResponse.java
│   │           │   │   └── store
│   │           │   │       ├── StoreGetResponse.java
│   │           │   │       ├── StoreListGetResponse.java
│   │           │   │       └── StorePostResponse.java
│   │           │   └── service
│   │           │       ├── favorite
│   │           │       │   ├── FavoriteService.java
│   │           │       │   └── FavoriteServiceImpl.java
│   │           │       ├── live
│   │           │       │   ├── LiveService.java
│   │           │       │   └── LiveServiceImpl.java
│   │           │       ├── market
│   │           │       │   ├── MarketService.java
│   │           │       │   └── MarketServiceImpl.java
│   │           │       ├── sales
│   │           │       │   ├── SalesService.java
│   │           │       │   └── SalesServiceImpl.java
│   │           │       └── store
│   │           │           ├── StoreService.java
│   │           │           └── StoreServiceImpl.java
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
│   │           │   │       ├── BaseResponseBody.java
│   │           │   │       └── dto
│   │           │   │           └── StoreLiveDto.java
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
│   │               │   ├── BankType.java
│   │               │   ├── Basket.java
│   │               │   ├── FavoriteStore.java
│   │               │   ├── Item.java
│   │               │   ├── ItemType.java
│   │               │   ├── Market.java
│   │               │   ├── OrderInfo.java
│   │               │   ├── OrderItem.java
│   │               │   ├── OrderStatus.java
│   │               │   ├── PayType.java
│   │               │   ├── Sales.java
│   │               │   ├── Store.java
│   │               │   ├── StoreLive.java
│   │               │   ├── StoreType.java
│   │               │   ├── User.java
│   │               │   └── UserType.java
│   │               ├── mapping
│   │               │   ├── sales
│   │               │   │   └── SalesMapping.java
│   │               │   └── store
│   │               │       └── StoreMapping.java
│   │               ├── repository
│   │               │   ├── BankTypeRepository.java
│   │               │   ├── BasketRepository.java
│   │               │   ├── FavoriteStoreRepository.java
│   │               │   ├── FavoriteStoreRepositorySupport.java
│   │               │   ├── ItemRepository.java
│   │               │   ├── ItemTypeRepository.java
│   │               │   ├── MarketRepository.java
│   │               │   ├── OrderInfoRepository.java
│   │               │   ├── OrderItemRepository.java
│   │               │   ├── OrderStatusRepository.java
│   │               │   ├── PayTypeRepository.java
│   │               │   ├── SalesRepository.java
│   │               │   ├── StoreLiveRepository.java
│   │               │   ├── StoreRepositiory.java
│   │               │   ├── StoreTypeRepository.java
│   │               │   ├── UserRepository.java
│   │               │   └── UserTypeRepository.java
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
                ├── api
                │   └── controller
                │       └── FavoriteControllerTest.java
                ├── common
                │   └── util
                │       └── RestUtilTest.java
                └── db
                    └── repository
                        ├── FavoriteStoreRepositorySupportTest.java
                        ├── FavoriteStoreRepositoryTest.java
                        ├── MarketRepositoryTest.java
                        ├── OrderItemRepositoryTest.java
                        ├── OrderListRepositoryTest.java
                        ├── SalesRepositoryTest.java
                        ├── StoreRepositioryTest.java
                        ├── StoreTypeRepositoryTest.java
                        └── UserRepositoryTest.java
```

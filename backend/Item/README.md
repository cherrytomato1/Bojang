# Item Server
URL
- http://i5a508.p.ssafy.io:8082/

Swagger-ui
- http://i5a508.p.ssafy.io:8082/swagger-ui/

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
- item/src/main 하위 구조

```bash
store/src/main
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
│   │           ├── ItemApplication.java
│   │           ├── api
│   │           │   ├── controller
│   │           │   │   ├── BasketController.java
│   │           │   │   ├── ItemController.java
│   │           │   │   ├── OrderController.java
│   │           │   │   ├── OrderInfoController.java
│   │           │   │   └── OrderItemController.java
│   │           │   ├── request
│   │           │   │   ├── basket
│   │           │   │   │   ├── BasketIdListDeleteRequest.java
│   │           │   │   │   ├── BasketPatchRequest.java
│   │           │   │   │   └── BasketPutRequest.java
│   │           │   │   ├── item
│   │           │   │   │   ├── ItemPatchRequest.java
│   │           │   │   │   └── ItemPutRequest.java
│   │           │   │   ├── order
│   │           │   │   │   └── OrderPostRequest.java
│   │           │   │   └── orderinfo
│   │           │   │       ├── OrderInfoPatchRequest.java
│   │           │   │       └── PickStatusPostRequest.java
│   │           │   ├── response
│   │           │   │   ├── basket
│   │           │   │   │   ├── BasketDeleteResponse.java
│   │           │   │   │   ├── BasketListGetResponse.java
│   │           │   │   │   ├── BasketPatchResponse.java
│   │           │   │   │   └── BasketPutResponse.java
│   │           │   │   ├── item
│   │           │   │   │   ├── ItemDeleteResponse.java
│   │           │   │   │   ├── ItemListGetResponse.java
│   │           │   │   │   ├── ItemPatchResponse.java
│   │           │   │   │   └── ItemPutResponse.java
│   │           │   │   ├── order
│   │           │   │   │   └── OrderPostResponse.java
│   │           │   │   ├── orderInfo
│   │           │   │   │   ├── OrderInfoGetResponse.java
│   │           │   │   │   └── OrderInfoListGetResponse.java
│   │           │   │   └── orderitem
│   │           │   │       └── StoreOrderItemListGetResponse.java
│   │           │   └── service
│   │           │       ├── basket
│   │           │       │   ├── BasketService.java
│   │           │       │   └── BasketServiceImpl.java
│   │           │       ├── item
│   │           │       │   ├── ItemService.java
│   │           │       │   └── ItemServiceImpl.java
│   │           │       ├── market
│   │           │       │   ├── MarketService.java
│   │           │       │   └── MarketServiceImpl.java
│   │           │       ├── order
│   │           │       │   ├── OrderService.java
│   │           │       │   └── OrderServiceImpl.java
│   │           │       ├── orderInfo
│   │           │       │   ├── OrderInfoService.java
│   │           │       │   └── OrderInfoServiceImpl.java
│   │           │       ├── orderitem
│   │           │       │   ├── OrderItemService.java
│   │           │       │   └── OrderItemServiceImpl.java
│   │           │       └── store
│   │           │           ├── StoreService.java
│   │           │           └── StoreServiceImpl.java
│   │           ├── common
│   │           │   ├── exception
│   │           │   │   └── handler
│   │           │   │       ├── AuthException.java
│   │           │   │       ├── BadRequestException.java
│   │           │   │       ├── DuplicateBasketItemException.java
│   │           │   │       ├── FileDownloadException.java
│   │           │   │       ├── FileUploadException.java
│   │           │   │       ├── NotFoundHandler.java
│   │           │   │       ├── ResourceNotFoundException.java
│   │           │   │       └── RestTemplateException.java
│   │           │   ├── model
│   │           │   │   ├── dto
│   │           │   │   │   ├── BasketResponseDto.java
│   │           │   │   │   ├── OrderItemRequestDto.java
│   │           │   │   │   └── StoreOrderItemDto.java
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
                ├── ItemApplicationTests.java
                ├── api
                │   ├── controller
                │   │   └── ItemControllerTest.java
                │   └── service
                │       └── order
                │           └── OrderServiceImplTest.java
                ├── common
                │   └── util
                │       └── RestUtilTest.java
                └── db
                    └── repository
                        ├── OrderInfoRepositoryTest.java
                        └── OrderItemRepositoryTest.java

```

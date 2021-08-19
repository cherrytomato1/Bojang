<template>
  <v-app class="color5">
    <v-container>
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            주문/결제
          </h2>
        </v-col>
        <v-col
          offset="10"
        >
          <p>
            장바구니 > 주문결제 > 완료
          </p>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          offset="1"
        >
          <h3>구매자 정보</h3><br>

          <!-- 이 부분에서 mapgetters 가져오는 건 맞는것 같은데 created hook error -->
          <!-- <p>{{ $store.getters.userData.name }}</p>
          <p>{{ $store.getters.userData.phoneNumber }}</p> -->


          <!-- <v-text>이름 : 김싸피</v-text><br>
          <v-text>휴대폰 번호 : 010-1234-5678</v-text> -->
          <!-- 이메일은 안 할것 -->
          <!-- <v-text>이메일 : ssafy@ssafy.com</v-text><br> -->
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          offset="1"
        >
          <h3>주문 내역</h3><br>
          <!-- <p>{{ $store.getters.basketList[0].storeName }}</p> -->
          <!-- <v-text>싸피시장</v-text><br> -->
          <div
            v-for="(fs, index) in basketList"
            :key="index"
          >
            <v-container>
              <v-row>
                <v-col
                  cols="2"
                >
                  <!-- size mx-auto가 안되네 추후 설정 -->
                  <img
                    src="@/assets/fish1.png"
                    alt="가게 사진"
                    style="width:60px"
                  >
                </v-col>
                <v-col
                  cols="2"
                >
                  {{ fs.storeName }}<br>
                  <h3>{{ fs.basket.item.name }}</h3>
                </v-col>
                <v-col
                  cols="2"
                  offset="4"
                >
                  <br>
                  {{ fs.basket.amount }}
                </v-col>
                <v-col
                  cols="2"
                >
                  <br>
                  {{ fs.basket.item.price }}원
                </v-col>
              </v-row>
            </v-container>
            <v-container>
              <v-row>
                <v-col>
                  <!-- {{ fs.basket.amount }} -->
                  <!-- comment 쓰기가 어렵네. 장바구니에는 없어서 -->
                <!-- {{ $store.getters.orderList.orderItemList.comment }} -->
                </v-col>
              </v-row>
            </v-container>
          </div>
        </v-col>
      </v-row>
    </v-container>

    <v-container>
      <v-row>
        <v-col
          cols="12"
          offset="1"
        >
          <h4>결제 정보</h4><br>
          <span>총 결제금액 26,000원</span>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col
          cols="2"
          offset="1"
        >
          <br>
          <p>
            결제 방법
          </p>
        </v-col>
        <v-col
          cols="1"
        >
          <v-checkbox value />
        </v-col>
        <v-col
          cols="2"
        >
          <br>
          <p>
            카드 결제
          </p>
        </v-col>
        <v-col
          cols="1"
        >
          <v-checkbox value />
        </v-col>
        <v-col
          cols="2"
        >
          <br>
          <p>
            카카오 페이
          </p>
        </v-col>
      </v-row>
    </v-container>
    <br>
    <br>
    <v-container>
      <v-row>
        <v-col
          offset="6"
        >
          <!-- <br> -->
          <v-btn
            color="blue"
            to="/finalorderdetail"
          >
            <span>
              결제하기
            </span>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: 'OrderPayment',
  data() {
    return{
      name: Object,
    }
  },
  // methods: {
  //   axios({
  //       method:'get',
  //       url:'http://localhost:8082/api/basket',
  //       headers:{
  //         Authorization: `Bearer `+ this.$store.getters.getToken
  //       },
  //       data:{
  //         amount: num,
  //         itemId: id,
  //       }
  //     })
  //     .then(() => {
  //       alert("장바구니에 상품을 넣었습니다.");
  //     })
  //     .catch(() => {
  //       alert("장바구니에 해당 상품이 이미 있습니다.");
  //     }),
  computed:{
    ...mapGetters(["basketList", "getToken", "userData"])
    // ...mapGetters(["basketList", "getToken"])
  },
  // 이 부분 다시 찾아보기 여러개로 써야 되는건지 따로 써야 되는지 등
  created() {
    // this.$store.dispatch(["getBasketList", "getUserData", "getOrderList"]);
    // this.$store.dispatch("getOrderList");
    this.$store.dispatch(["getBasketList"]);
    // this.$store.dispatch(["getUserData"]);
    // this.$store.dispatch(["getOrderList"]);
    // this.$store.dispatch("getUserData");
  }

};
</script>

<style>

</style>

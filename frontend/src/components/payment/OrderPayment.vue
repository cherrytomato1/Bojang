<template>
  <v-app class="color5">
    <v-container>
      <v-row>
        <v-col cols="8">
          <h2>
            주문/결제
          </h2>
        </v-col>
        <v-col offset="10">
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
          <h3>구매자 정보</h3>
          <br>

          <!-- 이 부분에서 mapgetters 가져오는 건 맞는것 같은데 created hook error -->
          <p>이름 : {{ $store.getters.userData.name }}</p>
          <p>휴대폰번호 : {{ $store.getters.userData.phoneNumber }}</p>

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
          <h3>주문 내역</h3>
          <br>
          <!-- <p>{{ $store.getters.basketList[0].storeName }}</p> -->
          <!-- <v-text>싸피시장</v-text><br> -->
          <div
            v-for="(fs, index) in basketList"
            :key="index"
          >
            <v-container>
              <v-row>
                <v-col cols="2">
                  <!-- size mx-auto가 안되네 추후 설정 -->
                  <img
                    src="@/assets/fish1.png"
                    alt="가게 사진"
                    style="width:60px"
                  >
                </v-col>
                <v-col cols="2">
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
                <v-col cols="2">
                  <br>
                  {{ fs.basket.item.price * fs.basket.amount }}원
                </v-col>
                <!-- input field 넣어서 요청사항 출력시키기 -->
                <v-col cols="4">
                  <v-text-field
                    v-model="comment[index]"
                    dense
                    placeholder="요청사항을 입력하세요"
                  />
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
          <h4>결제 정보</h4>
          <br>
          <span>총 결제금액: {{ total }} 원</span>
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
        <v-col cols="1">
          <v-checkbox value />
        </v-col>
        <v-col cols="2">
          <br>
          <p>
            카드 결제
          </p>
        </v-col>

        <!-- <v-col
          cols="2"
        >

        </v-col> -->
      </v-row>
    </v-container>
    <br>
    <br>
    <v-container>
      <v-row>
        <v-col offset="6">
          <!-- <br> -->
          <v-btn
            color="blue"
            @click="billing"
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
import { mapGetters } from "vuex";
import axios from "axios";
// import store from '@/store/store';

// const message = $store.getters.basketList.basket.item.comment

export default {
  name: "OrderPayment",
  data() {
    return {
      name: Object,
      comment: [],
      total: 0
    };
  },

  computed: {
    ...mapGetters(["basketList", "getToken", "userData"])
    // ...mapGetters(["basketList", "getToken"])
  },
  watch: {
    basketList() {
      this.totalHandler();
    }
  },
  created() {
    // this.$store.dispatch(["getBasketList", "getUserData"]);
    // this.$store.dispatch("getOrderList");
    this.$store.dispatch("getBasketList");
  },
  methods: {
    totalHandler: function() {
      // console.log("###" + this.basketList)
      console.log(this.basketList);

      this.basketList.forEach(basketItem => {
        this.total += basketItem.basket.amount * basketItem.basket.item.price;
      });
      console.log(this.total);
    },

    billing: function() {
      const orderItemList = [];

      // console.log(this.this.comment)
      let index = 0;
      this.basketList.forEach(basketItem => {
        let data = new Object();
        data.amount = basketItem.basket.amount;
        data.itemId = basketItem.basket.item.id;
        // console.log(this.this.comment)
        data.comment = this.comment[index++];
        orderItemList.push(data);
      });
      // console.log(orderItemList);
      axios({
        method: "post",
        url: "https://i5a508.p.ssafy.io:8082/api/billing",
        headers: {
          Authorization: `Bearer ` + this.$store.getters.getToken
        },
        data: {
          orderItemList: orderItemList,
          payTypeId: 1,
          userId: this.userData.id
        }
      })
        .then(() => {
          this.$store.dispatch("getOrderList", NaN);
          alert("결제 완료 되었습니다.");
          this.$router.push("/");
        })
        .catch(() => {
          alert("결제 실패");
        });
    }
  }
};
</script>

<style></style>

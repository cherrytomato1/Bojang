<template>
  <v-app class="color5">
    <v-container>
      <!-- grid로 조절했는데 아직 한줄로 안됨 조절해보기-->
      <v-row>
        <v-col offset="1" cols="8">
          <h2>
            주문 상세정보
          </h2>
        </v-col>
        <v-col offset="10">
          <!-- font color 적용이 잘 안됨 수정하기 -->
          <!-- <span>
            장바구니
          </span>
          <span> > 주문결제</span>
          <span> > 완료</span> -->
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col cols="6" offset="1">
          <span>주문 일자 {{ order.registerTime.split(" ")[0] }}</span>
        </v-col>
        <v-col cols="5">
          <span
            >주문번호 : {{ order.id.substring(1, order.id.length - 1) }}</span
          >
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col cols="12" offset="1">
          <h4>주문 상세정보</h4>
          <br />
          <span>{{ order.market.name }}</span
          ><br />
          <div v-for="(orderItem, index) in order.orderItemList" :key="index">
            <v-container>
              <v-row>
                <v-col cols="2">
                  <img
                    :src="
                      'http://localhost:8082/api/item/downloadFile/' +
                        orderItem.item.image
                    "
                    alt="상품 사진"
                    style="width:60px"
                  />
                </v-col>
                <v-col cols="2">
                  <!-- <span> 민기네 수산 </span><br /> -->
                  <span> {{ orderItem.item.name }} </span>
                </v-col>
                <v-col cols="2" offset="4">
                  <br />
                  <span> {{ orderItem.amount }} </span>
                </v-col>
                <v-col cols="2">
                  <br />
                  <span>
                    {{ orderItem.item.price * orderItem.amount }} 원
                  </span>
                </v-col>
              </v-row>
            </v-container>
            <v-container>
              <v-row>
                <v-col>
                  <span>{{ orderItem.comment }}</span>
                </v-col>
              </v-row>
            </v-container>
          </div>
        </v-col>
      </v-row>
    </v-container>

    <v-container>
      <v-row>
        <v-col cols="5" offset="1">
          <span>결제 정보</span><br />
          <span>결제 수단</span>
        </v-col>
        <v-col cols="4">
          <span>총 상품 가격</span>
          <h5>
            {{ order.payType.name }}
          </h5>
          <span>총 결제금액</span>
        </v-col>
        <v-col cols="2">
          <p>{{ order.price }} 원</p>
          <h5>{{ order.price }} 원</h5>
          <p>{{ order.price }} 원</p>
        </v-col>
      </v-row>
    </v-container>

    <br />
    <br />
    <v-container>
      <v-row>
        <v-col offset="1">
          <br />
          <v-btn color="light-blue" to="/ordercheck">
            <span> 주문 목록 돌아가기</span>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "OrderDetail",
  data() {
    return {
      name: "",
      comment: "",
      order: null,
      isLoaded: false
    };
  },
  mounted() {
    console.log(this.$route.params.id);
    console.log(this.orderList);
    this.order = this.orderList[this.$route.params.id];
    this.isLoaded = true;
    console.log(this.order);
  },
  computed: {
    ...mapGetters(["orderList", "getToken"])
  }
};
</script>

<style></style>

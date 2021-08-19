<template>
  <v-app class="color5">
    <v-container>
      <!-- grid로 조절했는데 아직 한줄로 안됨 조절해보기-->
      <v-row>
        <v-col
          offset="1"
          cols="8"
        >
          <h2>
            주문 상세정보
          </h2>
        </v-col>
        <v-col
          offset="10"
        >
          <!-- font color 적용이 잘 안됨 수정하기 -->
          <span>
            장바구니
          </span>
          <span> > 주문결제</span>
          <span> > 완료</span>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col
          cols="6"
          offset="1"
        >
          <!-- <span>주문 일자 : 2021.07.21</span> -->
          <!-- {{ $store.getters.orderList }} -->
        </v-col>
        <v-col
          cols="5"
        >
          <span>주문번호 : 202107211234566879</span>
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
          <!-- <span>싸피시장</span><br> -->
          <div
            v-for="(fs, index) in orderList"
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
                  {{ fs.name }}<br>
                  <span> 민기네 수산 </span><br>
                  <span> 고등어 </span>
                </v-col>
                <v-col
                  cols="2"
                  offset="4"
                >
                  <br>
                  <span> 13 </span>
                </v-col>
                <v-col
                  cols="2"
                >
                  <br>
                  <span> 2,000 원 </span>
                </v-col>
              </v-row>
            </v-container>
            <v-container>
              <v-row>
                <v-col>
                  <span>요청사항 : 고등어 손질 부탁드려요!</span>
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
          cols="5"
          offset="1"
        >
          <span>결제 정보</span><br>
          <span>결제 수단</span><br>
          <span>국민 카드</span>
        </v-col>
        <v-col
          cols="4"
        >
          <span>총 상품 가격</span>
          <h5>
            국민 카드
          </h5>
          <span>총 결제금액</span>
        </v-col>
        <v-col
          cols="2"
        >
          <p>26000원</p>
          <h5>
            26000원
          </h5>
          <p>26000원</p>
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
          <span>
            결제 방법
          </span>
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
          <span>
            카드 결제
          </span>
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
          <span>
            카카오 페이
          </span>
        </v-col>
      </v-row>
    </v-container>
    <br>
    <br>
    <v-container>
      <v-row>
        <v-col
          offset="1"
        >
          <br>
          <v-btn
            color="light-blue"
            to="/ordercheck"
          >
            <span> 주문 목록 돌아가기</span>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import {mapGetters} from "vuex";
import axios from 'axios'


export default {
  name: 'OrderDetail',
  data() {
    return{
      name: '',
      comment: '',
    }
  },
    computed:{
      ...mapGetters(["orderList", "getToken"])
    },
    created() {
      this.$store.dispatch("getOrderList");
    }
    // methods: {
    //   // submit: function(name,phoneNumber) {
    //     // 400 error 발생 해결방법
    //   storeSearch: function() {
    //     axios({
    //       method:'get',
    //       url:'http://localhost:8081/api/favorite/search',
    //       headers:{
    //         Authorization: `Bearer `+ this.$store.getters.getToken
    //       },
    //       data:{
    //         name: this.name,
    //         comment: this.comment,
    //       }
    //     })
    //     .then((res) => {
    //       // console.log(res)
    //       // alert("회원정보가 변경되었습니다.");[]
    //     })
    //     .catch((err) => {
    //       // console.log(err)
    //       alert("검색한 가게는 단골가게가 아닙니다. 확인해주세요");
    //     });
      // },
    // },
};
</script>

<style>

</style>


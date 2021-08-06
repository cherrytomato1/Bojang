<template>
  <v-app class="color5">
    <v-container>
      <!-- grid로 조절했는데 아직 한줄로 안됨 조절해보기-->
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
          <!-- font color 적용이 잘 안됨 수정하기 -->
          <v-text>
            장바구니
          </v-text>
          <v-text> > 주문결제</v-text>
          <v-text> > 완료</v-text>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          offset="1"
        >
          <p>구매자 정보</p>
          <v-text>이름 : 김싸피</v-text><br>
          <v-text>이메일 : ssafy@ssafy.com</v-text><br>
          <v-text>휴대폰 번호 : 010-1234-5678</v-text>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          offset="1"
        >
          <p>주문 내역</p>
          <v-text>싸피시장</v-text><br>
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
                <v-text> 민기네 수산 </v-text><br>
                <v-text> 고등어 </v-text>
              </v-col>
              <v-col
                cols="2"
                offset="4"
              >
                <br>
                <v-text> 13 </v-text>
              </v-col>
              <v-col
                cols="2"
              >
                <br>
                <v-text> 2,000 원 </v-text>
              </v-col>
            </v-row>
          </v-container>
          <v-container>
            <v-row>
              <v-col>
                <v-text>요청사항 : 고등어 손질 부탁드려요!</v-text>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
    </v-container>

    <v-container>
      <v-row>
        <v-col
          cols="12"
          offset="1"
        >
          <p>결제 정보</p>
          <v-text>총 결제금액 26,000원</v-text><br>
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
          <v-text>
            결제 방법
          </v-text>
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
          <v-text>
            카드 결제
          </v-text>
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
          <v-text>
            카카오 페이
          </v-text>
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
          <br>
          <v-btn
            color="blue"
            to="/finalorderdetail"
          >
            <v-text>결제하기</v-text>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: 'OrderPayment',
}
</script>

<style>

</style>

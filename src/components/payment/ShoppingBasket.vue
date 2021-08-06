<template>
  <v-app>
    <v-container>
      <!-- grid로 조절했는데 아직 한줄로 안됨 조절해보기-->
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            장바구니
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
          cols="2"
          offset="1"
        >
          <p>SSAFY 시장</p>
        </v-col>
      </v-row>
    </v-container>
    <v-form>
      <v-container>
        <v-row>
          <v-col
            cols="1"
          >
            <!-- 체크가 왜 안되지? -->
            <v-checkbox value />
          </v-col>
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
            <br>
            <v-text> 고등어 </v-text>
          </v-col>
          <v-col
            cols="3"
          >
            <br>
            <v-btn
              class="mx-2"
              fab
              dark
              x-small
              color="indigo"
            >
              <v-icon dark>
                mdi-minus
              </v-icon>
            </v-btn>
            <v-text> 1 </v-text>
            <v-btn
              class="mx-2"
              fab
              dark
              x-small
              color="indigo"
            >
              <v-icon dark>
                mdi-plus
              </v-icon>
            </v-btn>
          </v-col>
          <v-col
            cols="2"
          >
            <br>
            <v-text> 2,000 원 </v-text>
          </v-col>
          <v-col
            cols="2"
          >
            <br>
            <!-- x 표시 아이콘 찾으면 적용해주세요 -->
            <v-icon>x</v-icon>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col
            offset="9"
          >
            <v-text>전체금액 : 2,000원</v-text>
          </v-col>
        </v-row>
      </v-container>
    </v-form>

    <v-container>
      <v-row>
        <v-col
          cols="1"
        >
          <v-checkbox value />
        </v-col>
        <br>
        <v-col
          cols="2"
        >
          <br>
          <v-btn>
            <p>선택 삭제</p>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col
          offset="9"
        >
          <v-text>전체 주문 금액 : 2,000원</v-text>
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
            to="/payment"
          >
            <p>주문하기</p>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
<script>
export default {
  name: 'ShoppingBasket',
}
</script>

<style>
.vll {
  background-color: #FF6F61 !important;
}
.color1{
  background-color: #cff0da !important;
}
.color2{
  background-color: #88dba3 !important;
}
.color3{
  background-color: #dadbdb !important;
}
.color4{
  background-color: #3ac569 !important;
}
</style>

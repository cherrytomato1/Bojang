<template>
  <v-main>
    <v-app>
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
            cols="6"
            offset="1"
          >
            <v-text>주문 일자 : 2021.07.21</v-text>
          </v-col>
          <v-col
            cols="5"
          >
            <v-text>주문번호 : 202107211234566879</v-text>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col
            cols="12"
            offset="1"
          >
            <h5>주문 내역</h5>
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
      <!-- <v-container>
        <v-row>
          <v-col
            cols="6"
          >
            <v-text>
              결제수단
            </v-text>
          </v-col>
        </v-row>
      </v-container> -->
      <v-container>
        <v-row>
          <v-col
            cols="5"
            offset="1"
          >
            <p>결제 정보</p>
            <p>결제 수단</p>
            <p>국민 카드</p>
          </v-col>
          <v-col
            cols="4"
          >
            <p>총 상품 가격</p>
            <h5>
              국민 카드
            </h5>
            <p>총 결제금액</p>
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
            offset="1"
          >
            <br>
            <v-btn
              color="light-blue"
              to="/ordercheck"
            >
              <v-text> 주문 목록 돌아가기</v-text>
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-app>
  </v-main>
</template>

<script>
export default {
  name: 'OrderDetail',
}
</script>

<style>

</style>


<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            주문 내역
          </h2>
        </v-col>
        <v-col
          cols="4"
        >
          <v-text-field
            v-col
            class="col-4"
            flat
            hide-details
            label="Search"
            prepend-inner-icon="mdi-magnify"
            solo-inverted
          />
        </v-col>
      </v-row>
      <v-col
        cols="12"
        class="py-2"
      >
        <v-btn-toggle
          v-model="text"
          tile
          color="deep-purple accent-3"
          group
        >
          <v-btn value="left">
            최근 6개월
          </v-btn>
          <v-btn value="center">
            2021
          </v-btn>
          <v-btn value="right">
            2020
          </v-btn>
          <v-btn value="justify">
            2019
          </v-btn>
        </v-btn-toggle>
      </v-col>
    </v-container>
    <!-- outlined 적용이 잘 안되는데 다시 적용해보기 -->
    <v-container>
      <v-row>
        <v-col
          cols="4"
        >
          <!-- 주문 날짜 불러오기 -->
          <v-text>2021.07.23 주문</v-text>
        </v-col>
        <v-col
          cols="2"
        >
          <v-row
            row="2"
          >
            <!-- 주문 날짜 불러오기 -->
            <v-text>싸피시장</v-text>
          </v-row>
        </v-col>

        <v-col
          cols="2"
        >
          <v-text>
            106,000원
          </v-text>
        </v-col>
        <v-col
          cols="2"
        >
          <p>
            결제 확인
          </p>
        </v-col>
        <v-col
          cols="2"
        >
          <v-btn
            color="warning"
          >
            결제 취소
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <v-form>
      <v-container>
        <v-row>
          <v-col
            cols="2"
          >
            <!-- size mx-auto가 안되네 추후 설정 -->
            <img
              src="@/assets/logo.png"
              alt="가게 사진"
              style="width:60px"
            >
          </v-col>

          <v-col
            cols="4"
          >
            <!-- 입력한 필드가 아닌 데이터를 가져와야됨 변경필요 -->
            <v-text>
              가게이름<br>
              제품 명<br>
              수량: 1 <br>
            </v-text>
          </v-col>
          <v-col
            cols="2"
          >
            <v-text>
              개당 가격<br>
              주문 일자<br>
            </v-text>
          </v-col>
          <v-col
            cols="4"
          >
            <v-text>
              요청사항<br>
            </v-text>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col
            cols="2"
          >
            <!-- size mx-auto가 안되네 추후 설정 -->
            <img
              src="@/assets/logo.png"
              alt="가게 사진"
              style="width:60px"
            >
          </v-col>

          <v-col
            cols="4"
          >
            <!-- 입력한 필드가 아닌 데이터를 가져와야됨 변경필요 -->
            <v-text>
              가게이름<br>
              제품 명<br>
              수량: 1 <br>
            </v-text>
          </v-col>
          <v-col
            cols="2"
          >
            <v-text>
              개당 가격<br>
              주문 일자<br>
            </v-text>
          </v-col>
          <v-col
            cols="4"
          >
            <v-text>
              요청사항<br>
            </v-text>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-row>
      <v-col
        cols="12"
        offset="9"
      >
        <v-btn
          color="success"
        >
          주문 상세보기
        </v-btn>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>

export default {
  name: 'OrderList',
}
</script>

<style>

</style>

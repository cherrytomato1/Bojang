<template>
  <v-app class="color5">
    <v-container>
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            단골 가게 관리
          </h2>
        </v-col>
        <v-col
          cols="4"
        >
          <v-text-field
            flat
            hide-details
            label="Search"
            prepend-inner-icon="mdi-magnify"
            solo-inverted
          />
        </v-col>
      </v-row>
    </v-container>
    <v-form>
      <v-container>
        <v-row>
          <v-col
            cols="2"
          >
            <!-- 체크가 왜 안되지? -->
            <v-checkbox value />
          </v-col>
          <v-col
            cols="2"
          >
            <!-- size mx-auto가 안되네 추후 설정 -->
            <img
              src="@/assets/fish_store.png"
              alt="가게 사진"
              style="width:60px"
            >
          </v-col>

          <v-col
            cols="2"
          >
            <v-text>
              싸피시장<br>
              <h3>민기네 수산</h3><br>
              수산
            </v-text>
          </v-col>
          <v-col
            cols="4"
          >
            <v-text>
              <br>
              싱싱한 생선 팔아요
            </v-text>
          </v-col>
          <v-col
            cols="2"
          >
            <!-- x 표시 아이콘 찾으면 적용해주세요 -->
            <v-icon>x</v-icon>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col
            cols="2"
          >
            <!-- 체크가 왜 안되지? -->
            <v-checkbox value />
          </v-col>
          <v-col
            cols="2"
          >
            <!-- size mx-auto가 안되네 추후 설정 -->
            <img
              src="@/assets/fruit.png"
              alt="가게 사진"
              style="width:60px"
            >
          </v-col>

          <v-col
            cols="2"
          >
            <v-text>
              싸피시장<br>
              <h3>수민이네 과일</h3><br>
              맛있는 과일
            </v-text>
          </v-col>
          <v-col
            cols="4"
          >
            <v-text>
              <br>
              신선하고 맛있는 과일!
            </v-text>
          </v-col>
          <v-col
            cols="2"
          >
            <!-- x 표시 아이콘 찾으면 적용해주세요 -->
            <v-icon>x</v-icon>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </v-app>
</template>

<script>
export default {
  name: 'FrequentStore',
  data () {
      return {
        store:
          {
            name: '싸피수산',
            image: '',
            ment: '싸피수산은 싸피시장에서 30년 이상 장사한 가게입니다. 믿음과 신뢰를 바탕으로 판매하고 있습니다.',
            products: [{
              image: '',
              name: '갈치',
              ment: '몸에 좋고 맛도 좋은 갈치가 1마리에 5천원',
              price: '5,000',
              selling: true,
            },{
              image: '',
              name: '꽁치',
              ment: '몸에 좋고 맛도 좋은 꽁치가 1마리에 6천원',
              price: '6,000',
              selling: true,
            },{
              image: '',
              name: '쥐치',
              ment: '몸에 좋고 맛도 좋은 쥐치가 1마리에 7천원',
              price: '7,000',
              selling: false,
            },{
              image: '',
              name: '자갈치',
              ment: '몸에 좋고 맛도 좋은 자갈치가 1마리에 8천원',
              price: '8,000',
              selling: true,
            }],
          },
      }
    },
}
</script>

<style>
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
.color5{
  background-color: #fFFFF3 !important;
}
</style>

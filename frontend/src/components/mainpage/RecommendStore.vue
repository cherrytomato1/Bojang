<template>
  <div
    class="color1 container"
  >
    <h3>이 가게는 어때요?</h3>
    <v-sheet
      class="mx-auto"
      elevation="8"
      max-width="800"
    >
      <v-slide-group
        v-model="model"
        class="pa-4"
        center-active
        show-arrows
      >
        <v-slide-item
          v-for="item in items"
          :key="item.tabName"
          v-slot="{ active, toggle }"
        >
          <v-card
            :color="active ? 'color2' : 'grey lighten-1'"
            class="ma-4"
            height="200"
            width="100"
            @click="toggle"
          >
            <!-- 수정 -->
            <v-img
              :src="item.image"
            />

            <v-row
              class="fill-height"
              align="center"
              justify="center"
            >
              <v-scale-transition>
                <v-icon
                  v-if="active"
                  color="white"
                  size="48"
                />
              </v-scale-transition>
            </v-row>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<style scoped>
.color1{
  background-color: #cff0da  !important;
}
.color2{
  background-color: #88dba3   !important;
}
.color3{
  background-color: #dadbdb  !important;
}
.color4{
  background-color: #3ac569 !important;
}
</style>

<script>
  export default {
    data: () => ({
      model: null,
      items: [
          {tabName:'아담부각',image:require("../../assets/store/store_1.jpg")},
          {tabName:'인계잡곡',image:require("../../assets/store/store_2.jpg")},
          {tabName:'소백산흥부네',image:require("../../assets/store/store_3.jpg")},
          {tabName:'착한칼국수',image:require("../../assets/store/store_4.jpg")},
          {tabName:'어사또수산',image:require("../../assets/store/store_5.jpeg")},
          {tabName:'동성분식',image:require("../../assets/store/store_6.jpeg")},
          {tabName:'금산인삼',image:require("../../assets/store/store_7.jpeg")},
          {tabName:'경진 김',image:require("../../assets/store/store_8.jpeg")},
          {tabName:'영화해물',image:require("../../assets/store/store_9.jpeg")},
          {tabName:'지동기물',image:require("../../assets/store/store_10.jpeg")},
          {tabName:'부부젓갈',image:require("../../assets/store/store_11.jpeg")},

        ],
    }),
  }
</script>

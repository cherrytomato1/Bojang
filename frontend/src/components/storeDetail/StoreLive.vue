<template>
  <v-app class="color5">
    <v-card
      class="mx-auto"
      max-width="375"
    >
      <!-- img height를 812 - 184 = 로 우선설정 -->
      <v-img
        class="white--text align-end"
        height="628px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
      <!-- <v-card-title>Top 10 Australian beaches</v-card-title> -->
      </v-img>
      <!-- 아래는 채팅창 / 이후 채팅창이 화면 안에 하단으로 들어와야 함. -->

      <v-card-subtitle class="pb-0">
        채팅창
      </v-card-subtitle>

      <v-card-text
        class="text--primary"
      >
        <div>채팅내역1</div>
        <div>채팅내역2</div>
      </v-card-text>

      <v-card-actions>
        <v-text-field />

        <v-btn
          icon
          color="blue"
        >
          <v-icon>
            mdi-send
          </v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-app>
</template>

<style>
/* Helper classes */
.basil {
  background-color: #FFFBE6 !important;
}
.vll {
  background-color: #FF6F61 !important;
}
.color1{
  background-color: #cff0da   !important;
}
.color2{
  background-color: #88dba3   !important;
}
.color3{
  background-color: #dadbdb    !important;
}
.color4{
  background-color: #3ac569 !important;
}

.basil--text {
  color: #3ac569 !important;
}
</style>

<script>
  export default {
  }
</script>

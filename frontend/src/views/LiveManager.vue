<template>
  <v-app class="color5">
    <v-card
      class="mx-auto"
      max-width="375"
    >
      <!-- img 812 * 375-->
      <v-img
        class="white--text align-end"
        height="812px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
      <!-- <v-card-title>Top 10 Australian beaches</v-card-title> -->
      </v-img>
      <!-- 아래는 채팅창 / 이후 채팅창이 화면 안에 하단으로 들어와야 함. -->
    </v-card>
  </v-app>
</template>

<script>

export default {
  name: 'LiveManager',

  data(){
    return{
      view:1
    }
  },

  methods: {
    change(no){
      if(this.tab != no) {
         return this.tab = no
      }
    }
  }
}
</script>

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



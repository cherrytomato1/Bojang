<template>
  <v-btn
    elevation="5"
    x-large
  />
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
    data () {
      return {
        tab: null,
        items: [
          {tabName:'단골가게',image:require("../../assets/map/MarketMap_7.jpg"),
          stores:['동아미용실','충남상회','형제사','남해건어물','남문뻥튀기','남문상회','완도고금상회','월계슈퍼','풍년종묘농약사','재호이불','완도상회']},
          {tabName:'농산물',image:require("../../assets/map/MarketMap_1.jpg")},
          {tabName:'축산물',image:require("../../assets/map/MarketMap_2.jpg")},
          {tabName:'수산물',image:require("../../assets/map/MarketMap_3.jpg")},
          {tabName:'가공식품',image:require("../../assets/map/MarketMap_4.jpg")},
          {tabName:'의류신발',image:require("../../assets/map/MarketMap_5.jpg")},
          {tabName:'가정용품',image:require("../../assets/map/MarketMap_6.jpg")},
          {tabName:'기타',image:require("../../assets/map/MarketMap_7.jpg")},
        ],
      }
    },
  }
</script>

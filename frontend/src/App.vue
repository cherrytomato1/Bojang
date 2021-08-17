<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>


<script>

export default {
  name: 'App',
}
</script>

<style scoped>
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
.color5{
  background-color: #fFFFF3 !important;
}
#app{
  font-family: 'Stylish', sans-serif;
  font-family: 'Jua', sans-serif;
  font-family: 'Yeon Sung', cursive;
}
.fixed-bar {
  position: sticky;
  top: 0;
  z-index: 2;
}
.text-h1{
  color: #3ac569 !important;
  font-family: 'Jua', sans-serif !important;
}
</style>

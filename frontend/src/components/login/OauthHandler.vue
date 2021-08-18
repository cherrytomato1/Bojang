<template>
  <div>Oauth</div>
</template>
<script>
// import { onMounted } from '@vue/runtime-core'
// import useRoute from 'vue-router'
// import {useStore} from "vuex"

export default {
    name: "OauthHandler",

    mounted() {
        const url = this.$route.fullPath;
        // console.log(url);
        const slice = url.split("token=");
        const token = slice[1];
        // token은 분리되서 나옴
        console.log(token);


        // 아래 부분 새롭게 다시 짜기 mapActions 사용해서 짜는거 추천받음


        if (token) {
          // console.log("###"+this.$store.state.token);
            // this.$store.dispatch("token/setToken", token);
            // this.$store.dispatch("getToken", token);
            this.$store.commit("setToken", token);
            // console.log(this.$store.state.token);
            localStorage.getItem("token", token);
            this.$store.dispatch("getIsLogin", true);

            this.$router.push({ name: "InitPage" });
        } else {
            console.log("token is not found ");
            this.$router.push({ name: "InitPage" });
        }
    },
};
</script>

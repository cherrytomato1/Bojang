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
        console.log(url);
        const slice = url.split("token=");
        const token = slice[1];
        // 분기처리
        // token 존재 => /profile
        // error 존재 => /
        if (token) {
            this.$store.dispatch("token/setToken", token);
            localStorage.setItem("token", token);
            this.$store.dispatch("token/setIsLogin", true);
            console.log(this.$store.getters["token/getToken"]);

            this.$router.push({ name: "InitPage" });
        } else {
            console.log("token is not found ");
            this.$router.push("/");
        }
    },
};
</script>

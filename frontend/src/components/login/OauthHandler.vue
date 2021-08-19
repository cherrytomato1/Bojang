<template>
  <div></div>
</template>
<script>
export default {
  name: "OauthHandler",

  mounted() {
    const url = this.$route.fullPath;
    console.log(url);
    const slice = url.split("token=");
    const token = slice[1];
    // token은 분리되서 나옴

    if (!token) {
      console.log("token is not found ");
      this.$router.push({ name: "InitPage" });
    }
    this.$store.dispatch("getToken", token);
    console.log(token);
    this.$store.dispatch("getIsLogin", true);
    this.$store.dispatch("getUserData");

    this.$router.push({ name: "InitPage" });
  }
};
</script>

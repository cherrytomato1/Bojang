<template>
  <v-app class="color5">
    <v-container>
      <!-- <v-container class="justify-content-center align-item-center"> -->
      <span>
        <v-row class="pa-5">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link
                :to="{ name: 'UserMypage' }"
              >
                회원정보수정
              </router-link>
            </li>
            <br>
            <li class="nav-item">
              <router-link
                :to="{ name: 'OrderCheck' }"
              >
                주문 내역
              </router-link>
            </li>
            <br>
            <li class="nav-item">
              <router-link
                :to="{ name: 'FrequentStoreManage' }"
              >
                단골 가게 관리
              </router-link>
            </li>
          </ul>
        </v-row>
      </span>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: 'MypageSide',
}
</script>

<style>

</style>

<template>
  <v-container>
    <span>
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link
            :to="{ name: 'UserInformationModify' }"
          >
            회원정보수정
          </router-link>
        </li>
        <br>
        <li class="nav-item">
          <router-link
            :to="{ name: 'OrderList' }"
          >
            주문 내역
          </router-link>
        </li>
        <br>
        <li class="nav-item">
          <router-link
            :to="{ name: 'FrequentStore' }"
          >
            단골 가게 관리
          </router-link>
        </li>
      </ul>
    </span>
    <!-- <router-view /> -->
  </v-container>
</template>

<script>
export default {
  name: 'MypageSide',
}
</script>

<style>

</style>

<template>
  <!-- 주문 내역과 검색을 같은 줄로 하기 -->
  <div>
    <h2>주문 내역</h2>
    <v-text-field
      append-icon="mdi-microphone"
      class="mx-4"
      flat
      hide-details
      label="Search"
      prepend-inner-icon="mdi-magnify"
      solo-inverted
    />
    <span>
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link
            :to="{ name: 'UserInformationModify' }"
          >회원정보수정</router-link>
        </li>
        <li class="nav-item">
          <router-link
            :to="{ name: 'OrderList' }"
          >주문 내역</router-link>
        </li>
        <li class="nav-item">
          <router-link
            :to="{ name: 'FrequentStore' }"
          >단골 가게 관리</router-link>
        </li>
      </ul>
    </span>
    <v-col
      cols="12"
      class="py-2"
    >
      <!-- <p>Text Options</p> -->

      <v-btn-toggle
        v-model="text"
        tile
        color="deep-purple accent-3"
        group
      >
        <v-btn value="left">
          최근 6개월
        </v-btn>
        <v-btn value="center">
          2021
        </v-btn>
        <v-btn value="right">
          2020
        </v-btn>
        <v-btn value="justify">
          2019
        </v-btn>
        <!-- <v-btn value="left">
          2018
        </v-btn>
        <v-btn value="left">
          2017
        </v-btn>
        <v-btn value="left">
          2016
        </v-btn> -->
      </v-btn-toggle>
    </v-col>
  </div>
</template>

<script>

export default {
  name: 'OrderList',
}
</script>

<style>

</style>

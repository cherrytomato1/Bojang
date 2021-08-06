
<template>
  <v-container>
    <v-row>
      <h1>나의 상점</h1>
      <v-spacer />
      <v-btn
        large
      >
        <h3>상점 개설</h3>
      </v-btn>
    </v-row>
    <v-row>
      <v-tabs
        fixed-tabs
        background-color="primary"
        dark
      >
        <v-tab>
          가게 관리
        </v-tab>
        <v-tab>
          주문 관리
        </v-tab>
        <v-tab>
          판매 금액
        </v-tab>
      </v-tabs>
    </v-row>
  </v-container>
</template>

<template>
  <v-container>
    <span>
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link
            :to="{ name: 'StoreInformationModify' }"
          >
            회원정보수정
          </router-link>
        </li>
      </ul>
    </span>
  </v-container>
</template>

<script>
export default {
  name: 'StoreMypageSide',
}
</script>

<style>

</style>

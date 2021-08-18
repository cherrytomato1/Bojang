<template>
  <div class="color5">
    <Navbar />
    <v-container>
      <StoreBar
        @change="change"
      />
      <StoreManagerView1 v-show="tab==1" />
      <StoreManagerView2 v-show="tab==2" />
      <StoreManagerView3 v-show="tab==3" />
    </v-container>
  </div>
</template>

<style scoped>

</style>

<script>
import Navbar from '@/views/Navbar';
import StoreBar from '@/components/storeManager/StoreBar';
import StoreManagerView1 from '@/components/storeManager/StoreManagerView1';
import StoreManagerView2 from '@/components/storeManager/StoreManagerView2';
import StoreManagerView3 from '@/components/storeManager/StoreManagerView3';

export default {
  name: 'StoreManager',
  components: {
    Navbar,
    StoreBar,
    StoreManagerView1,
    StoreManagerView2,
    StoreManagerView3,
  },

  // data: () => ({
  //   no:1,
  // }),
  data(){
    return{
      tab:1
    }
  },
  created() {
    this.$store.dispatch("getMyStore");
  },

  methods: {
    change(no){
      if(this.tab != no) {
         return this.tab = no
      }
    }
  }
}
</script>


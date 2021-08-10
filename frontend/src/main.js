import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'

Vue.config.productionTip = false

window.Kakao.init('d8d2a25fb9a3d72d3564ed9c5d33c6b3');

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')

      // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
      // Kakao.init('d8d2a25fb9a3d72d3564ed9c5d33c6b3');




// kakao social login test code

// import Vue from 'vue'
// import App from './App.vue'
// import vuetify from './plugins/vuetify'

// import router from './router'

// import { createApp } from 'vue';
// import ElementPlus from 'element-plus';
// import 'element-plus/lib/theme-chalk/index.css';

// import axios from 'axios';
// import VueAxios from 'vue-axios';    //axios 추가
// import store from "./store";        //vuex 추가
// // import { router } from './router/router';  //라우터 추가

// Vue.config.productionTip = false

// // new Vue({
// //   vuetify,
// //   router,
// //   render: h => h(App)
// // }).$mount('#app')

// const app = createApp(App);
// app.use(VueAxios, axios);
// app.use(router);
// app.use(store);
// app.use(ElementPlus);
// app.use(vuetify);
// app.mount('#app');

import Vue from 'vue'
import VueRouter from 'vue-router'


import Mainpage from '@/views/Mainpage'
import StoreDetail from '@/views/StoreDetail'


Vue.use(VueRouter)

const routes = [
  {
    path: '/mainpage',
    name: 'Mainpage',
    component: Mainpage
  },
  {
    path: '/storedetail',
    name: 'StoreDetail',
    component: StoreDetail
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router

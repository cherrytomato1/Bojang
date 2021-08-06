import Vue from 'vue'
import VueRouter from 'vue-router'
import Mainpage from '@/views/Mainpage'
import StoreDetail from '@/views/StoreDetail'
import InitPage from '@/views/InitPage'

import Basket from '@/views/Basket'
import OrderCheck from '@/views/OrderCheck'
import UserMypage from '@/views/UserMypage'
import FrequentStoreManage from '@/views/FrequentStoreManage'
import Payment from '@/views/Payment'
import FinalOrderDetail from '@/views/FinalOrderDetail'

import StoreInformationModify from '@/components/mypage/StoreInformationModify'
import OrderPayment from '@/components/payment/OrderPayment'


``
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'InitPage',
    component: InitPage
  },
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
  {
    path: '/ordercheck',
    name: 'OrderCheck',
    component: OrderCheck
  },
  {
    path: '/basket',
    name: 'Basket',
    component: Basket
  },
  {
    path: '/frequentstoremanage',
    name: 'FrequentStoreManage',
    component: FrequentStoreManage
  },
  {
    path: '/mypage/storeinformationmodify',
    name: 'StoreInformationModify',
    component: StoreInformationModify
  },
  {
    path: '/usermypage',
    name: 'UserMypage',
    component: UserMypage
  },
  {
    path: '/orderpayment',
    name: 'OrderPayment',
    component: OrderPayment
  },
  {
    path: '/payment',
    name: 'Payment',
    component: Payment
  },
  {
    path: '/finalorderdetail',
    name: 'FinalOrderDetail',
    component: FinalOrderDetail
  },

]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router

// export const router = createRouter({
//   history: createWebHistory(),
//   routes
// });

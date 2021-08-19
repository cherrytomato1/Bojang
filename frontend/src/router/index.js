import Vue from "vue";
import VueRouter from "vue-router";
import Mainpage from "@/views/Mainpage";
import StoreDetail from "@/views/StoreDetail";
import StoreManager from "@/views/StoreManager";
import InitPage from "@/views/InitPage";
import LiveManager from "@/views/LiveManager";

import Basket from "@/views/Basket";
import OrderCheck from "@/views/OrderCheck";
import UserMypage from "@/views/UserMypage";
import StoreMypage from "@/views/StoreMypage";
import FrequentStoreManage from "@/views/FrequentStoreManage";
import Payment from "@/views/Payment";
import FinalOrderDetail from "@/views/FinalOrderDetail";

import StoreInformationModify from "@/components/mypage/StoreInformationModify";
import OrderPayment from "@/components/payment/OrderPayment";
import OauthHandler from "@/components/login/OauthHandler";
import Pickup from "@/views/Pickup";

``;
Vue.use(VueRouter);

const routes = [
  {
    // 초기 화면으로 하기 위해서
    path: "/",
    // path: '/Init',
    name: "InitPage",
    component: InitPage
  },
  {
    // 아래와 같이하면 화면이 안나옴
    // path: '/',
    path: "/mainpage",
    name: "Mainpage",
    component: Mainpage
  },
  {
    path: "/storedetail",
    name: "StoreDetail",
    component: StoreDetail
  },
  {
    path: "/storemanager",
    name: "StoreManager",
    component: StoreManager
  },
  {
    path: "/ordercheck",
    name: "OrderCheck",
    component: OrderCheck
  },
  {
    path: "/basket",
    name: "Basket",
    component: Basket
  },
  {
    path: "/frequentstoremanage",
    name: "FrequentStoreManage",
    component: FrequentStoreManage
  },
  {
    path: "/mypage/storeinformationmodify",
    name: "StoreInformationModify",
    component: StoreInformationModify
  },
  {
    path: "/usermypage",
    name: "UserMypage",
    component: UserMypage
  },
  {
    path: "/storemypage",
    name: "StoreMypage",
    component: StoreMypage
  },
  {
    path: "/orderpayment",
    name: "OrderPayment",
    component: OrderPayment
  },
  {
    path: "/payment",
    name: "Payment",
    component: Payment
  },
  {
    path: "/pickup",
    name: "Pickup",
    component: Pickup
  },
  {
    path: "/finalorderdetail/:order-id",
    name: "FinalOrderDetail",
    component: FinalOrderDetail
  },
  {
    path: "/oauth2/redirect",
    name: "OauthHandler",
    component: OauthHandler
  },
  {
    path: "/livemanager",
    name: "LiveManager",
    component: LiveManager
  }
];

const router = new VueRouter({
  mode: "history",
  routes
});

export default router;

// export const router = createRouter({
//   history: createWebHistory(),
//   routes
// });

import Vue from 'vue'
import Login from "./views/user/Login.vue";
import Join from "./views/user/Join.vue";
import Mypage from "./views/user/Mypage.vue";
import Yourpage from "./views/user/Yourpage.vue";
import ModifyUser from "./views/user/ModifyUser.vue";
import SearchPassword from "./views/user/SearchPassword.vue";
import CheckCertification from "./views/user/CheckCertification";
import FeedMain from "./views/feed/IndexFeed.vue";
import FeedDetail from "./views/feed/FeedDetail.vue";
import Components from "./views/Components.vue";
import Home from "./views/Home.vue";
import Alarm from "./views/user/Alarm.vue";
import EvaluateUser from "./views/user/EvaluateUser.vue";
import MarketPlace from "./views/store/Marketplace.vue";
import MarketPlaceDetail from "./views/store/MarketplaceDetail.vue";
import ModifyMarketPlace from "./views/store/ModifyMarketplace.vue"
import GroupBuying from "./views/store/Groupbuying.vue";
import GroupBuyingdetail from "./views/store/Groupbuyingdetail.vue";
import WriteGroupBuying from "./views/store/WriteGroupbuying.vue";
import ModifyGroupBuying from "./views/store/ModifyGroupbuying.vue";
import Main from "./views/Main.vue";
import MyRef from "./views/user/MyRef.vue";
import WriteRecipe from "./views/feed/WriteRecipe.vue"
import Error from "./views/error.vue"
import DirectChat from "./views/chat/DirectChat.vue"
import MyChatList from "./views/user/MyChatList.vue"
import ModifyRecipe from "./views/feed/ModifyRecipe.vue";
import MarketMap from "./views/store/MarketMap.vue";
import PrivateChat from "./views/chat/PrivateChat.vue";
import Tutorial from './views/tutorial/Tutorial.vue';
import Tutorial1 from './views/tutorial/Tutorial_1.vue';
import Tutorial2 from './views/tutorial/Tutorial_2.vue'
import Tutorial3 from './views/tutorial/Tutorial_3.vue'
import Tutorial4 from './views/tutorial/Tutorial_4.vue'
import SendMail from './views/tutorial/SendMail.vue'

export default [
  {
    path: "/",
    name: "Login",
    component: Login,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/main')
      }
      else {
        next()
      }
    }
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/alarm",
    name: "Alarm",
    component: Alarm,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/evaluateuser",
    name: "EvaluateUser",
    component: EvaluateUser,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/tutorial",
    name: "Tutorial",
    component: Tutorial,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/tutorial/1",
    name: "Tutorial1",
    component: Tutorial1,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/tutorial/2",
    name: "Tutorial2",
    component: Tutorial2,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/tutorial/3",
    name: "Tutorial3",
    component: Tutorial3,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/tutorial/4",
    name: "Tutorial4",
    component: Tutorial4,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path:"/directchat",
    name:"DirectChat",
    component: DirectChat,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/error",
    name: "Error",
    component: Error,
  },
  {
    path: "/user/join",
    name: "Join",
    component: Join
  },
  {
    path: "/chat/mychatlist",
    name: "MyChatList",
    component: MyChatList,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/user/mypage",
    name: "Mypage",
    component: Mypage,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/user/yourpage/:email",
    name: "Yourpage",
    component: Yourpage,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/tutorial/sendmail",
    name: "SendMail",
    component: SendMail,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/user/modifyuser",
    name: "ModifyUser",
    component: ModifyUser,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/feed/main",
    name: "FeedMain",
    component: FeedMain,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/feed/detail/:feedNo",
    name: "FeedDetail",
    component: FeedDetail,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/components",
    name: "Components",
    component: Components,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/user/searchpassword",
    name: "SearchPassword",
    component: SearchPassword
  },
  {
    path: "/user/checkcertification",
    name: "CheckCertification",
    component: CheckCertification
  },
  {
    path: "/store/marketplace",
    name: "MarketPlace",
    component: MarketPlace,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/chat",
    name: "PrivateChat",
    component: PrivateChat,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/store/marketplace/:id?",
    name: "MarketPlaceDetail",
    component: MarketPlaceDetail,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/store/marketmap",
    name: "MarketMap",
    component: MarketMap,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/store/groupbuying",
    name: "GroupBuying",
    component: GroupBuying,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/store/groupbuying/:id?",
    name: "GroupBuyingdetail",
    component: GroupBuyingdetail,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/store/writegroupbuying",
    name: "WriteGroupBuying",
    component: WriteGroupBuying,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/Main",
    name: "Main",
    component: Main,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/MyRef",
    name: "MyRef",
    component: MyRef,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path:"/feed/write",
    name : "WriteRecipe",
    component : WriteRecipe,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path:"/feed/modify/:feedNo?",
    name : "ModifyRecipe",
    component : ModifyRecipe,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: "/store/modify/marketplace/:pagenumber?",
    name: "ModifyMarketPlace",
    component: ModifyMarketPlace,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path:"/store/modify/groupbuying/:id?",
    name: "ModifyGroupBuying",
    component:ModifyGroupBuying,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next()
      }
      else {
        next('/')
      }
    }
  },
  {
    path: '*',
    redirect: '/error'
  }
];

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
        next ()
      }
    }
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/alarm",
    name: "Alarm",
    component: Alarm,
  },
  {
    path: "/evaluateuser",
    name: "EvaluateUser",
    component: EvaluateUser,
  },
  {
    path: "/tutorial",
    name: "Tutorial",
    component: Tutorial,
  },
  {
    path: "/tutorial/1",
    name: "Tutorial1",
    component: Tutorial1,
  },
  {
    path: "/tutorial/2",
    name: "Tutorial2",
    component: Tutorial2,
  },
  {
    path: "/tutorial/3",
    name: "Tutorial3",
    component: Tutorial3,
  },
  {
    path: "/tutorial/4",
    name: "Tutorial4",
    component: Tutorial4,
  },
  {
    path:"/directchat",
    name:"DirectChat",
    component: DirectChat,
  },
  {
    path: "/error",
    name: "Error",
    component: Error,
  },
  {
    path: "/user/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/chat/mychatlist",
    name: "MyChatList",
    component: MyChatList,
  },
  {
    path: "/user/mypage",
    name: "Mypage",
    component: Mypage,
  },
  {
    path: "/user/yourpage/:email",
    name: "Yourpage",
    component: Yourpage,
  },
  {
    path: "/user/modifyuser",
    name: "ModifyUser",
    component: ModifyUser,
  },
  {
    path: "/feed/main",
    name: "FeedMain",
    component: FeedMain,
  },
  {
    path: "/feed/detail/:feedNo",
    name: "FeedDetail",
    component: FeedDetail,
  },
  {
    path: "/components",
    name: "Components",
    component: Components,
  },
  {
    path: "/user/searchpassword",
    name: "SearchPassword",
    component: SearchPassword,
  },
  {
    path: "/user/checkcertification",
    name: "CheckCertification",
    component: CheckCertification,
  },
  {
    path: "/store/marketplace",
    name: "MarketPlace",
    component: MarketPlace,
  },
  {
    path: "/chat",
    name: "PrivateChat",
    component: PrivateChat,
  },
  {
    path: "/store/marketplace/:id?",
    name: "MarketPlaceDetail",
    component: MarketPlaceDetail,
  },
  {
    path: "/store/marketmap",
    name: "MarketMap",
    component: MarketMap,
  },
  {
    path: "/store/groupbuying",
    name: "GroupBuying",
    component: GroupBuying,
  },
  {
    path: "/store/groupbuying/:id?",
    name: "GroupBuyingdetail",
    component: GroupBuyingdetail,
  },
  {
    path: "/store/writegroupbuying",
    name: "WriteGroupBuying",
    component: WriteGroupBuying,
  },
  {
    path: "/Main",
    name: "Main",
    component: Main,
  },
  {
    path: "/MyRef",
    name: "MyRef",
    component: MyRef,
  },
  {
    path:"/feed/write",
    name : "WriteRecipe",
    component : WriteRecipe
  },
  {
    path:"/feed/modify/:feedNo?",
    name : "ModifyRecipe",
    component : ModifyRecipe
  },
  {
    path: "/store/modify/marketplace",
    name: "ModifyMarketPlace",
    component: ModifyMarketPlace,
  },
  {
    path:"/store/modify/groupbuying/:id?",
    name: "ModifyGroupBuying",
    component:ModifyGroupBuying,
  }
  // {
  //   path: "/feed/detail",
  //   name: "FeedDetail",
  //   component: FeedDetail,
  // },
];

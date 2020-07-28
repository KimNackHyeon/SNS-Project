import Login from "./views/user/Login.vue";
import Join from "./views/user/Join.vue";
import Mypage from "./views/user/Mypage.vue";
import FeedMain from "./views/feed/IndexFeed.vue";
import FeedDetail from "./views/feed/FeedDetail.vue";
import Components from "./views/Components.vue";
import Home from "./views/Home.vue";
import MarketPlace from "./views/store/Marketplace.vue";
import GroupBuying from "./views/store/Groupbuying.vue";
import SearchPassword from "./components/common/SearchPassword.vue";
import CheckCertification from "./components/common/CheckCertification";
import Main from "./views/Main.vue";
import MyRef from "./views/user/MyRef.vue";
import WriteRecipe from "./views/feed/WriteRecipe.vue"

export default [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/user/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/user/mypage",
    name: "Mypage",
    component: Mypage,
  },
  {
    path: "/feed/main",
    name: "FeedMain",
    component: FeedMain,
  },
  {
    path: "/feed/detail",
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
    path: "/store/groupbuying",
    name: "GroupBuying",
    component: GroupBuying,
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
];

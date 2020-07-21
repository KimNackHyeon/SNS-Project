import Login from "./views/user/Login.vue";
import Join from "./views/user/Join.vue";
import FeedMain from "./views/feed/IndexFeed.vue";
import Components from "./views/Components.vue";
import SearchPassword from "./components/common/SearchPassword.vue";
import CheckCertification from "./components/common/CheckCertification";

export default [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/user/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/feed/main",
    name: "FeedMain",
    component: FeedMain,
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
];

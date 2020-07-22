

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import Mypage from './views/user/Mypage.vue'
import FeedMain from './views/feed/IndexFeed.vue'
import Components from './views/Components.vue'
import Home from './views/Home.vue'
import SearchPassword from "./components/common/SearchPassword.vue";
import CheckCertification from "./components/common/CheckCertification";

export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/home',
        name : 'Home',
        component : Home
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/user/mypage',
        name : 'Mypage',
        component : Mypage
    },
    {
        path : '/feed/main',
        name : 'FeedMain',
        component : FeedMain
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
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
]

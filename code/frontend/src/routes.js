

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import Mypage from './views/user/Mypage.vue'
import FeedMain from './views/feed/IndexFeed.vue'
import Components from './views/Components.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'

export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/home',
        name : 'Header',
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
    }
]

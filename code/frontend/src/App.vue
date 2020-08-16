<template>
<div style="width:360px; height:640px; margin:auto; overflow-y:hidden; overflow-x:hidden;">
  <v-app id="app">
    <Home  @logout="onLogout" v-if="$route.path !== '/'&&$route.path !== '/user/join'&&$route.path !=='/user/searchpassword'&&$route.path !=='/user/checkcertification'"/>
    <router-view @login="onLogin" @signup="onSignup"></router-view>
  </v-app>
</div>
</template>
<script>
import "./components/css/style.scss";
import store from './vuex/store'
import axios from "axios";
import Swal from 'sweetalert2'
import Home from './views/Home.vue'

const SERVER_URL = store.state.SERVER_URL;
const cors = require('cors');


export default {
  components:{Home},
  name: "app",
  data() {
    return {
      isLoggedIn: false,
      userInfo: {},
    };
  },
  methods: {
    onLogin(email, password) {
      const loginData = {
        email: email,
        password: password,
      };
      // console.log(typeof(loginData));
      axios
        .post(`https://i3b301.p.ssafy.io:9999/food/api/account/login`, loginData)
        .then((response) => {
          // console.log(response);
          // console.log(this)
          this.$cookies.set("auth-token", response.data.token);
          this.isLoggedIn = true;
          this.$router.push("/main");
          // store에 회원정보 저장
          const userInfo = response.data.userinfo;
          // console.log(userInfo);
          store.commit('setUserInfo', userInfo);
          store.commit('delKakaouserInfo');
          // console.log(store.state.userInfo);
        })
        .catch((error) => {
          Swal.fire({
            title: '아이디와 비밀번호를 확인해주세요!',
            text: '아이디 또는 비밀번호가 틀렸어요!',
          })
          // console.log(error.response);
        });
    },

    onSignup(signupData) {
      // console.log(signupData);
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/signup`, signupData)
        .then((response) => {
          // console.log(response);
          // this.$cookies.set("auth-token", response.data.key);
          this.$router.push("/");
          Swal.fire(
          '회원가입 축합니다!',
          'success'
        )
        })
        .catch((error) => {
          // console.log(error.response);
        });
    },

    onLogout() {
      var token = this.$cookies.get("auth-token");
      if (store.state.userInfo) {
        axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/logout`, {params: { token : token}})
        .then(() => {
          // console.log(this.$cookies.keys());
          var cookies = document.cookie.split(";");
          // console.log(cookies);
          this.$cookies.remove('auth-token');
          // this.$cookies.keys().forEach((cookie) => this.$cookies.remove(cookie));
          this.isLoggedIn = false;
          // store에 저장한 사용자 정보 지우기
          store.commit('deluserInfo');
          // console.log(store.state.userInfo);
          this.$router.push('/');
        })
        .catch(error => {
          // console.log(error.response);
        })
      }
      else {
        // cookies에 토큰 지우기
        this.$cookies.remove('auth-token');
        this.isLoggedIn = false;
        // store에 저장한 사용자 정보 지우기
          store.commit('delKakaouserInfo');
          // console.log(store.state.KaKaoUserInfo);
        this.$router.push('/');
      }
      
    }
    
  },
  mounted() { 
    this.isLoggedIn = this.$cookies.isKey("auth-token");
  },
  
};
</script>

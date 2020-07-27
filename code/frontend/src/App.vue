<template>
<div style="width:360px; height:640px; margin:auto;">
  <v-app id="app">
    <Home></Home>
    <router-view @login="onLogin" @signup="onSignup"></router-view>
  </v-app>
</div>
</template>
<script>
import "./components/css/style.scss";
import axios from "axios";
import Swal from 'sweetalert2'
import Home from './views/Home.vue'

const SERVER_URL = "http://127.0.0.1:9999";

export default {
  components:{Home},
  name: "app",
  data() {
    return {
      isLoggedIn: false,
    };
  },
  methods: {
    onLogin(email, password) {
      const loginData = {
        email: email,
        password: password,
      };
      console.log(loginData);
      axios.post(`${SERVER_URL}/account/login`, loginData)
        .then((response) => {
          console.log(response);
          this.$cookies.set("auth-token", response.data.key);
          this.isLoggedIn = true
          this.$router.push("/home");
        })
        .catch((error) => {
          console.log(error.response);
        });
    },

    onSignup(signupData) {
      console.log(signupData);
      axios.post(`${SERVER_URL}/account/signup`, signupData)
        .then((response) => {
          console.log(response);
          this.$cookies.set("auth-token", response.data.key);
          this.isLoggedIn = true;
          this.$router.push("/");
          Swal.fire(
          '회원가입 축합니다!',
          'success'
        )
        })
        .catch((error) => {
          console.log(error.response);
          if(error.response.data.email) {
            Swal.fire({
              title: 'Error!',
              text: error.response.data.email,
              confirmButtonText: '취소'
            })
          } else if(error.response.data.password) {
            Swal.fire({
              title: 'Error!',
              text: error.response.data.password,
              confirmButtonText: '취소'
            })
          } else {
            Swal.fire({
              title: 'Error!',
              text: '비밀번호가 일치하지 않습니다!',
              confirmButtonText: '취소'
            })
          }
        });
    },
  },
  mounted() {
    this.isLoggedIn = this.$cookies.isKey("auth-token");
  },
};
</script>

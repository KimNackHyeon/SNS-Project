<template>
  <v-app id="app">
    <router-view @login="onLogin" @signup="onSignup"></router-view>
  </v-app>
</template>
<script>
import "./components/css/style.scss";
import axios from "axios";

const SERVER_URL = "http://127.0.0.1:9999/food/api";

export default {
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
      axios
        .post(`${SERVER_URL}/account/login`, loginData)
        .then((response) => {
          console.log(response);
          this.$cookies.set("auth-token", response.data);
          this.isLoggedIn = true;
          this.$router.push("/home");
        })
        .catch((error) => {
          console.log(error.response);
        });
    },

    onSignup(signupData) {
      axios
        .post(`${SERVER_URL}/account/signup`, signupData)
        .then((response) => {
          this.$cookies.set("auth-token", response.data);
          this.isLoggedIn = true;
          this.$router.push("/home");
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
  },
  mounted() {
    this.isLoggedIn = this.$cookies.isKey("auth-token");
  },
};
</script>

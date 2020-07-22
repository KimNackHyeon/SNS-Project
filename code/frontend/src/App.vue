<template>
  <v-app id="app">
    <router-view @login="onLogin"></router-view>
  </v-app>
</template>
<script>
import "./components/css/style.scss";
import axios from 'axios'

const SERVER_URL = 'http://127.0.0.1:9999'

export default {
  name: "app",
  methods: {
    onLogin(email, password) {
      const loginData = {
        "email": email,
        "password": password,
      }
      console.log(loginData)
      axios.post(`${SERVER_URL}/account/login`, loginData)
        .then(response => {
          console.log(response)
          this.$cookies.set('auth-token', response.data.key)
          this.$router.push('/home')
        })
        .catch(error => {
          console.log(error.response)
        })
    },
  }
};
</script>

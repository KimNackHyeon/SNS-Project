import Vue from "vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import routes from "./routes";
import store from "./vuex/store";
import vuetify from "./plugins/vuetify";
import VueCookies from "vue-cookies";
import VueGeolocation from 'vue-browser-geolocation';
import VueMoment from 'vue-moment'


Vue.config.productionTip = false;

Vue.use(VueRouter);
Vue.use(VueCookies);
Vue.use(VueGeolocation);
Vue.use(VueMoment);

import * as VueGoogleMaps from 'vue2-google-maps';
import Swal from "sweetalert2";

Vue.use(VueGoogleMaps, {
  load:{
    key:'AIzaSyD7M99_qJn1LYKzodnDK67-i_lF0HHDlp8'
  },
})

const router = new VueRouter({
  mode: 'history',
  routes,
});

// router.beforeEach((to, from, next) => {

//   if(from.path == "/" || to.path == "/"){
//     if (Vue.$cookies.isKey('auth-token')) {
//       next('/main')
//     }
//     else {
//       next()
//     }
//   }
//   else{
//     if (Vue.$cookies.isKey('auth-token')) {
//       // next()
//       console.log('로그인했음');
//     }
//     else {
//       next('/');
//       // Swal.fire({
//       //   title: '로그인 해주세요!',
//       //   text: "",
//       //   icon: 'warning',
//       //   showCancelButton: true,
//       //   confirmButtonColor: '#3085d6',
//       //   cancelButtonColor: '#d33',
//       //   confirmButtonText: '로그인 하기',
//       //   cancelButtonText:'안 하기'
//       // }).then((result) => {
//       //   if (result.value) {
//       //     next('/');
//       //   }
//       // })
//       console.log('로그인안했음');
//       // next('/error')
//     }
//   }
// });

new Vue({
  router,
  store,
  VueGeolocation,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");

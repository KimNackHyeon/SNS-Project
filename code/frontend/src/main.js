import Vue from "vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import routes from "./routes";
import store from "./vuex/store";
import vuetify from "./plugins/vuetify";
import VueCookies from "vue-cookies";
import VueGeolocation from 'vue-browser-geolocation';

Vue.config.productionTip = false;

Vue.use(VueRouter);
Vue.use(VueCookies);
Vue.use(VueGeolocation)

import * as VueGoogleMaps from 'vue2-google-maps';

Vue.use(VueGoogleMaps, {
  load:{
    key:'AIzaSyD7M99_qJn1LYKzodnDK67-i_lF0HHDlp8'
  },
})

const router = new VueRouter({
  mode: 'history',
  routes,
});

new Vue({
  router,
  store,
  VueGeolocation,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");

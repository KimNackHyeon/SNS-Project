import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import { dataStore } from './module'
import createPersistedState from "vuex-persistedstate";


Vue.use(Vuex)

const state = {
    isUser: false,
}

const dataState = createPersistedState({
    paths: ['data']
  })

export default new Vuex.Store({
  modules: {
    dataStore
  },
  plugins: [dataState, createPersistedState()],

  state: {
    user: '',
    pwd: '',
    confirm: '',
    kakaoUserInfo:{
          email:'',
          nickname:'',
          profile_image_url:'',
          thumbnail_image_url:''
    },
    userInfo: {
      email: null,
      password: null,
      nickname: null,
      address: null,
      profile_image_url: '/img/ref_close.748290f9.png',
    }
  },
  mutations: {
    setUserInfo(state, userinfo) {
      state.userInfo.email = userinfo.email;
      state.userInfo.password = userinfo.password;
      state.userInfo.nickname = userinfo.nickname;
      state.userInfo.address = userinfo.address;
    },
    setKakaoUserInfo(state,kakaoLoginResult) {
    state.kakaoUserInfo.email = kakaoLoginResult.email;
    state.kakaoUserInfo.nickname = kakaoLoginResult.profile.nickname;
    state.kakaoUserInfo.profile_image_url = kakaoLoginResult.profile.profile_image_url;
    state.kakaoUserInfo.thumbnail_image_url = kakaoLoginResult.profile.thumbnail_image_url;
    },
    deluserInfo(state) {
      state.userInfo.email = null;
      state.userInfo.password = null;
      state.userInfo.nickname = null;
      state.userInfo.address = null;
    },
    delKakaouserInfo(state) {
      state.kakaoUserInfo.email = null;
      state.kakaoUserInfo.profile_image_url = null;
      state.kakaoUserInfo.nickname = null;
      state.kakaoUserInfo.thumbnail_image_url = null;
    },
    confirmPwd(state, data){
      state.confirm = data;
    },
    userEmail(state, data) {
      state.pwd = data;
    },
  },
  getters,
  actions,
  
})
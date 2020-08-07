import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import { dataStore } from './module'
import createPersistedState from "vuex-persistedstate";
import firebase from 'firebase'

// Required for side-effects
require("firebase/firestore");

var firebaseConfig = {
  apiKey: "AIzaSyCqBsuQt5GDnZgVKk3_3po64HodjyyuVWw",
  authDomain: "disco-dispatch-246806.firebaseapp.com",
  databaseURL: "https://disco-dispatch-246806.firebaseio.com",
  projectId: "disco-dispatch-246806",
  storageBucket: "disco-dispatch-246806.appspot.com",
  messagingSenderId: "853039422287",
  appId: "1:853039422287:web:d9337f6d9efa36ec5c433a",
  measurementId: "G-1SFW7LE2TN"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

var db = firebase.firestore();

window.db = db;

db.settings({
  timestampsInSnapshots: true
});

Vue.use(Vuex)

// const SERVER_URL = 'http://i3b301.p.ssafy.io:9999/food/api';
const SERVER_URL = 'http://localhost:9999/food'

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
    SERVER_URL : 'http://i3b301.p.ssafy.io:9999/food/api',
    // SERVER_URL : 'http://localhost:9999/food/api',
    kakaoUserInfo:{
      email:'',
      nickname:'',
      profile_image_url:'',
      thumbnail_image_url:'',
      address:'',
    },
    userInfo: {
      email: null,
      password: null,
      nickname: null,
      address: null,
      profile_image_url: '/img/ref_close.748290f9.png',
    },
    mapOtherUserInfo: {
      address: [],
      food: [],
    }
  },
  mutations: {
    confirmPwd(state, data){
      state.confirm = data;
    },
    userEmail(state, data) {
      state.pwd = data;
    },
    setMapOtherUserInfo(state, data){
      state.mapOtherUserInfo.address.push(data);
      state.mapOtherUserInfo.food.push(data);
    },
    setUserInfo(state, userinfo) {
      state.userInfo.email = userinfo.email;
      state.userInfo.password = userinfo.password;
      state.userInfo.nickname = userinfo.nickname;
      state.userInfo.address = userinfo.address;
      state.userInfo.profile_image_url = userinfo.image;
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
      state.kakaoUserInfo.address = null;
    },
    modifyUserInfo(state, modifyResult) {
      state.userInfo.nickname = modifyResult.newNickname;
      state.userInfo.profile_image_url = modifyResult.newImgUrl;
      state.userInfo.address = modifyResult.newAddress;
    },
    modifyKakaoUserInfo(state, kakaoModifyResult) {
      state.kakaoUserInfo.nickname = kakaoModifyResult.newNickname;
      state.kakaoUserInfo.profile_image_url = kakaoModifyResult.newImgUrl;
      state.kakaoUserInfo.address = kakaoModifyResult.newAddress;
    },
  },
  getters,
  actions,
  
})

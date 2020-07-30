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
      plugins: [dataState,createPersistedState()]
    ,
    state: {
        kakaoUserInfo:{
            email:'',
            nickname:'',
            profile_image_url:'',
            thumbnail_image_url:''
        },
    },
    mutations:{
        setUserInfo(state,kakaoLoginResult){
            state.kakaoUserInfo.email = kakaoLoginResult.email;
            state.kakaoUserInfo.nickname = kakaoLoginResult.profile.nickname;
            state.kakaoUserInfo.profile_image_url = kakaoLoginResult.profile.profile_image_url;
            state.kakaoUserInfo.thumbnail_image_url = kakaoLoginResult.profile.thumbnail_image_url;
            
        },
    },  //데이터값(state 값)이 바뀔 때 실행되는 함수
    getters,    //데이터값(state)을 계산
    actions,    //-공부해보기
})
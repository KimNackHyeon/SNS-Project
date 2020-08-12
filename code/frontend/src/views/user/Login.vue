<template>
  <div class="user" id="login">
    <div class="wrapC">
      <!-- -------------------------------- -->
      <div style="width:100%; height : 400px;">
        <div style="height : 25%;">
          <p class="login-title">LOGIN</p>
        </div>
        <!-- login 큰 클자 -->
        <div style="width : 100%; height:25%; margin: auto;">
          <!-- 로그인 입력 큰 틀 -->
          <div style="float : left; width: 77%;">
            <!-- 로그인 입력 -->
            <div style="margin : 5px auto;">
              <div id="describe" class="login-input-text">이메일</div>
              <div>
                <input
                  v-model="email"
                  v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
                  type="text"
                  class="login-input"
                  @keyup.enter="$emit('login', email, password)"
                />
              </div>
            </div>
            <!--이메일 -->
            <div style="margin : 5px auto; padding-top: 10px;">
              <div id="describe" class="login-input-text">비밀번호</div>
              <div>
                <input
                  v-model="password"
                  v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
                  type="password"
                  class="login-input"
                  @keyup.enter="$emit('login', email, password)"
                />
              </div>
            </div>
            <!-- 비밀번호 -->
          </div>
          <div style="margin-top:5px; float:right; width:20%;">
            <v-btn class="login-btn" @click="$emit('login', email, password)">LOGIN</v-btn>
            <!-- <button  >LOGIN</button> -->
          </div>
          <!-- 로그인 버튼 -->
        </div>
        <div style="width : 100%; height:5%; margin: auto; text-align: center; color: white">
          <router-link to="/user/searchpassword" class="bottom-btn">비밀번호 찾기 </router-link>|
          <router-link to="/user/join" class="bottom-btn"> 회원가입</router-link>
        </div>
        <!-- 아이디 찾기 ~ 회원가입 -->
        <div id="otherLogin" style="display: inline-block; float: right">
          <!-- <kakaoLogin :component="component" /> -->
          <v-dialog v-model="dialog" scrollable width="100%">

          </v-dialog>
          <KakaoLogin
            api-key="4c86227162ae9066234fa38d59d75010"
            image="kakao_login_btn_small"
            :on-success=onSuccess
            :on-failure=onFailure
          />
          <!-- <GoogleLogin :component="component" /> -->
        </div>
        <!-- 다른 플랫폼으로 로그인 -->
      </div>
      <!-- -------------------------------- -->
    </div>
    <div class="error-message" v-if="error.email||error.password">이메일이 등록되지 않았거나 비밀번호가 달라요.</div>
  </div>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js" language="javascript" type="text/javascript"></script>
<script>
import "../../components/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
// import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import KakaoLogin from 'vue-kakao-login'
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";
import Main from "../Main.vue";
import axios from 'axios';
import store from '../../vuex/store'

export default {
  components: {
    KakaoLogin,
    GoogleLogin,
  },
  created() {
    this.component = this;

    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
    
  },
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    },
  }, 
  methods: {

    onSuccess(data){
        console.log("success");
        console.log(data);
        this.$cookies.set("auth-token", data.access_token);
        Kakao.API.request({
          url: '/v2/user/me',
            success: function(response) {
              console.log(response);
              const kakaoUserInfo = {
                email: response.kakao_account.email,
                nickname: response.kakao_account.profile.nickname,
                profile_image_url: response.kakao_account.profile.profile_image_url,
                thumbnail_image_url: response.kakao_account.profile.thumbnail_image_url
              };
              store.commit('deluserInfo');
              store.commit('setKakaoUserInfo', response.kakao_account)
              console.log(store.state.kakaoUserInfo)
            },
            fail: function(error) {
              console.log(error);
            }
        });
        this.$router.push("/main");
    },
    onFailure(data){
        console.log(data)
        console.log("failure")
    },

    checkForm() {
      if (this.email.length > 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else if (this.email == "")
        this.error.email = false;
      else this.error.email = false; 

      if (
        this.password.length > 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else if (this.password == "") 
        this.error.password = false;
      else this.error.password = false;


      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      component: this,
    };
  },
};
</script>

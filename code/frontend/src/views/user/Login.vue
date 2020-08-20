<template>
  <div class="user" id="login">
    <div class="wrapC" :style="{'margin-top': ((frameSize.y*0.15)-40)/2 +'px'}">
      <!-- :style="{width: (frameSize.x*0.3)+'px', height: (frameSize.x*0.3)+'px'}" -->
      <div style="width:100%; height : 25%;">
        <div :style="{ height: (frameSize.y*0.15)+'px', 'padding-top' : ((frameSize.y*0.15)-90)/2 +'px','margin-bottom' : ((frameSize.y*0.15)-50)/2 +'px' }">
          <!-- <p class="login-title" style="font-size : 40px">우리 동네 냉장고</p> -->
          <img src="../../assets/images/title.gif" style="width:100%; height:100%;">
        </div>
        <!-- login 큰 클자 -->
        <div style="width : 100%; margin: auto;" :style="{height: (frameSize.x*0.4)+'px'}">
          
          <div style="color: white; padding-left: 10px; padding-bottom: 10px;" align="center">
          </div>
        <div style="overflow: hidden; padding-bottom: 10px;">
          <!-- 로그인 입력 큰 틀 -->
          <div style="float : left; width: 75%;">
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
          <div style="margin:5px 0 0 5px; float:left; width:20%;">
            <v-btn class="login-btn" @click="$emit('login', email, password)">LOGIN</v-btn>
            <!-- <button  >LOGIN</button> -->
          </div>
          </div>
          <!-- 로그인 버튼 -->
          <div style="width : 100%; margin: auto; text-align: center; color: white">
            <router-link to="/user/searchpassword" class="bottom-btn">비밀번호 찾기 </router-link>|
            <router-link to="/user/join" class="bottom-btn"> 회원가입</router-link>
          </div>
          <div style="float:right;">
            <KakaoLogin :component="component" />
          </div>
        </div>
        
        <!-- 아이디 찾기 ~ 회원가입 -->
        <!-- <div id="otherLogin" style="display: inline-block; float: right"> -->
          
          <!-- <KakaoLogin
            api-key="4c86227162ae9066234fa38d59d75010"
            image="kakao_login_btn_small"
            :on-success=onSuccess
            :on-failure=onFailure
          /> -->
          <!-- <GoogleLogin :component="component" /> -->
        <!-- </div> -->
        <!-- 다른 플랫폼으로 로그인 -->
      </div>
      <!-- -------------------------------- -->
    </div>
    <div class="project" align="center">
      <span style="margin: 0">우리 동네 냉장고에서 신선한 재료로<br> 맛있는 음식을 만드세요!</span>
    </div>
    <!-- <div class="error-message" v-if="error.email">{{error.email}}</div>
    <div class="error-message" v-if="error.password">{{error.password}}</div>
    <div class="error-message" v-if="error.email&&error.password">이메일 형식과 영문,숫자 포함 8 자리이상이어야 합니다.</div> -->
  </div>
</template>
 
<!--<script src="//developers.kakao.com/sdk/js/kakao.min.js" language="javascript" type="text/javascript"></script>-->
<script>
import "../../components/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
// import KakaoLogin from 'vue-kakao-login'
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";
import Main from "../Main.vue";
import axios from 'axios';
import store from '../../vuex/store'

export default {
  components: {
    KakaoLogin,
    // GoogleLogin,
  },
  mounted() {
    this.onResize();
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
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
    onResize() {
      if (window.innerHeight * 0.5625 <= window.innerWidth) {
        this.frameSize = {
          x: window.innerHeight * 0.5625,
          y: window.innerHeight,
          per: innerHeight / 640,
        };
      } else {
        this.frameSize = {
          x: window.innerWidth,
          y: window.innerWidth * 1.77,
          per: innerWidth / 360,
        };
      }
    },
    // onSuccess(data){
    //     // // console.log("success");
    //     // // console.log(data);
    //     this.$cookies.set("auth-token", data.access_token);
    //     Kakao.API.request({
    //       url: '/v2/user/me',
    //         success: function(response) {
    //           // // console.log(response);
    //           const kakaoUserInfo = {
    //             email: response.kakao_account.email,
    //             nickname: response.kakao_account.profile.nickname,
    //             profile_image_url: response.kakao_account.profile.profile_image_url,
    //             thumbnail_image_url: response.kakao_account.profile.thumbnail_image_url
    //           };
    //           store.commit('deluserInfo');
    //           store.commit('setKakaoUserInfo', response.kakao_account)
    //           // // console.log(store.state.kakaoUserInfo)
    //         },
    //         fail: function(error) {
    //           // // console.log(error);
    //         }
    //     });
    //     this.$router.push("/main");
    // },
    // onFailure(data){
    //     // // console.log(data)
    //     // // console.log("failure")
    // },

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
      frameSize: {
        x: window.innerHeight * 0.5625,
        y: window.innerHeight,
        per: 1,
      },
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

<style scoped>
  .project {
    box-sizing: border-box;
    color: white;
    position: absolute;
    bottom: 10px;
    width: 100%;
    height: 50px;
    padding-left: 5px;
  }
</style>
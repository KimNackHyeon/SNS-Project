<template>
  <div class="user" id="login">
    <div class="wrapC">
      <!-- -------------------------------- -->
      <div style="width:100%; height : 200px;">
        <div style="height : 35%;">
          <p class="title">LOGIN</p>
        </div>
        <!-- login 큰 클자 -->
        <div style="width : 500px; height:35%;
      margin: auto; padding-top: 30px;
  ">
          <!-- 로그인 입력 큰 틀 -->
          <div style="float : left; width: 80%;">
            <!-- 로그인 입력 -->
            <div style="margin : 5px auto;">
              <div
                id="describe"
                style="float:left; width:20%; text-align:center; padding:5px; color: white;"
              >이메일</div>
              <div>
                <input
                  style="height:25px; width: 75%; border : 0px solid white; border-radius:5px;"
                  type="text"
                />
              </div>
            </div>
            <!--이메일 -->
            <div style="margin : 5px auto; padding-top: 10px;">
              <div
                id="describe"
                style="float:left; width:20%; text-align:center; padding:5px; color: white;"
              >비밀번호</div>
              <div>
                <input
                  style="height:25px; width: 75%; border : 0px solid white; border-radius:5px;"
                  type="text"
                />
              </div>
            </div>
            <!-- 비밀번호 -->
          </div>
          <div style="float:right; width:20%;">
            <button
              style="width:75px; height:75px; background-color:yellowgreen; color: white; border : 0px solid white; border-radius:5px;"
            >LOGIN</button>
          </div>
          <!-- 로그인 버튼 -->
        </div>
        <div
          style="width : 80%; height:15%; margin: auto; text-align: center; padding-top: 15px; color: white"
        >
          <router-link to="/" class="bottom-btn">아이디 찾기</router-link>|
          <router-link to="/" class="bottom-btn">비밀번호 찾기</router-link>|
          <router-link to="/user/join" class="bottom-btn">회원가입</router-link>
        </div>
        <!-- 아이디 찾기 ~ 회원가입 -->
        <div id="otherLogin" style="display: inline-block; width: 100%">
          <kakaoLogin :component="component" />
          <GoogleLogin :component="component" />
        </div>
        <!-- 다른 플랫폼으로 로그인 -->
      </div>

      <!-- -------------------------------- -->
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";

export default {
  components: {
    KakaoLogin,
    GoogleLogin
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
    }
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onLogin() {
      if (this.isSubmit) {
        let { email, password } = this;
        let data = {
          email,
          password
        };

        //요청 후에는 버튼 비활성화
        this.isSubmit = false;

        UserApi.requestLogin(
          data,
          res => {
            //통신을 통해 전달받은 값 콘솔에 출력
            //console.log(res);

            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;

            this.$router.push("/main");
          },
          error => {
            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
          }
        );
      }
    }
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  }
};
</script>

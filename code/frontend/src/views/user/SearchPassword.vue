<template>
  <div style="width:100%; height:100%;">
    <div style="height:40px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/">
        <v-btn icon color="gray" style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div class="titleBox">
        <div class="pageTitle">
          <h3>비밀번호 찾기</h3>
        </div>
      </div>
    </div>
    <div class="user join wrapC">
      <p style="font-size: 13px; text-align: center; margin-top: 10px;">비밀번호는 가입 시 입력하신 이메일을 통해 찾을 수 있습니다.</p>
      <div class="form-wrap">
        <div class="input-with-label">
          <input v-model="email" @keyup="onOkBtn" id="email" type="text" />
          <label for="email">이메일</label>
        </div>
      </div>
      <button v-if='!okBtn' class="btn-bottom" style="margin-bottom: 0px; bottom: 0; width: 100%; background-color: gray;">인증번호 보내기</button>
      <button v-if='okBtn' @click="searchPwd(email)" class="btn-bottom" style="margin-bottom: 0px; bottom: 0; width: 100%">인증번호 보내기</button>
    </div>
  </div>
</template>

<script>
import * as EmailValidator from "email-validator"
import axios from 'axios'
import Swal from 'sweetalert2'
import { mapState, mapMutations } from 'vuex'
import store from '../../vuex/store.js'

const SERVER_URL = store.state.SERVER_URL;

export default {
  name: 'SearchPassword',
  data: () => {
    return {
      email: "",
      // phonenumber: "",
      emailVaild: false,
      certificationNumber: "",
      okBtn: false,
      errorMsg: false,
    };
  },
  watch: {
    email() {
      this.checkEmailValidate();
    },
  },
  computed: {
    ...mapState(['confirm'])
  },
  methods: {
    ...mapMutations(['confirmPwd', 'userEmail']),
    onOkBtn() {
      if (this.emailVaild) {
        // // console.log('ok')
          this.okBtn = true
      } else {
        this.okBtn = false
      }
    },
    searchPwd(email){
      const emailData = {
        email: email,
      }
      let timerInterval
Swal.fire({
  title: '인증번호 전송중',
  html: '전송까지 <b></b> 초 남았습니다.',
  timer: 3000,
  timerProgressBar: true,
  onBeforeOpen: () => {
    Swal.showLoading()
    timerInterval = setInterval(() => {
      const content = Swal.getContent()
      if (content) {
        const b = content.querySelector('b')
        if (b) {
          b.textContent = Swal.getTimerLeft()
        }
      }
    }, 100)
  },
  onClose: () => {
    clearInterval(timerInterval)
  }
}).then((result) => {
  /* Read more about handling dismissals below */
  if (result.dismiss === Swal.DismissReason.timer) {
    // console.log('I was closed by the timer')
  }
})
      // Swal.fire({
      //     title: '잠시만 기다려주세요',
      //     text: '가입된 이메일인지 확인하고 있습니다.',
      //   })
      // // console.log(typeof(emailData))
      // // console.log(email)
      axios
      .post(`https://i3b301.p.ssafy.io:9999/food/api/account/searchpwd`, emailData)
      .then((data) => {
        // // console.log("성공")
        console.dir(data)
        if (data.data.data) {
          // // console.log(data.data.data)
          // // console.log(data.data.email)
          this.confirmPwd(data.data.data)
          this.userEmail(data.data.email)
          this.$router.push("/user/checkcertification")
        } else {
          // // console.log('실패')
          Swal.fire({
          title: '다시 한번 더 확인해주세요',
          text: '이메일이 틀렸어요',
        })
        }
      })
      .catch(data => {
        // // console.log(data)
      });
    },
    checkEmailValidate() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        { // // console.log('올바르지 않습니다.'); 
        this.emailVaild = false;
        }
      else { // // console.log('올바릅니다.'); 
      this.emailVaild = true;
      }
    },
  }
};
</script>

<style scoped>
  .titleBox {
    display: inline-block;
    width: 90%;
    height: 100%;
    font-size: 17px;
    text-align: center;
  }
  .pageTitle {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>
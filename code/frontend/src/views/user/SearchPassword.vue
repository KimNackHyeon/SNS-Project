<template>
  <div class="user join wrapC" style="position: relative;">
    <h1>비밀번호 찾기</h1>
    <p style="font-size: 13px; text-align: center;">비밀번호는 가입 시 입력하신 이메일을 통해 찾을 수 있습니다.</p>
    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="email" @keyup="onOkBtn" id="email" type="text" />
        <label for="email">이메일</label>
      </div>

      <!-- <div class="input-with-label">
        <input v-model="phonenumber" @keyup="onOkBtn" id="phonenumber" type="text" />
        <label for="phonenumber">휴대폰 번호</label>
      </div> -->
    </div>

    <!-- <div v-if='!okBtn' class='btn find-btn'>인증메일 발송</div>
    <div v-if='okBtn' @click='findPassword' class='btn on-find-btn'>인증메일 발송</div> -->
    <!-- <div>
      <router-link to="/user/checkcertification">
        <button
          class="btn-bottom"
          style="margin-bottom: 0px; bottom: 0; width: 320px"
        >인증번호 보내기</button>
      </router-link>
    </div> -->
    <button v-if='!okBtn' class="btn-bottom" style="margin-bottom: 0px; bottom: 0; width: 320px; background-color: gray;">인증번호 보내기</button>
    <button v-if='okBtn' @click="searchPwd(email)" class="btn-bottom" style="margin-bottom: 0px; bottom: 0; width: 320px">인증번호 보내기</button>
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
        console.log('ok')
          this.okBtn = true
      } else {
        this.okBtn = false
      }
    },
    searchPwd(email){
      const emailData = {
        email: email,
      }
      // Swal.fire({
      //     title: '잠시만 기다려주세요',
      //     text: '가입된 이메일인지 확인하고 있습니다.',
      //   })
      console.log(typeof(emailData))
      console.log(email)
      axios
      .post(`${SERVER_URL}/account/searchpwd`, emailData)
      .then((data) => {
        console.log("성공")
        console.dir(data)
        if (data.data.data) {
          console.log(data.data.data)
          console.log(data.data.email)
          this.confirmPwd(data.data.data)
          this.userEmail(data.data.email)
          this.$router.push("/user/checkcertification")
        } else {
          console.log('실패')
          Swal.fire({
          title: '다시 한번 더 확인해주세요',
          text: '이메일이 틀렸어요',
        })
        }
      })
      .catch(data => {
        console.log(data)
      });
    },
    checkEmailValidate() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        { console.log('올바르지 않습니다.'); this.emailVaild = false;}
      else { console.log('올바릅니다.'); this.emailVaild = true;}
    },
  }
};
</script>

<style></style>

<template>
  <div class="user join wrapC" style="position: relative;">
    <h1>비밀번호 찾기</h1>
    <p style="font-size: 13px; text-align: center;">비밀번호는 가입 시 입력하신 이메일을 통해 찾을 수 있습니다.</p>
    <div class="form-wrap">
      <h1 class="text-left">인증번호를 보냈습니다.</h1>
      <h1 class="text-left">메일을 확인해주세요.<br><br></h1>
      <div class="input-with-label">
        <input v-model="certificationNumber" id="certificationNumber" type="text" placeholder="인증번호를 입력해주세요." style="margin-bottom: 10px;"/>
        <span id="times">03:00</span>
        <label for="certificationNumber">인증번호</label>
        <button class="btn-bottom" @click="sendPwd" style="border-radius: 10px; float: right; position: unset; margin: 0 0 10px;">인증</button>
        <button class="btn-bottom" @click="retry" style="border-radius: 10px; float: right; position: unset; margin: 0 0 10px; background-color: red;">인증번호 재전송</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState, mapMutations } from 'vuex'
import Swal from 'sweetalert2'
import store from '../../vuex/store.js'

const SERVER_URL = store.state.SERVER_URL;

export default {
  data: () => {
    return {
      certificationNumber: "",
      resetTime: '',
      time: false,
    };
  },
  mounted() {
    var threeMin = 60 * 3,
    display = document.querySelector('#times');
    this.countTimer(threeMin, display);
  },
  computed: {
    ...mapState(['pwd', 'confirm']),
  },
  methods: {
    ...mapMutations(['confirmPwd', 'userEmail']),
    countTimer(duration, display) {
      var time = duration, minutes, seconds;
      this.resetTime = setInterval(function () {
      minutes = parseInt(time / 60, 10);
      seconds = parseInt(time % 60, 10);
      minutes = minutes < 10 ? "0" + minutes : minutes;
      seconds = seconds < 10 ? "0" + seconds : seconds;
      display.textContent = minutes + ":" + seconds;
      if (--time < 0) {
        display.textContent = '00' + ":" + '00';
        this.time = true;
        clearInterval(this.resetTime);
      }
    }, 1000);
    },
    retry() {
      const emailData = {
        email: this.pwd,
      }
      console.log(emailData)
      clearInterval(this.resetTime);
      var threeMin = 60 * 3,
      display = document.querySelector('#times');
      this.countTimer(threeMin, display);
      axios.post(`${SERVER_URL}/account/researchpwd`, emailData)
      .then(data => {
        console.log("성공")
        console.dir(data)
        this.confirmPwd(data.data.data)
        this.userEmail(data.data.email)
      })
      .catch(data => {
        console.log(data)
      });
    },
    sendPwd() {
      const emailData = {
        email: this.pwd,
      }
      console.log('ok')
      if (this.certificationNumber === this.confirm) {
        console.log('ok2')
        axios
        .post(`${SERVER_URL}/account/findpwd`, emailData)
        .then((data) => {
          console.log(data.data.data)
          Swal.fire({
            title: '인증에 성공했습니다.',
            text: '이메일로 비밀번호를 전송하였습니다.',
        })
          this.$router.push("/")
        })
        .catch((data) => {
          console.log(data)
        })
      } else {
        Swal.fire({
          title: '인증번호가 틀렸습니다.',
          text: '한번 더 확인해주세요',
        })
      }
    },
  }
};
</script>

<style>
</style>

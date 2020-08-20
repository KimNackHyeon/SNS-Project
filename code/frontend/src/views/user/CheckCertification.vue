<template>
  <div style="width:100%; height:100%;">
    <div style="height:40px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/user/searchpassword">
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
      <p style="font-size: 13px; text-align: center;">비밀번호는 가입 시 입력하신 이메일을 통해 찾을 수 있습니다.</p>
      <div class="form-wrap">
        <h1 class="text-left">인증번호를 보냈습니다.</h1>
        <h1 class="text-left">메일을 확인해주세요.<br><br></h1>
        <div class="input-with-label">
          <input v-model="certificationNumber" id="certificationNumber" type="text" placeholder="인증번호를 입력해주세요." style="margin-bottom: 10px;"/>
          <span id="times">03:00</span>
          <label for="certificationNumber">인증번호</label>
          <button class="btn-bottom" @click="sendPwd" style="border-radius: 10px; float: right; position: unset; margin: 0 0 10px; width : 100%;">인증</button>
          <button class="btn-bottom" @click="retry" style="border-radius: 10px; float: right; position: unset; margin: 0 0 10px; background-color: red; width : 100%;">인증번호 재전송</button>
        </div>
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
      let timerInterval
Swal.fire({
  title: '인증번호 전송중',
  html: '전송까지 <b></b> 초 남았습니다.',
  timer: 2000,
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
      const emailData = {
        email: this.pwd,
      }
      // // console.log(emailData)
      clearInterval(this.resetTime);
      var threeMin = 60 * 3,
      display = document.querySelector('#times');
      this.countTimer(threeMin, display);
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/researchpwd`, emailData)
      .then(data => {
        // console.log("성공")
        // console.dir(data)
        this.confirmPwd(data.data.data)
        this.userEmail(data.data.email)
      })
      .catch(data => {
        // // console.log(data)
      });
    },
    sendPwd() {
      const emailData = {
        email: this.pwd,
      }
      // // console.log('ok')
      if (this.certificationNumber === this.confirm) {
        // // console.log('ok2')
        Swal.fire({
  icon: 'success',
  title: '인증에 성공했습니다.',
  text:'비밀번호를 가입 이메일로 전송했습니다.',
  showConfirmButton: false,
  timer: 2500
})
        axios
        .post(`https://i3b301.p.ssafy.io:9999/food/api/account/findpwd`, emailData)
        .then((data) => {
          // // console.log(data.data.data)
          
          this.$router.push("/")
        })
        .catch((data) => {
          // // console.log(data)
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
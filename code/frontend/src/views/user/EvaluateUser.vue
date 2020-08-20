<template>
  <div>
    <div style="width:100%; height:40px; border: 1px solid rgba(128, 128, 128, 0.15);">
      <div @click="onleft" style="width:40px; height:100%; border-right: 1px solid rgba(128, 128, 128, 0.15); float:left;">
          <v-icon size="30px" style="padding:6px 0px;">mdi-chevron-left</v-icon>
      </div>
      <div style="height:100%; text-align:center;">
        <p style="font-size: 17px; margin: 0; height: inherit; line-height: 38px">평가</p>
      </div>
    </div>
    <div style="width: 100%; height: 20%; text-align: center; display: flex;">
      <h1 style="margin: auto; margin-right: 0">"{{evalData.nickname}}님"</h1>
      <h2 style="margin: auto; margin-left: 0"> 과의 거래는 어떠셨나요?</h2>
    </div>
    <!-- 평가하는거 넣기 -->
    <div style="display: flex; height: 35%; justify-content: center; align-items: center;">
      <div>
        <div style="overflow: hidden; padding: 0 20px;">
          <v-icon color="black" style="float: left" size="30">mdi-thumb-down-outline</v-icon>
          <v-icon color="black" style="float: right" size="30">mdi-thumb-up-outline</v-icon>
        </div>
        <div class="ref" style="padding: 20px">
          <img class="ref1" style="width: 20%; height: 20%;" src="../../assets/images/fresh_grade/0.png" alt="1" @click="onscore(1)">
          <img class="ref2" style="width: 20%; height: 20%;" src="../../assets/images/fresh_grade/1.png" alt="2" @click="onscore(2)">
          <img class="ref3" style="width: 20%; height: 20%;" src="../../assets/images/fresh_grade/2.png" alt="3" @click="onscore(3)">
          <img class="ref4" style="width: 20%; height: 20%;" src="../../assets/images/fresh_grade/3.png" alt="4" @click="onscore(4)">
          <img class="ref5" style="width: 20%; height: 20%;" src="../../assets/images/fresh_grade/4.png" alt="5" @click="onscore(5)">
        </div>
        <div style="padding: 20px">
          <v-progress-linear
            color="rgb(160, 212, 105)"
            height="20"
            :value="power"
          ></v-progress-linear>
        </div>
      </div>
    </div>
    <div style="padding: 20px; text-align: center; font-size: 22px">
      <span style="font-weight: 500; font-size: 25px">"{{evalData.nickname}}님"</span>
      <span> 에게</span>
      <br>
      <span>신선도 </span>
      <span style="font-weight: 500; font-size: 40px; color: rgb(160, 212, 105)">{{score}}</span>
      <span>점을 주었습니다.</span>
    </div>
    <!-- 평가하기 버튼 -->
    <div style="width: inherit">
      <v-btn color="rgb(160, 212, 105)" 
      style="width: inherit; height: 50px; color: white; font-size: 18px; position:fixed; bottom: 0; border-radius: unset;"
      @click="onEvaluate"
      >평가하기</v-btn>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import Swal from 'sweetalert2'
import axios from "axios"

export default {
  data() {
    return {
      power: 0,
      score: 0,
      // check: false,
      evalData : '',
    }
  },
  created(){
    axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/evaluate`,{params:{no : this.$route.params.no}})
    .then(response => {
      // console.log(response);
      this.evalData = response.data;
    }).catch(error => {
      Swal.fire({
        icon: 'error',
        title: '',
        text: '이미 평가가 완료되었습니다.',
      }).then(response => {
        this.$router.push('/alarm');
      })
    })
  },
  methods: {
    onleft() {
      this.$router.go(-1)
    },
    onscore(num) {
      this.score = num
      if (num==1) {
        if (this.power == 20) {
          this.score = 0
          this.power = 0
          $('.ref1').css('opacity', '0.2')
        }
        else {
          this.power = 20
          $('.ref1').css('opacity', '1')
          $('.ref2').css('opacity', '0.2')
          $('.ref3').css('opacity', '0.2')
          $('.ref4').css('opacity', '0.2')
          $('.ref5').css('opacity', '0.2')
        }
      }
      else if (num==2) {
        if (this.power == 40) {
          this.score = 0
          this.power = 0
          $('.ref2').css('opacity', '0.2')
        }
        else {
        this.power = 40
          $('.ref1').css('opacity', '0.2')
          $('.ref2').css('opacity', '1')
          $('.ref3').css('opacity', '0.2')
          $('.ref4').css('opacity', '0.2')
          $('.ref5').css('opacity', '0.2')
        }
      }
      else if (num==3) {
        if (this.power == 60) {
          this.score = 0
          this.power = 0
          $('.ref3').css('opacity', '0.2')
        }
        else {
          this.power = 60
          $('.ref1').css('opacity', '0.2')
          $('.ref2').css('opacity', '0.2')
          $('.ref3').css('opacity', '1')
          $('.ref4').css('opacity', '0.2')
          $('.ref5').css('opacity', '0.2')
        }
      }
      else if (num==4) {
        if (this.power == 80) {
          this.score = 0
          this.power = 0
          $('.ref4').css('opacity', '0.2')
        }
        else {
          this.power = 80
          $('.ref1').css('opacity', '0.2')
          $('.ref2').css('opacity', '0.2')
          $('.ref3').css('opacity', '0.2')
          $('.ref4').css('opacity', '1')
          $('.ref5').css('opacity', '0.2')
        }
      }
      else if (num==5) {
        if (this.power == 100) {
          this.score = 0
          this.power = 0
          $('.ref5').css('opacity', '0.2')
        }
        else {
          this.power = 100
          $('.ref1').css('opacity', '0.2')
          $('.ref2').css('opacity', '0.2')
          $('.ref3').css('opacity', '0.2')
          $('.ref4').css('opacity', '0.2')
          $('.ref5').css('opacity', '1')
        }
      }
    },
    onEvaluate() {
      Swal.fire({
        title: '평가 완료하시겠습니까?',
        text: `"${this.evalData.nickname}님에게 ${this.score}점을 주었습니다. 되돌릴 수 없습니다!"`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '네 평가할게요!'
      })
      .then((result) => {
        if (result.value) {
          // 평가한거 axios 보내기
          axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/evaluate`,{
              no : this.evalData.no, 
              email : this.evalData.email, 
              score : this.score
            })
          .then(response => {
            Swal.fire({
                // position: 'top-end',
                icon: 'success',
                title: '평가가 완료되었습니다.',
                showConfirmButton: false,
                timer: 1500
            })
            // window.location.reload();
            this.$router.push('/alarm')
          })
          .catch(error => {
            // console.log(error.response)
          })
        }
      })
    },
  }
}
</script>

<style scoped>
.ref img {
  opacity: 0.2;
}
.ref img:hover {
  opacity: 1;
}
</style>
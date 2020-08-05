<template>
  <div style="height: 100%;">
    <div style="height:48px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/user/mypage">
        <v-btn icon color="gray" style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div class="titleBox">
        <div class="pageTitle">
          <p style="margin: 0;">회원 정보 수정</p>
        </div>
      </div>
    </div>
    <div class="modifyBody">
      <div class="myphoto">
        <v-avatar size="100"><img :src="newUserInfo.newImgUrl" alt="John"></v-avatar>
        <div class="filebox">
          <v-btn color="#a0d469">
            <label for="changeFile">사진 변경</label>
            <input type="file" id="changeFile" @change="changeImg">
          </v-btn>
        </div>
      </div>
      <div class="input-with-label">
        <label for="nickname">닉네임</label>
        <input v-model="newUserInfo.newNickname" type="text" id="nickname" placeholder="닉네임을 입력하세요."  maxlength="128">
        <p v-if="nickErrMsg" class="errorMsg">이미 사용중인 닉네임입니다.</p>
      </div>
      <div class="input-with-label">
        <label for="password">비밀번호</label>
        <input v-model="newUserInfo.newPassword" type="password" id="password" placeholder="비밀번호를 입력하세요.">
        <p v-if="pwdErrMsg" class="errorMsg">영문, 숫자 포함 8자리 이상이어야 합니다.</p>
      </div>
      <div class="input-with-label">
        <label for="password-confirm">비밀번호 확인</label>
        <input v-model="newUserInfo.newPasswordConfirm" type="password" id="password-confirm" placeholder="비밀번호를 확인해주세요.">
        <p v-if="pwdCofErrMsg" class="errorMsg">비밀번호가 일치하지 않습니다.</p>
      </div>
      <div class="input-with-label">
        <label for="address">주소</label>
        <input v-model="newUserInfo.newAddress" type="text" id="address" placeholder="주소를 입력하세요." @click="addressgo()">
        <p v-if="addErrMsg" class="errorMsg">주소를 입력해주세요.</p>
      </div>
      <v-dialog v-model="dialog" scrollable width= "100%" class="adressDialog">
        <v-card>
          <v-card-title >주소 검색</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <DaumPostcode style="height:300px" :on-complete="handleAddress" />
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="dialog = false">삭제</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <div>
        <v-btn 
        color="rgb(160, 212, 105)" 
        style="width: 100%; height: 50px; color: white; font-size: 18px; margin-top: 35px;" 
        @click="onModify()"
        >
        수정하기
        </v-btn>
      </div>
    </div>
  </div>
</template>



<script>
import "../../assets/css/components.scss";
import store from '../../vuex/store.js'
import DaumPostcode from "vuejs-daum-postcode";
import axios from 'axios';
import PasswordValidator from 'password-validator'

// const SERVER_URL = "http://127.0.0.1:9999/food/api";
const SERVER_URL = "http://i3b301.p.ssafy.io:9999/food/api";
export default {
  data() {
    return {
      userinfo: "",
      image:'',
      newUserInfo: {
        newImgUrl: "",
        newNickname: "",
        newPassword: "",
        newPasswordConfirm: "",
        newAddress: "",
      },
      nickErrMsg: false,
      pwdErrMsg: false,
      passwordSchema: new PasswordValidator(),
      pwdCofErrMsg: false,
      addErrMsg: false,
      dialog: false,
    }
  },
  components: {
    DaumPostcode
  },
  watch: {
    'newUserInfo.newNickname'() {
      this.checkNickname();
    },
    'newUserInfo.newPassword'() {
      this.checkPasswordValidate();
      this.checkPassword();
    },
    'newUserInfo.newPasswordConfirm'() {
      this.checkPassword();
    },
  },
  created() {
    this.checkUser()
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(20)
      .has()
      .digits()
      .has()
      .letters();
  },
  mounted() {
    if(store.state.kakaoUserInfo.email != null){
      this.userinfo = store.state.kakaoUserInfo;
      this.newUserInfo.newNickname = this.userinfo.nickname;
      this.newUserInfo.newImgUrl = this.userinfo.profile_image_url;
      this.newUserInfo.newAddress = this.userinfo.address;
      console.log(this.userinfo)
    }
    else{
      console.log(store.state.userInfo)
      console.log(this.userinfo)
      this.userinfo = store.state.userInfo;
      this.newUserInfo.newNickname = this.userinfo.nickname;
      this.newUserInfo.newImgUrl = this.userinfo.profile_image_url;
      this.newUserInfo.newAddress = this.userinfo.address;
      this.newUserInfo.newPassword = this.userinfo.password
    }
    // console.log(this.userinfo)
  },
  methods: {
    checkUser() {
      var token = this.$cookies.get("auth-token");
      axios.get(`${SERVER_URL}/info`, {params: { token : token}})
        .then((response) => {
          console.log(response);
        })
        .catch(error => {
          console.log(error.response);
          this.$cookies.remove('auth-token');
          this.$router.push('/');
        })
    },
    // 에러 확인(닉네임, 비밀번호, 비밀번호 확인, 주소)
    checkNickname() {
      axios.post(`${SERVER_URL}/account/nicknameconfirm`, { nickname : this.newUserInfo.newNickname })
      .then(data => {
        console.log(data.data.data)
        if (data.data.data == "1") {
          console.log('중복')
          this.nickErrMsg = true;
        } else {
          console.log('가능')
          this.nickErrMsg = false;
        }
      })
      .catch(error => {
        console.log(error.response);
      })
    },
    checkPasswordValidate() {
      if (
        this.newUserInfo.newPassword.length >= 0 &&
        !this.passwordSchema.validate(this.newUserInfo.newPassword)
      )
        { this.pwdErrMsg= true; }
      else {
      this.pwdErrMsg= false;
      }
    },
    checkPassword() {
      if (this.newUserInfo.newPasswordConfirm !== '') {

        if(this.newUserInfo.newPassword != this.newUserInfo.newPasswordConfirm) {
          this.pwdCofErrMsg = true
        } else if (this.newUserInfo.newPassword && this.newUserInfo.newPasswordConfirm && this.newUserInfo.newPassword === this.newUserInfo.newPasswordConfirm) {
          this.pwdCofErrMsg = false
        }
      }
      else {
        this.pwdCofErrMsg = true
      }
    },
    changeImg(event) {
      const newImg = event.target.files[0];
      this.image = event.target.files[0];
      console.log(newImg)
      this.newUserInfo.newImgUrl = URL.createObjectURL(newImg);
      console.log(this.userinfo.profile_image_url)
      // this.createImage(newImg);
    },
    addressgo(){
      if(this.dialog==false){
        this.dialog = true;
      }else{
        this.dialog = false
      }
    },
    handleAddress(data){
      console.log(data)
      let fullAddress = data.address
      let extraAddress = ''
      if (data.addressType === 'R') {
        if (data.bname !== '') {
          extraAddress += data.bname
        }
        if (data.buildingName !== '') {
          extraAddress += (extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName)
        }
        fullAddress += (extraAddress !== '' ? ` (${extraAddress})` : '')
      }
      this.newUserInfo.newAddress = fullAddress
      this.dialog = false;
      this.inputAddress = true
    },
    onModify() {
      if (!this.nickErrMsg && !this.pwdErrMsg && !this.pwdCofErrMsg 
      && this.newUserInfo.newAddress && this.newUserInfo.newNickname && this.newUserInfo.newPassword 
      && this.newUserInfo.newPasswordConfirm) {
        if(store.state.kakaoUserInfo.email != null) {
          store.commit('modifyKakaoUserInfo', this.newUserInfo)
          this.$router.go(-1)
          console.log(store.state.kakaoUserInfo)
        }
        else {
          axios.put(`${SERVER_URL}/account/update/`,{
            email : store.state.userInfo.email,
            nickname : this.newUserInfo.newNickname,
            address : this.newUserInfo.newAddress,
            password : this.newUserInfo.newPassword,
            image : this.newUserInfo.newImgUrl
          }).then(response => {
            console.log(response);
            store.commit('modifyUserInfo', this.newUserInfo)
            console.log(this.newUserInfo)
            this.$router.go(-1)
          }).catch(error => {
            console.log(error.response);
          })
        }
      }
      else {
        console.log('입력칸이 비어있습니다.')
        this.checkNickname()
        this.checkPasswordValidate()
        this.checkPassword()
      }
    },
    createBase64Image(filename){
      const reader = new FileReader();
      reader.readAsBinaryString(filename);
      console.log(filename);
    },
  }
}
</script>

<style scoped>
  .left-icon:hover {
    color: #a0d469;
  }
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
  .filebox {
    margin: 20px 0;
  }
  .filebox .v-btn { 
    display: inline-block; 
    padding: .5em .75em; 
    color: white; 
    font-size: inherit; 
    line-height: normal; 
    vertical-align: middle; 
    background-color: #a0d469; 
    cursor: pointer; 
    border: 1px solid #a0d469; 
    border-bottom-color: #a0d469; 
    border-radius: .25em; 
  }
  .filebox input[type="file"] { /* 파일 필드 숨기기 */ 
    position: absolute; 
    width: 1px; 
    height: 1px; 
    padding: 0; 
    margin: -1px; 
    overflow: hidden; 
    clip:rect(0,0,0,0); 
    border: 0; 
  }
  .modifyBody {
    padding: 20px;
    overflow: scroll;
    height: 540px;
  }
  .myphoto {
    /* padding: 0 10px; */
    text-align: center;
  }
  .errorMsg {
    color: red; 
    display: inline-block; 
    margin: 5px 0 0 5px;
  }
  .adressDialog {
    /* margin: 18px !important; */
  }
</style>
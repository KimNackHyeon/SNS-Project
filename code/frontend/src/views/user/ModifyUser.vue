<template>
  <div style="height: 100%;">
    <div style="height:48px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/user/mypage">
        <v-btn icon color="gray" style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%;">
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
        <v-avatar size="120"><img :src="newUserInfo.newImgUrl" alt="John"></v-avatar>
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
        <p v-if="nickErrMsg" style="color: red;">이미 사용중인 닉네임입니다.</p>
      </div>
      <div class="input-with-label">
        <label for="password">비밀번호</label>
        <input v-model="newUserInfo.newPassword" type="password" id="password" placeholder="비밀번호를 입력하세요.">
        <p v-if="pwdErrMsg" style="color: red;">영문,숫자 포함 8 자리이상이어야 합니다.</p>
      </div>
      <div class="input-with-label">
        <label for="password-confirm">비밀번호 확인</label>
        <input v-model="newUserInfo.newPasswordConfirm" type="password" id="password-confirm" placeholder="비밀번호를 확인해주세요.">
        <p v-if="pwdCofErrMsg" style="color: red;">비밀번호가 일치하지 않습니다.</p>
      </div>
      <div class="input-with-label">
        <label for="address">주소</label>
        <input v-model="newUserInfo.newAddress" type="text" id="address" placeholder="주소를 입력하세요." @click="addressgo()">
      </div>
      <v-dialog v-model="dialog" width="100%">
        <v-card>
          <v-card-title class="headline">주소 검색</v-card-title>
          <v-card-text>
            <DaumPostcode style="height:300px" :on-complete="handleAddress" />
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="dialog = false">삭제</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <div>
        <router-link to="/user/mypage">
          <v-btn 
          color="rgb(160, 212, 105)" 
          style="width: 100%; height: 50px; color: white; font-size: 18px; margin-top: 35px;" 
          v-if="newUserInfo.newImgUrl && newUserInfo.newNickname && newUserInfo.newPassword && newUserInfo.newPasswordConfirm && newUserInfo.newAddress && (newUserInfo.newPassword === newUserInfo.newPasswordConfirm)"
          @click="onModify"
          >
          수정하기
          </v-btn>
        </router-link>
      </div>
    </div>
  </div>
</template>



<script>
import "../../assets/css/components.scss";
import store from '../../vuex/store.js'
import DaumPostcode from "vuejs-daum-postcode";
import axios from 'axios';

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
      pwdCofErrMsg: false,
      open: false,
      dialog: false,
    }
  },
  components: {
    DaumPostcode
  },
  created() {
    this.checkUser()
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
          // this.$cookies.remove('auth-token');
          this.$router.push('/');
        })
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
      if(store.state.kakaoUserInfo.email != null) {
        store.commit('modifyKakaoUserInfo', this.newUserInfo)
        console.log(store.state.kakaoUserInfo)
      }
      else {
        store.commit('modifyUserInfo', this.newUserInfo)
        // console.log(store.state.userInfo) 
        console.log(this.newUserInfo)
        
        axios.put(`${SERVER_URL}/account/update/`,{
          email : store.state.userInfo.email,
          nickname : this.newUserInfo.newNickname,
          address : this.newUserInfo.newAddress,
          password : this.newUserInfo.newPassword,
          image : this.newUserInfo.newImgUrl
        }).then(response => {
          console.log(response);
        }).catch(error => {
          console.log(error.response);
        })

        this.uploadImage();
      }
    },
    uploadImage(file) {
      // var file = this.state.file;
      var formData = new FormData();
      formData.append("image", this.image);

      axios.post(`${SERVER_URL}/account/upload/`, formData, { 
          headers: { 'Content-Type': 'multipart/form-data' } 
      }).then(response => {
        console.log(response);
      });
    }
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
    margin: 10px 0;
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
    overflow: hidden;
  }
  .myphoto {
    padding: 10px;
    text-align: center;
  }
</style>
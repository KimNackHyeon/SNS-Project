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
        <v-avatar size="100"><img :src="userinfo.profile_image_url" alt="John"></v-avatar>
        <div class="filebox">
          <v-btn color="#a0d469">
            <label for="changeFile">사진 변경</label>
            <input type="file" id="changeFile" @change="changeImg">
          </v-btn>
        </div>
      </div>
      <div class="input-with-label">
        <label for="nickname">닉네임</label>
        <input v-model="newNickname" type="text" id="nickname" :placeholder="userinfo.nickname"  maxlength="128">
        <p v-if="nickErrMsg" style="color: red;">이미 사용중인 닉네임입니다.</p>
      </div>
      <div class="input-with-label">
        <label for="password">비밀번호</label>
        <input v-model="newPassword" type="password" id="password" placeholder="password">
        <p v-if="pwdErrMsg" style="color: red;">영문,숫자 포함 8 자리이상이어야 합니다.</p>
      </div>
      <div class="input-with-label">
        <label for="password-confirm">비밀번호 확인</label>
        <input v-model="newPasswordConfirm" type="password" id="password-confirm" placeholder="password confirm">
        <p v-if="pwdCofErrMsg" style="color: red;">비밀번호가 일치하지 않습니다.</p>
      </div>
      <button style="border:3px #a0d469 solid; border-radius: 5px; font-size:15px; background-color:#a0d469; color:#fff;" @click="addressgo()">주소검색</button>
      <div class="input-address">
        <v-text-field
          readonly=""
          v-model="newAddress"
          placeholder="주소가 입력될 공간입니다."
          value=""
          single-line
          @click="addressgo()"
          width="300px;"
          color="#a0d469"
        >
        </v-text-field>
      </div>
      <div v-if="open">
        <DaumPostcode style="height:200px" :on-complete="handleAddress" />
      </div>
      <div>
        <v-btn color="rgb(160, 212, 105)" style="width: 100%; height: 50px; color: white; font-size: 18px;">수정하기</v-btn>
      </div>
    </div>
  </div>
</template>



<script>
import "../../assets/css/components.scss";
import store from '../../vuex/store.js'
import DaumPostcode from "vuejs-daum-postcode";

export default {
  data() {
    return {
      userinfo: "",
      newImgUrl: "",
      newEmail: "",
      newNickname: "",
      nickErrMsg: false,
      newPassword: "",
      pwdErrMsg: false,
      newPasswordConfirm: "",
      pwdCofErrMsg: false,
      newAddress: "",
      open: false,
    }
  },
  components: {
    DaumPostcode
  },
  mounted() {
    if(store.state.kakaoUserInfo.email != null){
      this.userinfo = store.state.kakaoUserInfo;
    }else{
      this.userinfo = store.state.userInfo;
    }
    // console.log(this.userinfo)
  },
  methods: {
    changeImg(event) {
      const newImg = event.target.files[0];
      console.log(newImg)
      this.userinfo.profile_image_url = URL.createObjectURL(newImg);
      console.log(this.userinfo.profile_image_url)
    },
    addressgo(){
      if(this.open==false){
        this.open = true;
      }else{
        this.open = false
      }
    },
    handleAddress(data){
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
      this.signupData.address = fullAddress
      this.open = false;
      this.inputAddress = true
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
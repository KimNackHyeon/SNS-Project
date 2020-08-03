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
            <input type="file" id="changeFile">
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
        <p v-if="pwErrMsg" style="color: red;">비밀번호가 일치하지 않습니다.</p>
      </div>
    </div>
    
  </div>
</template>




<script>
import "../../assets/css/components.scss";
import store from '../../vuex/store.js'

export default {
  data() {
    return {
      userinfo: "",
      newEmail: "",
      newNickname: "",
      nickErrMsg: false,
      newPassword: "",
      newPasswordConfirm: "",
    }
  },
  mounted() {
    if(store.state.kakaoUserInfo.email != null){
      this.userinfo = store.state.kakaoUserInfo;
    }else{
      this.userinfo = store.state.userInfo;
    }
    console.log(this.userinfo)
  },
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
    color: black; 
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
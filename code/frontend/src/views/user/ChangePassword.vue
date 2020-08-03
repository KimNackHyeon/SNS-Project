<template>
  <div class="user join wrapC" style="position: relative;">
    <h1>비밀번호 변경</h1>
    <div class="form-wrap">
      <h1 class="text-left">새로운 비밀번호로 변경하세요.<br><br></h1>
      <div class="input-with-label">
        <label for="password">새 비밀번호</label>
        <input v-model='password' :type="passwordType" id="password" placeholder="password" >
        <p v-if="pwdErrMsg" style="color: red;">영문,숫자 포함 8 자리이상이어야 합니다.</p>
      </div>

      <div class="input-with-label">
        <label for="password-confirm">새 비밀번호 확인</label>
        <input v-model='passwordConfirm' :type="passwordConfirmType" id="password-confirm" placeholder="password confirm">
        <p v-if="pwdConfErrMsg" style="color: red;">비밀번호가 일치하지 않습니다.</p>
      </div>
      <button v-if="!changePassword" class="btn-bottom" style="border-radius: 10px; float: right; position: unset; margin: 0 0 10px; background-color: lightgray;">새 비밀번호로 변경</button>
      <button v-if="changePassword" class="btn-bottom" @click="sendPwd" style="border-radius: 10px; float: right; position: unset; margin: 0 0 10px;">새 비밀번호로 변경</button>
    </div>
  </div>
</template>

<script>
import PasswordValidator from 'password-validator'
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  data() {
    return {
      password: "",
      passwordConfirm: "",
      passwordType: "password",
      passwordConfirmType: "password",
      pwdErrMsg: false,
      pwdSucMsg: false,
      pwdConfErrMsg: false,
      pwdConfSucMsg: false,
      passwordSchema: new PasswordValidator(),
      changePassword: false,
    }
  },
  watch: {
    'password'() {
      
    }
  },
  
}
</script>

<style>

</style>
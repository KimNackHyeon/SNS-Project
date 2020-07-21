<template>
  <div class="user join wrapC" style="position: relative;">
    <h1>비밀번호 찾기</h1>
    <p style="font-size: 13px; text-align: center;">비밀번호는 가입 시 입력하신 이메일을 통해 찾을 수 있습니다.</p>
    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="email" id="email" type="text" />
        <label for="email">이메일</label>
      </div>

      <div class="input-with-label">
        <input v-model="phonenumber" id="phonenumber" type="text" />
        <label for="phonenumber">휴대폰 번호</label>
      </div>

      <div class="input-with-label">
        <input
          v-model="certificationNumber"
          id="certificationNumber"
          type="text"
          style="width: 75%"
        />
        <label for="certificationNumber">인증번호</label>
        <button
          class="btn-bottom"
          style="width: 20%; border-radius: 7px; float: right; position: unset; margin: 0;"
        >재전송</button>
      </div>
    </div>

    <div>
      <router-link to="/">
        <button
          class="btn-bottom"
          style="margin-bottom: 0px; position: absolute; bottom: 0; width: 320px"
        >인증번호 보내기</button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      email: "",
      phonenumber: "",
      certificationNumber: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        phonenumber: false,
        certificationNumber: false,
        term: false
      },
      isSubmit: false,
      termPopup: false
    };
  }
};
</script>

<style>
</style>
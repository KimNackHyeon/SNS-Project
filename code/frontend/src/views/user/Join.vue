<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div style="width:100%; height:100%;">
    <div style="height:40px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/">
        <v-btn
          icon
          color="gray"
          style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%; border-right: 1px solid lightgray"
        >
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div class="titleBox">
        <div class="pageTitle">
          <h3>가입하기</h3>
        </div>
      </div>
    </div>
    <div style="width:inherit">
      <div class="user join wrapC" style="width: inherit">
        <div class="form-wrap">
          <div class="input-with-label">
            <label for="email-join">이메일</label>
            <input
              v-model="signupData.email"
              type="text"
              id="email-join"
              placeholder="example@naver.com"
              style="width: 80%;"
            />
            <button
              type="button"
              @click="checkEmail"
              style="width: 18%; height: 50px; background-color: yellowgreen; color: white; border-radius: 10px; margin-left: 2%;"
            >인증</button>
            <div v-if="!confirm" style="margin-top: 10px">
              <label for="confirmNumber" style="margin-top: 3.65rem;">인증번호</label>
              <input
                v-model="certification"
                type="text"
                id="confirmNumber"
                placeholder="comfirm"
                style="width: 80%;"
              />
              <button
                type="button"
                @click="checkCertification"
                style="width: 18%; height: 50px; background-color: yellowgreen; color: white; border-radius: 10px; margin-left: 2%;"
              >확인</button>
            </div>
            <p v-if="emailErrMsg" style="color: red;">유효하지 않은 이메일 형식입니다.</p>
            <p v-if="emailSucMsg && completeMail" style="color: red;">이미 사용중인 이메일입니다.</p>
          </div>

          <div class="input-with-label">
            <label for="password">비밀번호</label>
            <input
              v-model="signupData.password"
              :type="passwordType"
              id="password"
              placeholder="password"
            />
            <p v-if="pwdErrMsg" style="color: red; display: inline;">영문,숫자 포함 8 자리이상이어야 합니다.</p>
          </div>

          <div class="input-with-label">
            <label for="password-confirm">비밀번호 확인</label>
            <input
              v-model="signupData.passwordConfirm"
              :type="passwordConfirmType"
              id="password-confirm"
              placeholder="password confirm"
            />
            <p v-if="pwErrMsg" style="color: red; display: inline;">비밀번호가 일치하지 않습니다.</p>
          </div>

          <div class="input-with-label">
            <label for="nickname">닉네임</label>
            <input
              v-on:input="signupData.nickname = $event.target.value"
              type="text"
              id="nickname"
              placeholder="nickname"
              maxlength="128"
            />
            <p v-if="nickErrMsg" style="color: red; display: inline;">이미 사용중인 닉네임입니다.</p>
          </div>
          <div class="input-with-label">
            <label for="address">주소</label>
            <input
              tabindex="-1"
              v-model="signupData.address"
              type="text"
              id="address"
              placeholder="주소를 입력하세요."
              @click="addressgo()"
            />
          </div>
          <v-dialog v-model="open" scrollable width="100%">
            <v-card>
              <v-card-title>주소 검색</v-card-title>
              <v-divider></v-divider>
              <v-card-text>
                <DaumPostcode style="height:300px" :on-complete="handleAddress" />
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="open = false">닫기</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
        <label>
          <input @click="close_toggle" v-model="isTerm" type="checkbox" id="term" />
          <span>약관을 동의합니다.</span>
        </label>

        <v-dialog v-model="dialog" scrollable width="100%">
          <v-card>
            <v-card-title>약 관</v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <h5>
                <strong>제 1 장 총 칙</strong>
                <br />
                <br />
                <strong>제 1 조 (목적)</strong>
                <br />
                이 약관은 {COMPANY_NAME}(이하 "사이트"라 합니다)에서 제공하는 인터넷서비스(이하 "서비스"라 합니다)의 이용 조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.
                <br />
                <br />
                <strong>제 2 조 (약관의 효력 및 변경)</strong>
                <br />① 이 약관은 서비스 화면이나 기타의 방법으로 이용고객에게 공지함으로써 효력을 발생합니다.
                <br />② 사이트는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.
                <br />
                <br />
                <strong>제 3 조 (용어의 정의)</strong>
                <br />이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
                <br />① 회원 : 사이트와 서비스 이용계약을 체결하거나 이용자 아이디(ID)를 부여받은 개인 또는 단체를 말합니다.
                <br />② 신청자 : 회원가입을 신청하는 개인 또는 단체를 말합니다.
                <br />③ 아이디(ID) : 회원의 식별과 서비스 이용을 위하여 회원이 정하고 사이트가 승인하는 문자와 숫자의 조합을 말합니다.
                <br />④ 비밀번호 : 회원이 부여 받은 아이디(ID)와 일치된 회원임을 확인하고, 회원 자신의 비밀을 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 말합니다.
                <br />⑤ 해지 : 사이트 또는 회원이 서비스 이용계약을 취소하는 것을 말합니다.
                <br />
                <br />
                <strong>제 2 장 서비스 이용계약</strong>
                <br />
                <br />
                <strong>제 4 조 (이용계약의 성립)</strong>
                <br />① 이용약관 하단의 동의 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다.
                <br />② 이용계약은 서비스 이용희망자의 이용약관 동의 후 이용 신청에 대하여 사이트가 승낙함으로써 성립합니다.
                <br />
                <br />
                <strong>제 5 조 (이용신청)</strong>
                <br />① 신청자가 본 서비스를 이용하기 위해서는 사이트 소정의 가입신청 양식에서 요구하는 이용자 정보를 기록하여 제출해야 합니다.
                <br />② 가입신청 양식에 기재하는 모든 이용자 정보는 모두 실제 데이터인 것으로 간주됩니다. 실명이나 실제 정보를 입력하지 않은 사용자는 법적인 보호를 받을 수 없으며, 서비스의 제한을 받을 수 있습니다.
                <br />
                <br />
                <strong>제 6 조 (이용신청의 승낙)</strong>
                <br />① 사이트는 신청자에 대하여 제2항, 제3항의 경우를 예외로 하여 서비스 이용신청을 승낙합니다.
                <br />② 사이트는 다음에 해당하는 경우에 그 신청에 대한 승낙 제한사유가 해소될 때까지 승낙을 유보할 수 있습니다.
                <br />가. 서비스 관련 설비에 여유가 없는 경우
                <br />나. 기술상 지장이 있는 경우
                <br />다. 기타 사이트가 필요하다고 인정되는 경우
                <br />③ 사이트는 신청자가 다음에 해당하는 경우에는 승낙을 거부할 수 있습니다.
                <br />가. 다른 개인(사이트)의 명의를 사용하여 신청한 경우
                <br />나. 이용자 정보를 허위로 기재하여 신청한 경우
                <br />다. 사회의 안녕질서 또는 미풍양속을 저해할 목적으로 신청한 경우
                <br />라. 기타 사이트 소정의 이용신청요건을 충족하지 못하는 경우
                <br />
                <br />
                <strong>제 7 조 (이용자정보의 변경)</strong>
                <br />회원은 이용 신청시에 기재했던 회원정보가 변경되었을 경우에는, 온라인으로 수정하여야 하며 변경하지 않음으로 인하여 발생되는 모든 문제의 책임은 회원에게 있습니다.
                <br />
                <br />
                <strong>제 3 장 계약 당사자의 의무</strong>
                <br />
                <br />
                <strong>제 8 조 (사이트의 의무)</strong>
                <br />① 사이트는 회원에게 각 호의 서비스를 제공합니다.
                <br />가. 신규서비스와 도메인 정보에 대한 뉴스레터 발송
                <br />나. 추가 도메인 등록시 개인정보 자동 입력
                <br />다. 도메인 등록, 관리를 위한 각종 부가서비스
                <br />② 사이트는 서비스 제공과 관련하여 취득한 회원의 개인정보를 회원의 동의없이 타인에게 누설, 공개 또는 배포할 수 없으며, 서비스관련 업무 이외의 상업적 목적으로 사용할 수 없습니다. 단, 다음 각 호의 1에 해당하는 경우는 예외입니다.
                <br />가. 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우
                <br />나. 범죄에 대한 수사상의 목적이 있거나 정보통신윤리 위원회의 요청이 있는 경우
                <br />다. 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우
                <br />③ 사이트는 이 약관에서 정한 바에 따라 지속적, 안정적으로 서비스를 제공할 의무가 있습니다.
                <br />
                <br />
                <strong>제 9 조 (회원의 의무)</strong>
                <br />① 회원은 서비스 이용 시 다음 각 호의 행위를 하지 않아야 합니다.
                <br />가. 다른 회원의 ID를 부정하게 사용하는 행위
                <br />나. 서비스에서 얻은 정보를 사이트의 사전승낙 없이 회원의 이용 이외의 목적으로 복제하거나 이를 변경, 출판 및 방송 등에 사용하거나 타인에게 제공하는 행위
                <br />다. 사이트의 저작권, 타인의 저작권 등 기타 권리를 침해하는 행위
                <br />라. 공공질서 및 미풍양속에 위반되는 내용의 정보, 문장, 도형 등을 타인에게 유포하는 행위
                <br />마. 범죄와 결부된다고 객관적으로 판단되는 행위
                <br />바. 기타 관계법령에 위배되는 행위
                <br />② 회원은 관계법령, 이 약관에서 규정하는 사항, 서비스 이용 안내 및 주의 사항을 준수하여야 합니다.
                <br />③ 회원은 내용별로 사이트가 서비스 공지사항에 게시하거나 별도로 공지한 이용 제한 사항을 준수하여야 합니다.
                <br />
                <br />
                <strong>제 4 장 서비스 제공 및 이용</strong>
                <br />
                <br />
                <strong>제 10 조 (회원 아이디(ID)와 비밀번호 관리에 대한 회원의 의무)</strong>
                <br />① 아이디(ID)와 비밀번호에 대한 모든 관리는 회원에게 책임이 있습니다. 회원에게 부여된 아이디(ID)와 비밀번호의 관리소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 전적인 책임은 회원에게 있습니다.
                <br />② 자신의 아이디(ID)가 부정하게 사용된 경우 또는 기타 보안 위반에 대하여, 회원은 반드시 사이트에 그 사실을 통보해야 합니다.
                <br />
                <br />
                <strong>제 11 조 (서비스 제한 및 정지)</strong>
                <b></b>
                ① 사이트는 전시, 사변, 천재지변 또는 이에 준하는 국가비상사태가 발생하거나 발생할 우려가 있는 경우와 전기통신사업법에 의한 기간통신 사업자가 전기통신서비스를 중지하는 등 기타 불가항력적 사유가 있는 경우에는 서비스의 전부 또는 일부를 제한하거나 정지할 수 있습니다.
                <br />② 사이트는 제1항의 규정에 의하여 서비스의 이용을 제한하거나 정지할 때에는 그 사유 및 제한기간 등을 지체없이 회원에게 알려야 합니다.
                <br />
                <br />
                <strong>제5장 계약사항의 변경, 해지</strong>
                <br />
                <br />
                <strong>제 12 조 (정보의 변경)</strong>
                <br />회원이 주소, 비밀번호 등 고객정보를 변경하고자 하는 경우에는 홈페이지의 회원정보 변경 서비스를 이용하여 변경할 수 있습니다.
                <br />
                <br />
                <strong>제 13 조 (계약사항의 해지)</strong>
                <br />회원은 서비스 이용계약을 해지할 수 있으며, 해지할 경우에는 본인이 직접 서비스를 통하거나 전화 또는 온라인 등으로 사이트에 해지신청을 하여야 합니다. 사이트는 해지신청이 접수된 당일부터 해당 회원의 서비스 이용을 제한합니다. 사이트는 회원이 다음 각 항의 1에 해당하여 이용계약을 해지하고자 할 경우에는 해지조치 7일전까지 그 뜻을 이용고객에게 통지하여 소명할 기회를 주어야 합니다.
                <br />① 이용고객이 이용제한 규정을 위반하거나 그 이용제한 기간 내에 제한 사유를 해소하지 않는 경우
                <br />② 정보통신윤리위원회가 이용해지를 요구한 경우
                <br />③ 이용고객이 정당한 사유 없이 의견진술에 응하지 아니한 경우
                <br />④ 타인 명의로 신청을 하였거나 신청서 내용의 허위 기재 또는 허위서류를 첨부하여 이용계약을 체결한 경우
                <br />사이트는 상기 규정에 의하여 해지된 이용고객에 대해서는 별도로 정한 기간동안 가입을 제한할 수 있습니다.
                <br />
                <br />
                <strong>제6장 손해배상</strong>
                <br />
                <br />
                <strong>제 14 조 (면책조항)</strong>
                <br />① 사이트는 회원이 서비스 제공으로부터 기대되는 이익을 얻지 못하였거나 서비스 자료에 대한 취사선택 또는 이용으로 발생하는 손해 등에 대해서는 책임이 면제됩니다.
                <br />② 사이트는 회원의 귀책사유나 제3자의 고의로 인하여 서비스에 장애가 발생하거나 회원의 데이터가 훼손된 경우에 책임이 면제됩니다.
                <br />③ 사이트는 회원이 게시 또는 전송한 자료의 내용에 대해서는 책임이 면제됩니다.
                <br />④ 상표권이 있는 도메인의 경우, 이로 인해 발생할 수도 있는 손해나 배상에 대한 책임은 구매한 회원 당사자에게 있으며, 사이트는 이에 대한 일체의 책임을 지지 않습니다.
                <br />
                <br />
                <strong>제 15 조 (관할법원)</strong>
                <br />서비스와 관련하여 사이트와 회원간에 분쟁이 발생할 경우 사이트의 본사 소재지를 관할하는 법원을 관할법원으로 합니다.
              </h5>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <button
                @click="handle_toggle2"
                type="button"
                style="background-color:#a0d469; border:2px #a0d469 solid; border-radius:5px; color:#fff; width:100%;"
              >확인</button>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <span @click="handle_toggle" type="button">약관보기</span>
      </div>
    </div>
    <div style="position: fixed; bottom: 0; width: inherit">
    <button
          v-if="JoinBtn"
          style="margin-top:60px; width:100%"
          class="btn-bottom"
          @click="$emit('signup', signupData)"
        >가입하기</button>
    </div>
  </div>
</template>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
import DaumPostcode from "vuejs-daum-postcode";
import Vue from "vue";
import VueOverflowScroll from "vue-overflow-scroll";
import PasswordValidator from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios";
import Swal from "sweetalert2";
import store from "../../vuex/store.js";

const SERVER_URL = store.state.SERVER_URL;

export default {
  name: "App",
  components: {
    DaumPostcode,
  },
  data() {
    return {
      is_show: false,
      signupData: {
        email: "",
        password: "",
        passwordConfirm: "",
        nickname: "",
        address: "",
      },
      certification: "",
      checkCert: "",
      pwdErrMsg: false,
      pwdSucMsg: false,
      passwordSchema: new PasswordValidator(),
      confirm: true,
      isConfirm: false,
      JoinBtn: false,
      pwErrMsg: false,
      pwSucMsg: false,
      emailErrMsg: false,
      emailSucMsg: false,
      completeMail: false,
      nickErrMsg: false,
      nickSucMsg: false,
      isTerm: false,
      open: false,
      inputAddress: false,
      passwordType: "password",
      passwordConfirmType: "password",
      dialog: false,
    };
  },
  created() {
    this.passwordSchema.is().min(8).is().max(20).has().digits().has().letters();
  },
  watch: {
    select() {
      this.checkSelect();
      this.checkEmailValidate();
      this.checkJoinForm();
    },
    "signupData.passwordConfirm"() {
      this.checkPassword();
    },
    "signupData.password"() {
      this.checkPasswordValidate();
      this.checkPassword();
    },
    "signupData.email"() {
      this.checkEmailValidate();
    },
    "signupData.nickname"() {
      this.checkNickname();
    },
    completeMail() {
      this.completeMailCheck();
    },
    input: {
      handler() {
        this.checkJoinForm();
      },
      deep: true,
    },
  },
  methods: {
    handleAddress(data) {
      let fullAddress = data.address;
      let extraAddress = "";
      if (data.addressType === "R") {
        if (data.bname !== "") {
          extraAddress += data.bname;
        }
        if (data.buildingName !== "") {
          extraAddress +=
            extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
        }
        fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
      }
      this.signupData.address = fullAddress;
      this.open = false;
      this.inputAddress = true;
    },
    addressgo() {
      if (this.open == false) {
        this.open = true;
      } else {
        this.open = false;
      }
    },
    handle_toggle: function () {
      this.is_show = !this.is_show; // #2, #3
      this.dialog = true;
    },
    handle_toggle2: function () {
      this.is_show = !this.is_show; // #2, #3
      if (this.isTerm == false) {
        this.isTerm = true;
      }
      this.dialog = false;
      this.checkJoinForm();
    },
    close_toggle: function () {
      this.is_show = false; // #2, #3
      this.isTerm = true;
      this.checkJoinForm();
    },
    checkJoinForm() {
      if (
        this.signupData.email &&
        this.signupData.password &&
        this.isTerm &&
        this.isConfirm &&
        this.signupData.passwordConfirm &&
        this.signupData.nickname &&
        this.signupData.address &&
        this.pwdSucMsg &&
        !this.completeMail &&
        this.pwSucMsg &&
        this.nickSucMsg
      ) {
        this.JoinBtn = true;
      } else {
        this.JoinBtn = false;
      }
    },
    checkPassword() {
      if (this.signupData.passwordConfirm !== "") {
        if (this.signupData.password != this.signupData.passwordConfirm) {
          this.pwErrMsg = true;
          this.pwSucMsg = false;
        } else if (
          this.signupData.password &&
          this.signupData.passwordConfirm &&
          this.signupData.password === this.signupData.passwordConfirm
        ) {
          this.pwErrMsg = false;
          this.pwSucMsg = true;
        }
      }
    },
    checkCertification() {
      if (this.certification === this.checkCert) {
        this.confirm = true;
        this.isConfirm = true;
        this.checkJoinForm();
        // // console.log('good')
        Swal.fire({
          icon: "success",
          title: "인증이 완료되었습니다.",
          showConfirmButton: false,
          timer: 1500,
        });
      } else {
        // // console.log('실패')

        Swal.fire({
          title: "다시 한번 더 확인해주세요",
          text: "인증번호가 틀렸어요",
        });
      }
    },
    checkNickname() {
      axios
        .post(
          `https://i3b301.p.ssafy.io:9999/food/api/account/nicknameconfirm`,
          this.signupData
        )
        .then((data) => {
          // // console.log(data.data.data)
          if (data.data.data == "1") {
            // // console.log('중복')
            this.nickErrMsg = true;
            this.nickSucMsg = false;
            this.checkJoinForm();
          } else {
            // // console.log('가능')
            this.nickSucMsg = true;
            this.nickErrMsg = false;
            this.checkJoinForm();
          }
        })
        .catch(function () {});
    },
    checkPasswordValidate() {
      if (
        this.signupData.password.length >= 0 &&
        !this.passwordSchema.validate(this.signupData.password)
      ) {
        this.pwdErrMsg = true;
        this.pwdSucMsg = false;
      } else {
        this.pwdSucMsg = true;
        this.pwdErrMsg = false;
      }
    },
    completeMailCheck() {
      this.checkJoinForm();
    },
    checkEmail() {
      if (this.signupData.email) {
        //         Swal.fire({
        //             icon: 'success',
        //             title: '인증번호를 전송했습니다.',
        //             showConfirmButton: false,
        //             timer: 1500
        // })
        let timerInterval;
        Swal.fire({
          title: "인증번호 전송중",
          html: "전송까지 <b></b> 초 남았습니다.",
          timer: 2000,
          timerProgressBar: true,
          onBeforeOpen: () => {
            Swal.showLoading();
            timerInterval = setInterval(() => {
              const content = Swal.getContent();
              if (content) {
                const b = content.querySelector("b");
                if (b) {
                  b.textContent = Swal.getTimerLeft();
                }
              }
            }, 100);
          },
          onClose: () => {
            clearInterval(timerInterval);
          },
        }).then((result) => {
          /* Read more about handling dismissals below */
          if (result.dismiss === Swal.DismissReason.timer) {
            // console.log("I was closed by the timer");
          }
        });
        axios
          .post(
            `https://i3b301.p.ssafy.io:9999/food/api/account/emailconfirm`,
            this.signupData
          )
          .then((data) => {
            if (data.data.data == "1") {
              // // console.log('success')
              this.completeMail = true;
              Swal.fire({
                title: "다시 한번 더 확인해주세요",
                text: "이미 가입된 이메일입니다.",
              });
            } else {
              // // console.log('no')
              this.confirm = !confirm;
              this.checkCert = data.data.data;
              // // console.log(data.data.data)
              this.completeMail = false;
            }
          });
        this.emailSucMsg = true;
      } else {
        this.emailSucMsg = false;
      }
    },
    checkEmailValidate() {
      if (
        this.signupData.email.length >= 14 &&
        EmailValidator.validate(this.signupData.email)
      ) {
        this.emailSucMsg = true;
        this.emailErrMsg = false;
        this.completeMail = false;
        if (this.emailSucMsg) {
          // // console.log('sucsess1')
          // // console.log(this.signupData.email)
          axios
            .post(
              `https://i3b301.p.ssafy.io:9999/food/api/account/checkemail`,
              this.signupData
            )
            .then((data) => {
              // // console.log('sucsess2')
              if (data.data.data === "1") {
                this.completeMail = true;
              } else {
                // // console.log('no')
                this.checkCert == data.data.data;
                this.completeMail = false;
              }
            })
            .catch(function () {});
        }
      } else {
        this.emailSucMsg = false;
        this.emailErrMsg = true;
      }
    },
  },
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
.btn-bottom {
  /* position: fixed;
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 18px;
  width: 20%;
  bottom: 0; */
}
</style>
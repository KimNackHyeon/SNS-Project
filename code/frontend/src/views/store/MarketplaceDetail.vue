<template>
  <div style="height: 100%;">
    <!-- 메뉴바 -->
    <div style="height:48px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/store/marketplace">
        <v-btn icon color="gray" style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div class="titleBox">
        <div class="pageTitle">
          <p style="margin: 0;">우리 동네 장터</p>
        </div>
      </div>
    </div>
    <!-- 지역 -->
    <div style="text-align: center; border-bottom: 1px solid lightgray; padding: 5px 0; background: #eee;">
      <h4 style="">{{ detailinfo.address }}</h4>
    </div>
    <!-- 작성자 등록일 -->
    <div style="overflow: hidden; border-bottom: 1px solid lightgray">
      <div style="float: left;">
        <div style="font-size: 10px; display: inline-block; margin-left: 10px">등록일</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 10px">{{ detailinfo.regist_date }}</div>
      </div>
      <div style="float: right;">
        <div style="font-size: 10px; display: inline-block; margin-right: 20px;">작성자</div>
        <div style="font-size: 10px; display: inline-block; margin-right: 20px;">{{ detailinfo.nickname }}</div>
      </div>
    </div>
    <div style="overflow: scroll; height: 400px;">
      <!-- 교환물품 -->
      <table style="width: 100%; border-collapse: collapse;">
        <thead></thead>
        <tbody>
          <tr class="tableHeader">
            <td colspan="2">제가 가진</td><td></td><td colspan="2">당신이 가진</td><td></td>
          </tr>
          <tr class="tableBody">
            <td><img :src="'/img/food/' + detailinfo.myfood + '.png'" :alt="detailinfo.myfood" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td><h4>{{ detailinfo.myfood_kor }} 1개</h4></td>
            <td style="padding-left: 5px;">를</td>
            <td><img :src="'/img/food/' + detailinfo.tradefood1 + '.png'" :alt="detailinfo.tradefood1" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td><h4>{{ detailinfo.tradefood1_kor }} {{ detailinfo.foodcount1 }}개</h4></td>
            <td style="padding-left: 5px;">와 바꾸고 싶어요</td>
          </tr>
          <tr class="tableBody" v-if="detailinfo.tradefood2">
            <td><img :src="'/img/food/' + detailinfo.myfood + '.png'" :alt="detailinfo.myfood" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td><h4>{{ detailinfo.myfood_kor }} 1개</h4></td>
            <td style="padding-left: 5px;">를</td>
            <td><img :src="'/img/food/' + detailinfo.tradefood2 + '.png'" :alt="detailinfo.tradefood2" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td><h4>{{ detailinfo.tradefood2_kor }} {{ detailinfo.foodcount2 }}개</h4></td>
            <td style="padding-left: 5px;">와 바꾸고 싶어요</td>
          </tr>
        </tbody>
      </table>
      <!-- 식품 싯가 -->
      <div style="background: black; font-size: 12px">
        <div style="padding: 5px 0 0 5px">
          <div style="color: white; display: inline-block;">1일전</div>
          <div style="color: yellow; display: inline-block; margin: 0 5px;">계란/특란 (등급:중품)</div>
          <div style="color: white; display: inline-block;">소매 개당 가격 172 원</div>
        </div>
        <div style="padding: 5px 0 0 5px">
          <div style="color: white; display: inline-block;">1일전</div>
          <div style="color: yellow; display: inline-block; margin: 0 5px;">양파/양파 (등급:중품)</div>
          <div style="color: white; display: inline-block;">소매 개당 가격 332 원</div>
        </div>
        <div style="padding: 5px 0 0 5px">
          <div style="color: white; display: inline-block;">1일전</div>
          <div style="color: yellow; display: inline-block; margin: 0 5px;">감자/수미 (등급:중품)</div>
          <div style="color: white; display: inline-block;">소매 개당 가격 211 원</div>
        </div>
        <div style="color: gray; font-size: 10px; text-align: right;">출처 : 농산물 유통정보 KAMIS </div>
      </div>
      <!-- 글 내용 -->
      <div style="padding: 10px; height: 300px">
        {{ detailinfo.content }}
      </div>
    </div>
    <!-- 현금 거래 -->
    <div style="padding: 5px; background: #eee; overflow: hidden;">
      <div style="float: left"><h4>현금 거래시</h4></div>
      <div style="float: right">
        <div style="display: inline-block"><h6>1개당</h6></div>
        <div style="display: inline-block; color: rgb(209, 77, 0); margin: 0 5px 0 10px;"><h4>{{ detailinfo.price }}</h4></div>
        <div style="display: inline-block"><h4>원</h4></div>
      </div>
    </div>
    <!-- 채팅 버튼 -->
    <div>
      <!-- <router-link to="/chat/privatechat"> -->
      
      <v-btn @click="registChattingRoom()"
        color="rgb(160, 212, 105)" 
        style="width: 100%; height: 50px; color: white; font-size: 22px; border-radius: unset;" 
        >
        <v-icon style="margin-right: 5px">mdi-chat</v-icon>채팅하기
        </v-btn>
    </div>
  </div>
</template>

<script>
// const SERVER_URL = "http://127.0.0.1:9999/food/api";
// const SERVER_URL = "http://i3b301.p.ssafy.io:9999/food/api";
import store from '../../vuex/store.js'
import axios from 'axios'

const SERVER_URL = store.state.SERVER_URL;

export default {
  data(){
    return{
      privatechat:``, //해당 게시글의 번호가 들어가면된다 임시로 123으로 해놈
      chatName:'',
      userinfo:'',
      detailinfo: [],
    }
  },
  methods:{
    registChattingRoom(){
      axios.post(`${SERVER_URL}/chatting`, {chatName:this.detailinfo.nickname+" 님의 "+this.detailinfo.myfood_kor+"교환방", chatKey:this.privatechat ,myEmail:this.userinfo.email, myNickname:this.userinfo.nickname, otherEmail:this.detailinfo.email, otherNickname:this.detailinfo.nickname, type:"1"})
        .then(response => {
          this.chatName = response.data;
          this.$router.push({ name: 'PrivateChat', params: { privatechat: this.privatechat, chatName: this.chatName }})
        })
        .catch(error => {
        })
    }
  },
  created(){
    this.privatechat = this.$route.params.id
    if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
    const id = this.$route.params.id
    if (id === undefined) {
      // this.$router.push('/store/groupbuying')
      this.$router.go(-1)
    }
    axios.get(`${SERVER_URL}/trade/article/${id}`)
    .then(response => {
      this.detailinfo = response.data
    })
    .catch(error => {
    })
    // axios
    //     .get(`${SERVER_URL}/account/apitest`)
    //     .then(response => {
    //         this.xmldata = response.data;
    //         console.log(this.xmldata);
    //         console.log(this.detailinfo)
    //         }
    //     )
  },
  computed: {
    param: function() {
      return this.$route.params
    }
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
  .tableHeader {
    text-align: center; 
    background: rgb(202, 231, 171); 
    height: 28px; 
    font-size: 16px; 
    font-weight: 650; 
    color: gray;
  }
  .tableBody {
    background: rgb(224, 241, 206);
    height: 45px;
    border-top: 1px solid white;
  }
</style>>

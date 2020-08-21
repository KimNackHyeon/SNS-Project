<template>
  <div style="height: 100%; width:100%;">
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
    <div>
      <!-- 교환물품 -->
      <table style="width: 100%; border-collapse: collapse;">
        <thead></thead>
        <tbody>
          <tr class="tableHeader">
            <td colspan="2">제가 가진</td><td></td><td colspan="2">당신이 가진</td><td></td>
          </tr>
          <tr class="tableBody">
            <td><img :src="'/img/food/' + detailinfo.myfood + '.png'" :alt="detailinfo.myfood" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td v-if="myapi.unit === '1개'"><h4>{{ detailinfo.myfood_kor }} {{myapi.unit}}</h4></td>
            <td v-if="myapi.unit !== '1개'"><h4>{{ detailinfo.myfood_kor }} {{myapi.unit}}</h4></td>
            <td style="padding-left: 5px;">을/를</td>
            <td><img :src="'/img/food/' + detailinfo.tradefood1 + '.png'" :alt="detailinfo.tradefood1" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td><h4>{{ detailinfo.tradefood1_kor }} {{ detailinfo.tradefoodcount1 }}개</h4></td>
            <td style="padding-left: 5px;">와 바꾸고 싶어요</td>
          </tr>
          <tr class="tableBody" v-if="detailinfo.tradefood2">
            <td><img :src="'/img/food/' + detailinfo.myfood + '.png'" :alt="detailinfo.myfood" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td><h4>{{ detailinfo.myfood_kor }} {{ detailinfo.myfoodcount2 }}개</h4></td>
            <td style="padding-left: 5px;">를</td>
            <td><img :src="'/img/food/' + detailinfo.tradefood2 + '.png'" :alt="detailinfo.tradefood2" style="width: 30px; height: 30px; margin: 0 10px;"></td>
            <td><h4>{{ detailinfo.tradefood2_kor }} {{ detailinfo.tradefoodcount2 }}개</h4></td>
            <td style="padding-left: 5px;">와 바꾸고 싶어요</td>
          </tr>
        </tbody>
      </table>
      <!-- 식품 싯가 -->
      <div style="background: black; font-size: 12px">
        <div style="color: white; font-size: 15px; text-align: center;">- 실시간 시세 정보 -</div>
        <div v-if="myapi.name!=''" style="padding: 5px 0 0 5px">
          <div style="color: yellow; display: inline-block; margin: 0 5px;">{{myapi.name}}</div>
          <div style="color: white; display: inline-block;">소매 {{myapi.unit}}당 가격 {{myapi.price}} 원</div>
        </div>
        <div  v-if="trade1api.name!=''" style="padding: 5px 0 0 5px">
          <div style="color: yellow; display: inline-block; margin: 0 5px;">{{trade1api.name}}</div>
          <div style="color: white; display: inline-block;">소매 {{trade1api.unit}}당 가격 {{trade1api.price}} 원</div>
        </div>
        <div  v-if="trade2api.name!=''" style="padding: 5px 0 0 5px">
          <div style="color: yellow; display: inline-block; margin: 0 5px;">{{trade2api.name}}</div>
          <div style="color: white; display: inline-block;">소매 {{trade2api.unit}}당 가격 {{trade2api.price}} 원</div>
        </div>
        <div  v-if="myapi.name==''&&trade1api.name==''&&trade2api.name==''" style="padding: 5px 0 0 5px">
          <div style="color: white; display: inline-block;">해당 음식들의 가격정보가 없습니다.</div>
        </div>
        <div style="color: gray; font-size: 10px; text-align: right;">출처 : 농산물 유통정보 KAMIS </div>
      </div>
      <!-- 글 내용 -->
      <div v-html="detailinfo.content" style="padding: 10px; height: 280px;">
        {{ detailinfo.content }}
      </div>
    </div>
    <div style="position:fixed; bottom: 0; width: inherit">
      <!-- 현금 거래 -->
      <div style="padding: 5px; background: #eee; overflow: hidden;">
        <div style="float: left"><h4>현금 거래시</h4></div>
        <div style="float: right">
          <div v-if="!(myapi.name==''&&trade1api.name==''&&trade2api.name=='')" style="display: inline-block"><h6>{{myapi.unit}}당</h6></div>
          <div v-if="!(myapi.name==''&&trade1api.name==''&&trade2api.name=='')" style="display: inline-block; color: rgb(209, 77, 0); margin: 0 5px 0 10px;"><h4>{{ myapi.price }}</h4></div>
          <div v-if="!(myapi.name==''&&trade1api.name==''&&trade2api.name=='')" style="display: inline-block"><h4>원</h4></div>
          <div v-if="myapi.name==''&&trade1api.name==''&&trade2api.name==''" style="display: inline-block; color: rgb(209, 77, 0); margin: 0 5px 0 10px;"><h4>직접 문의해주세요.</h4></div>
        </div>
      </div>
      <div style="overflow: hidden">
      <!-- 채팅 버튼 -->
        <div style="float:left; width: 49%">
          <!-- <router-link to="/chat/privatechat"> -->
          <v-btn @click="registChattingRoom()"
            color="rgb(160, 212, 105)" 
            style="width: 100%; height: 50px; color: white; font-size: 22px; border-radius: unset;" 
            >
            <v-icon style="margin-right: 5px">mdi-chat</v-icon>채팅하기
          </v-btn>
        </div>
        <!-- 참가자 명단 -->
        <div v-if="userinfo.email == detailinfo.email" style="float: right; width: 49%" @click="member">
          <v-btn 
            color="rgb(160, 212, 105)" 
            style="width: 100%; height: 50px; color: white; font-size: 22px; border-radius: 0px;" 
            >
            <v-icon style="margin-right: 5px">mdi-account-multiple-outline</v-icon>참가자 명단
          </v-btn>
        </div>
        <v-dialog v-model="openMember" scrollable width= "100%">
          <v-card>
            <v-card-title >참가자 {{memberList.length}}명</v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <div class="members" v-for="(member, i) in memberList" :key="i">
                <div class="userImg">
                  <v-avatar size="35" @click="moveUser(member.email)"><img :src="member.image" :alt="`${member.nickname} 사진`"></v-avatar>
                </div>
                <div class="content">
                  <p class="memberNick" @click="moveUser(member.email)">{{member.nickname}}</p>
                  <p class="memberEmail">{{member.email}}</p>
                </div>
                <div class="completebtn" @click="onCompleteBtn(member.email, member.nickname)">
                  <v-btn class="Complete" color="#a0d469" style="box-shadow: unset; color: white">거래완료</v-btn>
                </div>
              </div>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="black" text @click="openMember = false">닫기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- 참가하기 -->
        <div v-if="userinfo.email != detailinfo.email" style="float: right; width: 49%"  @click="onParticipate">
          <v-btn 
            color="rgb(160, 212, 105)" 
            style="width: 100%; height: 50px; color: white; font-size: 22px; border-radius: 0px;" 
            >
            <v-icon style="margin-right: 5px">mdi-account-multiple-outline</v-icon>참가하기
          </v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// const SERVER_URL = "http://127.0.0.1:9999/food/api";
// const SERVER_URL = "http://i3b301.p.ssafy.io:9999/food/api";
import store from '../../vuex/store.js'
import axios from 'axios'
import Swal from 'sweetalert2'
import $ from 'jquery'

const SERVER_URL = store.state.SERVER_URL;

export default {
  data(){
    return{
      privatechat:``, //해당 게시글의 번호가 들어가면된다 임시로 123으로 해놈
      chatName:'',
      userinfo:'',
      detailinfo: '',
      memberList: '',
      openMember: false,
      xmldata:[],
      myapi : {
        name : '',
        unit : '',
        price : '',
      },
      trade1api : {
          name : '',
          unit : '',
          price : '',
      },
      trade2api : {
          name : '',
          unit : '',
          price : '',
      },
    }
  },
  methods:{
    registChattingRoom(){
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/chatting`, {chatName:this.detailinfo.nickname+" 님의 "+this.detailinfo.myfood_kor+"교환방", chatKey:this.privatechat ,myEmail:this.userinfo.email, myNickname:this.userinfo.nickname, otherEmail:this.detailinfo.email, otherNickname:this.detailinfo.nickname, type:"1"})
        .then(response => {
          this.chatName = response.data;
          this.$router.push({ name: 'PrivateChat', params: { privatechat: this.privatechat, chatName: this.chatName }})
        })
        .catch(error => {
        })
    },
    member() {
      if (this.openMember == false) {
        this.openMember = true
      }
      else {
        this.openMember = false
      }
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/participatelist`, {tradeNo:this.$route.params.id})
        .then(response => {
          this.memberList = response.data
        })
        .catch(error => {
          // // console.log(error)
        })
    },
    onParticipate() {
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/participate`, {tradeNo: this.$route.params.id, participantEmail: this.userinfo.email, participantNickname: this.userinfo.nickname,})
        .then(response => {
          // console.log(response.data)
          if(response.data == "Fail"){
            Swal.fire({
              icon: 'error',
              title: '이미 참가하신 교환방입니다.',
            })
                      }else if(response.data == "Owner"){
                        Swal.fire({
              icon: 'error',
              title: '참가할 수 없습니다.',
              text: '작성자 본인은 아니신가요?',
            })
                      }else{
                        Swal.fire({
              icon: 'success',
              title: '참가가 완료되었습니다.',
              showConfirmButton: false,
              timer: 1500
            })
          }
          window.location.reload();
        })
        .catch(error => {
          // // console.log(error)
        })
    },
    moveUser(user_email){
      if(user_email == this.userinfo.email){
        this.$router.push({name: 'Mypage'});
      }else{
        // // console.log(user_email)
        this.$router.push({name: 'Yourpage', params: {email : user_email}});
        this.openMember = false;
      }
    },
    onCompleteBtn(user_email, user_nickname){
      $('Complete').css('color', 'black')
      $('Complete').attr('color', '#eee')
      // axios로 보내기(새소식으로 신선도 평가 보내기)
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/freshalarm`, {email:user_email, content:this.userinfo.nickname+"님을 평가해주세요!", type:"4", image:this.userinfo.profile_image_url, semail:this.userinfo.email })
    .then(response => {
      if(response.data == "Success"){
        Swal.fire({
        icon: 'success',
        title: '거래완료 알림이 전송되었습니다.',
      })
      }
    })
    // console.log(user_email+" "+this.$route.params.id+" "+user_nickname)
    axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/removetrademember`, {participantEmail:user_email, tradeNo:this.$route.params.id, participantNickname: user_nickname})
        .then(response => {
          this.memberList = response.data
          
    })
    
    },
  },
  created(){
    this.privatechat = this.$route.params.id
    // console.log(this.privatechat)
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
    axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/article/${id}`)
    .then(response => {
      this.detailinfo = response.data;
      // 주소 형식 변환
      this.detailinfo.regist_date = this.detailinfo.regist_date.substring(0, 10)
      const [year, month, day] = this.detailinfo.regist_date.split('-')
      this.detailinfo.regist_date = `${year}/${month}/${day}`
      // 내용 엔터 변환
      this.detailinfo.content = this.detailinfo.content.split('^').join('<br />');

      // console.log(this.detailinfo.email);
    })
    .catch(error => {
    })

     axios
        .get(`https://i3b301.p.ssafy.io:9999/food/api/account/apitest`)
        .then(response => {
            this.xmldata = response.data;
            // console.log(this.xmldata);
            
            for(var i=0; i<this.xmldata.price.length;i++){
            var tF = this.xmldata.price[i];
            var tFname = tF.productName.split('/')[0];
            // console.log(tFname);
            if(tF.product_cls_code == '01' ){
              if(tFname == this.detailinfo.myfood_kor ){
                this.myapi.name = tFname;
                this.myapi.unit = tF.unit;
                this.myapi.price = tF.dpr1;
                // console.log(this.myapi.price)
                continue;
              }
              if(tFname == this.detailinfo.tradefood1_kor){
                this.trade1api.name = tFname;
                this.trade1api.unit = tF.unit;
                this.trade1api.price = tF.dpr1;
                continue;
              }
              if(tFname == this.detailinfo.tradefood2_kor){
                this.trade2api.name = tFname;
                this.trade2api.unit = tF.unit;
                this.trade2api.price = tF.dpr1;
              }
            }
          }
        }
      )
  },
  computed: {
    param: function() {
      return this.$route.params
    }
  },
}
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
  .member {
    -webkit-box-shadow: unset;
    box-shadow: unset;
    width: 100%; 
    height: 20px; 
    color: white; 
    font-size: 18px; 
    padding: 0px 30px; 
    border-radius: 0px;
  }
  .members {
    padding: 10px 0 10px 0;
    overflow: hidden;
  }
  .userImg {
    float: left;
    margin-right: 20px;
  }
  .content {
    float: left;
  }
  .completebtn {
    float: right;
    width: 80px;
  }
  .memberNick {
    margin: 0 !important;
    color: black;
    font-weight: bold;
    font-size: 15px;
  }
  .memberEmail {
    margin: 0 !important;
    font-size: 10px
  }
  .v-card {
    height: 450px;
  }
</style>>
<template>
  <div style="height: 100%;">
    <div style="height:48px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/store/groupbuying">
        <v-btn icon color="gray" style="float: left; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div style="display: inline-block; width: 90%; height: 100%; font-size: 17px; text-align: center;">
        <div style="height: 100%; display: flex; justify-content: center; align-items: center;">
          <p style="margin: 0;">공구하기</p>
        </div>
      </div>
    </div>
    <div style="text-align: center; border-bottom: 1px solid lightgray; padding: 5px 0;">
      <h4>{{groupbuying.title}}</h4>
    </div>
    <div style="overflow: hidden; border-bottom: 1px solid lightgray">
      <div style="float: left;">
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">등록일</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">{{groupbuying.regist_date}}</div>
      </div>
      <div style="float: left;">
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">공구 마감일</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">{{groupbuying.end_date}}</div>
      </div>
      <div style="float: right;">
        <div style="font-size: 10px; display: inline-block; margin-right: 20px; color: gray;">작성자</div>
        <div style="font-size: 10px; display: inline-block; margin-right: 20px; color: gray;">{{groupbuying.nickname}}</div>
      </div>
    </div>
    <div style="text-align: center; border-bottom: 1px solid lightgray; padding: 0px;">
      <p style="margin: 0px;">{{groupbuying.address}}</p>
    </div>
    <div style="overflow: hidden; background: black;">
      <div v-if="myapi.unit != ''" style="float: left;">
        <div style="font-size: 10px; display: inline-block; margin-left: 15px; color: orange;">{{groupbuying.food_kor}}</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 15px; color: white;">소매 {{myapi.unit}}당 {{myapi.price}}가격</div>
      </div>
      <div v-if="myapi.unit == ''" style="text-align:center; padding:10px;">
        <div style="font-size: 10px;  margin-left: 15px; color: white;">해당 음식의 가격정보가 없습니다.</div>
        <div style="font-size: 10px;  margin-left: 15px; color: white;">게시물 작성자에게 문의해주세요.</div>
      </div>
      <div style="float: right;">
        <div style="font-size: 6px; display: inline-block; margin-right: 20px; color: gray; padding-top: 20px;">출처: 농산물 유통정보 KAMIS</div>
      </div>
    </div>
    <!-- 참가자 명단 -->
    <v-dialog v-model="openMember" scrollable width= "100%">
      <v-card>
        <v-card-title >참가자 {{groupbuying.now_people}}명</v-card-title>
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
          </div>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="black" text @click="openMember = false">닫기</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <div style="text-align: center; border-bottom: 1px solid lightgray; padding: 5px 0; background: rgb(224, 241, 206);">
      <h3 style="color: gray;">현재 {{groupbuying.max_people}}명 중 {{groupbuying.now_people}}명이 모여</h3>
      <div style="font-size: 30px; display: inline-block; margin-left: 10px; color: red;">{{groupbuying.max_people - groupbuying.now_people}} </div>
      <div style="font-size: 30px; display: inline-block; margin-left: 10px; color: gray;">자리 남았습니다.</div>
    </div>
    <div v-html="groupbuying.content" style="padding: 10px; height: 280px">
    </div>
    <div style="position:fixed; bottom: 0;" :style="{width:frameSize.x+'px'}">
      <div>
        <v-btn
          :href="groupbuying.link"
          color="rgb(160, 212, 105)" 
          style="width: 100%; height: 20px; color: white; font-size: 16px; border-radius: 0px; margin-bottom: 2px;" 
          >
          <v-icon style="margin-right: 5px">mdi-link</v-icon>제품 보러가기
        </v-btn>
      </div>
      <div v-if="groupbuying.email != userinfo.email" style="overflow: hidden;">
        <div style="float: left; width: 49%">
          <v-btn @click="moveDirectChat"
            color="rgb(160, 212, 105)" 
            style="height: 50px; color: white; font-size: 22px; padding: 0px 30px; border-radius: 0px;"  :style="{width:frameSize.x/2+'px'}"
            >
            <v-icon style="margin-right: 5px">mdi-comment-multiple-outline</v-icon>문의하기
            </v-btn>
        </div>
        <div style="float: right; width: 49%" @click="onParticipate">
          <v-btn 
            color="rgb(160, 212, 105)" 
            style="height: 50px; color: white; font-size: 22px; padding: 0px 30px; border-radius: 0px;" :style="{width:frameSize.x/2+'px'}"
            >
            <v-icon style="margin-right: 5px">mdi-account-multiple-outline</v-icon>참가하기
            </v-btn>
        </div>
      </div>
      <div v-if="groupbuying.email == userinfo.email" style="overflow: hidden;">
        <v-btn color="rgb(160, 212, 105)" class="member" @click="member">
        참가자 명단
      </v-btn>
      </div>
    </div>
    
  </div>
</template>

<script>
const SERVER_URL = store.state.SERVER_URL;

import axios from "axios"
import store from '../../vuex/store.js'
import Swal from 'sweetalert2'


export default {
  data() {
    return {
      frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
      userinfo: '',
      groupbuying: '',
      memberList: '',
      directchat: '',
      openMember: false,
      xmldata:[],
      myapi : {
                    name : '',
                    unit : '',
                    price : '',
                },
    }
  },
  computed: {
    param: function() {
      return this.$route.params
    }
  },
  created() {
    if (store.state.kakaoUserInfo.email != null) {
      this.userinfo = store.state.kakaoUserInfo;
    }
    else {
      this.userinfo = store.state.userInfo;
    }
    const id = this.$route.params.id
    if (id === undefined) {
      // this.$router.push('/store/groupbuying')
      this.$router.go(-1)
    }
    else {
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/readdetail/`+id)
        .then(response => {
          // // console.log(response)
          this.groupbuying = response.data
          // console.log(this.groupbuying.now_people / this.groupbuying.max_people)
          // console.log(this.groupbuying)
          // 작성일, 마감일 형식변환
          const [year1, month1, day1] = this.groupbuying.end_date.split('-')
          this.groupbuying.end_date = `${year1}/${month1}/${day1}`
          this.groupbuying.regist_date = this.groupbuying.regist_date.substring(0, 10)
          const [year2, month2, day2] = this.groupbuying.regist_date.split('-')
          this.groupbuying.regist_date = `${year2}/${month2}/${day2}`
          // 줄바꿈
          this.groupbuying.content = this.groupbuying.content.split('^').join('<br />');
          axios.post(`${SERVER_URL}/chatting`, {otherNickname:this.groupbuying.nickname, myNickname:this.userinfo.nickname ,otherEmail:this.groupbuying.email, myEmail:this.userinfo.email, type:"2"})
          .then(response => {
            // console.log(response.data)
            this.directchat = response.data
          })
        })
        .catch(error => {
          // // console.log(error)
        })

axios
        .get(`https://i3b301.p.ssafy.io:9999/food/api/account/apitest`)
        .then(response => {
            this.xmldata = response.data;
            // console.log(this.xmldata);
            for(var i=0; i<this.xmldata.price.length;i++){
                        var tF = this.xmldata.price[i];
                        var tFname = tF.productName.split('/')[0];
                        if(tF.product_cls_code == '01' ){
                            if(tFname==this.groupbuying.food_kor){
                                this.myapi.name = tFname;
                                this.myapi.unit = tF.unit;
                                this.myapi.price = tF.dpr1;
                            }
                        }
                    }
            }
        )
    }
  },
  mounted() {
     this.onResize();
    if (store.state.kakaoUserInfo.email != null) {
      this.userinfo = store.state.kakaoUserInfo;
    }
    else {
      this.userinfo = store.state.userInfo;
    }
  },
  methods: {
      onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
        },
  moveDirectChat(){
      // axios.post(`${SERVER_URL}/directchatting/`, {chatTitle:this.detailinfo.myfood_kor, chatNo:this.privatechat ,email:this.userinfo.email, nickname:this.detailinfo.nickname})
      //   .then(response => {
      //     this.chatName = response.data;
      //     this.$router.push({ name: 'PrivateChat', params: { privatechat: this.privatechat, chatName: this.chatName }})
      //   })
      //   .catch(error => {
      //   })
      axios.post(`${SERVER_URL}/chatting`, {otherNickname:this.groupbuying.nickname, myNickname:this.userinfo.nickname ,otherEmail:this.groupbuying.email, myEmail:this.userinfo.email, type:"2"})
      .then(response=>{
        if(response.data == "불가능"){
          Swal.fire({
          icon: 'error',
          title: '자기자신에게 문의할 수 없습니다.',
        })
        }else{
          // console.log(response.data)
          this.directchat = response.data
          // console.log(this.groupbuying.nickname)
          this.$router.push({ name: 'DirectChat', params: { chatKey: this.directchat, receiverNickname: this.groupbuying.nickname }})
        }
      }).error(response=>{
        // console.log(response)
      })
    },
    onParticipate() {
      if (this.groupbuying.now_people / this.groupbuying.max_people >= 1) {
        Swal.fire({
          icon: 'error',
          text: '인원이 가득 찼습니다.'
        })
      } else {
        
        axios.post(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/participate`, {groupNo: this.$route.params.id, participantEmail: this.userinfo.email, participantNickname: this.userinfo.nickname,})
          .then(response => {
            if(response.data == "Fail"){
              Swal.fire({
              text: "이미 참가하신 공동구매 방입니다.",
            })
            } else {
              this.groupbuying.now_people += 1;
              Swal.fire({
                text: this.groupbuying.title+"공동구매에 참가하셨습니다.",
              })
            }
            // window.location.reload();
          })
          .catch(error => {
            // // console.log(error)
          })
      }
    },
    member() {
      if (this.openMember == false) {
        this.openMember = true
      }
      else {
        this.openMember = false
      }
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/participatelist`, {groupNo:this.$route.params.id})
        .then(response => {
          // // console.log(response.data)
          this.memberList = response.data
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
  },
}
</script>

<style scoped>
.member {
  -webkit-box-shadow: unset;
  box-shadow: unset;
  width: 100%; 
  height: 50px; 
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
</style>
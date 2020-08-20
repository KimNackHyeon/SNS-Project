<template>
  <div style="height: 100%;">
    <div style="height:48px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/Main">
        <v-btn icon color="gray" style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div class="titleBox">
        <div class="pageTitle">
          <p style="margin: 0;">내가 참여한 채팅방</p>
        </div>
      </div>
    </div>
    <div class="chatdivide">
      <button class="marketchat" @click="marketOn">
        <div>
          <!-- <img src="../../assets/images/pencil.png" alt="my recipe"> -->
          <!-- <i class="fas fa-pencil-alt fa-lg"></i> -->
          <h4>우리동네장터</h4>
          <h4>채팅방</h4>
        </div>
      </button>
      <button class="groupchat" @click="groupOn">
        <div>
          <!-- <img src="../../assets/images/bookmark.png" alt="scrap"> -->
          <!-- <i class="far fa-bookmark"></i> -->
          <h4>공동구매</h4>
          <h4>채팅방</h4>
        </div>
      </button>
    </div>
    <v-card text>
      <v-container fluid style="padding: 0; margin: 0;">
        <div style="padding: 10px; margin: 0; overflow: scroll; height: 544px;" grid-list-lg>
          <v-row dense style="padding: 0;">
            <v-col v-for="(info, i) in chatlist" :key="i" cols="12">
              <router-link v-if="info.type === '2'" :to="{ name: 'DirectChat', params: { chatKey : info.chatKey, receiverNickname: info.otherNickname }}">
                <v-card style="padding: 5px;" v-if="info.type === chattype">
                  <v-row justify="left">
                    <v-col style="margin: 0 10px; padding: 0;" cols="auto">
                      <v-img style="margin: 10px; padding: 0" height="30" width="30" :src="info.image"></v-img>
                    </v-col>
                    <v-col style="padding: 12px 0" cols="auto">
                      <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">{{ info.chatName }}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </router-link>
              <router-link v-if="info.type === '1'" :to="{ name: 'PrivateChat', params: { privatechat : info.chatKey, chatName: info.chatName }}">
                <v-card style="padding: 5px;" v-if="info.type === chattype">
                  <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">{{ info.chatName }}</v-card-text>
                </v-card>
              </router-link>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-card>

  </div>
</template>

<script>
import $ from 'jquery'
import axios from 'axios'
import { mapState, mapMutations } from 'vuex'
import store from '../../vuex/store.js'
import Swal from 'sweetalert2'
// const SERVER_URL = 'https://i3b301.p.ssafy.io:9999/food/api';
const SERVER_URL = store.state.SERVER_URL;

export default {
  data() {
    return {
      tradelist: [
      ],
      pagenumber: '',
      switched:true,
      userinfo:'',
      show:false,
      chatlist:'',
      chattype: '1',
    }
  },
  methods:{
    ...mapMutations(['setMapOtherUserInfo']),
    search(){
      if($('.searchBox').css('display')=='none'){
        $('.searchBox').css('display','unset');
      }else{
        if(document.getElementById("searchcontent").value != ""){
          axios.get(`${SERVER_URL}/trade/search/`+document.getElementById("searchcontent").value)
          .then(response => {
          this.tradelist = response.data.list
          // // console.log(this.tradelist)
        })
        .catch(error => {
          // // console.log(error.response)
        })
        $('.searchBox').css('display','none')
        document.getElementById("searchcontent").value = ""
        }else{
          $('.searchBox').css('display','none')
        }
      }
    },
    marketOn() {
      this.chattype = '1'
      $('.marketchat').css('background-color', 'rgb(202, 231, 171)')
      $('.groupchat').css('background-color', 'white')
    },
    groupOn() {
      this.chattype = '2'
      $('.marketchat').css('background-color', 'white')
      $('.groupchat').css('background-color', 'rgb(202, 231, 171)')
    },
    call(){
      if(this.switched == true){
        // console.log(this.userinfo.email)
        axios.get(`${SERVER_URL}/trade/filter/`+this.userinfo.email)
        .then(response => {
          this.tradelist = response.data.list
          // // console.log(this.mapOtherUserInfo)
        })
        .catch(error => {
          // console.log(error)
        })
        this.switched = false;
    }
    else{
      axios.get(`${SERVER_URL}/trade/`)
        .then(response => {
          this.tradelist = response.data.list
          // // console.log(this.tradelist)
          this.mapOtherUserInfo = store.state.mapOtherUserInfo
          this.mapOtherUserInfo.address = this.tradelist[0].address
          this.mapOtherUserInfo.food = this.tradelist[0].myfood
          // // // console.log(this.mapOtherUserInfo)
        })
        .catch(error => {
          // // console.log(error.response)
        })
        this.switched = true;
      }
    },
    edit(pageno) {
      axios.post(`${SERVER_URL}/trade/beforeupdate`, {no:pageno})
        .then(response => {
          this.pagenumber = pageno;
          // // console.log(this.pagenumber)
        })
        .catch(error => {
          // // console.log(error.response)
        })
    },
    del(pageno) {
      Swal.fire({
  title: '정말 삭제하시겠습니까?',
  text: "되돌릴 수 없습니다!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: '네 삭제할게요!'
}).then((result) => {
  if (result.value) {
    axios.post(`${SERVER_URL}/trade/deletetrade`, {no:pageno})
      .then(response => {
        this.pagenumber = pageno;
        Swal.fire({
            // position: 'top-end',
            icon: 'success',
            title: '삭제가 완료되었습니다.',
            showConfirmButton: false,
            timer: 1500
})
        window.location.reload();
      })
      .catch(error => {
        // console.log(error.response)
      })
  }
})
    },
  },
created() {
  if(store.state.kakaoUserInfo.email != null){
    this.userinfo = store.state.kakaoUserInfo;
  }else{
    this.userinfo = store.state.userInfo;
  }
  axios.get(`${SERVER_URL}/chatting/`+ this.userinfo.email)
  .then(response => {
    this.chatlist = response.data.list
    // console.log(this.chatlist)
    this.marketOn()
  })
  .catch(error => {
    // // console.log(error)
  })
}
}
</script>

<style>
  .titleBox {
    display: inline-block;
    width: 77%;
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
  .searchBox{
    width: 306px;
    height: 38px;
    background-color: white;
    display: none;
    position: fixed;
    z-index: 100;
    margin: 5px;
    font-size: 21px;
    padding: 4px 6px;
  }
  .chatdivide {
    height: 7%;
  }
  .marketchat{
    width: 50%;
    height: 100%;
    float: left;
    border: 1px solid lightgray;
    border-left: none;
    background-color: rgb(202, 231, 171);
  }
  .groupchat{
    width: 50%;
    height: 100%;
    float: right;
    border: 1px solid lightgray;
    border-left: none;
    border-right: none;
  }
</style>
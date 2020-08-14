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
          <p style="margin: 0;">우리 동네 장터</p>
        </div>
      </div>
      <router-link to="/store/marketmap" style="">
        <v-btn  flat icon style="width:30x; height:30px; background-size:cover;">
          <img v-show="$route.name=='MarketPlace'" id="mapIcon" style="margin-left:5px; margin-bottom: 8px; width:auto; height:35px;" src="../../assets/images/map.png">
        </v-btn>
      </router-link>
    </div>
    <div style="display: flex; border-bottom: 1px solid lightgray; padding: 0; background: rgb(202, 231, 171); height: 48px;">
      <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
        <v-flex>
          <div class="searchBox">
            <input type="text" placeholder="  검색하기  (ex '달걀')" style="resize:none; width:100%; height:100%;" id="searchcontent" v-model="inputKeyword" @keyup.enter="searchKeyword">
          </div>
          <v-toolbar color="rgba(202, 231, 171)" flat height="48px">
            <v-switch @change="call" label="물물교환 가능 물품만 보기" style="margin-top:20px; margin-right: 18px;"></v-switch>
          </v-toolbar>
        </v-flex>
        <div @click="search" style="border: solid 1px lightgrey; background-color: white;">
          <v-btn icon  style="margin: 5px">
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
      </v-layout>
    </div>
    <v-card flat>
      <v-container fluid style="padding: 0; margin: 0; width:360px;">
        <div style="padding: 10px; margin: 0; overflow: scroll; height: 544px;" grid-list-lg>
          <v-row dense style="padding: 0;">
            <v-col v-for="(info, i) in tradelist" :key="i" cols="12">
              <router-link :to="`/store/marketplace/${ info.no }`">
                <v-card style="padding: 5px;">
                  <v-row style="padding: 0; margin: 0;">
                    <v-col cols="4" style="padding: 0; padding-right: 1px; border-right: solid 1px lightgray;">
                      <v-img height="105" width="105" padding="60" :src="require(`../../assets/images/food/${info.myfood}.png`)" style="border-radius: 5px;"></v-img>
                      <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">{{ info.myfood_kor }}</v-card-text>
                      <v-card-text class="text-center" style="padding: 0; font-size: 9px;">{{ info.address }}</v-card-text>
                    </v-col>
                    <v-col cols="5" class="text-center" style="padding: 0;">
                      <v-row class="pa-0">
                        <v-card-text style="font-size: 20px; padding: 0; padding-bottom: 15px;">물물교환</v-card-text>
                      </v-row>
                      <v-row class="pa-0 ma-1">
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="require(`../../assets/images/food/${info.myfood}.png`)"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">{{ info.myfoodcount1 }}개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="require(`../../assets/images/food/${info.tradefood1}.png`)"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">{{ info.tradefoodcount1 }}개</v-card-text>
                        </v-col>
                      </v-row>
                      <v-row class="pa-0 ma-1" v-if="info.tradefood2!=null">
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="require(`../../assets/images/food/${info.myfood}.png`)"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">{{ info.myfoodcount2 }}개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="require(`../../assets/images/food/${info.tradefood2}.png`)"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">{{ info.tradefoodcount2 }}개</v-card-text>
                        </v-col>
                      </v-row>
                    </v-col>
                    <v-col cols="3" style="padding: 0; border-left: solid 1px lightgray">
                      <v-row class="pa-0 ma-1">
                        <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                          <v-card-text class="text-center pa-0" style="font-size: 15px; font-weight: bold;">구매</v-card-text>
                        </v-col>
                        <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                          <v-card-text class="text-center pa-0" style="font-size: 10px;">1개당</v-card-text>
                        </v-col>
                        <v-col cols="12" class="pa-1 text-center">
                          <span class="text-center pa-0" style="font-size: 18px; color: red;">{{ info.price }}</span>
                          <span class="text-center pa-0" style="font-size: 18px;">원</span>
                        </v-col>
                        <v-col cols="12" class="pa-1 text-center" v-if="userinfo.email === tradelist[i].email">
                          <router-link :to="{ name: 'ModifyMarketPlace', params: { pagenumber: info.no }}">
                            <v-btn @click="edit(info.no)" class="text-center mr-2" style="font-size: 10px; background-color: rgb(159 201 114); color: white;">수정</v-btn>
                          </router-link>
                          <router-link to="/store/marketplace">
                            <v-btn @click="del(info.no)" class="text-center" style="font-size: 10px; background-color: red; color: white;">삭제</v-btn>
                          </router-link>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>
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
// const SERVER_URL = 'http://localhost:9999/food/api';
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
      inputKeyword:'',
      originalList:[],
    }
  },
  methods:{
    ...mapMutations(['setMapOtherUserInfo']),
    search(){
      if($('.searchBox').css('display')=='none'){
        $('.searchBox').css('display','unset');
      }else{
        if(document.getElementById("searchcontent").value != ""){
          axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/search/`+document.getElementById("searchcontent").value)
          .then(response => {
          this.tradelist = response.data.list
          // console.log(this.tradelist)
        })
        .catch(error => {
          // console.log(error.response)
        })
        $('.searchBox').css('display','none')
        document.getElementById("searchcontent").value = ""
        }else{
          $('.searchBox').css('display','none')
        }
      }
    },
    call(){
      if(this.switched == true){
        console.log(this.userinfo.email)
        axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/filter/`+this.userinfo.email)
        .then(response => {
          this.tradelist = response.data.list
          // console.log(this.mapOtherUserInfo)
        })
        .catch(error => {
          console.log(error)
        })
        this.switched = false;
    }
    else{
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/`)
        .then(response => {
          this.tradelist = response.data.list
          // console.log(this.tradelist)
          this.mapOtherUserInfo = store.state.mapOtherUserInfo
          this.mapOtherUserInfo.address = this.tradelist[0].address
          this.mapOtherUserInfo.food = this.tradelist[0].myfood
          // // console.log(this.mapOtherUserInfo)
        })
        .catch(error => {
          // console.log(error.response)
        })
        this.switched = true;
      }
    },
    edit(pageno) {
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/beforeupdate`, {no:pageno})
        .then(response => {
          this.pagenumber = pageno;
          // console.log(this.pagenumber)
        })
        .catch(error => {
          // console.log(error.response)
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
    axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/deletetrade`, {no:pageno})
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
        console.log(error.response)
      })
  }
})
    },
    searchKeyword(){
      var keyword = this.inputKeyword;
      this.tradelist = this.originalList;
      this.tradelist = this.tradelist.filter(function (item) {
          return item.myfood_kor.indexOf(keyword)!=-1;
        });
    }
  },
created() {
  if(store.state.kakaoUserInfo.email != null){
    this.userinfo = store.state.kakaoUserInfo;
  }else{
    this.userinfo = store.state.userInfo;
  }
  axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/`)
    .then(response => {
      this.tradelist = response.data.list
      // console.log(this.tradelist)
      // console.log(this.mapOtherUserInfo.address)
      if (this.mapOtherUserInfo.address.length === 0) {
        for (var i = 0; i < this.tradelist.length; i++) {
          store.state.mapOtherUserInfo.address.push(this.tradelist[i].address)
        }
        // console.log('good')
        // console.log(store.state.mapOtherUserInfo)
      }
      this.originalList = this.tradelist;
    })
    .catch(error => {
      // console.log(error.response)
    })
  },
  updated(){
    
  },
  computed: {
    ...mapState(['mapOtherUserInfo']),
  },
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
</style>
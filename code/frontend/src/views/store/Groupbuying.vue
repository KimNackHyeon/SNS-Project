<template>
  <div style="overflow-x:hidden; width:100%; height:100%; overflow-y:hidden;">
  <v-app style="overflow-x:hidden;  width:100%; height:100%; overflow-y:hidden;">
    <v-card text>
      <v-container fluid style="padding: 0; margin: 0;" :style="{width:frameSize.x+'px', position: relative}">
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <div style="border: solid 1px lightgrey">
            <router-link to="/Main">
            <v-btn icon style="margin: 5px">
              <v-icon>mdi-chevron-left</v-icon>
            </v-btn>
            </router-link>
          </div>
          <v-flex>
            <v-toolbar text style="border: solid 1px lightgrey" height="48px">
              <div class="text-h6 mx-auto">
                공동구매
              </div>
            </v-toolbar>
          </v-flex>
        </v-layout>
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <div class="searchBox">
            <input type="text" placeholder="  검색하기  (ex '달걀')" style="resize:none; width:100%; height:100%;" id="searchcontent" v-model="inputKeyword" @keyup.enter="searchKeyword">
          </div>
          <v-flex style="width:90%; float:left;">
            <v-toolbar color="rgba(202, 231, 171)" text height="48px">
              <v-toolbar color="rgba(202, 231, 171)" text height="48px">
                <v-switch :input-value="distswitch" @change="callwithaddress" label="가까운순" style="margin-top:20px;"></v-switch>
              </v-toolbar>
              <v-toolbar color="rgba(202, 231, 171)" text height="48px">
                <v-switch id="date" :input-value="dateswitch" @change="call" label="마감임박순" style="margin-top:20px;"></v-switch>
              </v-toolbar>
            </v-toolbar>
          </v-flex>
          <div style="border: solid 1px lightgrey; width:10%; float:left;">
            <v-btn icon style="margin: 5px" @click="search">
              <v-icon>mdi-magnify</v-icon>
            </v-btn>
          </div>
        </v-layout>
        <div style="padding: 10px; padding-bottom: 40px; margin: 0; overflow-y: scroll;overflow-x: hidden;" grid-list-lg :style="{height:(frameSize.y-146)+'px'}">
          <v-row dense style="padding: 0;">
            <v-col v-for="(groupBuying, i) in groupBuyings" :key="i" cols="12">
              <router-link :to="`/store/groupbuying/${ groupBuying.no }`">
                <v-card class="onegroupbuying" style="padding: 5px;" >
                  <v-row style="padding: 0; margin: 0;">
                    <v-col cols="3" style="padding: 0; padding-right: 8px; border-right: solid 1px lightgray;">
                      <img height="80" width="80" padding="60" :src="require(`../../assets/images/food/${groupBuying.food}.png`)" style="border-radius: 5px;">
                    </v-col>
                    <v-col cols="6" class="text-left pl-2" style="padding: 0;">
                      <v-card-text style="padding: 0;">{{ groupBuying.title }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">품목 : {{ groupBuying.food_kor }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">동네 : {{ groupBuying.address }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">마감일 : {{ groupBuying.end_date }}</v-card-text>
                    </v-col>
                    <v-col v-if="userinfo.email == groupBuying.email" cols="3" style="padding: 0;">
                      <div style="text-align:center">
                        <router-link :to="`/store/modify/groupbuying/${groupBuying.no}`">
                          <v-btn class="groupbuyingbtn" color="rgba(159, 201, 114)" style="margin-right: 5px; width: 35px; height: 25px; color: white">수정</v-btn>
                        </router-link>
                        <router-link :to="`/store/groupbuying`">
                          <v-btn class="groupbuyingbtn" @click="deleteGroupbuying(groupBuying.no)" color="red" style="width: 35px; height: 25px; color: white">삭제</v-btn>
                        </router-link>
                      </div>
                      <div>
                        <p style="height: 80px; line-height: 80px; text-align: center; font-size: 35px; margin: 0;">{{ groupBuying.now_people }}/{{ groupBuying.max_people }}</p>
                      </div>
                    </v-col>
                    <v-col v-if="userinfo.email != groupBuying.email" cols="3" style="padding: 0;">
                      <div>
                        <p style="height: 100px; line-height: 100px; text-align: center; font-size: 35px; margin: 0;">{{ groupBuying.now_people }}/{{ groupBuying.max_people }}</p>
                      </div>
                    </v-col>
                  </v-row>
                </v-card>
              </router-link>
            </v-col>
          </v-row>
        </div>
        <router-link to="/store/writegroupbuying">
          <div class="writeButton">
            <v-icon color="white">mdi-lead-pencil</v-icon>
            <h4 style="color:whiste; font-size:14px;">글쓰기</h4>
          </div>
        </router-link>
      </v-container>
    </v-card>
  </v-app>
</div>
</template>

<script>
const SERVER_URL = store.state.SERVER_URL;

import axios from "axios"
import store from '../../vuex/store.js'
import Swal from 'sweetalert2'
import $ from 'jquery'

// import '../../components/css/store/groupbuying.scss'
const config = {
    headers: {
      'content-type': 'application/json',
      'Accept': 'application/json'
    }
}
export default {
  name: 'Groupbuying',
  data() {
    return {
      distswitch:false,
      dateswitch:false,
      groupBuyings: '',
      distancegroup:[],
      switched:true,
      switched2:true,
      mapdata: [],
      mydata: [],
      otherdata: [],
      distancedata2:[],
      frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
      inputKeyword:'',
      originalList:[],
      addresspoint: [],
    }
  },
  mounted(){
    this.onResize();
  },
  methods:{
     onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
    },
    deleteGroupbuying(data) {
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
          axios.post(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/delete` , {no:data})
            .then(response => {
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
    callwithaddress(){
      if(this.switched2 == true){
        let timerInterval
                  Swal.fire({
                    title: '가까운 순으로 정렬 중입니다.',
                    html: '정렬하기까지 <b></b> 초.',
                    timer: 500,
                    timerProgressBar: true,
                    onBeforeOpen: () => {
                      Swal.showLoading()
                      timerInterval = setInterval(() => {
                        const content = Swal.getContent()
                        if (content) {
                          const b = content.querySelector('b')
                          if (b) {
                            b.textContent = Swal.getTimerLeft()
                          }
                        }
                      }, 100)
                    },
                    onClose: () => {
                      clearInterval(timerInterval)
                    }
                  })
        axios({
                    url:`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/orderbyaddress/`+this.mydata[0][0]+`/`+this.mydata[0][1],
                    method:'post',
                    data: JSON.stringify(this.groupBuyings),
                    headers: config.headers})
                .then((response)=>{
                    this.groupBuyings = response.data
                })
                .catch((error)=>{
                    // console.log(error.response);
                })
        this.distswitch = true;
        this.dateswitch = false;
        this.switched2 = false;
        this.switched = true;
    }
    else{
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/read`)
        .then(response => {
          this.groupBuyings = response.data.reverse();
        })
        .catch(error => {
          // console.log(error.response)
        })
        this.distswitch = false;
        this.dateswitch = false;
        this.switched2 = true;
        this.switched = true;
      }
    },
    call(){
      if(this.switched == true){
        axios.get(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/orderbyenddate`)
        .then(response => {
          this.groupBuyings = response.data
        })
        .catch(error => {
          // console.log(error.response)
        })
        this.dateswitch = true;
        this.distswitch = false;
        this.switched = false;
        this.switched2 = true;
    }
    else{
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/read`)
        .then(response => {
          this.groupBuyings = response.data.reverse();
        })
        .catch(error => {
          // console.log(error.response)
        })
        this.dateswitch = false;
        this.distswitch = false;
        this.switched = true;
        this.switched2 = true;
      }
    },
    search(){
      if($('.searchBox').css('display')=='none'){
        $('.searchBox').css('display','unset');
      }else{
        if(document.getElementById("searchcontent").value != ""){
          $('.searchBox').css('display','none')
          document.getElementById("searchcontent").value = ""
        }else{
          $('.searchBox').css('display','none')
        }
      }
      this.inputKeyword = "";
    },
    searchKeyword(){
      var keyword = this.inputKeyword;
      this.groupBuyings = this.originalList;
      this.groupBuyings = this.groupBuyings.filter(function (item) {
          return item.food_kor.indexOf(keyword)!=-1;
        });

      if(this.groupBuyings.length == 0){
          Swal.fire({
            icon: 'error',
            title: '',
            text: '검색어와 일치하는 재료가 없습니다!',
            footer: ''
          })
      }
    }
  },
  created(){
    if(store.state.kakaoUserInfo.email != null){
      this.userinfo = store.state.kakaoUserInfo;
    }else{
      this.userinfo = store.state.userInfo;
    }
    // if(userinfo.email == )
    axios.get(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/read`)
      .then(response => {
        // // console.log(response)
        this.groupBuyings = response.data.reverse();
        this.originalList = this.groupBuyings;
        // // console.log(this.groupBuyings)
        for (var i = 0; i < this.groupBuyings.length; i++) {
          this.mapdata.push(this.groupBuyings[i].address)
        }
        // // console.log(this.userinfo.address)
        // // console.log(this.mapdata)
        const script = document.createElement('script');
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src = 'https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=93896045350a4c0fb6b7c93ae2527085&libraries=services';
        document.head.appendChild(script);
        var geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch(this.userinfo.address, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            this.mydata.push([result[0].y, result[0].x])
          }
        })
      })
      .catch(error => {
        // console.log(error)
      })
  }
}
</script>

<style scoped>
.v-sheet.v-toolbar:not(.v-sheet--outlined) {
  box-shadow: unset !important;
  -webkit-box-shadow: unset !important;
}
.writeButton{
  width: 60px;
  height: 60px;
  position: absolute;
  top: 82%;
  left: 80%;
  background-color: rgb(147 203 88);
  z-index: 90;
  border-radius: 30px;
  box-shadow: 7px 7px 10px rgb(0 0 0 / 44%);
  text-align: center;
  padding-top: 7px;;
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
.onegroupbuying:hover {
  box-shadow: 0px 0px 10px rgb(160, 212, 105);
}
.groupbuyingbtn {
  box-shadow: unset;
  -webkit-box-shadow: unset;
}
.groupbuyingbtn:hover {
  box-shadow: 0px 0px 10px gray;
}
</style>
<template>
  <div style="height: 100%; position: relative;">
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
        <v-btn text icon style="width:30x; height:30px; background-size:cover;">
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
          <v-toolbar color="rgba(202, 231, 171)" text height="48px">
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
    <v-card text>
      <v-container fluid style="padding: 0; margin: 0;" :style="{width:frameSize.x+'px'}">
        <div style="padding: 10px; padding-bottom: 50px; margin: 0; overflow: scroll;" :style="{height:(frameSize.y-146)+'px'}" grid-list-lg>
          <v-row dense style="padding: 0;">
            <v-col v-for="(info, i) in tradelist" :key="i" cols="12">
              <router-link :to="`/store/marketplace/${ info.no }`">
                <v-card class="onemarketplace" style="padding: 5px;">
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
                          <v-card-text class="pa-0" style="font-size: 11px;">{{ info.myfoodcount1 }}개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="require(`../../assets/images/food/${info.tradefood1}.png`)"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 11px;">{{ info.tradefoodcount1 }}개</v-card-text>
                        </v-col>
                      </v-row>
                      <v-row class="pa-0 ma-1" v-if="info.tradefood2!=null">
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="require(`../../assets/images/food/${info.myfood}.png`)"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 11px;">{{ info.myfoodcount2 }}개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="require(`../../assets/images/food/${info.tradefood2}.png`)"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 11px;">{{ info.tradefoodcount2 }}개</v-card-text>
                        </v-col>
                      </v-row>
                    </v-col>
                    <v-col cols="3" style="padding: 0; border-left: solid 1px lightgray">
                      <v-row class="pa-0 ma-1">
                        <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                          <v-card-text class="text-center pa-0" style="font-size: 15px; font-weight: bold;">구매</v-card-text>
                        </v-col>
                        <v-col v-if="!(info.price === '0')" cols="12" style="padding: 0; padding-bottom: 13px">
                          <v-card-text class="text-center pa-0" style="font-size: 10px;">{{ tradeunit[i] }}당</v-card-text>
                        </v-col>
                        <v-col cols="12" class="pa-1 text-center">
                          <span v-if="!(info.price === '0' || info.price === '300')" class="text-center pa-0" style="font-size: 18px; color: red;">{{ info.price }}</span>
                          <span v-if="!(info.price === '0' || info.price === '300')" class="text-center pa-0" style="font-size: 18px;">원</span>
                          <span v-if="info.price === '0' || info.price === '300'" class="text-center pa-0" style="font-size: 12px; color: red;">가격정보가 없습니다. 직접 문의해주세요</span>
                        </v-col>
                        <v-col cols="12" class=" text-center" v-if="userinfo.email === tradelist[i].email" style="padding: 0">
                          <router-link :to="{ name: 'ModifyMarketPlace', params: { pagenumber: info.no }}">
                            <v-btn @click="edit(info.no)" class="text-center mr-2 marketplacebtn" style="font-size: 11px; background-color: rgb(159 201 114); color: white;">수정</v-btn>
                          </router-link>
                          <router-link to="/store/marketplace">
                            <v-btn @click="del(info.no)" class="text-center marketplacebtn" style="font-size: 11px; background-color: red; color: white;">삭제</v-btn>
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
        <router-link to="/MyRef">
          <div class="writeButton">
            <v-icon color="white">mdi-lead-pencil</v-icon>
            <h4 style="color:whiste; font-size:14px;">글쓰기</h4>
          </div>
        </router-link>
  </div>
</template>

<script>
import $ from 'jquery'
import axios from 'axios'
import { mapState, mapMutations } from 'vuex'
import store from '../../vuex/store.js'
import Swal from 'sweetalert2'
import {foods} from '../../views/Food/Foods.js'
// const SERVER_URL = 'https://i3b301.p.ssafy.io:9999/food/api';
const SERVER_URL = store.state.SERVER_URL;

export default {
  data() {
    return {
      frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
      tradelist: [
      ],
      tradeunit: [],
      pagenumber: '',
      switched:true,
      userinfo:'',
      show:false,
      inputKeyword:'',
      originalList:[],
      myList:[],
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
    ...mapMutations(['setMapOtherUserInfo']),
    search(){
      if($('.searchBox').css('display')=='none'){
        $('.searchBox').css('display','unset');
      }else{
        if(document.getElementById("searchcontent").value != ""){
          axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/search/`+document.getElementById("searchcontent").value)
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
      this.inputKeyword = "";
    },
    call(){
      if(this.switched == true){
        // console.log(this.userinfo.email)
        axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/filter/`+this.userinfo.email)
        .then(response => {
          this.tradelist = response.data.list.reverse();
          this.myList = this.tradelist
          // // console.log(this.mapOtherUserInfo)
          
          this.mapOtherUserInfo.address.length = []
          if (this.mapOtherUserInfo.address.length === 0) {
            for (var k = 0; k < this.tradelist.length; k++) {
              this.mapOtherUserInfo.address.push(this.tradelist[k].address)
            }
          }
          // console.log(store.state.mapOtherUserInfo.address)
          this.originalList = this.tradelist;
          })
        .catch(error => {
          // console.log(error)
        })
        this.switched = false;
    }
    else{
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/trade/`)
        .then(response => {
          this.tradelist = response.data.list.reverse();
          this.myList = this.tradelist
          // // console.log(this.tradelist)
          this.mapOtherUserInfo.address.length = []
          if (this.mapOtherUserInfo.address.length === 0) {
            for (var k = 0; k < this.tradelist.length; k++) {
              this.mapOtherUserInfo.address.push(this.tradelist[k].address)
            }
          }
          // console.log(store.state.mapOtherUserInfo.address)
          // // // console.log(this.mapOtherUserInfo)
        })
        .catch(error => {
          // // console.log(error.response)
        })
        this.switched = true;
      }
    },
    edit(pageno) {
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/beforeupdate`, {no:pageno})
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
        // console.log(error.response)
      })
  }
})
    },
    searchKeyword(){
      var keyword = this.inputKeyword;
      // console.log(this.myList);
      if(this.myList.length == 0){
        this.tradelist = this.originalList;
        this.tradelist = this.tradelist.filter(function (item) {
            return item.myfood_kor.indexOf(keyword)!=-1;
          });
        if(this.tradelist.length == 0){
          Swal.fire({
            icon: 'error',
            title: '',
            text: '검색어와 일치하는 재료가 없습니다!',
            footer: ''
          })
        }
      }else{
        this.tradelist = this.myList;
        this.tradelist = this.tradelist.filter(function (item) {
            return item.myfood_kor.indexOf(keyword)!=-1;
          });
        // console.log(this.tradelist);
        if(this.tradelist.length == 0){
          if(this.tradelist.length == 0){
            Swal.fire({
              icon: 'error',
              title: '',
              text: '검색어와 일치하는 재료가 없습니다!',
              footer: ''
            })
          }
        }
      }
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
      // // console.log(this.tradelist)
      // // console.log(this.mapOtherUserInfo.address)
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/apitest`)
        .then(response => {
            this.xmldata = response.data;
            // console.log(this.xmldata.price)
            for (var i = 0; i < this.tradelist.length; i++) {
              for(var m = 0; m < this.xmldata.price.length; m++){
                for (var j = 0; j < foods.length; j++) {
                  if (this.tradelist[i].myfood === foods[j].name) {
                    this.tradelist[i].myfood = foods[j].img
                  }
                  if (this.tradelist[i].tradefood1 === foods[j].name) {
                    this.tradelist[i].tradefood1 = foods[j].img
                  }
                  if (this.tradelist[i].tradefood2 === foods[j].name) {
                    this.tradelist[i].tradefood2 = foods[j].img
                  }
                }
                var tF = this.xmldata.price[m];

                var tFname = tF.productName.split('/')[0];
                if(tF.product_cls_code == '01' ){
                  if (this.tradelist[i].price === '0' || this.tradelist[i].price === '300') {
                    if(tFname === this.tradelist[i].myfood_kor){
                      this.tradelist[i].price = tF.dpr1;
                      this.tradeunit.push(tF.unit)
                      // console.log(tF)
                      // // console.log(this.tradelist[i].price)
                    }
                  }
                }
              }
              if (this.tradelist[i].price === '0' || this.tradelist[i].price === '300') {
                this.tradeunit.push('1개')
              }
            }
            // console.log(this.tradeunit)
          }
        )
        if (this.mapOtherUserInfo.address.length === 0) {
          for (var k = 0; k < this.tradelist.length; k++) {
            store.state.mapOtherUserInfo.address.push(this.tradelist[k].address)
          }
        }
        this.originalList = this.tradelist.reverse();
        })
        .catch(error => {
          // // console.log(error.response)
        })
  },
  updated(){
    
  },
  computed: {
    ...mapState(['mapOtherUserInfo']),
  },
}
</script>

<style scoped>
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
  .writeButton{
  width: 60px;
  height: 60px;
  position: absolute;
  /* margin-top: 427px;
  margin-left: 280px; */
  top: 88%;
  left: 82%;
  background-color: rgb(147 203 88);
  z-index: 90;
  border-radius: 30px;
  box-shadow: 7px 7px 10px rgb(0 0 0 / 44%);
  text-align: center;
  padding-top: 7px;;
}
.onemarketplace:hover {
  box-shadow: 0px 0px 10px rgb(160, 212, 105);
}
.marketplacebtn {
  box-shadow: unset;
  -webkit-box-shadow: unset;
}
.marketplacebtn:hover {
  box-shadow: 0px 0px 10px gray;
}
</style>
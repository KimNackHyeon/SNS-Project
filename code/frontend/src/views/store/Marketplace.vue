<template>
  <div>
  <v-app>
    <v-card flat>
      <v-container fluid style="padding: 0; margin: 0;">
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <div style="border: solid 1px lightgrey">
            <router-link to="/Main">
            <v-btn icon style="margin: 5px">
              <v-icon>mdi-chevron-left</v-icon>
            </v-btn>
            </router-link>
          </div>
          
          <v-flex>
            <v-toolbar flat style="border: solid 1px lightgrey" height="48px">
              <div class="text-h6 mx-auto">
                우리 동네 장터
              </div>
                <router-link to="/store/marketmap"><v-btn  flat icon style="width:30x; height:30px; background-size:cover; ">
                <img v-show="$route.name=='MarketPlace'" id="mapIcon" style="margin-left:5px; margin-bottom: 8px;width:auto; height:35px;" src="../../assets/images/map.png">
          </v-btn></router-link>
            </v-toolbar>
          </v-flex>
        </v-layout>
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <v-flex>
            <div class="searchBox">
              <textarea placeholder="  검색하기  (ex '달걀')" style="resize:none; width:100%; height:100%;" id="searchcontent" value=""></textarea>
            </div>
            <v-toolbar color="rgba(160, 212, 105, 0.5)" flat height="48px">
              <v-switch @change="call" label="물물교환 가능 물품만 보기" style="margin-top:18px; margin-right: 18px;"></v-switch>
            </v-toolbar>
          </v-flex>
          <div @click="search" style="border: solid 1px lightgrey">
            <v-btn icon  style="margin: 5px">
              <v-icon>mdi-magnify</v-icon>
            </v-btn>
          </div>
      
        </v-layout>
        <div style="padding: 10px; margin: 0; overflow: scroll; height: 544px;" grid-list-lg>
          <v-row dense style="padding: 0;">
            <v-col v-for="(info, i) in tradelist" :key="i" cols="12">
              <router-link :to="`/store/marketplace/${ info.no }`">
                <v-card style="padding: 5px;">
                  <v-row style="padding: 0; margin: 0;">
                    <v-col cols="4" style="padding: 0; padding-right: 1px; border-right: solid 1px lightgray;">
                      <v-img height="105" width="105" padding="60" :src="info.imgUrl" style="border-radius: 5px;"></v-img>
                      <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">{{ info.myfood }}</v-card-text>
                      <v-card-text class="text-center" style="padding: 0; font-size: 9px;">{{ info.address }}</v-card-text>
                    </v-col>
                    <v-col cols="5" class="text-center" style="padding: 0;">
                      <v-row class="pa-0">
                        <v-card-text style="font-size: 20px; padding: 0; padding-bottom: 15px;">물물교환</v-card-text>
                      </v-row>
                      <v-row class="pa-0 ma-1">
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="info.myfood"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">1개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="info.tradefood1"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">{{ info.foodcount1 }}개</v-card-text>
                        </v-col>
                      </v-row>
                      <v-row class="pa-0 ma-1" v-if="info.tradefood2">
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="info.myfood"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">1개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" :src="info.tradefood2"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">{{ info.foodcount2 }}개</v-card-text>
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
  </v-app>
</div>
</template>

<script>
import $ from 'jquery'
import axios from 'axios'
import { mapState, mapMutations } from 'vuex'
import store from '../../vuex/store.js'

const SERVER_URL = 'http://localhost:9999/food/api';

export default {
  data() {
    return {
      tradelist: [
      ],
      switched:true,
      userinfo:'',
      show:false,
      market: [
        {
          id: 1,
          picture: "/img/egg.b0f12e35.png",
          food: '계란',
          address: '대전광역시 유성구 덕명동',
          exchange: [
            {
              my_icon: '/img/egg.4082cc2d.png',
              your_icon: '/img/onion.981ae9c7.png',
              my_cnt: '2',
              your_cnt: '1'
            },
            {
              my_icon: '/img/egg.4082cc2d.png',
              your_icon: '/img/potato.32622784.png',
              my_cnt: '3',
              your_cnt: '1'
            },
          ],
          price: '187.3'
        },
        {
          id: 2,
          picture: '/img/potato.717377a0.png',
          food: '감자',
          address: '대전광역시 유성구 덕명동',
          exchange: [
            {
              my_icon: '/img/potato.32622784.png',
              your_icon: 'data:image/png;base64,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',
              my_cnt: '2',
              your_cnt: '1'
            },
          ],
          price: '980.1'
        },
        {
          id: 3,
          picture: '/img/onion.c1585269.png',
          food: '양파',
          address: '대전광역시 유성구 봉명동',
          exchange: [
            {
              my_icon: '/img/onion.981ae9c7.png',
              your_icon: '/img/egg.4082cc2d.png',
              my_cnt: '1',
              your_cnt: '2'
            },
            {
              my_icon: '/img/onion.981ae9c7.png',
              your_icon: '/img/potato.32622784.png',
              my_cnt: '1',
              your_cnt: '1'
            },
          ],
          price: '495.0'
        },
        {
          id: 4,
          picture: '/img/egg.b0f12e35.png',
          food: '계란',
          address: '대전광역시 유성구 덕명동',
          exchange: [
            {
              my_icon: '/img/egg.4082cc2d.png',
              your_icon: '/img/vanilla.d3b9f946.png',
              my_cnt: '5',
              your_cnt: '1'
            },
          ],
          price: '187.3'
        },
      ]
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
          console.log(this.tradelist)
        })
        .catch(error => {
          console.log(error.response)
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
    axios.post(`${SERVER_URL}/trade/` , {email:this.userinfo.email})
        .then(response => {
          this.tradelist = response.data.list
          console.log(this.tradelist)
          // this.mapOtherUserInfo = store.state.mapOtherUserInfo
          // this.mapOtherUserInfo.address = this.tradelist[0].address
          // this.mapOtherUserInfo.food = this.tradelist[0].myfood
          // console.log(this.mapOtherUserInfo)
        })
        .catch(error => {
          console.log(error.response)
        })
        this.switched = false;
    }
    else{
      axios.get(`${SERVER_URL}/trade/`)
        .then(response => {
          this.tradelist = response.data.list
          console.log(this.tradelist)
          // this.mapOtherUserInfo = store.state.mapOtherUserInfo
          // this.mapOtherUserInfo.address = this.tradelist[0].address
          // this.mapOtherUserInfo.food = this.tradelist[0].myfood
          // console.log(this.mapOtherUserInfo)
        })
        .catch(error => {
          console.log(error.response)
        })
        this.switched = true;
    }
    }
  },
created() {
  if(store.state.kakaoUserInfo.email != null){
    this.userinfo = store.state.kakaoUserInfo;
  }else{
    this.userinfo = store.state.userInfo;
  }
  console.log(`${SERVER_URL}/trade/`)
  axios.get(`${SERVER_URL}/trade/`)
    .then(response => {
      this.tradelist = response.data.list
      console.log(this.tradelist)
      // console.log(this.mapOtherUserInfo.address)
      if (this.mapOtherUserInfo.address.length === 0) {
        for (var i = 0; i < this.tradelist.length; i++) {
          store.state.mapOtherUserInfo.address.push(this.tradelist[i].address)
          store.state.mapOtherUserInfo.food.push(this.tradelist[i].myfood)
        }
        // console.log(store.state.mapOtherUserInfo)
      }
    })
    .catch(error => {
      console.log(error.response)
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
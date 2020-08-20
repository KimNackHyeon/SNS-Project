<template>
  <v-layout wrap style="width:100%;height:50px; margin: 0px auto; max-height: 50px;">
    <div v-if="$route.name!='Error'" style="width:100%; height:50px;background-color:white; z-index:100">
      <v-btn icon @click.stop="drawer = !drawer" style="height: 100%">
        <v-icon size="30px">
          fas fa-list
        </v-icon>
      </v-btn>
      <v-btn icon :style="{'margin-left':((frameSize.x-212)/2)+'px','margin-right':((frameSize.x-212)/2)+'px'}" style="width:140px; height: 100%">
        <router-link to="/Main">
          <h3 style="font-weight:bold; color:rgb(160,212,105);">
            우리동네 냉장고
          </h3>
        </router-link>
      </v-btn>
      <router-link v-if="$route.name!='MyRef'" to="/MyRef">
        <v-btn icon style="width:30px; height:30px; background-size:cover; ">
          <img id="refIcon" style="width:auto; height:30px;" src="../assets/images/ref_close.png">
        </v-btn>
      </router-link> 
      <router-link v-if="$route.name=='MyRef'" to="/">
        <v-btn icon style="width:30px; height:30px; background-size:cover; ">
          <img id="refIcon" style="width:auto; height:30px;" src="../assets/images/ref_open.png">
        </v-btn>
      </router-link> 
    </div>
    
    <v-navigation-drawer
      v-model="drawer"
      temporary
      absolute
      width=320px
      style=" z-index: 300;"
    >
      <v-list class="pa-1" style="background:linear-gradient( to top, #a0d469, rgb(27, 187, 26)); width:100%; height:150px; position:relative;">
        <div  style="background-color:white; width:80%; height:170px; position:relative; border-radius: 8px; border:0px solid white; margin: 40px auto auto auto; box-shadow: #1e1e1e4a 2px 2px 9px;">
            <div style="width:100%; height:52%; padding:auto; text-align: center;">
              <div style="position:fixed; width: 25px; height: 25px; margin-left: 214px;
    margin-top: 10px;"><router-link to="/user/modifyuser"><v-icon size="24px;" color="rgb(0 0 0 / 22%)">mdi-cog</v-icon></router-link></div>
                <div class="profilePhotobox">
                    <!-- <img class="circlePhoto" :src="require(`../assets/images/profiles/${user.profilePhoto}`)"  /> -->
                    <img class="circlePhoto" :src="userinfo.profile_image_url" />
                </div>
                <router-link to="/user/mypage"><div style=" width:100px; height:100%; display: inline-table; overflow: hidden;">
                    <p style="font-size: 17px; color: black; font-weight: bold; margin: 0;">{{userinfo.nickname}} 님</p>
                    <h4>안녕하세요.</h4>
                </div></router-link>
            </div>
            <div  style="width:100%; height:48%;">
                <div class="grayLine" @click="chatlist" style="cursor:pointer; width:50%; height:100%; display: inline-block; text-align: center;">
                  <div class="iconBox">
                    <v-icon size="30px" color="rgb(160,212,105)" >mdi-comment-multiple-outline</v-icon>
                  </div>
                  <div class="subBox">
                    <h4>내 채팅방</h4>
                  </div>
                </div>
                <div class="grayLine" style="width:50%; height:100%; display: inline-block; text-align: center;">
                  <router-link to="/alarm">
                  <div class="iconBox"> 
                   <v-icon size="30px" color="rgb(160,212,105)">mdi-bell</v-icon>
                  </div>
                    <div class="subBox">
                      <h4>새 소식</h4>
                    </div>
                  </router-link>
                </div>
            </div>
        </div>
      </v-list>

      <v-list style="height:100px; width:100%;">
      </v-list>
      <router-link to="/store/marketplace"><div class="category" style="border-top:1px solid #8080802e;">
         <v-icon size="50px" color="#808080b5">mdi-basket</v-icon>
        <h2 class="categorysub">우리동네 장터</h2>
      </div></router-link>
      <router-link to="/store/groupbuying">
      <div class="category">
        <v-icon size="50px" color="#808080b5">mdi-cart</v-icon>
        <h2 class="categorysub">공동구매</h2>
      </div>
      </router-link>
      <router-link to="/feed/main">
      <div class="category">
        <v-icon size="50px" color="#808080b5">mdi-chef-hat</v-icon>
        <h2 class="categorysub">레시피 찾기</h2>
      </div>
      </router-link>

      <div :style="{'margin-top':(frameSize.y-532)+'px'}" style="background-color:rgba(224, 224, 224, 0.42); height:40px; position: unset;
     width:100%;"> <!-- 하단 -->
        <!-- <router-link to="/user/modifyuser"><v-icon style="margin:8px">mdi-cog</v-icon></router-link> -->
        <v-btn @click="$emit('logout')" icon style="float: right; width: 80px; margin: 3px;">로그아웃</v-btn>
      </div>
    </v-navigation-drawer>
  </v-layout>
</template>

<script>
const SERVER_URL = store.state.SERVER_URL;
import Vue from 'vue'
import Vuetify from 'vuetify'
import $ from 'jquery'
import Vuex from 'vuex'
 import store from '../vuex/store.js'
 import axios from "axios"

Vue.use(Vuex)

Vue.use(Vuetify, {
  iconfont: 'mdi' // 'md' || 'mdi' || 'fa' || 'fa4'
})
  export default {
    updated(){
      
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
        // console.log(this.userinfo);
      }
    },
    mounted(){
      this.onResize();
    },
    components:{ },
    data () {
      return {
        userinfo:'',
        drawer: null,
        frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
      }
    },
    methods:{
        onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
        },
        chatlist(){
          axios.get(`${SERVER_URL}/chatting/`+ this.userinfo.email)
        .then(response => {
          this.$router.push({ name: 'MyChatList', params: { chatlist: response.data}})
        })
        .catch(error => {
          // console.log(error)
        })
        },
        showSlides() {
            var slideIndex = 0;
            var i;
            var slides = document.getElementsByClassName("mySlides");
            var dots = document.getElementsByClassName("dot");
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";  
            }5
            slideIndex++;
            if (slideIndex > slides.length) {slideIndex = 1}    
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "block";  
            dots[slideIndex-1].className += " active";
        },
        isOpen(){
          
            return true;
          
        },
    },
  }
</script>

<style>

a h2{
  color: black;
}
a h3{
  color: black;
}
a h4{
  color: black;
}
.categorysub{
display: inline-block;
  vertical-align: center;
  margin: 10px;
}
.iconBox{
  margin-top: 15px;
}
.profilePhotobox{
    width: 60px;
    height: 60px; 
    border-radius: 70%;
    overflow: hidden;
    display: inline-table;
    margin-top: 15px;
}
.circlePhoto{
    width: 60px;
    height: 60px;
    -o-object-fit: cover;
    object-fit: cover;
}
.grayLine{
    border: 1px solid #8080802e;
}
.category{
  height:80px;
  width:100%;
  border-bottom: 1px solid #8080802e;
  padding: 16px 30px;
}

img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
}
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}
.active {
  background-color: #717171;
}
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}
</style>
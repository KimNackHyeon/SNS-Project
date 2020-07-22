<template>
  <v-layout wrap style="width:360px; height: 600px; margin:auto;">
    <div style="width:100%; height:60px;">
        <v-btn flat icon @click.stop="drawer = !drawer"><v-icon size="30px">fas fa-list</v-icon></v-btn>
        <v-btn flat icon style="margin:5px 73px; width:140px;"><h4 style="font-weight:bold; color:rgb(160,212,105);">우리동네 냉장고</h4></v-btn>
        <v-btn flat icon><v-icon size="30px">mdi-fridge</v-icon></v-btn>
    </div>
    
    <Main/>
    <v-navigation-drawer
      v-model="drawer"
      temporary
      absolute
      width=320px
    >
      <v-list class="pa-1" style="background:linear-gradient( to top, #a0d469, rgb(27, 187, 26)); width:100%; height:150px; position:relative;">
        <div  style="background-color:white; width:80%; height:170px; position:relative; border-radius: 8px; border:0px solid white; margin: 40px auto auto auto; box-shadow: #1e1e1e4a 2px 2px 9px;">
            <div style="width:100%; height:52%; padding:auto; text-align: center;">
                <div class="profilePhotobox">
                    <img class="circlePhoto" src="https://cdn.vuetifyjs.com/images/profiles/marcus.jpg" />
                </div>
                <div style=" width:100px; height:100%; display: inline-table; overflow: hidden;">
                    <h3>mamtte 님</h3>
                    <h4>안녕하세요.</h4>
                </div>
            </div>
            <div  style="width:100%; height:48%;">
                <div class="grayLine" style="width:50%; height:100%; display: inline-block; text-align: center;">
                  <div class="iconBox">
                    <v-badge color="red" dot><v-icon size="30px" color="rgb(160,212,105)" >mdi-comment-multiple-outline</v-icon></v-badge>
                  </div>
                  <div class="subBox">
                    <h4>내 채팅방</h4>
                  </div>
                </div>
                <div class="grayLine" style="width:50%; height:100%; display: inline-block; text-align: center;">
                  <div class="iconBox"> 
                   <v-badge color="red" dot> <v-icon size="30px" color="rgb(160,212,105)">mdi-bell</v-icon></v-badge>
                  </div>
                  <div class="subBox">
                    <h4>새 소식</h4>
                  </div>
                </div>
            </div>


        </div>
      </v-list>

      <v-list style="height:100px; width:100%;">
      </v-list>
      <div class="category" style="border-top:1px solid #8080802e;">
         <v-icon size="50px" color="#808080b5">mdi-basket</v-icon>
        <h2 class="categorysub">우리동네 장터</h2>
      </div>
      <div class="category">
        <v-icon size="50px" color="#808080b5">mdi-cart</v-icon>
        <h2 class="categorysub">공동구매</h2>
      </div>
      <div class="category">
        <v-icon size="50px" color="#808080b5">mdi-magnify</v-icon>
        <h2 class="categorysub">레시피 찾기</h2>
      </div>

      <div style="background-color:rgba(224, 224, 224, 0.42); height:40px; position:fixed; bottom:0; width:100%;"> <!-- 하단 -->
        <v-icon style="margin:8px">mdi-cog</v-icon>
        <v-btn flat icon style="float: right; width: 80px; margin: 3px;">로그아웃</v-btn>
      </div>
    </v-navigation-drawer>
  </v-layout>
</template>

<script>
import Vue from 'vue'
import Vuetify from 'vuetify'
import Main from './Main.vue'

Vue.use(Vuetify, {
  iconfont: 'mdi' // 'md' || 'mdi' || 'fa' || 'fa4'
})
  export default {
    components:{Main},
    data () {
      return {
        drawer: null,
      }
    },
    methods:{
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
        }
    }
  }
</script>

<style>

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
  
    width: 100%;
    height:100%;
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
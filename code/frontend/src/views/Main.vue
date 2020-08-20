<template>
<div style="width:100%; height:100%;">
  <div class="tutorialBox" :style="{width:frameSize.x+'px',height:frameSize.y+'px'}">
    <div class="confirmBox">
      <h1>튜토리얼을 진행하시겠습니까?</h1>
      <button @click="closetutorial" class="confirmbtn" style="background-color:red;">아니오</button>
      <router-link to="/tutorial">
        <button class="confirmbtn" style="background-color:rgb(160,212,105);">네</button>
      </router-link>
    </div>
    <div style="width: 100%; height: 116px; background-color: #000000a6; ">
      <img src="../assets/images/talkRef.gif" style="float:left;width: 118px;
    height: auto;
    margin-top: -77px;">
    <div class="typewriter">
      <h4 id="first">안녕하세요! </h4>
      <!-- <h4 id="second">우리동네 냉장고의 마스코트 냉장고입니다.</h4> -->
      <h4 id="second">튜토리얼하기를 눌러줘서 고마워요.</h4>
      <h4 id="third">튜토리얼을 진행하시겠어요?</h4>
    <!-- <h4>{{script}}</h4> -->
    <!-- <span class="image blinking"><v-icon color="white" size="30px">mdi-menu-down</v-icon>클릭하기 </span> -->
    </div>
    </div>
  </div>
  <div>
   <div @click="opentutorial" class="introduce" style="cursor:pointer">
      <!-- <div class="introduce"> -->
      <div class="introduceIMG" :style="{'background-position-x': -7+'px'}"></div>
      <div style="height:112px; float:right; text-align:right;
    font-size:25px; color:white; text-shadow: #80808075 1px 1px 1px;padding-top: 17px;
    padding-right: 5px;  text-overflow:hidden;" :style="{width:frameSize.x-230+'px'}">
        <h4>남는 식재료</h4>
        <h4>더이상 버리지 마세요</h4>
        <span class="blinking">튜토리얼 하러가기<v-icon color="white" size="15px">mdi-play</v-icon></span>
      </div>
    </div>
    <div style=" box-shadow: 0px 0px 10px #00000078 ;">
  <v-carousel style="margin:20px 0px; " :style="{height:frameSize.y-330+'px'}" hide-delimiters cycle vertical-delimiters="true" interval="2000" show-arrows-on-hover>
    <v-carousel-item  cycle 
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      hide-delimiters
      style="width:100%;height:100%;cursor:pointer;"
      @click="openLink(item.link)"
    ></v-carousel-item>
  </v-carousel>
  </div> 
  </div><!-- 광고판 끝 -->
  <div style="width:100%; height:200px; margin:1%;"> <!-- 메인 네비게이터-->
    <router-link to="/store/marketplace"><div class="navbox" style="border-left: 0px solid #8080803b;">
      <v-icon color="rgb(198 199 197)" size="21px" style="margin:5px;">mdi-basket-outline</v-icon>
      <h4>우리 동네</h4>
      <h4>장터</h4>
    </div></router-link>
    <router-link to="/store/groupbuying"><div class="navbox">
      <v-icon color="rgb(198 199 197)" size="21px" style="margin:5px;"> mdi-cart-outline</v-icon>
      <h4>공동</h4>
      <h4>구매</h4>
    </div>
    </router-link>
    <router-link to="/feed/main"><div class="navbox" >
      <v-icon color="rgb(198 199 197)" size="21px" style="margin:5px;">mdi-chef-hat</v-icon>
      <h4>레시피</h4>
      <h4>찾기</h4>
    </div>
    </router-link>
  </div>
  </div>
</template>

<script>
     import $ from 'jquery'

   export default {
    data () {
      return {
        frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
        script:'안녕하세요! 우리동네 냉장고의 마스코트 냉장고입니다.',
        ref_script2:'튜토리얼하기를 눌러줘서 고마워요.',
        ref_script3:'튜토리얼을 진행하시겠어요?',
        items: [
          // {
          //   src:'https://blogfiles.naver.net/MjAxNzA0MjRfMTcy/MDAxNDkzMDEzMjQ1MjEx.ovTdJhgqOsB86TVABuLhxdz98kfWOPNvq1eJNI32Qt4g.uqgB-iHZo_tZ4Mt67nRI5YVe3rb3bkYet9bFl4Yuczsg.JPEG.woorikangsan/1.jpg',
          //   link:'https://blog.naver.com/woorikangsan/220990801581'
          // },
          {
            src:'https://www.gompyo.net:444/upld/bd/2020/04/09/thmb_img_20200409173757_3787.jpg',
            link:'https://www.gompyo.net:444/mil_recipe_list.php'
          },
          {
            src: 'https://image.auction.co.kr/itemimage/1d/7e/cf/1d7ecf24d3.jpg',
            link:'https://itempage3.auction.co.kr/detailview.aspx?ItemNo=B443993038&listqs=catetab%3d4%26class%3dCorner.CategoryBest%26listorder%3d1&listtitle=%ba%a3%bd%ba%c6%ae100&frm2=through'
          },
          // {
          //   src: 'https://image7.coupangcdn.com/image/bannerunit/bannerunit_2243356c-de84-4983-aa65-695a41d83356.jpg',
          //   link: 'https:'
          // },
          {
            src:'https://image9.coupangcdn.com/image/displayitem/displayitem_8ea96a48-bb2e-412e-b9a9-97f5f6e95b5c.png',
            link:'https://www.coupang.com/np/promotion/77260' 
             }
        ],
      }
    },
    mounted(){
         this.onResize();
         $('.v-image').css('height','100%');
    },
    methods:{
       onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
    },
      openLink(link){
      // var newWindow = window.open("about:blank");
      window.location.href = link;
      },
      opentutorial(){
        $('.tutorialBox').css('display','unset'); 
      },
      closetutorial(){
        $('.tutorialBox').css('display','none'); 
      }
    }
  }
</script>


<style scoped>
body{
  overflow: hidden;
}
.tutorialBox{
  background-color: #00000085;
  position:fixed;
  z-index:1000;
  display: none;
}
.navbox{
    width: 33%;
    height: 110px;
    margin-top: 0;
    float: left;
    text-align: center;
    /* border-left: 1px solid #8080803b; */
    padding: 13px;
    box-shadow: 0px 0px 4px #7aa37e38;

}
h4{
  font-weight: 1000;
  font-size: 17px;
}
.introduce{
      margin:5px 0px;
      height:112px;
      width:100%;
      background-color:rgb(129 203 104); 
}
.introduceIMG{
    background-image: url(/img/login_backgroundimg.3ff816a3.gif);
    height: 112px;
    width: 225px;
    background-size: 242px 314px;

    background-position-y: -141px;
    float: left;
}
.v-image__image--cover {
    background-size: contain;
}
.confirmBox{
  width: 300px;
    height: 153px;
    background-color: white;
    border: 5px solid black;
    border-radius: 22px;
    -webkit-box-shadow: 1px 1px 30px black;
    box-shadow: 1px 1px 30px #00000052;
    margin:133px auto ;
    /* margin-top: 133px; */
}
.confirmbtn{
  width: 100px;
    height: 50px;
    float: left;
    border: 4px solid black;
    border-radius: 7px;
    margin: 6px 22px;
    color: white;
    text-shadow: 1px 1px 2px black;
    font-size: 25px;
    font-weight: 600;
    box-shadow: 2px 2px 1px black;
}
.typewriter{
  color:white;
  padding: 10px;
}
.typewriter h4[id="first"]{
     font-size: 100%;
     white-space: nowrap;
     overflow: hidden;
     -webkit-animation: typing 1s steps(26, end), blink-caret 1s step-end infinite;
     -webkit-animation-fill-mode:backwards;
     -moz-animation: typing 1s steps(26, end), blink-caret 1s step-end infinite;
}
.typewriter h4[id="second"]{
     font-size: 100%;
     white-space: nowrap;
     overflow: hidden;
     -webkit-animation: typing 1s steps(26, end), blink-caret 1s step-end infinite;
     -webkit-animation-delay: 1s;
     -webkit-animation-fill-mode:backwards;
     -moz-animation: typing 1s steps(26, end), blink-caret 1s step-end infinite;
     -moz-animation-delay: 1s;
}
.typewriter h4[id="third"]{
     font-size: 100%;
     white-space: nowrap;
     overflow: hidden;
     border-right: .15em solid black;
     -webkit-animation: typing 1s steps(26, end), blink-caret 1s step-end infinite;
     -webkit-animation-delay: 2s;
     -webkit-animation-fill-mode:backwards;
     -moz-animation: typing 1s steps(26, end), blink-caret 1s step-end infinite;
     -moz-animation-delay: 2s;
}
.typewriter h4[id="fourth"]{
     font-size: 100%;
     white-space: nowrap;
     overflow: hidden;
     border-right: .15em solid black;
     -webkit-animation: typing 5s steps(20, end);
     -webkit-animation-delay: 2s;
     /* -webkit-animation-fill-mode: both; */
     -moz-animation: typing 5s steps(20, end);
     -moz-animation-delay: 2s;
}
.confirmBox{
  -webkit-animation: show 1s steps(20, end);
  -webkit-animation-delay: 3s;
  -webkit-animation-fill-mode:backwards;
  -moz-animation: show 1s steps(20, end);
     -moz-animation-delay: 3s;
}
@keyframes typing {
     from {
       width: 0
     }
     to {
       width: 9em;
     }
}
@keyframes blink-caret {
     from, to {
       border-color: transparent
     }
     50% {
       border-color: #000;
     }
}
@keyframes show {
     from {
       opacity: 0;
     }
     to {
       opacity: 100%;
     }
}
.blinking{
  margin-right: -18px;
  font-size: 11px;
   -webkit-animation:blink 0.5s ease-in-out infinite alternate;
    -moz-animation:blink 0.5s ease-in-out infinite alternate;
    animation:blink 0.5s ease-in-out infinite alternate;
}
@-webkit-keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
@-moz-keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
@keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}

</style>
<template>
  <div style="width:100%; height:100%;">
      <router-link to="/tutorial"><div :style="{'margin-left':(frameSize.x-100)/2+'px'}" class="back"><h4 style="color:white; text-shadow:1px 1px 5px black">돌아가기</h4></div></router-link>
      <div @click="nextPage" class="tutorialPage">
          <img style="width:100%; height:100%;" :src="require(`../../assets/images/tutorial/3/${Page}.png`)">
      </div>
      <div @click="nextPage" style="height: 116px; background-color: #000000a6;z-index:30; position:fixed;" :style="{width:frameSize.x+'px'}">
            <img src="../../assets/images/talkRef.gif" style="float:left;width: 118px;
            height: auto;
            margin-top: -77px;">
            <div class="typewriter">
            <h4 id="first">{{script[Page-1].line1}}</h4>
            <!-- <h4 id="second">우리동네 냉장고의 마스코트 냉장고입니다.</h4> -->
            <h4 id="second">{{script[Page-1].line2}}</h4>
            <h4 id="third">{{script[Page-1].line3}}</h4>
            <!-- <h4>{{script}}</h4> -->
            <!-- <span class="image blinking"><v-icon color="white" size="30px">mdi-menu-down</v-icon>클릭하기 </span> -->
            </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
export default {
 data () {
      return {
        Page:1,
        frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
        script:[
            {line1:'여러 레시피를 볼 수 있는 레시피 찾기 페이지에요',line2:'아차!',line3:'잊은거 없나요?'},
            {line1:'내가 가진 재료로 만들 수 있는 레시피를 보려면',line2:'먼저 냉장고에 재료가 있어야해요.',line3:'그렇지 않으면 맞춤 레시피가 안 뜨니 주의해주세요!'},
            {line1:'다시 레시피 찾기 페이지로 넘어와서',line2:'상단 검색창 밑의 "나의 재료로 만들수 있는',line3:'레시피 보기" 토글 버튼을 눌러주세요!'},
            {line1:'짠! 이것들이',line2:'내가 가진 재료로 만들 수 있는 레시피 들이에요.',line3:'먼저 밑에 있는 파스타를 볼까요?'},
            {line1:'나에게 있는 재료로 만들 수 있어서',line2:'지금 당장이라도 만들수있을것같아요!',line3:''},
            {line1:'다음은 삼겹살 레시피에요.',line2:'이 레시피는 돼지고기 빼고 나에게 있는 재료에요.',line3:''},
            {line1:'그럴 땐 나에게 없는 재료를 클릭해서',line2:'공동구매나 물물교환을 통해서',line3:'바로 거래를 할 수 있도록 도와주고있어요!'},
            {line1:'레시피가 마음에 든다면',line2:'좋아요와',line3:'댓글도 달아주는것!잊지마세요~'},
        ],
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
     nextPage(){
       if(this.Page ==8){
         $('.back').css('display','block');
        }else{
          this.Page += 1;
        }
      //  console.log(this.Page)
     }
 }
}
</script>

<style>
.back{
    background-color: red;
    width: 100px;
    height: 60px;
    text-align: center;
    position: fixed;
    margin: auto;
    padding: 15px;
    border: 4px solid #8a1818;
    border-radius: 8px;
    margin-top: 200px;
        box-shadow: 0px 0px 141px 196px #00000042;
    display: none;
}
.tutorialPage{
    background-color: black;
    z-index: 1;
    margin: auto;
    width: 80%;
    height: 80%;
}
.typewriter{
  color:white;
  padding: 10px;
}
.tutorial h4{
    font-weight: 700;
    font-size: 16px;
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
@keyframes typing {
     from {
       width: 0
     }
     to {
       width: 9em;
     }
}
</style>
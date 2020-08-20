<template>
  <div class="root">
      <div class="tutorialBox" :style="{width:frameSize.x+'px'}"></div>
    <div class="selectTutorial" :style="{'margin-left':((frameSize.x-340)/2)+'px'}">
        <router-link to="/tutorial/1"><div id="Marketplace" class="tutorial"><h4>내 재료 다른 재료와</h4> <h4>교환하기</h4></div></router-link>
        <router-link to="/tutorial/2"><div id="GroupBuying"  class="tutorial"><h4>다른사람과</h4><h4>공동구매하기</h4></div></router-link>
        <router-link to="/tutorial/3"><div id="feed" class="tutorial"><h4>내가 가진 재료로</h4><h4>다양한 음식만들기</h4></div></router-link>
        <router-link to="/tutorial/sendmail"><div id="QnA" class="tutorial" style="color:gray"><h4>더 알고싶다면?</h4><h4 style="font-size:10px;">관리자에게 메일보내기</h4></div></router-link>
    </div>
    <router-link to="/Main"><div class="tutorial" style="background-color:red; font-color:white;width:100px; height:60px; " :style="{'margin-left':frameSize.x-110+'px'}"><h4 style="color:white; text-shadow:1px 1px 5px black">그만보기</h4></div></router-link>
    <div style="width: 100%; height: 116px; background-color: #000000a6; margin-top:419px;">
      <img src="../../assets/images/talkRef.gif" style="float:left;width: 118px;
    height: auto;
    margin-top: -77px;">
    <div class="typewriter">
      <h4 id="first">위의 것들 중</h4>
      <!-- <h4 id="second">우리동네 냉장고의 마스코트 냉장고입니다.</h4> -->
      <h4 id="second">알고싶은것을 클릭하세요!</h4>
      <!-- <h4 id="third">튜토리얼을 진행하시겠어요?</h4> -->
    <!-- <h4>{{script}}</h4> -->
    <!-- <span class="image blinking"><v-icon color="white" size="30px">mdi-menu-down</v-icon>클릭하기 </span> -->
    </div>
    </div>
  
  </div>
</template>

<script>
export default {
data () {
      return {
        frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
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
    }
}
</script>

<style scoped>
.root{
    width:100%; 
    height:100%; 
}
.tutorialBox{
   background-color: #00000085;
  position:fixed;
  z-index: 0;
  -webkit-animation: gonewidth 2s steps(20, end);
  -webkit-animation-delay: 0s;

}
.selectTutorial{
    width:340px;
    margin: auto;
    margin-top: 80px;
    width:auto;
    height:auto;
    -webkit-animation: show 1s steps(20, end);
  -webkit-animation-delay: 0s;
  -webkit-animation-fill-mode:backwards;
  -moz-animation: show 1s steps(20, end);
     -moz-animation-delay: 0s;
}
.tutorial{
    width: 150px;
    height: 89px;
    border: 5px solid #5f5f5f;
    -webkit-box-shadow: 1px 1px 1px black;
    box-shadow: 3px 4px 1px black;
    margin: 11px;
    border-radius: 11px;
    float: left;
    text-align: center;
    padding: 15px 0px;
    background-color: white;
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
@keyframes gone {
     from {
      background-color: #00000085;
     }
     to {
        background-color: transparent;
     }

}
@keyframes gonewidth {
     from {
      height: 100%;
      opacity: 100%;
     }
     to {
        height: 0;
        opacity: 0;
     }
}
</style>
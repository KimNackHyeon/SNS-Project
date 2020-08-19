<template>
  <div style="width:100%; height:100%;">
      <router-link to="/tutorial"><div :style="{'margin-left':(frameSize.x-100)/2+'px'}" class="back"><h4 style="color:white; text-shadow:1px 1px 5px black">돌아가기</h4></div></router-link>
      <div @click="nextPage" class="tutorialPage">
          <img style="width:100%; height:100%;" :src="require(`../../assets/images/tutorial/1/${Page}.png`)">
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
            {line1:'이게 우리 동네 장터 화면이에요!',line2:'글올리기를 하려면',line3:'내 냉장고로 가서 올려야해요!'},
            {line1:'내 냉장고화면이에요.',line2:'채우기버튼을 눌러',line3:'재료를 채워봐요!'},
            {line1:'어떤재료를 넣을지',line2:'넣을 재료 버튼을 클릭해볼까요?',line3:''},
            {line1:'많은 재료들 중',line2:'넣고싶은 음식을',line3:' 검색할수있어요'},
            {line1:'감자를 검색한후',line2:'클릭해봐요!',line3:''},
            {line1:'감자가',line2:' 들어간게 보이네요.',line3:''},
            {line1:'몇개를 넣을건지,',line2:'유통기한은 얼마나되는지 입력후',line3:'냉장고넣기 버튼을클릭하면'},
            {line1:'짠!',line2:'감자가 내 냉장고로',line3:'들어갔어요!'},
            {line1:'다른 재료들로도',line2:'냉장고를 꽉꽉채워주세요',line3:'^_^'},
            {line1:'공유할 음식을 클릭하면',line2:'이런 창이 떠요!',line3:'공유해볼까요?'},
            {line1:'내가 가진음식과 교환할 음식을',line2:'교환목록에 넣고 설명을 적은뒤',line3:'바구니에 넣어봅시다'},
            {line1:'바구니에 들어갔네요!',line2:'이제 공유하기 버튼을 눌러',line3:'바구니를 들고 장터로 가봐요!'},
            {line1:'짠!! 우리동네 장터에',line2:'내가 공유할 정보가 올라가있어요!',line3:'이제 채팅오는 사람과 교환을해봐요'},
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
        if(this.Page ==13){
             $('.back').css('display','block');
        }else{
             this.Page += 1;
        }
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
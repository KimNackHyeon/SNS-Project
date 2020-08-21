<template>
  <div style="width:100%; height:100%;">
      <router-link to="/tutorial"><div :style="{'margin-left':(frameSize.x-100)/2+'px'}" class="back"><h4 style="color:white; text-shadow:1px 1px 5px black">돌아가기</h4></div></router-link>
      <div @click="nextPage" class="tutorialPage">
          <img style="width:100%; height:100%;" :src="require(`../../assets/images/tutorial/2/${Page}.png`)">
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
            {line1:'이게 공동구매를 하는 화면이에요!',line2:'공구하고싶은 게시물을',line3:'클릭해보세요'},
            {line1:'클릭하면 보이는 상세페이지에요.',line2:'제품보러가기 버튼을 누르면',line3:'게시물 올린사람이 걸어놓은 링크로 갈수있어요!'},
            {line1:'흠...',line2:'이 제품을 공동구매 하려고 하는군요!',line3:''},
            {line1:'더 궁금한게 있다면',line2:'하단의 "문의하기"버튼을 누르세요!',line3:''},
            {line1:'글을 올린사람과',line2:'1대1 채팅을 할 수 있습니다.',line3:'궁금한것은 다 물어보세요^_^'},
            {line1:'제품이 마음에 든다면',line2:'하단의 "참가하기"버튼을 눌러',line3:'공동구매에 참가하세요!'},
            {line1:'공동구매에 참가가 되었습니다~',line2:'글을 올린사람은 이 버튼을 누르면',line3:'공동구매 참가자 리스트를 볼 수 있어요'},
            {line1:'참가자 명단에',line2:'인원수가 늘고',line3:'남은자리가 줄었네요~'},
            {line1:'다시누른다고 참여할수 없어요!',line2:'한 공동구매에는 한번만 참여 가능해요~',line3:''},
            {line1:'다음은 내가 공동구매를 하고 싶을 때',line2:'공동구매 글을 쓰는 법을 알아볼까요?',line3:'하단 글쓰기 버튼을 눌러보세요!'},
            {line1:'글쓰기 페이지에요',line2:'제목을 적어주시고',line3:'품목 적는곳을 클릭하면'},
            {line1:'원하는 음식을 검색하는 창이 떠요!',line2:'검색을 해서',line3:'공구를 원하는 음식을 클릭해봐요.'},
            {line1:'그런다음, 나머지 정보들을',line2:'빠짐없이 채워주세요~ 구매하는사람들을 위해',line3:'링크를 거는것도 잊지말아줘요!'},
            {line1:'등록하기를 누르면',line2:'이렇게 뜨면서',line3:'등록이 완료돼요!'},
            {line1:'이렇게 내 공구하기가 올라왔네요',line2:'문의 오는사람들의 질문에 대답하고',line3:'참가인원끼리 단톡을 만들어 공구진행해봐요!'},
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
       if(this.Page ==15){
         $('.back').css('display','block');
        }else{
          this.Page += 1;
        }
       // console.log(this.Page)
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
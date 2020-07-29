<template>
  <div>
    
    <div style="height:40px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/feed/main">
        <v-btn icon color="gray" style="background-color: #f1f3f5; border-radius: unset; height: 38px;">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
    </div>
    <div style="overflow: scroll; height: 540px; ">
      <div style="overflow: hidden; padding: 5px; border-bottom: 1px solid lightgray;">
        <div style="float: left;">
          <v-avatar size="35"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar>
          <h4 style="display: inline-block; padding-left: 10px">사용자 이름</h4>
        </div>
        <div style="float: right;">
          <v-btn icon color="lightgray">
            <v-icon size="30px">mdi-account-plus</v-icon>
          </v-btn>
        </div>
      </div>
      <!-- 재료 -->
      <div style="overflow: hidden; border-bottom: 1px solid lightgray">
        <!-- 나에게 있는 재료 -->
        <div style="float: left; padding: 5px 10px; width: 60%; text-align: center">
          <h5>나에게 있는 재료</h5>
          <div style="padding: 5px 10px; display: flex; overflow: scroll;">
            <div class="food" v-for="(food, i) in inFoods" :key="i">
              <img :src="require(`../../assets/images/food/${food.img}.png`)" alt="flour" style="width: 30px; height: 30px;">
              <h5 class="food-name">{{food.name}}</h5>
              <h6>{{food.amount}}</h6>
            </div>
          </div>
        </div>
        <!-- 나에게 없는 재료 -->
        <div style="float: right; background-color: #a0d469; padding: 5px 10px; width: 40%; text-align: center">
          <h5>나에게 없는 재료</h5>
          <div style="padding: 5px 10px; display: flex; overflow-x: scroll;" id="scrollBtns">
            <div class="food" v-for="(food, i) in outFoods" :key="i">
              <div>
                <v-btn icon @click="onBuyingBtn(food)">
                  <img :src="require(`../../assets/images/food/${food.img}.png`)" alt="flour" style="width: 30px; height: 30px;">
                </v-btn>
              </div>
              <h5 class="food-name">{{food.name}}</h5>
              <h6>{{food.amount}}</h6>
            </div>
              <div  class="balloon">
                <h5 style="color: white">{{nowFood}}</h5>
                <v-btn class="buybtn">공동<br>구매</v-btn>
                <v-btn class="changebtn">물물<br>교환</v-btn>
              </div>
          </div>
        </div>
      </div>
      <!-- 글 내용 -->
      <div style="">
        <div style="text-align: center; padding: 5px">
          <h2>레시피 제목</h2>
        </div>
        <div>
          <img src="../../assets/images/food1.jpg" alt="food" style="width: 100%; height: 300px;">
          <p style="padding-top: 10px">1. 실온 버터는 마요네즈 상태로 풀어준 뒤, 설탕과 소금을 넣고 크림화를 합니다.</p>
        </div>
      </div>
      <!-- 댓글 -->
      <div>
        <div class="commentDiv">
          <input v-model="comment" type="text" class="commentInput" placeholder="Comment">
          <v-btn icon class="sendBtn">
            <v-icon>mdi-send</v-icon>
          </v-btn>
        </div>
        <div class="comments" v-for="(comment, i) in comments" :key="i">
          <div class="userImg">
            <v-avatar size="35"><img :src="comment.img" alt="John"></v-avatar>
          </div>
          <div class="content">
            <div>
              <p class="commentUser">{{comment.name}}</p>
              <span>{{comment.content}}</span>
            </div>
            <span style="font-size: 12px">{{comment.created_at}}</span>
          </div>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  data() {
    return {
      nowFood : '',
      inFoods: [
        {img: "flour", name: "박력분", amount: "105g"},
      ],
      outFoods: [
        {img: "egg", name: "달걀", amount: "1개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
      ],
      onBuying: false,
      comment: "",
      comments: [
        {img: "https://cdn.vuetifyjs.com/images/john.jpg", 
        name: "Jhon34", 
        content: "아이들도 담백한 맛에 잘 먹을 것 같아요 한번 만들어보세요!! 감사합니다!",
        created_at: "2020-07-28 15:59"},
        {img: "https://cdn.vuetifyjs.com/images/john.jpg", 
        name: "Sara12", 
        content: "비주얼도 좋고 영양도 듬뿍~~ 다욧식품으로 좋을것 같아요",
        created_at: "2020-07-28 17:59"},
      ]
    }
  },
  watch: {
    comment: function(v) {
      if (this.comment.length > 0) {
        $('.sendBtn').css('color', '#a0d469')
      }
      else {
        $('.sendBtn').css('color', 'unset')
      }
    },
  },
  methods: {
    onBuyingBtn(food) {
      this.nowFood = food.name;
      // food.showBtn = !food.showBtn;
      if($('.balloon').css('display')=='block'){
        $('.balloon').css('display','none');  
      }else{
        $('.balloon').css('display','unset');
      }
    },
  }
}
</script>

<style scoped>
  .left-icon:hover {
    color: #a0d469;
  }
  .food {
    padding-right: 10px;
    text-align: center;
  }
  .food h6 {
    color: gray;
  }
  .food-name {
    font-weight: bold;
  }
  h5{
    font-size: unset;
    margin:unset;
    margin-left:unset;
    float:unset;
  }
  .buybtn {
    background-color: #4682b4!important;
    color: white;
    width: 45px !important;
    height: 45px !important;
    margin: 0 5px 5px 5px;
  }
  .changebtn {
    background-color: #ff6347 !important;
    color: white;
    width: 45px !important;
    height: 45px !important;
    margin: 0 5px 5px 5px;
  }
  .balloon {  
    position: fixed; 
    width: 120px; 
    background:rgba(44, 44, 44, 0.8);
    border-radius: 5px;
    margin-top: 40px;
    display: none;
  }
  .commentDiv {
    border: 1px solid lightgray;
    height: 40px;
  }
  .commentInput {
    height: inherit;
    width: 90%;
    border-right: 1px solid lightgray;
  }
  .sendBtn {
    width: 10%;
    height: inherit;
    color: black;
  }
  .comments {
    padding: 10px 10px 0 10px;
    overflow: hidden;
  }
  .userImg {
    float: left;
    margin-right: 10px;
    width: 10%;
  }
  .content {
    float: left;
    width: 85%;
  }
  .commentUser {
    margin-bottom: 0 !important;
    font-weight: bold; 
    margin-right: 5px;
  }
</style>
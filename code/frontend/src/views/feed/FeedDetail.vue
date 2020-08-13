<template>
  <div style="width:100%; height:100%;">
    <div style="width:100%; height:40px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/feed/main">
        <v-btn icon color="gray" style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div class="titleBox">
        <div class="pageTitle">
          <p style="margin: 0; text-overflow: ellipsis; overflow: hidden;">레시피보기</p>
        </div>
      </div>
    </div>
    <div style="text-align: center; padding: 5px 10px; border-bottom: 1px solid lightgray;">
          <h2 style="   font-weight: 500; font-size: 22px; text-overflow: ellipsis; overflow: hidden;">{{feedData.title}}</h2>
        </div>
    <div style="overflow: scroll; ">
      <div style="overflow: hidden; padding: 5px; border-bottom: 1px solid lightgray;">
        <div style="float: left;">
          <v-avatar size="35"><img :src="feedData.profile" alt="John"></v-avatar>
          <h4 style="display: inline-block; padding-left: 10px">{{feedData.nickname}}</h4>
        </div>
        <div style="float: right; height: 35px; line-height: 35px">
          <!-- <v-btn icon color="lightgray">
            <v-icon size="30px">mdi-account-plus</v-icon>
          </v-btn> -->
          <v-btn icon @click="likedbtn" style="width: 25px; height: 25px; margin: 5px">
              <v-icon v-if="!feedData.isLike" size="25px" color="black">mdi-heart-outline</v-icon>
              <v-icon v-if="feedData.isLike" size="25px" color="red">mdi-heart</v-icon>
          </v-btn>
          <v-btn icon @click="scrapedbtn" style="width: 25px; height: 25px">
              <v-icon v-if="!feedData.isScrap" size="25px" color="black">mdi-bookmark-outline</v-icon>
              <v-icon v-if="feedData.isScrap" size="25px" color="#a0d469">mdi-bookmark</v-icon>
          </v-btn>
          <!-- 수정 삭제 -->
          <v-menu botoom offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-bind="attrs" v-on="on" icon style="width: 25px; height: 25px">
                <v-icon color="black" size="25px">mdi-dots-vertical</v-icon>
              </v-btn>
            </template>
            <v-list>
              <!-- <router-link to="/"> -->
                <v-list-item @mouseover="overModifyBtn" @mouseout="outModifyBtn"  style="min-height: unset; height: 30px">
                  <v-list-item-title class="modifybtn">수정</v-list-item-title>
                </v-list-item>
              <!-- </router-link> -->
              <button>
                <v-list-item @mouseover="overDeleteBtn" @mouseout="outDeleteBtn" style="min-height: unset; height: 30px">
                  <v-list-item-title class="deletebtn">삭제</v-list-item-title>
                </v-list-item>
              </button>
            </v-list>
          </v-menu>
        </div>
      </div>
      
      <!-- 재료 -->
      <div style="overflow: hidden; border-bottom: 1px solid lightgray;margin-bottom:20px;    height: 110px;">
        <!-- 나에게 있는 재료 -->
        <div style="float: left; padding: 5px 10px; width: 60%; text-align: center;    height: 110px;">
          <h5>나에게 있는 재료</h5>
          <div style="padding: 5px 10px; display: flex; overflow: scroll;">
            <div class="food" v-for="(food, i) in havingFood" :key="i">
              <img :src="require(`../../assets/images/food/${food.img}.png`)" alt="flour" style="width: 30px; height: 30px;">
              <h5 class="food-name">{{food.name_kor}}</h5>
              <h6>{{food.amount}}</h6>
            </div>
          </div>
        </div>
        <!-- 나에게 없는 재료 -->
        <div style="float: right; background-color: rgb(202, 231, 171); padding: 5px 10px; width: 40%; text-align: center;    height: 110px;">
          <h5>나에게 없는 재료</h5>
          <div style="padding: 5px 10px; display: flex; overflow-x: scroll;" id="scrollBtns">
            <div class="food" v-for="(food, i) in otherFood" :key="i">
              <div>
                <v-btn icon @click="onBuyingBtn(food)">
                  <img :src="`/img/food/${food.img}`" alt="flour" style="width: 30px; height: 30px;">
                  <!-- <img :src="require(`../../assets/images/food/${food.img}.png`)" alt="flour" style="width: 30px; height: 30px;"> -->
                </v-btn>
              </div>
              <h5 class="food-name">{{food.name_kor}}</h5>
              <h6>{{food.amount}}개</h6>
            </div>
            <div  class="balloon">
              <h5 style="color: white">{{nowFood}}</h5>
              <router-link to="/store/groupbuying"><v-btn class="buybtn">공동<br>구매</v-btn></router-link>
              <router-link to="/store/marketplace"><v-btn class="changebtn">물물<br>교환</v-btn></router-link>
            </div>
          </div>
        </div>
      </div>
      <!-- 글 내용 -->
      <div style="">
        <div>
          <div v-for="(item,i) in feedData.items" :key="i" style="margin-bottom:10px;">
            <div>
              <img :src="require(`../../assets/images${item.img}`)" style="width:360px; height:auto;">
            </div>
            <div>
              {{item.content}}
            </div>
          </div>
          </div>
      </div>
      <!-- 해시태그 -->
      <div class="hashBox">
        <div v-for="(hashTag, i) in feedData.hashTags" :key="i" style="display: inline-block">
          <div class="hash">#{{ hashTag }}</div>
        </div>
      </div>
      <!-- 댓글 -->
      <div>
        <div class="commentDiv">
          <input v-model="comment" @keyup.enter="onCommentBtn" class="commentInput" placeholder="Comment">
          <v-btn icon class="sendBtn" @click="onCommentBtn">
            <v-icon>mdi-send</v-icon>
          </v-btn>
        </div>
        <div class="comments" v-for="(comment, i) in feedData.comments" :key="i">
          <div class="userImg">
            <v-avatar size="35"><img :src="comment.img" alt="John"></v-avatar>
          </div>
          <div class="content">
            <div>
              <p class="commentUser" style="margin: 0;">{{comment.nickname}}</p>
              <p style="margin: 0 5px 0 0; display: inline-block;">{{comment.comment}}</p>
              <p style="margin: 0; display: inline-block; font-size: 12px">{{comment.create_date}}</p>
            </div>
          </div>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import axios from "axios"
import store from '../../vuex/store.js'

const SERVER_URL = store.state.SERVER_URL;

export default {
  data() {
    return {
      userinfo:'',
      nowFood : '',
      feedData: '',
      myrefFood:[],
      havingFood:[],
      otherFood:[],
      inFoods: [
      ],
      outFoods: [
        {img: "egg", name: "달걀", amount: "1개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개"},
      ],
      // onBuying: false,
      comment: "",
      hashTags: [
        '빵', 
        '베이커리',
        '인생빵',
        'JMT',
        '성심당',
        '대전빵집',
        '부추빵',
        '꿀키',
        '소보로'
      ],
      offset: true,
    }
  },
  watch: {
    comment: function(v) {
      if (this.comment.length > 0) {
        $('.sendBtn').css('color', '#a0d469')
      }
      else {
        $('.sendBtn').css('color', 'rgba(0, 0, 0, 0.54)')
      }
    },
  },

  created(){
     if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
        console.log(this.userinfo);
      }else{
        this.userinfo = store.state.userInfo;
      }
      axios.get(`${SERVER_URL}/myref/search/`+this.userinfo.email)
    //   axios.get('http://localhost:9999/food/api/myref/search/'+this.userinfo.email)
        .then(response => {
        //   this.myreflist = response.data.myreflist
          this.myrefFood = response.data.myreflist
        })
        .catch(error => {
          console.log(error.response)
        });
    
    var feedNo = this.$route.params.feedNo;
    axios.get(`${SERVER_URL}/feed/search`,{params:{feedNo:feedNo}}) // 피드 가져오기
        .then(response => {
          console.log(response);
            this.feedData = { // 하나의 피드 데이터
              no: response.data.feeddata.no,
              nickname : response.data.feeddata.nickname,
              profile : response.data.feeddata.profile,
              title: response.data.feeddata.title,
              items: [],
              comments:[],
              hashTags:[],
              foods:[],
              isLike : '',
              isScrap : '',
            }
            response.data.taglist.forEach(tag =>{
              this.feedData.hashTags.push(tag.tagName);
            });

            response.data.foodlist.forEach(food =>{
              this.feedData.foods.push(food);
            });

            response.data.datalist.forEach(d =>{
              this.feedData.items.push(d);
            });

            response.data.datalist.forEach(d =>{
              this.feedData.items.push(d);
            });
          
          var myrefFoodName = [];
          var feedFoodlist = this.feedData.foods;
          this.myrefFood.forEach(reffood=>{
            myrefFoodName.push(reffood.name_kor);
          });
          console.log(this.myrefFood);
          feedFoodlist.forEach(recipefood => {
          // console.log(recipefood.name);
          if(myrefFoodName.includes(recipefood.name_kor)){
            this.havingFood.push(recipefood);
            // console.log(recipefood.name);
          }else{
            this.otherFood.push(recipefood);
          }
            });
        // console.log(this.feedData.foods);
        })
        .catch((error) => {
          console.log(error.response);
        });
        setTimeout(() => {
          axios.get(`${SERVER_URL}/feed/searchComment`,{params:{feedNo : feedNo}}) // 피드에 해당하는 댓글 불러오기
              .then(response => {
                // console.log(response);
                response.data.forEach(c =>{
                  var comment = { // 피드에 해당하는 하나의 댓글
                    img : '',
                    nickname : c.nickname,
                    content : c.comment,
                    created_at : c.create_date,
                  }
                  this.feedData.comments.push(c);
                })
          });
        }, 500);
        
  },
  methods: {
    likedbtn() {
      this.feedData.isLike = !this.feedData.isLike;
      axios.get(`${SERVER_URL}/feed/like`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : this.feedData.no,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          console.log(error.response);
      });
    },
    scrapedbtn() {
      this.feedData.isScrap = !this.feedData.isScrap;
      axios.get(`${SERVER_URL}/feed/scrap`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : this.feedData.no,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          console.log(error.response);
      });
    },
    overModifyBtn() {
      $('.modifybtn').css('color', '#a0d469')
    },
    outModifyBtn() {
      $('.modifybtn').css('color', 'unset')
    },
     overDeleteBtn() {
      $('.deletebtn').css('color', '#a0d469')
    },
    outDeleteBtn() {
      $('.deletebtn').css('color', 'unset')
    },
    onBuyingBtn(food) {
      // food.showBtn = !food.showBtn;
      if($('.balloon').css('display')=='block' && this.nowFood ==food.name){
        $('.balloon').css('display','none');  
      }else{
        $('.balloon').css('display','unset');
      }
      this.nowFood = food.name;
    },
    onCommentBtn() {
      // 댓글 추가기능
      var comment = {
        img: this.userinfo.profile_image_url, 
        nickname: this.userinfo.nickname,
        email:this.userinfo.email,
        feedNo: this.$route.params.feedNo,
        comment: this.comment
      }
      console.log(comment);
      axios.post(`${SERVER_URL}/feed/register`,comment)
      .then(response=>{
        console.log(response);
        this.comment = "";
      })

      this.feedData.comments.push(comment);
    },
  },
}
</script>

<style scoped>
  .left-icon:hover {
    color: #a0d469;
  }
  .titleBox {
    display: inline-block;
    width: 90%;
    height: 100%;
    font-size: 17px;
    text-align: center;
  }
  .pageTitle {
    height: 100%;
    width:100%;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0px 10px;
    
  }
  .v-btn {
    background-color: unset;
    -webkit-box-shadow: unset;
    box-shadow: unset;
    color: unset;
  }
  .hashBox {
    overflow-x: scroll;
    white-space: nowrap;
    width: 100%;
    height: 45px;
    border-top: 1px solid lightgray;
    background-color: #80808021;
    padding: 3px 0px;
    overflow-y: hidden;
  }
  .hash {
    float: left; 
    margin: 5px; 
    background: #a0d469; 
    color: white; 
    padding: 5px; 
    border-radius: 12px; 
    font-size: 12px
  }
  .food {
    padding-right: 10px;
    text-align: center;
  }
  .food h6 {
    color: gray;
    font-size: 12px;
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
    margin-bottom: 5px;
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
    padding: 5px 10px 5px 10px;
    width:100%;
    height: 60px;
    overflow: hidden;
    line-height: 60px;
  }
  .userImg {
    float: left;
    width: 10%;
  }
  .content {
    float: left;
    width: 85%;
    height: 60px;
  }
  .commentUser {
    /* margin-bottom: 0 !important; */
    font-weight: bold; 
    margin-right: 5px;
  }
</style>
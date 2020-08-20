<template>
  <div style="width:100%; height:100%;">
    <div :style="{height: (frameSize.y*0.15)+'px'}">
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
      <div style="overflow: hidden; padding: 5px; border-bottom: 1px solid lightgray;">
        <div style="float: left;">
          <v-avatar size="35" style="cursor : pointer;"><img :src="feedData.profile" @click="moveUser(feedData.email)"></v-avatar>
          <h4 style="display: inline-block; padding-left: 10px; cursor : pointer;" @click="moveUser(feedData.email)">{{feedData.nickname}}</h4>
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
          <div style="display: inline-block" v-if="feedData.email==userinfo.email">
            <v-btn @click="openBtn" icon style="width: 25px; height: 25px">
              <v-icon color="black" size="25px" style="">mdi-dots-vertical</v-icon>
            </v-btn>
            <div class="btns">
              <router-link :to="{ name: 'ModifyRecipe', params: { feedNo : feedData.no }}"><v-btn class="btn">수정</v-btn></router-link>
              <v-btn class="btn" @click="deleteNo(feedData.no)">삭제</v-btn>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div :style="{height: (frameSize.y*0.75)+'px'}" style="overflow: scroll; position: relative; padding-bottom: 20px;">
      <!-- 재료 -->
      <div style="overflow: hidden; border-bottom: 1px solid lightgray; height: 130px;">
        <!-- 나에게 있는 재료 -->
        <div style="float: left; padding: 5px 10px; width: 60%; text-align: center; height: 130px;">
          <h5>나에게 있는 재료</h5>
          <div style="padding: 5px 10px; display: flex; overflow-x: scroll; height: 100px">
            <div class="food" v-for="(food, i) in havingFood" :key="i">
              <img :src="require(`../../assets/images/food/${food.img}.png`)" style="width: 30px; height: 30px;">
              <h5 class="food-name">{{food.name_kor}}</h5>
              <h6>{{food.amount}}개</h6>
            </div>
          </div>
        </div>
        <!-- 나에게 없는 재료 -->
        <div style="float: right; background-color: rgb(202, 231, 171); padding: 5px 10px; width: 40%; text-align: center; height: 130px;">
          <h5>나에게 없는 재료</h5>
          <div style="padding: 5px 10px; display: flex; overflow-x: scroll; height: 100px" id="scrollBtns">
            <div class="food" v-for="(food, i) in otherFood" :key="i">
              <div>
                <v-btn icon @click="onBuyingBtn(food)">
                  <img :src="`/img/food/${food.img}.png`" alt="flour" style="width: 30px; height: 30px;">
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
      <!-- 제목 -->
      <div style="text-align: center; padding: 5px 10px; border-bottom: 1px solid lightgray;">
        <h3 style="font-weight: 500;text-overflow: ellipsis; overflow: hidden;">{{feedData.title}}</h3>
      </div>

      <!-- 글 내용 -->
      <div style="">
        <div>
          <div v-for="(item,i) in feedData.items" :key="i" style="margin-bottom:10px;">
            <div>
              <img :src="item.img" style="width:100%; height:auto;">
              <!-- <img :src="require(`../../assets/images${item.img}`)" style="width:360px; height:auto;"> -->
              
            </div>
            <div class="feedContents" v-html="item.content" style="margin: 10px 0; font-size: 16px;">
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
            <v-avatar size="35" style="cursor : pointer;"><img :src="comment.img" alt="John" @click="moveUser(comment.email)"></v-avatar>
          </div>
          <div class="content" style="display: table; padding-left: 15px;">
            <div style="display: table-cell; vertical-align: middle;">
              <p class="commentUser" style="margin: 0; cursor : pointer;" @click="moveUser(feedData.email)">{{comment.nickname}}</p>
              <!-- <p style="margin: 0 5px 0 0;">댓글</p> -->
              <p style="margin: 0 5px 0 0;">{{comment.comment}}</p>
              <p style="margin: 0; font-size: 12px">{{comment.create_date}}</p>
            </div>
          </div>
          <div style="float: right; width: 10%" v-if="comment.email==userinfo.email">
            <v-btn icon color="black" @click="deleteComment(feedData.no, comment)">
              <v-icon size="18px">mdi-trash-can-outline</v-icon>
            </v-btn>
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
import Swal from 'sweetalert2'

const SERVER_URL = store.state.SERVER_URL;

export default {
  data() {
    return {
      frameSize: {
        x: window.innerHeight * 0.5625,
        y: window.innerHeight,
        per: 1,
      },
      userinfo:'',
      nowFood : '',
      feedData: '',
      myrefFood:[],
      havingFood:[],
      otherFood:[],   
      // onBuying: false,
      comment: "",
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
  mounted() {
    this.onResize();
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
  },
  created(){
     if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
        // console.log(this.userinfo);
      }else{
        this.userinfo = store.state.userInfo;
      }
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/myref/search/`+this.userinfo.email)
    //   axios.get('https://i3b301.p.ssafy.io:9999/food/api/myref/search/'+this.userinfo.email)
        .then(response => {
        //   this.myreflist = response.data.myreflist
          this.myrefFood = response.data.myreflist
        })
        .catch(error => {
          // console.log(error.response)
        });
    
    var feedNo = this.$route.params.feedNo;
    axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/search`,{params:{feedNo:feedNo}}) // 피드 가져오기
        .then(response => {
          // console.log(response);
            this.feedData = { // 하나의 피드 데이터
              no: response.data.feeddata.no,
              nickname : response.data.feeddata.nickname,
              profile : response.data.feeddata.profile,
              title: response.data.feeddata.title,
              email: response.data.feeddata.email,
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

            axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/check`,{
              params:
              {
                email:store.state.userInfo.email,
                feedNo : response.data.feeddata.no,
              }
              })
            .then(response =>{
              // console.log(response);
                this.feedData.isLike = response.data.like;
                this.feedData.isScrap = response.data.scrap;
            });
          
          var myrefFoodName = [];
          var feedFoodlist = this.feedData.foods;
          this.myrefFood.forEach(reffood=>{
            myrefFoodName.push(reffood.name_kor);
          });
          // console.log(this.myrefFood);
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
          // console.log(error.response);
        });
        setTimeout(() => {
          axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/searchComment`,{params:{feedNo : feedNo}}) // 피드에 해당하는 댓글 불러오기
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
    onResize() {
      if (window.innerHeight * 0.5625 <= window.innerWidth) {
        this.frameSize = {
          x: window.innerHeight * 0.5625,
          y: window.innerHeight,
          per: innerHeight / 640,
        };
      } else {
        this.frameSize = {
          x: window.innerWidth,
          y: window.innerWidth * 1.77,
          per: innerWidth / 360,
        };
      }
    },
    likedbtn() {
      this.feedData.isLike = !this.feedData.isLike;
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/like`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : this.feedData.no,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          // console.log(error.response);
      });
    },
    scrapedbtn() {
      this.feedData.isScrap = !this.feedData.isScrap;
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/scrap`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : this.feedData.no,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          // console.log(error.response);
      });
    },
    openBtn() {
      if($('.btns').css('display')=='block'){
        $('.btns').css('display','none');  
      }else{
        $('.btns').css('display','block');
      }
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
      let today = new Date()
      var comment = {
        img: this.userinfo.profile_image_url, 
        nickname: this.userinfo.nickname,
        email:this.userinfo.email,
        feedNo: this.$route.params.feedNo,
        comment: this.comment,
        // create_date: `${today.getFullYear()}-${today.getMonth()}-${today.getDate()}T${today.getHours()}:${today.getMinutes()}:${today.getSeconds()}`
        create_date: `${today.toISOString().substring(0, 10)}T${today.getHours()}:${today.getMinutes()}:${today.getSeconds()}`
      }
      // console.log(comment);
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/feed/register`,comment)
      .then(response=>{
        // console.log(response);
        this.comment = "";
      })

      this.feedData.comments.push(comment);
    },
    // 댓글 삭제하기
    deleteComment(feedData_id, comment) {
      var comm = this.feedData.comments[this.feedData.comments.indexOf(comment)];
      this.feedData.comments.splice(this.feedData.comments.indexOf(comment), 1);
      // console.log(comm);

      axios.delete(`https://i3b301.p.ssafy.io:9999/food/api/feed/comment`,{params:{no : comm.no}})
      .then(response =>{

      })
      
    },
    moveUser(user_email){
      if(user_email == store.state.userInfo.email){
        this.$router.push({name: 'Mypage'});
      }else{
        // console.log(user_email)
        this.$router.push({name: 'Yourpage', params: {email : user_email}});
      }
    },
    deleteNo(feed_no){
      Swal.fire({
        title: '정말 삭제하시겠습니까?',
        text: "되돌릴 수 없습니다!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '네 삭제할게요!'
      }).then((result) => {
        if (result.value) {
          axios.delete(`https://i3b301.p.ssafy.io:9999/food/api/feed/delete`,{params:{feedNo : feed_no}})
          .then(response => {
            Swal.fire({
                // position: 'top-end',
                icon: 'success',
                title: '삭제가 완료되었습니다.',
                showConfirmButton: false,
                timer: 1500
            })
            // window.location.reload();
            this.$router.push('/feed/main');
          })
        }
      })
    }
  },
}
</script>

<style scoped>
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
  .btns {
    display: none;
    position: absolute;
    width: 80px;
    /* height: 60px; */
    z-index: 99;
    left: 76%;
    top: 9%;
    border: 1px solid lightgray;
    border-radius: 4px;
    background-color: white;
  }
  .btn {
    /* width: 100%; */
    background-color: unset !important;
    border-radius: unset;
    box-shadow: unset;
    -webkit-box-shadow: unset;
    height: 100%;
    color: rgba(0, 0, 0, 0.87);
  }
  .btn span {
    height: 30px;
  }
  .hashBox {
    overflow-x: scroll;
    white-space: nowrap;
    width: 100%;
    height: 55px;
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
    padding-right: 15px;
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
    position: absolute; 
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
    width: 80%;
    height: 60px;
  }
  .commentUser {
    /* margin-bottom: 0 !important; */
    font-weight: bold; 
    margin-right: 5px;
  }

  .feedContents{
    text-align: center;
  }
</style>
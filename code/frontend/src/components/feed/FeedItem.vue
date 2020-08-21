<template>
  <div class="feed-item">
    <v-toolbar color="rgba(202, 231, 171)" text height="36px">
      <v-switch @change="call" label="나의 재료로 만들 수 있는 레시피 보기" style="margin-top:20px; margin-right: 10px;"></v-switch>
    </v-toolbar>
    <div v-for="(feedData, i) in feedDatas" :key="i" style="position:relative">
      <div class="feed-profil"  >
        <div class="feed-user">
          <v-avatar size="35" style="cursor : pointer;"><img :src="feedData.profile" alt="John" @click="moveUser(feedData.email)"></v-avatar>
          <h4 @click="moveUser(feedData.email)" style="display:inline-block; padding-left:10px; cursor:pointer;">{{feedData.nickname}}</h4>
        </div>
        <div style="height: 45px; float: right; width: 10%;">
          <router-link :to="{ name: 'FeedDetail', params: { feedNo : feedData.no }}">
            <v-btn icon color="gray" style="background-color: #f1f3f5; border-radius: unset; height: 45px; width: 100%">
            <!-- <v-btn icon color="gray" style="background-color: #f1f3f5; border-radius: unset; height: 45px; width: 100% border-right: 1px solid lightgray"> -->
              <v-icon class="feed-right-icon" size="35px">mdi-chevron-right</v-icon>
            </v-btn>
          </router-link>
        </div>
      </div>
      <div  style=" width:inherit">
        <v-carousel v-model="feedData.pictureNum">
        <!-- <v-carousel show-arrows-on-hover> -->
          <v-carousel-item
            v-for="(item, i) in feedData.items"
            :key="i"
            :src="item.image"
          ></v-carousel-item>
            <!-- :src="require(`../../assets/images${item.image}`)" -->
        </v-carousel>
        <v-chip
            class="ma-2 imgCount"
            color="#2c2c2c"
            style="
            top: 49px;
            width: 46px;
            left: 83%;">
            {{feedData.pictureNum + 1}}/{{feedData.items.length}} 
          </v-chip>
      </div>
      <!-- 좋아요, 댓글, 스크랩 버튼 -->
      <div style="overflow: hidden; border-bottom: 1px solid lightgray; height: 45px;">
        <div style="float: left;">
          <v-btn icon @click="likedbtn(feedData.no)">
              <v-icon v-if="!feedData.islike" size="30px" color="black">mdi-heart-outline</v-icon>
              <v-icon v-if="feedData.islike" size="30px" color="red">mdi-heart</v-icon>
          </v-btn>
          <v-btn icon color="black" @click="onComment(feedData.no)">
              <v-icon size="30px">mdi-chat-outline</v-icon>
          </v-btn>
          <b>좋아요 {{feedData.likecount}} 개</b>
        </div>
        <div style="float: right;">
          <v-btn icon @click="scrapedbtn(feedData.no)">
              <v-icon v-if="!feedData.isscrap" size="30px" color="black">mdi-bookmark-outline</v-icon>
              <v-icon v-if="feedData.isscrap" size="30px" color="#a0d469">mdi-bookmark</v-icon>
          </v-btn>
          <!-- 수정 삭제 -->
          <div style="display: inline-block" v-if="feedData.email==userInfo.email">
            <v-btn @click="openBtn(feedData.no)" icon style="width: 25px; height: 25px">
              <v-icon color="black" size="25px" style="">mdi-dots-vertical</v-icon>
            </v-btn>
            <div class="btns" v-if="feedData.no == btnsFeedNo">
              <router-link :to="{ name: 'ModifyRecipe', params: { feedNo : feedData.no }}"><v-btn class="btn" >수정</v-btn></router-link>
              <v-btn class="btn" @click="deleteNo(feedData.no)">삭제</v-btn>
            </div>
          </div>
        </div>
      </div>
      <!-- 댓글 -->
      <div class="commentbody" v-if="feedData.openComment">
        <div class="commentDiv">
          <input v-model="inputComment" @keyup.enter="onCommentBtn(feedData.no)" type="text" class="commentInput" placeholder="Comment">
          <v-btn icon class="sendBtn" @click="onCommentBtn(feedData.no)">
            <v-icon>mdi-send</v-icon>
          </v-btn>
        </div>
        <div class="comments" v-for="(comment, i) in feedData.comments" :key="i">
          <div class="userImg">
            <v-avatar size="35" style="cursor : pointer;"><img :src="comment.img" alt="John" @click="moveUser(comment.email)"></v-avatar>
          </div>
          <div class="content" style="display: table; height: 35px; margin-left: 10px">
            <div style="display: table-cell; vertical-align: middle;">
              <span class="commentUser" style="margin: 0 5px 0 0; cursor : pointer;" @click="moveUser(comment.email)">{{comment.nickname}}</span>
              <span>{{comment.comment}}</span>
            </div>
          </div>
          <div style="float: right; width: 10%" v-if="comment.email==userInfo.email">
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
import axios from "axios";
import store from '../../vuex/store'
import moment from "moment";
import $ from 'jquery';
import Swal from 'sweetalert2'
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";

const SERVER_URL = store.state.SERVER_URL;

export default {
  data: () => {
    return {
      userInfo: "",
      feedDatas: [],
      imgNumber: "",
      inputComment: '',
      commentData: [],
      originalDatas: [],
      btnsFeedNo: '',
      myDatas:[],
      switched:true,
    };
  },
  computed: {
  },
  watch: {
    // feedDatas: function(v) {
    //   this.feedDatas.forEach(feedData => {
    //     // console.log(feedData)
    //     if (feedData.comment.length > 0) {
    //       $('.sendBtn').css('color', '#a0d469')
    //     }
    //     else {
    //       $('.sendBtn').css('color', 'rgba(0, 0, 0, 0.54)')
    //     }
    //   })
    // },
  },

  mounted(){
    this.callList();
    if(store.state.kakaoUserInfo.email != null){
      this.userInfo = store.state.kakaoUserInfo;
    }else{
      this.userInfo = store.state.userInfo;
    }  
  },
  methods: {
    callList(){
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/searchAll`) // 피드 가져오기
        .then(response => {
          // console.log(response);
            // var data = {};

          response.data.feedlist.forEach(d =>{
            var data = { // 하나의 피드 데이터
              no: d.no,
              nickname : d.nickname,
              email : d.email,
              profile : d.profile,
              islike: false,
              isscrap: false,
              openComment: false,
              comment: "",
              items: [],
              tags:[],
              comments:[],
              likecount: d.likecount,
              foods:[],
            }

          axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/searchComment`,{params:{feedNo : d.no}}) // 피드에 해당하는 댓글 불러오기
          .then(response => {
            // console.log(response);
            response.data.forEach(c =>{
              var comment = { // 피드에 해당하는 하나의 댓글
                no : c.no,
                feed_no : d.no,
                img : c.img,
                nickname : c.nickname,
                email: c.email,
                comment : c.comment,
                create_date : c.create_date,
              }
              data.comments.push(comment);
            })
          });

          axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/check`,{
            params:
            {
              email:store.state.userInfo.email,
              feedNo : d.no,
            }
            })
          .then(response =>{
            // console.log(response);
              data.islike = response.data.like;
              data.isscrap = response.data.scrap;
          });

          response.data.datalist.forEach(dindex => {
            if(dindex.feedNo == d.no){
              var image = {image : dindex.img};
              data.items.push(image);
            }
          });

          response.data.taglist.forEach(t => {
            if(t.feedNo == d.no){
              var tag = {tagName : t.tagName};
              data.tags.push(tag);
            }
          });

          response.data.foodlist.forEach(f => {
            if(f.feedNo == d.no){
              var food = {foodName : f.name_kor};
              data.foods.push(food);
            }
          })

          this.feedDatas.push(data); // 피드 데이터 저장
          // console.log(data);
        });
        // console.log(this.feedDatas);
        this.originalDatas = this.feedDatas.reverse();
      })
      .catch((error) => {
        // console.log(error.response);
      });
    },
    countItem(i) {
      this.imgNumber = i
    },
    onComment(feedData_id) {
      this.feedDatas.forEach(feedData => {
        if (feedData.no == feedData_id) {
          feedData.openComment = !feedData.openComment
        }
      })
    },
    onCommentBtn(feedData_id) {
      // 댓글 추가기능
      var comment = {
        img: store.state.userInfo.profile_image_url, 
        nickname: store.state.userInfo.nickname,
        email:store.state.userInfo.email,
        feedNo: feedData_id,
        comment: this.inputComment
      }
      // console.log(comment);
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/feed/register`,comment)
      .then(response=>{
        // console.log(response);
        this.inputComment = "";
      })

      this.feedDatas.forEach(feedData => {
        if (feedData.no == feedData_id) {
          feedData.comments.push(comment);
        }
        // 댓글 input 초기화 해주기
      })
    },
    // 댓글 삭제하기
    deleteComment(feedData_id, comment) {
      this.feedDatas.forEach(feedData => {
        if (feedData.no == feedData_id) {
          var comm = feedData.comments[feedData.comments.indexOf(comment)];
          feedData.comments.splice(feedData.comments.indexOf(comment), 1);
          // console.log(comm);

          axios.delete(`https://i3b301.p.ssafy.io:9999/food/api/feed/comment`,{params:{no : comm.no}})
          .then(response =>{

          })
        }
      })
    },
    likedbtn(feedData_id) {
      // console.log(feedData_id + " " + store.state.userInfo.email);
      this.feedDatas.forEach(feedData => {
        if (feedData.no == feedData_id) {
          if(feedData.islike == false){
            feedData.likecount += 1;
          }else{
            feedData.likecount -= 1;
          }
          feedData.islike = !feedData.islike
        }
      })
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/like`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : feedData_id,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          // console.log(error.response);
      });
    },
    scrapedbtn(feedData_id) {
      // console.log(feedData_id + " " + store.state.userInfo.email);
      this.feedDatas.forEach(feedData => {
        if (feedData.no == feedData_id) {
          feedData.isscrap = !feedData.isscrap
        }
      })
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/scrap`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : feedData_id,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          // console.log(error.response);
        });
    },
    openBtn(feedNo) {
      // if($('.btns').css('display')=='block'){
      //   $('.btns').css('display','none');  
      // }else{
      //   $('.btns').css('display','block');
      // }
      // console.log(feedNo)
      if (this.btnsFeedNo) {
        this.btnsFeedNo = ''
      }
      else{
        this.btnsFeedNo = feedNo
      }
    },
    moveUser(user_email){
      if(user_email == store.state.userInfo.email){
        this.$router.push({name: 'Mypage'});
      }else{
        // console.log(user_email)
        this.$router.push({name: 'Yourpage', params: {email : user_email}});
      }
    },
    searchTag(tags){
      // console.log(tags);
      if(this.myDatas.length == 0){
        this.feedDatas = this.originalDatas;
        if(tags.length != 0){
          this.feedDatas = this.feedDatas.filter(function (item) {
            var isTag = false;
            item.tags.forEach(tag => {
              if(tags.indexOf("#"+tag.tagName) != -1){
                // console.log("#"+tag.tagName + " " + tags.indexOf("#"+tag.tagName));
                // console.log(item);
                isTag = true;
                return;
              }
            })
            return isTag;
          });

          if(this.feedDatas.length == 0){
            Swal.fire({
              icon: 'error',
              title: '',
              text: '검색어와 일치하는 레시피가 없습니다!',
              footer: ''
            })
          }
        }
      } else{
        this.feedDatas = this.myDatas;
        if(tags.length != 0){
          this.feedDatas = this.feedDatas.filter(function (item) {
            var isTag = false;
            item.tags.forEach(tag => {
              if(tags.indexOf("#"+tag.tagName) != -1){
                // console.log("#"+tag.tagName + " " + tags.indexOf("#"+tag.tagName));
                // console.log(item);
                isTag = true;
                return;
              }
            })
            return isTag;
          });
          if(this.feedDatas.length == 0){
            Swal.fire({
              icon: 'error',
              title: '',
              text: '검색어와 일치하는 레시피가 없습니다!',
              footer: ''
            })
          }
        }
      }
      // console.log(this.feedDatas);
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
            window.location.reload();
            this.$router.push('/feed/main');
          })
        }
      })
    },
    call(){
      if(this.switched == true){
        let timerInterval
        Swal.fire({
          title: '레시피를 찾는 중입니다.',
          html: '',
          timer: 500,
          timerProgressBar: true,
          onBeforeOpen: () => {
            Swal.showLoading()
            timerInterval = setInterval(() => {
              const content = Swal.getContent()
              if (content) {
                const b = content.querySelector('b')
                if (b) {
                  b.textContent = Swal.getTimerLeft()
                }
              }
            }, 100)
          },
          onClose: () => {
            clearInterval(timerInterval)
          }
        })
        var myFood = [];
        axios.get(`https://i3b301.p.ssafy.io:9999/food/api/myref/search/`+store.state.userInfo.email)
        .then(response => {
          this.feedDatas = this.originalDatas;  //  원본 데이터
          response.data.myreflist.forEach(d => {
            myFood.push(d.name_kor);
          })
          // console.log(myFood);
          this.feedDatas = this.feedDatas.filter(function (feed) {
            var hasFood = false;
            feed.foods.forEach(food => {
              if(myFood.indexOf(food.foodName) != -1){
                // console.log(feed.no);
                hasFood = true;
                // this.myDatas.push(feed);
                return;
              }
            });
            return hasFood;
          });
          // console.log(this.feedDatas);
          this.myDatas = this.feedDatas;
          if(this.myDatas.length == 0){
            Swal.fire({
              icon: 'error',
              title: '만들 수 있는 레시피가 없습니다!',
              text: '냉장고에 재료를 등록해주세요!',
              footer: ''
            })
          }
        })
        .catch(error => {
          // console.log(error.response)
        });
        // console.log(this.feedDatas);
        

        this.switched = false;
      }
      else{
          this.feedDatas = this.originalDatas;
          this.switched = true;
        }
    },
  },
};
</script>
<style scoped>
.btns {
  /* display: none; */
  position: absolute;
  width: 80px;
  /* height: 60px; */
  z-index: 80;
  left: 77%;
  top: 83%;
  border: 1px solid lightgray;
  border-radius: 4px;
}
.btn {
  /* width: 100%; */
  background-color: white !important;
  border-radius: unset;
  box-shadow: unset;
  -webkit-box-shadow: unset;
}
.feed-item {
    padding:0px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    margin: 0;
    border: none;
    border-top: 1px solid #eee;
    left:auto;
}
.btn span {
    height: 30px;
  }
.commentbody {
  border-bottom: 1px solid lightgray;
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
  overflow: hidden;
}
.userImg {
  float: left;
  width: 10%;
}
.content {
  float: left;
  width: 75%;
}
.commentUser {
  margin-bottom: 0 !important;
  font-weight: bold; 
  margin-right: 5px;
}
.imgCount {
    position: absolute;
    top: 0;
    right: 0;
    margin-right: -50px;
}
</style>
<template>
  <div class="feed-item">
    <div v-for="(feedData, i) in feedDatas" :key="i">
      <div class="feed-profil">
        <div class="feed-user">
          <v-avatar size="35"><img :src="feedData.profile" alt="John" @click="moveUser(feedData.email)"></v-avatar>
          <h4 @click="moveUser(feedData.email)" style="display:inline-block; padding-left:5px">{{feedData.nickname}}</h4>
        </div>
        <div style="height: 45px; float: right; width: 10%;">
          <router-link :to="{ name: 'FeedDetail', params: { feedNo : feedData.no }}">
            <v-btn icon color="gray" style="background-color: #f1f3f5; border-radius: unset; height: 45px;">
              <v-icon class="feed-right-icon" size="35px">mdi-chevron-right</v-icon>
            </v-btn>
          </router-link>
        </div>
      </div>
      <div style="position: relative;">
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
            color="#2c2c2c">
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
        </div>
        <div style="float: right;">
          <v-btn icon @click="scrapedbtn(feedData.no)">
              <v-icon v-if="!feedData.isscrap" size="30px" color="black">mdi-bookmark-outline</v-icon>
              <v-icon v-if="feedData.isscrap" size="30px" color="#a0d469">mdi-bookmark</v-icon>
          </v-btn>
          <!-- 수정 삭제 -->
          <v-menu botoom offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-bind="attrs" v-on="on" icon style="width: 25px; height: 25px">
                <v-icon color="black" size="25px" style="">mdi-dots-vertical</v-icon>
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
            <v-avatar size="35"><img :src="comment.img" alt="John" @click="moveUser(comment.email)"></v-avatar>
          </div>
          <div class="content">
            <p class="commentUser" style="display: inline-block; margin: 0 5px 0 0;" @click="moveUser(comment.email)">{{comment.nickname}}</p>
            <span>{{comment.comment}}</span>
          </div>
          <div style="float: right; width: 10%" v-if="comment.email===userInfo.email">
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
    };
  },
  computed: {
  },
  watch: {
    // feedDatas: function(v) {
    //   this.feedDatas.forEach(feedData => {
    //     console.log(feedData)
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
      axios.get(`${SERVER_URL}/feed/searchAll`) // 피드 가져오기
        .then(response => {
          console.log(response);
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
              items: [    
              ],

              comments:[],
            }

          axios.get(`${SERVER_URL}/feed/searchComment`,{params:{feedNo : d.no}}) // 피드에 해당하는 댓글 불러오기
          .then(response => {
            console.log(response);
            response.data.forEach(c =>{
              var comment = { // 피드에 해당하는 하나의 댓글
                img : c.img,
                nickname : c.nickname,
                email: c.email,
                comment : c.comment,
                created_at : c.create_date,
              }
              data.comments.push(comment);
            })
          });

          axios.get(`${SERVER_URL}/feed/check`,{
            params:
            {
              email:store.state.userInfo.email,
              feedNo : d.no,
            }
            })
          .then(response =>{
            console.log(response);
              data.islike = response.data.like;
              data.isscrap = response.data.scrap;
          });

          response.data.datalist.forEach(dindex => {
            if(dindex.feedNo == d.no){
              var image = {image : dindex.img};
              data.items.push(image);
            }
          });

          this.feedDatas.push(data); // 피드 데이터 저장
          console.log(data);
        });
        console.log(this.feedDatas);
      })
      .catch((error) => {
        console.log(error.response);
      });
      
  },
  methods: {
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
      console.log(comment);
      axios.post(`${SERVER_URL}/feed/register`,comment)
      .then(response=>{
        console.log(response);
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
          feedData.comments.splice(feedData.comments.indexOf(comment), 1);
        }
      })
    },
    likedbtn(feedData_id) {
      console.log(feedData_id + " " + store.state.userInfo.email);
      this.feedDatas.forEach(feedData => {
        if (feedData.no == feedData_id) {
          feedData.islike = !feedData.islike
        }
      })
      axios.get(`${SERVER_URL}/feed/like`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : feedData_id,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          console.log(error.response);
      });
    },
    scrapedbtn(feedData_id) {
      console.log(feedData_id + " " + store.state.userInfo.email);
      this.feedDatas.forEach(feedData => {
        if (feedData.no == feedData_id) {
          feedData.isscrap = !feedData.isscrap
        }
      })
      axios.get(`${SERVER_URL}/feed/scrap`,{
        params:{
          email : store.state.userInfo.email,
          feedNo : feedData_id,
        }
        })
        .then(response => {
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
    moveUser(user_email){
      if(user_email == store.state.userInfo.email){
        this.$router.push({name: 'Mypage'});
      }else{
        console.log(user_email)
        this.$router.push({name: 'Yourpage', params: {email : user_email}});
      }
    }
  },
};
</script>
<style scoped>
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
  width: 80%;
}
.commentUser {
  margin-bottom: 0 !important;
  font-weight: bold; 
  margin-right: 5px;
}
</style>
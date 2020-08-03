<template>
  <div class="feed-item">
    <div v-for="(feedData, i) in feedDatas" :key="i">
      <div class="feed-profil">
        <div class="feed-user">
          <v-avatar size="35"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar>
          <h4 style="display:inline-block; padding-left:5px">사용자 이름</h4>
        </div>
        <div style="height: 45px; float: right; width: 10%;">
          <router-link to="/feed/detail">
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
            :src="item.src"
          ></v-carousel-item>
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
          <v-btn icon @click="likedbtn(feedData.id)">
              <v-icon v-if="!feedData.islike" size="30px" color="black">mdi-heart-outline</v-icon>
              <v-icon v-if="feedData.islike" size="30px" color="red">mdi-heart</v-icon>
          </v-btn>
          <v-btn icon color="black" @click="onComment(feedData.id)">
              <v-icon size="30px">mdi-chat-outline</v-icon>
          </v-btn>
        </div>
        <div style="float: right;">
          <v-btn icon @click="scrapedbtn(feedData.id)">
              <v-icon v-if="!feedData.isscrap" size="30px" color="black">mdi-bookmark-outline</v-icon>
              <v-icon v-if="feedData.isscrap" size="30px" color="#a0d469">mdi-bookmark</v-icon>
          </v-btn>
        </div>
      </div>
      <!-- 댓글 -->
      <div class="commentbody" v-if="feedData.openComment">
        <div class="commentDiv">
          <input v-model="feedData.comment" @keyup.enter="onCommentBtn(feedData.id)" type="text" class="commentInput" placeholder="Comment">
          <v-btn icon class="sendBtn" @click="onCommentBtn(feedData.id)">
            <v-icon>mdi-send</v-icon>
          </v-btn>
        </div>
        <div class="comments" v-for="(comment, i) in feedData.comments" :key="i">
          <div class="userImg">
            <v-avatar size="35"><img :src="comment.img" alt="John"></v-avatar>
          </div>
          <div class="content">
            <div>
              <p class="commentUser">{{comment.name}}</p>
              <span>{{comment.content}}</span>
            </div>
            <!-- <span style="font-size: 12px">{{comment.created_at}}</span> -->
          </div>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import axios from "axios";
import $ from 'jquery';
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";

const SERVER_URL = 'http://i3b301.p.ssafy.io:9999/food/api'

export default {
  data: () => {
    return {
      feedDatas: [
        {
          id: 1,
          pictureNum: 0,
          islike: false,
          isscrap: false,
          openComment: false,
          comment: "",
          items: [
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
            },
          ],
          comments: [
            {
              img: "https://cdn.vuetifyjs.com/images/john.jpg", 
              name: "Jhon34", 
              content: "아이들도 담백한 맛에 잘 먹을 것 같아요 한번 만들어보세요!! 감사합니다!",
              created_at: "2020-07-28 15:59"
            },
            {
              img: "https://cdn.vuetifyjs.com/images/john.jpg", 
              name: "Sara12", 
              content: "비주얼도 좋고 영양도 듬뿍~~ 다욧식품으로 좋을것 같아요",
              created_at: "2020-07-28 17:59"
            },
          ],
        },
        {
          id: 2,
          pictureNum: 0,
          islike: false,
          isscrap: false,
          openComment: false,
          comment: "",
          items: [
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
            },
            {
              src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
            },
          ],
          comments: [
            {
              img: "https://cdn.vuetifyjs.com/images/john.jpg", 
              name: "Jhon34", 
              content: "두번째 댓글 아이들도 담백한 맛에 잘 먹을 것 같아요 한번 만들어보세요!! 감사합니다!",
              created_at: "2020-07-28 15:59"
            },
            {
              img: "https://cdn.vuetifyjs.com/images/john.jpg", 
              name: "Sara12", 
              content: "두번째 댓글 비주얼도 좋고 영양도 듬뿍~~ 다욧식품으로 좋을것 같아요",
              created_at: "2020-07-28 17:59"
            },
          ],
        },
      ],
      imgNumber: "",
      commentData: [],
    };
  },
  watch: {
    feedDatas: function(v) {
      this.feedDatas.forEach(feedData => {
        console.log(feedData)
        if (feedData.comment.length > 0) {
          $('.sendBtn').css('color', '#a0d469')
        }
        else {
          $('.sendBtn').css('color', 'rgba(0, 0, 0, 0.54)')
        }
      })
    },
    // nowpicture: function(v) {
    //   this.countItem();
    // },
  },
  methods: {
    countItem(i) {
      this.imgNumber = i
    },
    onComment(feedData_id) {
      this.feedDatas.forEach(feedData => {
        if (feedData.id == feedData_id) {
          feedData.openComment = !feedData.openComment
        }
      })
    },
    onCommentBtn(feedData_id) {
      // 댓글 추가기능
      this.feedDatas.forEach(feedData => {
        if (feedData.id == feedData_id) {
          feedData.comments.push({
              img: "https://cdn.vuetifyjs.com/images/john.jpg", 
              name: "Sara12", 
              content: feedData.comment,
              created_at: "2020-07-28 17:59"
            },)
        }
        // 댓글 input 초기화 해주기
        feedData.comment = ""
      })
    },
    likedbtn(feedData_id) {
      this.feedDatas.forEach(feedData => {
        if (feedData.id == feedData_id) {
          feedData.islike = !feedData.islike
        }
      })
      // axios.get(`${SERVER_URL}/like`)
      //   .then(response => {
      //     const liked = response.data
      //     if ( liked ) {
      //       this.islike = liked
      //     }
      //     else {
      //       this.islike = liked
      //     }
      //   })
      //   .catch((error) => {
      //     console.log(error.response);
      //   });
    },
    scrapedbtn(feedData_id) {
      this.feedDatas.forEach(feedData => {
        if (feedData.id == feedData_id) {
          feedData.isscrap = !feedData.isscrap
        }
      })
      // axios.get(`${SERVER_URL}/scrap`)
      //   .then(response => {
      //     const scrapped = response.data
      //     if ( scrapped ) {
      //       this.isscrap = scrapped
      //     }
      //     else {
      //       this.isscrap = scrapped
      //     }
      //   })
      //   .catch((error) => {
      //     console.log(error.response);
      //   });
    },
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
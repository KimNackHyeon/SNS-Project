<template>
  <div class="user">
    <div class="mypage">
      <div class="mypage-body">
        <div class="profil">
          <div style="overflow: hidden; margin: 20px 0;">
            <div class="myphoto" :style="{width: (frameSize.x*0.3)+'px', height: (frameSize.x*0.3)+'px'}"><v-avatar :style="{width: (frameSize.x*0.3)+'px', height: (frameSize.x*0.3)+'px'}"><img :src="userinfo.profile_image_url" alt="John"></v-avatar></div>
            <div class="myprofil" :style="{width: (frameSize.x*0.7)+'px', height: (frameSize.x*0.4)+'px'}">
              <div style="margin-bottom: 10px">
                <!-- <img style="width: 50px; height: 50px;" src="../../assets/images/fresh_grade/4.png" alt="신선도"> -->
                <img :style="{width: (frameSize.x*0.15)+'px', height: (frameSize.x*0.15)+'px'}" :src="require(`../../assets/images/fresh_grade/${userData.score}.png`)" alt="신선도">
                <h2 class="user-name">{{userinfo.nickname}}</h2>
                <router-link to="/user/modifyuser"><v-btn class="myprofil-icon" icon><v-icon>mdi-cog</v-icon></v-btn></router-link>
              </div>
              <v-container style="min-height: 0; padding: 10px;">
                <v-row class="myprofil-boxes" no-gutters>
                  <v-col class="myprofil-box" cols="4">
                    <span>레시피 수</span>
                    <h1>{{userData.recipe}}</h1>
                  </v-col>
                  <!-- 팔로워 -->
                  <v-col class="onmyprofil-box" cols="4" @click="onFollower">
                    <span style="color: black;">팔로워</span>
                    <h1>{{userData.follower}}</h1>
                  </v-col>
                  <!-- 팔로워 dialog -->
                  <v-dialog v-model="openFollower" scrollable width= "100%">
                    <v-card>
                      <v-card-title >팔로워 {{userData.follower}}명</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text>
                        <div class="follow" v-for="(follower, i) in followers" :key="i">
                          <div class="userImg">
                            <v-avatar style="cursor:pointer;" size="35" @click="moveUser(follower.email)"><img :src="follower.image" :alt="`${follower.nickname} 사진`"></v-avatar>
                          </div>
                          <div class="content">
                            <p class="followNick" style="cursor:pointer;" @click="moveUser(follower.email)">{{follower.nickname}}</p>
                            <p class="followEmail">{{follower.email}}</p>
                          </div>
                        </div>
                      </v-card-text>
                      <v-divider></v-divider>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="black" text @click="openFollower = false">닫기</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                  <!-- 팔로잉 -->
                  <v-col class="onmyprofil-box" cols="4" style="border-right: 1px solid lightgray" @click="onFollowing">
                    <span>팔로잉</span>
                    <h1>{{userData.following}}</h1>
                  </v-col>
                  <!-- 팔로잉 dialog -->
                  <v-dialog v-model="openFollowing" scrollable width= "100%">
                    <v-card>
                      <v-card-title >팔로잉 {{userData.following}}명</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text>
                        <div class="follow" v-for="(following, i) in followings" :key="i">
                          <div class="userImg">
                            <v-avatar size="35" style="cursor:pointer;" @click="moveUser(following.email)"><img :src="following.image" :alt="`${following.nickname} 사진`"></v-avatar>
                          </div>
                          <div class="content">
                            <p class="followNick" style="cursor:pointer;" @click="moveUser(following.email)">{{following.nickname}}</p>
                            <p class="followEmail">{{following.email}}</p>
                          </div>
                          <div class="followbtn" @click="onFollowBtn(following)">
                            <v-btn color="#a0d469" style="box-shadow: unset; color: white" v-if="!following.isfollow">팔로우</v-btn>
                            <v-btn color="#eee" style="box-shadow: unset; color: black" v-if="following.isfollow">팔로잉</v-btn>
                          </div>
                        </div>
                      </v-card-text>
                      <v-divider></v-divider>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="black" text @click="openFollowing = false">닫기</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-row>
              </v-container>
            </div>
          </div>
        </div>
        <!-- 팔로우 버튼 -->
        <div class="myfeed">
          <button class="myprofil-feed" @click="scrapOff">
            <div class="myprofil-text">
              <img src="../../assets/images/pencil.png" alt="my recipe">
              <!-- <i class="fas fa-pencil-alt fa-lg"></i> -->
              <h3>내가 쓴 글</h3>
            </div>
          </button>
          <button class="myprofil-scrap" @click="scrapOn">
            <div class="myprofil-text">
              <img src="../../assets/images/bookmark.png" alt="scrap">
              <!-- <i class="far fa-bookmark"></i> -->
              <h3>스크랩한 글</h3>
            </div>
          </button>
        </div>
        <div class="myrecipe" style="overflow-y: scroll;" v-if="!myscrap">
          <h3 class="myrecipe-title">내 레시피</h3>
          <div class="myrecipe-body">
            <div class="myrecipe-img" style="width: 32%; height:32%" v-for="(recipe, i) in recipes" :key="i">
              <router-link :to="{ name: 'FeedDetail', params: { feedNo : recipe.feedNo }}">
                <img class="myrecipe-img-size" style="width:100%; height:100%;" :src="recipe.img" alt="food">
                <!-- <img class="myrecipe-img-size" :src="require(`../../assets/images${recipe.img}`)" alt="food"> -->
              </router-link>
            </div>
          </div>
        </div>
        
        <div class="myrecipe" style="overflow-y: scroll;" v-if="myscrap">
          <h3 class="myrecipe-title">내 스크랩</h3>
          <div class="myrecipe-body">
            <div class="myrecipe-img" style="width: 33%; height:33%" v-for="(scrap, i) in scraps" :key="i">
              <router-link :to="{ name: 'FeedDetail', params: { feedNo : scrap.feedNo }}">
                <img class="myrecipe-img-size" style="width:100%; height:100%;" :src="scrap.img" alt="food">
                <!-- <img class="myrecipe-img-size" :src="require(`../../assets/images${scrap.img}`)" alt="food"> -->
              </router-link>
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
import "../../components/css/user.scss"
import store from '../../vuex/store.js'

const SERVER_URL = store.state.SERVER_URL;

export default {
  mounted(){
    this.onResize();
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }

      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/myrecipe/`, {params: {email: this.userinfo.email}})
      .then(response => {
          // // console.log(response)
          this.recipes = response.data;
        })
        .catch(error =>{
          // // console.log(error)
        })

      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/myscrap/`, {params: {email: this.userinfo.email}})
      .then(response => {
          // // console.log(response)
          this.scraps = response.data;
        })
        .catch(error =>{
          // // console.log(error)
        })
    },
  data() {
    return {
      frameSize: {
        x: window.innerHeight * 0.5625,
        y: window.innerHeight,
        per: 1,
      },
      userinfo:'',
      myscrap: false,
      userData:{
        recipe:"",
        follower:"",
        following:"",
        score:"",
      },
      recipes:[],
      scraps:[],
      openFollower: false,
      followers: "",
      openFollowing: false,
      followings: "",
    }
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
    onFollower() {
      if(this.openFollower==false){
        this.openFollower = true;
      }else{
        this.openFollower = false
      }
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/follow/`, {params: {email: this.userinfo.email}})
        .then(response => {
          // // console.log(response)
          this.followers = response.data;
        })
        .catch(error =>{
          // // console.log(error)
        })
    },
    onFollowing() {
      if(this.openFollowing==false){
        this.openFollowing = true;
      }else{
        this.openFollowing = false
      }
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/following/`, {params: {email: this.userinfo.email}})
        .then(response => {
          // // console.log(response)
          this.followings = response.data
          this.followings.forEach(following => {
            this.$set(following, 'isfollow', true)
          })
          // response.data.forEach(following => {
          //   this.followings.push({
          //   nickname : following.nickname,
          //   email : following.email,
          //   image : following.image,
          //   isfollow : true,
          // })
          // })

          // // console.log(this.followings)
        })
        .catch(error =>{
          // // console.log(error)
        })
    },
    onFollowBtn(following) {
      following.isfollow = !following.isfollow
      // // console.log(following);
      // this.isfollow = !this.isfollow;
      if(following.isfollow){
        this.addFollow(following.email);
      } else{
        this.unFollow(following.email);
      }
    },
    addFollow(yourEmail){
      // alert('팔로우');
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/follow/`,
        {
          email : store.state.userInfo.email,
          yourEmail : yourEmail,
        }
      ).then(response => {
        this.updateList();
      })
    },
    unFollow(yourEmail){
      // alert('언팔로우');
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/unfollow/`,
        {
          email : store.state.userInfo.email,
          yourEmail : yourEmail,
        }
      ).then(response => {
        this.updateList();
      })
    },
    updateList(){
      // axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/yourpage/`+ this.$route.params.email)
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/yourpage/`+ this.userinfo.email)
        .then(response => {
          // // console.log(response);
          this.userData.nickname = response.data.nickname;
          this.userData.image = response.data.img;
          this.userData.following = response.data.following;
          this.userData.follower = response.data.follower;
          // // console.log(this.userData.follower+" "+this.userData.following);
        })
        .catch(error => {
          // // console.log(error.response)
        })
    },
    moveUser(user_email){
      if(user_email == store.state.userInfo.email){
        this.$router.push({name: 'Mypage'});
      }else{
        // // console.log(user_email)
        this.$router.push({name: 'Yourpage', params: {email : user_email}});
      }
    },
    scrapOn(){
      this.myscrap = true
      $('.myprofil-scrap').css('background-color', 'rgb(202, 231, 171)')
      $('.myprofil-feed').css('background-color', 'white')
    },
    scrapOff(){
      this.myscrap = false
      $('.myprofil-feed').css('background-color', 'rgb(202, 231, 171)')
      $('.myprofil-scrap').css('background-color', 'white')
    },
  },
  created() {
    if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
    }else{
      this.userinfo = store.state.userInfo;
    }
    axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/mypage/`+ this.userinfo.email)
      .then(response => {
        this.userData.recipe = response.data.recipe;
        this.userData.following = response.data.following;
        this.userData.follower = response.data.follower;
        const score = Math.floor(response.data.eval_point/response.data.eval_count);
        this.userData.score = score +"";
        // // console.log(this.userData.follower+" "+this.userData.following);
      })
      .catch(error => {
        // // console.log(error.response)
      })
    // // console.log(this.kakaoUserInfo);
},
}
</script>

<style scoped>
  .follow {
    padding: 10px 0 10px 0;
    overflow: hidden;
  }
  .userImg {
    float: left;
    margin-right: 20px;
  }
  .content {
    float: left;
  }
  .followNick {
    margin: 0 !important;
    color: black;
    font-weight: bold;
    font-size: 15px;
  }
  .followEmail {
    margin: 0 !important;
    font-size: 10px
  }
  .followbtn {
    float: right;
    width: 50px;
  }
  .v-card {
    height: 420px;
  }
  .myprofil .container {
    width: 100% !important;
  }
</style>
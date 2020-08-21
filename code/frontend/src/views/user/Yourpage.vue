<template>
  <div class="user">
    <div class="mypage">
      <div class="mypage-body">
        <div class="profil">
          <div style="overflow: hidden; margin: 20px 0;">
            <div class="myphoto" :style="{width: (frameSize.x*0.3)+'px', height: (frameSize.x*0.3)+'px'}"><v-avatar :style="{width: (frameSize.x*0.3)+'px', height: (frameSize.x*0.3)+'px'}"><img :src="yourData.image" alt="John"></v-avatar></div>
            <div class="myprofil" :style="{width: (frameSize.x*0.7)+'px', height: (frameSize.x*0.4)+'px'}">
              <div style="margin: 10px">
                <img :style="{width: (frameSize.x*0.15)+'px', height: (frameSize.x*0.15)+'px'}" :src="require(`../../assets/images/fresh_grade/${yourData.score}.png`)" alt="신선도">
                <h2 class="user-name">{{yourData.nickname}}</h2>
              </div>
              <v-container style="min-height: 0; padding: 10px;" >
                <v-row class="myprofil-boxes" no-gutters>
                  <v-col class="myprofil-box" cols="4">
                    <span>레시피 수</span>
                    <h1>{{yourData.recipe}}</h1>
                  </v-col>
                  <!-- 팔로워 -->
                  <v-col class="myprofil-box onmyprofil-box" cols="4" @click="onFollower">
                    <span>팔로워</span>
                    <h1>{{yourData.follower}}</h1>
                  </v-col>
                  <!-- 팔로워 dialog -->
                  <v-dialog v-model="openFollower" scrollable width= "100%">
                    <v-card>
                      <v-card-title >팔로워 {{yourData.follower}}명</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text>
                        <div class="follow" v-for="(follower, i) in followers" :key="i">
                          <div class="userImg" >
                            <v-avatar style="cursor:pointer;" size="35" @click="moveUser(follower.email)"><img :src="follower.image" :alt="`${follower.nickname} 사진`"></v-avatar>
                            <!-- <v-avatar size="35"><img :src="require(`../../assets/images/food/${follower.img}`)" alt="John"></v-avatar> -->
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
                  <v-col class="myprofil-box onmyprofil-box" cols="4" style="border-right: 1px solid lightgray" @click="onFollowing">
                    <span>팔로잉</span>
                    <h1>{{yourData.following}}</h1>
                  </v-col>
                  <!-- 팔로잉 dialog -->
                  <v-dialog v-model="openFollowing" scrollable width= "100%">
                    <v-card>
                      <v-card-title >팔로잉 {{yourData.following}}명</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text>
                        <div class="follow" v-for="(following, i) in followings" :key="i">
                          <div class="userImg">
                            <v-avatar style="cursor:pointer;" size="35" @click="moveUser(following.email)"><img :src="following.image" :alt="`${following.nickname} 사진`"></v-avatar>
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
        <div style="margin: 10px;" @click="onFollow">
          <v-btn color="rgb(160, 212, 105)" style="width: 100%; height: 35px;" v-if="!isfollow">팔로우</v-btn>
          <v-btn color="#EEE" style="width: 100%; height: 35px;" v-if="isfollow">팔로잉</v-btn>
        </div>
        
        <div class="myrecipe" style="overflow-y: scroll;">
          <h3 class="myrecipe-title">{{yourData.nickname}}님의 레시피</h3>
          <div class="myrecipe-body">
            <div class="myrecipe-img" style="width: 33%; height:33%" v-for="(recipe, i) in recipes" :key="i">
              <router-link :to="{ name: 'FeedDetail', params: { feedNo : recipe.feedNo }}">
                <img class="myrecipe-img-size" style="width:100%; height:100%;" :src="recipe.img" alt="food">
                <!-- <img class="myrecipe-img-size" :src="require(`../../assets/images${recipe.img}`)" alt="food"> -->
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import "../../components/css/user.scss"
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
      yourData:{
        email: '',
        nickname:'',
        image:'',
        recipe:"",
        follower:"",
        following:"",
        score:"",
      },
      recipes:[],
      isfollow : false,
      openFollower: false,
      followers: "",
      openFollowing: false,
      followings: "",
    }
  },
  watch: {
    followings: function(v) {
      this.updateFollowBtn();
    },
  },
  mounted(){
      this.onResize();
        if(store.state.kakaoUserInfo.email != null){
          this.userinfo = store.state.kakaoUserInfo;
        }else{
          this.userinfo = store.state.userInfo;
        }
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }

      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/myrecipe/`, {params: {email: this.$route.params.email}})
      .then(response => {
          // // console.log(response)
          this.recipes = response.data;
          this.yourData.recipe = this.recipes.length;
        })
        .catch(error =>{
          // // console.log(error)
        })
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
    onFollow(){
      this.isfollow = !this.isfollow;
      if(this.isfollow){
        this.addFollow(this.$route.params.email);
      } else{
        this.unFollow(this.$route.params.email);
      }
    },
    addFollow(yourEmail){
      // alert('팔로우');
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/follow/`,
        {
          email : this.userinfo.email,
          yourEmail : yourEmail
        }
      ).then(response => {
        this.updateList();
      });

      
    },
    unFollow(yourEmail){
      // alert('언팔로우');
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/account/unfollow/`,
        {
          email : this.userinfo.email,
          yourEmail : yourEmail
        }
      ).then(response => {
        this.updateList();
      })
    },
    updateList(){
      // axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/yourpage/`+ this.$route.params.email)
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/yourpage/`+ this.yourData.email)
        .then(response => {
          // // console.log(response);
          this.yourData.nickname = response.data.nickname;
          this.yourData.image = response.data.img;
          this.yourData.following = response.data.following;
          this.yourData.follower = response.data.follower;
          // // console.log(this.yourData.follower+" "+this.yourData.following);
        })
        .catch(error => {
          // // console.log(error.response)
        })
    },
    onFollower() {
      if(this.openFollower==false){
        this.openFollower = true;
      }else{
        this.openFollower = false
      }
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/follow/`, {params: {email: this.yourData.email}})
        .then(response => {
          // // console.log(response)
          this.followers = response.data
        })
        .catch(error =>{
          // // console.log(error)
          // // console.log('에러')
        })
    },
    onFollowing() {
      if(this.openFollowing==false){
        this.openFollowing = true;
      }else{
        this.openFollowing = false
      }
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/following/`, {params: {email: this.yourData.email}})
        .then(response => {
          // // console.log(response)
          this.followings = response.data
          // // console.log(this.followings)
        })
        .catch(error =>{
          // // console.log(error)
        })      
    },
    updateFollowBtn() {
      // yourpage의 following 리스트가 뜨고 내가 그 사람을 팔로우 했는지 안했는지 검사
      this.followings.forEach(following => {
        axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/isfollow/`,
        {
          params:
          {
            email : this.userinfo.email,
            yourEmail : following.email
          }
        })
        .then(response => {
          // // console.log('성공')
          this.$set(following, 'isfollow', response.data)
        })
        .catch(error => {
          // // console.log(error)
        })
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
    moveUser(user_email){
      if(user_email == store.state.userInfo.email){
        this.$router.push({name: 'Mypage'});
      }else{
        // // console.log(user_email)
        this.$router.push({name: 'Yourpage', params: {email : user_email}});
        this.openFollowing = false;
        this.openFollower = false;
        Swal.fire({
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        })
        window.location.reload();
      }
    },
  },
  created() {
    if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/isfollow/`,
      {
        params:
        {
          email : this.userinfo.email,
          yourEmail : this.$route.params.email
        }
      })
      .then(response => {
        // // console.log(response.data);
        this.isfollow = response.data;
      })


      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/account/yourpage/`+ this.$route.params.email)
        .then(response => {
          // // console.log(response);
          this.yourData.email = this.$route.params.email;
          this.yourData.nickname = response.data.nickname;
          this.yourData.image = response.data.img;
          this.yourData.following = response.data.following;
          this.yourData.follower = response.data.follower;
          this.yourData.score = Math.floor((response.data.eval_point) / (response.data.eval_count));
          // console.log(this.yourData)
          // console.log(this.yourData.follower+" "+this.yourData.following);
        })
        .catch(error => {
          // // console.log(error.response)
        })
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
    font-size: 10px;
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
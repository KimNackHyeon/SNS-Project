<template>
  <div class="user">
    <div class="mypage">
      <div class="mypage-body">
        <div class="profil">
          <div style="overflow: hidden; margin: 20px 0;">
            <div class="myphoto"><v-avatar size="100"><img :src="userData.image" alt="John"></v-avatar></div>
            <div class="myprofil">
              <div style="margin: 10px">
                <h2 class="user-name">{{userData.nickname}}</h2>
              </div>
              <v-container style="min-height: 0; padding: 10px" >
                <v-row class="myprofil-boxes" no-gutters>
                  <v-col class="myprofil-box" cols="4">
                    <span>레시피 수</span>
                    <h1>5</h1>
                  </v-col>
                  <!-- 팔로워 -->
                  <v-col class="myprofil-box" cols="4" @click="onFollower">
                    <span>팔로워</span>
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
                            <v-avatar size="35"><img :src="follower.image" :alt="`${follower.nickname} 사진`"></v-avatar>
                            <!-- <v-avatar size="35"><img :src="require(`../../assets/images/food/${follower.img}`)" alt="John"></v-avatar> -->
                          </div>
                          <div class="content">
                            <p class="followEmail">{{follower.email}}</p>
                            <p class="followNick">{{follower.nickname}}</p>
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
                  <v-col class="myprofil-box" cols="4" style="border-right: 1px solid lightgray" @click="onFollowing">
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
                            <v-avatar size="35"><img :src="following.image" :alt="`${following.nickname} 사진`"></v-avatar>
                          </div>
                          <div class="content">
                            <p class="followEmail">{{following.email}}</p>
                            <p class="followNick">{{following.nickname}}</p>
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
        
        <div class="myrecipe">
          <h3 class="myrecipe-title">{{userData.nickname}}님의 레시피</h3>
          <div class="myrecipe-body">
            <div class="myrecipe-img">
              <img class="myrecipe-img-size" src="../../assets/images/food1.jpg" alt="food">
            </div>
            <div class="myrecipe-img">
              <img class="myrecipe-img-size" src="../../assets/images/food2.png" alt="food">
            </div>
            <div class="myrecipe-img">
              <img class="myrecipe-img-size" src="../../assets/images/food3.jpg" alt="food">
            </div>
            <div class="myrecipe-img">
              <img class="myrecipe-img-size" src="../../assets/images/food4.jpg" alt="food">
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

const SERVER_URL = store.state.SERVER_URL;

export default {
  data() {
    return {
      userinfo:'',
      userData:{
        email: '',
        nickname:'',
        image:'',
        recipe:"",
        follower:"",
        following:"",
      },
      isfollow : false,
      openFollower: false,
      followers: "",
      openFollowing: false,
      followings: "",
    }
  },
  mounted(){
    },
  methods: {
    onFollow(){
      this.isfollow = !this.isfollow;
      if(this.isfollow){
        this.addFollow();
      } else{
        this.unFollow();
      }
    },
    addFollow(){
      // alert('팔로우');
      axios.post(`${SERVER_URL}/account/follow/`,
        {
          email : store.state.userInfo.email,
          yourEmail : this.$route.params.email
        }
      ).then(response => {
        this.updateList();
      })
    },
    unFollow(){
      // alert('언팔로우');
      axios.post(`${SERVER_URL}/account/unfollow/`,
        {
          email : store.state.userInfo.email,
          yourEmail : this.$route.params.email
        }
      ).then(response => {
        this.updateList();
      })
    },
    updateList(){
      // axios.get(`${SERVER_URL}/account/yourpage/`+ this.$route.params.email)
      axios.get(`${SERVER_URL}/account/yourpage/`+ this.userData.email)
        .then(response => {
          console.log(response);
          this.userData.nickname = response.data.nickname;
          this.userData.image = response.data.img;
          this.userData.following = response.data.following;
          this.userData.follower = response.data.follower;
          console.log(this.userData.follower+" "+this.userData.following);
        })
        .catch(error => {
          console.log(error.response)
        })
    },
    onFollower() {
      if(this.openFollower==false){
        this.openFollower = true;
      }else{
        this.openFollower = false
      }
      axios.get(`${SERVER_URL}/account/follow/`, {params: {email: this.userData.email}})
        .then(response => {
          console.log(response)
          this.followers = response.data
        })
        .catch(error =>{
          console.log(error)
          console.log('에러')
        })
    },
    onFollowing() {
      if(this.openFollowing==false){
        this.openFollowing = true;
      }else{
        this.openFollowing = false
      }
      axios.get(`${SERVER_URL}/account/following/`, {params: {email: this.userData.email}})
        .then(response => {
          console.log(response)
          this.followings = response.data
          console.log(this.followings)
        })
        .catch(error =>{
          console.log(error)
        })
    },
    
  },
  created() {
      axios.get(`${SERVER_URL}/account/isfollow/`,
      {
        params:
        {
          email : store.state.userInfo.email,
          yourEmail : this.$route.params.email
        }
      })
      .then(response => {
        console.log(response.data);
        this.isfollow = response.data;
      })

      console.log(this.$route.params.email)
      axios.get(`${SERVER_URL}/account/yourpage/`+ this.$route.params.email)
        .then(response => {
          console.log(response);
          this.userData.email = this.$route.params.email;
          this.userData.nickname = response.data.nickname;
          this.userData.image = response.data.img;
          this.userData.following = response.data.following;
          this.userData.follower = response.data.follower;
          console.log(this.userData.follower+" "+this.userData.following);
        })
        .catch(error => {
          console.log(error.response)
        })
  },
}
</script>
<style scoped>
  .v-dialog {
    height: 70%;
  }
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
  .followEmail {
    margin: 0 !important;
    color: black;
    font-weight: bold;
  }
  .followNick {
    margin: 0 !important;
  }
  .followbtn {
    float: right;
    width: 50px;
  }
</style>

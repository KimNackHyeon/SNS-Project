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
                <router-link to="/user/modifyuser"><v-btn class="myprofil-icon" icon><v-icon>mdi-cog</v-icon></v-btn></router-link>
              </div>
              <v-container style="min-height: 0; padding: 10px" >
                <v-row class="myprofil-boxes" no-gutters>
                  <v-col class="myprofil-box" cols="4">
                    <span>레시피 수</span>
                    <h1>5</h1>
                  </v-col>
                  <v-col class="myprofil-box" cols="4">
                    <span>팔로워</span>
                    <h1>{{userData.follower}}</h1>
                  </v-col>
                  <v-col class="myprofil-box" cols="4" style="border-right: 1px solid lightgray">
                    <span>팔로잉</span>
                    <h1>{{userData.following}}</h1>
                  </v-col>
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
  // mounted(){
  //     if(store.state.kakaoUserInfo.email != null){
  //       this.userinfo = store.state.kakaoUserInfo;
  //     }else{
  //       this.userinfo = store.state.userInfo;
  //     }
  //   },
  data() {
    return {
      userinfo:'',
      userData:{
        nickname:'',
        image:'',
        recipe:"",
        follower:"",
        following:"",
      },
      isfollow : false
    }
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
      axios.get(`${SERVER_URL}/account/yourpage/`+ this.$route.params.email)
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
    }
    
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


      axios.get(`${SERVER_URL}/account/yourpage/`+ this.$route.params.email)
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
}
</script>

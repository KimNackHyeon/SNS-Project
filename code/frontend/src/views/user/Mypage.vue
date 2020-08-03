<template>
  <div class="user">
    <div class="mypage">
      <div class="mypage-body">
        <div class="profil">
          <div style="overflow: hidden; margin: 20px 0;">
            <div class="myphoto"><v-avatar size="100"><img :src="userinfo.profile_image_url" alt="John"></v-avatar></div>
            <div class="myprofil">
              <div style="margin: 10px">
                <h2 class="user-name">{{userinfo.nickname}}</h2>
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
        <div style="margin: 10px;">
          <v-btn color="rgb(160, 212, 105)" style="width: 100%; height: 35px;">팔로우</v-btn>
        </div>
        <div class="myfeed">
          <div class="myprofil-feed">
              <div class="myprofil-text">
                <img src="../../assets/images/pencil.png" alt="my recipe">
                <!-- <i class="fas fa-pencil-alt fa-lg"></i> -->
                <h3>내가 쓴 글</h3>
              </div>
          </div>
          <div class="myprofil-scrap">
            <div class="myprofil-text">
              <img src="../../assets/images/bookmark.png" alt="scrap">
              <!-- <i class="far fa-bookmark"></i> -->
              <h3>스크랩한 글</h3>
            </div>
          </div>
        </div>
        <div class="myrecipe">
          <h3 class="myrecipe-title">내 레시피</h3>
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

// const SERVER_URL = 'http://i3b301.p.ssafy.io:9999/food/api'
const SERVER_URL = 'http://localhost:9999/food/api'

export default {
  mounted(){
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
    },
  data() {
    return {
      userinfo:'',
      userData:{
        recipe:"",
        follower:"",
        following:"",
      },
    }
  },
  methods: {
    // fetchUser() {
    //   axios.get(`${SERVER_URL}/account/mypage/`+ this.userInfo.email
    //   )
    //     .then(response => {
    //       console.log(response)
    //       this.userData = response.data
    //     })
    //     .catch(error => {
    //       console.log(error.response)
    //     })
    // }
  },
  created() {
    if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
      axios.get(`${SERVER_URL}/account/mypage/`+ this.userinfo.email)
        .then(response => {
          console.log(response)
          this.userData.following = response.data.following
          this.userData.follower = response.data.follower
          console.log(this.userData.follower+" "+this.userData.following)
        })
        .catch(error => {
          console.log(error.response)
        })
      console.log(this.kakaoUserInfo);
  },
}
</script>

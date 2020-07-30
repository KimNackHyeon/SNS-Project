<template>
  <div class="user">
    <div class="mypage">
      <div class="mypage-body">
        <div class="profil">
          <div style="overflow: hidden; margin: 20px 0;">
            <div class="myphoto"><v-avatar size="100"><img :src="kakaoUserInfo.profile_image_url" alt="John"></v-avatar></div>
            <div class="myprofil">
              <div style="margin: 10px">
                <h2 class="user-name">{{kakaoUserInfo.nickname}}</h2>
                <span class="myprofil-icon"><i class="fas fa-cog" style="color: gray"></i></span>
              </div>
              <v-container style="min-height: 0; padding: 10px" >
                <v-row class="myprofil-boxes" no-gutters>
                  <v-col class="myprofil-box" cols="4">
                    <span>레시피 수</span>
                    <h1>5</h1>
                  </v-col>
                  <v-col class="myprofil-box" cols="4">
                    <span>팔로우</span>
                    <h1>32</h1>
                  </v-col>
                  <v-col class="myprofil-box" cols="4" style="border-right: 1px solid lightgray">
                    <span>팔로잉</span>
                    <h1>49</h1>
                  </v-col>
                </v-row>
              </v-container>
            </div>
          </div>
          
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

const SERVER_URL = 'http://i3b301.p.ssafy.io:9999/food/api'

export default {
  mounted(){
      this.kakaoUserInfo = store.state.kakaoUserInfo;
    },
  data() {
    return {
      kakaoUserInfo:'',
      userData: [],
    }
  },
  methods: {
    fetchUser() {
      axios.get(`${SERVER_URL}/mypage`)
        .then(response => {
          console.log(response)
          this.userData = response.data
        })
        .catch(error => {
          console.log(error.response)
        })
    }
  },
  created() {
    this.fetchUser()
  },
}
</script>

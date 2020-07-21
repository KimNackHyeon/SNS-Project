<template>
  <div class="user">
    <div class="mypage">
      <div class="header">Header</div>
      <div class="mypage-body">
        <div class="profil">
          <div class="myphoto"><v-avatar size="100"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar></div>
          <div class="myprofil">
            <div style="margin: 10px">
              <h2 class="user-name">사용자 이름</h2>
              <span class="myprofil-icon"><i class="fas fa-cog"></i></span>
            </div>
            <v-container class="grey lighten-5" style="min-height: 0; padding: 10px" >
              <v-row class="myprofil-boxes" no-gutters>
                <v-col class="myprofil-box" cols="4">
                  <span>레시피 수</span>
                  <h2>5</h2>
                </v-col>
                <v-col class="myprofil-box" cols="4">
                  <span>팔로우</span>
                  <h2>32</h2>
                </v-col>
                <v-col class="myprofil-box" cols="4" style="border-right: 1px solid black">
                  <span>팔로잉</span>
                  <h2>49</h2>
                </v-col>
              </v-row>
            </v-container>
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
          <v-container class="grey lighten-5" style="min-height: 0; padding: 10px" >
              <v-row class="" no-gutters style="padding: 0;">
                <v-col class="" cols="4">
                  <img src="" alt="">
                </v-col>
                <v-col class="" cols="4">
                  <img src="" alt="">
                </v-col>
                <v-col class="" cols="4">
                  <img src="" alt="">
                </v-col>
              </v-row>
            </v-container>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";

export default {

}
</script>

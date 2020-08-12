<template>
  <div>
    <v-card flat>
      <v-container fluid style="padding: 0; margin: 0;">
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <div style="border: solid 1px lightgrey">
            <router-link to="/Main">
            <v-btn icon style="margin: 5px">
              <v-icon>mdi-chevron-left</v-icon>
            </v-btn>
            </router-link>
          </div>
          <v-flex>
            <v-toolbar flat style="border: solid 1px lightgrey" height="48px">
              <div class="text-h6 mx-auto">
                공동구매
              </div>
            </v-toolbar>
          </v-flex>
        </v-layout>
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <v-flex>
            <v-toolbar color="rgba(160, 212, 105, 0.5)" flat height="48px">
              <v-checkbox label="거리순 정렬" style="margin-top:18px; margin-right: 18px;">

              </v-checkbox>
              <v-checkbox label="마감임박" style="margin-top:18px">

              </v-checkbox>
            </v-toolbar>
          </v-flex>
          <div style="border: solid 1px lightgrey">
            <v-btn icon style="margin: 5px">
              <v-icon>mdi-magnify</v-icon>
            </v-btn>
          </div>
        </v-layout>
        <div style="padding: 10px; margin: 0; overflow: scroll; height: 480px;" grid-list-lg>
          <v-row dense style="padding: 0;">
            <v-col v-for="(groupBuying, i) in groupBuyings" :key="i" cols="12">
              <router-link :to="`/store/groupbuying/${ groupBuying.no }`">
                <v-card style="padding: 5px;">
                  <v-row style="padding: 0; margin: 0;">
                    <v-col cols="3" style="padding: 0; padding-right: 8px; border-right: solid 1px lightgray;">
                      <img height="80" width="80" padding="60" :src="require(`../../assets/images/food/${groupBuying.food}.png`)" style="border-radius: 5px;">
                    </v-col>
                    <v-col cols="6" class="text-left pl-2" style="padding: 0;">
                      <v-card-text style="padding: 0;">{{ groupBuying.title }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">품목 : {{ groupBuying.food_kor }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">동네 : {{ groupBuying.address }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">마감일 : {{ groupBuying.end_date }}</v-card-text>
                    </v-col>
                    <v-col cols="3" style="padding: 0;">
                      <div v-if="userinfo.email == groupBuying.email">
                        <v-btn style="margin-right: 5px">수정</v-btn>
                        <v-btn>삭제</v-btn>
                      </div>
                      <v-card-text class="text-right" style="font-size: 29px; height: 0px">{{ groupBuying.now_people }}/{{ groupBuying.max_people }}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </router-link>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-card>
    <router-link to="/store/writegroupbuying">
      <div class="writeButton">
        <v-icon color="white">mdi-lead-pencil</v-icon>
        <h4 style="color:whiste; font-size:14px;">글쓰기</h4>
      </div>
    </router-link>
  </div>
</template>

<script>
const SERVER_URL = store.state.SERVER_URL;

import axios from "axios"
import store from '../../vuex/store.js'
export default {
  name: 'Groupbuying',
  data() {
    return {
      groupBuyings: '',
    }
  },
  created(){
    if(store.state.kakaoUserInfo.email != null){
      this.userinfo = store.state.kakaoUserInfo;
    }else{
      this.userinfo = store.state.userInfo;
    }
    // if(userinfo.email == )
    axios.get(`${SERVER_URL}/groupbuying/read`)
      .then(response => {
        console.log(response)
        this.groupBuyings = response.data
        console.log(this.groupBuyings)
      })
      .catch(error => {
      })
  }
}
</script>

<style scoped>
.container {
  min-height: unset !important;
}
.v-application--wrap {
  min-height: unset !important;
}
.writeButton{
  width: 60px;
  height: 60px;
  position: fixed;
  margin-top: 500px;
  margin-left: 284px;
  background-color: rgb(147 203 88);
  z-index: 90;
  border-radius: 30px;
  box-shadow: 7px 7px 10px rgb(0 0 0 / 44%);
  text-align: center;
  padding-top: 7px;;
}

</style>
<template>
  <div style="height: 590px">
    <div style="width:100%; height:40px; border-top: 1px solid rgba(128, 128, 128, 0.15)">
      <div @click="onleft" style="width:40px; height:100%; border-right: 1px solid rgba(128, 128, 128, 0.15); float:left;">
          <v-icon size="30px" style="padding:6px 0px;">mdi-chevron-left</v-icon>
      </div>
      <div style="height:100%; text-align:center; padding-top:8px;">
        <h4>새소식</h4>
      </div>
    </div>
    <div class="likebox" v-for="(alarm, i) in alarms" :key="i">
      <div class="myphoto">
        <v-avatar size="50"><img :src="alarm.image"></v-avatar>
      </div>
      <div class="content" style="display: table;" @click="move(alarm)">
        <div style="display: table-cell; vertical-align: middle;">
            <p style="margin: 0;">{{alarm.content}}</p>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios"
import store from '../../vuex/store.js'

export default {
  data() {
    return {
      alarms:[]
    }
  },
  created(){
    axios.get(`http://localhost:9999/food/api/account/alarm`,{params : {email : store.state.userInfo.email}})
    .then(response => {
      console.log(response);
      response.data.reverse();
      this.alarms = response.data;
    })
  },
  methods: {
    onleft() {
      this.$router.go(-1)
    },
    move(alarm){
      console.log(alarm);
      switch(alarm.type){
        case "2": 
          this.$router.push({name: 'FeedDetail',params: { feedNo : alarm.feedNo }});
          break;
        case "3": 
          this.$router.push({name: 'FeedDetail',params: { feedNo : alarm.feedNo }});
          break;
        default:
      }
    }
  }
}
</script>

<style scoped>
/* 공통 박스 axios에서 오는 type에 따라 배경색 다르게 */
.alarmbox {
  height: 80px;
  background-color: #eee;
  padding: 5px;
}
.likebox {
  height: 80px;
  background-color: #FFCCCC;
  padding: 5px;
  /* border-radius: 4px; */
}
.followbox {
  height: 80px;
  background-color: #D9EFB9;
  padding: 5px;
}
.commentbox {
  height: 80px;
  background-color: #CEEEFF;
  padding: 5px;
}
.myphoto {
  float: left;
  line-height: 70px;
}
.content {
  overflow: hidden;
  /* display: flex; */
  /* align-items: center; */
  height: 70px;
  padding-left: 15px;
  width: 85%;
}
.content span {
  font-size: 13px;
}
</style>
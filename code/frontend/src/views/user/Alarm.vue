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
    <!-- 좋아요 알람 -->
    <div class="likebox">
      <div class="myphoto">
        <v-avatar size="50"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar>
      </div>
      <div class="content" style="display: table;">
        <div style="display: table-cell; vertical-align: middle;">
          <p style="margin: 0;">000님이 000님의 게시글에 좋아요<v-icon color="red" size="18">mdi-heart</v-icon>를 눌렀습니다.</p>
        </div>
      </div>
    </div>
    <!-- 팔로우 알람 -->
    <div class="followbox" style="height: 80px;">
      <div class="myphoto">
        <v-avatar size="50"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar>
      </div>
      <div class="content" style="display: table;">
        <div style="display: table-cell; vertical-align: middle;">
          <p style="margin: 0;">000님이 000님을 팔로우 했습니다.</p>
        </div>
      </div>
    </div>
    <!-- 댓글 알람 -->
    <div class="commentbox" style="height: 80px;">
      <div class="myphoto">
        <v-avatar size="50"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar>
      </div>
      <div class="content" style="display: table;">
        <div style="display: table-cell; vertical-align: middle;">
          <p style="margin: 0;">000님이 000님의 게시글에 댓글을 작성했습니다.</p>
          <p style="margin: 0 5px 0 0; display: inline-block"><v-icon color="blue" size="18">mdi-chat</v-icon>너무 맛있겠다^^</p>
          <p style="margin: 0; display: inline-block">08/20</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
  methods: {
    onleft() {
      this.$router.go(-1)
    },
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
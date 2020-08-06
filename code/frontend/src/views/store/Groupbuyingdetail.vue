<template>
  <div style="height: 100%;">
    <div style="height:48px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/store/groupbuying">
        <v-btn icon color="gray" style="float: left; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
      <div style="display: inline-block; width: 90%; height: 100%; font-size: 17px; text-align: center;">
        <div style="height: 100%; display: flex; justify-content: center; align-items: center;">
          <p style="margin: 0;">공구하기</p>
        </div>
      </div>
    </div>
    <div style="text-align: center; border-bottom: 1px solid lightgray; padding: 5px 0;">
      <h4>♡감자가 좋아♡</h4>
    </div>
    <div style="overflow: hidden; border-bottom: 1px solid lightgray">
      <div style="float: left;">
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">등록일</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">2020/07/14</div>
      </div>
      <div style="float: left;">
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">공구 마감일</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: gray;">2020/08/01</div>
      </div>
      <div style="float: right;">
        <div style="font-size: 10px; display: inline-block; margin-right: 20px; color: gray;">작성자</div>
        <div style="font-size: 10px; display: inline-block; margin-right: 20px; color: gray;">mamtte</div>
      </div>
    </div>
    <div style="text-align: center; border-bottom: 1px solid lightgray; padding: 0px;">
      <p style="margin: 0px;">대전광역시 유성구 덕명동</p>
    </div>
    <div style="overflow: hidden; background: black;">
      <div style="float: left;">
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: white; padding: 10px 0;">1일전</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: orange;">감자/수미(등급:중품)</div>
        <div style="font-size: 10px; display: inline-block; margin-left: 10px; color: white;">소매 개당 가격</div>
      </div>
      <div style="float: right;">
        <div style="font-size: 6px; display: inline-block; margin-right: 20px; color: gray; padding-top: 20px;">출처: 농산물 유통정보 KAMIS</div>
      </div>
    </div>
    <div style="text-align: center; border-bottom: 1px solid lightgray; padding: 5px 0; background: rgb(224, 241, 206);">
      <h3 style="color: gray;">현재 5명 중 4명이 모여</h3>
      <div style="font-size: 30px; display: inline-block; margin-left: 10px; color: red;">1 </div>
      <div style="font-size: 30px; display: inline-block; margin-left: 10px; color: gray;">자리 남았습니다.</div>
    </div>
    <div style="padding: 10px; height: 280px">
      저는 감자를 좋아해요! <br>
      하지만 감자 한 박스를 사면 밑에 감자는 싹이나서 <br>
      버리게 되더라고요 ㅠㅠ <br>
      같이 사고싶어요!! 많이 모여주세요.
    </div>
    <div>
      <v-btn
        color="rgb(160, 212, 105)" 
        style="width: 100%; height: 20px; color: white; font-size: 16px; padding: 0px 30px; border-radius: 0px; margin-bottom: 2px;" 
        >
        <v-icon style="margin-right: 5px">mdi-link</v-icon>제품 보러가기
      </v-btn>
    </div>
    <div>
      <div style="float: left;">
        <v-btn 
          color="rgb(160, 212, 105)" 
          style="width: 100%; height: 50px; color: white; font-size: 22px; padding: 0px 30px; border-radius: 0px;" 
          >
          <v-icon style="margin-right: 5px">mdi-comment-multiple-outline</v-icon>문의하기
          </v-btn>
      </div>
      <div style="float: right;">
        <v-btn 
          color="rgb(160, 212, 105)" 
          style="width: 100%; height: 50px; color: white; font-size: 22px; padding: 0px 30px; border-radius: 0px;" 
          >
          <v-icon style="margin-right: 5px">mdi-account-multiple-outline</v-icon>참가하기
          </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    param: function() {
      return this.$route.params
    }
  },
  created() {
    const id = this.$route.params.id
    if (id === undefined) {
      // this.$router.push('/store/groupbuying')
      this.$router.go(-1)
    }
  },
}
</script>

<style>

</style>
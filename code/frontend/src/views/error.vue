<template>
  <div style="width:100%; height:100%">
      <div style="width:80%; height:192px; margin:auto; margin-top: 156px;">
          <img src="../assets/images/ERROR.png" style="width:100%;">
            <h1 style="margin: 4px 0px 12px 0px;">에러가 발생했어요!</h1>
            <!-- <router-link to="/"> -->
              <v-btn width="100%" color="rgb(160,212,105)" @click="goback">돌아가기</v-btn>
            <!-- </router-link> -->
      </div>
  </div>
</template>

<script>
export default {
  methods:{
    goback(){
      this.$router.go(-1);
    }
  }
}
</script>

<style>

</style>
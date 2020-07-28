<template>
<div>
  <div>
  <v-carousel height="300px" hide-delimiters cycle vertical-delimiters="true" interval="2000" show-arrows-on-hover = "true">
    <v-carousel-item  cycle 
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      
      hide-delimiters
      width="360px"
      height="300px"
    ></v-carousel-item>
  </v-carousel>
  </div> <!-- 광고판 끝 -->
  <div style="width:100%; height:200px; margin:1%;"> <!-- 메인 네비게이터-->
    <router-link to="/store/marketplace"><div class="navbox" style="border-left: 0px solid #8080803b;">
      <v-icon color="rgb(123, 190, 52)" size="30px" style="margin:5px;">mdi-basket-outline</v-icon>
      <h4>우리 동네</h4>
      <h4>장터</h4>
    </div></router-link>
    <router-link to="/store/groupbuying"><div class="navbox">
      <v-icon color="rgb(123, 190, 52)" size="30px" style="margin:5px;"> mdi-cart-outline</v-icon>
      <h4>공동</h4>
      <h4>구매</h4>
    </div>
    </router-link>
    <router-link to="/feed/main"><div class="navbox" >
      <v-icon color="rgb(123, 190, 52)" size="30px" style="margin:5px;">mdi-chef-hat</v-icon>
      <h4>레시피</h4>
      <h4>찾기</h4>
    </div>
    </router-link>
  </div>
  </div>
</template>

<script>
   export default {
    data () {
      return {
        items: [
          {
            src: 'http://blogfiles.naver.net/MjAxNzA4MTBfMjU3/MDAxNTAyMjk0NTY0NjM4.PgCFLkJ-2gU3G2Fi8dpxAi4sXgzbq-fWJ5GH3fFRLmEg.wyLGBUA21aGglsiWM8MxChVcjKcvI6lGegMGsr3juLcg.JPEG.shin00512/%B1%CD%C2%FA%C0%BB_%B6%A7_%B5%FC_%C1%C1%C0%BA_%C3%CA%B0%A3%B4%DC_%B7%B9%BD%C3%C7%C7_%B8%F0%C0%BD1.jpg',
          },
          {
            src: 'https://image7.coupangcdn.com/image/bannerunit/bannerunit_2243356c-de84-4983-aa65-695a41d83356.jpg',
          }
        ],
      }
    },
  }
</script>


<style>
body{
  overflow: hidden;
}
.navbox{
width:33%;
height:110px;
margin-top:60px;
float:left;
text-align:center;
border-left: 1px solid #8080803b;
padding: 13px;
}
h4{
  font-weight: 1000;
  font-size: 17px;
}
</style>
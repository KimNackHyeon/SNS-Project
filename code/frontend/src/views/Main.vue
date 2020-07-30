<template>
<div style="width:100%; height:100%;">
  <div>
    <div class="introduce">
      <div class="introduceIMG"></div>
      <div style="height:112px; width:186px; float:left; text-align:right;
    padding: 28px 16px; font-size:25px; color:white; text-shadow: #80808075 1px 1px 1px;;  ">
        <h4>남는 식재료</h4>
        <h4>더이상 버리지 마세요</h4>
      </div>
    </div>
    <div style=" box-shadow: 0px 0px 10px #00000078 ;">
  <v-carousel style="margin:20px 0px; " height="300px" hide-delimiters cycle vertical-delimiters="true" interval="2000" show-arrows-on-hover = "true">
    <v-carousel-item  cycle 
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      :link="item.link"
      
      hide-delimiters
      width="360px"
      height="300px"
    ></v-carousel-item>
  </v-carousel>
  </div> 
  </div><!-- 광고판 끝 -->
  <div style="width:100%; height:200px; margin:1%;"> <!-- 메인 네비게이터-->
    <router-link to="/store/marketplace"><div class="navbox" style="border-left: 0px solid #8080803b;">
      <v-icon color="rgb(198 199 197)" size="21px" style="margin:5px;">mdi-basket-outline</v-icon>
      <h4>우리 동네</h4>
      <h4>장터</h4>
    </div></router-link>
    <router-link to="/store/groupbuying"><div class="navbox">
      <v-icon color="rgb(198 199 197)" size="21px" style="margin:5px;"> mdi-cart-outline</v-icon>
      <h4>공동</h4>
      <h4>구매</h4>
    </div>
    </router-link>
    <router-link to="/feed/main"><div class="navbox" >
      <v-icon color="rgb(198 199 197)" size="21px" style="margin:5px;">mdi-chef-hat</v-icon>
      <h4>레시피</h4>
      <h4>찾기</h4>
    </div>
    </router-link>
  </div>
  </div>
</template>

<script>
// store.commit('incrememt');
// console.log(store.state.count);
   export default {
    data () {
      return {
        items: [
          {
            src: 'http://blogfiles.naver.net/MjAxNzA4MTBfMjU3/MDAxNTAyMjk0NTY0NjM4.PgCFLkJ-2gU3G2Fi8dpxAi4sXgzbq-fWJ5GH3fFRLmEg.wyLGBUA21aGglsiWM8MxChVcjKcvI6lGegMGsr3juLcg.JPEG.shin00512/%B1%CD%C2%FA%C0%BB_%B6%A7_%B5%FC_%C1%C1%C0%BA_%C3%CA%B0%A3%B4%DC_%B7%B9%BD%C3%C7%C7_%B8%F0%C0%BD1.jpg',
            link:'http://blogfiles.naver.net/MjAxNzA4MTBfMjU3/MDAxNTAyMjk0NTY0NjM4.PgCFLkJ-2gU3G2Fi8dpxAi4sXgzbq-fWJ5GH3fFRLmEg.wyLGBUA21aGglsiWM8MxChVcjKcvI6lGegMGsr3juLcg.JPEG.shin00512'
          },
          {
            src:'https://www.gompyo.net:444/upld/bd/2020/04/09/thmb_img_20200409173757_3787.jpg',
            link: 'http://blogfiles.naver.net/MjAxNzA4MTBfMjU3/MDAxNTAyMjk0NTY0NjM4.PgCFLkJ-2gU3G2Fi8dpxAi4sXgzbq-fWJ5GH3fFRLmEg.wyLGBUA21aGglsiWM8MxChVcjKcvI6lGegMGsr3juLcg.JPEG.shin00512'
          },
          // {
          //   src: 'https://image7.coupangcdn.com/image/bannerunit/bannerunit_2243356c-de84-4983-aa65-695a41d83356.jpg',
          //   link: 'https:'
          // },
          {
            src:'https://image7.coupangcdn.com/image/bannerunit/bannerunit_a277f0a2-707b-4f7f-9d21-183b9e4a90c1.jpg',
            link:'https://www.coupang.com/vp/products/306157806?vendorItemId=5369794953&isAddedCart='
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
    width: 33%;
    height: 110px;
    margin-top: 0;
    float: left;
    text-align: center;
    /* border-left: 1px solid #8080803b; */
    padding: 13px;
    box-shadow: 0px 0px 4px #7aa37e38;

}
h4{
  font-weight: 1000;
  font-size: 17px;
}
.introduce{
      margin:5px 0px;
      height:112px;
      width:100%;
      background-color:rgb(129 203 104); 
}
.introduceIMG{
     background-image: url(/img/login_backgroundimg.3ff816a3.gif);
    height: 112px;
    width: 174px;
    background-size: 109%;
    background-position-x: -14px;
    background-position-y: -134px;
    float: left;
}
</style>
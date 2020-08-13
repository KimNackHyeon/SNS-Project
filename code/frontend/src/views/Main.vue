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
  <v-carousel style="margin:20px 0px; " height="300px" hide-delimiters cycle vertical-delimiters="true" interval="2000" show-arrows-on-hover>
    <v-carousel-item  cycle 
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      hide-delimiters
      width="360px"
      height="300px"
      @click="openLink(item.link)"
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

   export default {
    data () {
      return {
        items: [
          {
            src:'http://blogfiles.naver.net/MjAxNzA0MjRfMTcy/MDAxNDkzMDEzMjQ1MjEx.ovTdJhgqOsB86TVABuLhxdz98kfWOPNvq1eJNI32Qt4g.uqgB-iHZo_tZ4Mt67nRI5YVe3rb3bkYet9bFl4Yuczsg.JPEG.woorikangsan/1.jpg',
            link:'https://blog.naver.com/woorikangsan/220990801581'
          },
          {
            src:'https://www.gompyo.net:444/upld/bd/2020/04/09/thmb_img_20200409173757_3787.jpg',
            link:'https://www.gompyo.net:444/mil_recipe_list.php'
          },
          {
            src: 'http://image.auction.co.kr/itemimage/1d/7e/cf/1d7ecf24d3.jpg',
            link:'http://itempage3.auction.co.kr/detailview.aspx?ItemNo=B443993038&listqs=catetab%3d4%26class%3dCorner.CategoryBest%26listorder%3d1&listtitle=%ba%a3%bd%ba%c6%ae100&frm2=through'
          },
          // {
          //   src: 'https://image7.coupangcdn.com/image/bannerunit/bannerunit_2243356c-de84-4983-aa65-695a41d83356.jpg',
          //   link: 'https:'
          // },
          {
            src:'https://image9.coupangcdn.com/image/displayitem/displayitem_8ea96a48-bb2e-412e-b9a9-97f5f6e95b5c.png',
            link:'https://www.coupang.com/np/promotion/77260' 
             }
        ],
      }
    },
    methods:{
      openLink(link){
      var newWindow = window.open("about:blank");
      newWindow.location.href = link;
      }
    }
  }
</script>


<style scoped>
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
.v-image__image--cover{
  background-size: contain;
}
</style>
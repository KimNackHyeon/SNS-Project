<template>
    <div style="height:100%; width:100%;">
      <div style="width:100%; height:40px; border-top:1px solid #0000001f; border-bottom: 1px solid #0000001f;">
        <router-link to="/feed/main">
        <div style="width:50px; height:100%; border-right:1px solid #0000001f; float:left; padding:4px 6px;">
          <v-icon size="35px">mdi-chevron-left</v-icon>
        </div>
        </router-link>
        <div style="width:260px; height:100%; float:left; text-align:center; padding-top:8px;">
          <h4> 레시피 작성</h4>
        </div>
      </div>  <!-- end of header -->

        <div style="width:100%; height:0px; border-top: 1px solid rgba(128, 128, 128, 0.15); border-bottom: 1px solid rgba(128, 128, 128, 0.15); text-align:center; margin-bottom:10px; margin-top:15px;">
           <div id="app">
  <p><a href="https://github.com/ahastudio/CodingLife/tree/master/20170406/vuejs" target="_blank">https://github.com/ahastudio/CodingLife/tree/master/20170406/vuejs</a></p>
  <carousel-component>
    <carousel-item>
      <h2>#1</h2>
      <img src="https://octodex.github.com/images/original.png" alt="">
    </carousel-item>
    <carousel-item>
      <h2>#2</h2>
      <img src="https://octodex.github.com/images/octobiwan.jpg" alt="">
    </carousel-item>
    <carousel-item>
      <h2>#3</h2>
      <img src="https://octodex.github.com/images/daftpunktocat-thomas.gif" alt="">
    </carousel-item>
    <carousel-item>
      <h2>#4</h2>
      <img src="https://octodex.github.com/images/adventure-cat.png" alt="">
    </carousel-item>
  </carousel-component>
</div>

        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import Vuex from 'vuex'
import Vueditor from 'vueditor'

import 'vueditor/dist/style/vueditor.min.css'

// your config here

Vue.use(Vuex);
    
// create a root instance
export default {
    data () {
      return {
        items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: '',
          },
          {
            src: '',
          },
          {
            src: '',
          },
          {
            src: '',
          },
          {
            src: '',
          },
          {
            src: '',
          },
        ],
      }
    },
  }
</script>

<style scoped>
#app {
  margin: 10px auto;
  width: 80%;
}

.carousel-component {
  position: relative;
  overflow: hidden;
}

.carousel-page {
  visibility: hidden;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  transition: 0.5s;
  padding: 10px;
  border: 1px solid #EEE;
}
.carousel-page  h2 {
    margin: 0;
  }

.carousel-page  img {
    width: 300px;
  }


.carousel-page.active {
  visibility: visible;
  position: static;
}

.carousel-nav-prev,
.carousel-nav-next {
  position: absolute;
  top: 50%;
  transform: translate(0, -50%);
  display: block;
  width: 50px;
  height: 50px;
  border: 0;
  background: rgba(0, 0, 0, 0.5);
  color: #FFF;
  cursor: pointer;
}

.carousel-nav-prev {
  left: 5px;
}

.carousel-nav-next {
  right: 5px;
}
</style>
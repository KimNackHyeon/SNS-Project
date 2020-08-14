<template>
  <div id="app" style="width:100%;     height: 100%;">
    <div style="width: 100%;
    height: 40px;
    border-top: 1px solid rgba(128, 128, 128, 0.4);
    padding-right: 35px;
    text-align: center;
    font-size: 21px;">
      <div @click="goback" style="padding: 2px;width:40px; float:left; height:100%;border-right:1px solid #80808066;"><v-icon size="35px">mdi-chevron-left</v-icon></div>
      지도로 보기
    </div>
    <Map />
  </div>
</template>

<script>
import Map from './Map';
export default {
  name: 'App',
  components: {
    Map
  },
  methods:{
    goback(){
      window.history.go(-1);
    }
  }
}
</script>
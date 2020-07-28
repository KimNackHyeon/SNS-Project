<template>
    <div style="height:100%; width:100%;">
        <div style="width:100%; height:40px; border-top: 1px solid rgba(128, 128, 128, 0.15); border-bottom: 1px solid rgba(128, 128, 128, 0.15); text-align:center; margin-bottom:10px; ">
            <router-link to="/feed/main">
                <div style="width:50px; height:100%;border-right: 1px solid rgba(128, 128, 128, 0.15); float:left;">
                    <v-icon size="30px" style="padding:6px 0px;">mdi-chevron-left</v-icon>
                </div>
            </router-link>
            <div style="width:270px;height:100%; text-align:center; float:left; padding-top:8px;">
            <h4>글쓰기</h4>
            </div>
            <div style="width:40px; height:100%; float:left;">
            </div>
        </div>
        <div style="height:400px; width:100%;margin:auto;">
         <Vueditor></Vueditor>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import Vuex from 'vuex'
import Vueditor from 'vueditor'

import 'vueditor/dist/style/vueditor.min.css'

// your config here

let config = {
  toolbar: [
    'removeFormat', 'undo', '|', 'elements', 'fontName', 'fontSize', 'foreColor', 'backColor', 'divider',
    'bold', 'italic', 'underline', 'strikeThrough', 'links', 'divider',
    'divider', 'justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull', 'indent', 'outdent',
    'insertOrderedList', 'insertUnorderedList', '|', 'picture', 'tables', '|', 'switchView'
  ],
  fontName: [
    {val: 'arial black'}, 
    {val: 'times new roman'}, 
    {val: 'Courier New'}
  ],
  fontSize: ['12px', '14px', '16px', '18px', '0.8rem', '1.0rem', '1.2rem', '1.5rem', '2.0rem'],
  uploadUrl: ''
};

Vue.use(Vuex);
Vue.use(Vueditor, config);
    
// create a root instance
new Vue({
  el: '#editorContainer'
});
export default {
}
</script>

<style>
.vueditor{
    margin-right: 10px;
    margin-left: 10px;
}
._2T9WD2D47RUUwZfCP40fYJ_0{
 width:300px;
}
img{
    width:200px;
    height:200px;
}
</style>
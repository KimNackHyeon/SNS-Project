<template>
<div style="height:100%; width:100%;">
    <div style="width:100%; height:80px; border-top: 1px solid rgba(128, 128, 128, 0.15); border-bottom: 1px solid rgba(128, 128, 128, 0.15); text-align:center; margin-bottom:-9px; ">
          <div style="width:100%; height:50%">
            <router-link to="/feed/main">
                <div style="width:50px; height:100%;border-right: 1px solid rgba(128, 128, 128, 0.15); float:left;">
                    <v-icon size="30px" style="padding:6px 0px;">mdi-chevron-left</v-icon>
                </div>
            </router-link>
            <div style="width:270px;height:100%; text-align:center; float:left; padding-top:8px;">
            <h4>글쓰기</h4>
            </div>
            <div style="width:40px; height:100%; float:left; ">
            </div>
          </div>
          <div style="width:100%; height:50%; border-top: 1px solid rgba(128, 128, 128, 0.15); overflow:hidden; padding:5px;">
            <textarea placeholder="제목" style="width:100%; height:100%; paddng:5px; text-align:center; font-size:19px; font-weight:700; resize: none; overflow:hidden;"  v-model="title"></textarea>
          </div>
        </div><!-- end of head -->
    
    <div class="addFoodBtn" @click="openAddFood"> <!-- 재료 등록 -->
      <img src="../../assets/images/fruit.png" style="width:35px; height:35px;">
      <h4>재료추가</h4>
    </div><!-- end of 재료 등록 -->
    <div class="addFood">
      <div @click="closeAddFood" style="width:40px; height:40px;position:fixed;"><v-icon style="margin-left:330px;">mdi-close</v-icon></div>
      <div style="width:100%; height:20px;"></div>
      <search-food></search-food>
    </div>
    <div class="carousel-container" style="height:480px; width:100%;">
    <div class="carousel-slide" style="display: flex; overflow: hidden; margin-left:11px;">
  <div v-for="(img,index) in items" :key="index" style="width:300px; height:450px; margin:20px; margin-top:20px; box-shadow: #80808066 1px 1px 8px; float:left;">
    <div style="width:300px; height:300px; margin:auto;">
      <div @click="deleteRecipe(index)" style="position:fixed; width:30px; height:30px; margin-left:270px;"><v-icon size="30px">mdi-close</v-icon></div>
        <div v-if="img.imageUrl == null" style="width:100%; height:100%; background-color:#e0e0e0d9;">
            <div class="fileBox">
                <label for="ex_file"><v-icon size="100px">mdi-camera-plus</v-icon></label>
                <input id="ex_file" type="file" accept="image/*" @change="onChange($event,index)" style="width:150px; height:150px;"/>
            </div>
        </div>
        <div v-if="img.imageUrl != null" id="preview" style="width:100%; height:100%;">
            <img style="width:300px; height:300px;"  :src="img.imageUrl" />
        </div>
    </div>
    <div style="width:300px; height:150px; border-top: 1px solid #80808063;">
        <textarea style="width:100%; height:100%" v-model="items[index].desc" placeholder="레시피를 적어보세요."></textarea>
    </div>
</div>

<div class="plusBtnArea">
 <div class="plusBtn" @click="addRecipe"><v-icon size="100px">mdi-plus</v-icon></div>
</div> <!-- end of 추가버튼 -->

</div> <!-- end of carousel-slide -->
</div> <!-- end of carousel-container -->
<div style="height: 45px;
    width: 100%;
    background-color: rgb(160,212,105); text-align:center; padding:5px;">
<h3>레시피 등록</h3>
</div>
<div>
  <div class="toLeftBtn" @click="clicktoLeftBtn"><v-icon color="white" size="47px">mdi-chevron-left</v-icon></div>
  <div class="toRightBtn" @click="clicktoRightBtn"><v-icon color="white" size="47px">mdi-chevron-right</v-icon></div>
</div>
</div>
</template>


<script>
import $ from 'jquery'
import axios from 'axios'
import store from '../../vuex/store.js'
import searchFood from '../searchFood.vue'

const SERVER_URL = "http://i3b301.p.ssafy.io:9999/food/api";

export default {
  thisPage:1,
  components:{searchFood},
  name: 'imageUpload',
  userinfo:'',
  data() {
    return {
      title:'',
      item:{
          //...
          imageUrl: null,
          desc:null
      },
      ingradient:[
        {food:'apple', amount:'2'},
        {food:'banana', amount:'1'},
        {food:'carrot', amount:'1/4'},
      ],
      tags:[
        {tag:'한식'},
        {tag:'베이킹'}
      ],
        items:[
        {    //...작성자, 재료, 글작성 일, items, tag,제목
          imageUrl: null,
          desc:null
        },
        
      ],
    }
  },
  methods: {
    onChange(e,index) {
      const file = e.target.files[0];
    //   this.item.imageUrl = URL.createObjectURL(file);
    this.$set(this.items[index],'imageUrl',URL.createObjectURL(file));
      
    },
    addRecipe(){
        this.items.push(
          {imageUrl: null,
          desc:null});
        if(this.items.length >= 10){
          $('.plusBtnArea').css('display','none');
        }
        if(this.items.length <=9){
          $('.plusBtnArea').css('display','unset');
        }
        console.log(this.item);
        console.log(this.items);
    },
    clicktoLeftBtn(){
      var thisMarginStr = $('.carousel-slide').css('margin-left');
      var thisMargin = Number(thisMarginStr.split('p')[0]);
      if(thisMargin <11){
        var afterMargin = (thisMargin + 340)+'px';

      }
      $('.carousel-slide').css('margin-left',afterMargin);
      // transform:translateX(10px); 
      console.log(this.item);
        console.log(this.items);
    },
    clicktoRightBtn(){
      var thisMarginStr = $('.carousel-slide').css('margin-left');
      var thisMargin = Number(thisMarginStr.split('p')[0]);
      var afterMargin = (thisMargin - 340)+'px';
      $('.carousel-slide').css('margin-left',afterMargin);
      console.log(this.item);
        console.log(this.items);
    },
    closeAddFood(){
      $('.addFood').css('display','none');
    },
    openAddFood(){
      $('.addFood').css('display','unset');
    },
    deleteRecipe(index){
      this.items.splice(index);
    },
    whiteReciptComplete(){
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
        console.log(this.userinfo);
      }else{
        this.userinfo = store.state.userInfo;
      }
      const recipe = {  //...작성자, 재료, 글작성 일, items, tag,제목
        title: this.title,
        author : this.userinfo.email,
        food : this.ingradient,
        recipes : this.items,
        tags : this.tags,

      }
      axios
        .post(`${SERVER_URL}/feed/write`,recipe)
        .then((response)=>{
          console.log(response);
          this.$router.push("/feed/view");
        })
        .catch((error)=>{
          console.log(error.response);
        });
    }
  }
} 
</script>
<style scoped>
.addFoodBtn{
    width: 80px;
    height: 80px;
    position: fixed;
    background-color: white;
    border-radius: 15px;
    box-shadow: 1px 1px 7px #00000052;
    margin-top: 16px;
    padding: 12px 0px;
    text-align: center;
}

.addFood{
 width: 360px;
    height: 510px;
    background-color: white;
    position: fixed;
    z-index: 101;
    margin-top: 12px;
    display:none;
}

.toLeftBtn{
  width: 50px;
    height: 50px;
    background-color: #00000070;
    border-radius: 10px;
    color: white;
    position: absolute;
    left: 0;
    /* margin-left: auto; */
    margin-top: -362px;
}

.toRightBtn{
  width: 50px;
    height: 50px;
    background-color: #00000070;
    border-radius: 10px;
    color: white;
    position: absolute;
    right: 0;
    /* margin-left: auto; */
    margin-top: -362px;
}
.fileBox label {
 width: 200px;
    height: 200px;
    margin: 47px;
    display: inline-block;
    color: #fff;
    font-size: inherit;
    background-color: #ffffff5c;
    border-radius: 150px;
    cursor: pointer;
    padding: 43px;
}

.plusBtn{
   width: 150px;
    height: 150px;
    background-color: rgb(173 222 122 / 85%);
    margin: 100px 19px;
    margin-right: 108px;
    border-radius: 75px;
    -webkit-box-shadow: 1px 1px 10px #0000005e;
    box-shadow: 1px 1px 10px #0000005e;
    text-align: center;
    padding: 24px;
}
.plusBtnArea{
    width: 240px;
    height: 450px;
    margin: 20px;
    margin-right: 149px;
    float: left;
}

h3{
    font-size: 22px;
    font-weight: 550;
    color: white;
    text-shadow: 1px 1px 4px #0000004f;
}

.fileBox label:active {
 color: gray;
}

.fileBox input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}
</style>
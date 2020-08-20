<template>
  <div class="writegroupbuying" style="height: 100%">
    <div style="width:100%; height:40px; border-top: 1px solid rgba(128, 128, 128, 0.15); border-bottom: 1px solid rgba(128, 128, 128, 0.15)">
      <router-link to="/store/groupbuying">
        <div style="width:40px; height:100%;border-right: 1px solid rgba(128, 128, 128, 0.15); float:left;">
            <v-icon size="30px" style="padding:6px 0px;">mdi-chevron-left</v-icon>
        </div>
      </router-link>
      <div style="height:100%; text-align:center; padding-top:8px;">
        <h4>공동구매 글쓰기</h4>
      </div>
    </div>
    <!-- 제목 -->
    <!-- <div style="width:100%; height:40px; border-top: 1px solid rgba(128, 128, 128, 0.15); border-bottom: 1px solid rgba(128, 128, 128, 0.15); overflow:hidden; padding:5px;">
      <textarea placeholder="제목을 입력해주세요" style="width:100%; height:100%; paddng:5px; text-align:center; font-size:19px; font-weight:700; resize: none; overflow:hidden;"  v-model="title"></textarea>
    </div> -->
    <!-- 글작성 본문 -->
    <div style="padding: 10px 20px">
      <!-- 제목 -->
      <div style="margin: 10px 0">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 40px">제목</span>
        </div>
        <div style="overflow: hidden;">
          <input v-model="title" class="titleinput" type="text" placeholder="제목을 입력해주세요." style="float:left; width: 100%; height: 40px; padding: 0 10px;">
        </div>
      </div>
      <!-- 품목 -->
      <div style="margin: 10px 0">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 40px">품목</span>
        </div>
        <div style="overflow: hidden;">
          <input type="text" v-model="food.name_kor" @click="getFood" class="setFood" placeholder="ex) 양파" style="float:left; width: 100%; height: 40px; text-align: center; font-size: 15px;">
        </div>
        <v-dialog v-model="dialog" scrollable width= "100%" class="adressDialog">
          <v-card class="foodDialog">
            <v-card-title >
              <!-- 품목 검색 -->
              <div style="width:100%;"> 
                <div class="search" >
                  <input class="s" placeholder="음식재료 이름 검색" v-on:input="searchQuery=$event.target.value" style="text-align:left;">
                  <ul style="display:none;" tabindex="3" >
                    <li tabindex="24" 
                        v-for="(el, index) in filterList" 
                        :key="index"
                        style="z-index:22;"
                        >
                      <span>{{ el.name_kor }}</span>
                    </li>
                  </ul>
                </div>
              </div>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <div style=" z-index:20;">
                <div @click="chooseComplete(food)" class="card" v-for="(food,index) in filterListImg" :key="index">
                  <div>
                    <img style="margin:10px auto 5px auto;width:60px; height:auto; font-size:20px;" v-bind:src="require(`../../assets/images/food/${food.img}.png`)"/>
                  </div>
                  <div>
                    {{ food.name_kor }}
                  </div>
                </div>
                <div style="width:100%;height:100%;" v-if="filterListImg.length==0">
          <div style="width:80%; height:50%;text-align:center;margin-top:50px;">
            <h4>해당 음식이</h4>  <h4>아직 등록되지 않았어요</h4> <h4>기타 이미지로 등록해주세요.</h4>
            <img src="../../assets/images/fruit.png" style="width:80px;">
            <div style="width:150px; margin:auto;">
            <h4 style="float:left;">이름 : </h4><input v-model="etcName" type="text" class="inputText" style=" float:left;width: 80px; height: 35px; text-align: center;">
            <v-btn @click="chooseComplete({name:'etc',
            name_kor:etcName,
            img:'etc' })" width="100%" style="margin-top:30px;" color="rgb(160,212,105)">등록하기</v-btn>
          </div>
          </div>
        </div>
              </div>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="dialog = false">닫기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <!-- 마감일 -->
      <div style="margin: 10px 0">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 40px">마감일</span>
        </div>
        <div>
          <v-menu
            ref="menu1"
            v-model="menu1"
            :close-on-content-click="false"
            transition="scale-transition"
            offset-y
            max-width="290px"
            min-width="290px"
          >
          <!-- prepend-inner-icon="mdi-calendar-check" -->
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dateFormatted"
                persistent-hint
                v-bind="attrs"
                @blur="date = parseDate(dateFormatted)"
                @click="removedate"
                v-on="on"
                color="#a0d469"
                hide-details
                outlined
                placeholder="YYYY/MM/DD"
              ></v-text-field>
            </template>
            <v-date-picker v-model="date" no-title @input="menu1 = false" color="#a0d469"></v-date-picker>
          </v-menu>
        </div>
      </div>
      
      <!-- 참여 인원 -->
      <div style="margin: 10px 0">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 40px">참여인원</span>
        </div>
        <div style="overflow: hidden;">
          <input v-model="numberPeople" class="cntPeople" type="text" placeholder="숫자만 입력해주세요." style="float:left; width: 100%; height: 40px; ">
        </div>
      </div>
      <!-- 파일 링크 -->
      <div style="margin: 10px 0">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 40px">파일</span>
        </div>
        <div style="overflow: hidden;">
          <input v-model="fileLink" class="fileinput" type="text" placeholder="ex) https://www.coupang.com/" style="float:left; width: 100%; height: 40px;">
        </div>
      </div>
      <!-- 글 내용 -->
      <div style="margin: 10px 0">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 30px">내용</span>
        </div>
        <div style="overflow: hidden;">
          <textarea v-model="content" placeholder="공동구매글에 들어갈 내용을 적어주세요." class="contentinput"></textarea>
        </div>
      </div>
    </div>
    <!-- 글 작성 버튼 -->
    <div>
      <v-btn 
      color="rgb(160, 212, 105)" 
      :style="{width:frameSize.x+'px'}" style=" height: 50px; color: white; font-size: 18px; position:fixed; bottom: 0; border-radius: unset;" 
      @click="onCreate()"
      >
      작성하기
      </v-btn>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import "../../components/css/store/writegroupbuying.scss";
import axios from "axios"
import {foods} from '../Food/Foods.js'
import store from '../../vuex/store.js'
import Swal from 'sweetalert2'


const SERVER_URL = store.state.SERVER_URL;

export default {
  data() {
    return {
       frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
      userinfo:'',
      title: '',
      food: '',
      numberPeople: '',
      oknumPeople: false,
      fileLink: '',
      content: '',
      dialog: false,
      selectedFood: '',
      // 마감일
      date: '',
      dateFormatted: '',
      menu1: false,
      // 품목 선택
      searchQuery: '',
      names : foods,
    }
  },
  mounted(){
    this.onResize();
  },
  watch: {
    date (val) {
      this.dateFormatted = this.formatDate(this.date)
    },
    numberPeople () {
      if (Number(this.numberPeople) == 1){
        this.oknumPeople = false
        // Swal.fire({
        //   title: '참여인원 수를 확인해 주세요',
        //   text: '참여인원은 2명 이상부터 가능합니다.',
        // })
      }
      else {
        this.oknumPeople = true;
      }
      
    }
  },
  methods: {
    onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
    },
    getFood(){
      // // console.log('getFood 실행')
      if (this.dialog === false) {
        this.dialog = true
        // // console.log('false')
      }
      else {
        this.dialog = false
        // // console.log('true')
      }
    },
    chooseComplete:function(food){
      // alert(food.name_kor+'을(를) 선택했습니다.');
      this.dialog = false
      // $('.setFood').text(food.name_kor);
      this.food = food
      this.searchQuery = ''
      document.querySelector('.s').value = '';
    },
    formatDate (date) {
        if (!date) return null
        const [year, month, day] = date.split('-')
        return `${year}/${month}/${day}`
    },
    parseDate (date) {
      if (!date) return null
      const [year, month, day] = date.split('/')
      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
    },
    removedate() {
      this.dateFormatted = ''
    },
    onCreate(){
      // 모든 항목 다 작성되었는지 검사
      if (this.title && this.food && this.date && this.numberPeople && this.fileLink && this.content && this.oknumPeople && this.userinfo.address) {
        const sendContent = this.content.replace(/\n/g, '^')
        axios.post(`https://i3b301.p.ssafy.io:9999/food/api/groupbuying/create`, {title:this.title, food:this.food.name, food_kor:this.food.name_kor, address:this.userinfo.address, end_date:this.date, max_people:this.numberPeople, now_people:0, link:this.fileLink, nickname:this.userinfo.nickname, email:this.userinfo.email, content:sendContent})
          .then(response => {
            Swal.fire({
            title: '등록이 완료되었습니다.',
          })
            this.$router.go(-1);
          })
          .catch(error => {
          })
      }
      else if (this.title && this.food && this.date && this.numberPeople && this.fileLink && this.content && !this.oknumPeople && this.userinfo.address) {
        Swal.fire({
          title: '참여인원 수를 확인해 주세요',
          text: '참여인원은 2명 이상부터 가능합니다.',
        })
      }
      else if (this.title && this.food && this.date && this.numberPeople && this.fileLink && this.content && this.oknumPeople && !this.userinfo.address) {
        Swal.fire({
            title: '필수 정보가 부족합니다.',
            text: "회원정보수정에서 주소를 입력해주세요.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '회원정보수정'
        })
        .then((result) => {
        if (result.value) {
            this.$router.push('/user/modifyuser')
        }
        })
      }
      else{
        Swal.fire({
          title: '입력이 완료되지 않았습니다.',
          text: '빈칸을 모두 입력해주세요!',
        })
      }
    },
  },
  computed: {
    filterList() {
      const str = this.searchQuery;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      // // console.log(`typing value: ${str}`);
      if (reg === false && str !== '' && str !== ' ') {
        // this.isActive = true;
        return this.names.filter((el) => {
          return el.name_kor.match(str);
        });
      }else{
        return '';
      }
    },
    filterListImg() {
      const str = this.searchQuery;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      // // console.log(`typing value: ${str}`);
      if (reg === false && str !== '' && str !== ' ') {
        // this.isActive = true;
        return this.names.filter((el) => {
          return el.name_kor.match(str);
        });
      } else if(str == '') {
        // this.isActive = false;
        return this.names;
      }else{
        return '';
      }
    },
    // computedDateFormatted () {
    //   return this.formatDate(this.date)
    // },
  },
  created(){
    if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
      
  },
}
</script>

<style scoped>
.foodDialog {
  height: 460px;
}
.titleinput {
  background-color: unset;
  border: 1px solid lightgray;
  border-radius: 4px;
  text-align: center;
  padding: 0 10px;
}
.titleinput:hover {
  border: 2px solid #a0d469;
}
.setFood {
  border: 1px solid lightgray;
  background-color: unset;
  -webkit-box-shadow: unset;
  box-shadow: unset;
  border-radius: 4px;
  color: black;
  float: unset;
  width: unset;
  height: unset;
  overflow: unset;
}
.setFood:hover {
  border: 2px solid #a0d469;
}
.v-dialog {
  height: 80% !important;
}
input{
  padding:0px;
  background-color: #ebebeb;
  width:25px;
  height:25px;
  text-align: right;
}
.s {
  padding: 10px 20px;
  width: 90%;
  height: 100%;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-box-shadow: 0 0 3px rgba(#000, 0.2);
  box-shadow: 0 0 3px rgba(#000, 0.2);
  border-bottom: 1px solid #8888882b;
  font-size: 16px;
  background-color: #eee;
  border-radius: 30px;
  margin: 0px 18px;
}
.card {
  box-shadow: rgba(0, 0, 0, 0.117647) 0px 1px 6px, rgba(0, 0, 0, 0.117647) 0px 1px 4px;
  width: 27%;
  height:100px;
  margin: 3%;
  text-align: center;
  font-size: 18px;
  overflow: hidden;
  transition: .15s all ease-in-out;
  float:left;
  z-index: 24;
}
.card:hover {
  transform: scale(1.1);
  background-color: rgb(228 213 213 / 72%);;
  transition: 1s;
}
.v-text-field {
  margin: 0 !important;
  padding: 0 !important;
}
.v-input__control {
  border: 1px solid lightgray !important;
}
.v-input__slot:before{
  border-color: unset !important;
}
.cntPeople {
  background-color: unset;
  border: 1px solid lightgray;
  border-radius: 4px;
  text-align: center;
  padding: 0 10px;
}
.cntPeople:hover {
  border: 2px solid #a0d469;
}
.fileinput {
  background-color: unset;
  border: 1px solid lightgray;
  border-radius: 4px;
  text-align: center;
  padding: 0 10px;
}
.fileinput:hover {
  border: 2px solid #a0d469;
}
/* 날짜 css => writegroupbuying.scss */
.contentinput {
  float: left; 
  width:100%; 
  height: 200px; 
  resize: none;
  border: 1px solid lightgray;
  padding: 5px 10px
}
.contentinput:hover {
  border: 2px solid #a0d469;
}
</style>
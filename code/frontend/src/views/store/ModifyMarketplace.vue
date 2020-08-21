<template>
  <div style="height: 100%">
    <div style="width:100%; height:40px; border-top: 1px solid rgba(128, 128, 128, 0.15); border-bottom: 1px solid rgba(128, 128, 128, 0.15)">
      <router-link to="/store/marketplace">
        <div style="width:40px; height:100%;border-right: 1px solid rgba(128, 128, 128, 0.15); float:left;">
            <v-icon size="30px" style="padding:6px 0px;">mdi-chevron-left</v-icon>
        </div>
      </router-link>
      <div style="height:100%; text-align:center; padding-top:8px;">
        <h4>우리동네장터 글 수정하기</h4>
      </div>
    </div>
    <!-- 수정하기 본문 -->
    <div style="padding: 10px 20px">
      <!-- 내 식재료 -->
      <div style="margin: 10px 0; overflow: hidden;">
        <div style="float: left; width: 30%; margin-right: 10px">
          <span style="line-height: 40px">내 식재료 / 개당</span>
        </div>
        <div>
          <!-- <input type="text" v-model="myFood" class="myFood" style="float: left; width: 40%; height: 40px; text-align: center; font-size: 15px;"> -->
          <div class="myFood" style="float: left; width: 40%; height: 40px; text-align: center; font-size: 15px; line-height: 40px">{{ beforedata.myfood_kor }}</div>
        </div>
        <div>
          <input type="text" v-model="beforedata.myfoodcount1" class="cntfood" style="float: left; width: 15%; height: 40px; text-align: center; font-size: 15px;">
        </div>
      </div>
      <!-- 교환 물품 1 -->
      <div style="margin: 10px 0; overflow: hidden;">
        <div style="float: left; width: 30%; margin-right: 10px">
          <span style="line-height: 40px">교환물품1 / 수량</span>
        </div>
        <div>
          <input type="text" v-model="food1" @click="getFood1" class="setFood" style="float:left; width: 40%; height: 40px; text-align: center; font-size: 15px;">
        </div>
        <!-- 수정 -->
        <v-dialog v-model="dialog1" scrollable width= "100%" class="adressDialog">
          <v-card class="foodDialog">
            <v-card-title >
              <!-- 품목 검색 -->
              <div style="width:100%;"> 
                <div class="search" >
                  <input class="s" placeholder="음식재료 이름 검색" v-on:input="searchQuery1=$event.target.value" style="text-align:left;">
                  <ul style="display:none;" tabindex="3" >
                    <li tabindex="24" 
                        v-for="(el, index) in filterList1" 
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
                <div @click="chooseComplete1(food)" class="card" v-for="(food,index) in filterListImg1" :key="index">
                  <div>
                    <img style="margin:10px auto 5px auto;width:60px; height:auto; font-size:20px;" v-bind:src="require(`../../assets/images/food/${food.img}.png`)"/>
                  </div>
                  <div>
                    {{ food.name_kor }}
                  </div>
                </div>
                <div style="width:100%;height:100%;" v-if="filterListImg1.length==0">
          <div style="width:80%; height:50%;text-align:center;margin-top:50px;">
            <h4>해당 음식이</h4>  <h4>아직 등록되지 않았어요</h4> <h4>기타 이미지로 등록해주세요.</h4>
            <img src="../../assets/images/fruit.png" style="width:80px;">
            <div style="width:150px; margin:auto;">
            <h4 style="float:left;">이름 : </h4><input v-model="etcName1" type="text" class="inputText" style=" float:left;width: 80px; height: 35px; text-align: center;">
            <v-btn @click="chooseComplete1({name:'etc',
            name_kor:etcName1,
            img:'etc' })" width="100%" style="margin-top:30px;" color="rgb(160,212,105)">등록하기</v-btn>
          </div>
          </div>
        </div>
              </div>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="dialog1 = false">닫기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- 교환 물품 수량 -->
        <div>
          <input type="text" v-model="beforedata.tradefoodcount1" class="cntfood" style="float: left; width: 15%; height: 40px; text-align: center; font-size: 15px;">
        </div>
      </div>
      <!-- 내 식재료 -->
      <div style="margin: 10px 0; overflow: hidden;">
        <div style="float: left; width: 30%; margin-right: 10px">
          <span style="line-height: 40px">내 식재료 / 개당</span>
        </div>
        <div>
          <!-- <input type="text" v-model="myFood" class="myFood" style="float: left; width: 40%; height: 40px; text-align: center; font-size: 15px;"> -->
          <div class="myFood" style="float: left; width: 40%; height: 40px; text-align: center; font-size: 15px; line-height: 40px">{{ beforedata.myfood_kor }}</div>
        </div>
        <div>
          <input type="text" v-model="beforedata.myfoodcount2" class="cntfood" style="float: left; width: 15%; height: 40px; text-align: center; font-size: 15px;">
        </div>
      </div>
      <!-- 교환 물품 2 -->
      <div style="margin: 10px 0; overflow: hidden;">
        <div style="float: left; width: 30%; margin-right: 10px">
          <span style="line-height: 40px">교환물품2 / 수량</span>
        </div>
        <div>
          <input type="text" v-model="food2" @click="getFood2" class="setFood" style="float:left; width: 40%; height: 40px; text-align: center; font-size: 15px;">
        </div>
        <v-dialog v-model="dialog2" scrollable width= "100%" class="adressDialog">
          <v-card class="foodDialog">
            <v-card-title >
              <!-- 품목 검색 -->
              <div style="width:100%;"> 
                <div class="search" >
                  <input class="s" placeholder="음식재료 이름 검색" v-on:input="searchQuery2=$event.target.value" style="text-align:left;">
                  <ul style="display:none;" tabindex="3" >
                    <li tabindex="24" 
                        v-for="(el, index) in filterList2" 
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
              <div style="z-index:20;">
                <div @click="chooseComplete2(food)" class="card" v-for="(food,index) in filterListImg2" :key="index">
                  <div>
                    <img style="margin:10px auto 5px auto;width:60px; height:auto; font-size:20px;" v-bind:src="require(`../../assets/images/food/${food.img}.png`)"/>
                  </div>
                  <div>
                    {{ food.name_kor }}
                  </div>
                </div>
                <div style="width:100%;height:100%;" v-if="filterListImg2.length==0">
          <div style="width:80%; height:50%;text-align:center;margin-top:50px;">
            <h4>해당 음식이</h4>  <h4>아직 등록되지 않았어요</h4> <h4>기타 이미지로 등록해주세요.</h4>
            <img src="../../assets/images/fruit.png" style="width:80px;">
            <div style="width:150px; margin:auto;">
            <h4 style="float:left;">이름 : </h4><input v-model="etcName2" type="text" class="inputText" style=" float:left;width: 80px; height: 35px; text-align: center;">
            <v-btn @click="chooseComplete2({name:'etc',
            name_kor:etcName2,
            img:'etc' })" width="100%" style="margin-top:30px;" color="rgb(160,212,105)">등록하기</v-btn>
          </div>
          </div>
        </div>
              </div>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="dialog2 = false">닫기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- 교환 물품 수량 -->
        <div>
          <input type="text" v-model="beforedata.tradefoodcount2" class="cntfood" style="float: left; width: 15%; height: 40px; text-align: center; font-size: 15px;">
        </div>
      </div>
      <!-- 주소 -->
      <div style="margin: 10px 0; overflow: hidden;">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 40px">주소</span>
        </div>
        <div style="overflow: hidden;">
          <input v-model="address" type="text" class="adress" @click="addressgo()" style="float: left; width: 100%; height: 40px; text-align: center; font-size: 15px;">
        </div>
        <v-dialog v-model="addressDialog"  width= "100%" class="adressDialog">
          <v-card>
            <v-card-title >주소 검색</v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <DaumPostcode style="height:300px" :on-complete="handleAddress" />
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="addressDialog = false">닫기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <!-- 내용 -->
      <div style="margin: 10px 0;">
        <div style="float: left; width: 15%; margin-right: 10px">
          <span style="line-height: 40px">내용</span>
        </div>
        <div style="overflow: hidden;">
          <textarea v-model="beforedata.content" class="contentinput"></textarea>
        </div>
      </div>
    </div>
    <!-- 글 작성 버튼 -->
    <div style="width: inherit">
      <router-link to="/store/marketplace" style="width: inherit">
        <v-btn 
        color="rgb(160, 212, 105)" 
        style="width: inherit; height: 50px; color: white; font-size: 18px; position:fixed; bottom: 0; border-radius: unset;" 
        @click="onModify()"
        >
        수정하기
        </v-btn>
      </router-link>
    </div>
  </div>
</template>

<script>
import {foods} from '../Food/Foods.js'
import DaumPostcode from "vuejs-daum-postcode";
import axios from 'axios'
import store from '../../vuex/store.js'
// const SERVER_URL = 'https://i3b301.p.ssafy.io:9999/food/api';
const SERVER_URL = store.state.SERVER_URL;
export default {
  components: {
    DaumPostcode
  },
  data() {
    return {
      beforedata: [],
      myFood: '달걀',
      cntMyFood: '',
      food1: '',
      food1_en: '',
      cntFood1: '',
      food2: '',
      food2_en: '',
      cntFood2: '',
      dialog1: false,
      dialog2: false,
      searchQuery1: '',
      searchQuery2: '',
      address: '',
      addressDialog: false,
      content: '',
      names : foods,
      etcName1:'',
      etcName2:'',

    }
  },
  created() {
    axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/beforeupdate` , {no:this.$route.params.pagenumber})
      .then(response => {
        this.beforedata = response.data
        // 줄바꿈 
        this.beforedata.content = this.beforedata.content.split('^').join('\n');
        this.food1 = this.beforedata.tradefood1_kor
        this.food1_en = this.beforedata.tradefood1
        this.food2 = this.beforedata.tradefood2_kor
        this.food2_en = this.beforedata.tradefood2
        this.address = this.beforedata.address
        // console.log(this.beforedata)
      })
      .catch(error => {
        // console.log(error.response)
      })
  },
  methods: {
    getFood1(){
      // console.log('getFood1 실행')
      if (this.dialog1 === false) {
        this.dialog1 = true
        // console.log('false')
      }
      else {
        this.dialog1 = false
        // console.log('true')
      }
    },
    getFood2(){
      // console.log('getFood2 실행')
      if (this.dialog2 === false) {
        this.dialog2 = true
        // console.log('false')
      }
      else {
        this.dialog2 = false
        // console.log('true')
      }
    },
    chooseComplete1:function(food){
      // alert(food.name_kor+'을(를) 선택했습니다.');
      this.dialog1 = false
      // $('.setFood').text(food.name_kor);
      this.food1 = food.name_kor
      this.food1_en = food.name
      this.searchQuery1 = ''
      document.querySelector('.s').value = '';
    },
    chooseComplete2:function(food){
      // alert(food.name_kor+'을(를) 선택했습니다.');
      this.dialog2 = false
      // $('.setFood').text(food.name_kor);
      this.food2 = food.name_kor
      this.food2_en = food.name
      this.searchQuery2 = ''
      document.querySelector('.s').value = '';
    },
    addressgo(){
      if(this.addressDialog==false){
        this.addressDialog = true;
      }else{
        this.addressDialog = false
      }
    },
    handleAddress(data){
      // console.log(data)
      let fullAddress = data.address
      let extraAddress = ''
      if (data.addressType === 'R') {
        if (data.bname !== '') {
          extraAddress += data.bname
        }
        if (data.buildingName !== '') {
          extraAddress += (extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName)
        }
        fullAddress += (extraAddress !== '' ? ` (${extraAddress})` : '')
      }
      this.address = fullAddress
      this.addressDialog = false;
      this.inputAddress = true
    },
    onModify() {
      this.beforedata.tradefood1_kor = this.food1
      this.beforedata.tradefood1 = this.food1_en
      this.beforedata.tradefood2_kor = this.food2
      this.beforedata.tradefood2 = this.food2_en
      this.beforedata.address = this.address
      const sendContent = this.beforedata.content.replace(/\n/g, '^')
      axios.post(`https://i3b301.p.ssafy.io:9999/food/api/trade/updatetrade`, 
      {no:this.$route.params.pagenumber,address: this.beforedata.address, content: sendContent, 
      email: this.beforedata.email, myfood: this.beforedata.myfood, myfood_kor: this.beforedata.myfood_kor, 
      myfoodcount1: this.beforedata.myfoodcount1, myfoodcount2: this.beforedata.myfoodcount2, nickname: this.beforedata.nickname, 
      price: this.beforedata.price, tradefood1: this.beforedata.tradefood1, tradefood1_kor: this.beforedata.tradefood1_kor, 
      tradefood2: this.beforedata.tradefood2, tradefood2_kor: this.beforedata.tradefood2_kor, tradefoodcount1: this.beforedata.tradefoodcount1, 
      tradefoodcount2: this.beforedata.tradefoodcount2})
        .then(response => {
          // console.log(this.beforedata)
        })
        .catch(error => {
          // console.log(error.response)
        })
    },
  },
  computed: {
    filterList1() {
      const str = this.searchQuery1;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      // console.log(`typing value: ${str}`);
      if (reg === false && str !== '' && str !== ' ') {
        // this.isActive = true;
        return this.names.filter((el) => {
          return el.name_kor.match(str);
        });
      }else{
        return '';
      }
    },
    filterListImg1() {
      const str = this.searchQuery1;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      // console.log(`typing value: ${str}`);
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
    filterList2() {
      const str = this.searchQuery2;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      // console.log(`typing value: ${str}`);
      if (reg === false && str !== '' && str !== ' ') {
        // this.isActive = true;
        return this.names.filter((el) => {
          return el.name_kor.match(str);
        });
      }else{
        return '';
      }
    },
    filterListImg2() {
      const str = this.searchQuery2;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      // console.log(`typing value: ${str}`);
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
  },
}
</script>

<style scoped>
.foodDialog {
  height: 460px;
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
  width:25px;
  height:25px;
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
.cntfood {
  margin-left: 10px;
  border: 1px solid lightgray;
  background-color: unset;
  border-radius: 4px;
}
.cntfood:hover {
  border: 2px solid #a0d469;
}
.adress {
  padding: 0 10px;
  border: 1px solid lightgray;
  border-radius: 4px;
}
.adress:hover {
  border: 2px solid #a0d469;
}
.contentinput {
  float: left; 
  width:100%; 
  height: 200px; 
  resize: none;
  border: 1px solid lightgray;
  border-radius: 4px;
  padding: 5px 10px
}
.contentinput:hover {
  border: 2px solid #a0d469;
}
</style>
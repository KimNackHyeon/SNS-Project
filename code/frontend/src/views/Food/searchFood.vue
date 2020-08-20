<template>
  <div style="width:100%; height:94%;">
      <div style="width:100%; height:10%; z-index:23;" v-on:keyup.down="selectValue('down')"
       v-on:keyup.up="selectValue('up')"> <!-- 검색 -->
            <div class="search" >
          <input class="s" placeholder="음식재료 이름 검색" 
                v-on:input="searchQuery=$event.target.value" style="text-align:left;">
          <ul style="display:none;" tabindex="3" 
              
              v-on:mouseover="removeValue">
            <li tabindex="-1" 
                v-for="(el, index) in filterList" 
                v-on:click="changeValue(el.name_kor)"
                v-on:keyup.enter="selectValue('enter', el.name_kor)"
                :key="index"
                style="z-index:24;"
                >
              <span>{{ el.name_kor }}</span>
            </li>
          </ul>
        </div>
      </div> <!-- end of 검색 --> 

      <div style="height:74%; border-top:1px solid #9e9e9e6b;overflow-y: scroll; z-index:25;">
        <div class="card" v-for="(food,index) in filterListImg" :key="index">
        <div @click="changeValue(food)">
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
            <div style="width:50%; margin:auto;">
            <h4 style="float:left;">이름 : </h4><input v-model="etcName" type="text" class="inputText" style=" float:left;width: 80px; height: 35px; text-align: center;">
            <div style="width:100%; height:60px; padding: 14px 0px;">
            <h5 >필요한 갯수 : </h5>
          <button @click="selectAmountType" id="natureBtn" class="nature" style="float:right;">1개 미만</button>
            <div class="Nature" style="float:right;">
                <input type="text" style="float:left" v-model="amount">
                <h5>개</h5>
            </div>
            <div class="underNature" style="display:none; float:right;">
                <input type="text" style="float:left;" v-model="amount">
                <h5 style="float:left">/</h5>
                <input type="text" style="float:left;" v-model="amountundernature">
                <h5>개</h5>
            </div>
        </div>
            <v-btn @click="addEtcIngradient()" width="100%" style="margin-top:30px;" color="rgb(160,212,105)">추가하기</v-btn>
          </div>
          </div>
        </div>
      <div class="putFoodInform">
        <div style="width:100%; height:50px; overflow:hidden text-align:center; padding:13px; font-size:17px; border-bottom:1px solid #80808033;">
          <div style="width:63px; overflow:hidden; float:left;">
            <h4 style="float:left;">{{thisSelectedFood.name_kor}}</h4> 
          </div>
          <h5 style="float:left;">필요한 갯수</h5>
        </div>
        <div style="width:100%; height:60px; padding: 14px 0px;">
          <button @click="selectAmountType" id="natureBtn" class="nature">1개 미만</button>
            <div class="Nature" style="float:right;">
                <input type="text" style="float:left" v-model="amount">
                <h5>개</h5>
            </div>
            <div class="underNature" style="display:none; float:right;">
                <input type="text" style="float:left;" v-model="amount">
                <h5 style="float:left">/</h5>
                <input type="text" style="float:left;" v-model="amountundernature">
                <h5>개</h5>
            </div>
        </div>
        <div @click="closeinputForm" style="width: 50%;
    height: 40px;
    background-color: red;
    text-align: center;
    font-size: 22px;
    font-weight: bolder;
    color: white;
    text-shadow: 1px 1px 5px #0000004f; float:left;">
          취소
        </div>
        <div @click="addIngradient" style="width: 50%;
    height: 40px;
    background-color: rgb(160, 212, 105);
    text-align: center;
    font-size: 22px;
    font-weight: bolder;
    color: white;
    text-shadow: 1px 1px 5px #0000004f; float:left;">
          추가
        </div>
      </div>
      </div>
      <div style="width:100%; height:8%; background-color:white; overflow-x: hidden; white-space: nowrap; box-shadow: 0px -1px 13px #0000002b;">
        <div v-for="(food,index) in selectedFood" :key="index" class="addedfood">
          {{food.name_kor}} {{food.amount}}개
        </div>
      </div>
      <div style="width:100%; height:8%;">
        <div @click="addFoodComplete" style="width:100%; height:100%; height: 45px;
    width: 100%;
    background-color: rgb(160,212,105); text-align:center; padding:4px;"><h3>등록하기</h3></div>
      </div>
  
  </div>
</template>

<script>
import $ from 'jquery'
import {foods} from './Foods.js'
import Swal from 'sweetalert2'

export default {
         
    data() {
        return {
            isActive: false,
            searchQuery: '',
            selectedFood: [],
            thisSelectedFood:'',
            amount:'',
            amountundernature:'',
            names : foods,
            etcName:''
    }
  },
  props: ['foodlist'],
  watch: {
    foodlist() {
      if (this.foodlist == undefined) {
        this.selectedFood = []
      }
      else {
        this.selectedFood = this.foodlist
      }
    }
  },
  methods: {
    addFoodComplete(){
      this.$emit('addfood',this.selectedFood);
    },
    changeValue(food) {
      // console.log(`change value: ${food}`);
      this.isActive = false;
      document.querySelector('.s').value = '';
      // this.selectedFood.push(food);
      this.thisSelectedFood = food;
      $('.putFoodInform').css('display','block');
    },
    addIngradient(){
      var resultamount ='';
      if(this.amount==''){
        Swal.fire({
          icon: 'info',
          title: '필요한 갯수를 적어주세요.',
        })
      }else{
        if(this.amountundernature !=''){
          resultamount = this.amount + '/'+this.amountundernature;
      }else{
        resultamount = this.amount;
      }
      this.selectedFood.push({name:this.thisSelectedFood.name,name_kor:this.thisSelectedFood.name_kor,img:this.thisSelectedFood.img,amount:resultamount});
      $('.putFoodInform').css('display','none');
        }
        this.amount = '';
        this.amountundernature = '';
    },
    addEtcIngradient(){
       var resultamount ='';
      if(this.amount==''){
        Swal.fire({
          icon: 'info',
          title: '필요한 갯수를 적어주세요.',
        })
      }else{
        if(this.amountundernature !=''){
          resultamount = this.amount + '/'+this.amountundernature;
      }else{
        resultamount = this.amount;
      }
      this.selectedFood.push({name:'etc',name_kor:this.etcName,img:'etc',amount:resultamount});
      $('.putFoodInform').css('display','none');
        }
        this.amount = '';
        this.amountundernature = '';
    },
    closeinputForm(){
       $('.putFoodInform').css('display','none');
    },
    selectValue(keycode, str) {
      $('.r').css('display','unset');
      if (this.isActive === true) {
        const hasClass = document.querySelector('.r').classList.contains('key');
        if (keycode === 'down') {
          if (!hasClass) {
            const thisEl = document.querySelectorAll('.r li')[0];
            document.querySelector('.r').classList.add('key');
            thisEl.classList.add('sel');
            thisEl.focus();
          } else {
            const lastEl = document.querySelector('.r li:last-child');
            const thisEl = document.querySelector('.r li.sel');
            const nextEl = thisEl.nextElementSibling;
            if (!lastEl.classList.contains('sel')) {
              thisEl.classList.remove('sel');
              nextEl.classList.add('sel');
              nextEl.focus();
            }
          }
        }
        if (keycode === 'up' && hasClass) {
          const firstEl = document.querySelectorAll('.r li')[0];
          const thisEl = document.querySelector('.r li.sel');
          const prevEl = thisEl.previousElementSibling;
          if (!firstEl.classList.contains('sel')) {
            thisEl.classList.remove('sel');
            prevEl.classList.add('sel');
            prevEl.focus();
          } else {
            document.querySelector('.s').focus();
          }
        }
        if (keycode === 'enter' && hasClass) {
          this.changeValue(str);
        }
      }
    },
    removeValue() {
      if (document.querySelector('.r').classList.contains('key')) {
        document.querySelector('.r').classList.remove('key');
        document.querySelector('.r li.sel').classList.remove('sel');
      }
    },
    selectAmountType(){
      if($('.Nature').css('display')=='none'){
        $('.Nature').css('display','block');
        $('.underNature').css('display','none');
        $('#natureBtn').css('background-color','#e0e0e0bf');
         $('#natureBtn').css('color','#1e1e1e61');
      }else{
        $('.Nature').css('display','none');
         $('.underNature').css('display','block');
        $('#natureBtn').css('background-color','rgb(160,212,105)');
         $('#natureBtn').css('color','black');
         
      }
    }
  },
  computed: {
    filterList() {
      const str = this.searchQuery;
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
    filterListImg() {
      const str = this.searchQuery;
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
.card:hover{
  cursor: pointer;
}
input{
  padding:0px;
  background-color: #ebebeb;
  width:25px;
  height:25px;
  text-align: right;
}
.undernature{
  float:right; display:none;background-color:rgb(160,212,105);padding:5px; border-radius:7px; font-weight:800; margin:0px 8px;
}
.addedfood{
      float: left;
    margin: 8px 4px;
    background-color: rgb(160 212 105 / 25%);
    padding: 4px 6px;
    font-size: 12px;
    border-radius: 6px;
    box-shadow: 1px 1px 4px #0000002e;
    
}
.nature{
  float:left;
    background-color: #e0e0e0bf;
    padding: 5px;
    border-radius: 7px;
    font-weight: 800;
    color: #1e1e1e61;
    margin-left: 8px;
    box-shadow: 0px 0px 4px #00000057 inset;
}
.putFoodInform{
      width: 160px;
    height: 150px;
    background-color: white;
    z-index: 30;
    position: fixed;
    display: none;
    margin: 98px 100px;
    -webkit-box-shadow: 0px 0px 105px #0000001a;
    box-shadow: 0px 0px 105px #00000061;
}
.searchBox{
    width: 90%;
    height:38px;
    margin: 19px auto;
    border-radius: 25px;
    background-color: rgba(128, 128, 128, 0.21);
}
.searchBarBtn{
    width:87%;
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
}

.card:hover {
      transform: scale(1.1);
      background-color: rgb(228 213 213 / 72%);;
      transition: 1s;
    }

h3{
    font-size: 22px;
    font-weight: 550;
    color: white;
    text-shadow: 1px 1px 4px #0000004f;
}
.search{
      z-index: 21;
    position: relative;
    margin: 0 auto;
    width: 100%;
    max-width: 600px;
    height: 44px;
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
    .v-application ul, .v-application ol {
    padding-left: 0px;
}
ul{
  box-shadow: 1px 1px 10px #00000047;
    width: 77%;
    margin: 0px 42px;
}
li {
            /* margin-top: -1px; */
    padding: 0 20px;
    width: 100%;
    height: 40px;
    background-color: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    border-bottom: 1px solid #88888830;
    outline: none;
    font-size: 16px;
    line-height: 40px;
    cursor: pointer;
        }
li:hover {
          background-color: darken(#fff, 5%);
}
</style>

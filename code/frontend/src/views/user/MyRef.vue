<template>
<div style="width:100%; height:100%; background-color:#f5efb3;">
  <div class="rootContainer">
      <div class="selectfood" style="width:100%; height:100%; background-color:white; display:none; position:fixed; z-index:80;">
          <div style="height:50px; background-color:white" :style="{width:frameSize.x+'px'}">
              <div style="float:right; margin: 11px;" @click="closeGetOneFood"><v-icon>mdi-close</v-icon></div>
          </div>
          <get-one-food @addfood="addFood" :style="{width:frameSize.x+'px', height:(frameSize.y-90)+'px'}"></get-one-food>
      </div>
      <div id="dark" @click="closeCheckBasket()" :style="{width:frameSize.x+'px', height:frameSize.y+'px'}" style="background-color:#00000075; z-index:99; position:fixed; display:none;"></div>
    
        <ref-paging :list-array="foods" @openShare="openShare"  id="insideRef" >
            
        </ref-paging>
            <div id="basket">
            <v-btn @click="openCheckBasket()" icon :style="{width:(frameSize.per*200)+'px',height:(frameSize.per*150)+'px'}"><img style="width:100%; height:100%;" src="../../assets/images/basket.png"></v-btn>

        </div>
        <div id="FillBtn" :style="{'margin-left': (256*frameSize.per)+'px'}" style="position:fixed; margin-top: 10px; display:unset;">
            <v-btn  v-on:click="openregistMater" color="rgb(160,212,105)" width="90px" height="50px" >
                <v-icon >mdi-cart</v-icon>
                <h4>채우기</h4>
            </v-btn>
        </div>
        <div id="shareField" class="inputFeild"> <!-- 바구니에 넣기 -->
            <div style="width: 100%;
    height: 38px;
    background-color: rgba(224, 224, 224, 0.51);
    text-align: center;
    font-weight: bold;
    padding: 9px 0px;
    overflow: hidden;">{{Nowgra.name_kor}} (재고: {{Nowgra.amount}} )<button v-on:click="closeShare" type="button" height="15px" width="15px"  style="float:right;"> <v-icon size="30px" style="    margin-top: -5px;
">mdi-close</v-icon></button></div>
            <div class="textArea" style="height: 50px; margin-top: 0px; text-align: center; padding: 5px 36px;;">
                <input v-model="totalShareAmount" type="text" class="inputText" style="float: left; width: 40px; height: 35px; text-align: center;"><h5 style="font-size:15px">개를</h5>
                <div class="shareButton" @click="openShareBox">공유</div>
                <div class="deleteButton" @click="deleteFoodfromRef" >삭제</div>
            
            </div>
            <div class="sharebox" style="display:none;">
            <div class="textArea" style="height:35px; padding:0px;">
                <h5 class="longNameBox" style="width:30%; padding:0px 7px; font-size:15px; font-weight:600;">{{Nowgra.name_kor}}</h5><h5 style="float:left; font-size:15px"> 와(과) 교환할 재료를 담아주세요</h5>
               
            </div>
            <div style="width:100%; height:50px; padding: 4px 58px; font-size:15px;">
                    <input type="text" v-model="nowmyamount" class="inputText" style="float:left;height:30px;text-align:center;"><h5 style="float:left">개당</h5> 
                    <button type="button" @click="getFood" class="setFood" style="float:left; margin-right:5px;"></button>
                    <input type="text" v-model="nowCamount" class="inputText" style="float:left; height:30px;text-align:center;"><h5 style="float:left">개</h5>
            </div>
                <div style="width:100%; height:40px;">
                    <button v-on:click="addChangeGradient" class="tempInputButton"><v-icon size="15px">mdi-arrow-down-bold</v-icon>교환목록에 넣기</button>
                </div>
                
            <div style="width: 100%; height: 111px;  background-color: rgba(128, 128, 128, 0.2); overflow: scroll; box-shadow: 0px 1px 9px inset #00000042;">
                <div  v-for="(food,index) in changeFoodsTemp" :key="index" >
                    <div class="changeFood" style="font-size:15px;">
                        <h4 style="float:left; width:90%; float:left;">{{food.Mygradient_kor}} {{food.myamount}}개당 {{food.Cgradient_kor}} {{food.Camount}}개</h4>
                        <div style="width:10%; float:left;">
                        <button v-on:click="deleteShareList(index)" style="border-radius: 5px;
    background-color: #cc2b2b;
    width: 32px;
    height: 31px;
    margin-top: -7px;"><v-icon color="white" size="20px">mdi-trash-can-outline</v-icon></button>
                        </div>
                    </div>
            </div>
                
            </div>
            <div class="textArea" style="height:130px">
                <textarea v-model="sharedesc" style="width:100%; height:100%; resize: none;" placeholder="공유글에 들어갈 글을 적어주세요"></textarea>
            </div>
            <div style="width:100%; height:40px;">
                    <button v-on:click="putIntoBasket" class="tempInputButton" style="background-color:rgb(160,212,105);font-size:18px; font-weight:700;">바구니에 넣기</button>
                </div>
            </div>
            <div style="width:100%; height:74px; background-color:black; color:white; font-size: 15px; padding: 5px; overflow-y:scroll;">
             <div style="color: white; font-size: 15px; text-align: center;">- 실시간 시세 정보 -</div>
             <div v-if="myapi.name!=''">{{myapi.name}} {{myapi.unit}} 당 {{myapi.price}}원</div>
             <div v-if="trade1api.name!=''">{{trade1api.name}} {{trade1api.unit}} 당 {{trade1api.price}}원</div>
             <div v-if="trade2api.name!=''">{{trade2api.name}} {{trade2api.unit}} 당 {{trade2api.price}}원</div>
            <div v-if="myapi.name =='' && trade1api.name==''&&trade2api.name==''">가격 데이터가 존재하지 않습니다.</div>
                <div style="color:white; font-size:8px; position:absolute; bottom:0; right:0; ">출처 : 농산물 유통정보 KAMIS</div>
            </div>
        </div><!-- end of 바구니에 넣기 -->

        <div class="registMaterial"> <!-- 채우기 박스 -->
            <div style="width:100%; height:30px; background-color:rgba(224, 224, 224, 0.51); text-align:center; font-weight:bold;text">채우기<button v-on:click="closeregistMater" type="button" height="15px" width="15px"  style="float:right;"> <v-icon size="15px">mdi-close</v-icon></button></div>
            <div style="width:100%; height:190px;">
                <div class="textArea">
                    <h5>넣을 재료</h5><v-btn @click="getFood" class="fillBtn" style="float:left; width:65px; height:30px;"></v-btn>
                </div>
                <div class="textArea">
                    <h5>넣을 개수</h5><input v-model="fillFoodNum" type="text" class="inputText" style="float:left; width:40px; height:30px;"><h5>개</h5>
                </div>
                <div class="textArea" style="height:500px">
                    <h5>유통 기한</h5>
                    <div style="width:60%; float:left; margin-left:27px; height:15px;">
                        <v-menu ref="menu" v-model="menu" :close-on-content-click="false"
                            :return-value.sync="date"  transition="scale-transition" offset-y  min-width="290px" >
                            <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                                v-model="fillFoodExpireDate"
                                readonly
                                v-bind="attrs"
                                v-on="on"
                                style="font-size:10px; margin:0px; padding:0px; width:100%; height:30px; background-color:#f5f5f5;"
                                ></v-text-field>
                            </template>
                            <v-date-picker v-model="fillFoodExpireDate" no-title scrollable>
                            <v-spacer></v-spacer>
                            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                            </v-date-picker>
                        </v-menu>
                    </div>
                        <v-icon color="#e0e0e0" style="float:left; padding-top:5px;">mdi-calendar</v-icon>
                </div>
                
            </div>
            <button @click="fillFoodtoRef" type="button" v-on:click="closeregistMater" style="width:100%; height:33px;background-color:rgb(160, 212, 105); font-weight:bold; color:white; font-size:16px;">냉장고에 넣기</button>
        </div><!-- end of 채우기 -->

        <div class="checkBasket"> <!-- 장바구니 안 보기 -->
            <div style="width:100%; height:30px; background-color:rgba(224, 224, 224, 0.51); text-align:center; font-weight:bold; padding-top:5px; overflow:hidden;">공유 바구니</div>
           <div style="width:100%; height:196px; overflow:scroll; text-align: center;">
           <div class="textArea" v-for="(food,index) in changeFoods" :key="index" style="height:auto;">
               <h3>{{food.myfood_kor}}와 교환할 재료</h3> <h4 style="fload:left; font-size:12px;">(개당 {{food.price}} 원)</h4>
                <div style=" height: 20px; font-size: 15px;">
                    <h4 style="float:left; width:100%; float:left;">{{food.myfoodcount1}}개당 {{food.tradefood1_kor}} {{food.tradefoodcount1}}개</h4>
                </div>
                <div v-if="food.tradefood2 !=null" style=" height: 20px; font-size: 15px;">
                     <h4 style="float:left; width:100%; float:left;">{{food.myfoodcount2}}개당 {{food.tradefood2_kor}} {{food.tradefoodcount2}}개</h4>
                </div>
           
           </div>
           </div>
        </div><!-- end of 장바구니 안 보기 -->
        
        <div :style="{'margin-top':frameSize.y-85+'px',width:frameSize.x+'px'}" style="position:fixed;">
            <button @click="shareFinish" type="button" style="width:100%; height:40px;background-color:rgb(160, 212, 105); font-weight:bold; color:white; font-size:20px;">장터에 글올리기</button>
        
        </div>
  </div>
  </div>
</template>

<script>
import $ from 'jquery';
import axios from "axios";
import store from '../../vuex/store.js'
import getOneFood from '../Food/getOneFood.vue'
import Swal from 'sweetalert2'
import qs from 'query-string';
import RefPaging from './RefPaging.vue'
// import json from 'http://www.kamis.or.kr/service/price/xml.do?action=dailySalesList&p_cert_key=73081fa5-fa86-492a-b3f3-905e315da6ac&p_cert_id=1137&p_returntype=json';

const SERVER_URL = store.state.SERVER_URL;
// const SERVER_URL = 'http://localhost:9999/food/api';
var convert = require('xml-js');
const $api_url = 'https://www.kamis.or.kr/service/price/xml.do?action=dailySalesList&p_cert_key=73081fa5-fa86-492a-b3f3-905e315da6ac&p_cert_id=1137&p_returntype=json';

const config = {
    headers: {
      'Access-Control-Allow-Origin':'*',
      'Content-type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Methods':'GET, POST, PUT, DELETE, OPTIONS',
      'Access-Control-Allow-Headers':'Origin,Access-Control-Request-Headers, Authorization'
    }
}

export default {
    components:{getOneFood,RefPaging},
data() {
    return {
        frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
        selectedFood:'',  //냉장고에 채우고싶은 재료 
        fillFoodNum:1, //냉장고에 채우고 싶은 재료 개수
        fillFoodDate:'', //냉장고에 채우고 싶은 재료를 산 날짜
        fillFoodExpireDate:'',//냉장고에 채우고 싶은 재료 유통기한
        totalShareAmount:1, //공유하고싶은 총 량
      userinfo:'',
      Nowgra:'',
        Nowgra_kor : '',
        NowClassNum : 1, //클릭한 칸의 클래스 넘버
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      nowmyamount:1, //교환할 재료 밑의 '개당'전의 숫자
    nowCgradient:'', //교환하고싶은 재료 '개당'후의 버튼 값
    nowCamount:1, //교환하고 싶은 재료 양
    nowSellAmount:1,
    nowSellPrice:0,
    intoFood:'egg',
    sharedesc:'',
    apiUnit:'',
    apiPrice:0,
    myapi : {
        name : '',
        unit : '',
        price : '',
    },
    trade1api : {
        name : '',
        unit : '',
        price : '',
    },
    trade2api : {
        name : '',
        unit : '',
        price : '',
    },
        foods:[],
        changeFoodsTemp:[
              ],
        changeFoods:[  
            ],
        
        myreflist:[],
        xmldata:[],
    }
  },
  mounted(){
     this.onResize();
  },
    methods:{
        onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
    },
        deleteFoodfromRef(){
            var minusAmount = this.totalShareAmount * -1;
            const deleteFood = {
                amount:minusAmount,
                email:this.userinfo.email,
                expire_date:'',
                name:this.Nowgra.name,
                name_kor:this.Nowgra.name_kor,
                img:this.Nowgra.img,
            };
            axios
            // .post(`http://localhost:9999/food/api/myref/delete`,deleteFood)
            .post(`https://i3b301.p.ssafy.io:9999/food/api/myref/delete`,deleteFood)
            .then((response)=>{
                // // console.log(response);
                window.location.reload();
            })
        },
        fillFoodtoRef(){
        const registFood = {
            amount:this.fillFoodNum,
            email:this.userinfo.email,
            expire_date:this.fillFoodExpireDate,
            name:this.selectedFood.name,
            name_kor:this.selectedFood.name_kor,
            img:this.selectedFood.img
            // food:{name:this.selectedFood.name,name_kor:this.selectedFood.name_kor,img:this.selectedFood.img,expire_date:this.fillFoodExpireDate,amount:this.fillFoodNum},
        };
        axios
        // .post(`http://localhost:9999/food/api/myref/regist`,registFood)
        .post(`https://i3b301.p.ssafy.io:9999/food/api/myref/regist`,registFood)
        .then((response)=>{
            // // console.log(response);
            Swal.fire({
                icon: 'success',
                title: registFood.name_kor+"(이)가 냉장고에 들어갔습니다!",
            })
            window.location.reload();
        })
        .catch((error)=>{
                // console.log(error.response);
                
        })   

        // this.foods.push({name:this.selectedFood.name,name_kor:this.selectedFood.name_kor,img:this.selectedFood.img,expire_date:this.fillFoodExpireDate,amount:this.fillFoodNum})
        $('.registMaterial').css('display','none');
        },
        closeGetOneFood(){
            $('.selectfood').css('display','none');
        },
        getFood:function(){
            $('.selectfood').css('display','block');
        },
        addFood:function(food){
            Swal.fire({
                icon: 'success',
                title: food.name_kor+'을(를) 선택했습니다.'
            })
            this.nowCgradient = food.name_kor;
            $('.selectfood').css('display','none');
            if($('.registMaterial').css('display')!='none'){
                $('.fillBtn').text(food.name_kor);
                this.selectedFood = food;
            }else{
                $('.setFood').text(food.name_kor);
                this.selectedFood = food;
            }
        },
            openregistMater: function () {
                this.closeShare();
                $('.registMaterial').css('display','unset');
                $('#FillBtn').css('display','none');
            },
            closeregistMater:function(){
                $('.registMaterial').css('display','none');
                $('#FillBtn').css('display','unset');
            },
            getSrc:function(food) {
               var src = '../../assets/images/food/'+food+'.png';
               alert(src);
                return src;
            },
            
            openShare:function(sendData){
                // console.log("openshare");
                this.closeregistMater();
                var nowfood = sendData.nowfood;
                var index = sendData.index;
                this.Nowgra_kor = nowfood.name_kor;
                this.Nowgra = nowfood;
                this.nowmyamount = 1;
                this.selectedFood = '';
                this.nowCamount= 1;
                $('.setFood').text("");
                $('#shareField').css('display','unset');
                $('#FillBtn').css('display','none');
                this.NowClassNum = index;
                var className = '.F'+(this.NowClassNum+1);
                var classN = 'F'+(this.NowClassNum+1);
                this.intoFood =  this.Nowgra.name;
                $('#justMoveImg').attr('src',nowfood.img);
                $('#justMove').attr('class',classN);
                $('#justMove').css('display','unset');
                this.intoFood = this.Nowgra.name;
                this.changeFoodsTemp = [];
                this.myapi = {
                    name : '',
                    unit : '',
                    price : '',
                };
                this.trade1api = {
                    name : '',
                    unit : '',
                    price : '',
                };
                this.trade2api = {
                    name : '',
                    unit : '',
                    price : '',
                };
                    this.apiPrice = '';
                for(var i=0; i<this.xmldata.price.length;i++){
                    var tF = this.xmldata.price[i];
                    var tFname = tF.productName.split('/')[0];
                    if(tF.product_cls_code == '01' ){
                        if(tFname==this.Nowgra.name_kor){
                            this.myapi.name = tFname;
                            this.myapi.unit = tF.unit;
                            this.myapi.price = tF.dpr1;
                        }
                    }
                }
            },
            openShareBox:function(){
                if($('.sharebox').css('display')=='none'){
                    $('.sharebox').css('display','block');
                    $('.shareButton').css('background-color','rgb(160,212,105)');
                }else{
                     $('.sharebox').css('display','none');
                    $('.shareButton').css('background-color','#80808066');
                }
            },
            closeShare:function(){
                $('#shareField').css('display','none');
                $('#FillBtn').css('display','unset');
            },
            addChangeGradient:function(){
               
                var sum = 0;
                if(this.changeFoodsTemp.length<2){
                    if(Number(this.totalShareAmount)<=Number(this.Nowgra.amount)){
                        if(Number(this.nowmyamount)>Number(this.totalShareAmount)){
                            Swal.fire({
                                icon: 'error',
                                title: '교환하고싶은 양이 총 공유양보다 많으면 안됩니다!',
                            })
                        this.nowmyamount = this.totalShareAmount;
                    }else{
                        if(this.nowmyamount>=1 && (this.selectedFood!='')&&this.nowCamount>=1){

                            var sellPrice = Number(this.nowSellPrice);
                            var sellAmount = Number(this.nowSellAmount);
                            this.changeFoodsTemp.push({
                                Mygradient:this.Nowgra.name,
                                Mygradient_kor:this.Nowgra.name_kor,
                                myamount:this.nowmyamount,
                                Cgradient:this.selectedFood.name,
                                Cgradient_kor:this.selectedFood.name_kor,
                                Camount:this.nowCamount,
                            });
                        }else{
                            Swal.fire({
                                icon: 'warning',
                                title: '공유할 재료의 양식을 모두 채워주세요',
                            })
                        }

                        for(var i=0; i<this.xmldata.price.length;i++){
                            var tF = this.xmldata.price[i];
                            var tFname = tF.productName.split('/')[0];
                            if(tF.product_cls_code == '01' ){
                                if(tFname==this.selectedFood.name_kor){
                                    if(this.trade1api.name==''){
                                        this.trade1api.name = tFname;
                                        this.trade1api.unit = tF.unit;
                                        this.trade1api.price = tF.dpr1;
                                    }else{
                                        this.trade2api.name = tFname;
                                        this.trade2api.unit = tF.unit;
                                        this.trade2api.price = tF.dpr1;
                                    }
                                }
                            }
                        }
                    }
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: '가지고 있는 ' + this.Nowgra.name_kor + '보다 많은 양을 공유할 수 없습니다.',
                    })
                    this.totalShareAmount=this.Nowgra.amount;
                }
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: '교환목록에는 최대 2개만 들어갈 수 있습니다',
                    })
                }
            },
            putIntoBasket:function(){
                if(this.sharedesc !=''){
                if(this.changeFoodsTemp.length==2){
                    this.changeFoods.push({
                         email : this.userinfo.email,
                         nickname : this.userinfo.nickname,
                         myfood: this.changeFoodsTemp[0].Mygradient,
                         myfood_kor : this.changeFoodsTemp[0].Mygradient_kor,
                         price: 0,
                         myfoodcount1 : this.changeFoodsTemp[0].myamount,
                         tradefood1 : this.changeFoodsTemp[0].Cgradient,
                         tradefood1_kor : this.changeFoodsTemp[0].Cgradient_kor,
                         tradefoodcount1 : this.changeFoodsTemp[0].Camount,
                         myfoodcount2 : this.changeFoodsTemp[1].myamount,
                         tradefood2 : this.changeFoodsTemp[1].Cgradient,
                         tradefood2_kor : this.changeFoodsTemp[1].Cgradient_kor,
                         tradefoodcount2 : this.changeFoodsTemp[1].Camount,
                         content : this.sharedesc,
                         address : this.userinfo.address,
                     });
                     var shareMotion = 'share'+(this.NowClassNum+1);
                        $('#justMove').addClass(shareMotion);
                        $('.inputFeild').css('display','none');
                }else if(this.changeFoodsTemp.length==1){
                     this.changeFoods.push({
                         email : this.userinfo.email,
                         nickname : this.userinfo.nickname,
                         myfood: this.changeFoodsTemp[0].Mygradient,
                         myfood_kor : this.changeFoodsTemp[0].Mygradient_kor,
                         price: 0,
                         myfoodcount1 : this.changeFoodsTemp[0].myamount,
                         tradefood1 : this.changeFoodsTemp[0].Cgradient,
                         tradefood1_kor : this.changeFoodsTemp[0].Cgradient_kor,
                         tradefoodcount1 : this.changeFoodsTemp[0].Camount,
                         myfoodcount2 : null,
                         tradefood2 : null,
                         tradefood2_kor : null,
                         tradefoodcount2 : null,
                         content : this.sharedesc,
                         address : this.userinfo.address,
                     });
                     shareMotion = 'share'+(this.NowClassNum+1);
                $('#justMove').addClass(shareMotion);
                $('.inputFeild').css('display','none');
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: '교환목록을 채워주세요.',
                    })
                }
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: '장터에 올릴때 들어갈 부가설명을 적어주세요.',
                    })
                }
            },
            openCheckBasket:function(){
                if($('.checkBasket').css('display')=='none'){
                    $('.checkBasket').css('display','unset');
                }else{
                    $('.checkBasket').css('display','none');
                }
                $('#dark').css('display','unset');
            },
            closeCheckBasket:function(){
                $('.checkBasket').css('display','none');
                $('#dark').css('display','none');
            },
            deleteShareList:function(index){
                this.changeFoodsTemp.splice(index,1);
            },
            shareFinish:function(){
                // const shareList = this.changeFoods;
                
                if(this.changeFoods.length>0 && this.userinfo.address){
                    
                    // console.log(typeof(this.changeFoods));
                axios({
                    url:`https://i3b301.p.ssafy.io:9999/food/api/myref/share`,
                    method:'post',
                    data: JSON.stringify(this.changeFoods),
                    headers: config.headers})
                .then((response)=>{
                    // console.log(response);
                    this.$router.push("/store/marketplace");
                })
                .catch((error)=>{
                    // console.log(error.response);
                })
                }
                else if (!this.userinfo.address) {
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
                        icon: 'error',
                        title: '장터에 올릴 재료를 바구니에 넣어주세요.',
                    })
                }
            }
    },
    computed:{

    pageCount () {
      let listLeng = this.listArray.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      
      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.listArray.slice(start, end);
    }
  

    },
    created() {
        if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
        // console.log(this.userinfo);
      }else{
        this.userinfo = store.state.userInfo;
      }
      axios.get(`https://i3b301.p.ssafy.io:9999/food/api/myref/search/`+this.userinfo.email)
        .then(response => {
        //   this.myreflist = response.data.myreflist
          this.foods = response.data.myreflist;
        })
        .catch(error => {
          // console.log(error.response)
        })

        axios
        .get(`https://i3b301.p.ssafy.io:9999/food/api/account/apitest`)
        .then(response => {
            this.xmldata = response.data;
            // console.log(this.xmldata);
            }
        )
  },
}
</script>

<style>
.setFood{
    background-color: gray;
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12); 
    background-color: #f5f5f5;
    border-radius: 4px;
    color:black;
    float: left;
    width: 60px;
    height: 30px;
    overflow: hidden;
}
.longNameBox{
    width: 67px;
    float: left;
    white-space: break-spaces;
    text-overflow: ellipsis;
    overflow: hidden;
    display: block;
    padding: 4px 4px;
    text-align: center;
}
.rootContainer{
    width:360px;
    height:590px;
    background-image: url(../../assets/images/myref.png);
    background-size: contain;
    position:relative;
    overflow-x: hidden;
}
#insideRef{
    width:183px;
    height:452px;
    margin-left: 18px;
    margin-top:82px;
    border: 1px solid transparent;
    position: fixed;
}
.smallboxInside{
    width: 183px;
    border: 1px solid black;
}
#basket{
   position: fixed;
    margin-left: 141px;
    margin-top: 408px;
}
.inputFeild{
    width: 315px;
    background-color: white;
    position: fixed;
    margin-left: 26px;
    margin-top: 25px;
    display: none;
    box-shadow: 0px 0px 26px #00000052;
    z-index:25;
}
.registMaterial{
    width: 143px;
    height: 226px;
    background-color: white;
    position: fixed;
    margin-left: 212px;
    margin-top: 25px;
    display:none;
}
.checkBasket{
    width: 200px;
    height: 226px;
    background-color: white;
    position: fixed;
    margin-left: 152px;
    margin-top: 168px;
    box-shadow: 1px 3px 15px #8080806b;
    display: none;
    z-index: 150;
}
h5{
    font-size: 13px;
    margin: 5px;
    margin-left: 4px;
    float:left;
}
.inputText{
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12); 
    background-color: #f5f5f5;
    border-radius: 4px;
    color:black;
    width:40px;
    height:60px;
    padding:4px;

}
.v-btn:not(.v-btn--round).v-size--default {
    height: 30px;
    min-width: 0px;
    padding: 0 4px;
}
.textArea{
    height: 51px;
    margin-top: 6px;
    padding: 5px 7px;
    border-bottom: 1px solid rgba(224, 224, 224, 0.51);
}
.tempInputButton{
    background-color: rgb(199 230 166);
    width: 100%;
    height: 100%;
    border-radius: 9px;
    box-shadow: 2px 2px 1px #70b526;
    margin-left: -2px;
    font-size: 16px;
}
.tempInputButton:active{
margin-left: 2px;
box-shadow: -2px -2px 1px #70b526;
}
.shareButton{
    height: 36px;
    width: 78px;
    background-color: rgb(160, 212, 105);
    float: left;
    margin-right: 5px;
    padding: 4px 0px;
    font-size: 18px;
    font-weight: bold;
    border-radius: 8px;
    text-shadow: 1px 1px 3px #0000002e;
    box-shadow: 2px 2px 1px #77a942;
}
.deleteButton{
    height: 36px;
    width: 78px;
    background-color: red(160, 212, 105);
    float: left;
    margin-right: 5px;
    padding: 4px 0px;
    font-size: 18px;
    font-weight: bold;
    border-radius: 8px;
    color: white;
    background-color: red;
    text-shadow: 1px 1px 3px #0000002e;
    box-shadow: 2px 2px 1px #ab1b29;
}
.F1{
position:fixed;
margin-left: 15px;
margin-top:10px;
}
.F2{

position:fixed;
margin-left: 108px;
margin-top:10px;
}
.F3{

/* float: left;
margin: 10px 15px; */
position:fixed;
margin-left: 15px;
margin-top:87px;
}
.F4{

/* float: left;
margin: 10px 15px; */
position:fixed;
margin-left: 108px;
margin-top:87px;
}
.F5{

/* float: left;
margin: 46px 15px 10px 15px; */
position:fixed;
margin-left: 15px;
    margin-top: 202px;
}

.F6{

/* float: left;
margin: 46px 15px 10px 15px; */
position:fixed;
    margin-left: 108px;
    margin-top: 202px;
}
.F7{

/* float: left;
margin:20px 106px 10px 15px; */
position:fixed;
    margin-left: 15px;
    margin-top: 291px;
}
.F8{

/* float: left;
margin:14px 106px 10px 15px; */
position:fixed;
margin-left: 15px;
    margin-top: 372px;
}
.changeFood{
    height: 50px;
    border-bottom: 1px solid #8080802e;
    padding: 16px 17px;
}

.share1{
    z-index: 100;
    /* animation-name: move;
    animation-duration: 0.9s; */
    transform: translate(220px,360px);
    transition:all ease 1.4s;
}
.share2{
    z-index: 100;
     transform: translate(108px,360px);
    transition:all ease 1.4s;
}
.share3{
    z-index: 100;
     transform: translate(250px,300px);
    transition:all ease 1.4s;
}
.share4{
    z-index: 100;
     transform: translate(108px,300px);
    transition:all ease 1.4s;
}
.share5{
    z-index: 100;
     transform: translate(250px,250px);
    transition:all ease 1.4s;
}
.share6{
    z-index: 100;
     transform: translate(108px,250px);
    transition:all ease 1.4s;
}
.share7{
    z-index: 100;
     transform: translate(250px,100px);
    transition:all ease 1.4s;
}
.share8{
    z-index: 100;
     transform: translate(108px,0px);
    transition:all ease 1.4s;
}

.comback1{
    z-index: 100;
        transform: translate(-220px,-360px) scale(0,0);
        transition:all ease 0.9s;
}
.comback2{
    z-index: 100;
     transform: translate(108px,360px) scale(0,0);
    transition:all ease 0.8s;
}
.comback3{
    z-index: 100;
     transform: translate(250px,300px) scale(0,0);
    transition:all ease 0.8s;
}
.comback4{
    z-index: 100;
     transform: translate(108px,300px) scale(0,0);
    transition:all ease 0.8s;
}
.comback5{
    z-index: 100;
     transform: translate(250px,250px) scale(0,0);
    transition:all ease 0.8s;
}
.comback6{
    z-index: 100;
     transform: translate(108px,250px) scale(0,0);
    transition:all ease 0.8s;
}
.comback7{
    z-index: 100;
     transform: translate(250px,100px) scale(0,0);
    transition:all ease 0.8s;
}
.comback8{
    z-index: 100;
     transform: translate(108px,0px) scale(0,0);
    transition:all ease 0.8s;
}
</style>
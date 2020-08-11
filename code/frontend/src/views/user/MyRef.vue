<template>
  <div class="rootContainer">
      <div class="selectfood" style="width:360px; height:600px; background-color:white; display:none;">
          <div style="height:50px; width:100%; background-color:white">
              <div style="float:right; margin: 11px;" @click="closeGetOneFood"><v-icon>mdi-close</v-icon></div>
          </div>
          <get-one-food @addfood="addFood"></get-one-food>
      </div>
      <div id="dark" @click="closeCheckBasket()" style="width:100%; height:100%; background-color:#00000075; z-index:99; position:fixed; display:none;"></div>
        <div id="insideRef">
            <div id="justMove" class="F1" style="display:none; position:fixed;">
                    <img :src="require(`../../assets/images/food/${intoFood}.png`)" id="justMoveImg" style="width:100%; height:100%">
                </div>
               <div v-for="(food,index) in foods" :key="(index)">
                <button id="ingradient" type="button" :class="'F'+((index%8)+1)" v-on:click="openShare(food,index)" style="float:left"><img style="width:100%; height:auto;" :src="require(`../../assets/images/food/${food.img}.png`)">
                
                </button>
            </div>
        </div>
        <div id="basket">
            <!-- <v-btn @click="openCheckBasket()" @mouseleave="closeCheckBasket()"  @mouseup="closeCheckBasket()"  @touchstart="openCheckBasket()"  @touchend="closeCheckBasket()"  @touchcancel="closeCheckBasket()" flat icon width="200px" height="150px"><img style="width:auto; height:150px;" src="../../assets/images/basket.png"></v-btn> -->
            <v-btn @click="openCheckBasket()" icon width="200px" height="150px"><img style="width:auto; height:150px;" src="../../assets/images/basket.png"></v-btn>

        </div>
        <div id="FillBtn" style="position:fixed; margin-left: 256px; margin-top: 10px; display:unset;">
            <v-btn  v-on:click="openregistMater" color="rgb(160,212,105)" width="90px" height="50px" >
                <v-icon >mdi-cart</v-icon>
                <h4>채우기</h4>
            </v-btn>
        </div>
        <div id="shareField" class="inputFeild"> <!-- 바구니에 넣기 -->
            <div style="width: 100%;
    height: 30px;
    background-color: rgba(224, 224, 224, 0.51);
    text-align: center;
    font-weight: bold;
    padding: 6px 0px;
    overflow: hidden;">{{Nowgra.name_kor}} (재고: {{Nowgra.amount}} )<button v-on:click="closeShare" type="button" height="15px" width="15px"  style="float:right;"> <v-icon size="15px">mdi-close</v-icon></button></div>
            <div class="textArea" style="height: 40px; margin-top: 0px; text-align: center; padding: 5px 2px;">
                <!-- <div class="longNameBox">{{Nowgra.name_kor}}</div> <input v-model="totalShareAmount" type="text" class="inputText" style="float:left; width:40px; height:30px;"><h5>개</h5> -->
                <input v-model="totalShareAmount" type="text" class="inputText" style="float:left; width:24px; height:30px;"><h5>개를</h5>
                <div class="shareButton" @click="openShareBox" style="height:100%; width:37px; background-color:#80808066; float:left; margin-right:5px;">공유</div>
                <div class="deleteButton" @click="deleteFoodfromRef" style="height:100%; width:37px; background-color:red; float:left;">삭제</div>
            
            </div>
            <div class="sharebox" style="display:none;">
            <div class="textArea" style="height:24px; padding:0px;">
                <div class="longNameBox" style="width:58px; height:100%; padding:0px 7px;">{{Nowgra.name_kor}}</div>와 교환할 재료
               
            </div>
            <div style="width:100%; height:33px; padding: 4px 6px;">
                    <!-- <input type="text" style="width:25px; height:30px; background-color:gray; color:black;"> -->
                    <input type="text" v-model="nowmyamount" class="inputText" style="float:left;height:24px;"><h5 style="font-size:10px;float:left">개당</h5> 
                    <button type="button" @click="getFood" class="setFood" style="float:left; margin-right:5px;"></button>
                    <input type="text" v-model="nowCamount" class="inputText" style="float:left; width:24px; height:24px;"><h5 style="font-size:10px;float:left">개</h5>
            </div>
                <div style="width:100%; height:21px;">
                    <button v-on:click="addChangeGradient" style="background-color: rgb(160,212,105); width: 100%; height:100% text-align: center;"><v-icon size="15px">mdi-arrow-down-bold</v-icon>추가하기</button>
                </div>
                
            <div style="width:100%; height:67px; background-color:#80808033; overflow:scroll;">
                <div  v-for="(food,index) in changeFoods" :key="index" >
                    <div class="changeFood" v-if="food.Mygradient == Nowgra.name_kor" style="font-size:13px;">
                        <h4 style="float:left;font-size:11px; width:80%; float:left;">{{food.Mygradient}} {{food.myamount}}개당 {{food.Cgradient}} {{food.Camount}}개</h4>
                        <div style="width:20%; float:left;">
                        <button v-on:click="deleteShareList(index)" style="border-radius:5px; background-color:red; width:20px; height:20px;"><v-icon color="white" size="11px">mdi-trash-can-outline</v-icon></button>
                        </div>
                    </div>
            </div>
                
            </div>
            <div class="textArea">
                <textarea v-model="sharedesc" style="width:100%; height:100%; resize: none;" placeholder="공유글에 들어갈 글을 적어주세요"></textarea>
            </div>
            </div>
            <div style="width:100%; height:100px; background-color:black; color:white;">
             {{apiUnit}} 당 {{apiPrice}}원
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
           <div style="width:100%; height:196px; overflow:scroll;">
           <div class="textArea" v-for="(food,index) in changeFoods" :key="index">
                <h4 style="float:left;font-size:10px; width:80%; float:left;">{{food.Mygradient}} {{food.myamount}}개당 {{food.Cgradient}} {{food.Camount}}개</h4>
                <h4 style="float:left;font-size:10px; width:80%; float:left;">{{food.Mygradient}} 1개당 {{food.pricePerOne}}원</h4>
           
           </div>
           </div>
        </div><!-- end of 장바구니 안 보기 -->
        
        <div style="position:fixed; bottom:0; width:360px;">
            <button @click="shareFinish" type="button" style="width:100%; height:40px;background-color:rgb(160, 212, 105); font-weight:bold; color:white; font-size:20px;">공유하기</button>
        
        </div>
  </div>
</template>

<script>
import $ from 'jquery';
import axios from "axios";
import store from '../../vuex/store.js'
import getOneFood from '../Food/getOneFood.vue'

const SERVER_URL = store.state.SERVER_URL;
var convert = require('xml-js')

export default {
    components:{getOneFood},
data() {
    return {
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
    //   foods:[
    //         {name:"egg",name_kor:"계란",img:'egg',expire_date:'2020-09-01',amount:3},
    //         {name:"flour",name_kor:"밀가루",img:'flour',expire_date:'2020-09-01',amount:4},
    //         {name:"milk",name_kor:"우유",img:'milk',expire_date:'2020-09-01',amount:5},
    //         {name:"olive-oil",name_kor:"올리브유",img:'olive-oil',expire_date:'2020-09-01',amount:6},
    //         {name:"potato",name_kor:"감자",img:'potato',expire_date:'2020-09-01',amount:7},
    //         {name:"vanilla",name_kor:"바닐라빈",img:'vanilla',expire_date:'2020-09-01',amount:4},
    //         {name:"sugar",name_kor:"설탕",img:'sugar',expire_date:'2020-09-01',amount:3},
    //         {name:"sweetpotato",name_kor:"고구마",img:'sweetpotato',expire_date:'2020-09-01',amount:4}
    //     ],
        foods:[],
        changeFoodsTemp:[
              ],
        changeFoods:[  
            ],
        
        myreflist:[],
        xmldata:[],
    }
  },
    methods:{
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
            .post(`${SERVER_URL}/myref/delete`,deleteFood)
            .then((response)=>{
                console.log(response);
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
        .post(`${SERVER_URL}/myref/regist`,registFood)
        .then((response)=>{
            console.log(response);
            window.location.reload();
        })
        .catch((error)=>{
                console.log(error.response);
                
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
            alert(food.name_kor+'을(를) 선택했습니다.');
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
            
            openShare:function(nowfood,index){
                this.closeregistMater();
                this.Nowgra_kor = nowfood.name_kor;
                this.Nowgra = nowfood;
                this.nowmyamount = nowfood.myamount;
                this.nowCgradient = nowfood.Cgradient;
                this.nowCamount = nowfood.Camount;
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
                for(var i=0; i<this.xmldata.price.length;i++){
                    var tF = this.xmldata.price[i];
                    var tFname = tF.productName.split('/')[0];
                    if(tF.product_cls_code == '01' && tFname==this.Nowgra.name_kor){
                        this.apiUnit = tF.unit;
                        this.apiPrice = tF.dpr1;
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
                if(this.nowmyamount>=1){
                    if(this.Nowgra.name_kor!='' && this.nowmyamount>=1 && this.nowCgradient!='' &&this.nowCamount>=1){
                        if(this.nowmyamount>this.totalShareAmount){
                            alert("교환하고싶은 양이 총 공유양보다 많으면 안됩니다!")
                        this.nowmyamount = this.totalShareAmount;
                    }else{
                        var sellPrice = Number(this.nowSellPrice);
                        var sellAmount = Number(this.nowSellAmount);
                        this.changeFoods.push({
                            Mygradient:this.Nowgra.name_kor,
                            myamount:this.nowmyamount,
                            Cgradient:this.nowCgradient,
                            Camount:this.nowCamount,
                            pricePerOne:(sellPrice/sellAmount),
                            })
                            this.nowmyamount = 1;
                            this.nowCgradient = '';
                            $('.setFood').text("");
                            this.nowCamount = 1;
                            var shareMotion = 'share'+(this.NowClassNum+1);
                            $('#justMove').addClass(shareMotion);
                            
                    }
                }else{
                    alert('교환하려는 물품과 교환 비율을 정확히 기입해주세요.');
                }
                }else{
                    alert('공유하려는 '+this.Nowgra.name_kor+'의 양을 1개이상 적어주세요.');
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
                const shareList = this.changeFoods;
                console.log(shareList);
                axios
                .post(`${SERVER_URL}/myref/share`,shareList)
                .then((response)=>{
                    console.log(response);
                    this.$router.push("/");
                })
                .catch((error)=>{
                    console.log(error.response);
                })
            }
    },
    created() {
        if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
        console.log(this.userinfo);
      }else{
        this.userinfo = store.state.userInfo;
      }
      axios.get(`${SERVER_URL}/myref/search/`+this.userinfo.email)
        .then(response => {
        //   this.myreflist = response.data.myreflist
          this.foods = response.data.myreflist;
          console.log(response.data.myreflist);
        })
        .catch(error => {
          console.log(error.response)
        })

        axios.get('https://www.kamis.or.kr/service/price/xml.do?action=dailySalesList&p_cert_key=73081fa5-fa86-492a-b3f3-905e315da6ac&p_cert_id=1137&p_returntype=json')
        .then((response) => {
            var xml = response.data;
        var json = convert.xml2json(xml, { compact: true });
        this.xmldata = JSON.parse(json);
            console.log(this.xmldata);
        })
        .catch((error)=>{
                    console.log(error.response);
        })
      
    
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
    width: 33px;
    height: 24px;
    overflow: hidden;
}
.longNameBox{
    width: 67px;
    height: 30px;
    float: left;
    white-space: break-spaces;
    text-overflow: ellipsis;
    overflow: hidden;
    display: block;
    padding: 4px 4px;
}
.rootContainer{
    width:100%;
    height:100%;
    background-image: url(../../assets/images/myref.png);
    background-size: contain;
    position:relative;
}
#insideRef{
    width: 183px;
    height: 452px;
    border: 1px solid transparent;
    position: fixed;
    margin-left: 18px;
    margin-top: 82px;
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
    width: 143px;
    background-color: white;
    position: fixed;
    margin-left: 212px;
    margin-top: 25px;
    display:none;
    
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
    width:24px;
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
.F1{
width: 60px;
height: 60px;
position:fixed;
margin-left: 15px;
margin-top:10px;
}
.F2{
width: 60px;
height: 60px;
position:fixed;
margin-left: 108px;
margin-top:10px;
}
.F3{
width: 60px; 
height: 60px;
/* float: left;
margin: 10px 15px; */
position:fixed;
margin-left: 15px;
margin-top:87px;
}
.F4{
width: 60px;
height: 60px;
/* float: left;
margin: 10px 15px; */
position:fixed;
margin-left: 108px;
margin-top:87px;
}
.F5{
width: 60px;
height: 60px;
/* float: left;
margin: 46px 15px 10px 15px; */
position:fixed;
margin-left: 15px;
    margin-top: 202px;
}

.F6{
width: 60px;
height: 60px;
/* float: left;
margin: 46px 15px 10px 15px; */
position:fixed;
    margin-left: 108px;
    margin-top: 202px;
}
.F7{
width: 60px;
height: 60px;
/* float: left;
margin:20px 106px 10px 15px; */
position:fixed;
    margin-left: 15px;
    margin-top: 291px;
}
.F8{
width: 60px;
height: 60px;
/* float: left;
margin:14px 106px 10px 15px; */
position:fixed;
margin-left: 15px;
    margin-top: 372px;
}
.changeFood{
    height:38px;
    border-bottom: 1px solid #8080802e;
    padding: 3px;
}

.share1{
    z-index: 100;
    /* animation-name: move;
    animation-duration: 0.9s; */
    transform: translate(220px,360px) scale(0,0);
    transition:all ease 0.9s;
}
.share2{
    z-index: 100;
     transform: translate(108px,360px) scale(0,0);
    transition:all ease 0.8s;
}
.share3{
    z-index: 100;
     transform: translate(250px,300px) scale(0,0);
    transition:all ease 0.8s;
}
.share4{
    z-index: 100;
     transform: translate(108px,300px) scale(0,0);
    transition:all ease 0.8s;
}
.share5{
    z-index: 100;
     transform: translate(250px,250px) scale(0,0);
    transition:all ease 0.8s;
}
.share6{
    z-index: 100;
     transform: translate(108px,250px) scale(0,0);
    transition:all ease 0.8s;
}
.share7{
    z-index: 100;
     transform: translate(250px,100px) scale(0,0);
    transition:all ease 0.8s;
}
.share8{
    z-index: 100;
     transform: translate(108px,0px) scale(0,0);
    transition:all ease 0.8s;
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
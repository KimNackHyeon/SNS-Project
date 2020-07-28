<template>
  <div class="rootContainer">
        <div id="insideRef">
               <div v-for="(food,index) in foods" :key="(food,index)">
                <button id="Gradient" type="button" :class="'F'+((index%8)+1)" v-on:click="openShare(food.gra_kor,index)" style="float:left"><img style="width:100%; height:auto;" :src="require(`../../assets/images/food/${food.gradient}.png`)">
                </button>
            </div>
        </div>
        <div id="basket">
            <v-btn @mousedown="openCheckBasket()" @mouseleave="closeCheckBasket()"  @mouseup="closeCheckBasket()"  @touchstart="openCheckBasket()"  @touchend="closeCheckBasket()"  @touchcancel="closeCheckBasket()" flat icon width="200px" height="150px"><img style="width:auto; height:150px;" src="../../assets/images/basket.png"></v-btn>
        </div>
        <div id="FillBtn" style="position:fixed; margin-left: 256px;
    margin-top: 10px; display:unset;">
            <v-btn  v-on:click="openregistMater" v-show="!hidden" color="rgb(160,212,105)" width="90px" height="50px" >
                  <v-icon >mdi-cart</v-icon>
                  <h4>채우기</h4>
                </v-btn>
        </div>
        <div id="shareField" class="inputFeild"> <!-- 바구니에 넣기 -->
            <div style="width:100%; height:30px; background-color:rgba(224, 224, 224, 0.51); text-align:center; font-weight:bold;text">{{Nowgra}}<button v-on:click="closeShare" type="button" height="15px" width="15px"  style="float:right;"> <v-icon size="15px">mdi-close</v-icon></button></div>
            <div class="textArea">
                <div class="longNameBox">{{Nowgra}}</div> <input type="text" class="inputText" style="float:left; width:40px; height:30px;"><h5>개</h5>
            </div>
            <div class="textArea" style="height:24px; padding:0px;">
                <div class="longNameBox" style="width:58px; padding:0px 7px;">{{Nowgra}}</div>와 교환할 재료
            </div>
            <div style="width:100%; height:67px; background-color:#80808033; overflow:scroll;">
                <div class="changeFood" v-for="(food,index) in changeFoodsTemp" :key="food" style="font-size:13px;">
                    <div>
                    <h4 style="float:left;font-size:11px; width:80%; float:left;">{{food.Mygradient}} {{food.myamount}}개당 {{food.Cgradient}} {{food.Camount}}개</h4>
                    <div style="width:20%; float:left;">
                    <button v-on:click="deleteShareList(index)" style="border-radius:5px; background-color:red; width:20px; height:20px;"><v-icon color="white" size="11px">mdi-trash-can-outline</v-icon></button>
                    </div>
                    </div>
            </div>
                <div class="changeFood">
                    <!-- <input type="text" class="inputText" style="float:left; width:24px; height:24px; padding-left:2px;"><h5 style="float:left">개당</h5><v-btn style="float:left; width:32px; height:24px;"></v-btn> <input type="text" class="inputText" style="float:left; width:24px; height:24px; margin-left:4px;"><h5 style="float:left">개</h5> -->
                <input type="text" v-model="nowmyamount" class="inputText" style="float:left; width:24px; height:24px; padding-left:2px;"><h5 style="float:left">개당</h5> <input type="text" v-model="nowCgradient" class="inputText" style="float:left; width:24px; height:24px; margin-left:4px;">
                <input type="text" v-model="nowCamount" class="inputText" style="float:left; width:24px; height:24px; margin-left:4px;"><h5 style="float:left">개</h5>
                
                </div>
                <button><div class="changeFood" style="color:#808080c7; margin:0px 23px 10px 23px;">
                    <v-btn v-on:click="addChangeGradient" color="rgb(160,212,105)" width="80px;"><v-icon size="15px">mdi-arrow-up-bold</v-icon><h5>추가하기</h5></v-btn>
                </div>
                </button>
            </div>
            <div class="textArea">
                <h4>판매가격</h4>
                <input type="text" class="inputText" style="float:left; width:24px; height:24px; padding-left:2px;">
                <h5 style="float:left">개당</h5><input type="text" class="inputText" style="float:left; width:48px; height:24px; margin-left:4px;"><h5 style="float:left">원</h5>
            </div>
            
            <div style="width:100%; height:33px;">
             <button type="button" v-on:click="closeregistMater" style="width:50%; height:33px;background-color:red; font-weight:bold; color:white; font-size:16px;">삭제</button>
             <button type="button" v-on:click="ShareComplete" style="width:50%; height:33px;background-color:rgb(160, 212, 105); font-weight:bold; color:white; font-size:16px;">담기</button>
            </div>
            <div style="width:100%; height:100px; background-color:black;">
                <div style="color:white; font-size:8px; position:absolute; bottom:0; right:0; ">출처 : 농산물 유통정보 KAMIS</div>
            
            </div>
        </div><!-- end of 바구니에 넣기 -->

        <div class="registMaterial"> <!-- 채우기 박스 -->
            <div style="width:100%; height:30px; background-color:rgba(224, 224, 224, 0.51); text-align:center; font-weight:bold;text">채우기<button v-on:click="closeregistMater" type="button" height="15px" width="15px"  style="float:right;"> <v-icon size="15px">mdi-close</v-icon></button></div>
            <div style="width:100%; height:190px;">
                <div class="textArea">
                    <h5>넣을 재료</h5><v-btn style="float:left; width:65px; height:30px;"></v-btn>
                </div>
                <div class="textArea">
                    <h5>넣을 개수</h5><input type="text" class="inputText" style="float:left; width:40px; height:30px;"><h5>개</h5>
                </div>
                <div class="textArea" style="height:500px">
                    <h5>구매 날짜</h5>
                    <div style="width:60%; float:left; margin-left:27px; height:15px;">
                        <v-menu ref="menu" v-model="menu" :close-on-content-click="false"
                            :return-value.sync="date"  transition="scale-transition" offset-y  min-width="290px" >
                            <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                                v-model="date"
                                readonly
                                v-bind="attrs"
                                v-on="on"
                                style="font-size:10px; margin:0px; padding:0px; width:100%; height:30px; background-color:#f5f5f5;"
                                ></v-text-field>
                            </template>
                            <v-date-picker v-model="date" no-title scrollable>
                            <v-spacer></v-spacer>
                            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                            </v-date-picker>
                        </v-menu>
                    </div>
                        <v-icon color="#e0e0e0" style="float:left; padding-top:5px;">mdi-calendar</v-icon>
                </div>
            </div>
            <button type="button" v-on:click="closeregistMater" style="width:100%; height:33px;background-color:rgb(160, 212, 105); font-weight:bold; color:white; font-size:16px;">냉장고에 넣기</button>
        </div><!-- end of 채우기 -->

        <div class="checkBasket"> <!-- 장바구니 안 보기 -->
            <div style="width:100%; height:30px; background-color:rgba(224, 224, 224, 0.51); text-align:center; font-weight:bold; padding-top:5px; overflow:scroll;">공유 바구니</div>
           <div class="textArea" v-for="food in changeFoods" :key="food" >
                    <h4 style="float:left;font-size:11px; width:80%; float:left;">{{food.Mygradient}} {{food.myamount}}개당 {{food.Cgradient}} {{food.Camount}}개</h4>
           </div>
        </div><!-- end of 장바구니 안 보기 -->
        
        <div style="position:fixed; bottom:0; width:360px;">
            <button type="button" style="width:100%; height:40px;background-color:rgb(160, 212, 105); font-weight:bold; color:white; font-size:20px;">공유하기</button>
        
        </div>
  </div>
</template>

<script>
import $ from 'jquery';

export default {
data: () => ({
        Nowgra : '',
        NowClassNum : 1, //클릭한 칸의 클래스 넘버
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      nowmyamount:'',
    nowCgradient:'',
    nowCamount:0,
      foods:[
            {gradient:"egg",gra_kor:"계란fkfkfkfk"},
            {gradient:"flour",gra_kor:"밀가루"},
            {gradient:"milk",gra_kor:"우유"},
            {gradient:"olive-oil",gra_kor:"올리브유"},
            {gradient:"potato",gra_kor:"감자"},
            {gradient:"vanilla",gra_kor:"바닐라빈"},
            {gradient:"sugar",gra_kor:"설탕"},
            {gradient:"sweetpotato",gra_kor:"고구마"}
        ],
        changeFoodsTemp:[
              ],
        changeFoods:[
            {Mygradient:"egg",Mygra_kor:"계란", myamount:5, Cgradient:"milk",Cgra_kor:"우유", Camount:1},
            {Mygradient:"potato",Mygra_kor:"감자", myamount:3, Cgradient:"milk",Cgra_kor:"우유", Camount:1},
            {Mygradient:"egg",Mygra_kor:"계란", myamount:5, Cgradient:"sweetpotato",Cgra_kor:"고구마", Camount:1},             
            ],

    }),
    methods:{
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
            openShare:function(now,index){
                this.closeregistMater();
                this.Nowgra = now;
                $('#shareField').css('display','unset');
                $('#FillBtn').css('display','none');
                this.NowClassNum = index;
            },
            closeShare:function(now){
                this.Nowgra = now;
                $('#shareField').css('display','none');
                $('#FillBtn').css('display','unset');
            },
            addChangeGradient:function(){
                this.changeFoodsTemp.push({
                    Mygradient:this.Nowgra,
                    myamount:this.nowmyamount,
                    Cgradient:this.nowCgradient,
                    Camount:this.nowCamount
                })
            },
            ShareComplete:function(){
                var className = '.F'+(this.NowClassNum+1);
                this.closeShare();
                var shareMotion = 'share'+(this.NowClassNum+1);
                $(className).addClass(shareMotion);
                this.changeFoods.push(this.changeFoodsTemp);
                var afterclass = className+shareMotion;
                var combackclass = 'comback'+(this.NowClassNum+1);
                $('#Gradient').addClass(combackclass);

            },
            openCheckBasket:function(){
                $('.checkBasket').css('display','unset');
            },
            closeCheckBasket:function(){
                $('.checkBasket').css('display','none');
            },
            deleteShareList:function(index){
                this.changeFoodsTemp.splice(index,1);
            }
    }
}
</script>

<style>
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
    height: 362px;
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
    width: 143px;
    height: 226px;
    background-color: white;
    position: fixed;
    margin-left: 212px;
    margin-top: 168px;
    box-shadow: 1px 3px 15px #8080806b;
    display: none;
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
    width:60px;
    height:60px;
    padding:4px;

}
.v-btn:not(.v-btn--round).v-size--default {
    height: 30px;
    min-width: 0px;
    padding: 0 4px;
}
.textArea{
    height: 45px;
    margin-top: 6px;
    padding: 5px 7px;
    border-bottom: 1px solid rgba(224, 224, 224, 0.51);
}
.F1{
width: 60px;
height: 60px;
float: left;
margin: 10px 15px;

}
.F2{
width: 60px;
height: 60px;
float: left;
margin: 10px 15px;
}
.F3{
width: 60px; 
height: 60px;
float: left;
margin: 10px 15px;
}
.F4{
width: 60px;
height: 60px;
float: left;
margin: 10px 15px;
}
.F5{
width: 60px;
height: 60px;
float: left;
margin: 46px 15px 10px 15px;
}
.F6{
width: 60px;
height: 60px;
float: left;
margin: 46px 15px 10px 15px;
}
.F7{
width: 60px;
height: 60px;
float: left;
margin:20px 106px 10px 15px;
}
.F8{
width: 60px;
height: 60px;
float: left;
margin:14px 106px 10px 15px;
}
.changeFood{
    height:30px;
    border-bottom: 1px solid #8080802e;
    padding: 3px;
}

.share1{
    z-index: 100;
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
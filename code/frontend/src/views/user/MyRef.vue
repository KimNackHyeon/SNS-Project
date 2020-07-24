<template>
  <div class="rootContainer">
        <div id="insideRef">
               <div v-for="food in foods" :key="food">
                <button type="button" v-on:click="openShare(food.gra_kor)" style="float:left"><img :class="'F'+food.num" :src="require(`../../assets/images/food/${food.gradient}.png`)">
                </button>
            </div>
            <!-- <div class="smallboxInside" style="height: 76px;"></div>
            <div class="smallboxInside" style="height: 79px;"></div>
            <div class="smallboxInside" style="height: 117px;"></div>
            <div class="smallboxInside" style="height: 89px; width:92px;"></div>
            <div class="smallboxInside" style="height: 91px; width:92px;"></div> -->
        </div>
        <div id="basket">
            <v-btn flat icon width="200px" height="150px"><img style="width:auto; height:150px;" src="../../assets/images/basket.png"></v-btn>
        </div>
        <div id="FillBtn" style="position:fixed; margin-left: 256px;
    margin-top: 10px; display:unset;">
            <v-btn  v-on:click="registMater" v-show="!hidden" color="rgb(160,212,105)" width="90px" height="50px" >
                  <v-icon >mdi-cart</v-icon>
                  <h4>채우기</h4>
                </v-btn>
        </div>
        <div class="inputFeild"> <!-- 바구니에 넣기 -->
            <div style="width:100%; height:30px; background-color:rgba(224, 224, 224, 0.51); text-align:center; font-weight:bold;text">{{Nowgra}}<button v-on:click="closeShare" type="button" height="15px" width="15px"  style="float:right;"> <v-icon size="15px">mdi-close</v-icon></button></div>
            <div class="textArea">
                <h4 style="font-size:15px;">공유할 개수</h4>
            </div>
        </div><!-- end of 바구니에 넣기 -->

        <div class="registMaterial">
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
        </div><!-- end of registMeterial -->
        
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
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      foods:[
            {gradient:"egg",gra_kor:"계란",num:1},
            {gradient:"flour",gra_kor:"밀가루",num:2},
            {gradient:"milk",gra_kor:"우유",num:3},
            {gradient:"olive-oil",gra_kor:"올리브유",num:4},
            {gradient:"potato",gra_kor:"감자",num:5},
            {gradient:"vanilla",gra_kor:"바닐라빈",num:6},
            {gradient:"sugar",gra_kor:"설탕",num:7},
            {gradient:"sweetpotato",gra_kor:"고구마",num:8}
        ],
        
    }),
    methods:{
            registMater: function () {
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
            openShare:function(now){
                this.Nowgra = now;
                $('.inputFeild').css('display','unset');
                $('#FillBtn').css('display','none');
            },
            closeShare:function(now){
                this.Nowgra = now;
                $('.inputFeild').css('display','none');
                $('#FillBtn').css('display','unset');
            }
    }
}
</script>

<style>
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
    min-width: 50px;
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
margin:20px 106px 10px 15px;
}
</style>
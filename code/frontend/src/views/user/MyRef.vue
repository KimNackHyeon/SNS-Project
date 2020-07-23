<template>
  <div class="rootContainer">
        <div id="insideRef">
            <div class="smallboxInside" style="height: 76px;"></div>
            <div class="smallboxInside" style="height: 79px;"></div>
            <div class="smallboxInside" style="height: 117px;"></div>
            <div class="smallboxInside" style="height: 89px; width:92px;"></div>
            <div class="smallboxInside" style="height: 91px; width:92px;"></div>
        </div>
        <div id="basket">
            <v-btn flat icon width="200px" height="150px"><img style="width:auto; height:150px;" src="../../assets/images/basket.png"></v-btn>
        </div>
        <div style="position:absolute; bottom:0; width:360px;">
            <button type="button" style="width:100%; height:40px;background-color:rgb(125, 197, 49); font-weight:bold; color:white; font-size:20px;">공유하기</button>
        </div>
        <div style="position:fixed; margin-left: 256px;
    margin-top: 10px;">
            <v-btn v-show="!hidden" color="rgb(160,212,105)" width="90px" height="50px" >
                  <v-icon >mdi-cart</v-icon>
                  <h4>채우기</h4>
                </v-btn>
        </div>
        <div class="inputFeild">

        </div>
        <div class="registMaterial">
            <div style="width:100%; height:30px; background-color:rgba(224, 224, 224, 0.51); text-align:center; padding:5px; font-weight:bold;text">채우기</div>
            <div>
                <h4>넣을재료</h4><input>
            </div>
        </div>
  </div>
</template>

<script>
export default {

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
    border: 1px solid black;
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
    display: none;
}
.registMaterial{
    width: 143px;
    height: 260px;
    background-color: white;
    position: fixed;
    margin-left: 212px;
    margin-top: 25px;
}
</style>
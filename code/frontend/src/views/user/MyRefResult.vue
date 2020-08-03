<template>
  <div></div>
</template>

<script>
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
        changeFoods:[
             {Mygradient:"egg",Mygra_kor:"계란", myamount:5, Cgradient:"milk",Cgra_kor:"우유", Camount:1},
             {Mygradient:"potato",Mygra_kor:"감자", myamount:3, Cgradient:"milk",Cgra_kor:"우유", Camount:1},
             {Mygradient:"egg",Mygra_kor:"계란", myamount:5, Cgradient:"sweetpotato",Cgra_kor:"고구마", Camount:1},             
              ],
    }),
}
</script>

<style>

</style>
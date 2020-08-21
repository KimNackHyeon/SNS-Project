<template>
  <div >
      <div>
          <span style="position: fixed; margin: -32px 76px; font-weight: bold;" class="page-count">{{ pageNum + 1 }} / {{ pageCount }}</span>
      <div id="justMove" class="F1" style="display:none; position:fixed; width:60px; height:60px;">
                    <img :src="require(`../../assets/images/food/${intoFood}.png`)" id="justMoveImg" style="width:100%; height:100%">
            </div>
      <div v-for="(p,index) in paginatedData" :key="index">
                
                <button id="ingradient" type="button" :class="'F'+((index%8)+1)" v-on:click="openShare(p,index)" style="float:left; width:60px; height:60px;"><img style="width:100%; height:auto;" :src="require(`../../assets/images/food/${p.img}.png`)">
                
                </button>
      </div>
      </div>
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn" style="cursor:pointer;">
        <v-icon size="40px">mdi-arrow-left-drop-circle</v-icon>
      </button>
      
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn" style="cursor:pointer; margin-left: 154px;">
        <v-icon size="40px">mdi-arrow-right-drop-circle</v-icon>
      </button>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: 'ref-paging',
 
  data () {
    return {
       frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
      pageNum: 0,
       NowClassNum:1,
        intoFood:'egg',
        Nowgra:'',
    }
  },
  props: {
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 8
    },
    
  },
  mounted(){
 this.onResize();

  },
  methods: {
      onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
    },
    nextPage () {
      this.pageNum += 1;
      $('#justMove').css('display','none');
    },
    prevPage () {
      this.pageNum -= 1;
      $('#justMove').css('display','none');
    },
    openShare:function(nowfood,index){
      $('#justMove').css('display','block');
          this.Nowgra = nowfood;
              this.intoFood =  this.Nowgra.img;
                var sendData = {nowfood : nowfood, index : index};
                this.$emit('openShare',sendData);
            },
  },
  computed: {
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
  }
}
</script>

<style>
.btn-cover {
  width: 50px;
    height: 50px;
    /* letter-spacing: 0.5px; */
    position: fixed;
    margin-top: 151px;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
.page-btn{
    width: 50px;
    height: 50px;
    margin-top: 153px;
    margin-left: -25px;
    position: fixed;
}
</style>
<template>
  <div>
    <div style="height:60px">Header</div>
    <div style="height:40px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <router-link to="/feed/main">
        <v-btn icon color="gray" style="background-color: #f1f3f5; border-radius: unset; height: 38px;">
          <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
        </v-btn>
      </router-link>
    </div>
    <div style="overflow: scroll; height: 540px;">
      <div style="overflow: hidden; padding: 5px; border-bottom: 1px solid lightgray;">
        <div style="float: left;">
          <v-avatar size="35"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar>
          <h4 style="display:inline-block; padding-left:5px">사용자 이름</h4>
        </div>
        <div style="float: right; padding">
          <v-btn icon color="lightgray">
            <v-icon size="30px">mdi-account-plus</v-icon>
          </v-btn>
        </div>
      </div>
      <!-- 재료 -->
      <div style="overflow: hidden; border-bottom: 1px solid lightgray">
        <!-- 나에게 있는 재료 -->
        <div style="float: left; padding: 5px 10px; overflow: scroll; width: 60%; text-align: center">
          <h5 style="text-align: center;">나에게 있는 재료</h5>
          <div style="padding: 5px 10px; display: flex;">
            <div class="food" v-for="(food, i) in inFoods" :key="i">
              <img :src="require(`../../assets/images/food/${food.img}.png`)" alt="flour" style="width: 30px; height: 30px;">
              <h5 class="food-name">{{food.name}}</h5>
              <h6>{{food.amount}}</h6>
            </div>
          </div>
        </div>
        <!-- 나에게 없는 재료 -->
        <div style="float: right; background-color: #a0d469; padding: 5px 10px; overflow: scroll; width: 40%;">
          <h5 style="text-align: center;">나에게 없는 재료</h5>
          <div style="padding: 5px 10px; display: flex;">
            <div class="food" v-for="(food, i) in outFoods" :key="i">
              <v-btn icon @click="onBuyingBtn(food)">
                <img :src="require(`../../assets/images/food/${food.img}.png`)" alt="flour" style="width: 30px; height: 30px;">
              </v-btn>
              <div v-if="food.showBtn">
                <h5>{{food.name}}</h5>
                <div>
                  <v-btn>공동<br>구매</v-btn>
                  <v-btn>물물<br>교환</v-btn>
                </div>
              </div>
              <h5 class="food-name">{{food.name}}</h5>
              <h6>{{food.amount}}</h6>
            </div>
          </div>
        </div>
      </div>
      <!-- 글 내용 -->
      <div>
        <div style="text-align: center; padding: 5px">
          <h2>레시피 제목</h2>
        </div>
        <div>
          <img src="../../assets/images/food1.jpg" alt="food" style="width: 100%; height: 300px;">
          <p style="padding-top: 10px">1. 실온 버터는 마요네즈 상태로 풀어준 뒤, 설탕과 소금을 넣고 크림화를 합니다.</p>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      inFoods: [
        {img: "flour", name: "박력분", amount: "105g"},
        
      ],
      outFoods: [
        {img: "egg", name: "달걀", amount: "1개", showBtn: false},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개", showBtn: false},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개", showBtn: false},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개", showBtn: false},
        {img: "vanilla", name: "바닐라빈", amount: "1/4개", showBtn: false},
      ],
      onBuying: false
    }
  },
  methods: {
    onBuyingBtn(food) {
      food.showBtn = !food.showBtn
    }
  },
}
</script>

<style scoped>
  .left-icon:hover {
    color: #a0d469;
  }
  .food {
    padding-right: 10px;
    text-align: center;
  }
  .food h6 {
    color: gray;
  }
  .food-name {
    font-weight: bold;
  }
  h5{
    font-size: unset;
    margin:unset;
    margin-left:unset;
    float:unset;
  }
</style>
<template>
  <div style="width:100%; height:100%;">
      <div class="width:100%; height:30%; margin:19px auto;"> <!-- 검색 -->
      <div class="searchBox">
        <v-combobox
          v-on:input="searchFood=event.target.value"
          :hide-no-data="!search"
          hide-details
          :search-input.sync="search"
          hide-selected
          label="Search"
          multiple
          small-chips
          solo
          dense
          deletable-chips
          class="searchBarBtn"
          background-color="transparent"
        ></v-combobox>
        <ul tabindex="0" :class="{show: isActive}" v-on:mouseover="removeValue">
            <li tabindex="-1" v-for="(el,index) in filterList" :key="index" v-onclick="changeValue(el.name)" v-on:keyup.enter="selectValue('enter',el.name)">
                <span>{{el.name}}</span>
            </li>
        </ul>
        <div style="overflow: hidden;">
          <v-btn icon>
            <v-icon class="right-icon">mdi-magnify</v-icon>
          </v-btn>
        </div>
      </div>
      </div> <!-- end of 검색 --> 

      <div style="height:70%; border-top:1px solid #9e9e9e6b;overflow-y: scroll;">
        <div class="card" v-for="(food,index) in allFoodList" :key="index">
        <img style="margin:10px auto 5px auto;width:60px; height:auto; font-size:20px;" v-bind:src="require(`../assets/images/food/${food.name}.png`)"/>
        <h5>{{ food.name_kor }}</h5>
    </div>
      </div>
      <div style="width:100%; height:20%; background-color:rgb(160,212,105)">추가</div>
  </div>
</template>

<script>

export default {
         
    data() {
        return {
            isActive:false,
            searchFood:'',
      searchData: [],
        allFoodList : [
            {name:'egg',
            name_kor:'계란',
            img:'egg'},
            {name:'flour',
            name_kor:'밀가루',
            img:'flour'
            },
            {name:'milk',
            name_kor:'우유',
            img:'flour'
            },
            {name:'olive-oil',
            name_kor:'올리브오일',
            img:'olive-oil'
            },
            {name:'onion',
            name_kor:'양파',
            img:'onion'
            },
            {name:'potato',
            name_kor:'감자',
            img:'potato'
            },
            {name:'sugar',
            name_kor:'설탕',
            img:'sugar'
            },
            {name:'sweetpotato',
            name_kor:'고구마',
            img:'sweetpotato'
            },
            {name:'vanilla',
            name_kor:'바닐라빈',
            img:'vanilla'
            },
            {name:'egg',
            name_kor:'계란',
            img:'egg'},
            {name:'egg',
            name_kor:'계란',
            img:'egg'},
            {name:'egg',
            name_kor:'계란',
            img:'egg'},
            {name:'egg',
            name_kor:'계란',
            img:'egg'},
            ],
      items: [],
      model: [],
      search: null,
      
    }
  },
  
  methods: {
  }
}
</script>

<style scoped>
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
    height:30%;
    margin: 3%;
    text-align: center;
    font-size: 20px;
    overflow: hidden;
    transition: .15s all ease-in-out;
    float:left;
}

.card:hover {
      transform: scale(1.1);
    }
</style>
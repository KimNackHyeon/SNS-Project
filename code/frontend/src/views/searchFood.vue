<template>
  <div style="width:100%; height:100%;">
      <div style="width:100%; height:10%;" v-on:keyup.down="selectValue('down')"
       v-on:keyup.up="selectValue('up')"> <!-- 검색 -->
            <div class="search">
          <input class="s" placeholder="'장'을 써보세요" 
                v-on:input="searchQuery=event.target.value">
          <ul class="r" tabindex="0" 
              v-bind:class="{ show: isActive }"
              v-on:mouseover="removeValue">
            <li tabindex="-1" 
                v-for="(el, index) in filterList" 
                v-on:click="changeValue(el.name)"
                v-on:keyup.enter="selectValue('enter', el.name)"
                :key="index">
              <span>{{ el.name }}</span>
            </li>
          </ul>
        </div>
      </div> <!-- end of 검색 --> 

      <div style="height:82%; border-top:1px solid #9e9e9e6b;overflow-y: scroll;">
        <div class="card" v-for="(food,index) in allFoodList" :key="index">
        <div>
          <img style="margin:10px auto 5px auto;width:60px; height:auto; font-size:20px;" v-bind:src="require(`../assets/images/food/${food.name}.png`)"/>
        </div>
        <div>
          {{ food.name_kor }}
        </div>
    </div>
      </div>
      <div style="width:100%; height:8%;">
        <div style="width:100%; height:100%; height: 45px;
    width: 100%;
    background-color: rgb(160,212,105); text-align:center; padding:4px;"><h3>등록하기</h3></div>
      </div>
  </div>
</template>

<script>

export default {
         
    data() {
        return {
            isActive: false,
            searchQuery: '',
            names : [
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
      
    }
  },
  methods: {
    changeValue(str) {
      console.log(`change value: ${str}`);
      this.isActive = false;
      document.querySelector('.s').value = str;
    },
    selectValue(keycode, str) {
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
  },
  computed: {
    filterList:function() {
      const str = this.searchQuery;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      console.log(`typing value: ${str}`);
      if (reg === false && str !== '' && str !== ' ') {
        return this.names.filter((el) => {
          return el.name.match(str);
        });
      } else {
        
        return this.names.filter((el) => {
          return el.name.match(str);
        });
      }
    },
  },
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
</style>
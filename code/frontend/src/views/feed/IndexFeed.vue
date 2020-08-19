<template>
  <div style="width:360px; height:100%">
    <div class="searchBar" style="height: 40px; border: 1px solid lightgray;">
      <!-- 해시태그 검색창 -->
      <div style="height: 100%; overflow-y:hidden;">
        <v-combobox
          v-model="model"
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
          @keyup.enter="change"
          class="searchBarBtn"
          style="overflow-y:hidden;"
        ></v-combobox>
        <div style="overflow: hidden; height: 38px">
          <v-btn icon @click="change">
            <v-icon class="right-icon">mdi-magnify</v-icon>
          </v-btn>
        </div>
      </div>
      <router-link to="/feed/write">
      <div class="writeButton">
        <v-icon color="white">mdi-lead-pencil</v-icon>
        <h4 style="color:whiste; font-size:14px;">글쓰기</h4>
      </div>
      </router-link>
    </div>
    <div class="feed-body" style="width:inheirt; height:92%; padding-bottom: 70px">
        <feed-item ref="feeditem"/>
        <!-- <FeedItem />
        <FeedItem />
        <FeedItem /> -->
    </div>
    
  </div>
</template>

<script>

import $ from 'jquery';
import { mapState } from "vuex";
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import FeedItem from "../../components/feed/FeedItem.vue";


export default {
  
  data () {
    return {
      searchData: [],
      items: [],
      model: [],
      search: null,
    }
  },
  components: { FeedItem },
  watch: {
      model (val, prev) {
        if (val.length === prev.length) return

        this.model = val.map(v => {
          if (typeof v === 'string') {
            v = {text: `#${v}`}

            this.items.push(v)

            this.nonce++
          }

          return v
        })
      },
      search() {
        $('.mdi-magnify').css('color', '#a0d469')
      }
    },

    methods: {
      searchTag(){
        // console.log(this.items);
      },
      change(){
        // console.log(this.model);
        const tags = [];
        this.model.forEach(tag =>{
          tags.push(tag.text);
        })
        this.$refs.feeditem.searchTag(tags);
      }
    //   edit (index, item) {
    //     if (!this.editing) {
    //       this.editing = item
    //       this.index = index
    //     } else {
    //       this.editing = null
    //       this.index = -1
    //     }
    //   },
    //   filter (item, queryText, itemText) {
    //     if (item.header) return false

    //     const hasValue = val => val != null ? val : ''

    //     const text = hasValue(itemText)
    //     const query = hasValue(queryText)

    //     return text.toString()
    //       .toLowerCase()
    //       .indexOf(query.toString().toLowerCase()) > -1
    //   },
    },
};
</script>

<style scoped>
.v-application--wrap {
  position: relative;
}
.writeButton{
  width: 60px;
  height: 60px;
  position: absolute;
  top: 88%;
  left: 82%;
  background-color: rgb(147 203 88);
  z-index: 90;
  border-radius: 30px;
  box-shadow: 7px 7px 10px rgb(0 0 0 / 44%);
  text-align: center;
  padding-top: 7px;
}
.searchBarBtn:hover {
  border: 2px solid #a0d469;
}
.v-input__slot:hover {
  border: unset !important;
}
.v-input__control {
  height: 38px;
}
.searchBarBtn::-webkit-scrollbar {
  height:6px;
}
.searchBarBtn::-webkit-scrollbar-track {
  background-color: transparent;
  height:0px;
}
.searchBarBtn::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: gray;
  height:3px;
}
.searchBarBtn::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
.feed-body::-webkit-scrollbar {
  width:3px;
}
.feed-body::-webkit-scrollbar-track {
  background-color: transparent;
  height:5px;
}
.feed-body::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: gray;
  height:3px;
  width: 10px;
}
</style>
<template>
  <div>
    <div class="searchBar" style="height:40px; border: 1px solid lightgray;">
      <!-- 해시태그 검색창 -->
      <div style="height: 100%;">
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
          class="searchBarBtn"
        ></v-combobox>
        <div style="overflow: hidden;">
          <v-btn icon>
            <v-icon class="right-icon">mdi-magnify</v-icon>
          </v-btn>
        </div>
      </div>
      
    </div>
    <div class="feed-body">
        <FeedItem />
        <!-- <FeedItem />
        <FeedItem />
        <FeedItem /> -->
    </div>
  </div>
</template>

<script>


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
  props: ["keyword"],
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
    },

    methods: {
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


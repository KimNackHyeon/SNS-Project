<template>
  <div>
    <div style="height:60px">Header</div>
    <div class="searchBar" style="height:40px; border: 1px solid lightgray;">
      <!-- 해시태그 검색창 -->
      <div>
        <v-combobox
          v-model="model"
          :filter="filter"
          :hide-no-data="!search"
          hide-details
          :search-input.sync="search"
          hide-selected
          label="Search"
          multiple
          small-chips
          solo
          dense
          style="float: left; width: 89%; border-right: 1px solid lightgray; border-radius: unset;"
        ></v-combobox>
        <div>
          <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
      </div>
      

      <!-- 간단한 검색창 -->
      <!-- <v-text-field
      v-model="searchData"
      label="Search"
      outlined
      clearable
      class="search-input"
      append-icon="mdi-magnify"
      color="#a0d469 "
      dense
      click:append-outer
      hide-details
      style="margin-top:0px; border-radius: unset;"
      ></v-text-field> -->
    </div>
    <div class="feed-body">
        <FeedItem />
        <FeedItem />
        <FeedItem />
        <FeedItem />
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
      // activator: null,
      // attach: null,
      // editing: null,
      // index: -1,
      // nonce: 1,
      // menu: false,
      items: [],
      model: [],
      search: null,
      // x: 0,
      // y: 0,
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


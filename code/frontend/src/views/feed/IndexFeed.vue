<template>
  <div>
    <div >
      <div style="height:60px">Header</div>
      <div style="height:33px">
        <v-text-field
        v-model="searchData"
        label="Search"
        single-line
        clearable
        class="search-input"
        append-icon="mdi-magnify"
        color="#a0d469"
        dense
        click:append-outer
        hide-details
        style="margin-top:0px;"
        ></v-text-field>
      </div>
      
      <div class="feed-body">
          <FeedItem />
          <FeedItem />
          <FeedItem />
          <FeedItem />
      </div>
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
      searchData: ""
    }
  },
  props: ["keyword"],

  components: { FeedItem }
};
</script>


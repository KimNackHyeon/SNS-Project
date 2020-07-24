<template>
  <div>
    <div style="height:60px">Header</div>
    <div style="height:40px">
      <v-text-field
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
      ></v-text-field>
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
      searchData: "",
    }
  },
  props: ["keyword"],
  components: { FeedItem }
};
</script>


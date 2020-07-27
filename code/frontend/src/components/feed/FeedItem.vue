<template>
  <div class="feed-item">
    <div class="feed-profil">
      <div class="feed-user">
        <v-avatar size="35"><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John"></v-avatar>
        <h4 style="display:inline-block; padding-left:5px">사용자 이름</h4>
      </div>
      <div style="height: 45px; float: right;">
        <router-link to="/feed/detail">
          <v-btn icon color="gray" style="background-color: #f1f3f5; border-radius: unset; height: 45px;">
            <v-icon class="left-icon" size="35px">mdi-chevron-right</v-icon>
          </v-btn>
        </router-link>
      </div>
    </div>
    <div>
      <v-carousel show-arrows-on-hover>
        <v-carousel-item
          v-for="(item, i) in items"
          :key="i"
          :src="item.src"
        ><v-chip
          class="ma-2"
          color="#2c2c2c"
          style="float: right; font-size: 12px; height: 28px;">
          {{i + 1}}/{{items.length}} 
        </v-chip></v-carousel-item>
      </v-carousel>
    </div>
    <div style="overflow: hidden; border-bottom: 1px solid lightgray; height: 45px;">
      <div style="float: left;">
        <v-btn icon color="red">
            <v-icon size="30px">mdi-heart</v-icon>
        </v-btn>
        <v-btn icon color="black">
            <v-icon size="30px">mdi-chat-outline</v-icon>
        </v-btn>
      </div>
      <div style="float: right;">
        <v-btn icon color="#a0d469">
            <v-icon size="30px">mdi-bookmark</v-icon>
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";
export default {
  data: () => {
    return { 
      defaultImage, 
      defaultProfile,
      items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
      ],
    };
  }
};
</script>

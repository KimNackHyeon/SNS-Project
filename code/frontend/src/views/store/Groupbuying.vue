<template>
  <div>
  <v-app>
    <v-card flat>
      <v-container fluid style="padding: 0; margin: 0;">
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <div style="border: solid 1px lightgrey">
            <router-link to="/Main">
            <v-btn icon style="margin: 5px">
              <v-icon>mdi-chevron-left</v-icon>
            </v-btn>
            </router-link>
          </div>
          <v-flex>
            <v-toolbar flat style="border: solid 1px lightgrey" height="48px">
              <div class="text-h6 mx-auto">
                공동구매
              </div>
            </v-toolbar>
          </v-flex>
        </v-layout>
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <v-flex>
            <v-toolbar color="rgba(160, 212, 105, 0.5)" flat height="48px">
              <v-checkbox label="거리순 정렬" style="margin-top:18px; margin-right: 18px;">

              </v-checkbox>
              <v-checkbox label="마감임박" style="margin-top:18px">

              </v-checkbox>
            </v-toolbar>
          </v-flex>
          <div style="border: solid 1px lightgrey">
            <v-btn icon style="margin: 5px">
              <v-icon>mdi-magnify</v-icon>
            </v-btn>
          </div>
        </v-layout>
        <div style="padding: 10px; margin: 0; overflow: scroll; height: 544px;" grid-list-lg>
          <v-row dense style="padding: 0;">
            <v-col v-for="buy in buying" :key="buy" :buyings="buying" cols="12">
              <router-link :to="`/store/groupbuying/${ buy.id }`">
                <v-card style="padding: 5px;">
                  <v-row style="padding: 0; margin: 0;">
                    <v-col cols="3" style="padding: 0; padding-right: 8px; border-right: solid 1px lightgray;">
                      <img height="80" width="80" padding="60" :src="buy.picture" style="border-radius: 5px;">
                    </v-col>
                    <v-col cols="6" class="text-left pl-2" style="padding: 0;">
                      <v-card-text style="padding: 0;">{{ buy.title }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">품목 : {{ buy.food }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">동네 : {{ buy.address }}</v-card-text>
                      <v-card-text style="padding: 0; font-size: 10px;">마감일 : {{ buy.date }}</v-card-text>
                    </v-col>
                    <v-col cols="3" style="padding: 0;">
                      <v-card-text class="text-right" style="padding: 16px 0 0; margin-top: 15px; font-size: 29px; height: 0px">{{ buy.members }}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </router-link>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-card>
  </v-app>
</div>
</template>

<script>
export default {
  name: 'Groupbuying',
  data() {
    return {
      buying: [
        {
          id: 1,
          picture: '/img/onion.c1585269.png',
          title: '양파 필요하신분',
          food: '양파',
          address: '대전광역시 유성구 덕명동',
          date: '2020/08/01',
          members: '9/10'
        },
        {
          id: 2,
          picture: '/img/potato.717377a0.png',
          title: '♡감자가 좋아♡',
          food: '감자',
          address: '대전광역시 유성구 덕명동',
          date: '2020/08/01',
          members: '4/5'
        },
        {
          id: 3,
          picture: '/img/onion.c1585269.png',
          title: '양파 급하게 필요하신 분만!',
          food: '양파',
          address: '대전광역시 유성구 덕명동',
          date: '2020/07/16',
          members: '1/3'
        },
        {
          id: 4,
          picture: '/img/egg.b0f12e35.png',
          title: '한밭대 달걀 공구',
          food: '달걀',
          address: '대전광역시 유성구 덕명동',
          date: '2020/08/20',
          members: '2/5'
        },
        {
          id: 5,
          picture: '/img/egg.b0f12e35.png',
          title: '달걀 도매 구매',
          food: '달걀',
          address: '대전광역시 유성구 봉명동',
          date: '2020/08/31',
          members: '11/30'
        },
        {
          id: 6,
          picture: '/img/egg.b0f12e35.png',
          title: '달걀 도매 구매',
          food: '달걀',
          address: '대전광역시 유성구 봉명동',
          date: '2020/08/31',
          members: '11/30'
        }
      ]
    }
  },
}
</script>

<style>
  
</style>
<template>
  <div>
  <v-app>
    <v-card flat>
      <v-container fluid style="padding: 0; margin: 0;">
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <div style="border: solid 1px lightgrey">
            <v-btn icon style="margin: 5px">
              <v-icon>mdi-chevron-left</v-icon>
            </v-btn>
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
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row justify="space-between" style="padding: 0; margin: 0;">
                  <v-col cols="auto" style="padding: 0;">
                    <v-img height="80" width="80" padding="60"  src="../../assets/images/store/onion.png" style="border-radius: 5px;"></v-img>
                  </v-col>
                  <v-col cols="auto" class="text-left pl-0" style="padding: 0; border-left: solid 1px lightgray;">
                    <v-card-text style="padding: 0 0 0 5px;">양파 필요하신분</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">품목 : 양파</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">동네 : 대전광역시 유성구 덕명동</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">마감일 : 2020/08/01</v-card-text>
                  </v-col>
                  <v-col cols="auto" style="padding: 0;">
                    <v-card-text style="padding: 16px 0 0; margin-top: 15px; font-size: 40px; height: 0px">9/10</v-card-text>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row justify="space-between" style="padding: 0; margin: 0;">
                  <v-col cols="auto" style="padding: 0;">
                    <v-img height="80" width="80" padding="60"  src="../../assets/images/store/potato.png" style="border-radius: 5px;"></v-img>
                  </v-col>
                  <v-col cols="auto" class="text-left pl-0" style="padding: 0; border-left: solid 1px lightgray;">
                    <v-card-text style="padding: 0 0 0 5px;">♡감자가 좋아♡</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">품목 : 감자</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">동네 : 대전광역시 유성구 덕명동</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">마감일 : 2020/08/01</v-card-text>
                  </v-col>
                  <v-col cols="auto" style="padding: 0;">
                    <v-card-text style="padding: 16px 0 0; margin-top: 15px; font-size: 40px; height: 0px;">4/5</v-card-text>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row justify="space-between" style="padding: 0; margin: 0;">
                  <v-col cols="auto" style="padding: 0;">
                    <v-img height="80" width="80" padding="60"  src="../../assets/images/store/onion.png" style="border-radius: 5px;"></v-img>
                  </v-col>
                  <v-col cols="auto" class="text-left pl-0" style="padding: 0; border-left: solid 1px lightgray;">
                    <v-card-text style="padding: 0 0 0 5px;">양파 급하게 필요하신 분만!</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">품목 : 양파</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">동네 : 대전광역시 유성구 덕명동</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">마감일 : 2020/07/16</v-card-text>
                  </v-col>
                  <v-col cols="auto" style="padding: 0;">
                    <v-card-text style="padding: 16px 0 0; margin-top: 15px; font-size: 40px; height: 0px">1/3</v-card-text>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row justify="space-between" style="padding: 0; margin: 0;">
                  <v-col cols="auto" style="padding: 0;">
                    <v-img height="80" width="80" padding="60"  src="../../assets/images/store/egg.png" style="border-radius: 5px;"></v-img>
                  </v-col>
                  <v-col cols="auto" class="text-left pl-0" style="padding: 0; border-left: solid 1px lightgray;">
                    <v-card-text style="padding: 0 0 0 5px;">한밭대 달걀 공구</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">품목 : 달걀</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">동네 : 대전광역시 유성구 덕명동</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">마감일 : 2020/08/20</v-card-text>
                  </v-col>
                  <v-col cols="auto" style="padding: 0;">
                    <v-card-text style="padding: 16px 0 0; margin-top: 15px; font-size: 40px; height: 0px">2/5</v-card-text>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row justify="space-between" style="padding: 0; margin: 0;">
                  <v-col cols="auto" style="padding: 0;">
                    <v-img height="80" width="80" padding="60"  src="../../assets/images/store/egg.png" style="border-radius: 5px;"></v-img>
                  </v-col>
                  <v-col cols="auto" class="text-left pl-0" style="padding: 0; border-left: solid 1px lightgray;">
                    <v-card-text style="padding: 0 0 0 5px;">달걀 도매 구매</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">품목 : 달걀</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">동네 : 대전광역시 유성구 봉명동</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">마감일 : 2020/08/31</v-card-text>
                  </v-col>
                  <v-col cols="auto" style="padding: 0;">
                    <v-card-text style="padding: 16px 0 0; margin-top: 15px; font-size: 40px; height: 0px;">11/30</v-card-text>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row justify="space-between" style="padding: 0; margin: 0;">
                  <v-col cols="auto" style="padding: 0;">
                    <v-img height="80" width="80" padding="60"  src="../../assets/images/store/egg.png" style="border-radius: 5px;"></v-img>
                  </v-col>
                  <v-col cols="auto" class="text-left pl-0" style="padding: 0; border-left: solid 1px lightgray;">
                    <v-card-text style="padding: 0 0 0 5px;">달걀 도매 구매</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">품목 : 달걀</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">동네 : 대전광역시 유성구 봉명동</v-card-text>
                    <v-card-text style="padding: 0 0 0 5px; font-size: 10px;">마감일 : 2020/08/31</v-card-text>
                  </v-col>
                  <v-col cols="auto" style="padding: 0;">
                    <v-card-text style="padding: 16px 0 0; margin-top: 15px; font-size: 40px; height: 0px;">11/30</v-card-text>
                  </v-col>
                </v-row>
              </v-card>
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

}
</script>

<style>
  
</style>
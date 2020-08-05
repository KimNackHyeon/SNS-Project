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
                우리 동네 장터
              </div>
                <router-link to="/store/marketmap"><v-btn  flat icon style="width:30x; height:30px; background-size:cover; ">
                <img v-show="$route.name=='MarketPlace'" id="mapIcon" style="margin-left:5px; margin-bottom: 8px;width:auto; height:35px;" src="../../assets/images/map.png">
          </v-btn></router-link>
            </v-toolbar>
          </v-flex>
        </v-layout>
        <v-layout row wrap justify-space-between style="padding: 0; margin: 0; height: 48px;">
          <v-flex>
            <v-toolbar color="rgba(160, 212, 105, 0.5)" flat height="48px">
              <v-switch label="물물교환 가능 물품만 보기" style="margin-top:18px; margin-right: 18px;"></v-switch>
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
            <router-link to="/store/marketplace/detail">
              <v-col cols="12">
                <v-card style="padding: 5px;">
                  <v-row style="padding: 0; margin: 0;">
                    <v-col cols="4" style="padding: 0; padding-right: 1px; border-right: solid 1px lightgray;">
                      <v-img height="105" width="105" padding="60"  src="../../assets/images/store/egg.png" style="border-radius: 5px;"></v-img>
                      <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">계란</v-card-text>
                      <v-card-text class="text-center" style="padding: 0; font-size: 9px;">대전광역시 유성구 덕명동</v-card-text>
                    </v-col>
                    <v-col cols="5" class="text-center" style="padding: 0;">
                      <v-row class="pa-0">
                        <v-card-text style="font-size: 20px; padding: 0; padding-bottom: 15px;">물물교환</v-card-text>
                      </v-row>
                      <v-row class="pa-0 ma-1">
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/egg.png"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">2개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/onion.png"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">1개</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/egg.png"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">3개당</v-card-text>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/potato.png"></v-img>
                        </v-col>
                        <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                          <v-card-text class="pa-0" style="font-size: 13px;">1개</v-card-text>
                        </v-col>
                      </v-row>
                    </v-col>
                    <v-col cols="3" style="padding: 0; border-left: solid 1px lightgray">
                      <v-row class="pa-0 ma-1">
                        <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                          <v-card-text class="text-center pa-0" style="font-size: 15px; font-weight: bold;">구매</v-card-text>
                        </v-col>
                        <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                          <v-card-text class="text-center pa-0" style="font-size: 10px;">1개당</v-card-text>
                        </v-col>
                        <v-col cols="12" class="pa-1 text-center">
                          <span class="text-center pa-0" style="font-size: 18px; color: red;">187.3</span>
                          <span class="text-center pa-0" style="font-size: 18px;">원</span>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>
                </v-card>
              </v-col>
            </router-link>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row style="padding: 0; margin: 0;">
                  <v-col cols="4" style="padding: 0; padding-right: 1px; border-right: solid 1px lightgray;">
                    <v-img height="105" width="105" padding="60"  src="../../assets/images/store/potato.png" style="border-radius: 5px;"></v-img>
                    <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">감자</v-card-text>
                    <v-card-text class="text-center" style="padding: 0; font-size: 9px;">대전광역시 유성구 덕명동</v-card-text>
                  </v-col>
                  <v-col cols="5" class="text-center" style="padding: 0;">
                    <v-row class="pa-0">
                      <v-card-text style="font-size: 20px; padding: 0; padding-bottom: 15px;">물물교환</v-card-text>
                    </v-row>
                    <v-row class="pa-0 ma-1">
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/potato.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">1개당</v-card-text>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/sweetpotato.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">1.5개</v-card-text>
                      </v-col>
                    </v-row>
                  </v-col>
                  <v-col cols="3" style="padding: 0; border-left: solid 1px lightgray">
                    <v-row class="pa-0 ma-1">
                      <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                        <v-card-text class="text-center pa-0" style="font-size: 15px; font-weight: bold;">구매</v-card-text>
                      </v-col>
                      <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                        <v-card-text class="text-center pa-0" style="font-size: 10px;">1개당</v-card-text>
                      </v-col>
                      <v-col cols="12" class="pa-1 text-center">
                        <span class="text-center pa-0" style="font-size: 18px; color: red;">980.1</span>
                        <span class="text-center pa-0" style="font-size: 18px;">원</span>
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row style="padding: 0; margin: 0;">
                  <v-col cols="4" style="padding: 0; padding-right: 1px; border-right: solid 1px lightgray;">
                    <v-img height="105" width="105" padding="60"  src="../../assets/images/store/onion.png" style="border-radius: 5px;"></v-img>
                    <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">양파</v-card-text>
                    <v-card-text class="text-center" style="padding: 0; font-size: 9px;">대전광역시 유성구 봉명동</v-card-text>
                  </v-col>
                  <v-col cols="5" class="text-center" style="padding: 0;">
                    <v-row class="pa-0">
                      <v-card-text style="font-size: 20px; padding: 0; padding-bottom: 15px;">물물교환</v-card-text>
                    </v-row>
                    <v-row class="pa-0 ma-1">
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/onion.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">1개당</v-card-text>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/egg.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">2개</v-card-text>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/onion.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">1개당</v-card-text>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/potato.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">1개</v-card-text>
                      </v-col>
                    </v-row>
                  </v-col>
                  <v-col cols="3" style="padding: 0; border-left: solid 1px lightgray">
                    <v-row class="pa-0 ma-1">
                      <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                        <v-card-text class="text-center pa-0" style="font-size: 15px; font-weight: bold;">구매</v-card-text>
                      </v-col>
                      <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                        <v-card-text class="text-center pa-0" style="font-size: 10px;">1개당</v-card-text>
                      </v-col>
                      <v-col cols="12" class="pa-1 text-center">
                        <span class="text-center pa-0" style="font-size: 18px; color: red;">495.0</span>
                        <span class="text-center pa-0" style="font-size: 18px;">원</span>
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
            <v-col cols="12">
              <v-card style="padding: 5px;">
                <v-row style="padding: 0; margin: 0;">
                  <v-col cols="4" style="padding: 0; padding-right: 1px; border-right: solid 1px lightgray;">
                    <v-img height="105" width="105" padding="60"  src="../../assets/images/store/egg.png" style="border-radius: 5px;"></v-img>
                    <v-card-text class="text-center" style="padding: 0; border-bottom: solid 1px lightgray">계란</v-card-text>
                    <v-card-text class="text-center" style="padding: 0; font-size: 9px;">대전광역시 유성구 덕명동</v-card-text>
                  </v-col>
                  <v-col cols="5" class="text-center" style="padding: 0;">
                    <v-row class="pa-0">
                      <v-card-text style="font-size: 20px; padding: 0; padding-bottom: 15px;">물물교환</v-card-text>
                    </v-row>
                    <v-row class="pa-0 ma-1">
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/egg.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">5개당</v-card-text>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-img height="30" width="30" class="ma-0 pa-0" src="../../assets/images/store/icons/vanilla.png"></v-img>
                      </v-col>
                      <v-col cols="3" class="pa-0" style="margin-bottom: 13px">
                        <v-card-text class="pa-0" style="font-size: 13px;">1개</v-card-text>
                      </v-col>
                    </v-row>
                  </v-col>
                  <v-col cols="3" style="padding: 0; border-left: solid 1px lightgray">
                    <v-row class="pa-0 ma-1">
                      <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                        <v-card-text class="text-center pa-0" style="font-size: 15px; font-weight: bold;">구매</v-card-text>
                      </v-col>
                      <v-col cols="12" style="padding: 0; padding-bottom: 13px">
                        <v-card-text class="text-center pa-0" style="font-size: 10px;">1개당</v-card-text>
                      </v-col>
                      <v-col cols="12" class="pa-1 text-center">
                        <span class="text-center pa-0" style="font-size: 18px; color: red;">187.3</span>
                        <span class="text-center pa-0" style="font-size: 18px;">원</span>
                      </v-col>
                    </v-row>
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
// const SERVER_URL = 'http://i3b301.p.ssafy.io:9999/food/api'
const SERVER_URL = 'http://localhost:9999/food'
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  data() {
    return {
      tradelist: [
      ]
    }
  },
created() {
      axios.get(`${SERVER_URL}/trade/`)
        .then(response => {
          this.tradelist = response.data.list
          console.log(this.tradelist)
        })
        .catch(error => {
          console.log(error.response)
        })
  },
}
</script>

<style>
  
</style>
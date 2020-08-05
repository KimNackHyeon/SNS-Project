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
            <v-toolbar flat height="36px" style="border: solid 1px lightgray">
              {{param}}
              {{buying}}
            </v-toolbar>
          </v-flex>
        </v-layout>
      </v-container>
    </v-card>
    </v-app>
  </div>
</template>

<script>
import Buyings from '@/views/store/Groupbuying'

export default {
  // components: {
  //   Buyings,
  // },
  computed: {
    param: function() {
      return this.$route.params
    }
  },
  created() {
    const id = this.$route.params.id
    if (id === undefined) {
      // this.$router.push('/store/groupbuying')
      this.$router.go(-1)
    }
  },
  props: ['buying'],
}
</script>

<style>

</style>
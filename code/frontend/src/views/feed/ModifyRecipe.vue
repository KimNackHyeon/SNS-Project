<template>
  <div style="height:100%; width:100%;">
    <div
      style="width:100%; height:120px; border-top: 1px solid rgba(128, 128, 128, 0.15); border-bottom: 1px solid rgba(128, 128, 128, 0.15); text-align:center; margin-bottom:-9px; "
    >
      <div style="width:100%; height:34%; border-bottom: 1px solid rgba(128, 128, 128, 0.15);">
        <router-link :to="{ name: 'FeedDetail', params: { feedNo : $route.params.feedNo }}">
          <div
            style="width:50px; height:100%;border-right: 1px solid rgba(128, 128, 128, 0.15); float:left;"
          >
            <v-icon size="30px" style="padding:6px 0px;">mdi-chevron-left</v-icon>
          </div>
        </router-link>
        <div style="height:100%; text-align:center; float:left; padding-top:8px;" :style="{width:frameSize.x-90+'px'}">
          <h4>레시피 수정하기</h4>
        </div>
        <div style="width:40px; height:100%; float:left; "></div>
      </div>
      <div>
        <div style="float: left; width: 50px; border-right: 1px solid rgba(128, 128, 128, 0.15);">
          <span style="line-height: 40px; font-size: 15px;">제목</span>
        </div>
        <div style="overflow: hidden;">
          <input
            v-model="title"
            class="titleinput"
            type="text"
            placeholder="제목을 입력해주세요."
            style="float:left; width: 100%; height: 40px;"
          />
        </div>
      </div>
      <div
        style="width:100%; height:32%; border-top: 1px solid rgba(128, 128, 128, 0.15); overflow:hidden;"
      >
        <div style="height: 100%;">
          <v-container style="max-height:30px;min-height:13px; padding:0px;">
            <v-combobox
              v-model="tags"
              :hide-no-data="!search"
              :search-input.sync="search"
              hide-selected
              label="# 레시피 관련 태그를 입력해주세요(추가:enter)"
              multiple
              small-chips
              deletable-chips
              solo
              dense
            ></v-combobox>
          </v-container>
          <div style="overflow: hidden;">
            <v-btn icon>
              <v-icon class="right-icon">mdi-magnify</v-icon>
            </v-btn>
          </div>
        </div>
      </div>
    </div>
    <!-- end of head -->

    <div class="addFoodBtn" @click="openAddFood">
      <!-- 재료 등록 -->
      <img src="../../assets/images/fruit.png" style="width:35px; height:35px;" />
      <h4>재료추가</h4>
    </div>
    <!-- end of 재료 등록 -->
    <div class="addFood" :style="{width:frameSize.x+'px',height:frameSize.y-50+'px'}">
      <div @click="closeAddFood" style="width:40px; height:40px;">
        <v-icon style="margin-left:330px;">mdi-close</v-icon>
      </div>
      <div style="width:100%; height:470px">
        <search-food :foodlist="foodlist" @addfood="addFood"></search-food>
      </div>
    </div>
    <div class="carousel-container" style="width:100%;" :style="{height:(frameSize.x-30+frameSize.y*0.2)+'px'}">
      <div class="carousel-slide" style="height: 100%; display: flex; overflow: hidden; margin-left:11px;">
        <div
          v-for="(img,index) in items"
          :key="index"
          style="margin:20px; margin-top:20px; box-shadow: #80808066 1px 1px 8px; float:left;"
        >
          <div style="margin:auto;" :style="{width:(frameSize.x-60)+'px',height:(frameSize.x-60)+'px'}">
            <div
              @click="deleteRecipe(index)"
              style="position:fixed; width:30px; height:30px;" :style="{'margin-left':(frameSize.x-20-60)+'px'}"
            >
              <v-icon size="30px">mdi-close</v-icon>
            </div>
            <div
              v-if="img.imageUrl == null"
              style="width:100%; height:100%; background-color:#e0e0e0d9;"
            >
              <div class="fileBox">
                <label for="ex_file" :style="{margin:(frameSize.x-60)/4+'px',width:(frameSize.x-60)/2+'px',height:(frameSize.x-60)/2+'px',padding:(frameSize.x-140)/4+'px'}">
                  <v-icon style="width:50%; height:50%" :size="80">mdi-camera-plus</v-icon>
                </label>
                <input
                  id="ex_file"
                  type="file"
                  accept="image/*"
                  @change="onChange($event,index)"
                  :style="{width:(frameSize.x-60)/2+'px',height:(frameSize.x-60+frameSize.y*0.2)/2+'px'}"
                />
              </div>
            </div>
            <div v-if="img.imageUrl != null" id="preview" style="width:100%; height:100%;">
              <img :style="{width:(frameSize.x-60)+'px',height:(frameSize.x-60)+'px'}" :src="img.imageUrl" />
            </div>
          </div>
          <div style="border-top: 1px solid #80808063;" :style="{width:(frameSize.x-60)+'px',height:(frameSize.y*0.2)+'px'}">
            <textarea
              style="width:100%; height:100%; resize: none;"
              v-model="items[index].desc"
              placeholder="레시피를 적어보세요."
            ></textarea>
          </div>
        </div>

        <!-- <v-dialog v-model="loading"> -->
        <!-- <v-container>
          <v-layout row justify-center align-center>
            <v-progress-linear :indeterminate="true"></v-progress-linear>
          </v-layout>
        </v-container>-->
        <!-- </v-dialog> -->

        <div class="plusBtnArea" style="width:100%; height:100%;">
          <div class="plusBtn" @click="addRecipe" :style="{'margin-top':(frameSize.y-407)/2+'px','margin-left':(frameSize.x-150-60)/2+'px'}">
            <v-icon size="100px">mdi-plus</v-icon>
          </div>
        </div>
        <!-- end of 추가버튼 -->
      </div>
      <!-- end of carousel-slide -->
    </div>
    <!-- end of carousel-container -->

    <div
      @click="modifyReciptComplete"
      style="height: 45px;
    background-color: rgb(160,212,105); text-align:center; padding:5px;"
    :style="{width:frameSize.x+'px'}"
    >
      <h3 v-if="!loading">레시피 수정</h3>
      <div align="center">
        <v-progress-circular :indeterminate="true" v-if="loading" color="white"></v-progress-circular>
      </div>
    </div>
    <div>
      <div class="toLeftBtn" @click="clicktoLeftBtn" :style="{'margin-top':-(frameSize.y-50)/2+'px'}">
        <v-icon color="white" size="47px">mdi-chevron-left</v-icon>
      </div>
      <div class="showPage" :style="{'margin-top':-100+'px','margin-left':(frameSize.x-80)/2+'px'}">
        - {{thisPage}} -
      </div>
      <div class="toRightBtn" @click="clicktoRightBtn" :style="{'margin-top':-(frameSize.y-50)/2+'px','margin-left':(frameSize.x-60)+'px'}">
        <v-icon color="white" size="47px">mdi-chevron-right</v-icon>
      </div>
    </div>
  </div>
</template>


<script>
import $ from "jquery";
import axios from "axios";
import store from "../../vuex/store.js";
import searchFood from "../Food/searchFood.vue";
import Swal from "sweetalert2";

const SERVER_URL = store.state.SERVER_URL;

export default {
  thisPage: 1,
  components: { searchFood },
  name: "imageUpload",
  userinfo: "",
  activator: null,
  attach: null,
  colors: ["green", "purple", "indigo", "cyan", "teal", "orange"],
  editing: null,
  index: -1,
  items: [
    { header: "Select an option or create one" },
    {
      text: "Foo",
      color: "blue",
    },
    {
      text: "Bar",
      color: "red",
    },
  ],
  data() {
    return {
      frameSize : {x:window.innerHeight*0.5625, y:window.innerHeight,per:1},
      thisPage: 1,
      search: null,
      title: "",
      item: {
        //...
        imageUrl: null,
        desc: null,
      },
      foodlist: "",
      tags: [],
      items: [
        {
          //...작성자, 재료, 글작성 일, items, tag,제목
          imageUrl: null,
          desc: null,
          file: null,
        },
      ],
      images: [],
      loading: false,
      likecount: '',
      createDate:'',
    };
  },
  watch: {
    model(val, prev) {
      if (val.length === prev.length) return;

      this.model = val.map((v) => {
        if (typeof v === "string") {
          v = {
            text: v,
            color: this.colors[this.nonce - 1],
          };

          this.items.push(v);

          this.nonce++;
        }

        return v;
      });
    },
  },
   mounted(){
    this.onResize();
    
  },
    
  created() {
    // 제목 : title
    // 태그 : tags라는 배열
    // 재료추가 : foodlisk 배열
    // 사진 : items 배열 안에 imageUrl에 url저장, decs: 내용 저장
    var feedNo = this.$route.params.feedNo;
    axios.get(`https://i3b301.p.ssafy.io:9999/food/api/feed/search`,{params:{feedNo:feedNo}}) // 피드 가져오기
      .then(response => {
        // console.log(response.data)
        this.title = response.data.feeddata.title
        this.likecount = response.data.feeddata.likecount
        this.createDate = response.data.feeddata.create_date
        for (let tag of response.data.taglist) {
          this.tags.push(tag.tagName)
        }
        this.foodlist = response.data.foodlist
        // this.items 초기화
        this.items = []
        for (let item of response.data.datalist){
          const data = {
            'desc': item.content.split('<br />').join('\n'),
            'file': 'File',
            'imageUrl': item.img
          }
          this.images.push(data.imageUrl);
          this.items.push(data)
        }
        // console.log(this.items)
      })
  },
  methods: {
    onResize(){
      if(window.innerHeight*0.5625 <=window.innerWidth){
        this.frameSize = {x:window.innerHeight*0.5625, y:window.innerHeight,per:innerHeight/640};
      }else{
        this.frameSize = {x:window.innerWidth, y:window.innerWidth*1.77,per:innerWidth/360};
        }
    },
    addFood(foodlist) { 
      var FL = "음식재료 ";
      for (var i = 0; i < foodlist.length; i++) {
        FL = FL + foodlist[i].name_kor + " ";
        FL = FL + foodlist[i].amount + "개 ";
        if (i != foodlist.length - 1) {
          FL += ",";
        }
      }
      FL = FL + "를 재료로 추가하였습니다.";
      // console.log(foodlist.length)
      Swal.fire({
        icon: "success",
        title: FL,
      });
      this.foodlist = foodlist;
      $(".addFood").css("display", "none");
    },
    onChange(e, index) {
      const file = e.target.files[0];
      //   this.item.imageUrl = URL.createObjectURL(file);
      this.$set(this.items[index], "imageUrl", URL.createObjectURL(file));
      this.$set(this.items[index], "file", file);
    },
    addRecipe() {
      this.items.push({ imageUrl: null, desc: null });
      if (this.items.length >= 10) {
        $(".plusBtnArea").css("display", "none");
      }
      if (this.items.length <= 9) {
        $(".plusBtnArea").css("display", "unset");
      }
      // console.log(this.item);
      // console.log(this.items);
    },
    clicktoLeftBtn() {
      if(this.thisPage>1){
      var thisMarginStr = $(".carousel-slide").css("margin-left");
      var thisMargin = Number(thisMarginStr.split("p")[0]);
      if (thisMargin < 11) {
        var afterMargin = thisMargin + (this.frameSize.x-20) + "px";
      }
      $(".carousel-slide").css("margin-left", afterMargin);
        this.thisPage -=1;
      }
        // console.log(this.thisPage);
      // transform:translateX(10px);
      // console.log(this.item);
      // console.log(this.items);
    },
    clicktoRightBtn() {
      if(this.thisPage < this.items.length+1){
      var thisMarginStr = $(".carousel-slide").css("margin-left");
      var thisMargin = Number(thisMarginStr.split("p")[0]);
      var afterMargin = thisMargin - (this.frameSize.x-20) + "px";
      $(".carousel-slide").css("margin-left", afterMargin);
      // console.log(this.item);
      // console.log(this.items);
        this.thisPage += 1;
      }
        // console.log(this.thisPage+" "+this.items.length);
    },
    closeAddFood() {
      $(".addFood").css("display", "none");
    },
    openAddFood() {
      $(".addFood").css("display", "unset");
    },
    deleteRecipe(index) {
      this.items.splice(index);
      this.images.splice(index);
    },
    edit(index, item) {
      if (!this.editing) {
        this.editing = item;
        this.index = index;
      } else {
        this.editing = null;
        this.index = -1;
      }
    },
    filter(item, queryText, itemText) {
      if (item.header) return false;

      const hasValue = (val) => (val != null ? val : "");

      const text = hasValue(itemText);
      const query = hasValue(queryText);

      return (
        text.toString().toLowerCase().indexOf(query.toString().toLowerCase()) >
        -1
      );
    },
    modifyReciptComplete() {
      if (store.state.kakaoUserInfo.email != null) {
        this.userinfo = store.state.kakaoUserInfo;
        // console.log(this.userinfo);
      } else {
        this.userinfo = store.state.userInfo;
      }

      if(!this.title){
        Swal.fire({
          icon: 'error',
          title: '',
          text: '제목을 입력해주세요',
        })
        return;
      } else if(!this.foodlist){
        Swal.fire({
          icon: 'error',
          title: '',
          text: '재료를 하나 이상 입력해주세요',
        })
        return;
      } else if(!this.items[0].imageUrl){
        Swal.fire({
          icon: 'error',
          title: '',
          text: '레시피를 하나 이상 입력해주세요',
        })
        return;
      }

      this.loading = true;

      var formData = new FormData();
      formData.append("email", this.userinfo.email);
      const contents = [];

      this.items.forEach((item) => {
        if (item.desc != "" && item.desc != null) {
          contents.push(item.desc.replace(/(?:\r\n|\r|\n)/g, "<br />"));
        } else {
          contents.push(" ");
        }
        if(item.file != "File")
          formData.append("images", item.file);
      });

      const feedData = {
        no : this.$route.params.feedNo,
        title: this.title,
        email: this.userinfo.email,
        nickname: this.userinfo.nickname,
        profile: this.userinfo.profile_image_url,
        likecount: this.likecount,
        create_date : this.createDate
      };

      const data = {
        feedData: feedData,
        food: this.foodlist,
        tags: this.tags,
        contents: contents,
        images: this.images,
      };

      // console.log(formData);

      axios
        .post(`https://i3b301.p.ssafy.io:9999/food/api/feed/img`, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((response) => {
          // console.log(response.data);
          response.data.forEach(url => {
            this.images.push(url);
          })
          // this.images = response.data;
          data.images = this.images;
          // console.log(data);
          let timerInterval;
          Swal.fire({
            title: '레시피 수정중',
            // html: '전송까지 <b></b> 초 남았습니다.',
            timer: 1000*this.items.length + 2000,
            timerProgressBar: true,
            onBeforeOpen: () => {
              Swal.showLoading()
              Swal.color= 'green';
              timerInterval = setInterval(() => {
                const content = Swal.getContent()
                if (content) {
                  const b = content.querySelector('b')
                  if (b) {
                    b.textContent = Swal.getTimerLeft()
                  }
                }
              }, 100)
            },
            onClose: () => {
              clearInterval(timerInterval)
              Swal.fire(
                '수정완료!',
                '',
                'success'
              )
              this.loading = false;
            this.updateData(data);
            }
          }).then((result) => {
            /* Read more about handling dismissals below */
            if (result.dismiss === Swal.DismissReason.timer) {
              // console.log('I was closed by the timer')
            }
          })
          // setTimeout(() => {
            
          // }, 1000 * this.items.length + 2000);
        })
        .catch((error) => {
          // console.log(error.response);
        });
    },
    updateData(data) {
      // console.log(data);
      axios
        .put(`https://i3b301.p.ssafy.io:9999/food/api/feed/update`, data)
        .then((response) => {
          // // console.log(response);
          this.$router.push("/feed/main");
        })
        .catch((error) => {
          // // console.log(error.response);
        });
    },
  },
}
</script>
<style scoped>
.tags {
  width: 100%;
  height: 32%;
  border-top: 1px solid rgba(128, 128, 128, 0.15);
  overflow: hidden;
}
.tags:hover {
  border: 2px solid #a0d469;
  border-radius: 4px;
}
.addFoodBtn {
  width: 80px;
  height: 80px;
  position: fixed;
  background-color: white;
  border-radius: 15px;
  box-shadow: 1px 1px 7px #00000052;
  margin-top: 16px;
  padding: 12px 0px;
  text-align: center;
}

.addFood {
  background-color: white;
  position: fixed;
  z-index: 101;
  margin-top: -29px;
  display: none;
}

.toLeftBtn {
  width: 50px;
  height: 50px;
  background-color: #00000070;
  border-radius: 10px;
  color: white;
  position: fixed;
  /* margin-left: auto; */

}
.v-input__control {
  min-height: 13px;
}
.toRightBtn {
  width: 50px;
  height: 50px;
  background-color: #00000070;
  border-radius: 10px;
  color: white;
  /* margin-left: auto; */
position: fixed;
}
.fileBox label {
  display: inline-block;
  color: #fff;
  font-size: inherit;
  background-color: #ffffff5c;
  border-radius: 150px;
  cursor: pointer;
}

.plusBtn {
  width: 150px;
  height: 150px;
  background-color: rgb(173 222 122 / 85%);
  border-radius: 75px;
  -webkit-box-shadow: 1px 1px 10px #0000005e;
  box-shadow: 1px 1px 10px #0000005e;
  text-align: center;
  padding: 24px;
}
.plusBtnArea {
  margin: 20px;
  float: left;
}

h3 {
  font-size: 22px;
  font-weight: 550;
  color: white;
  text-shadow: 1px 1px 4px #0000004f;
}

.fileBox label:active {
  color: gray;
}

.fileBox input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}

.titleinput {
  background-color: unset;
  text-align: left;
  padding: 0 10px;
  font-size: 15px;
}
.titleinput:hover {
  border: 2px solid #a0d469;
}
.showPage{
  width: 80px;
  height: 50px;

  border-radius: 10px;
  color: black;
  position: fixed;
  /* margin-left: auto; */
  font-size: 30px;
  text-align: center;
}
</style>
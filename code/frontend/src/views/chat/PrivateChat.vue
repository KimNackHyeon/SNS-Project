<template>
  <div style="width:100%; height:100%">
    <div style="height:48px; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
      <v-btn @click="goback" icon color="gray" style="float: left; background-color: #f1f3f5; border-radius: unset; height: 100%; border-right: 1px solid lightgray">
        <v-icon class="left-icon" size="35px">mdi-chevron-left</v-icon>
      </v-btn>
      <div class="titleBox">
        <div class="pageTitle">
            {{this.chatname}}
        </div>
      </div>
    </div>
    <div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>Recent</h4>
            </div>
            <div class="srch_bar">
              <div class="stylish-input-group">
                <input type="text" class="search-bar"  placeholder="Search" >
                <span class="input-group-addon">
                <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                </span> </div>
              </div>
            </div>
            <div class="inbox_chat">
              <div class="chat_list active_chat">
                <div class="chat_people">
                  <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <div class="chat_ib">
                    <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                    <p>Test, which is a new approach to have all solutions 
                      astrology under one roof.</p>
                  </div>
                </div>
              </div>
              <div class="chat_list">
                <div class="chat_people">
                  <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <div class="chat_ib">
                    <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                    <p>Test, which is a new approach to have all solutions 
                      astrology under one roof.</p>
                  </div>
                </div>
              </div>
              <div class="chat_list">
                <div class="chat_people">
                  <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <div class="chat_ib">
                    <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                    <p>Test, which is a new approach to have all solutions 
                      astrology under one roof.</p>
                  </div>
                </div>
              </div>
              <div class="chat_list">
                <div class="chat_people">
                  <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <div class="chat_ib">
                    <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                    <p>Test, which is a new approach to have all solutions 
                      astrology under one roof.</p>
                  </div>
                </div>
              </div>
              <div class="chat_list">
                <div class="chat_people">
                  <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <div class="chat_ib">
                    <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                    <p>Test, which is a new approach to have all solutions 
                      astrology under one roof.</p>
                  </div>
                </div>
              </div>
              <div class="chat_list">
                <div class="chat_people">
                  <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <div class="chat_ib">
                    <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                    <p>Test, which is a new approach to have all solutions 
                      astrology under one roof.</p>
                  </div>
                </div>
              </div>
              <div class="chat_list">
                <div class="chat_people">
                  <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                  <div class="chat_ib">
                    <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
                    <p>Test, which is a new approach to have all solutions 
                      astrology under one roof.</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        <div class="mesgs">
          <div class="msg_history">
            <div v-for="(message, index) in messages" :key="index" class="incoming_msg">
              <!-- <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div> -->
              <div class="received_withd_msg">
                <div style="width: 100%; position: relative; ">
                  <div :class="[message.author===authUser.name?'sent_msg':'received_msg']">
                    <p>{{message.message}}</p>
                    <span class="time_date">| {{message.createdAt}} | {{message.author}} |</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="type_msg">
            <div class="input_msg_write" style="width: 80%; z-index: 50; background-color: white;">
              <input @keyup.enter="saveMessage" v-model="message" type="text" style="padding-left: 10px;" placeholder="Type a message" />
              <button v-on:click="saveMessage" class="msg_send_btn" type="button"><v-icon style="transform:rotate(45)">mdi-send</v-icon></button>
            </div>
          </div>
        </div>
      </div>
    </div></div>
</template>

<script>
import store from '../../vuex/store.js'
import axios from 'axios'
const SERVER_URL = store.state.SERVER_URL;

export default {
    name:'PrivateChat',
    props:['privatechat'],
    data(){
        return{
          userinfo:'',
          chatNo: '',
          chatname: '',
          chatlist:'',
          writerinfo:'',
            message:null,
            messages:[],
            authUser:{},
        }
    },
    methods:{
     
            scrollToBottom(){
            let box = document.querySelector('.msg_history');
            box.scrollTop = box.scrollHeight;
        },
        goback(){
          this.$router.go(-1);
        },
        saveMessage(){
            //파이어스토어에 메세지 저장하자 건호야
            db.collection(this.chatNo).add({
                message:this.message,
                author:this.authUser.name, //이 부분에 유저의 닉네임을 넣어주면 된다.
                createdAt: this.$moment(new Date()).format('YYYY년MM월DD일'),
                createDate: new Date(),
            }).then(()=>{
                this.scrollToBottom();
            })

            this.message=null
        },
        fetchMessages(){
            db.collection(this.chatNo).orderBy(`createDate`).onSnapshot((querySnapshot)=>{
                let allMessages=[];
                querySnapshot.forEach(doc=>{
                    allMessages.push(doc.data())
                })

                this.messages = allMessages;

                setTimeout(()=>{
                    this.scrollToBottom();
                }, 10);
            })
        }
    },
    created(){
      this.chatNo = this.$route.params.privatechat;
      // console.log(this.chatNo)
      // console.log(this.$route.params)
      this.chatname = this.$route.params.chatName;
      // console.log(this.chatname)
      if(store.state.kakaoUserInfo.email != null){
        this.userinfo = store.state.kakaoUserInfo;
      }else{
        this.userinfo = store.state.userInfo;
      }
        this.authUser = {name:this.userinfo.nickname};
        this.fetchMessages();
    }
}
</script>


<style scoped ="">
img{ max-width:100%;}
.inbox_people {
  /* background: #f8f8f8 none repeat scroll 0 0; */
    float: left;
    overflow: hidden;
    width: 0%;
    /* border-right: 1px solid #c4c4c4; */
    /* display: none;*/
}
.inbox_msg {
  /* border: 1px solid #c4c4c4; */
  clear: both;
  overflow: hidden;
  height: 100%;
    width: 100%;
}
.top_spac{ margin: 20px 0 0;}


.recent_heading {float: left; width:40%;}
.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}
.header{
  width: 100%;
    height: 41px;
    border-top: 1px solid #e0e0e0;
    border-bottom: 1px solid #e0e0e0;
    text-align: center;
    font-size: 18px;
    padding: 6px 0px;
    font-weight: 500;
}
.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.recent_heading h4 {
  color: #05728f;
  font-size: 21px;
  margin: auto;
}
.srch_bar input{ border:1px solid #cdcdcd; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
.srch_bar .input-group-addon button {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  padding: 0;
  color: #707070;
  font-size: 18px;
}
.srch_bar .input-group-addon { margin: 0 0 0 -27px;}

.chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
.chat_ib h5 span{ font-size:13px; float:right;}
.chat_ib p{ font-size:14px; color:#989898; margin:auto}
.chat_img {
  float: left;
  width: 11%;
}
.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}
.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}
.inbox_chat { height: 550px; overflow-y: scroll;}

.active_chat{ background:#ebebeb;}
.incoming_msg{
  width: 100%;
    min-height: 60px;
    padding-right: 9px;
}
.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  width: 60%;
    position: absolute;
    right: 0;
 }
 
 .received_msg p {
  background: #ebebeb none repeat scroll 0 0;
    border-radius: 3px;
    color: #646464;
    font-size: 14px;
    margin: 0;
    padding: 5px 10px 5px 12px;
    width: 100%;
    -webkit-box-shadow: 1px 1px 9px #00000057;
    box-shadow: 1px 1px 9px #00000057;
}
.sent_msg p {
  background: #8dc057e8 none repeat scroll 0 0;
    border-radius: 3px;
    font-size: 14px;
    margin: 5px 0px;
    color: #fff;
    text-shadow: 1px 1px 5px #000000ad;
    padding: 5px 10px 5px 12px;
    width: 100%;
    box-shadow: -2px 2px 7px #00000059;
    word-break: normal;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  /* margin: 8px 0 0; */
}
/* .received_withd_msg { width: 100%;} */
.mesgs {
 float: right;
    /* padding: 15px 15px 50px 15px; */
    width: 100%;
    height: 100%;
}

 
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
 width:60%;
    padding: 0px;;
}
.input_msg_write input {
    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
    border: medium none;
    color: #4c4c4c;
    font-size: 15px;
    min-height: 48px;
    width: 100%;
    padding: 0px;
}

.type_msg {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 10%;
  margin: 0px;
  box-shadow: 0px -2px 10px #00000038;
  }
.msg_send_btn {
  background: rgb(161 219 99) none repeat scroll 0 0;
    border: medium none;
    /* border-radius: 50%; */
    color: #fff;
    text-align: center;
    text-shadow: 1px 1px 9px #00000069;
    cursor: pointer;
    font-size: 17px;
    height: 100%;
    position: absolute;
    right: 0;
    /* top: 11px; */
    width: 20%;
}
.messaging {
  width: 100%;
    height: 92%;
    position: relative;
    /* padding: 0 0 50px 0;*/}
.msg_history {
  height: 90%;
  overflow-y: auto;
  width:100%;
  overflow-x: hidden;
}
</style>

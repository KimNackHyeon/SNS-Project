<template>
  <v-layout wrap style="width:360px; height: 600px; margin:auto; border:1px solid #a0d469;">
    <div style="width:100%; height:60px;">
        <v-btn flat icon @click.stop="drawer = !drawer"><v-icon>fas fa-list</v-icon></v-btn>
    </div>
    <div  class="slideshow-container" style="width:100%; height:300px;">
        <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="img_nature_wide.jpg" style="width:100%">
        <div class="text">Caption Text</div>
        </div>

        <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="img_snow_wide.jpg" style="width:100%">
        <div class="text">Caption Two</div>
        </div>

        <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="img_mountains_wide.jpg" style="width:100%">
        <div class="text">Caption Three</div>
        </div>
    </div>
    <v-navigation-drawer
      v-model="drawer"
      temporary
      absolute
      width=320px
    >
      <v-list class="pa-1" style="background:linear-gradient( to top, #a0d469, rgb(27, 187, 26)); width:100%; height:17%; position:relative;">
        <div  style="background-color:white; width:80%; height:170px; position:relative; border-radius: 8px; border:0px solid white; margin: 40px auto auto auto; box-shadow: #1e1e1e4a 2px 2px 9px;">
            <div style="width:100%; height:52%; padding:auto;">
                <div class="profilePhotobox">
                    <img class="circlePhoto" src="https://cdn.vuetifyjs.com/images/profiles/marcus.jpg" />
                </div>
                <div style=" width:100px; height:100%; display: inline-table;">
                    <h2>mamtte 님</h2>
                    <h4>안녕하세요.</h4>
                </div>
            </div>
            <div  style="width:100%; height:48%;">
                <div class="grayLine" style="width:50%; height:100%; display: inline-block;">
                    <v-icon>question_answer</v-icon>
                </div>
                <div class="grayLine" style="width:50%; height:100%; display: inline-block;"></div>
            </div>
        </div>
        <v-list-tile avatar>
          <v-list-tile-avatar>
            
          </v-list-tile-avatar>
          <v-list-tile-content>
           
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
      <v-list class="pt-0" dense>
        <v-divider></v-divider>
        <v-list-tile v-for="item in items" :key="item.title">
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
           
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
  </v-layout>
</template>

<script>
  export default {
    data () {
      return {
        drawer: null,
      }
    },
    methods:{
        showSlides() {
            var slideIndex = 0;
            var i;
            var slides = document.getElementsByClassName("mySlides");
            var dots = document.getElementsByClassName("dot");
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";  
            }5
            slideIndex++;
            if (slideIndex > slides.length) {slideIndex = 1}    
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "block";  
            dots[slideIndex-1].className += " active";
        }
    }
  }
</script>

<style>
.profilePhotobox{
    width: 80px;
    height: 80px; 
    border-radius: 70%;
    overflow: hidden;
    display: inline-table;
}
.circlePhoto{
    width: 100%;
    height:100%;
    object-fit: cover;
}
.grayLine{
    border: 1px solid #8080802e;
}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
}
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}
.active {
  background-color: #717171;
}
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}
</style>
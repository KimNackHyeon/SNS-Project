<template>
  <div id="map" style="width: 100%; height: 100%;"></div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import store from '../../vuex/store.js'

export default {
  data() {
    return {
      map: null,
      mapdata: null,
      location: {
        latitude: null,
        longitude: null,
      }
    }
  },
  mounted() {
    this.mapdata = store.state.mapOtherUserInfo.address
    if (window.kakao && window.kakao.maps) {
      console.log(this.mapdata)
      this.initMap();
      this.searchSubmit();
      this.getLocation();
    } else {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=93896045350a4c0fb6b7c93ae2527085&libraries=services';
      document.head.appendChild(script);
    }
  },
  methods : {
    ...mapMutations(['setUserInfo', 'setMapOtherUserInfo']),
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 8
      };
      var map = new kakao.maps.Map(container, options);
      this.map = map;
      //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var marker = new kakao.maps.Marker({
        position: map.getCenter()
      });
      marker.setMap(map);
    },
    searchSubmit() {
      var geocoder = new kakao.maps.services.Geocoder();
      var address = [this.userInfo.address]
      for (var ad = 0; ad < this.mapdata.length; ad++) {
        address.push(this.mapdata[ad])
      }
      console.log('address')
      console.log(address)
      for (var i = 0; i < address.length; i ++) {
        geocoder.addressSearch(address[i], (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            var bounds = new kakao.maps.LatLngBounds();
            console.log('ok')
            for (var r = 0; r < result.length; r++) {
              var data = result[r];
              bounds.extend(new kakao.maps.LatLng(data.y - 50, data.x - 50));
            }
            this.map.setBounds(bounds);
            var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
            var positions = [
              {
                title: '<div>{ this.userInfo.address }</div>',
                latlng: this.map.getCenter(),
              },
              {
                title: '<div>내 위치</div>',
                latlng: new kakao.maps.LatLng(location.latitude, location.longitude)
              }
            ];
            for (var j = 0; j < positions.length; j++) {
              var imageSize = new kakao.maps.Size(24, 35);
              var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
              var marker = new kakao.maps.Marker({
                map: this.map,
                position: positions[j].latlng,
                image : markerImage
              });
              // var infowindow = new kakao.maps.InfoWindow({
              //   title: positions[j].title,
              // });
            }
            marker.setMap(this.map);
          }
        })
      }
    },
    getLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.watchPosition(function(position) {
        // navigator.geolocation.getCurrentPosition(function(position) {
          location.latitude = position.coords.latitude
          location.longitude = position.coords.longitude
          console.log(location.latitude, location.longitude)
        }, function(error) {
          console.error(error);
        }, {
          enableHighAccuracy: true,
          maximumAge: 0,
          timeout: Infinity
        });
      } else {
        alert('GPS를 지원하지 않습니다.');
      }
    }
  },
  computed: {
    ...mapState(['userInfo', 'mapOtherUserInfo']),
  }
}
</script>
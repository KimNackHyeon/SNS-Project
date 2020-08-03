<template>
  <div id="map" style="width: 100%; height: 100%;">
    <!-- <GmapMap :center="myCoordinates" :zoom="zoom" style="width:340px; height:400px; margin: 10px auto;" ref="mapRef"></GmapMap> -->
  </div>
</template>

<script>

export default {
  data() {
    return {
      map: null,
      myCoordinates: {
        lat: 0,
        lng: 0,
      },
      zoom: 14
    }
  },
  created() {
    this.$getLocation({})
      .then(coordinates => {
          this.myCoordinates = coordinates;
      })
      .catch(error => alert(error));
  },


  mounted() {
    // this.$refs.mapRef.$mapPromise
    //   .then(map => 
    //       this.map = map
    //   );

    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  methods : {
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
        };
        console.log(this.myCoordinates.lat.toFixed(4))
        console.log(this.myCoordinates.lng.toFixed(4))
        console.log(this.map)
        var map = new kakao.maps.Map(container, options);
        //마커추가하려면 객체를 아래와 같이 하나 만든다.
        var marker = new kakao.maps.Marker({
          position: map.getCenter()
        });
        marker.setMap(map);
    },
    addScript() {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=93896045350a4c0fb6b7c93ae2527085';
      document.head.appendChild(script);
    }
  },
  computed: {
    mapCoordinates() {
      if(!this.map) {
        console.log('ok')
        return {
          lat: 0,
          lng: 0
        };
      }
      console.log('ok2')
      return {
        lat: this.map.getCenter().lat().toFixed(4),
        lng: this.map.getCenter().lng().toFixed(4)
      }
    }
  }
}
</script>
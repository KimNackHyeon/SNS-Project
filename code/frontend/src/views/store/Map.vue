<template>
    <div id="app">
        <div style="max-width: 340px; margin: 0 5px; display: flex; align-items: center; justify-content: space-between">
            <div>
                <h1>사용자 위치</h1>
                <p style="text-align:center;">위도 : {{ myCoordinates.lat.toFixed(4) }}</p>
                <p style="text-align:center;">경도 : {{ myCoordinates.lng.toFixed(4) }} </p>
            </div>
            <div>
                <h1>지도 위치</h1>
                <p style="text-align:left;">위도 : {{ mapCoordinates.lat }}</p>
                <p style="text-align:left;">경도 : {{ mapCoordinates.lng }} </p>
            </div>
        </div>
        
        <GmapMap
            :center="myCoordinates"
            :zoom="zoom"
            style="width:340px; height:400px; margin: 10px auto;"
            ref="mapRef"
        ></GmapMap>
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
            // add the map to a data object
            this.$refs.mapRef.$mapPromise
            .then(map => 
                this.map = map
            );
        },
        computed: {
            mapCoordinates() {
                if(!this.map) {
                    return {
                        lat: 0,
                        lng: 0
                    };
                }
                return {
                    lat: this.map.getCenter().lat().toFixed(4),
                    lng: this.map.getCenter().lng().toFixed(4)
                }
            }
        }
    }
</script>
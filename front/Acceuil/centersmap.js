var locations = [];		
//var dataOfCenters;
function getDataOfCenters(){
	"use strict";
	let myHeaders = new Headers({
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'text/plain'
	});
	fetch("http://127.0.0.1:9080/cr/infoCentre").then(function(response){
		return response.json();
	}).then(function(data){
//		dataOfCenters = data;
		
		for(var i = 0; i<data.length;i++){
			var obj = {lat : null , lng : null};
			obj.lat = Number(data[i].AddGeo.latitude);
			obj.lng = Number(data[i].AddGeo.longitude);
			locations[i] = obj;
		}
		console.log(locations);
		console.log(data);
	}).catch(function(e){
		console.log("Oops, error"+e);
});
}
//function getCenterLocations(){
//	"use strict";
//for(var i = 0; i<dataOfCenters.length;i++){
//			var obj = {lat : null , lng : null};
//			obj.lat = Number(dataOfCenters[i].AddGeo.latitude);
//			obj.lng = Number(dataOfCenters[i].AddGeo.longitude);
//			locations[i] = obj;
//		}
//		console.log(locations);
//		console.log(dataOfCenters);
//}
getDataOfCenters();
console.log(locations);
//getCenterLocations();
function initMap() {

	var map = new google.maps.Map(document.getElementById('map'), {
    	zoom: 6,
        center: {lat: 46.997416, lng: 2.340819} 
    });

        // Create an array of alphabetical characters used to label the markers.
    var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

        // Add some markers to the map.
        // Note: The code uses the JavaScript Array.prototype.map() method to
        // create an array of markers based on a given "locations" array.
        // The map() method here has nothing to do with the Google Maps API.
    var markers = locations.map(function(location, i) {
    	return new google.maps.Marker({
        position: location,
        label: labels[i % labels.length]
       	});
    });

        // Add a marker clusterer to manage the markers.
    var markerCluster = new MarkerClusterer(map, markers,{
		imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
    }
initMap();
//getCenterLocations();


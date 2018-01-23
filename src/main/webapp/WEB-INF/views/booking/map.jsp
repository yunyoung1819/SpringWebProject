<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title> 숙박 예약 </title>
<link rel="stylesheet" href="https://openlayers.org/en/v4.6.4/css/ol.css" type="text/css">
<!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
<script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
<script src="https://openlayers.org/en/v4.6.4/build/ol.js"></script>
</head>
<body>
	 <div id="map" class="map"></div>
     <select id="layer-select">
       <option value="Aerial">Aerial</option>
       <option value="AerialWithLabels" selected>Aerial with labels</option>
       <option value="Road">Road (static)</option>
       <option value="RoadOnDemand">Road (dynamic)</option>
       <option value="collinsBart">Collins Bart</option>
       <option value="ordnanceSurvey">Ordnance Survey</option>
     </select>
	<script>
		var styles = [
			'Road',
			'RoadOnDemand',
			'Aerial',
			'AerialWithLabels',
			'collinsBart',
			'ordnanceSurvey'
		];
		var layers = [];
		var i, ii;
		for(i=0, ii=styles.length; i<ii; ++i){
			layers.push(new ol.layer.Tile({
				visible : false,
				preload : Infinity,
				source : new ol.source.BingMaps({
					key: 'AuCabqYjqUZKTvgUdHK2r6EOXL4NzgHFJhfOiMaZ8j285kTrcH9vZ4lB1GW2PeIc',
					imagerySet: styles[i]
				})
			}));
		}
		 var map = new ol.Map({
		        layers: layers,
		        // Improve user experience by loading tiles while dragging/zooming. Will make
		        // zooming choppy on mobile or slow devices.
		        loadTilesWhileInteracting: true,
		        target: 'map',
		        view: new ol.View({
		          center: [-6655.5402445057125, 6709968.258934638],
		          zoom: 13
		        })
		 });
		 
		 var select = document.getElementById('layer-select');
	      function onChange() {
	        var style = select.value;
	        for (var i = 0, ii = layers.length; i < ii; ++i) {
	          layers[i].setVisible(styles[i] === style);
	        }
	      }
	      select.addEventListener('change', onChange);
	      onChange();
	</script>
</body>
</html>
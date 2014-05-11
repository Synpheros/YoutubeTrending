<head>
	<meta charset="UTF-8">
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript"><!--
		var colors = ['#FFFFFF'];
		var map;
		
		var videos;
		
	    function initialize() {

	    	var styles = [
			    {stylers: [{ hue: "#000000" },{ saturation: 100 },{ lightness: -25 }]},
			    {featureType: "administrative.country",elementType: "geometry.fill",
			    	stylers: [{ hue: "#000000" },{ saturation: 90 },{ lightness: 100 },{ visibility: "on" }]},
			    {featureType: "administrative.country",elementType: "geometry.stroke",
			    	stylers: [{ hue: "#000000" },{ saturation: -100 },{ lightness: -100 },{ visibility: "on" }]},
			    {featureType: "administrative.country",elementType: "labels.text.fill",
			    	stylers: [{ hue: "#000000" },{ saturation: 100 },{ lightness: 100 },{ visibility: "on" }]},
			    {featureType: "administrative.country",elementType: "labels.text.stroke",
			    	stylers: [{ hue: "#000000" },{ saturation: 100 },{ lightness: -100 },{ visibility: "on" }]},
			    {featureType: "water",elementType: "all",
			    	stylers: [{ visibility: "on" },{ lightness: 100 },{ saturation: -100 }]}
			];

			var styledMap = new google.maps.StyledMapType(styles,{name: "Styled Map"});


			var latlng = new google.maps.LatLng(40.419272, -3.702196);
			var settings = {
				zoom: 3,
				center: latlng,
				backgroundColor: '#FFFFFF',
				mapTypeControl: true,
				mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
				navigationControl: true,
				navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			var myOptions = {
				zoom: 2,
				center: new google.maps.LatLng(10, 0),
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};

	        map = new google.maps.Map(document.getElementById('map_canvas'), myOptions);

	        // Initialize JSONP request
	        var script = document.createElement('script');
	        var url = ['https://www.googleapis.com/fusiontables/v1/query?'];
	        url.push('sql=');
	        var query = 'SELECT name, kml_4326, iso_a2 FROM ' +
	            '1foc3xO9DyfSIF6ofvN0kp2bxSfSeKog5FbdWdQ';
	        var encodedQuery = encodeURIComponent(query);
	        url.push(encodedQuery);
	        url.push('&callback=drawMap');
	        url.push('&key=AIzaSyC6_0NT7ygHLLMMjHGJD1kiUveq37wiScE');
	        script.src = url.join('');
	        var body = document.getElementsByTagName('body')[0];
	        body.appendChild(script);

			map.mapTypes.set('map_style', styledMap);
	  		map.setMapTypeId('map_style');
      }

      function drawMap(data) {
        var rows = data['rows'];
        for (var i in rows) {
          if (rows[i][0] != 'Antarctica') {
            var newCoordinates = [];
            var geometries = rows[i][1]['geometries'];
            if (geometries) {
              for (var j in geometries) {
                newCoordinates.push(constructNewCoordinates(geometries[j]));
              }
            } else {
              newCoordinates = constructNewCoordinates(rows[i][1]['geometry']);
            }
            var randomnumber = Math.floor(Math.random() * 4);
            var country = new google.maps.Polygon({
              paths: newCoordinates,
              strokeColor: "#FF0000",
              strokeOpacity: 0,
              strokeWeight: 1,
              fillColor: "#FF0000",
              fillOpacity: 0.1,
              rowsme: rows[i]
            });
            google.maps.event.addListener(country, 'mouseover', function() {
            	if(selectedcountry!==this)
              		this.setOptions({fillOpacity: 0.8});
            });
            google.maps.event.addListener(country, 'mouseout', function() {
            	if(selectedcountry!==this)
              		this.setOptions({fillOpacity: 0.1});
            });

            google.maps.event.addListener(country, 'click', function() {
            	if(selectedcountry!==null)
            		selectedcountry.setOptions({fillOpacity: 0.1, fillColor: "#FF0000"});

            	if(selectedcountry===this){
            		closeAll();
            		selectedcountry = null;
            	}else{
            		closeAll();
	            	selectedcountry = this;
					this.setOptions({fillOpacity: 0.8, fillColor: "#000000"});
					$('#loadingid').css("display","block");
					
					$.ajax({
						type: "POST",
						url: 'GetTrending',
						contentType: "application/x-www-form-urlencoded;charset=utf-8",
						data: {Country : this.rowsme[2]},
						success: function(responseText) {
							this.obj = JSON.parse(responseText);
							
							$('#loadingid').css("display","none");
							getYouTubeInfo(this.obj["videolist"]);
							videos = this.obj["videolist"];
							changeVideo(0);
							document.getElementById('paisid').innerHTML = selectedcountry.rowsme[0];
							document.getElementById('listname').innerHTML = ""+selectedcountry.rowsme[0]+" Top Ten Trending Video";
							openAll();
						}
					});
					
		            geocoder.geocode( { 'address': this.rowsme[0]}, function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						map.panTo(results[0].geometry.location,3,google.maps.MapTypeId.ROADMAP);
						} else {
							alert('Geocode was not successful for the following reason: ' + status);
						}
					});
		        }
	        });


            country.setMap(map);
          }
        }
      }

      var selectedcountry = null;
      var geocoder = new google.maps.Geocoder();
		

      function constructNewCoordinates(polygon) {
        var newCoordinates = [];
        var coordinates = polygon['coordinates'][0];
        for (var i in coordinates) {
          newCoordinates.push(
              new google.maps.LatLng(coordinates[i][1], coordinates[i][0]));
        }
        return newCoordinates;
      }

      function closeAll() {
		document.getElementById('country_container').style.top = "-50px";
		document.getElementById('video_container').style.left = "-580px";
		document.getElementById('trend_container').style.right = "-580px";
      }
      
      function openAll() {
    	  
    	$("#video_container").css("top",$(window).height()/2-($("#video_container").height()/2));
    	$("#trend_container").css("top",$(window).height()/2-($("#trend_container").height()/2));
		document.getElementById('country_container').style.top = "50px";
		document.getElementById('video_container').style.left = "0px";
		document.getElementById('trend_container').style.right = "0px";
      }
      
	function getYouTubeInfo(videoid) {
		$('#videolistcontent').html("");
		 $('#videolistcontent').html($('#videolistcontent').html()+"<tr><td><p class=\"tabletitle\">Video Title</td><td><p class=\"tabletitle\">Points</p></td><td><p class=\"tabletitle\">Tweets</p></td></tr>");
		for(var i=0; i<videoid.length; i++){
			parseresults(videoid[i], i);
		}
	}
	
	  function parseresults(data, ind) {
		  data.title = "Deleted Video";
	      if(data.Data)
         	 data.title = data.Data.entry.title.$t;

          $('#videolistcontent').html($('#videolistcontent').html()+"<tr><td><a onclick=\'changeVideo(\""+ind+"\")\'>"+(ind+1)+" - "+data.title+"</a></td><td class=\"numeric\">"+data.puntuation+"</td><td class=\"numeric\">"+(parseInt(data.count)+1)+"</td></tr>");
	  }

      function changeVideo(ind) {
    	  this.i = parseInt(ind)+1;
    	  $('#videotitle').html(this.i+" - "+videos[ind].title);
    	  $('#youtubevideo').attr('src', "//www.youtube.com/embed/"+videos[ind].entry);
    	  if(videos[ind].Data){
    	  if(videos[ind].Data.entry.gd$rating){
    	  	$('#greendivid').css("width",(videos[ind].Data.entry.gd$rating.average/videos[ind].Data.entry.gd$rating.max)*100+"%");
    	  	$('#videoratingid').html(videos[ind].Data.entry.gd$rating.average.toFixed(3) + ' out of ' + videos[ind].Data.entry.gd$rating.max + ' (' + videos[ind].Data.entry.gd$rating.numRaters + ' ratings)'); 
    	  }else{
    		  $('#greendivid').css("width","50%");
			  $('#videoratingid').html("Not rated yet."); 
    	  }
    	  $('#videoauthor').html(videos[ind].Data.entry.author[0].name.$t);
    	  $('#videodate').html((new Date(videos[ind].Data.entry.published.$t)).toLocaleDateString());
    	  }else{
    		  $('#greendivid').css("width","50%");
			  $('#videoratingid').html("Deleted."); 
    		  $('#videoauthor').html("Deleted");
        	  $('#videodate').html("Deleted");  
    	  }
      }

      google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="principal">
		<div class="shadow"></div>
		<div id="map_canvas"></div>
		<div class="topcontainer">
			<div class="topbanner">
				<h1>YOUTUBE TWEETINIZER</h1>
			</div>
		</div>

		<div id="country_container" class="countrycontainer">
			<div class="countrybanner">
					<h1 id="paisid"></h1>
			</div>
		</div>

		<div id="video_container" class="videocontainer">
			<div class="videodiv">
				<h1 id="videotitle"></h1>
				<iframe id="youtubevideo" width="550" height="300" src="//www.youtube.com/embed/aS2XGWC7ZtQ" frameborder="0" allowfullscreen></iframe>
				<div class="reddiv"><p id="videoratingid" class="videorating"></p><div id="greendivid" class="greendiv"></div></div>
				<p><b>Autor: </b><span id="videoauthor"></span></p>
				<p><b>Date: </b><span id="videodate"></span></p>
			</div>
		</div>

		<div id="trend_container" class="trendcontainer">
			<div class="trenddiv">
				<h1 id="listname"></h1>
				<div id="videolist">
					<table>
					<tbody id="videolistcontent"></tbody>
					</table>
				</div>
			</div>
		</div>

		<div id="country_container" class="countrycontainer">
			<div class="countrybanner">
					<h1 id="paisid"></h1>
			</div>
		</div>

		<div class="botcontainer">
			<div class="botbanner" onclick="openAll()">
				<h2>Copyright 2014 - Youtube Tweetinizer</h2>
				<h2>Todos los derechos reservados</h2>
			</div>
		</div>
		<div id="loadingid" class="loading">
			<div></div><img src="imas/loading.gif">
		</div>
	</div>
</body>
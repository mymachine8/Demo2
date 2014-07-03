//UserDetailsController

'use strict';

/* Controllers */

myApp.controller('UserDetailsController', function($scope,BookDB) {

  $scope.init = function() {
      /*      var mapOptions = {
        zoom: 12,
        center: new google.maps.LatLng(-28.643387, 153.612224),
        mapTypeControl: true,
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
            position: google.maps.ControlPosition.BOTTOM_CENTER
        },
        panControl: true,
        panControlOptions: {
            position: google.maps.ControlPosition.TOP_RIGHT
        },
        zoomControl: true,
        zoomControlOptions: {
            style: google.maps.ZoomControlStyle.LARGE,
            position: google.maps.ControlPosition.LEFT_CENTER
        },
        scaleControl: true,
        streetViewControl: true,
        streetViewControlOptions: {
            position: google.maps.ControlPosition.LEFT_TOP
        }
      } 
	var map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
    */
  	var input = document.getElementById('pac-input');
  	var autocomplete = new google.maps.places.Autocomplete(input);
  }
  	
    });
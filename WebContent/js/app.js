'use strict';


// Declare app level module which depends on filters, and services
var myApp = angular.module('myApp', [
  'ngRoute'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {templateUrl: 'template/EnterBookInfo.html', controller: 'AddBookController'});
  $routeProvider.when('/map',{templateUrl:'template/UserDetails.html', controller: 'UserDetailsController'});
  $routeProvider.when('/login',{templateUrl:'template/LoginPage.html', controller: 'LoginController'});
  $routeProvider.otherwise({redirectTo: '/view1'});
}]);

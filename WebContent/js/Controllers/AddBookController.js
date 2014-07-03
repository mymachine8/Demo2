'use strict';

/* Controllers */

myApp.controller('AddBookController', function($scope,BookDB) {

  	$scope.displayText = "Default Shit";
    $scope.books = [];
  	$scope.addBook = function (){ $scope.books.push({ name :$scope.newBook.name,author: $scope.newBook.author}); };
  });

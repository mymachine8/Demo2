'use strict';

/* Services */
myApp.factory('BookDB', function () {
	return {
		getBooks : function() {
				var books = {};
				books = [
					  	{name : 'Algorithm Design Manual', author:'Ron Rivest'},
					  	{name:'The Alchemist', author:'Paulo Coelho'}
  						];
  				return books;
  				}	
			}
	});


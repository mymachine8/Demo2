//login controller

myApp.controller('LoginController', function($scope,GSignInConfig) {
	  var Conf = GSignInConfig.getConfig();
	   $scope.renderSignIn = function() {
		    gapi.signin.render('myGsignin', {
		      'callback': $scope.signInCallback,
		      'clientid': Conf.clientId,
		      'requestvisibleactions': Conf.requestvisibleactions,
		      'scope': Conf.scopes,
		      // Remove the comment below if you have configured
		      // appackagename in services.js
		      //'apppackagename': Conf.apppackagename,
		      'theme': 'dark',
		      'cookiepolicy': Conf.cookiepolicy,
		      'accesstype': 'offline'
		    });
		  }
	  $scope.signInCallback = function(authResult) {
		  if (authResult['code']) {

		    // Hide the sign-in button now that the user is authorized, for example:
		    $('#signinButton').attr('style', 'display: none');

		    // Send the code to the server
		    $.ajax({
		      type: 'POST',
		      url: 'GConnect',
		      contentType: 'application/octet-stream; charset=utf-8',
		      success: function(result) {
		        // Handle or verify the server response if necessary.
				var d = result;
		        // Prints the list of people that the user has allowed the app to know
		        // to the console.
		        console.log(result);
		        if (result['profile'] && result['people']){
		          $('#results').html('Hello ' + result['profile']['displayName'] + '. You successfully made a server side call to people.get and people.list');
		        } else {
		          $('#results').html('Failed to make a server-side call. Check your configuration and console.');
		        }
		      },
		      processData: false,
		      data: authResult['code']
		    });
		  } else if (authResult['error']) {
			  $('#results').html("Crap!! stuff failed greatly");
		    // There was an error.
		    // Possible error codes:
		    //   "access_denied" - User denied access to your app
		    //   "immediate_failed" - Could not automatially log in the user
		    // console.log('There was an error: ' + authResult['error']);
		  }
		}
		
		$scope.start = function() {
			$scope.renderSignIn();
		}
	
 
  });
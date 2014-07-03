myApp.factory('GSignInConfig', function ($location) {
	function getRootUrl() {
        var rootUrl = $location.protocol() + '://' + $location.host();
        if ($location.port())
          rootUrl += ':' + $location.port();
        return rootUrl;
      };
	return {
		getConfig : function() {
			 var config = {
				 clientId :'408623907339-t2to3m90fu8fmtdetr2r032ummhv80b5.apps.googleusercontent.com',
			     apiBase: '/api/',
				 rootUrl: getRootUrl(),
				 scopes: 'https://www.googleapis.com/auth/plus.login ',
				 requestvisibleactions: 'http://schemas.google.com/AddActivity ' +
					         'http://schemas.google.com/ReviewActivity',
				 cookiepolicy: 'single_host_origin' 
  				};	
			 return config;
			}
	     }
	});

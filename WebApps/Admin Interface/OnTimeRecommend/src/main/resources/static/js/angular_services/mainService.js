app.service('mainService', function($http, $q, $location, $window) {
    //var serverURL = "http://204.76.187.58:8080/OnTimeRecommendAPI/";
	//var serverURL = "http://localhost:9090/OnTimeRecommendAPI/";
    var webServiceURL = "http://localhost:8080/OnTimeRecommend/";
//    /var webServiceURL = "http://204.76.187.58:8080/OnTimeRecommend/";
    
    var serverURL = "http://localhost:8050/";
    
	return {
	   callPostRestAPI : function(url, data) {
		   $('#cover').show();
	   var url = serverURL + url;

	   return $http({
		    method : "POST",
		    url : url,
		    data : data,
		    headers : {
		        'Content-Type' : 'application/json'
		    }
		}).then( function(response){
				console.log(response);
				$('#cover').hide();
				return response.data;
		},function(response){
				console.log(response);
				$('#cover').hide();
				var serverError =false;
				if(response.data.exception == 'java.io.FileNotFoundException'){
					if(response.data.message == "KNOWLEDGEBASE_NOT_FOUND_INTRNT"){
						serverError = true;
						alert("Please Check Knowledge Base Source URL. We can not download knowledge base from given URL.");	
	  				}
				}
				
				if(!serverError){
					alert("Error While Calling API " + url);
				}
				
				throw new Error("Error While Calling "+url+" API ");
			});
	   },
	   
	   callWebServiceRestAPI : function(url, data){
		   
		   var url = webServiceURL + url;
		   $('#cover').show();
		   return $http({
			    method : "POST",
			    url : url,
			    data : data,
			    headers : {
			        'Content-Type' : 'application/json'
			    }
			}).then( function(response){
					console.log(response);
					$('#cover').hide();
					return response.data;
			},function(response){
				$('#cover').hide();
					console.log(response);
					alert("Error While Calling API " + url);
					throw new Error("Error While Calling "+url+" API ");
			});
		   
	   },
	   
	   downloadFile : function(url) {
			$window.location = serverURL + url;

	   }
   }
   
});

app.service('sharedService', function() {
	   

	var _sharedData = {};

	return {
	    getSharedData: function () {
	        return _sharedData;
	    },
	    setSharedData: function (value) {
	    	_sharedData = value;
	    }
	};
	
});

app.controller('renderInput-controller',function($scope,$location, mainService, $routeParams,recSystemService){

	$scope.recInputParam={};
	$scope.getRecommenderDetails = function(){
		
		var recId = $routeParams.recId;
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("recommender-registry-services/getRecommenderDetails", input).then(function (data) {
			console.log(data);
			$scope.rec = data;
			
			$scope.recInputs = JSON.parse($scope.rec.inputParamter);
			console.log($scope.recInputs);
		});

	}
	
	$scope.getRecommenderDetails();
	
	$scope.showOutput = '';
	$scope.runRecommenderDetails = function(){
		$scope.showOutput = '';
		
		for(inputparam in $scope.recInputParam){
			try{
				if($scope.recInputParam[inputparam].toLowerCase() == "false"){
					$scope.recInputParam[inputparam] = false;
				}
				if($scope.recInputParam[inputparam].toLowerCase() == "true"){
					
					$scope.recInputParam[inputparam] = true;
				}
			}catch(e){
				console.log(e);
			}
		}

		var input = {}
		input['recInputParam'] = $scope.recInputParam;
		input['recId'] = $scope.rec['recId'];
		input['domain'] = 'neuro';
		input = angular.toJson(input);
		
		mainService.callPostRestAPI("recommendation-service/getRecommendation" , input).then(function (response) {
			$scope.showOutput = 'true';
			document.getElementById('recOutput').value=angular.toJson(response);
			console.log(response);
			$scope.recOutput = response;
		});
	}
	
});


app.controller('list-rec-controller',function($scope,$location, mainService, $routeParams){
	
	
	$scope.statusSDK = [];
	
	$scope.getAllRecommenderDetails = function(){
		
		var input = {};
		input['clientId'] = 4
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("recommendation-service/getRecommenderListClient/", input).then(function (data) {
			console.log(data);
			$scope.recList = data;
		});

	}
	
	$scope.getAllRecommenderDetails();
	

	
});

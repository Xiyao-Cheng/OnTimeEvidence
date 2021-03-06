app.controller('list-recommender',function($scope,$location, mainService, $routeParams,recSystemService){
	
	$scope.publishRec = function(recId){
		var input = {};
		input['recId']=recId;
		input = angular.toJson(input);
		mainService.callPostRestAPI("/api/recommender/publishRec", input).then(function (data) {
			console.log(data);
			$scope.getAllRecommenderDetails();
		});
	}
	$scope.getAllRecommenderDetails = function(){
		var input = {};
		input = angular.toJson(input);
		mainService.callPostRestAPI("recommender-registry-services/getAllRecommenderList/", input).then(function (data) {
			console.log(data);
			$scope.recList = data;
		});
	}
	
	$scope.getAllRecommenderDetails();
	
	$scope.manageProcess = function(recId){
		$location.path("manageProcess/"+recId);
	}
	
	$scope.manageEnvDetails = function(recId){
		$location.path("manageEnvDetails/"+recId);
	}
	
	$scope.updateRecDetails = function(recId){
		$location.path("updateRecDetails/"+recId);
	}
});

app.controller('byor-controller',function($scope, $window ,$rootScope,$location, mainService){
	
	$scope.rec = {}
	
	$scope.addRecommenderDetails = function(prepareSDK, dataCollection, dataProcessing, trainModel){
		$scope.rec.inputParamter=document.getElementById('json-requestPayload-req').value;
		var input = $scope.rec;
		input = angular.toJson($scope.rec);
		mainService.callPostRestAPI("recommender-registry-services/addRecommenderDetails", input).then(function (data) {
			console.log(data);
			
			alert("Recommender System Details is registered with OnTimeRecommend");
			$location.path("bring_ur_rec_list");
			
			//$scope.rec=data;
		});

	}

});

app.controller('rec-as-a-service-controller',function($scope, $window ,$rootScope,$location, mainService){
	
	$scope.recIds = [];
	$scope.getAllClients = function(){
		var input = {};
		input = angular.toJson(input);
		mainService.callPostRestAPI("rec-orch-process-services/getAllClientsWithRecommender", input).then(function (data) {
			console.log(data);
			$scope.clients = data;
		});
		
		mainService.callPostRestAPI("recommender-registry-services/getAllRecommenderList/", input).then(function (data) {
			console.log(data);
			$scope.recList = data;
		});
	}
	
	$scope.addRecommenderToClient = function(clientId, recId){
		var input = {};
		input['clientId']=clientId;
		input['recId']=recId;
		input = angular.toJson(input);
		mainService.callPostRestAPI("rec-orch-process-services/addRecommenderForClient", input).then(function (data) {
			console.log(data);
			$scope.getAllClients();
		});
	}
	
	$scope.getAllClients();
});

app.controller('orchestrator-process',function($scope ,$routeParams,$location, mainService){
	var recId = $routeParams.recId;
	var clientId = $routeParams.clientId;

	$scope.clientId = clientId;
	$scope.recId = recId;
	$scope.getProcessConfigDetails = function(){
		
		$scope.recId = recId;
		var input = {};
		input['recId']= recId;
		input['clientId']= clientId;
		input = angular.toJson(input);
		mainService.callPostRestAPI("rec-orch-process-services/getProcessConfigDetails", input).then(function (data) {
			$scope.processConfigs = data;
		});
	}
	
	$scope.startQueue = function(clientId, recId){
		
		$scope.recId = recId;
		var input = {};
		input['recId']= recId;
		input['clientId']= clientId;
		input = angular.toJson(input);
		mainService.callPostRestAPI("rec-orch-process-services/publishQueue", input).then(function (data) {
			//$scope.processConfigs = data;
			alert("Queue execution is started");
		});
	}
	
	$scope.getAllProcessDetails = function(){
		
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
		
	
		mainService.callPostRestAPI("recommender-registry-services/getRecommenderDetails", input).then(function (data) {
			console.log(data);
			$scope.rec = data;
			$scope.recProcess = $scope.rec.recProcessDataLst;
		});

	}

	$scope.getProcessConfigDetails();
	$scope.getAllProcessDetails();
	
	$scope.getInputParams= function(processId){
		$scope.recInputParam = {};

		for(process in $scope.recProcess){
			var processDtl = $scope.recProcess[process];
			if(processId == processDtl.recProcessId){
				$scope.htmlTags =  JSON.parse(processDtl.inputParamater);
			}
		}
	}
	
	$scope.saveProcessOrch = function(recInputParam, recId, orchId){
		
		$scope.recOrchPrc['inputConfig']=angular.toJson(recInputParam);
		var input = {};
		input= $scope.recOrchPrc;
		input['recId'] = recId;
		input['clientId'] = clientId;
		input = angular.toJson(input);
		mainService.callPostRestAPI("rec-orch-process-services/saveProcessOrch", input).then(function (data) {
			console.log(data);
			$scope.getProcessConfigDetails();
		});
	}
	
	$scope.deleteProcess = function(orchPrcId){

		var input = {};
		input['orchPrcId'] = orchPrcId;
		input = angular.toJson(input);
		mainService.callPostRestAPI("rec-orch-process-services/deleteProcessOrch", input).then(function (data) {
			console.log(data);
			$scope.getProcessConfigDetails();
		});
		
	}
});

app.controller('client-controller',function($scope, $window ,$rootScope,$location, mainService){
	
	$scope.clients = {}
	$scope.getAllClients = function(){
		var input = {};
		input = angular.toJson(input);
		mainService.callPostRestAPI("manage-client-services/listClients", input).then(function (data) {
			console.log(data);
			$scope.clients = data;
		});
	}
	$scope.getAllClients();
	
	$scope.deleteClient = function(clientId){
		var input = {};
		input['id']=clientId;
		input = angular.toJson(input);
		mainService.callPostRestAPI("manage-client-services/deleteClient", input).then(function (data) {
			console.log(data);
			$scope.clients = data;
			$location.path('science_gateway');
		});
	}
	
});

app.controller('add-client',function($scope, $window ,$rootScope,$location, mainService){
	
	$scope.client = {}
	$scope.saveClient = function(){
		var input = {};
		input=$scope.client;
		input = angular.toJson(input);
		mainService.callPostRestAPI("manage-client-services/addClient", input).then(function (data) {
			console.log(data);
			//$location.path('science_gateway');
			alert("Client registed in our system. We will contact using register email address.");
			$location.path("/");
		});
	}
});

app.controller('update-rec-controller',function($scope,$location, mainService, $routeParams,recSystemService){
	
	$scope.getRecommenderDetails = function(){
		
		var recId = $routeParams.recId;
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("recommender-registry-services/getRecommenderDetails", input).then(function (data) {
			console.log(data);
			$scope.rec = data;
			
			$scope.recInputs = JSON.parse($scope.rec.inputParamter);
			document.getElementById('json-requestPayload-req').value = $scope.rec.inputParamter;
			/*console.log($scope.recInputs);*/
		});

	}
	
	$scope.getRecommenderDetails();
	
	$scope.updateRecommenderDetails = function(){
		
		$scope.rec.inputParamter=document.getElementById('json-requestPayload-req').value;
		var input= {};
		input = $scope.rec;
		input = angular.toJson(input);
		mainService.callPostRestAPI("recommender-registry-services/updateRecommenderDetails", input).then(function (data) {
			console.log(data);
			alert("Recommender Details are updated.");
			$location.path("/bring_ur_rec_list");
		});
	}
	
});



app.controller('manage-process',function($scope,$location, mainService, $routeParams,recSystemService){
	
	$scope.getAllProcessDetails = function(){
		
		var recId = $routeParams.recId;
		$scope.recId = recId;
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
		
	
		mainService.callPostRestAPI("recommender-registry-services/getRecommenderDetails", input).then(function (data) {
			console.log(data);
			$scope.rec = data;
			$scope.recProcess = $scope.rec.recProcessDataLst;
			
			/*mainService.callPostRestAPI("rec-workflow-const-services/getAllProcessDetails", input).then(function (data) {
				console.log(data);
				$scope.recProcess = data;
			});*/
		});

	}
	
	$scope.getAllProcessDetails();
	
	$scope.manageProcess = function(recId){
		$location.path("manageProcess/"+recId);
	}
	
	$scope.deleteProcess = function(process){
		var input = process;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("rec-workflow-const-services/deleteProcess", input).then(function (data) {
			console.log(data);
			alert("Process is deleted.");
			$scope.getAllProcessDetails();
		});
	}
	
});

app.controller('add-process',function($scope,$location, mainService, $routeParams,recSystemService){
	$scope.proc = {};
	$scope.saveProcess = function(){
		//alert("saveProcess");
		var input = {};
		input= $scope.proc;
		input['recId']=$routeParams.recId;
		input['inputParamater']=document.getElementById("json_requestPayload").value;
		input = angular.toJson(input);
		
		mainService.callPostRestAPI("rec-workflow-const-services/saveProcess", input).then(function (data) {
			alert("New Process is added.");
			console.log(data);
			$location.path("manageProcess/"+$routeParams.recId);
		});
	}
});

app.controller('edit-process',function($scope,$location, mainService, $routeParams,recSystemService){
	$scope.proc = {};
	$scope.editProcess = function(){
		var input = {};
		input= $scope.proc;
		input['recId']=$scope.rec.recId;
		input['inputParamater']=document.getElementById("json_requestPayload").value;
		input = angular.toJson(input);
		
		mainService.callPostRestAPI("rec-workflow-const-services/editProcess", input).then(function (data) {
			alert("Process is updated.");
			console.log(data);
			$location.path("manageProcess/"+$scope.rec.recId);
		});
	}
	
	$scope.getProcessDetails = function(){
		
		var processId = $routeParams.processId;
		var input = {};
		input['processId']= processId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("rec-workflow-const-services/getProcessDetails", input).then(function (data) {
			console.log(data);
			$scope.proc = data['RecProcessData'];
			document.getElementById('json_requestPayload').value = $scope.proc['inputParamater'];
			$scope.rec = data['RecommendApps'];
		});

	}
	
	$scope.getProcessDetails();
});


app.controller('render-process-controller',function($scope,$location, mainService, $routeParams,recSystemService){
	
	$scope.getProcessDetails = function(){
		
		var processId = $routeParams.processId;
		var input = {};
		input['processId']= processId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("rec-workflow-const-services/getProcessDetails", input).then(function (data) {
			console.log(data);
			$scope.processDtl = data['RecProcessData'];
			$scope.rec = data['RecommendApps'];
			$scope.recInputs = JSON.parse($scope.processDtl['inputParamater']);
		});

	}
	
	$scope.getProcessDetails();
	$scope.recInputParam={};
	$scope.runService = function(){

		$scope.recInputParam['idRecProcess']=$scope.processDtl.recProcessId;
		
		var input = {}
		input['recInputParam'] = $scope.recInputParam;
		var url = "http://"+$scope.rec['hostName']+":"+$scope.rec['port']+"/"+$scope.processDtl['apiUrl'];
		input['url'] = url;
		input = angular.toJson(input);

		mainService.callWebServiceRestAPI('/webServices/callServices', input).then(function (data) {
			console.log(data);
			
			var inputUpdate = {};
			inputUpdate['status']=data['status'];
			inputUpdate['processId']=$scope.processDtl.recProcessId;
			inputUpdate = angular.toJson(inputUpdate);

			mainService.callPostRestAPI("/api/services/updateProcessStatus", inputUpdate).then(function (data) {
				console.log(data);
				alert("Process Started.");
				$location.path("/bring_ur_rec_list");

			});
			/*$scope.getAllProcessDetails();*/
		});
	}
	
});



app.controller('manageEnvDetails',function($scope,$location, mainService, $routeParams,recSystemService){

	$scope.getRecommenderDetails = function(){
		
		var recId = $routeParams.recId;
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("/api/recommender/getRecommenderDetails", input).then(function (data) {
			console.log(data);
			$scope.rec = data;
		
		});

	}
	
	$scope.getRecommenderDetails();
	
	$scope.deployRecommender = function(){
		console.log($scope.rec);
		var input = {};
		input = angular.toJson($scope.rec);
		mainService.callPostRestAPI("/api/recommender/deployRecommender", input).then(function (data) {
			console.log(data);
			alert("Recommender is deployed successfully. You can use recommender now");
			$location.path("bring_ur_rec_list");
		
		});

	}
	
});

app.controller('renderInput-controller',function($scope,$location, mainService, $routeParams,recSystemService){

	$scope.recInputParam={};
	$scope.getRecommenderDetails = function(){
		
		var recId = $routeParams.recId;
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("/api/recommender/getRecommenderDetails", input).then(function (data) {
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
		var url = "http://"+$scope.rec['hostName']+":"+$scope.rec['port']+"/"+$scope.rec['apiUrl'];
		input['url'] = url;
		input = angular.toJson(input);
		
		mainService.callWebServiceRestAPI("/webServices/callServices" , input).then(function (response) {
			$scope.showOutput = 'true';
			document.getElementById('recOutput').value=angular.toJson(response);
			console.log(response);
			$scope.recOutput = response;
			
			/*
			
			var outputHTML = '';
			
			if($scope.rec.outputType == 'table'){
				outputHTML = '<table class="table table-striped table-bordered"><thead ><tr>';
				var outputVarName =  $scope.rec.outputObjectName ;// 'topics';
				var headerArray = $scope.rec.objectNameToDisplay.split(",");

				if(typeof outputVarName !== 'undefined' && outputVarName != ''){
					var output= response[outputVarName];
				}else{
					var output=response;
				}
				
				outputHTML = outputHTML + '<th>#</th>';
				for(headerInd in headerArray){
					outputHTML = outputHTML + '<th class="capitalize" >'+headerArray[headerInd]+'</th>';
	
				}
				outputHTML = outputHTML + '</tr></thead><tbody>';
				if(output.length > 0){
					for(index in output){
						
						var row = output[index];
						outputHTML = outputHTML + '<tr>';
						outputHTML = outputHTML + '<td>'+(parseInt(index)+1)+'</td>';
	
						for(headerInd in headerArray){
							var rowDisplayObjectName = headerArray[headerInd];
							outputHTML = outputHTML + '<td>'+row[rowDisplayObjectName]+'</td>';
						}
						outputHTML = outputHTML + '</tr>';
					}
				}else{
					
					outputHTML = outputHTML + '<tr>';
					outputHTML = outputHTML + '<td colspan="' + ( parseInt(headerArray.length) + 1 ) +'">No Details Found</td>';
					outputHTML = outputHTML + '</tr>';
				}
				outputHTML = outputHTML + '</tbody></table>';
			
			}else if($scope.rec.outputType == 'list'){
				
				var outputVarName =  $scope.rec.outputObjectName ;
				var headerArray = $scope.rec.objectNameToDisplay.split(",");
				if(outputVarName !== undefined && outputVarName !== null && outputVarName != ''){
					var output= response[outputVarName];
				}else{
					var output=response;
				}
				
				
				if(output.length > 0){
					outputHTML = outputHTML + '<ol>';
					//outputHTML = outputHTML + '<div class="capitalize" >'+headerArray[headerInd]+'</div>';

					for(index in output){
						outputHTML = outputHTML + '<li class="rec-list">';
						var row = output[index];
						for(headerInd in headerArray){
							var rowDisplayObjectName = headerArray[headerInd];
							outputHTML = outputHTML + '<div><span class="capitalize bold-500" >'+headerArray[headerInd]+'</span> : ';
							outputHTML = outputHTML + row[rowDisplayObjectName]+'</div>';
						}
						outputHTML = outputHTML + '</li>';

					}
					outputHTML = outputHTML + '</ol>';

				}else{
					outputHTML = outputHTML + '<div>No Details Found</td>';
				}
				
			}
			
			$scope.finalOutputHTML = outputHTML;
			console.log(outputHTML);
		*/});
	}
	
});




app.controller('knowledgebase-controller',function($scope,$location, mainService, $routeParams){
	
	/*$scope.rec = {}*/
	
	$scope.statusKnowledgebase = [];
	
	$scope.getRecommenderDetails = function(){
		
		var recId = $routeParams.recId;
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("/api/recommender/getRecommenderDetails", input).then(function (data) {
			console.log(data);
			$scope.rec = data;
		});

	}
	
	$scope.getRecommenderDetails();
	
	$scope.addKnowledgeBaseDetails = function(){
		var input = angular.toJson($scope.rec);
		$scope.statusKnowledgebase['WORKING'] = true; 
		mainService.callPostRestAPI("/api/recommender/knowledgeBase/addKnowledgeBaseDetails", input).then(function (data) {
			console.log(data);
			alert("Knowledge Base Details is added with OnTimeRecommend");
			$location.path("algorithm_rec/"+data.recId);
			
		});

	}
	
});



app.controller('algorithm-controller',function($scope,$location, mainService, $routeParams, $http, $window){
	
	/*$scope.rec = {}*/
	
	$scope.statusSDK = [];
	
	$scope.getRecommenderDetails = function(){
		
		var recId = $routeParams.recId;
		var input = {};
		input['recId']= recId;
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("/api/recommender/getRecommenderDetails", input).then(function (data) {
			console.log(data);
			$scope.rec = data;
		});

	}
	
	$scope.getRecommenderDetails();
	
	$scope.prepareSDK = function(){
		var input = angular.toJson($scope.rec);
		$scope.statusSDK = 'WORKING'; 
		mainService.callPostRestAPI("/api/recommender/algorithm/addAlgorithmDetails", input).then(function (data) {
			console.log(data);
			$scope.statusSDK= 'DONE'; 
		});
	}
	
	$scope.downloadSDK = function(recId){
		
		//$window.location="http://localhost:9090/OnTimeRecommendAPI/api/recommender/SDK/downloadSDK?recId="+recId;
		mainService.downloadFile("api/recommender/SDK/downloadSDK?recId="+recId);
		$scope.statusSDK = 'DOWNLOAD'; 
		$location.path("developement_guide/"+recId);
	}
	
});


app.controller('list-rec-controller',function($scope,$location, mainService, $routeParams){
	
	
	$scope.statusSDK = [];
	
	$scope.getAllRecommenderDetails = function(){
		
		var input = {};
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("recommender-registry-services/getAllRecommenderList/", input).then(function (data) {
			console.log(data);
			$scope.recList = data;
		});

	}
	
	$scope.getAllRecommenderDetails();
	
	/*$scope.getAllChainDetails = function(){
		
		var input = {};
		input = angular.toJson(input);
	
		mainService.callPostRestAPI("/api/recommender/chainApp/getAllChainDetails", input).then(function (data) {
			console.log(data);
			$scope.chainList = data;
		});

	}	
	$scope.getAllChainDetails();


	$scope.openKnowledgebaseDtls = function(recId){
		$location.path("knowledgebase/"+recId);
	}
	$scope.openAlgorithmDtls = function(recId){
		$location.path("algorithm_rec/"+recId);
	}
	$scope.openDevelopementGuide = function(recId){
		$location.path("developement_guide/"+recId);
	}
	$scope.openDeploymentDtls = function(recId){
		$location.path("deploy_rec/"+recId);
	}
	$scope.renderInputRecPage = function(recId){
		$location.path("renderInputPage/"+recId);
	}
	$scope.renderInputChainApp = function(recId){
		$location.path("renderInputChainPage/"+recId);
	}*/
	
});


/*app.controller('scholar-finder-controller',function($scope, $window ,$rootScope,$location, mainService){

	$scope.labels = ['network', 'cloud', 'data', 'based', 'learning', 'edge'];

    $scope.data = [
      [1, 0.95, 0.9, 0.8, 0.75, 0.7, 0.6]
    ];
});*/


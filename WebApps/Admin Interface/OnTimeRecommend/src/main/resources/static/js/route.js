app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "view/common/about.html"
	})
	.when("/recommender_system",{
		templateUrl : "view/common/recommender.html"
	})
	.when("/bring_ur_rec",{
		templateUrl : "view/admin/rec_registry/addRec/bringUrRec.html"
	})
	.when("/bring_ur_rec_list",{
		templateUrl : "view/admin/rec_workflow_const/allNewRecList.html"
	})
	.when("/manageProcess/:recId",{
		templateUrl : "view/admin/rec_workflow_const/process/manageProcess.html"
	})
	.when("/add_new_proc/:recId",{
		templateUrl : "view/admin/rec_workflow_const/process/add_new_proc.html"
	})
	.when("/updateRecDetails/:recId",{
		templateUrl : "view/admin/rec_registry/update/updateRecDetails.html"
	})
	.when("/edit_proc/:processId",{
		templateUrl : "view/admin/rec_workflow_const/process/edit_new_proc.html"
	})
	.when("/openProcessForm/:processId",{
		templateUrl : "view/admin/rec_workflow_const/process/openProcessForm.html"
	})
	.when("/science_gateway/",{
		templateUrl : "view/admin/science_gateway/science_gateway.html"
	})
	.when("/add_new_client/",{
		templateUrl : "view/admin/science_gateway/add_new_client.html"
	})
	.when("/rec_orch/",{
		templateUrl : "view/admin/rec_orch/list_clients.html"
	})
	.when("/openProceessOrch/:recId/:clientId",{
		templateUrl : "view/admin/rec_orch/openProceessOrch.html"
	})
	.when("/renderInputPage/:recId",{
		templateUrl : "view/admin/byor/renderInputPage.html"
	})
	/*.when("/manageEnvDetails/:recId",{
		templateUrl : "view/admin/byor/manage/manageEnvDetails.html"
	})
	.when("/algorithm_rec/:recId",{
		templateUrl : "view/admin/byor/algorithmDetails.html"
	})
	.when("/knowledgebase/:recId",{
		templateUrl : "view/admin/byor/knowledgebase.html"
	})
	.when("/developement_guide/:recId",{
		templateUrl : "view/admin/byor/developementGuide.html"
	})
	.when("/deploy_rec/:recId",{
		templateUrl : "view/admin/byor/deploy_rec.html"
	})
	.when("/chain_rec/",{
		templateUrl : "view/admin/byor/chainApp/chain_rec.html"
	})
	.when("/renderInputChainPage/:appId",{
		templateUrl : "view/admin/byor/chainApp/renderInputChainPage.html"
	})*/

});

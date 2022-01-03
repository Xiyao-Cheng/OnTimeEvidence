app.config(function($routeProvider) {
	/*$routeProvider.when("/", {
		templateUrl : "view/common/about.html"
	})*/
	$routeProvider.when("/",{
		templateUrl : "view/common/recommender.html"
	})
	.when("/renderInputPage/:recId",{
		templateUrl : "view/user/byor/renderInputPage.html"
	})

	
});

app.controller('offerController', function($scope, $http) 
{
	$scope.message = "";
	
	$scope.subscribe = function(userSubscription) {
		 $http.post("/subscribe", userSubscription).then(function(response)
				 {
				 $scope.respmessage = response.data.message;
				 }
		 );
	}
});
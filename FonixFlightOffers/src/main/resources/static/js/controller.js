app.controller('offerController', function($scope, $http) 
{
	$scope.subscribe = function(userSubscription) {
		 $http.post("/subscribe", userSubscription).then( function(response)
				 {
				 $scope.status = response.data;
				 }
		 );
	}
});
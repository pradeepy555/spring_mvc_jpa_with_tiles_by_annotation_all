myApp.controller("mycontroller",function($scope,$http){
	$scope.test="ggggg";
	$scope.person={};
	console.log("test///////")
	
	
	$scope.list =function(){
		$http({
			   method:'POST',
			   url:'list'
			
		     }).then(
				function(response){
					console.log("success");
					$scope.responseDataArray =response.data;
					console.log($scope.responseDataArray)
				},
				function(response){
					console.log("error");
				}
				);
	
	}
	
	$scope.list();
	
	
	
	$scope.save= function(){
		
		$http({
			   method:'POST',
			   url:'save',
			   data:$scope.person
			
		     }).then(
				function(response){
					console.log("success");
					$scope.list();
				},
				function(response){
					console.log("error");
				}
				);
	}
	

	

});
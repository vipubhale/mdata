var app = angular.module('mtdata', [])
app.controller('getData', function($scope, $http, $interval) {
		$interval(function () {

				$http.get('/data').then(function success(response) {
					$scope.modifiedMachinesdata = {};
					$scope.numberOfMachines = 1;
					$scope.getNumber = function(num) {
					    return new Array(num+1);   
					}
					var machineDataFromBackend = response.data;
					
					// Checking if error is there from backend
					if (machineDataFromBackend["code"]){
						$scope.errorCode = machineDataFromBackend["code"];
						$scope.errorData = "Error Calling the MTconnect Agent. Status Code is " + $scope.errorCode + ". Please check agent is up and try again.";
						console.log('Errorx while calling the agent '+ $scope.errorCode);
						$scope.modifiedMachinesdata = {};
						$scope.numberOfMachines = 0;
					}
					// For successful response
					else {
						var machineDataMap={};
					    angular.forEach(machineDataFromBackend,function(machine,arrayIndex){
					        angular.forEach(machine,function(machineValue,machineKey){//this is nested angular.forEach loop
					        	if (machineDataMap[machineKey] == null  ) {
					        		machineDataMap[machineKey] = [];
					        		(machineDataMap[machineKey]).push(machineValue)
					        	}
					        	else {
						            machineDataMap[machineKey].push(machineValue);
					        		$scope.numberOfMachines = machineDataMap[machineKey].length;
					        	}
					        });
					    });
			            console.log(machineDataMap)
					    $scope.modifiedMachinesdata = machineDataMap;
			            $scope.errorData = "";
					}
					
				},function error(error, status) {
					$scope.errorCode = status;
					if ($scope.errorCode != '200'){
						console.log('Error while calling the application '+ $scope.errorCode);
						$scope.errorData = "Error Calling the application.Status Code is " + $scope.errorCode  ;
						$scope.modifiedMachinesdata = {};
						$scope.numberOfMachines = 0;
					}
				});
		  }, 3000);
});

var app = angular.module('mtdata', [])
app.controller('getData', function($scope, $http, $interval) {
		$interval(function () {

				$http.get('/data').then(function success(response) {
					$scope.modifiedMachinesdata = {};
					$scope.numberOfMachines = 1;
					$scope.getNumber = function(num) {
					    return new Array(num+1);   
					}
//					$scope.machinesData = response.data;
					var machineDataFromBackend = response.data;

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
					
				},function error(error, status) {
					$scope.errorCode = status;
					if ($scope.errorCode == '500'){
						console.log('Error while calling the agent '+ $scope.errorCode);
						$scope.errorData = "Error Calling the Agent. HTTP Status Code is " + $scope.errorCode  ;
						$scope.modifiedMachinesdata = {};
						$scope.numberOfMachines = 0;
					}
					
				});
		  }, 3000);
//		
//
//	}
});

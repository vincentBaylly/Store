(function() {
	var app = angular.module('client-include', []);

	app.directive("productDescription", function() {
		 return {
			 restrict : "E",
			 templateUrl : "include/product-description.html",
			 controller :['$scope', '$http', function($scope, $http) {
				 $http({url: 'http://localhost:8181/back-store/data/description', method: 'GET', params:{id: $scope.productId}}).success(function(data) {
					 $scope.descriptionValue = data;
				 });
			}],
			controllerAs : "descriptionCtrl"
		}
	});

	app.directive("productReviews", function() {
		return {
			restrict : 'E',
			templateUrl : "include/product-reviews.html",
			controller :['$scope', '$http', function($scope, $http) {
				 $http({
							  url: 'http://localhost:8181/back-store/data/reviews',
							  method: 'GET',
							  params:{id : $scope.productId}
						 }).success(function(data) {
					 $scope.reviews = data;
				 });
			}],
			controllerAs : "reviewsCtrl"
		};
	});

	app.directive("productSpecs", function() {
		return {
			restrict : "A",
			templateUrl : "include/product-specs.html",
			controller :['$scope', '$http', function($scope, $http) {
				 $http({
					 url:'http://localhost:8181/back-store/data/specification',
					 method : 'GET',
					 params:{id : $scope.productId}
				 	}).success(function(data) {
					 $scope.specs = data;
				 });
			}],
			controllerAs : "specsCtrl"
		};
	});

	app.directive("productTabs", function() {
		return {
			restrict : "E",
			templateUrl : "include/product-tabs.html",
			scope: {	
				productId: '=info'
			},
			controller :function() {
				this.tab = 1;

				this.isSet = function(checkTab) {
					return this.tab === checkTab;
				};

				this.setTab = function(activeTab) {
					this.tab = activeTab;
				};
			},
			controllerAs : "tab"
		};
	});

})();
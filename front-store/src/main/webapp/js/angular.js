(function() {

	var app = angular.module('site', [ 'client-include', 'menu-include' ]);

	// Call the back-store service
	app.controller('StoreController', [
			'$http',
			function($http) {

				var store = this;

				store.products = [];

				$http.get('http://localhost:8181/back-store/data/products')
						.success(function(data) {
							store.products = data;
						});
			} ]);

	app.controller('ReviewController', ['$scope', '$http', function($scope, $http) {
		this.review = {};
		this.addReview = function(reviews) {
			reviews.push(this.review);
			$http({
				url : 'http://localhost:8181/back-store/data/addReview',
				method : 'GET',
				params : {
					id : $scope.productId,
					nbstar : this.review.nbStar,
					body : this.review.body,
					author : this.review.author
				}
			}).success(function(data) {
				$scope.reviews = data;
			});
			this.review = {};
		}
	}]);

})();
(function(){

	var app = angular.module('gemStore', ['store-include']);
	
	//Call the back-store service
	app.controller('StoreController', [ '$http', function($http){
	
		var store = this;
		
		store.products = [ ];
		
		$http.get('http://localhost:8181/back-store/data/products').success(function(data){
			store.products = data;
		});
	}]);
	
	app.controller('ReviewController', function(){
		this.review = {};
		this.addReview = function(reviews){
			reviews.push(this.review);
			this.review = {};
		}
	});
	

})();
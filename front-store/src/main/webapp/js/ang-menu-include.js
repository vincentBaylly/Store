(function() {
	var app = angular.module('menu-include', []);
	
	app.directive("menu", function() {
		return {
			restrict : "E",
			templateUrl : "include/menu.html",
			controller :function() {
				var toggled = 0;
				$(document).ready(function() {
					$('.toggle').click(function() {
						$('.menu').slideToggle();
						if (toggled == 0) {
							$('.burgx3').stop().transition({
								rotate : "45",
								"margin-top" : "13px"
							});
							$('.burgx2').stop().transition({
								opacity : "0"
							}, "fast");
							$('.burgx').stop().transition({
								rotate : "-45",
								"margin-top" : "13px"
							});
							toggled++;
						} else {
							$('.burgx3').stop().transition({
								rotate : "+=135",
								"margin-top" : "3px"
							});
							$('.burgx2').transition({
								opacity : "1"
							}, "fast");
							$('.burgx').stop().transition({
								rotate : "-=135",
								"margin-top" : "13px"
							});
							toggled--;
						}
					});
				});
			},
			controllerAs : "menu"
		};
	});
	
})();
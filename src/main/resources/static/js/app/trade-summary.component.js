(function() {
	'use strict';

	angular
		.module('componentTrade')
		.component('tradeSummary', {
			templateUrl : "trade-summary.component.html",
			controller : [
				TradeSummaryController
			]
		});

	function TradeSummaryController() {
		var ctrl = this;

		ctrl.summary = getSummaryTrade();

		function getSummaryTrade() {
			$http({
				method : 'GET',
				url : '/api/bitcoins/summary/'
			}).then(
				function(res) { // success
					$scope.summary = res.data;
				},
				function(res) { // error
					console.log("Error: " + res.status + " : " + res.data);
				}
			);
		}

		function _success(res) {
			refreshEmployeeData();

		}

		function _error(res) {
			var data = res.data;
			var status = res.status;
			var header = res.header;
			var config = res.config;
			alert("Error: " + status + ":" + data);
		}
	}
})();
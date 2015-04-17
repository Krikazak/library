var libraryApp = angular.module('libraryApp', ['libraryControllers', 'libraryDirectives', 'libraryServices', 'libraryModels', 'metawidget', 'ngDialog', 'ngRoute']);

//====================================//
//==	CONSTANTS
//====================================//

libraryApp.constant ("SERVER_URL", "/web/");

//====================================//

libraryApp.config(['$routeProvider',
  function($routeProvider) {
	$routeProvider.
		when('/add', {
			templateUrl: 'partials/addElement.html',
			controller: 'AddElementController'
		}).
		when('/zones', {
			templateUrl: 'partials/zones.html',
			controller: 'ZoneController'
		}).
		when('/elements', {
			templateUrl: 'partials/elements.html',
			controller: 'ElementController'
		}).
		otherwise({
			redirectTo: '/elements'
		});
}]);

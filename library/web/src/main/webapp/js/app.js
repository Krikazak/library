var libraryApp = angular.module('libraryApp', ['libraryControllers', 'libraryDirectives', 'libraryServices', 'libraryModels', 'metawidget', 'ngDialog', 'ui.router', 'ngMaterial']);

//====================================//
//==	CONSTANTS
//====================================//

libraryApp.constant ("SERVER_URL", "/web/");

//====================================//

libraryApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/elements');
	
	$stateProvider.
		state('add', {
			url:'/add',
			templateUrl: 'partials/addElement.html',
			controller: 'AddElementController'
		}).
		state('zones', {
			url:'/zones',
			templateUrl: 'partials/zones.html',
			controller: 'ZoneController'
		}).
		state('elements', {
			url:'/elements',
			templateUrl: 'partials/elements.html',
			controller: 'ElementController'
		}).
		state('menu', {
			url:'/menu',
			templateUrl: 'partials/menuZone.html',
			controller: 'MenuController'
		});
}]);

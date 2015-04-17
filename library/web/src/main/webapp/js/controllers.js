var libraryControllers = angular.module('libraryControllers', []);

libraryControllers.controller("AddElementController", function(ContentModel, ElementModel, $scope, ngDialog) {
	
	//$scope.foundContents = [{"searchEngine":"google.books","data":{"accessInfo":{"accessViewStatus":"NONE","country":"FR","embeddable":false,"epub":{"isAvailable":false},"pdf":{"isAvailable":false},"publicDomain":false,"quoteSharingAllowed":false,"textToSpeechPermission":"ALLOWED","viewability":"NO_PAGES","webReaderLink":"http://books.google.fr/books/reader?id=PA6TuAAACAAJ&hl=&printsec=frontcover&output=reader&source=gbs_api"},"etag":"cw7gebSbqXQ","id":"PA6TuAAACAAJ","kind":"books#volume","saleInfo":{"country":"FR","isEbook":false,"saleability":"NOT_FOR_SALE"},"searchInfo":{"textSnippet":"Elantris est le premier roman publié de l&#39;auteur du cycle des Fils-des-Brumes, Brandon Sanderson, également choisi pour terminer le cycle de la Roue du temps après le décès prématuré de l&#39;écrivain Robert Jordan."},"selfLink":"https://www.googleapis.com/books/v1/volumes/PA6TuAAACAAJ","volumeInfo":{"authors":["Brandon Sanderson"],"canonicalVolumeLink":"http://books.google.fr/books/about/Elantris.html?hl=&id=PA6TuAAACAAJ","categories":["Fiction"],"contentVersion":"preview-1.0.0","description":"Il y a dix ans, la sublime cité d'Elantris, capitale de l'Arélon, a été frappée de malédiction. Ses portes sont désormais closes et nul ne sait ce qui se passe derrière ses murailles. Kae est devenue la première ville de l'Arélon. Quand la princesse Sarène y arrive pour épouser Raoden, l'héritier de la couronne, on lui apprend qu'il vient de mourir. Veuve d'un homme qu'elle n'a jamais vu, Sarène choisit pourtant de rester à la cour, et tente de percer le mystère d'Elantris... Elantris est le premier roman publié de l'auteur du cycle des Fils-des-Brumes, Brandon Sanderson, également choisi pour terminer le cycle de la Roue du temps après le décès prématuré de l'écrivain Robert Jordan.","industryIdentifiers":[{"identifier":"2253159913","type":"ISBN_10"},{"identifier":"9782253159919","type":"ISBN_13"}],"infoLink":"http://books.google.fr/books?id=PA6TuAAACAAJ&dq=isbn:2253159913&hl=&source=gbs_api","language":"fr","pageCount":797,"previewLink":"http://books.google.fr/books?id=PA6TuAAACAAJ&dq=isbn:2253159913&hl=&cd=1&source=gbs_api","printType":"BOOK","publishedDate":"2011-01","publisher":"Livre de Poche","readingModes":{"text":false,"image":false},"title":"Elantris"}}}];
	
	$scope.contentModel = ContentModel;
	
	ContentModel.isbn = '2253159913';
	
	$scope.search = function() {
		ContentModel.search();
	};
     
     $scope.open = function(foundContent) {
    	 ElementModel.foundContent = foundContent;
    	 ngDialog.open({
    		 template : 'partials/element.html',
    		 controller : 'ElementPopinController'});
     };
});

libraryControllers.controller("ZoneController", function(ZoneModel, $scope) {
	
	$scope.zoneModel = ZoneModel;
	
	$scope.findAll = function() {
		ZoneModel.findAll();
	};
	
	function init() {
		ZoneModel.findAll();
	};
	
	init();
});

libraryControllers.controller("ElementController", function(ElementModel, $scope) {
	
	$scope.elementModel = ElementModel;
	
	$scope.find = function(query) {
		ElementModel.find(query);
	};
	
	function init() {
		ElementModel.find(null);
	};
	
	init();
});

libraryControllers.controller("ElementPopinController", function(ElementModel, ElementServices, $scope) {
	$scope.elementModel = ElementModel;
	
	$scope.save = function(data) {
		ElementServices.create(data);
     };
});
'use strict';


var libraryModels = angular.module('libraryModels', []);


libraryModels.factory('ContentModel', function(ContentServices, $rootScope, $sce) {
	function ContentModelFactory () {
		/////////////////////
		//	PRIVATE VAR
		/////////////////////
		
		var self = this;
		
		/////////////////////
		//	PUBLIC VAR
		/////////////////////
		
		this.foundContents = null;
		this.loading = false;
		this.isbn = null;
		
		/////////////////////
		//	PRIVATE METHODS
		/////////////////////
		
		/////////////////////
		//	PUBLIC METHODS
		/////////////////////	
		
		this.search = function () {
			this.loading = true;
			self.foundContents = [];
			ContentServices.search(self.isbn).success (function (res) {
				self.foundContents = res;
				self.loading = false;
			}).error (function () {
				self.loading = false;
			});
		};
	}
	
	return new ContentModelFactory ();
});

libraryModels.factory('ElementModel', function() {
	function ElementModelFactory () {
		/////////////////////
		//	PRIVATE VAR
		/////////////////////
		
		var self = this;
		
		/////////////////////
		//	PUBLIC VAR
		/////////////////////
		
		this.foundElement = null;
		
		/////////////////////
		//	PRIVATE METHODS
		/////////////////////
		
		/////////////////////
		//	PUBLIC METHODS
		/////////////////////	
	}
	
	return new ElementModelFactory ();
});

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

libraryModels.factory('ZoneModel', function(ZoneServices) {
	function ZoneModelFactory () {
		/////////////////////
		//	PRIVATE VAR
		/////////////////////
		
		var self = this;
		
		/////////////////////
		//	PUBLIC VAR
		/////////////////////
		
		this.zones = null;
		this.loading = false;
		
		/////////////////////
		//	PRIVATE METHODS
		/////////////////////
		
		/////////////////////
		//	PUBLIC METHODS
		/////////////////////	
		
		this.findAll = function () {
			this.loading = true;
			self.zones = [];
			ZoneServices.findAll().success (function (res) {
				self.zones = res;
				self.loading = false;
			}).error (function () {
				self.loading = false;
			});
		};
	}
	
	return new ZoneModelFactory ();
});

libraryModels.factory('ElementModel', function(ElementServices) {
	function ElementModelFactory () {
		/////////////////////
		//	PRIVATE VAR
		/////////////////////
		
		var self = this;
		
		/////////////////////
		//	PUBLIC VAR
		/////////////////////
		
		this.foundElement = null;
		this.elements = null;
		
		/////////////////////
		//	PRIVATE METHODS
		/////////////////////
		
		/////////////////////
		//	PUBLIC METHODS
		/////////////////////	

		this.find = function (query) {
			this.loading = true;
			self.elements = [];
			ElementServices.find(query).success (function (res) {
				self.zones = res;
				self.loading = false;
			}).error (function () {
				self.loading = false;
			});
		};
	}
	
	return new ElementModelFactory ();
});

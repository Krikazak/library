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
		this.loading = false;
		
		/////////////////////
		//	PUBLIC VAR
		/////////////////////
		
		this.zones = null;
		
		/////////////////////
		//	PRIVATE METHODS
		/////////////////////
		
		/////////////////////
		//	PUBLIC METHODS
		/////////////////////	

		this.find = function (query) {
			this.loading = true;
			self.zones = [];
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

libraryModels.factory('MenuModel', function(ZoneServices) {
	function MenuModelFactory () {
		/////////////////////
		//	PRIVATE VAR
		/////////////////////
		
		var self = this;
		
		/////////////////////
		//	PUBLIC VAR
		/////////////////////
		
		this.zones = null;
		
		/////////////////////
		//	PRIVATE METHODS
		/////////////////////
		
		/////////////////////
		//	PUBLIC METHODS
		/////////////////////	
		

		this.findZones = function () {
			this.loading = true;
			self.elements = [];
			ZoneServices.findByParent().success (function (res) {
				self.zones = res;
				self.loading = false;
			}).error (function () {
				self.loading = false;
			});
		};
		
		this.load = function (zone) {
			ZoneServices.load(zone).success (function (res) {
				var offset = 20;
				if (zone.offset != undefined && zone.offset != null) {
					offset = zone.offset + 20;
				} else {
					zone.offset = 0;
				}
				zone.contained = res.contained;
				for (var i = 0; i < zone.contained.length; i++) {
					zone.contained[i].offset = offset;
				}
				zone.loaded = true;
			}).error (function () {
				zone.loaded = false;
			});
		};
	}
	
	return new MenuModelFactory ();
});

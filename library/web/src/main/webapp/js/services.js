'use strict';

/* Services */

var libraryServices = angular.module('libraryServices', []);

libraryServices.service('LibraryServices', function($http, $q, SERVER_URL, MessageManager) {
	this.call = function (params, errorMessage, successMessage) {
		var canceler = $q.defer();
		var cancel = function () {
			canceler.resolve();
		};
		params.timeout = canceler.promise;
		if (params.url)
			params.url = SERVER_URL + params.url;
		var res = $http (params).error (function (data, status, headers, config) {
			if (errorMessage)
				MessageManager.showCode (errorMessage);
		}).success (function (res) {
			if (successMessage)
				MessageManager.showCode (successMessage);
		});
		res.cancel = cancel;
		return res;
	};
	
	this.transformFileInfo = function (fileInfo) {
		var tempFileName = "";
		var fileName = "";
		if (fileInfo) {
			tempFileName = fileInfo.tempFileName;
            if(fileInfo.fileName == null) {
                fileName = fileInfo.file.name;
            } else {
                fileName = fileInfo.fileName;
            }

		}
		return {tempFileName:tempFileName, fileName:fileName};
	};
});

libraryServices.service('ContentServices', function(LibraryServices) {
	this.search = function (isbn) {
		return LibraryServices.call ({
			url:"json/search/",
			method:"GET",
            params: {
            	isbn:isbn
            }
		}, "ERROR_CODE_SEARCH_CONTENT");
	};
	
});

libraryServices.service('ElementServices', function(LibraryServices) {
	this.create = function (data) {
		var element = {name:data.volumeInfo.title,data:data};
		return LibraryServices.call ({
			url:"json/element/",
			method:"POST",
            data:element
		}, "ERROR_CODE_CREATE_ELEMENT");
	};
	this.find = function (query) {
		return LibraryServices.call ({
			url:"json/element/",
			method:"GET",
            params: {
            	q:query,
            	include:""
            }
		}, "ERROR_CODE_CREATE_ELEMENT");
	};
	
});

libraryServices.service('ZoneServices', function(LibraryServices) {
	this.findAll = function () {
		return LibraryServices.call ({
			url:"json/zone/tree",
			method:"GET",
            params: {
            	include:"contained.contained"
            }
		}, "ERROR_CODE_ZONE_FINDALL");
	};
	
});

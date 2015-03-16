'use strict';

var commonfuncs = angular.module('commonfuncs', []);

function safeApply($scope, fn) {
  var phase = $scope.$root.$$phase;
  if(phase == '$apply' || phase == '$digest') {
	if(fn && (typeof(fn) === 'function')) {
	  fn();
	}
  } else {
	$scope.$apply(fn);
  }
};

function convertNulltoNull(input) {
    // Ignore things that aren't objects.
    if (typeof input !== "object") return input;

    for (var key in input) {
        if (!input.hasOwnProperty(key)) continue;

        var value = input[key];
        // Check for string properties which look like dates.
        if (typeof value === "string" && value == "null") {
            delete input[key];
        } else if (typeof value === "object") {
            // Recurse into object
        	convertNulltoNull(value);
        }
    }
}

commonfuncs.config(["$httpProvider", function ($httpProvider) {
    $httpProvider.defaults.transformResponse.push(function(responseData){
       convertNulltoNull(responseData);
       return responseData;
   });
}]);

commonfuncs.factory('Commonfunctions', function($translate, $rootScope, SERVER_URL) {
    function CommonFactory () {

        var self = this;

        this.updateListWithItem = function(list, item) {
            if(list == null) {
                list = [];
            }
            if(item.selected) {
                //Add item to the list
                list.push(item);
            } else {
                //Remove item from the list
                for(var i=0;i<list.length;i++) {
                    if(list[i].id == item.id) {
                        list.splice(i, 1);
                        break;
                    }
                }
            }
            return list;
        };

        this.unselectItemsFromTreeLevel = function(levelItem, destList) {
            if(levelItem.childrenTerritories) {
                for (var i = 0; i < levelItem.childrenTerritories.length; i++) {
                    //Uncheck element
                    levelItem.childrenTerritories[i].selected = false;
                    //Remove element from Dest List
                    for(var j= 0; j < destList.length; j++)
                    {
                        if(destList[j].id == levelItem.childrenTerritories[i].id) {
                            //Remove Child of the selected element
                            destList.splice(j,1);
                            break;
                        }
                    }
                    //Uncheck all children of the current level item
                    self.unselectItemsFromTreeLevel(levelItem.childrenTerritories[i], destList);
                }
            }
        }
        
    }
    return new CommonFactory();
});

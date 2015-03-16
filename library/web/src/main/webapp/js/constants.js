var constants = angular.module('constants', []);

constants.factory("Independent", [function() {
    return {
    	COSNT_EXEMPLE:'EXEMPLE'
    }
}]);

constants.factory('Constants', ["Independent", function(I) {
    var cst = I;
    cst.ANSWER_TO_LIFE = I.C1;
    return cst;
}]);
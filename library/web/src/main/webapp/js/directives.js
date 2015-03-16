"use strict";

/////
// Directives and filters
/////
var libraryDirectives = angular.module('libraryDirectives', []);

libraryDirectives.factory ("MessageManager", function ($timeout) {
function MessageManagerFactory () {
	var boxes = [];

	function queueRemoveEl (box, subEl) {
		$timeout(function () {
			box.removeMessage(subEl);
		}, 5000);
	}
	
	this.addBox = function(box) {
		boxes.push(box);
	};
	
	this.show = function (msg) {
        for (var i = 0; i < boxes.length; i++) {
            var box = boxes[i];
            var subElement = box.addMessage(msg);
            queueRemoveEl(box, subElement);
        }
    };

    this.showCode = function (msg) {
        for (var i = 0; i < boxes.length; i++) {
            var box = boxes[i];
            var subElement = box.addMessage(msg);
            queueRemoveEl(box, subElement);
        }
    };
}
return new MessageManagerFactory ();
});


libraryDirectives.directive("messageBox", function (MessageManager, $compile) {
	function boxContainer (el, scope, msgClass, onlyOne) {
		var subElement = null;
		this.addMessage = function (msg) {
			if (onlyOne && subElement)
				this.removeMessage(subElement);
			subElement = angular.element($compile("<div message msg-class='" + msgClass + "' msg=\"" + msg + "\"></div>")(scope));
			el.append(subElement);
			return subElement;
		};
		
		this.removeMessage = function (subElt) {
			subElt.remove ();
			if (subElt == subElement)
				subElement = null;
		};
	}
	return {
		link: function($scope, element, attrs) {
			MessageManager.addBox(new boxContainer(element, $scope, attrs.messageBox, attrs.onlyOne == "true"));
		}
	};
});

libraryDirectives.directive("message", function () {
	return {
		restrict:"A",
		template:"<p class='bulle-notif {{msgClass}}'>{{msg}}</div>",
		scope: {
			msg:"@",
			msgClass:"@"
		}
	};
});




var libraryI18n = angular.module('libraryI18n', [ "pascalprecht.translate" ]);

libraryI18n.config([ '$translateProvider', function($translateProvider) {

    var frProperties = {

        "PAGE_TITLE"		:	"Gestion de bibliothèque",
        "MAIN_LOADING"		:	"Chargement de l'application Library"

    };

    var enProperties = {
        "PAGE_TITLE"		:	"Library management",
        "MAIN_LOADING"		:	"The Library application is loading"

    };

    $translateProvider.translations('fr_FR', frProperties);
    $translateProvider.translations('fr', frProperties);

    $translateProvider.translations('en', enProperties);
    $translateProvider.translations('en_US', enProperties);
	
	$translateProvider.preferredLanguage("en");
	$translateProvider.determinePreferredLanguage();
} ]);
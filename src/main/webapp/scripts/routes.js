// We create the module and name it angularApp
var angularApp = angular.module("angularApp",["ngRoute", "ngTagsInput"]);

// configure our routes
angularApp.config(function($routeProvider, $locationProvider) {

    $routeProvider
        // route for the home page
        .when('/', {
            templateUrl : 'pages/home.html',
            controller : 'mainController'
        })

        // route for the about page
        .when('/about', {
            templateUrl : 'pages/about.html',
            controller : 'aboutController'
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller : 'contactController'
        })

        // route for the hacking page
        .when('/hacking', {
            templateUrl : 'pages/hacking.html',
            controller : 'hackingController'
        });

    //$locationProvider.html5Mode(true);
})





// We create the module and name it angularApp
var angularApp = angular.module("angularApp",["ngRoute"]);

// configure our routes
angularApp.config(function($routeProvider) {
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
        });
})



// Create a controller and inject the scope
angularApp.controller('mainController', function($scope) {
    // Create a message for a view to display
    $scope.message = "Wow this is awesome";
})

angularApp.controller('aboutController', function($scope) {
    // Create a message
    $scope.message = "This is an about message";
})

angularApp.controller('contactController', function($scope){
    // create a message
    $scope.message = "This is a contact message"
})
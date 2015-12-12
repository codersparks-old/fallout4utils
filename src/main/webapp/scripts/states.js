angularApp.config(['$stateProvider', '$urlRouterProvider','$locationProvider',
    function ($stateProvider, $urlRouterProvider, $locationProvider) {

        $locationProvider.html5Mode({enabled:true, requireBase: false});

        // On any unknown route we redirect to /
        $urlRouterProvider
            .otherwise('/');

        // Now we configure the states
        $stateProvider

            // Home State
            .state("home", {
                url: '/',
                templateUrl: 'pages/home.html',
                controller: 'mainController'
            })
            .state('hacking', {
                abstract: true,
                templateUrl: 'pages/hacking.html',
                controller: 'hackingController'
            })
            .state('hacking.entry', {
                url: '/hacking',
                templateUrl: 'pages/hacking/entry.html',
                controller: 'hackingController'
            })
            .state("about", {
                url: '/about',
                templateUrl: 'pages/about.html',
                controller: 'aboutController'
            });
    }]);

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
                controller: 'hackingController',
                data: {
                    createSessionEnabled: true,
                    bestGuessEnabled: false,
                    updateLikenessEnabled: false
                }
            })
            .state('hacking.bestGuess', {
                templateUrl: 'pages/hacking/bestGuess.html',
                controller: 'hackingController',
                data: {
                    createSessionEnabled: true,
                    bestGuessEnabled: true,
                    updateLikenessEnabled: true
                }
            })
            .state('hacking.updateLikeness', {
                templateUrl: 'pages/hacking/updateLikeness.html',
                controller: 'hackingController',
                data: {
                    createSessionEnabled: true,
                    bestGuessEnabled: true,
                    updateLikenessEnabled: true
                }
            })
            .state("about", {
                url: '/about',
                templateUrl: 'pages/about.html',
                controller: 'aboutController'
            });
    }]);

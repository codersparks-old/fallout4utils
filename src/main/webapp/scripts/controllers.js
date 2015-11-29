
// Create a controller and inject the scope
angularApp.controller('mainController', function($scope) {
    // Create a message for a view to display
    $scope.message = "Wow this is awesome";
})

angularApp.controller('hackingController', function($scope){
    $scope.session = {"id":"asadsdfsd-asafs-a-sasfdd"}
    $scope.sessionId = "sssaaa-aas-a-aafsdf";
})

angularApp.controller('aboutController', function($scope) {
    // Create a message
    $scope.message = "This is an about message";
})

angularApp.controller('contactController', function($scope){
    // create a message
    $scope.message = "This is a contact message"
})
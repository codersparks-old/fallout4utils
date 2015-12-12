
// Create a controller and inject the scope
angularApp.controller('mainController', function($scope) {
    // Create a message for a view to display
    $scope.message = "Wow this is awesome";

});

angularApp.controller('hackingController', function($scope, $http){
    $scope.Model = $scope.Model || {}
    $scope.Model.showSessionData = false;

    $scope.Model.newSession = function(possibleSolutions) {
        console.log("Model.newSession called")
        console.log("Possible Solutions: " + possibleSolutions);

        var possibleSolutionsText = [];

        for(i=0; i<possibleSolutions.length; i++) {
            possibleSolutionsText.push(possibleSolutions[i].text)
        }
        possibleSolutionsJson = JSON.stringify(possibleSolutionsText)
        console.log("Possible Solutions (text): " + possibleSolutionsJson)

        $http.post('/api/session/', possibleSolutionsJson)
            .success(function successFunction(data) {
                console.log("Success: " + data);
            }).error(function errorFunction(data) {
                console.log("Error: ")
                console.log(data);
            });

    }
});


angularApp.controller('aboutController', function($scope) {
    // Create a message
    $scope.message = "This is an about message";
});

angularApp.controller('contactController', function($scope){
    // create a message
    $scope.message = "This is a contact message"
});


var sessionKeyHandler = function(e) {

}
// Create a controller and inject the scope
angularApp.controller('mainController', function ($scope) {
    // Create a message for a view to display
    $scope.message = "Wow this is awesome";

});

angularApp.controller('hackingController', function ($rootScope, $scope, $state, $http) {

    $scope.Model = $scope.Model || {}
    $scope.Model.showSessionData = $scope.Model.showSessionData || false;
    $scope.Model.instructionsCollapse = $scope.Model.instructionsCollapse || true;
    $scope.Model.instIconClass = $scope.Model.instIconClass || "fa fa-chevron-right pull-right";
    $scope.Model.session = $scope.Model.session || null;
    $scope.Model.alerts = $scope.Model.alerts || [];
    $scope.Model.NavBtns = $scope.Model.NavBtns || {};
    $scope.Model.likeness = "";
    $scope.Model.createSession = $state.current.data.createSessionEnabled;
    $scope.Model.calculateBestGuess = $state.current.data.bestGuessEnabled;
    $scope.Model.updateLikeness = $state.current.data.updateLikenessEnabled;
    console.log("Best Guess: " + $scope.Model.calculateBestGuess);
    var currentState = $state.current.name;


    switch (currentState) {
        case "hacking.entry":
            console.log("Current State: " + $state.current.name);
            $scope.Model.bestGuessClass = "";
            $scope.Model.updateLikenessClass = "";
            // Set the show status for nav buttons
            $scope.Model.bestGuess = ""

            break;
        case "hacking.bestGuess":
            $scope.Model.createSessionClass = "";
            $scope.Model.bestGuessClass = "active";
            $scope.Model.updateLikenessClass = "";
            $scope.Model.bestGuess = ""
            break;
        case "hacking.updateLikeness":
            $scope.Model.createSessionClass = "";
            $scope.Model.bestGuessClass = "";
            $scope.Model.updateLikenessClass = "active";

            if ($scope.Model.bestGuess.length != 1) {
                $scope.Model.bestGuess = ""
            }
            break;
    }


    $scope.Model.collapseInstructions = function () {
        $scope.Model.instructionsCollapse = !$scope.Model.instructionsCollapse;

        if ($scope.Model.instructionsCollapse) {
            $scope.Model.instIconClass = "fa fa-chevron-right pull-right";
        } else {
            $scope.Model.instIconClass = "fa fa-chevron-down pull-right";
        }
    }

    $scope.Model.newSession = function (possibleSolutions) {
        console.log("Model.newSession called");
        console.log("Possible Solutions: " + possibleSolutions);

        var possibleSolutionsText = [];

        for (i = 0; i < possibleSolutions.length; i++) {
            possibleSolutionsText.push(possibleSolutions[i].text)
        }
        var possibleSolutionsJson = JSON.stringify(possibleSolutionsText);
        console.log("Possible Solutions (text): " + possibleSolutionsJson);

        $http.post('/api/session/', possibleSolutionsJson)
            .success(function successFunction(data) {
                console.log("Success");
                console.log(data);
                $scope.Model.addAlert("success", "Successfully created session");
                $scope.Model.session = data;
                $scope.Model.updateState('hacking.bestGuess')

            }).error(function errorFunction(data) {
            console.log("Error: ");
            console.log(data);
            $scope.Model.addAlert("error", "Error encountered when creating session, see console log for details");
        });

    }

    $scope.Model.generateBestGuess = function (session) {
        console.log("Attempting to get best guess for session: " + session.id);

        $http.get('/api/session/calculateBestGuess/' + session.id)
            .success(function succssFunction(data) {
                console.log("Success");
                console.log(data);
                $scope.Model.addAlert("success", "Calculated Best Guess successfully");
                $scope.Model.bestGuess = data;
            }).error(function errorFunction(data) {
            console.log("Error: ");
            console.log(data);
            $scope.Model.addAlert("error", "Error encountered when creating session, see console log for details");
        })

    }

    $scope.Model.updateLikeness = function (session, bestGuess, likeness) {
        console.log("Updating likeness for " + bestGuess + " to " + likeness);
        console.log("Session: ")
        console.log(session);

        $http.post('/api/session/updateLikeness/' + session.id + "/" + bestGuess + "/" + likeness, "")
            .success(function successFunction(data) {
                console.log("Success");
                console.log(data);
                $scope.Model.session = data;
                $scope.Model.addAlert("success", "Successfully update likeness for " + bestGuess + " to " + likeness);
                $scope.Model.updateState('hacking.bestGuess')
            }).error(function errorFunction(data) {
            console.log("Error: ");
            console.log(data);
            $scope.Model.addAlert("error", "Error encountered when creating session, see console log for details");
        });
    }


// Alert functions
    $scope.Model.addAlert = function (alertType, alertMessage) {
        $scope.Model.alerts.push({type: alertType, msg: alertMessage});
    }

    $scope.Model.closeAlert = function (index) {
        $scope.Model.alerts.splice(index, 1);
    }

    // Change state
    $scope.Model.updateState = function (toState) {

        console.log("Going to state: " + toState);
        $state.go(toState);
    }
})
;


angularApp.controller('aboutController', function ($scope) {
    // Create a message
    $scope.message = "This is an about message";
});

angularApp.controller('contactController', function ($scope) {
    // create a message
    $scope.message = "This is a contact message";
});

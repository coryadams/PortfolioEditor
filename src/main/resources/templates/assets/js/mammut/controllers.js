app.controller("MainController", function($scope){
    $scope.understand = "I now understand how the scope works!";
});

app.controller("HelloController", function($scope, $http) {
    $http.get('http://rest-service.guides.spring.io/greeting').
        success(function(data) {
            $scope.greeting = data;
        });
});
app.controller("NamespaceController", function($scope, $http) {
    $http.get('http://localhost:8888/namespaces').
        success(function(data) {
            $scope.namespaces = data;
            console.log($scope.namespaces);
        }).
    error(function(data, status, headers, config) {
        console.log(data);
    });
});
app.controller("QueueController", function($scope, $http) {
    $http.get('http://localhost:8888/queues').
        success(function(data) {
            $scope.queues = data;
        }).
        error(function(data, status, headers, config) {
            console.log(data);
        });
});
app.controller("RelayController", function($scope, $http) {
    $http.get('http://localhost:8888/relays').
        success(function(data) {
            $scope.relays = data;
        }).
        error(function(data, status, headers, config) {
            console.log(data);
        });
});
app.controller("SubscriptionController", function($scope, $http) {
    $http.get('http://localhost:8888/subscriptions').
        success(function(data) {
            $scope.subscriptions = data;
        }).
        error(function(data, status, headers, config) {
            console.log(data);
        });
});
app.controller("TopicController", function($scope, $http) {
    $http.get('http://localhost:8888/topics').
        success(function(data) {
            $scope.topics = data;
        }).
        error(function(data, status, headers, config) {
            console.log(data);
        });
});
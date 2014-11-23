var app = angular.module('myApp', ['ngRoute']); //ime aplikacije
app.factory("services", ['$http', function($http) {
 var serviceBase = '../rest/v1/'
 var obj = {};
 
 //dodjeljivanje headera-> privremeno statièan
 var config = {headers: {
            'Authorization': '098f6bcd4621d373cade4e832627b4f6',
            'Accept': 'application/json;odata=verbose'
        }
    };

 
  //services---------------------------------------------------------------------
 
 //get firstname and lastname based on email
 /*
 obj.getUser = function(email){ // get customer based on id
 return $http.get(serviceBase + '/userdata/firstlast/?email=' + customerID, config); //service base je poèetni route
 }*/
 
 //get all institutions
 obj.getInstitutions = function(){ // get all stations
 return $http.get(serviceBase + 'stations', config);
 }
 
 //controllers for services-> kontroleri služi samo za parcijalne stranice(liste, tablice), vežu se dole na route providere
 //fetch institutions to list
 //get all institutions into list
 app.controller('institutionListCtrl', function ($scope, services) { //kontroler za ispis na listu, koristi services iz factorija
 services.getInstitutions().then(function(data){ // preko scopa vraæa poodatke kojima se pristupa u listu, koristi se getCustomer() REST za povlaæenje svih customersa
 $scope.stations = data.data; //vrati scope... ovaj .customers æe se koristit kasnije u ng-repeat
 });
});
 
 return obj;
}]);

//application routing------------------------

app.config(['$routeProvider',
 function($routeProvider) {
 $routeProvider.
 when('/', { //kada je home (/) odi na customers
 title: 'Institucije',
 templateUrl: 'index.html',
 controller: 'institutionListCtrl'
 })
 //ovo je za edit-> ime rerouting
 /*
 .when('/edit-customer/:customerID', { //kada je ovaj url, odi na rest api i dohvati customera s tim idijem
 title: 'Edit Customers',
 templateUrl: 'partials/edit-customer.html', //na ovoj stranici
 controller: 'editCtrl',
 resolve: {
 customer: function(services, $route){
 var customerID = $route.current.params.customerID; //customer id koji ce se koristiti gore
 return services.getCustomer(customerID); //vraæanje customer id ovisno o njegovom id-ju.
 }
 }
 })*/
 .otherwise({ //ako nema adrese tu gore, odi na home /
 redirectTo: '/'
 });
}]);
app.run(['$location', '$rootScope', function($location, $rootScope) {
 $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
 $rootScope.title = current.$$route.title;
 });
}]);


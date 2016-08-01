
var url="http://192.168.1.2/curisLoc/";
angular.module('starter', ['ionic','ngCordova','ionic-material', 'firebase'])

.run(function($ionicPlatform, GoogleMaps) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }

    GoogleMaps.init();
  })
})

.factory('Auth', function ($firebaseAuth) {
  var endPoint="https://curis.firebaseio.com/users";
  var userRef = new Firebase(endPoint);
  return $firebaseAuth(userRef);

})

  .factory('temanService', function($http) {

    return {

      getAll: function() {
        return $http.get(url+'reservations.php');
      },


      delete: function  (id){
        return $http.get(url+'delete.php?id='+id);
      }
    };

  })
  .factory('MedService', function($http) {

    return {

      getAll: function() {
        return $http.get(url+'medecins.php');
      }
    };
  })

.factory('Markers', function($http) {

  var markers = [];

  return {
    getMarkers: function(){

      return $http.get("http://192.168.1.2/curisLoc/markers.php").then(function(response){
        markers = response;
        return markers;
      });

    }
  }
})

.factory('GoogleMaps', function($cordovaGeolocation, Markers){

  var apiKey = false;
  var map = null;

  function initMap(){


    var options = {timeout: 10000, enableHighAccuracy: true};

    $cordovaGeolocation.getCurrentPosition(options).then(function(position){

      var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

      var mapOptions = {
        center: latLng,
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      };

      map = new google.maps.Map(document.getElementById("map"), mapOptions);
      marker=new google.maps.Marker({
        position:latLng,
        map:map,
        title:"je suis ici",
        icon:'img/markmoi.png'
      });
      //Wait until the map is loaded
      google.maps.event.addListenerOnce(map, 'idle', function(){

        //Load the markers
        loadMarkers();

      });

    }, function(error){
      console.log("Could not get location");

      //Load the markers
      loadMarkers();
    });

  }

  function loadMarkers(){

    //Get all of the markers from our Markers factory
    Markers.getMarkers().then(function(markers){

      console.log("Markers: ", markers);

      var records = markers.data;

      for (var i = 0; i < records.length; i++) {

        var record = records[i];
        var markerPos = new google.maps.LatLng(record.lat, record.lng);

        // Add the markerto the map
        var marker = new google.maps.Marker({
          map: map,
         animation: google.maps.Animation.DROP,
          position: markerPos
        });

        var infoWindowContent = "<h4>" + record.nom + "</h4>";

        addInfoWindow(marker, infoWindowContent, record);

      }

    });

  }

  function addInfoWindow(marker, message, record) {

    var infoWindow = new google.maps.InfoWindow({
      content: message
    });

    google.maps.event.addListener(marker, 'click', function () {
      infoWindow.open(map, marker);
    });

  }

  return {
    init: function(){
      initMap();
    }
  }

})



.config(function ($stateProvider, $urlRouterProvider) {

  $stateProvider.state("login",{
    url:"/login",
    templateUrl:"templates/login.html",
    controller:"loginCtrl"
  });
  $stateProvider.state("start",{
    url:"/start",
    templateUrl:"templates/start.html"

  });
  $stateProvider.state("Home",{
    url:"/Home",
    templateUrl:"templates/Home.html",
    controller:"HomeCtrl"
  });

  $stateProvider.state("medecins",{
    url:"/medecins",
    templateUrl:"templates/medecins.html",
    controller:"medecinsCtrl"
  });
  $stateProvider.state("geo",{
    url:"/geo",
    templateUrl:"templates/Geolocalisation.html",
    controller:"GeoCtrl"
  });

  $stateProvider.state("cabinets",{
    url:"/cabinets/:idmed",
    templateUrl:"templates/cabinets.html",
    controller:"cabinetsCtrl"
  });

  $stateProvider.state("reservation",{
    url:"/reservation/",
    templateUrl:"templates/reservation.html",
    controller:"reservationCtrl"
  });


  $urlRouterProvider.otherwise("start");

})

  .controller("loginCtrl",function ($scope, Auth,$state, $cordovaGeolocation) {

    Auth.$onAuth(function (authData) {
      if(authData===null){
        console.log("user pas authentifier");
      }else{
        console.log("user authentifier");
        console.log(authData);
      }
      $scope.authData= authData;



    });
    $scope.loghome= function(){

      $state.go('Home');
    };
    $scope.login = function (authMethod) {
      Auth.$authWithOAuthRedirect(authMethod).then(function (authData) {

      }).catch(function (error) {
        if(error.code=== 'TRANSPORT_UNAVAILABLE'){
          Auth.$authWithOAuthPopup(authMethod).then(function (authData) {

          });
        }else {
          console.log(error);
        }

      })

    };
  })
//controlleur donnée static
.controller("HomeCtrl",function ($http,$scope,$state, Auth) {

  $scope.home={
    nomApplication:"CurisLoc",
    Realisateur:"jihan ferfess"
  };
$scope.logout= function () {

  Auth.$unauth();
  $state.go("login");
};
  $scope.godoc= function(){

    $state.go('medecins');
  };
  $scope.gomap= function(){

    $state.go('geo');
  };

})

.controller("medecinsCtrl",function ($http,$scope,$state,$ionicLoading, ionicMaterialMotion,$ionicPopup, MedService) {

  $scope.medecins=[];
  $scope.url=url;
  $ionicLoading.show({

    template: '<div class="loader"><svg class="circular"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/></svg></div>'

  });



  $http.get(url+"medecins.php")
    .success(function (data) {
      $scope.medecins=data;
      $ionicLoading.hide();

    })
    .error(function (err) {
      console.log(err);
      $ionicLoading.hide();
    });
  $scope.chargerFormation= function (idmed) {
  // alert(nom );
   // alert(idmed);
    $state.go("cabinets",{
      idmed:idmed,


    });
  }
  $scope.showData = function() {
    MedService.getAll().success(function(data) {
      $scope.reservations = data;
    }).finally(function() {
      $scope.$broadcast('scroll.refreshComplete');
    });
  };


})
.controller("cabinetsCtrl",function ($http,$scope,$state,$stateParams, $cordovaDatePicker, $ionicPlatform) {
  $scope.url=url;
  $scope.idmed=$stateParams.idmed;
 // $scope.nom=$stateParams.nom;
  var now = new Date();
  $scope.date = now;
  $ionicPlatform.ready(function(){
    $scope.showDatePicker = function(){
      var options = {
        date: $scope.date,
        mode: 'datetime', // or 'time'
        minDate: new Date() - 10000,
        allowOldDates: false,
        allowFutureDates: true,
        doneButtonLabel: 'TERMINER',
        doneButtonColor: '#F2F3F4',
        cancelButtonLabel: 'CANCEL',
        cancelButtonColor: '#000000'
      };

      $cordovaDatePicker.show(options).then(function(date){
        alert(date);
        $scope.cabinetsCtrl.code =date;
        //$scope.cabinetsCtrl.code =date;
      });
    };

  });

  $scope.formations=[];
  $http.get(url+"reservations.php?idmed="+$scope.idmed)
    .success(function (data) {
      $scope.formations=data;

    })
    .error(function (err) {
      console.log(err);

    });
  $scope.addNewInvoice = function (add) {
    $state.go('reservation');

    var data = {
      "nom":$scope.cabinetsCtrl.nom,
      "idmed":$scope.idmed,
      "dt":$scope.cabinetsCtrl.code,
      "tp":$scope.cabinetsCtrl.code2,


    }
    console.log("inserted Successfully", data);
    $http.post(url+"add.php", { 'nom': $scope.cabinetsCtrl.nom,'idmed':$scope.idmed,'dt': $scope.cabinetsCtrl.code,'tp': $scope.cabinetsCtrl.code})
      .success(function (data, status, headers, config) {
        console.log($scope.cabinetsCtrl.nom);
        console.log($scope.cabinetsCtrl.code);
        console.log($scope.cabinetsCtrl.code2);

      });

  };

})
  .controller("GeoCtrl", function($scope, $state, $cordovaGeolocation,GoogleMaps) {
    GoogleMaps.init();
})

  .controller("reservationCtrl", function($http,$scope, $state,$ionicLoading,$stateParams,$ionicPopup,temanService) {
    $scope.reservations=[];
    $scope.url=url;
    $scope.idr=$stateParams.idr;
    $scope.idlog=$stateParams.idLogged
    $ionicLoading.show({

      template: '<div class="loader"><svg class="circular"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/></svg></div>'

    });

    $scope.data = {
      showDelete: false
    };

    $http.get(url+"reservations.php")
      .success(function (data) {
        $scope.reservations=data;
        $ionicLoading.hide();

      })
      .error(function (err) {
        console.log(err);
        $ionicLoading.hide();
      });
   $scope.chargerFormation= function (idr) {
      // alert(nom );


    }
    /*
    $scope.delete=function (idr) {
      $http.get(url+"delete.php?idr="+$scope.idr)
        .success(function (data) {
          console.log('mezian');

        })
        .error(function (err) {
          console.log(err);

        });

    }*/
    $scope.showAlert = function(msg) {
      $ionicPopup.alert({
        title: msg.title,
        template: msg.message,
        okText: 'Ok',
        okType: 'button-positive'
      });
    };
    $scope.delete = function (r){
      temanService.delete(r.id);
      console.log("deleted");
      $scope.reservations.splice($scope.reservations.indexOf(r),1);
    };

    $scope.showData = function() {
      temanService.getAll().success(function(data) {
        $scope.reservations = data;
      }).finally(function() {
        $scope.$broadcast('scroll.refreshComplete');
      });
    };
  })

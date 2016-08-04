/*
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
(function(){
  function GeoValidationProblemController($scope, datasetService) {
    var controller = this;
    $scope.problem.synonym = null;
    
    controller.getGeoEntitySuggestions = function( request, response ) {
      var limit = 20;
      
      var connection = {
        onSuccess : function(query){
          var resultSet = query.getResultSet()
            
          var results = [];
            
          $.each(resultSet, function( index, result ) {
            var label = result.getValue('displayLabel');
            var id = result.getValue('id');
              
            results.push({'label':label, 'value':label, 'id':id});
          });
            
          response( results );
        },
        onFailure : function(e){
          console.log(e);
        }
      };
      
      var text = request.term;
        
      datasetService.getGeoEntitySuggestions(connection, $scope.problem.parentId, $scope.problem.universalId, text, limit);
    }
    
    controller.setSynonym = function(value) {
      $scope.problem.synonym = value;
      
      controller.problemForm.$setValidity("synonym-length",  ($scope.problem.synonym != null));      
    }
    
    controller.createSynonym = function() {
      var connection = {
        elementId : '#uploader-overlay',
        onSuccess : function(){
          $scope.problem.resolved = true;
         
          $scope.$apply();
        },
        onFailure : function(e){
         $scope.errors = [];
         $scope.errors.push(e.localizedMessage);
         
         $scope.$apply();
        }        
      };
      
      $scope.errors = undefined;
      
      datasetService.createGeoEntitySynonym(connection, $scope.problem.synonym, $scope.problem.label);
    }
    
    controller.createEntity = function() {
      var connection = {
        elementId : '#uploader-overlay',
        onSuccess : function(){
          $scope.problem.resolved = true;
      
          $scope.$apply();        
        },
        onFailure : function(e){
          $scope.errors = [];
          $scope.errors.push(e.localizedMessage);
      
          $scope.$apply();
        }      
      };
        
      $scope.errors = undefined;
      
      datasetService.createGeoEntity(connection, $scope.problem.parentId, $scope.problem.universalId, $scope.problem.label);
    }
    
    controller.ignoreDataAtLocation = function() {
    	var locationLabel = $scope.problem.label;
    	var universal = $scope.problem.universalId;
    	
    	$scope.problem.resolved = true;
    	
    	datasetService.addLocationExclusion({"universal":universal, "locationLabel":locationLabel});
    }
  }
  
  function GeoValidationProblem($timeout) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/geo-validation-problem.jsp',
      scope: {
        problem : '=' 
      },
      controller : GeoValidationProblemController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs, ctrl) {
        $timeout(function(){
          ctrl.problemForm.$setValidity("synonym-length",  false);        
        }, 0);
      }
    }   
  }
  
  function GeoValidationPageController($scope, datasetService) {
    var controller = this;
    
    controller.hasProblems = function() {
      for(var i = 0; i < $scope.problems.length; i++) {
        if(!$scope.problems[i].resolved) {
          return true;
        }
      }
      
      return false;
    }
    
    $scope.$watch('problems', function(){
      $scope.form.$setValidity("size",  (!controller.hasProblems()));
    }, true);

    // Remove the global validation on the form
    $scope.$on('pagePrev', function(event, data){
      $scope.form.$setValidity("size",  true);
    });       
  }
  
  function GeoValidationPage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/geo-validation-page.jsp',
      scope: true,
      controller : GeoValidationPageController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs) {
      }
    }   
  }
  
  function MatchPageController($scope, datasetService) {
    var controller = this;
    
    controller.select = function(match, replace) {
      var connection = {
        elementId : '#uploader-div',
        onSuccess : function(result) {
          var sheet = result.datasets;
          sheet.replaceExisting = replace;
      
          $scope.$emit('loadConfiguration', {sheet: sheet});
      
          $scope.$apply();
        }    		  
      };
      
      datasetService.getSavedConfiguration(connection, match.id, $scope.sheet.name);      
    }
  }
  
  function MatchPage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/match-page.jsp',
      scope: true,
      controller : MatchPageController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs) {
      }
    }   
  }
  
  
  function BeginningInfoPageController($scope) {
    var controller = this;
    
  }
  
  
  function BeginningInfoPage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/beginning-info-page.jsp',
      scope: true,
      controller : BeginningInfoPageController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs) {
      }
    }   
  }
  
  function NamePage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/name-page.jsp',
      scope: true,
      link: function (scope, element, attrs) {
      }
    }   
  }  
  
  function AttributesPageController($scope) {
    var controller = this;
    
    controller.initialize = function() {
      // Initialize the universal options
      if($scope.options != null) {
        var countries = $scope.options.countries;
              
        for(var i = 0; i < countries.length; i++) {
          var country = countries[i];
                 
          if(country.value == $scope.sheet.country) {
            $scope.universals = country.options;
          }
        }
      }
      
      $scope.hasLocation = false;
      
      $scope.longitudeFields = {};
      $scope.latitudeFields = {};      
    }    
  
    controller.isUniqueLabel = function(label) {
      if($scope.sheet != null) {
        var count = 0;
          
        for(var i = 0; i < $scope.sheet.fields.length; i++) {
          var field = $scope.sheet.fields[i];
            
          if(field.label == label) {
            count++;
          }            
        }
          
        if(count > 1) {
          return false;
        }
      }  
        
      return true;
    }
    
    controller.accept = function(field) {
      field.accepted = true;
      
      if(field.type === "LOCATION" || field.type === 'COORDINATE'){
        controller.setLocationSelected(field.type);
      }
      else{
        controller.setLocationSelected([]);
      }
      
      if(field.type === "LATITUDE") {
        $scope.latitudeFields[field.name] = field;
      }
      else {
        delete $scope.latitudeFields[field.name];      
      }
      
      if(field.type === "LONGITUDE") {
        $scope.longitudeFields[field.name] = field;      
      }
      else {
        delete $scope.longitudeFields[field.name];
      }
      
      var matched = (Object.keys($scope.latitudeFields).length == Object.keys($scope.longitudeFields).length);
      $scope.form.$setValidity("coordinate", matched);
    }
    
    controller.setLocationSelected = function(locationType) {
      var locationTypeArr = [];
      
      if(locationType && locationType.length > 0){
        locationTypeArr.push(locationType);
        
        for(var i=0; i<$scope.sheet.fields.length; i++){
          var field = $scope.sheet.fields[i];
          
          if(((field.type === "LOCATION" || field.type === "LONGITUDE" || field.type === "LATITUDE") && field.type !== locationType) && locationTypeArr.indexOf(field.type) === -1 ){
            locationTypeArr.push(field.type);
          }
        }
      }
      else{
        for(var i=0; i<$scope.sheet.fields.length; i++){
          var field = $scope.sheet.fields[i];
          
          if((field.type === "LOCATION" || field.type === "LONGITUDE" || field.type === "LATITUDE") && locationTypeArr.indexOf(field.type) === -1){
            locationTypeArr.push(field.type);
          }
        }
      }
      
      $scope.$emit('hasLocationEvent', locationTypeArr);
    }
    
    controller.initialize();
    
  }
  
  function AttributesPage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/attributes-page.jsp',
      scope: true,
      controller : AttributesPageController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs) {
      }
    }   
  }
  
  function LocationPageController($scope, runwayService) {
    var controller = this;
    
    controller.initialize = function() {
      // Initialize the universal options
      var countries = $scope.options.countries;
            
      for(var i = 0; i < countries.length; i++) {
        var country = countries[i];
               
        if(country.value == $scope.sheet.country) {
          $scope.universals = country.options;
        }
      }
        
      // Create a map of possible location fields
      $scope.locationFields = {};
        
      for(var j = 0; j < $scope.sheet.fields.length; j++) {
        var field = $scope.sheet.fields[j];
            
        if(field.type == 'LOCATION') {
          if($scope.locationFields[field.universal]  == null) {
            $scope.locationFields[field.universal] = [];
          }
            
          $scope.locationFields[field.universal].push(field);          
        }
      }
      
      controller.newAttribute();
    }
    
    controller.getNextLocationField = function() {
      for(var i = ($scope.universals.length - 1); i >= 0; i--) {
        var universal = $scope.universals[i];
        
        if($scope.locationFields[universal.value] != null) {
          var fields = $scope.locationFields[universal.value];
          
          for(var j = 0; j < fields.length; j++) {
            var field = fields[j];
            
            if(!field.assigned) {              
              return {field:field, universal:universal};
            }
          }
        }        
      }  
      
      return null;
    }
    
    controller.edit = function(attribute) {
      $scope.attribute = angular.copy(attribute);
            
      var fieldLabel = $scope.attribute.fields[attribute.universal];      
      var field = controller.getField(fieldLabel);

      controller.setUniversalOptions(field);      
    }
    
    controller.getField = function(label) {
      for(var j = 0; j < $scope.sheet.fields.length; j++) {
        var field = $scope.sheet.fields[j];
                
        if(field.label === label) {
          return field;
        }      
      }
      
      return null;
    }
    
    controller.refreshUnassignedFields = function(selectedFields) {
      /*
       * Reset the unassigned field array
       */
      $scope.unassignedFields = [];
      
      if($scope.attribute != null) {
        for(var i = 0; i < $scope.sheet.fields.length; i++) {     
          var field = $scope.sheet.fields[i]
              
          if(field.type == 'LOCATION' && !field.assigned && field.name != $scope.attribute.name && selectedFields.indexOf(field.name) === -1 ) {
            $scope.unassignedFields.push(field);
          }
        }                      	  
      }
    }
      
    controller.remove = function(attribute) {
      if($scope.sheet.attributes.values[attribute.id] != null) {
              
        delete $scope.sheet.attributes.values[attribute.id];        
        $scope.sheet.attributes.ids.splice( $.inArray(attribute.id, $scope.sheet.attributes.ids), 1 );
        
        // Update the field.assigned status
        controller.setFieldAssigned();
        
        if($scope.attribute == null) {
          controller.newAttribute();
        } 
        else {
          controller.refreshUnassignedFields([]);
        }
      }
    }
    
    controller.newAttribute = function() {
    	
      if($scope.attribute != null) {      
        if($scope.attribute.id == -1) {
          $scope.attribute.id = runwayService.generateId();
          $scope.sheet.attributes.ids.push($scope.attribute.id);
          $scope.sheet.attributes.values[$scope.attribute.id] = {};              
        }     
        
        var attribute = $scope.sheet.attributes.values[$scope.attribute.id];      
        angular.copy($scope.attribute, attribute);              
        
        // Update the field.assigned status
        controller.setFieldAssigned();
      }
      
      var location = controller.getNextLocationField();      
      
      if(location != null) {
        var field = location.field;
        var universal = location.universal;
        
        $scope.attribute = {
          label : field.label,
          name : field.name,
          universal : universal.value,
          fields : {},
          id : -1
        };

        controller.addField(field);
      
        controller.setUniversalOptions(field);        
      }
      else {
        $scope.attribute = null;
      }
      
      controller.refreshUnassignedFields([]);
    }
    
    controller.addField = function(field) {
      $scope.attribute.fields[field.universal] = field.label;
    }
    
    controller.setUniversalOptions = function(field) {
      $scope.universalOptions = [];
      var valid = true;
      
      for(var i = 0; i < $scope.universals.length; i++) {
        var universal = $scope.universals[i];
              
        if(universal.value == field.universal) {
          valid = false;              
        }
        else if(valid && $scope.locationFields[universal.value] != null) {
          $scope.universalOptions.push(universal);
        }
      }
    }
    
    controller.setFieldAssigned = function() {
      for(var i = 0; i < $scope.sheet.fields.length; i++) {     
        var field = $scope.sheet.fields[i]
      
        if(field.type == 'LOCATION') {
          field.assigned = controller.isAssigned(field);          
        }
        else {
          field.assigned = false;                
        }
      }          
    }
    
    
    controller.isAssigned = function(field) {
      for(var i = 0; i < $scope.sheet.attributes.ids.length; i++) {
        var id = $scope.sheet.attributes.ids[i];
        var attribute = $scope.sheet.attributes.values[id];
                  
        for (var key in attribute.fields) {
          if (attribute.fields.hasOwnProperty(key)) {
            if(attribute.fields[key] == field.label) {
              return true;
            }
          }
        }
      }
      
      return false;
    }
    
    controller.isUniqueLabel = function(label) {
      if($scope.sheet != null) {
        var count = 0;
             
        for(var i = 0; i < $scope.sheet.fields.length; i++) {
          var field = $scope.sheet.fields[i];
                
          if(field.type != 'LOCATION' && field.label == label) {
            count++;
          }            
        }
          
        for(var i = 0; i < $scope.sheet.attributes.ids.length; i++) {
          var id = $scope.sheet.attributes.ids[i];
          var attribute = $scope.sheet.attributes.values[id];          
            
          if(attribute.label == label) {
            count++;
          }            
        }
              
        if(count > 0) {
          return false;
        }
      }  
            
      return true;
    }
      
    $scope.$watch('attribute', function(){
      $scope.form.$setValidity("size",  ($scope.attribute == null));
    }, true);   
    

    // Remove the global validation on the form
    $scope.$on('pagePrev', function(event, data){
      $scope.form.$setValidity("size",  true);
    });   
    
    controller.change = function(selectedFields){
    	var selectedFieldsArr = [];
    	for (var key in selectedFields) {
    		  if (selectedFields.hasOwnProperty(key)) {
    		    selectedFieldsArr.push(selectedFields[key]);
    		  }
    		}
    	controller.refreshUnassignedFields(selectedFieldsArr);
    }
    
    // Initialize the scope
    controller.initialize();
  }
  
  function LocationPage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/location-page.jsp',
      scope: true,
      controller : LocationPageController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs) {
      }
    }   
  }
  
  function CoordinatePageController($scope) {
    var controller = this;
    
    controller.initialize = function() {        
      $scope.longitudes = [];
      $scope.featureLabels = [];
      $scope.locations = [];
      $scope.featureIds = [];
      
      var countries = $scope.options.countries;
      
      for(var i = 0; i < countries.length; i++) {
        var country = countries[i];
               
        if(country.value == $scope.sheet.country) {
          $scope.universals = country.options;
          $scope.labels = {};
          
          for(var j = 0; j < country.options.length; j++) {
            var universal = country.options[j];
            
            $scope.labels[universal.value] = universal.label;
          }
        }
      }      
      
      for(var i = 0; i < $scope.sheet.fields.length; i++) {
        var field = $scope.sheet.fields[i];
        
        if(field.type == 'LATITUDE') {
        	
          if(!controller.hasCoordinateField(field)) {
            var coordinate = {
              label : "",
              latitude : field.label,
              longitude : "",
              featureLabel : "",
              location : "",
              featureId : "",
              id : field.label
            };
            
            $scope.sheet.coordinates.ids.push(coordinate.id);
            $scope.sheet.coordinates.values[coordinate.id] = coordinate;                          
          }
        }
        else if(field.type == 'LONGITUDE') {
          $scope.longitudes.push(field);          
        }
        else if(field.type == 'TEXT') {
          $scope.featureLabels.push(field);          
        }
        else if(controller.isBasic(field)) {
          $scope.featureIds.push(field);          
        }
      }
      
      /*
       * If there is only 1 longitude field then set that value
       * automatically and don't give the user a drop-down that
       * they need to select from
       */
      if($scope.longitudes.length == 1) {
        for(var i = 0; i < $scope.sheet.coordinates.ids.length; i++) {
          var id = $scope.sheet.coordinates.ids[i];
          var coordinate = $scope.sheet.coordinates.values[id];          
          
          coordinate.longitude = $scope.longitudes[0].label;
        }
      }
      
      if($scope.sheet.attributes != null) {
        for(var i = 0; i < $scope.sheet.attributes.ids.length; i++) {
          var id = $scope.sheet.attributes.ids[i];
          var attribute = $scope.sheet.attributes.values[id];          
        
          $scope.locations.push({
            label : attribute.label,
            universal : attribute.universal
          });
        }
      }
    }
    
    controller.hasCoordinateField = function(field) {
      for(var i = 0; i < $scope.sheet.coordinates.ids.length; i++) {
        var id = $scope.sheet.coordinates.ids[i];
        
        if(id === field.label) {
          return true;
        }
      }
      
      return false;
    }
    
    controller.isBasic = function(field) {
      return (field.type == 'TEXT' || field.type == 'LONG' || field.type == 'DOUBLE');  
    }
        
    controller.isUniqueLabel = function(label) {
      if($scope.sheet != null) {
        var count = 0;
        
        for(var i = 0; i < $scope.sheet.fields.length; i++) {
          var field = $scope.sheet.fields[i];
          
          if(field.label == label) {
            count++;
          }            
        }
        
        if($scope.sheet.attributes != null) {
          for(var i = 0; i < $scope.sheet.attributes.ids.length; i++) {
            var id = $scope.sheet.attributes.ids[i];
            var attribute = $scope.sheet.attributes.values[id];          
            
            if(attribute.label == label) {
              count++;
            }            
          }          
        }
        
        for(var i = 0; i < $scope.sheet.coordinates.ids.length; i++) {
          var id = $scope.sheet.coordinates.ids[i];
          var coordinate = $scope.sheet.coordinates.values[id];          
          
          if(coordinate.label == label) {
            count++;
          }            
        }
        
        if(count > 1) {
          return false;
        }
      }  
      
      return true;
    }
    
    // Initialize the scope
    controller.initialize();
  }
  
  function CoordinatePage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/coordinate-page.jsp',
      scope: true,
      controller : CoordinatePageController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs) {
      }
    }   
  }
  
  function SummaryPageController($scope) {
    var controller = this;
    
    controller.initialize = function() {
      // Initialize the universal options
      if($scope.options != null) {
        var countries = $scope.options.countries;
          
        for(var i = 0; i < countries.length; i++) {
          var country = countries[i];
                   
          if(country.value == $scope.sheet.country) {
            $scope.universals = country.options;
            $scope.labels = {};
              
            for(var j = 0; j < country.options.length; j++) {
              var universal = country.options[j];
                
              $scope.labels[universal.value] = universal.label;
            }
          }
        }      
      }
    }
    
    controller.hasFieldType = function(type) {
    	var fields = $scope.sheet.fields;
    	for(var i=0; i<fields.length; i++){
    		var field = fields[i];
    		if(field.type.toLowerCase() === type.toLowerCase()){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    controller.isValid = function(field) {
      return !(field.type == 'LOCATION' || field.type == 'LONGITUDE' || field.type == 'LATITUDE' || field.type == 'IGNORE'  || field.type == ''); 
    }
    
    controller.initialize();
  }
  
  function SummaryPage() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/summary-page.jsp',
      scope: true,
      controller : SummaryPageController,
      controllerAs : 'ctrl',            
      link: function (scope, element, attrs) {
      }
    }   
  }  

  function UploaderDialogController($scope, $rootScope, datasetService, localizationService, widgetService) {
    var controller = this;
    
    // Flag indicating if the modal and all of its elements should be destroyed
    $scope.show = false;    
    
    controller.clear = function() {
      // Flag indicating if the modal and all of its elements should be destroyed
      $scope.show = false;
        
      // Flag indicating if the modal is waiting from a response from the server
      $scope.busy = false;   
      
      // List of errors
      $scope.errors = undefined;
      
      // Stack of state snapshots captured when the user clicks next
      $scope.page = undefined;
      
      $scope.options = undefined;      
      $scope.sheet = undefined;      
      $scope.universals = undefined;
      
      $scope.currentStep = 0;
      $scope.locationType = []; 
    }

    controller.setCountry = function(country) {
      $scope.sheet.country = country;
    }
    
    controller.persist = function() {
      var connection = {
        elementId : '#uploader-overlay',
        onSuccess : function(result) {
          if(result.success) {          
            controller.clear();
          
            $scope.$emit('datasetChange', {datasets:result.datasets, finished : true});          
          }
          else {          
            $scope.page.current = 'GEO-VALIDATION';
            $scope.page.snapshots = [];
            
            if(controller.hasLocationField() && controller.hasCoordinateField()) {
            	$scope.currentStep = 5;
            }
            else if(controller.hasLocationField() || controller.hasCoordinateField()) {
          		$scope.currentStep = 4;
       		}
        	else{
          		$scope.currentStep = 3;
        	}
          
            $scope.sheets = result.sheets;
            $scope.sheet = $scope.sheets[0];
            $scope.problems = result.problems;
          
            $scope.$emit('datasetChange', {datasets:result.datasets, finished : false});
          }
         
          $scope.$apply();
        },
        onFailure : function(e){
          $scope.errors.push(e.message);
         
          $scope.$apply();
        }
      };
      // Reset the file Errors
      
      var config = datasetService.getDatasetConfiguration();
      config.sheets[0] = $scope.sheet;
      
      $scope.errors = [];
      
      datasetService.importData(connection, config);      
    }
    
    controller.cancel = function() {
      var connection = {
        elementId : '#uploader-overlay',
        onSuccess : function(result) {
          controller.clear();
         
          $scope.$emit('datasetChange', {finished : true});                
         
          $scope.$apply();
        },
        onFailure : function(e){
          $scope.errors.push(e.message);
         
          $scope.$apply();
        }             
      };
      // Reset the file Errors
      $scope.errors = [];
          
      datasetService.cancelImport(connection, $scope.configuration);
    }
    
    controller.load = function(information, options) {
      $scope.options = options;
      
//      $scope.configuration = information;
      datasetService.setDatasetConfiguration(information);
      var config = datasetService.getDatasetConfiguration();
      
      $scope.sheet = config.sheets[0];
      $scope.sheet.attributes = {ids:[], values : {}};    
      $scope.sheet.coordinates = {ids:[], values : {}};    
      $scope.errors = [];      
      $scope.show = true;
      $scope.locationType = []; 
      $scope.updateExistingDataset = false;
      
      $scope.userSteps = datasetService.getUploaderSteps([]);
      
      $scope.page = {
        snapshots : [],     
        current : 'BEGINNING-INFO',
      };
      
      if($scope.sheet.matches.length > 0) {
        $scope.page.current = 'MATCH';
      }
      
      $scope.$apply();
    }
    
    controller.next = function() {
      // State machine
      if($scope.page.current == 'MATCH') {
        $scope.page.current = 'BEGINNING-INFO';      
        
        var snapshot = {
            page : 'MATCH',
            sheet : angular.copy($scope.sheet)        
        };
        $scope.page.snapshots.push(snapshot);
      }
      else if($scope.page.current == 'BEGINNING-INFO') {
        $scope.page.current = 'INITIAL'; 
        $scope.currentStep = -1;
      }
      else if($scope.page.current == 'INITIAL') {
        // Go to fields page  
        $scope.page.current = 'FIELDS';      
        
        var snapshot = {
          page : 'INITIAL',
          sheet : angular.copy($scope.sheet)        
        };
        $scope.page.snapshots.push(snapshot);
        
        // re-set the step indicator since the snapshot re-sets the attributes page
        $scope.locationType = []; 
        $scope.userSteps = datasetService.getUploaderSteps([]);
        $scope.currentStep = 1;        
      }
      else if($scope.page.current == 'FIELDS') {
        if(controller.hasLocationField()) {
          // Go to location attribute page
          $scope.page.current = 'LOCATION';      
          $scope.currentStep = 3;
        }
        else if (controller.hasCoordinateField()) {
          // Go to coordinate page
          $scope.page.current = 'COORDINATE';   
          $scope.currentStep = 3;
        }
        else {
          // Go to summary page
          $scope.page.current = 'SUMMARY'; 
          $scope.currentStep = 3;
        }
        
        var snapshot = {
          page : 'FIELDS',
          sheet : angular.copy($scope.sheet)        
        };
        $scope.page.snapshots.push(snapshot);
        
        $scope.currentStep = 2;
      }
      else if($scope.page.current == 'LOCATION') {
        if (controller.hasCoordinateField()) {
          // Go to coordinate page
          $scope.page.current = 'COORDINATE'; 
          $scope.currentStep = 3;
        }
        else {
          // Go to summary page
          $scope.page.current = 'SUMMARY';    
          $scope.currentStep = 3;
        }
        
        var snapshot = {
          page : 'LOCATION',
          sheet : angular.copy($scope.sheet)        
        };        
        $scope.page.snapshots.push(snapshot);
      }
      else if($scope.page.current == 'COORDINATE') {
        // Go to summary page
        $scope.page.current = 'SUMMARY';  
        
        if(controller.hasLocationField()) {
          $scope.currentStep = 4;
        }
        else{
          $scope.currentStep = 3;
        }
        
        var snapshot = {
          page : 'COORDINATE',
          sheet : angular.copy($scope.sheet)        
        };        
        $scope.page.snapshots.push(snapshot);
      }
    }
    
    controller.prev = function() {
      if($scope.page.current === "SUMMARY" || $scope.page.current === "BEGINNING-INFO") {
        controller.handlePrev();    	  
      }
      else {
        var title = localizationService.localize("dataUploader", "prevDialogTitle");
        
        var message = localizationService.localize("dataUploader", "prevDialogContent");
        
        var buttons = [];
        buttons.push({
          label : localizationService.localize("dataUploader", "ok"),
          config : {class:'btn btn-primary'},
          callback : function(){
            controller.handlePrev();
            
            $scope.$apply();
          }
        });
        buttons.push({
          label : localizationService.localize("dataUploader", "cancel"),
          config : {class:'btn'},
        });
        
        widgetService.createDialog(title, message, buttons);      
      }
    }
    
    controller.handlePrev = function() {
      if($scope.page.snapshots.length > 0) { 
        if($scope.page.current == 'INITIAL') {
          $scope.page.current = 'BEGINNING-INFO'; 
        }
        else{
          $scope.$broadcast('pagePrev', {});
          
          var snapshot = $scope.page.snapshots.pop();
          
          $scope.page.current = snapshot.page;          
          $scope.sheet = snapshot.sheet;
          
          $scope.updateExistingDataset = false;
        }
      }     
      
      if($scope.page.current === "SUMMARY"){
        var stepCt = 4;
        if (!controller.hasCoordinateField()) {
          stepCt = stepCt - 1;
        }
        
        if(!controller.hasLocationField()) {
          stepCt = stepCt - 1;
        }
        
        $scope.currentStep = stepCt;
      }
      else if($scope.page.current === "COORDINATE"){
        var stepCt = 3;
        if(!controller.hasLocationField()) {
          stepCt = stepCt - 1;
        }
        
        $scope.currentStep = stepCt;
      }
      else if($scope.page.current === "LOCATION"){
        $scope.currentStep = 2;
      }
      else if($scope.page.current === "FIELDS"){
        $scope.currentStep = 1;
      }
      else if($scope.page.current === "INITIAL"){
        $scope.currentStep = 0;
      }      
    }
    
    controller.hasLocationField = function() {
      for(var i = 0; i < $scope.sheet.fields.length; i++) {     
        var field = $scope.sheet.fields[i]
          
        if(field.type == 'LOCATION') {
          return true;
        }
      }        
      
      return false;
    }
    
    controller.hasCoordinateField = function() {
      for(var i = 0; i < $scope.sheet.fields.length; i++) {     
        var field = $scope.sheet.fields[i]
              
        if(field.type == 'LONGITUDE' || field.type == 'LATITUDE' ) {
          return true;
        }
      }        
          
      return false;      
    }
    
    $rootScope.$on('dataUpload', function(event, data){
      if(data.information != null && data.information.sheets.length > 0) {
        controller.load(data.information, data.options);
      }
    });
    
    $scope.$on('loadConfiguration', function(event, data){
      // Go to summary page
      $scope.page.current = 'SUMMARY';  
      $scope.updateExistingDataset = true;
        
      var snapshot = {
        page : 'MATCH',
        sheet : angular.copy($scope.sheet)        
      };        
      $scope.page.snapshots.push(snapshot);

      angular.copy(data.sheet, $scope.sheet);
    });
    
    // AttributePageController emit's event when user toggles attribute types between location and non-location types
    $scope.$on('hasLocationEvent', function(event, locationType) { 
      $scope.locationType = locationType;
      
      if(locationType.length === 1 && locationType[0] === "LOCATION"){
        $scope.userSteps = datasetService.getUploaderSteps(["LOCATION"]);
      }
      else if(locationType.length === 1 && locationType[0] === "LONGITUDE" || locationType[0] === "LATITUDE"){
        $scope.userSteps = datasetService.getUploaderSteps(["COORDINATE"]);
      }
      else if(locationType.length > 1 && locationType.indexOf("LOCATION") !== -1 && (locationType.indexOf("LATITUDE") !== -1 || locationType.indexOf("LONGITUDE")) ){
        $scope.userSteps = datasetService.getUploaderSteps(["LOCATION", "COORDINATE"]);
      }
      else{
        $scope.userSteps = datasetService.getUploaderSteps([]);
      }
    });
  } 
  
  function UploaderDialog() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data-uploader/uploader-dialog.jsp',
      scope: {
      },
      controller : UploaderDialogController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      }
    }   
  }  
  
  function ValidateUnique() {
    return {
      restrict: "A",
      scope : {
        validator : '&'  
      },
      require: 'ngModel',
      link: function (scope, element, attr, ngModel) {
      
        element.bind('blur', function (e) {
          var unique = scope.validator()(element.val());
          ngModel.$setValidity('unique', unique);
          
          scope.$apply();
        });            
      }
    };
  };  
  
  function ValidateAccepted() {
    return {
      restrict: "A",
      scope : {
        field : '='  
      },
      require: 'ngModel',
      link: function (scope, element, attr, ngModel) {
//        scope.$watch('field.accepted', function() {
//          ngModel.$setValidity('accepted', scope.field.accepted);        
//        });
      }
    };
  };  
  
  
  angular.module("data-uploader", ["styled-inputs", "dataset-service", "localization-service", "widget-service", "runway-service" ]);
  angular.module("data-uploader")
   .directive('attributesPage', AttributesPage)
   .directive('matchPage', MatchPage)
   .directive('geoValidationPage', GeoValidationPage)
   .directive('geoValidationProblem', GeoValidationProblem)
   .directive('beginningInfoPage', BeginningInfoPage)
   .directive('namePage', NamePage)
   .directive('locationPage', LocationPage)
   .directive('coordinatePage', CoordinatePage)
   .directive('summaryPage', SummaryPage)
   .directive('validateUnique', ValidateUnique)
   .directive('validateAccepted', ValidateAccepted)
   .directive('uploaderDialog', UploaderDialog);
})();

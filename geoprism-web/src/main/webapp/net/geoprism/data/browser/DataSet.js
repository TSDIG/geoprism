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
  function DatasetController($scope, $timeout, datasetService, localizationService, widgetService, $window) {
    var controller = this;
    
    controller.init = function() {
      $scope.showForm = false;
      
      var connection = {
        onSuccess : function(response) {
          $scope.datasets = response.datasets;
            
          $scope.$apply();
        } 
      };      
      
      datasetService.getAll(connection);
    }
    
    controller.getIndex = function(dataset) {
      for(var i = 0; i < $scope.datasets.length; i++) {
        if($scope.datasets[i].id === dataset.id) {
          return i;
        }
      }
      
      return null;
    }
    
    controller.apply = function(dataset) {
      
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(dataset) {
          var index = controller.getIndex(dataset);
          var ds = $scope.datasets[index];
          ds.label = dataset.label;
          ds.editMode = false;
          
          $window.onclick = null;
          $scope.orignialDatasetState = null;
          $scope.$apply();
        }
      };
      
      datasetService.applyDatasetUpdate(connection, dataset);        
    }
    
    controller.setDatasetState = function(dataset) {
      dataset.editMode = true;
      if(!$scope.orignialDatasetState){
        $scope.orignialDatasetState = angular.copy(dataset);
      }
      
      // cancel edit mode if clicking outsid of the input element unless its a button (i.e. submit or cancel)
      $window.onclick = function (event) {
        if( !event.target.classList.contains("dataset-list-input") && event.target.type !== 'button' ){
          controller.cancelDatasetEdit(dataset);
          $scope.$apply();
        }
      };
    }
    
    controller.cancelDatasetEdit = function(dataset) {
      if($scope.orignialDatasetState){
        dataset.label = $scope.orignialDatasetState.label;
      }
      dataset.editMode = false;
      $scope.orignialDatasetState = null;
      $window.onclick = null;
    }
    
    controller.datasetElementHover = function($event) {
      if($event.target.readOnly){
        $scope.datasetListInputTitle = localizationService.localize("dataset", "datasetListInputTitle", "Click to edit this datasets name");
      }
      else{
        $scope.datasetListInputTitle = "";
      }
    }
    
    controller.edit = function(dataset) {
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(response) {
          $scope.$emit('datasetEdit', {dataset:response});            
          
          $scope.$apply();
        }
      };

      datasetService.edit(connection, dataset.id);
    }
    
    controller.remove = function(dataset) {
      var title = localizationService.localize("dataset", "deleteDatasetTitle", "Delete dataset");

      var message = localizationService.localize("dataset", "removeContent", "Are you sure you want to delete the dataset [{0}]?");
      message = message.replace('{0}', dataset.label);
      
      var buttons = [];
      buttons.push({
      label : localizationService.localize("dataset", "delete", "Delete"),
      config : {class:'btn btn-primary'},
        callback : function(){
          var connection = {
            elementId : '#innerFrameHtml',
            onSuccess : function() {
              // Find and remove the dataset from the datasets array         
              var index = controller.getIndex(dataset);
                        
              if(index != null) {
                $scope.datasets.splice(index, 1);        
              }
                          
              $scope.$apply();
            }
          };
                    
          datasetService.remove(connection, dataset.id);            
        }                    
      });
      buttons.push({
        label : localizationService.localize("dataset", "cancel", "Cancel"),
        config : {class:'btn'},
      });
      
      widgetService.createDialog(title, message, buttons);
    }
    
    /*
     * Data Upload Section
     */
    controller.uploadFile = function(files) {
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(result) {               
          $scope.$emit('dataUpload', result);            
                
          // Hide modal, but preserve the elements and values        
          $scope.hidden = true;
          $scope.$apply();
        },
        onFailure : function(e){
          $scope.errors.push(e.message);
          
          $scope.$apply();
          
          $('#app-container').parent().parent().animate({ scrollTop: 0 }, 'slow');
        }
      };
      
      // Reset the file Errors
      $scope.errors = [];
      datasetService.uploadSpreadsheet(connection, files[0]);
    }    
    
    controller.addDatasets = function(datasets) {
      for(var i = 0; i < datasets.length; i++) {
        var dataset = datasets[i];
      
        var index = controller.getIndex(dataset)
        
        if(index == -1) {
          $scope.datasets.push(dataset);        
        }
        else {
          $scope.datasets[index] = dataset;
        }
      }
    }
    
    controller.getIndex = function(dataset) {
      for(var i = 0; i < $scope.datasets.length; i++) {
        if($scope.datasets[i].id == dataset.id) {
          return i;
        }
      }
      
      return -1;
    }
    
    $scope.$on('datasetChange', function(event, data){    
      if(data.datasets != null) {
        controller.addDatasets(data.datasets);
      }
    });    
    
    controller.init();
  }
  
  function DatasetModalController($scope, $rootScope, datasetService) {
    var controller = this;
    
    controller.init = function() {
      $scope.show = false;
    }
    
    controller.load = function(dataset) {
      $scope.dataset = dataset;
      $scope.show = true;
    }
    
    controller.apply = function() {      
      var connection = {
        elementId : '#modal-div',
        onSuccess : function() {
          $scope.$emit('datasetChange', {datasets:[$scope.dataset]});
          $scope.show = false;	
        	
          $scope.$apply();          
        },
        onFailure : function(e){
          $scope.errors.push(e.message);
          
          $scope.$apply();
        }      
      };

      $scope.errors = [];
      
      datasetService.apply(connection, $scope.dataset);        
    }
    
    controller.cancel = function() {
      var connection = {
        elementId : '#modal-div',
        onSuccess : function() {
          $scope.show = false;  
                  
          $scope.$apply();          
        },
        onFailure : function(e){
          $scope.errors.push(e.message);
                  
          $scope.$apply();
        }      
      };

      $scope.errors = [];
              
      datasetService.cancel(connection, $scope.dataset.id);        
    }
    
    $rootScope.$on('datasetEdit', function(event, data) {
      controller.load(data.dataset);
    });
    
    
    controller.init();
  }
  
  function DatasetModal() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: '/partial/data/browser/dataset-modal.jsp',
      scope: {
      },
      controller : DatasetModalController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs, ctrl) {
      }
    }   
  }

  angular.module("data-set", ["data-uploader", "styled-inputs", 'ngFileUpload', "dataset-service", "localization-service", "widget-service", "runway-service"]);
  angular.module("data-set")
  .controller('DatasetController', DatasetController)
  .directive('datasetModal', DatasetModal)
  .directive('pressEnter', function () {
      return function (scope, element, attrs) {
          element.bind("keydown keypress", function (event) {
              if(event.which === 13) {
                  scope.$apply(function (){
                      scope.$eval(attrs.pressEnter);
                  });

                  event.preventDefault();
              }
          });
      };
  });
})();

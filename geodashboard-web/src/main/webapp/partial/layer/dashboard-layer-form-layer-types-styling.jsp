<%--

    Copyright (c) 2015 TerraFrame, Inc. All rights reserved.

    This file is part of Runway SDK(tm).

    Runway SDK(tm) is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Runway SDK(tm) is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="../../WEB-INF/tlds/geodashboard.tld" prefix="gdb"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
       
<div class="row-holder" style="display:none">
  <div class="label-holder">
    <strong><gdb:localize key="DashboardThematicLayer.form.styleTheLayer"/></strong>
  </div>
  <div class="holder">
    <div id="layer-type-styler-container" class="tab-content"> 
      
      <!-- BASICPOINT -->
      <div ng-class="{ 'active' : '{{layerModel.layerType}}' == 'BASICPOINT' }" class="tab-pane" id="tab001basicpoint">
        
        <!-- BASIC FILL -->
        <style-basic-fill fill="styleModel.pointFill" opacity="styleModel.pointOpacity"></style-basic-fill>
      
        <!-- BASIC STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.pointStroke" stroke-width="styleModel.pointStrokeWidth" stroke-opacity="styleModel.pointStrokeOpacity"></style-stroke>
      
        <!-- BASIC SHAPE -->
        <div class="fill-block">
          <strong class="title"><gdb:localize key="DashboardThematicLayer.form.shapeHeading"/></strong>
          <div class="cell-holder">
            <div class="cell">
              <label for="basic-point-radius-select"><gdb:localize key="DashboardLayer.form.size"/></label>
              <div class="text">
                <input id="basic-point-radius-select" name="style.basicPointSize" type="text" ng-model="styleModel.basicPointSize" placeholder="{{styleModel.basicPointSize}}"></input>
              </div>
            </div>
          </div>
        
          <div id="point-type-container" class="cell">
            <label for="point-type"><gdb:localize key="DashboardLayer.form.pointType"/></label>
            <styled-basic-select options="dynamicDataModel.pointTypes" model="styleModel.pointWellKnownName" class="method-select"></styled-basic-select>            
          </div>
        </div>
      </div>
      
      <!-- GRADIENT POINT -->
      <div class="tab-pane" id="tab006gradientpoint" ng-class="{ 'active' : '{{layerModel.layerType}}' == 'GRADIENTPOINT' }">
      
        <!-- POINT GRADIENT FILL -->
        <style-gradient-fill min-fill="styleModel.gradientPointMinFill" max-fill="styleModel.gradientPointMaxFill" opacity="styleModel.gradientPointFillOpacity" class="point-gradient"></style-gradient-fill>
        
        <!-- POINT GRADIENT STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.gradientPointStroke" stroke-width="styleModel.gradientPointStrokeWidth" stroke-opacity="styleModel.gradientPointStrokeOpacity"></style-stroke>
        
        <!-- POINT GRADIENT SHAPE -->        
        <div class="fill-block">
         <strong class="title"><gdb:localize key="DashboardThematicLayer.form.shapeHeading"/></strong>
          <div class="cell-holder">
            <div class="cell">
              <label for="gradient-point-radius-select"><gdb:localize key="DashboardLayer.form.size"/></label>
              <div class="text">
                <input id="gradient-point-radius-select" name="style.gradientPointSize" type="text" ng-model="styleModel.gradientPointSize"></input>
              </div>
            </div>
          </div>
          <div id="gradient-point-type-container" class="cell">
            <label for="gradient-point-type"><gdb:localize key="DashboardLayer.form.pointType"/></label>
            <styled-basic-select options="dynamicDataModel.pointTypes" model="styleModel.gradientPointWellKnownName" class="method-select"></styled-basic-select>            
          </div>
        </div>
      </div>
      
      <!-- CATEGORY POINT -->
      <div class="tab-pane" id="tab007categoriespoint" ng-class="{ 'active' : '{{layerModel.layerType}}' == 'CATEGORYPOINT' }">
      
        <div class="color-section">
          <strong class="title"><gdb:localize key="DashboardThematicLayer.form.fill"/></strong>
          <div class="heading-list">
            <span><gdb:localize key="DashboardThematicLayer.form.category"/></span>
            <span><gdb:localize key="DashboardThematicLayer.form.color"/></span>
            <span></span>
          </div>
          
          <div class="category-block" id="category-point-colors-container">

            <!-- RENDER ONTOLOGY TREE DATA  -->
            <style-category-ontology ng-if="dynamicDataModel.isOntologyAttribute" categories="categoryWidget.basicPointCatOptionsObj" nodes="dynamicDataModel.ontologyNodes"></style-category-ontology>
            
            <!-- RENDER BASIC CATEGORIES -->
            <style-category-list ng-if="!dynamicDataModel.isOntologyAttribute" categories="categoryWidget.basicPointCatOptionsObj" auto-complete="ctrl.basicCategoryAutocompleteSource"></style-category-list>
          </div>
        </div>
        
        <div class="stroke-block">
          <!-- POINT CATEGORY STROKE -->        
          <style-stroke stroke="styleModel.categoryPointStroke" stroke-width="styleModel.categoryPointStrokeWidth" stroke-opacity="styleModel.categoryPointStrokeOpacity"></style-stroke>
        
          <div id="category-point-radius-block" class="fill-block">
            <strong class="title"><gdb:localize key="DashboardThematicLayer.form.shapeHeading"/></strong>
            <div class="cell-holder">
              <div class="cell">
                <label for="category-point-radius-select"><gdb:localize key="DashboardLayer.form.size"/></label>
                <div class="text">
                  <input id="category-point-radius-select" name="style.categoryPointSize" type="text" ng-model="styleModel.categoryPointSize"></input>
                </div>
              </div>
              <div class="cell">
                <label for="category-point-fill-opacity-select"><gdb:localize key="DashboardLayer.form.opacity"/></label>
                <div class="text">
                  <select id="category-point-fill-opacity-select" class="tab-select" name="style.categoryPointFillOpacity"
                    ng-model="styleModel.categoryPointFillOpacity" convert-to-number>
                    <c:forEach step="5" begin="0" end="100" var="size">
                      <fmt:formatNumber value="${size/100}" maxFractionDigits="2" type="number" var="potentialValue"/>
                      <option value="${potentialValue}">${size}</option>
                    </c:forEach>                    
                  </select>
                </div>
              </div>
            </div>
          
            <div id="category-point-type-container" class="cell">
              <label for="category-point-type"><gdb:localize key="DashboardLayer.form.pointType"/></label>
              <styled-basic-select options="dynamicDataModel.pointTypes" model="styleModel.categoryPointWellKnownName" class="method-select"></styled-basic-select>            
            </div>
          </div>
        </div>
      </div>
    
      
      <!-- BUBBLE -->
      <div class="tab-pane" id="tab002bubble" ng-class="{ 'active' : '{{layerModel.layerType}}' == 'BUBBLE' }">
        <!-- BASIC FILL -->
        <style-basic-fill fill="styleModel.bubbleFill" opacity="styleModel.bubbleOpacity"></style-basic-fill>
      
        <!-- BASIC STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.bubbleStroke" stroke-width="styleModel.bubbleStrokeWidth" stroke-opacity="styleModel.bubbleStrokeOpacity"></style-stroke>
      
        <div class="fill-block">
          <strong class="title"><gdb:localize key="DashboardThematicLayer.form.shapeHeading"/></strong>
          <div class="cell-holder">
            <div class="cell">
              <label for="f76"><gdb:localize key="DashboardLayer.form.min"/></label>
              <div class="text">
                <input id="f76" name="style.bubbleMinSize" type="text" ng-model="styleModel.bubbleMinSize"/>
              </div>
            </div>
            <div class="cell">
              <label for="f77"><gdb:localize key="DashboardLayer.form.max"/></label>
              <div class="text">
                <input id="f77" name="style.bubbleMaxSize" type="text" ng-model="styleModel.bubbleMaxSize"></input>
              </div>
            </div>
            <styled-check-box model="styleModel.bubbleContinuousSize" label="<gdb:localize key="DashboardThematicLayer.form.continuousSize"/>"></styled-check-box>
          </div>
        </div>
        
        <div class="secondary-box">
          <strong class="title"><gdb:localize key="DashboardLayer.form.secondaryAttributeStyle"/></strong>
          <div class="cell-holder">        
            <label class="secondary-label" for="secondaryAttribute" ><gdb:localize key="DashboardLayer.form.secondaryAttribute"/></label>
            <div id="secondary-select-box" class="select-box">
              <select id="secondaryAttribute" class="method-select" name="secondaryAttribute"
                ng-model="styleModel.secondaryAggregation.attribute"
                ng-options="attr as attr.label for attr in dynamicDataModel.secondaryAttributes track by attr.id"
                ng-selected="styleModel.secondaryAggregation.attribute.id == attr.id"
                ng-change="ctrl.onAttributeChange()">
              </select>
            </div>
          </div>
          
          <div id="secondary-content" ng-show="ctrl.secondaryAttributeIsValid()" >
            <div class="cell-holder">
              <label class="secondary-label" for="secondaryAggregation"><gdb:localize key="DashboardLayer.form.secondaryAggregation"/></label>
              <div class="select-box" id="secondary-aggregation-container">
              
                <select id="secondaryAggregation" class="method-select" name="secondaryAggregation"
                  ng-model="styleModel.secondaryAggregation.method"
                  ng-options="agg as agg.label for agg in dynamicDataModel.secondaryAggregationMethods track by agg.value"
                  ng-selected="styleModel.secondaryAggregation.method.value == agg.value">
                </select>
                
              </div> 
            </div>
            <div id="secondary-cateogries">
              <div class="color-section">
                <div class="heading-list">
                  <span><gdb:localize key="DashboardLayer.form.category"/></span>
                  <span><gdb:localize key="DashboardLayer.form.color"/></span>
                  <span></span>
                </div>
                <div class="category-block">
                  <!-- RENDER SECONDARY ONTOLOGY TREE -->
                  <style-category-ontology ng-if="ctrl.ready && ctrl.isSecondaryAttributeOntology()" categories="styleModel.secondaryAggregation" nodes="styleModel.secondaryAggregation.attribute.nodes" show-other="false"></style-category-ontology>
                  
                  <!-- RENDER SECONDARY CATEGORIES -->
                  <style-category-list ng-if="ctrl.ready && !ctrl.isSecondaryAttributeOntology()" categories="styleModel.secondaryAggregation" auto-complete="ctrl.secondaryCategoryAutocompleteSource" show-other="false"></style-category-list>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      
      <!-- BASICPOLYGON -->
      <div class="tab-pane" id="tab003basicpolygon" ng-class="{ 'active' : '{{layerModel.layerType}}' == 'BASICPOLYGON' }">
        <!-- BASIC POLYGON FILL -->
        <style-basic-fill fill="styleModel.polygonFill" opacity="styleModel.polygonFillOpacity"></style-basic-fill>
      
        <!-- BASIC POLYGON STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.polygonStroke" stroke-width="styleModel.polygonStrokeWidth" stroke-opacity="styleModel.polygonStrokeOpacity"></style-stroke>                
      </div>
      
      
      <!-- GRADIENT POLYGON -->
      <div class="tab-pane" id="tab004gradientpolygon" ng-class="{ 'active' : '{{layerModel.layerType}}' == 'GRADIENTPOLYGON' }">
        <!-- POINT GRADIENT FILL -->
        <style-gradient-fill min-fill="styleModel.gradientPolygonMinFill" max-fill="styleModel.gradientPolygonMaxFill" opacity="styleModel.gradientPolygonFillOpacity" class="point-gradient"></style-gradient-fill>
        
        <!-- POINT GRADIENT STROKE -->
        <style-stroke class="stroke-block" stroke="styleModel.gradientPolygonStroke" stroke-width="styleModel.gradientPolygonStrokeWidth" stroke-opacity="styleModel.gradientPolygonStrokeOpacity"></style-stroke>        
      </div>
      
     
      <!-- CATEGORY POLYGON -->
       <div class="tab-pane" id="tab005categoriespolygon" ng-class="{ 'active' : '{{layerModel.layerType}}' == 'CATEGORYPOLYGON' }">
        <div class="color-section">
          <strong class="title"><gdb:localize key="DashboardThematicLayer.form.fill"/></strong>
          <div class="heading-list">
            <span><gdb:localize key="DashboardThematicLayer.form.category"/></span>
            <span><gdb:localize key="DashboardThematicLayer.form.color"/></span>
            <span></span>
          </div>
        <div class="category-block" id="category-colors-container">
          <!-- RENDER ONTOLOGY TREE DATA  -->
          <style-category-ontology ng-if="dynamicDataModel.isOntologyAttribute" categories="categoryWidget.polygonCatOptionsObj" nodes="dynamicDataModel.ontologyNodes"></style-category-ontology>
                    
          <!-- RENDER BASIC CATEGORIES -->
          <style-category-list ng-if="!dynamicDataModel.isOntologyAttribute" categories="categoryWidget.polygonCatOptionsObj" auto-complete="ctrl.basicCategoryAutocompleteSource"></style-category-list>
        </div>
      </div>
      
      <!-- POINT CATEGORY STROKE -->        
      <style-stroke class="stroke-block" stroke="styleModel.categoryPolygonStroke" stroke-width="styleModel.categoryPolygonStrokeWidth" stroke-opacity="styleModel.categoryPolygonStrokeOpacity"></style-stroke>
    
    </div> <!--  end style container  -->
  </div>  <!--  end holder  -->
</div> 
///
/// Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
///
/// This file is part of Runway SDK(tm).
///
/// Runway SDK(tm) is free software: you can redistribute it and/or modify
/// it under the terms of the GNU Lesser General Public License as
/// published by the Free Software Foundation, either version 3 of the
/// License, or (at your option) any later version.
///
/// Runway SDK(tm) is distributed in the hope that it will be useful, but
/// WITHOUT ANY WARRANTY; without even the implied warranty of
/// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/// GNU Lesser General Public License for more details.
///
/// You should have received a copy of the GNU Lesser General Public
/// License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
///
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var router_1 = require("@angular/router");
var datasets_component_1 = require("./datasets/datasets.component");
var dataset_detail_component_1 = require("./datasets/dataset-detail.component");
var category_detail_component_1 = require("./category/category-detail.component");
var routes = [
    {
        path: '',
        redirectTo: '/datasets',
        pathMatch: 'full'
    },
    {
        path: 'datasets',
        component: datasets_component_1.DatasetsComponent
    },
    {
        path: 'dataset/:id',
        component: dataset_detail_component_1.DatasetDetailComponent,
        resolve: {
            dataset: dataset_detail_component_1.DatasetResolver
        }
    },
    {
        path: 'category/:id',
        component: category_detail_component_1.CategoryDetailComponent,
        resolve: {
            category: category_detail_component_1.CategoryResolver
        }
    },
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    return AppRoutingModule;
}());
AppRoutingModule = __decorate([
    core_1.NgModule({
        imports: [router_1.RouterModule.forRoot(routes)],
        exports: [router_1.RouterModule],
        providers: [{ provide: common_1.LocationStrategy, useClass: common_1.HashLocationStrategy }, dataset_detail_component_1.DatasetResolver, category_detail_component_1.CategoryResolver]
    }),
    __metadata("design:paramtypes", [])
], AppRoutingModule);
exports.AppRoutingModule = AppRoutingModule;
exports.routedComponents = [datasets_component_1.DatasetsComponent, dataset_detail_component_1.DatasetDetailComponent, category_detail_component_1.CategoryDetailComponent];
//# sourceMappingURL=app-routing.module.js.map
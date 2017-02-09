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
/// License along with Runway SDK(tm).  If not, see <ehttp://www.gnu.org/licenses/>.
///
"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
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
var http_1 = require("@angular/http");
require("rxjs/add/operator/toPromise");
var core_service_1 = require("../service/core.service");
var event_http_service_1 = require("../service/event-http.service");
var IconService = (function (_super) {
    __extends(IconService, _super);
    function IconService(service, ehttp, http) {
        var _this = _super.call(this, service) || this;
        _this.ehttp = ehttp;
        _this.http = http;
        return _this;
    }
    IconService.prototype.getIcons = function () {
        return this.ehttp
            .get(acp + '/iconimage/getAll')
            .toPromise()
            .then(function (response) {
            return response.json().icons;
        })
            .catch(this.handleError.bind(this));
    };
    IconService.prototype.remove = function (id) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.ehttp
            .post(acp + '/iconimage/remove', JSON.stringify({ id: id }), { headers: headers })
            .toPromise()
            .catch(this.handleError.bind(this));
    };
    IconService.prototype.edit = function (id) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.ehttp
            .post(acp + '/iconimage/edit', JSON.stringify({ id: id }), { headers: headers })
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError.bind(this));
    };
    IconService.prototype.unlock = function (id) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.ehttp
            .post(acp + '/iconimage/unlock', JSON.stringify({ id: id }), { headers: headers })
            .toPromise()
            .catch(this.handleError.bind(this));
    };
    IconService.prototype.apply = function (id, label) {
        var data = new FormData();
        data.append('id', id);
        data.append('label', label);
        return this.ehttp
            .post(acp + '/iconimage/apply', data)
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError.bind(this));
    };
    return IconService;
}(core_service_1.BasicService));
IconService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [core_service_1.EventService, event_http_service_1.EventHttpService, http_1.Http])
], IconService);
exports.IconService = IconService;
//# sourceMappingURL=icon.service.js.map
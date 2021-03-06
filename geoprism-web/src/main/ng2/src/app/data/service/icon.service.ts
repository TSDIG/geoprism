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

import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { Icon } from '../model/icon';

declare var acp: any;

@Injectable()
export class IconService extends BasicService {
  
  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) {
    super(service); 
  }
  
  getIcons(): Promise<Icon[]> {
    return this.ehttp
      .get(acp + '/iconimage/getAll')
      .toPromise()
      .then(response => {
        return response.json().icons as Icon[];
      })
      .catch(this.handleError.bind(this));
  }
  
  remove(oid:string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });
    
    return this.ehttp
      .post(acp + '/iconimage/remove', JSON.stringify({oid:oid}), { headers: headers })
      .toPromise()
      .catch(this.handleError.bind(this));	  
  }
  
  edit(oid : string): Promise<Icon> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
      .post(acp + '/iconimage/edit', JSON.stringify({oid:oid}), { headers: headers })
      .toPromise()
      .then(response => {
        return response.json() as Icon;
      })      
      .catch(this.handleError.bind(this));
  }  
  
  unlock(oid: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
      .post(acp + '/iconimage/unlock', JSON.stringify({oid:oid}), { headers: headers })
      .toPromise()
      .catch(this.handleError.bind(this));
  }
  
    
  apply(oid: string, label: string): Promise<Icon> {
      
    let data = new FormData();
    data.append('oid', oid);
    data.append('label', label)
    
    return this.ehttp
      .post(acp + '/iconimage/apply', data)
      .toPromise()
      .then(response => {
        return response.json() as Icon;
      })      
      .catch(this.handleError.bind(this));
  }
  
}

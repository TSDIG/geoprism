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

import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { Program } from '../model/dhis2-program';
import { TrackedEntity } from '../model/dhis2-tracked-entity';
import { TrackedEntityAttribute } from '../model/dhis2-tracked-entity-attribute';

declare var acp: any;

@Injectable()
export class DHIS2Service extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  isEnabled() : Promise<boolean> {
    return this.ehttp
      .get(acp + '/dhis2/isEnabled')
      .toPromise()
      .then(response => {
        return response.json() as boolean;
      })
      .catch(this.handleError.bind(this));
  }

  getPrograms(): Promise<Program[]> {
    return this.ehttp
      .get(acp + '/dhis2/getPrograms')
      .toPromise()
      .then(response => {
        return response.json() as Program[];
      })
      .catch(this.handleError.bind(this));
  }
  
  getTrackedEntities(): Promise<TrackedEntity[]> {
    return this.ehttp
      .get(acp + '/dhis2/getTrackedEntities')
      .toPromise()
      .then(response => {
        return response.json() as TrackedEntity[];
      })
      .catch(this.handleError.bind(this));
  }
  
  getTrackedEntityAttributes(): Promise<TrackedEntityAttribute[]> {
    return this.ehttp
      .get(acp + '/dhis2/getTrackedEntityAttributes')
      .toPromise()
      .then(response => {
        return response.json() as TrackedEntityAttribute[];
      })
      .catch(this.handleError.bind(this));
  }
  
}

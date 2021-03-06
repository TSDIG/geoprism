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

import { Component, OnInit, Input, Output, EventEmitter, Directive} from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';

import { UploadInformation, Sheet, Page, Field, Universal, CoordinateAttribute} from './uploader-model';

@Component({
  
  selector: 'summary-page',
  templateUrl: './summary-page.component.html',
  styleUrls: []
})
export class SummaryPageComponent implements OnInit {

  @Input() sheet: Sheet;
  @Input() page: Page;
  @Input() info: UploadInformation;
  
  universals: Universal[];
  labels: {[key : string] : string};  
  
  texts: Field[];
  categories: Field[];
  numbers: Field[];
  booleans: Field[];
  dates: Field[];
  universalMap: {[key : string]: Universal[]};
  
  constructor() {
    this.texts = [];
    this.categories = [];
    this.numbers = [];
    this.booleans = [];
    this.dates = [];
    this.universalMap = {};
  }  

  ngOnInit(): void {
    // Initialize the universal options
    if(this.info.options != null) {
      let countries = this.info.options.countries;
          
      for(let i = 0; i < countries.length; i++) {
        let country = countries[i];
                   
        if(country.value == this.sheet.country) {
          this.universals = country.options;
          this.labels = {};
              
          for(let j = 0; j < country.options.length; j++) {
            let universal = country.options[j];
                
            this.labels[universal.value] = universal.label;
          }
        }
      }      
    }
    
    for(let i=0; i<this.sheet.fields.length; i++){
      let field = this.sheet.fields[i];
    
      let valid = this.isValid(field);
      
      if(valid && field.type === 'TEXT') {
        this.texts.push(field);
      }
      
      if(valid && (field.type === 'CATEGORY' || field.type === 'DOMAIN')) {
        this.categories.push(field);
      }
      
      if(valid && field.type === 'DOUBLE' || field.type === 'LONG') {
        this.numbers.push(field);
      }
      
      if(valid && field.type === 'BOOLEAN') {
        this.booleans.push(field);
      }
      
      if(valid && field.type === 'DATE') {
        this.dates.push(field);
      }
    }
    
    for(let i = 0; i < this.sheet.attributes.ids.length; i++) {
      let oid = this.sheet.attributes.ids[i];
      let attribute = this.sheet.attributes.values[oid];
      
      this.universalMap[oid] = [];
      
      for(let j = 0; j < this.universals.length; j++) {
        let universal = this.universals[j];
        
        if(attribute.fields[universal.value] != null && attribute.fields[universal.value] != 'EXCLUDE') {
          universal.useCoordinatesForLocationAssignment = attribute.useCoordinatesForLocationAssignment;
          this.universalMap[oid].push(universal);
        }
      }
    }
    
    let c = this.sheet.coordinates as any; 
    
    if(c != null && typeof c === 'object' && c.ids != null) {
      let coordinates = new Array<CoordinateAttribute>();
      
      c.ids.forEach((oid:string) => {
        coordinates.push(c.values[oid]);	  
      });
      
      this.sheet.coordinates = coordinates;
    }
  }
    
  hasFieldType(type: string): boolean {
    let fields = this.sheet.fields;
    
    for(let i=0; i<fields.length; i++){
      let field = fields[i];
      
      if(field.type.toLowerCase() === type.toLowerCase()){
        return true;
      }
    }
      
    return false;
  }
    
  isValid(field: Field): boolean {
    return !(field.type == 'LOCATION' || field.type == 'LONGITUDE' || field.type == 'LATITUDE' || field.type == 'IGNORE'  || field.type == ''); 
  }
}

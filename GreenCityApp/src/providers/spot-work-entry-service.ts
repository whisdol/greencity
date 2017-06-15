import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the SpotWorkEntryService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class SpotWorkEntryService {

  entry: any;
  entries: any;

  constructor(public http: Http) {
    console.log('Hello SpotWorkEntryService Provider');
  }

  // Get work entries for spot
  getAll(input: any) {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/spots/' + input.spotId + '/workEntries')
    .map(res => res.json())
    .subscribe(data => {
    this.entries = data;
          resolve(this.entries);
          console.log(this.entries, data);
        });
    });
  }

  // Get single work entry for spot
  get(input: any) {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/spots/' + input.spotId + '/workEntries/' + input.workEntryId)
    .map(res => res.json())
    .subscribe(data => {
    this.entry = data;
          resolve(this.entry);
          console.log(this.entry, data);
        });
    });
  }

  // Create work entry
  create(input: any) {

  let headers = new Headers();
  headers.append('Content-Type', 'application/json');

  let body = {
    user: input.user,
    spotId: input.spotId,
    shortDescription: input.shortDescription,
    description: input.description
  };

  return new Promise(resolve => {
    this.http.post('http://greencity.whisdol.de:8080/spots/' + input.spotId + '/workEntries/create', JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.entry = data;
          resolve(this.entry);
          console.log(this.entry, data);
        });
    });
  }

  // Update work entry
  update(input: any) {

  let headers = new Headers();
  headers.append('Content-Type', 'application/json');

  let body = {
    id: input.id,
    roadName: input.roadName,
    houseNumber: input.houseNumber,
    postCode: input.postCode,
    latitude: input.latitude,
    longitude: input.longitude,
    city: input.city
  };

  return new Promise(resolve => {
    this.http.put('http://greencity.whisdol.de:8080/spots/' + input.spotId + '/workEntries/' + input.workEntryId, JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.entry = data;
          resolve(this.entry);
          console.log(this.entry, data);
        });
    });
  }

  // Delete work entry
  delete(input: any) {

  return new Promise(resolve => {
    this.http.delete('http://greencity.whisdol.de:8080/spots/' + input.spotId + '/workEntries/' + input.workEntryId)
    .map(res => res.json())
    .subscribe(data => {
    this.entry = data;
          resolve(this.entry);
          console.log(this.entry, data);
        });
    });
  }

  }

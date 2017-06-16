import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the SpotService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class SpotService {

  spot: Object;
  // spot = {};
  searchParameter: any;

  constructor(public http: Http) {
    console.log('Hello SpotService Provider');
  }

  // Get spot by Id
  get(id: any) {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/spots/' + id)
    .map(res => res.json())
    .subscribe(data => {
    this.spot = data;
          resolve(this.spot);
          console.log(this.spot, data);
        });
    });
  }

  // Delete spot
  delete(input: any) {

  return new Promise(resolve => {
    this.http.delete('http://greencity.whisdol.de:8080/spot/' + input.id)
    .map(res => res.json())
    .subscribe(data => {
    this.spot = data;
          resolve(this.spot);
          console.log(this.spot, data);
        });
    });
  }

  // Create spot (or get existing with same name)
  create(input: any) {

  let headers = new Headers();
  headers.append('Content-Type', 'application/json');

  let body = {
    name: input.name,
    description: input.description,
    size: input.size,
    owner: input.owner,
    address: input.address
  };

  return new Promise(resolve => {
    this.http.post('http://greencity.whisdol.de:8080/spots/create', JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.spot = data;
          resolve(this.spot);
          console.log(this.spot, data);
        });
    });
  }

  // Search Spot
  search(input: any) {

  if (input.cityId != "") {
      this.searchParameter = 'search?cityId=' + input.cityId;
    }
    else if (input.cityName != "") {
      this.searchParameter = 'search?cityName=' + input.cityName;
    }

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/spots/' + this.searchParameter)
    // this.http.get('http://greencity.whisdol.de:8080/spots/search?cityId=2')
    .map(res => res.json())
    .subscribe(data => {
          this.spot = data;
          resolve(this.spot);
          console.log(this.spot, data);
        });
    });
  }

  }

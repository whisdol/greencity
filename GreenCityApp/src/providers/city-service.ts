import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the CityService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class CityService {

  cities: any;
  city: any;

  constructor(public http: Http) {
    console.log('Hello CityService Provider');
  }

  // Get all cities
  getAll() {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/cities')
    .map(res => res.json())
    .subscribe(data => {
    this.cities = data;
          resolve(this.cities);
          console.log(this.cities, data);
        });
    });
  }

  // Get city by Id
  get(input: any) {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/cities/' + input.id)
    .map(res => res.json())
    .subscribe(data => {
    this.city = data;
          resolve(this.city);
          console.log(this.city, data);
        });
    });
  }

  // Create city (or get existing with same name)
  create(input: any) {

  let headers = new Headers();
  headers.append('Content-Type', 'application/json');

  let body = {
    name: input.name
  };

  return new Promise(resolve => {
    this.http.post('http://greencity.whisdol.de:8080/cities/create', JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.city = data;
          resolve(this.city);
          console.log(this.city, data);
        });
    });
  }

}

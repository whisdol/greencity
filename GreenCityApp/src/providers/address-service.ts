import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the AddressService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class AddressService {

  address: any;

  constructor(public http: Http) {
    console.log('Hello AddressService Provider');
  }

  // Get address by Id
  get(input: any) {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/addresses/' + input.id)
    .map(res => res.json())
    .subscribe(data => {
    this.address = data;
          resolve(this.address);
          console.log(this.address, data);
        });
    });
  }

  // Update address
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
    this.http.put('http://greencity.whisdol.de:8080/addresses/' + input.id, JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.address = data;
          resolve(this.address);
          console.log(this.address, data);
        });
    });
  }

  // Delete address
  delete(input: any) {

  return new Promise(resolve => {
    this.http.delete('http://greencity.whisdol.de:8080/addresses/' + input.id)
    .map(res => res.json())
    .subscribe(data => {
    this.address = data;
          resolve(this.address);
          console.log(this.address, data);
        });
    });
  }

  // Create address
  // (or get existing with same roadName, houseNumber, cityId and postCode)
  create(input: any) {

  let headers = new Headers();
  headers.append('Content-Type', 'application/json');

  let body = {
    roadName: input.roadName,
    houseNumber: input.houseNumber,
    postCode: input.postCode,
    latitude: input.latitude,
    longitude: input.longitude,
    city: input.city
  };

  return new Promise(resolve => {
    this.http.post('http://greencity.whisdol.de:8080/addresses/create', JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.address = data;
          resolve(this.address);
          console.log(this.address, data);
        });
    });
  }

}

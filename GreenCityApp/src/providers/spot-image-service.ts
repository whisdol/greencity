import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the SpotImageService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class SpotImageService {

  image: any;
  images: any;

  constructor(public http: Http) {
    console.log('Hello SpotImageService Provider');
  }

  // Get spot images
  get(id: any) {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/spots/' + id + '/images')

    .map(res => res.json())
    .subscribe(data => {
    this.images = data;
          resolve(this.images);
          console.log(this.images, data);
        });
    });
  }

  // Add spot image
  add(input: any) {

  let headers = new Headers();
  headers.append('Content-Type', 'application/json');

  let body = {
    id: input.id
  };

  return new Promise(resolve => {
    this.http.post('http://greencity.whisdol.de:8080/spots/' + input.id + '/images/add', JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.image = data;
          resolve(this.image);
          console.log(this.image, data);
        });
    });
  }

  }

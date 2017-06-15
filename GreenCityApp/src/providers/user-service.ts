import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the UserService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class UserService {

  user: any;

  constructor(public http: Http) {
    console.log('Hello UserService Provider');
  }

  // Get user by Id
  get(input: any) {

  return new Promise(resolve => {
    this.http.get('http://greencity.whisdol.de:8080/users/' + input.id)
    .map(res => res.json())
    .subscribe(data => {
    this.user = data;
          resolve(this.user);
          console.log(this.user, data);
        });
    });
  }

  // Delete user
  delete(input: any) {

  return new Promise(resolve => {
    this.http.delete('http://greencity.whisdol.de:8080/users/' + input.id)
    .map(res => res.json())
    .subscribe(data => {
    this.user = data;
          resolve(this.user);
          console.log(this.user, data);
        });
    });
  }

  // Create user
  // (or get existing with same userName)
  create(input: any) {

  let headers = new Headers();
  headers.append('Content-Type', 'application/json');

  let body = {
    userName: input.userName,
    password: input.password,
    city: input.city,
    avatar: input.avatar
  };

  return new Promise(resolve => {
    this.http.post('http://greencity.whisdol.de:8080/users/create', JSON.stringify(body), {headers: headers})
    .map(res => res.json())
    .subscribe(data => {
    this.user = data;
          resolve(this.user);
          console.log(this.user, data);
        });
    });
  }

}

import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Item } from '../../models/item';

@Injectable()
export class Items {
  items: Item[] = [];

  defaultItem: any = {
    "name": "Max",
    "profilePic": "assets/img/max-avatar.jpg",
    "about": "Köln",
  };


  constructor(public http: Http) {
    let items = [
     {
        "name": "Alexander",
        "profilePic": "assets/img/alexander-avatar.jpg",
        "about": "Köln"
      },
      {
         "name": "Bastian",
         "profilePic": "assets/img/bastian-avatar.jpg",
         "about": "Köln"
       },
       {
          "name": "Chiara",
          "profilePic": "assets/img/chiara-avatar.jpg",
          "about": "Köln"
        },
       {
         "name": "Laura",
         "profilePic": "assets/img/laura-avatar.jpg",
         "about": "Köln"
       },
       {
         "name": "Marie",
         "profilePic": "assets/img/marie-avatar.jpg",
         "about": "Köln"
       },
       {
          "name": "Max",
          "profilePic": "assets/img/max-avatar.jpg",
          "about": "Köln"
        }
     ];

     for(let item of items) {
       this.items.push(new Item(item));
     }
  }

  query(params?: any) {
    if(!params) {
      return this.items;
    }

    return this.items.filter((item) => {
      for(let key in params) {
        let field = item[key];
        if(typeof field == 'string' && field.toLowerCase().indexOf(params[key].toLowerCase()) >= 0) {
          return item;
        } else if(field == params[key]) {
          return item;
        }
      }
      return null;
    });
  }

  add(item: Item) {
    this.items.push(item);
  }

  delete(item: Item) {
    this.items.splice(this.items.indexOf(item), 1);
  }
}

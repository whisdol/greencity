import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

/*
  Generated class for the Cards page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-wissen-top',
  templateUrl: 'wissen-top.html'
})
export class WissenTopPage {
  cardItems: any[];

  constructor(public navCtrl: NavController) {
    this.cardItems = [
      {
        user: {
          avatar: 'assets/img/laura-avatar.jpg',
          name: 'Laura'
        },
        date: 'vor 6 Stunde',
        image: 'assets/img/advance-card-bttf.png',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.',
      },
      {
        user: {
          avatar: 'assets/img/max-avatar.jpg',
          name: 'Max'
        },
        date: 'vor 7 Stunden',
        image: 'assets/img/advance-card-jp.jpg',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.'
      }
    ];

  }
}

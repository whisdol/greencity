import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

/*
  Generated class for the Cards page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-wissen-neu',
  templateUrl: 'wissen-neu.html'
})
export class WissenNeuPage {
  cardItems: any[];

  constructor(public navCtrl: NavController) {
    this.cardItems = [
      {
        user: {
          avatar: 'assets/img/tim-avatar.jpg',
          name: 'Tim'
        },
        date: 'vor 1 Stunde',
        image: 'assets/img/advance-card-bttf.png',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr.',
      },
      {
        user: {
          avatar: 'assets/img/chiara-avatar.jpg',
          name: 'Chiara'
        },
        date: 'vor 1 Stunde',
        image: 'assets/img/advance-card-jp.jpg',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.'
      }
    ];

  }
}

import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

/*
  Generated class for the Cards page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-wissen-experten',
  templateUrl: 'wissen-experten.html'
})
export class WissenExpertenPage {
  cardItems: any[];

  constructor(public navCtrl: NavController) {
    this.cardItems = [
      {
        user: {
          avatar: 'assets/img/bastian-avatar.jpg',
          name: 'Bastian'
        },
        date: 'vor 1 Stunde',
        image: 'assets/img/beet-8.png',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.',
      },
      {
        user: {
          avatar: 'assets/img/christina-avatar.jpg',
          name: 'Christina'
        },
        date: 'vor 4 Stunden',
        image: 'assets/img/beet-2.png',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.'
      }
    ];

  }
}

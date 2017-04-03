import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

/*
  Generated class for the Cards page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-wissen-beliebt',
  templateUrl: 'wissen-beliebt.html'
})
export class WissenBeliebtPage {
  cardItems: any[];

  constructor(public navCtrl: NavController) {
    this.cardItems = [
      {
        user: {
          avatar: 'assets/img/marie-avatar.jpg',
          name: 'Marie'
        },
        date: 'vor 2 Stunden',
        image: 'assets/img/advance-card-bttf.png',
        content: 'Hallo Leute! Hier möchte ich euch zeigen, wie ich das tolle Blumenbeet an der Feldstraße verschönert habe.',
      },
      {
        user: {
          avatar: 'assets/img/alexander-avatar.jpg',
          name: 'Alexander'
        },
        date: 'vor 3 Stunden',
        image: 'assets/img/advance-card-tmntr.jpg',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.'
      },
      {
        user: {
          avatar: 'assets/img/laura-avatar.jpg',
          name: 'Laura'
        },
        date: 'vor 3 Stunden',
        image: 'assets/img/advance-card-jp.jpg',
        content: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.'
      }
    ];

  }
}

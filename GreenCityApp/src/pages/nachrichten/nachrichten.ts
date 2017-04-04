import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

/*
  Generated class for the Cards page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-nachrichten',
  templateUrl: 'nachrichten.html'
})
export class NachrichtenPage {
  cardItems: any[];

  constructor(public navCtrl: NavController) {
    this.cardItems = [
      {
        user: {
          avatar: 'assets/img/tim-avatar.jpg',
        },
        date: 'vor 2 Tagen',
        content: 'Tim und 7 weiteren Personen gefällt dein Beitrag "Gemüsegarten an der Berliner Allee".',
      },
      {
        user: {
          avatar: 'assets/img/chiara-avatar.jpg',
        },
        date: 'vor 2 Tagen',
        content: 'Chiara folgt dir jetzt.'
      },
      {
        user: {
          avatar: 'assets/img/chiara-avatar.jpg',
        },
        date: 'vor 2 Tagen',
        content: 'Chiara und 7 weiteren Personen gefällt dein Beitrag "Wie pflege ich am besten weiße Callas?".'
      },
      {
        user: {
          avatar: 'assets/img/laura-avatar.jpg',
          name: 'Laura'
        },
        date: 'vor 3 Tagen',
        content: 'Laura und 5 weitere Personen haben deinen Beitrag "Wie pflege ich am besten weiße Callas?" kommentiert.'
      },
      {
        user: {
          avatar: 'assets/img/max-avatar.jpg',
          name: 'Max'
        },
        date: 'vor 4 Tagen',
        content: 'Dein Freund Max hat einen neuen Beitrag hinzugefügt.'
      }
    ];

  }
}

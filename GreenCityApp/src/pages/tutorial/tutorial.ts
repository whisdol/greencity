import { Component } from '@angular/core';

import { MenuController, NavController } from 'ionic-angular';

import { WelcomePage } from '../welcome/welcome';

import { TranslateService } from 'ng2-translate/ng2-translate';



export interface Slide {
  title: string;
  description: string;
  image: string;
}

@Component({
  selector: 'page-tutorial',
  templateUrl: 'tutorial.html'
})
export class TutorialPage {
  slides: Slide[];
  showSkip = true;

  constructor(public navCtrl: NavController, public menu: MenuController, translate: TranslateService) {
    translate.get(["TUTORIAL_SLIDE1_TITLE",
                   "TUTORIAL_SLIDE1_DESCRIPTION",
                   "TUTORIAL_SLIDE2_TITLE",
                   "TUTORIAL_SLIDE2_DESCRIPTION",
                   "TUTORIAL_SLIDE3_TITLE",
                   "TUTORIAL_SLIDE3_DESCRIPTION",
                   "TUTORIAL_SLIDE4_TITLE",
                   "TUTORIAL_SLIDE4_DESCRIPTION",
    ])
    .subscribe((values) => {
      console.log('Loaded values', values);
      this.slides = [
        {
          title: "Herzlich Willkommen bei<br/>Green City.",
          description: "Vielen Dank, dass du dich für Green City entschieden hast.",
          image: 'assets/img/004-sprout.png',
        },
        {
          title: "Gemeinsam stark.",
          description: "Interagiere mit anderen Nutzern, um Grünflächen zu pflegen, dich mit Experten auszutauschen und nützliche Tipps rund um die Pflege von Spots zu erhalten.",
          image: 'assets/img/002-chat.png',
        },
        {
          title: "Alles wird grün!",
          description: "Bring den Spross zum wachsen - mach deine Stadt zu einer grünen Oase und lass sie zur umweltfreundlichsten Stadt des Landes werden.",
          image: 'assets/img/001-badge.png',
        }
      ];
    });
  }

  startApp() {
    this.navCtrl.setRoot(WelcomePage, {}, {
      animate: true,
      direction: 'forward'
    });
  }

  onSlideChangeStart(slider) {
    this.showSkip = !slider.isEnd;
  }

  ionViewDidEnter() {
    // the root left menu should be disabled on the tutorial page
    this.menu.enable(false);
  }

  ionViewWillLeave() {
    // enable the root left menu when leaving the tutorial page
    this.menu.enable(true);
  }

}

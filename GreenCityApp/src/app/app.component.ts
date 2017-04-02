import { Component, ViewChild } from '@angular/core';
import {Platform, Nav, Config} from 'ionic-angular';
import { StatusBar, Splashscreen } from 'ionic-native';

import { Settings } from '../providers/providers';

import { FirstRunPage } from '../pages/pages';
//import { CardsPage } from '../pages/cards/cards';
//import { ContentPage } from '../pages/content/content';
import { LoginPage } from '../pages/login/login';
//import { SignupPage } from '../pages/signup/signup';
//import { TabsPage } from '../pages/tabs/tabs';
import { TutorialPage } from '../pages/tutorial/tutorial';
import { WelcomePage } from '../pages/welcome/welcome';
//import { ListMasterPage } from '../pages/list-master/list-master';
//import { MenuPage } from '../pages/menu/menu';
//import { SettingsPage } from '../pages/settings/settings';
//import { SearchPage } from '../pages/search/search';
import { MapPage } from '../pages/map/map';
import { WissenPage } from '../pages/wissen/wissen';
import { WissenBeliebtPage } from '../pages/wissen-beliebt/wissen-beliebt';
import { WissenNeuPage } from '../pages/wissen-neu/wissen-neu';
import { WissenTopPage } from '../pages/wissen-top/wissen-top';
import { WissenExpertenPage } from '../pages/wissen-experten/wissen-experten';
import { WissenSuchePage } from '../pages/wissen-suche/wissen-suche';
import { CommunityPage } from '../pages/community/community';
import { CommunityChatPage } from '../pages/community-chat/community-chat';
import { CommunityProfilPage } from '../pages/community-profil/community-profil';
import { CommunityUgalleryPage } from '../pages/community-ugallery/community-ugallery';
import { NachrichtenPage } from '../pages/nachrichten/nachrichten';
import { EinstellungenPage } from '../pages/einstellungen/einstellungen';
import { UeberPage } from '../pages/ueber/ueber';

import { TranslateService } from 'ng2-translate/ng2-translate';

@Component({
  template: `<ion-menu [content]="content">
    <ion-header>
      <ion-toolbar>
        <ion-title>Green City</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <ion-list>
        <button menuClose ion-item *ngFor="let p of pages" (click)="openPage(p)">
          {{p.title}}
        </button>
      </ion-list>
    </ion-content>

  </ion-menu>
  <ion-nav #content [root]="rootPage"></ion-nav>`
})
export class MyApp {
  rootPage = FirstRunPage;

  @ViewChild(Nav) nav: Nav;

  pages: any[] = [
    { title: 'Spots', component: MapPage },
    { title: 'Wissen', component: WissenPage },
    { title: 'Community', component: CommunityPage },
    { title: 'Nachrichten', component: NachrichtenPage },
    { title: 'Einstellungen', component: EinstellungenPage },
    { title: 'Über', component: UeberPage }
  ]

  constructor(translate: TranslateService, platform: Platform, settings: Settings, config: Config) {
    // Set the default language for translation strings, and the current language.
    translate.setDefaultLang('de');
    translate.use('de')

    translate.get(['BACK_BUTTON_TEXT']).subscribe(values => {
      config.set('ios', 'backButtonText', values.BACK_BUTTON_TEXT);
    });

    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      StatusBar.styleDefault();
      Splashscreen.hide();
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }
}

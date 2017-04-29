import { NgModule, ErrorHandler } from '@angular/core';
import { Http } from '@angular/http';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { Storage, IonicStorageModule } from '@ionic/storage';
import { Ionic2RatingModule } from 'ionic2-rating';

import { MyApp } from './app.component';

import { LoginPage } from '../pages/login/login';
import { SignupPage } from '../pages/signup/signup';
import { TutorialPage } from '../pages/tutorial/tutorial';
import { WelcomePage } from '../pages/welcome/welcome';
import { ItemCreatePage } from '../pages/item-create/item-create';
import { ItemDetailPage } from '../pages/item-detail/item-detail';
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

import { User } from '../providers/user';
import { Api } from '../providers/api';
import { Settings } from '../providers/settings';
import { Items } from '../mocks/providers/items';

import { TranslateModule, TranslateLoader, TranslateStaticLoader } from 'ng2-translate/ng2-translate';

// The translate loader needs to know where to load i18n files
// in Ionic's static asset pipeline.
export function createTranslateLoader(http: Http) {
  return new TranslateStaticLoader(http, './assets/i18n', '.json');
}

export function provideSettings(storage: Storage) {
  /**
   * The Settings provider takes a set of default settings for your app.
   *
   * You can add new settings options at any time. Once the settings are saved,
   * these values will not overwrite the saved values (this can be done manually if desired).
   */
  return new Settings(storage, {
    option1: true,
    option2: false,
    option4: "Köln"
  });
}


/**
 * The Pages array lists all of the pages we want to use in our app.
 * We then take these pages and inject them into our NgModule so Angular
 * can find them. As you add and remove pages, make sure to keep this list up to date.
 */
let pages = [
  MyApp,
  LoginPage,
  SignupPage,
  TutorialPage,
  WelcomePage,
  ItemDetailPage,
  ItemCreatePage,
  MapPage,
  WissenPage,
  WissenBeliebtPage,
  WissenNeuPage,
  WissenTopPage,
  WissenExpertenPage,
  WissenSuchePage,
  CommunityPage,
  CommunityChatPage,
  CommunityProfilPage,
  CommunityUgalleryPage,
  NachrichtenPage,
  EinstellungenPage,
  UeberPage,
];

export function declarations() {
  return pages;
}

export function entryComponents() {
  return pages;
}

export function providers() {
  return [
    User,
    Api,
    Items,

    { provide: Settings, useFactory: provideSettings, deps: [ Storage ] },
    // Keep this to enable Ionic's runtime error handling during development
    { provide: ErrorHandler, useClass: IonicErrorHandler }
  ];
}

@NgModule({
  declarations: declarations(),
  imports: [
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot(),
    TranslateModule.forRoot({
      provide: TranslateLoader,
      useFactory: (createTranslateLoader),
      deps: [Http]
    }),
    Ionic2RatingModule
  ],
  bootstrap: [IonicApp],
  entryComponents: entryComponents(),
  providers: providers()
})
export class AppModule {}

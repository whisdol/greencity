import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { TranslateService } from 'ng2-translate/ng2-translate';

import { Tab6Root } from '../pages';
import { Tab7Root } from '../pages';
import { Tab8Root } from '../pages';

@Component({
  selector: 'page-community',
  templateUrl: 'community.html'
})
export class CommunityPage {
  tab6Root: any = Tab6Root;
  tab7Root: any = Tab7Root;
  tab8Root: any = Tab8Root;

  tab6Title = " ";
  tab7Title = " ";
  tab8Title = " ";

  constructor(public navCtrl: NavController, public translateService: TranslateService) {
    translateService.get(['TAB1_TITLE', 'TAB2_TITLE', 'TAB3_TITLE']).subscribe(values => {
      this.tab6Title = "Profil";
      this.tab7Title = "Chat";
      this.tab8Title = "Buddys";
    });
  }
}

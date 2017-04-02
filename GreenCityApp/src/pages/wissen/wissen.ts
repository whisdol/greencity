import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { TranslateService } from 'ng2-translate/ng2-translate';

import { Tab1Root } from '../pages';
import { Tab2Root } from '../pages';
import { Tab3Root } from '../pages';
import { Tab4Root } from '../pages';
import { Tab5Root } from '../pages';

@Component({
  selector: 'page-wissen',
  templateUrl: 'wissen.html'
})
export class WissenPage {
  tab1Root: any = Tab1Root;
  tab2Root: any = Tab2Root;
  tab3Root: any = Tab3Root;
  tab4Root: any = Tab4Root;
  tab5Root: any = Tab5Root;

  tab1Title = " ";
  tab2Title = " ";
  tab3Title = " ";
  tab4Title = " ";
  tab5Title = " ";

  constructor(public navCtrl: NavController, public translateService: TranslateService) {
    translateService.get(['TAB1_TITLE', 'TAB2_TITLE', 'TAB3_TITLE']).subscribe(values => {
      this.tab1Title = "Beliebt";
      this.tab2Title = "Neu";
      this.tab3Title = "Top";
      this.tab4Title = "Experten";
      this.tab5Title = "Suche";
    });
  }
}

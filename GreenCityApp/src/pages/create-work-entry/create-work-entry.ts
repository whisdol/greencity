import { Component } from '@angular/core';
import { NavController, NavParams, ToastController } from 'ionic-angular';
import { SpotWorkEntryService } from '../../providers/spot-work-entry-service';

/*
  Generated class for the CreateWorkEntry page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-create-work-entry',
  templateUrl: 'create-work-entry.html',
  providers: [SpotWorkEntryService]
})
export class CreateWorkEntryPage {

	spotId: number;
  workEntryTitle: any;
  workEntryDescription: any;
  spotWorkEntry: any;
  input: any;
  createWorkEntryTitle: string;
  createWorkEntryDescription: string;

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              public spotWorkEntryService: SpotWorkEntryService,
              public toastCtrl: ToastController) {

  this.spotId = navParams.get('id');

}

ionViewDidLoad() {
  console.log('ionViewDidLoad CreateWorkEntryPage');
}

saveWorkEntry() {

  this.input = {
    user: '12',
    spotId: this.spotId,
    shortDescription: this.createWorkEntryTitle,
    description: this.createWorkEntryDescription
  };

  // Create work entry
  this.spotWorkEntryService.create(this.input)
    .then(entry => {
      this.spotWorkEntry = entry;
      });

  this.navCtrl.pop();

if (this.spotWorkEntry =! null) {

  let toast = this.toastCtrl.create({
    message: "Der Arbeitseintrag wurde gesichert",
    duration: 2000,
    position: 'bottom'
  });
  toast.present();
  }
}

abort() {
  this.navCtrl.pop();
}

readWorkEntryTitle(value) {
    this.workEntryTitle = value;
  }

  readWorkEntryDescription(value) {
      this.workEntryDescription = value;
    }
}

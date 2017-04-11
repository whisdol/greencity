import { Component } from '@angular/core';
import { NavController, NavParams, ViewController, ModalController} from 'ionic-angular';

import { SpotDetailPage } from '../spot-detail/spot-detail'

/*
	Generated class for the SpotPreview page.

	See http://ionicframework.com/docs/v2/components/#navigation for more info on
	Ionic pages and navigation.
*/
@Component({
	selector: 'page-spot-preview',
	templateUrl: 'spot-preview.html'
})
export class SpotPreviewPage {
	spot: Object

	constructor(public navCtrl: NavController, public navParams: NavParams, public viewCtrl: ViewController, public modCtrl: ModalController) {
		this.spot = navParams.data
	}

	ionViewDidLoad() {
		console.log('ionViewDidLoad SpotPreviewPage');
	}


	dismiss() {
		this.viewCtrl.dismiss();
	}

	openSpotDetail(id: number) {
		let detail = this.modCtrl.create(SpotDetailPage, {'id': id});
		detail.present();
		// this.navCtrl.push(SpotDetailPage, {
		// 	id: id
		// });
	}

}

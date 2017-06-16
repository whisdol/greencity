import { Component } from '@angular/core';
import { NavController, NavParams, ViewController } from 'ionic-angular';
import { SpotService } from '../../providers/spot-service';
import { SpotImageService } from '../../providers/spot-image-service';
import { SpotWorkEntryService } from '../../providers/spot-work-entry-service';
import { CreateWorkEntryPage } from '../create-work-entry/create-work-entry';

/*
  Generated class for the SpotDetail page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/

@Component({
  selector: 'page-spot-detail',
  templateUrl: 'spot-detail.html',
  providers: [SpotService, SpotImageService, SpotWorkEntryService, CreateWorkEntryPage]
})

export class SpotDetailPage {
	spotId: number;
	spot: Object;
	workLog: Array<Object>;

  public spotDetail = {};
  public spotOwner = {};
  public spotAddress = {};
  public spotCity = {};
  public spotImages: any;
  public spotWorkEntries: any;

	constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public viewCtrl: ViewController,
    public spotService: SpotService,
    public spotImageService: SpotImageService,
    public spotWorkEntryService: SpotWorkEntryService,
    public createWorkEntryPage: CreateWorkEntryPage) {

		this.spotId = navParams.get('id');

    // Get spot by Id
    this.spotService.get(this.spotId)
      .then(spot => {
        this.spotDetail = spot;
        this.spotOwner = spot['owner'];
        this.spotAddress = spot['address'];
        this.spotCity = this.spotAddress['city'];
        });

    // Get spot images
    this.spotImageService.get(this.spotId)
      .then(images => {
        this.spotImages = images;
        });

    // Get work entries for spot
    this.spotWorkEntryService.getAll(this.spotId)
      .then(entries => {
        this.spotWorkEntries = entries;
        });

  	}

    openCreateWorkEntry() {
      this.navCtrl.push(CreateWorkEntryPage);
    }

	ionViewDidLoad() {
		console.log('ionViewDidLoad SpotDetailPage');
	}

	ratingChange(event: any) {
		console.log('Rated something, tbd');
	}

}

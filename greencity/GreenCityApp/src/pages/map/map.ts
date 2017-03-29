/* import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular'; */

/*
  Generated class for the SpotsJS page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
  */
/* @Component({
  selector: 'page-map',
  templateUrl: 'map.html'
})
export class MapPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad MapPage');
  }

} */


import {
	Component,
	ElementRef,
	ViewChild
} from '@angular/core';
import {
	NavController
} from 'ionic-angular';
import {
	Geolocation
} from 'ionic-native';

declare var google;

@Component({
	selector: 'page-map',
	templateUrl: 'map.html'
})
export class MapPage {

	@ViewChild('map') mapElement: ElementRef;

	map: any;
	mapInitialised: boolean = false;
	apiKey: string = "AIzaSyBu9-88Bzcq4LlXqeQgXgT77iCW5q6X5Gw";

	constructor(public navCtrl: NavController) {
		this.loadGoogleMaps();
	}

	loadGoogleMaps() {

		if (typeof google == "undefined" || typeof google.maps == "undefined") {

			console.log("online, loading map");

			//Load the SDK
			window['mapInit'] = () => {
				this.initMap();
				this.enableMap();
			}

			let script = document.createElement("script");
			script.id = "googleMaps";

			if (this.apiKey) {
				script.src = 'http://maps.google.com/maps/api/js?key=' + this.apiKey + '&callback=mapInit';
			} else {
				script.src = 'http://maps.google.com/maps/api/js?callback=mapInit';
			}

			document.body.appendChild(script);

		} else {


			console.log("showing map");
			this.initMap();
			this.enableMap();

		}

	}

	initMap() {

		this.mapInitialised = true;

		Geolocation.getCurrentPosition().then((position) => {

			let latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

			let mapOptions = {
				center: latLng,
				zoom: 15,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			}

			this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

		});

	}

	disableMap() {
		console.log("disable map");
	}

	enableMap() {
		console.log("enable map");
	}

}

import { Component, ElementRef, ViewChild } from '@angular/core';
import { NavController } from 'ionic-angular';
import { SpotDetailPage } from '../spot-detail/spot-detail'
import { SpotService } from '../../providers/spot-service';

declare var google;

@Component({
	selector: 'page-map',
	templateUrl: 'map.html',
	providers: [SpotService]
})
export class MapPage {

	public markers: any;

	input: {cityId: string} = {
				cityId: '2'
			};

	// input = {
	// 			cityId: '2',
	// 			cityName: ''
	// 		};

	@ViewChild('map') mapElement: ElementRef;

	map: any;
	mapInitialised: boolean = false;
	apiKey: string = "AIzaSyBu9-88Bzcq4LlXqeQgXgT77iCW5q6X5Gw";

	constructor(public navCtrl: NavController,
						  public spotService: SpotService ) {
		console.log("Initializing MapPage, called Constructor");
		this.loadGoogleMaps();
	}

	loadGoogleMaps() {

		console.log("loading map");

		//Load the SDK
		window['mapInit'] = () => {
			this.initMap();
			this.enableMap();
			this.showMarkers();
		}

		let script = document.createElement("script");
		script.id = "googleMaps";

		if (this.apiKey) {
			script.src = 'http://maps.google.com/maps/api/js?key=' + this.apiKey + '&callback=mapInit';
		} else {
			script.src = 'http://maps.google.com/maps/api/js?callback=mapInit';
		}

		document.body.appendChild(script);

	}

	initMap() {

		this.mapInitialised = true;

		let centerLatLng = new google.maps.LatLng(50.954375, 6.952084);

		let mapOptions = {
			center: centerLatLng,
			zoom: 15,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		}

		this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

	}

	disableMap() {
		console.log("disable map");
	}

	enableMap() {
		console.log("enable map");
	}

	showMarkers() {
		console.log("adding markers to map");
		let spotMarker = 'assets/img/spotmarker.png';

	    this.spotService.search(this.input)
	      .then(spot => {
	        this.markers = spot;

					this.markers.forEach((marker) => {
						let pos = new google.maps.LatLng(marker.address['latitude'], marker.address['longitude']);
						let m = new google.maps.Marker({
							position: pos,
							icon: spotMarker,
							title: marker['name']
						});
						m.setMap(this.map);

						let localNavCtrl = this.navCtrl;
						m.addListener('click', function() {
							localNavCtrl.push(SpotDetailPage, { id: marker['id'] })
						});
					})

	      });

	}
}

import {
	Component,
	ElementRef,
	ViewChild
} from '@angular/core';
import { NavController, Modal, ModalController } from 'ionic-angular';

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

	constructor(public navCtrl: NavController, private modCtrl: ModalController) {
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

		let markers = [{'id': 12345, 'name': 'Escher Gärtchen', 'description': 'Das kleine Gärtchen an der Escherstr.', 'rating': 3.5, 'owner': 'Franz53', 'img': 'assets/img/spot_image.png', 'city': 'Köln', 'cityrank': 66, 'lat': 50.955639, 'lng': 6.948812},
						{'id': 12346, 'name': 'Wickraths Strauß', 'description': 'Blümchen! Schöne Blumen zu jeder Jahreszeit.', 'rating': 4.5, 'owner': 'Sandra', 'img': 'assets/img/spot_image.png', 'city': 'Köln', 'cityrank': 13, 'lat': 50.954375, 'lng': 6.952084},
						{'id': 12347, 'name': 'Kasparle', 'description': 'Gelbe Pflänzlein bevorzugt.', 'rating': 3.5, 'owner': 'Günni', 'img': 'assets/img/spot_image.png', 'lat': 50.953486, 'city': 'Köln', 'cityrank': 65, 'lng': 6.953065},
						{'id': 12348, 'name': 'Ringschen', 'description': 'Direkt am Hansaring: die kleine Oase.', 'rating': 5, 'owner': 'Haxn Herb', 'img': 'assets/img/spot_image.png', 'city': 'Köln', 'cityrank': 1, 'lat': 50.949931, 'lng': 6.955110},];

		markers.forEach((marker) => {
			let pos = new google.maps.LatLng(marker['lat'], marker['lng']);
			let m = new google.maps.Marker({
				position: pos,
				icon: spotMarker,
				title: marker['name']
			});
			m.setMap(this.map);
			var info = this.createInfoWindow(marker);
			m.addListener('click', function() {
				info.open(this.map, m);
			})
		});
	}

	createInfoWindow(marker: any) {
		// Returns a Google Maps Info Window to associate with markers
		var contentString = '<ion-content class="spot-preview">' +
			'	<h1>' + marker['name'] + '</h1><br>' +
			'	<span>Spot-Verantwortliche/r: <strong>' + marker['owner'] + '</strong></span><br>' +
			'	<img src="' + marker['img'] + '"><br>' +
			'	<span class="city-rating">Top #' + marker['cityrank'] + ' in ' + marker['city'] + '.</span>' +
			'</ion-content>'

		return new google.maps.InfoWindow({
			content: contentString
		});
	}

}

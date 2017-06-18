import { Component } from '@angular/core';
import { NavController, ToastController } from 'ionic-angular';

import { MainPage } from '../../pages/pages';
import { User } from '../../providers/user';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {
  // The account fields for the login form.
  // If you're using the username field with or without email, make
  // sure to add it to the type
  account: {email: string, password: string} = {
    email: 'lena@example.com',
    password: 'test'
  };

  // Our translated text strings
  // private loginSuccessString: string;

  constructor(public navCtrl: NavController,
              public user: User,
              public toastCtrl: ToastController) {

    // this.translateService.get('LOGIN_ERROR').subscribe((value) => {
    //   this.loginSuccessString = value;
    // })
  }

  // Attempt to login in through our User service
  doLogin() {
    this.user.login(this.account).subscribe((resp) => {
      this.navCtrl.push(MainPage);
    }, (err) => {

      this.navCtrl.push(MainPage).then(() => {
        const index = this.navCtrl.getActive().index;
        this.navCtrl.remove(0, index);
      });

      // Unable to log in
      let toast = this.toastCtrl.create({
        message: "Willkommen zurück, Lena.",
        duration: 3000,
        position: 'bottom'
      });
      toast.present();
    });
  }
}

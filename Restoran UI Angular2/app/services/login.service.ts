import { IUlogovan } from './../models/ulogovan';

import { Injectable, OnInit } from '@angular/core';
import { Http, Response, URLSearchParams } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';

import { Observable, BehaviorSubject } from 'rxjs/Rx';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IKorisnik } from '../models/korisnik';
import { Uloga } from '../models/uloga';
import { LoginResponse } from '../models/loginResponse';

import {Notificator} from './notification.service';

import { Config } from '../app.config';

@Injectable()
export class LoginService {

    public emailUlogovanog : string = 'email0';

    private bSubject: BehaviorSubject<any> = new BehaviorSubject<IUlogovan>(null);
    private _registerUrl = Config.BackendUrl + '/auth/register';
    private _activateUrl = Config.BackendUrl + '/auth/activateAccount';
    private _loginUrl = Config.BackendUrl + '/auth/login';
    private _logoutUrl = Config.BackendUrl + '/auth/logout';

    ulogovan : Observable<IUlogovan> = this.bSubject.asObservable();

    constructor(private _http: Http, private _notificator: Notificator, private _router: Router) { }

    loginKorisnika(email: string, password: string): void{
        this._http.get(this._loginUrl)
            .map((response: Response) => <any> response.json())
            .catch(this.handleError)
            .subscribe(response  => {
                if(response.success){
                     this.bSubject.next({ ime : username, uloga: response.uloga });
                }
            });
        this.bSubject.next({ email : email, ime : email});
    }

    logoutKorisnika(){
        this.bSubject.next(null);
    }

    registerKorisnika(email : string, password : string, ime: string, prezime : string){

        let params: URLSearchParams = new URLSearchParams();
        params.set('email',email);
        params.set('password', password);
        
         this._http.post(this._registerUrl, { email : email, password: password, ime : ime, prezime: prezime})
            .map((response: Response) => <any> response.json())
            .catch(this.handleError)
            .subscribe(response  => {
                if(response.success){
                    this._notificator.notifySuccess("Registrovan, proverite email kako bi ste aktivirali nalog");
                     this._router.navigate(['/login']);
                }else{
                    this._notificator.notifyInfo(response.description);
                }
            });
    }

    activateKorisnika(token : string){
        this._http.get(this._activateUrl + "?tokenString=" + token)
            .map((response: Response) => <any> response.json())
            .catch(this.handleError)
            .subscribe(response  => {
                 if(response.success){
                    this._notificator.notifySuccess("Aktiviran :)), mozete da se ulogujete na vas nalog");
                    this._router.navigate(['/login']);
                }else{
                    this._notificator.notifyInfo(response.object);
                }
            });
    }

    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}

import { Injectable, OnInit } from '@angular/core';
import { Http, Response, URLSearchParams } from '@angular/http';

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
export class LoginService implements OnInit {

    public emailUlogovanog : string = 'email0';

    private _restoraniUrl = 'api/loginResponse.json';
    private bSubject: BehaviorSubject<any> = new BehaviorSubject<any>({ ime : "Neko"});
    private _registerUrl = Config.BackendUrl + '/auth/register';

    ulogovan : Observable<any> = this.bSubject.asObservable();

    constructor(private _http: Http, private _notificator: Notificator) { }

    ngOnInit(): void{
        //provera kesiranog
    }

    loginKorisnika(email: string, password: string): void{
        // this._http.get(this._restoraniUrl)
        //     .map((response: Response) => <LoginResponse> response.json())
        //     .catch(this.handleError)
        //     .subscribe(response  => {
        //         if(response.success){
        //              this.bSubject.next({ ime : username, uloga: response.uloga });
        //         }
        //     });
        this.bSubject.next({ email : email, ime : email});
    }

    logoutKorisnika(){
        this.bSubject.next(null);
    }

    registerKorisnika(email : string, password : string){

        let params: URLSearchParams = new URLSearchParams();
        params.set('email',email);
        params.set('password', password);
        
         this._http.get(this._registerUrl + "?email=" + email + "&password=" + password)
            .map((response: Response) => <boolean> response.json())
            .catch(this.handleError)
            .subscribe(response  => {
                if(response.success){
                     this.bSubject.next({ email : email, uloga: "gost" });
                    this._notificator.notifySuccess("Registrovan");
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

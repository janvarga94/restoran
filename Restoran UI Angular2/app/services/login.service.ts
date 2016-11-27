import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable, BehaviorSubject } from 'rxjs/Rx';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IKorisnik } from '../models/korisnik';
import { Uloga } from '../models/uloga';
import { LoginResponse } from '../models/loginResponse';

@Injectable()
export class LoginService implements OnInit {

    private _restoraniUrl = 'api/loginResponse.json';
    private bSubject: BehaviorSubject<IKorisnik> = new BehaviorSubject<IKorisnik>(null); 

    ulogovan : Observable<IKorisnik> = this.bSubject.asObservable();   

    constructor(private _http: Http) { }

    ngOnInit(): void{
        //provera kesiranog
    }

    loginKorisnika(username: string, password: string): void{    
        this._http.get(this._restoraniUrl)
            .map((response: Response) => <LoginResponse> response.json())
            .catch(this.handleError)
            .subscribe(response  => {
                if(response.success){              
                     this.bSubject.next({ ime : username, uloga: response.uloga });
                }                   
            });   
    }

    logoutKorisnika(){
        this.bSubject.next(null);
    }

    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}

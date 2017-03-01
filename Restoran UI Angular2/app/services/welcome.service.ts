import {Injectable} from "@angular/core";
import {Notificator} from "./notification.service";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {IRestoran} from "../models/restoran";
import {IKorisnik} from "../models/korisnik";
/**
 * Created by Svetozar Stojkovic on 12/20/2016.
 */

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {IOcenaRestorana} from "../models/ocenaRestorana";


@Injectable()
export class WelcomeService {

    private _restorani_for_user_url = 'http://localhost:8080/resursi/restorani_for_user';

    constructor(private _http: Http, private _notificator: Notificator) { }

    getRestoraniForUser(email : string): Observable<any[]> {
        return this._http.get(this._restorani_for_user_url+"?email="+email)
            .map((response: Response) => {
                var restoraniOcena = <any[]> response.json();

                return restoraniOcena;
            })
            .catch(this.handleError);
    }

    postOcenaForRestoran(ocena : any){

        console.log(ocena.ocena);

        let ocenaUrl : string = 'http://localhost:8080/resursi/add_ocena_restoran';

        let creds = JSON.stringify(ocena);

        console.log(creds);

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        this._http.post(ocenaUrl, creds, {
            headers: headers
        })
            .subscribe(
                data => {

                },
                err => this.handleError(err.json().message),
                () => console.log('Authentication Complete')
            );
    }

    getOcenaForRestoran(id : number): Observable<any> {
        let ocenaUrl : string = 'http://localhost:8080/resursi/ocena_for_restoran';
        return this._http.get(ocenaUrl+"?id="+id)
            .map((response: Response) => {
                var restoraniOcena = <any> response.json();

                return restoraniOcena;
            })
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}

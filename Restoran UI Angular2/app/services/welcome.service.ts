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

    getRestoraniForUser(email : string): Observable<IRestoran[]> {
        return this._http.get(this._restorani_for_user_url+"?email="+email)
            .map((response: Response) => {
                var restorani = <IRestoran> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                return restorani;
            })
            .catch(this.handleError);
    }

    postOcenaForRestoran(ocena : IOcenaRestorana){

        let ocenaUrl : string = 'http://localhost:8080/resursi/add_ocena';

        let bodyString = JSON.stringify(ocena); // Stringify payload
        let headers      = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
        let options       = new RequestOptions({ headers: headers }); // Create a request option

        return this._http.post(this.commentsUrl, ocena, options) // ...using post request
            .map((res:Response) => res.json()) // ...and calling .json() on the response to return data
            .catch((error:any) => Observable.throw(error.json().error || 'Server error')); //...errors if any
    }

    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}

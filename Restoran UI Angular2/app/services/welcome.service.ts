import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Notificator} from "./notification.service";
import {Http, Response} from "@angular/http";
import {IRestoran} from "../models/restoran";
import {IKorisnik} from "../models/korisnik";
/**
 * Created by Svetozar Stojkovic on 12/20/2016.
 */

@Injectable()
export class WelcomeService {

    private _restorani_for_user_url = 'http://localhost:8080/resursi/restorani_for_user';

    constructor(private _http: Http, private _notificator: Notificator) { }

    getRestoraniForUser(email : string): Observable<IKorisnik> {
        return this._http.get(this._restorani_for_user_url+"?email="+email)
            .map((response: Response) => {
                var restorani = <IKorisnik> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                return restorani;
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

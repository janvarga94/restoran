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
import {Config} from "../app.config";


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

    postOcenaForRestoran(idRestorana : number, ocena : number, gostEmail : string) : Observable<any> {

        console.log("Mail: "+gostEmail);
        let ocenaUrl : string = Config.BackendUrl+'/resursi/add_ocena_restoran';

        return this._http.get(ocenaUrl+"?idRestorana="+idRestorana+"&ocena="+ocena+"&gostEmail="+encodeURIComponent(gostEmail))
            .map((response: Response) => {
                var restoraniOcena = <any> response.json();

                return restoraniOcena;
            })
            .catch(this.handleError);
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

    getJelaForRestoran(id : number, email : string): Observable<any> {
        let ocenaUrl : string = Config.BackendUrl+'/resursi/get_jela_for_restoran';
        return this._http.get(ocenaUrl+"?idRestorana="+id+"&email="+encodeURIComponent(email))
            .map((response: Response) => {
                return <any> response.json();
            })
            .catch(this.handleError);
    }

    oceniJelo(nazivJela : string, idRestorana : number, email : string, ocena : number): Observable<any> {
        let ocenaUrl : string = Config.BackendUrl+'/resursi/oceni_jelo';
        return this._http.get(ocenaUrl+"?nazivJela="+nazivJela+"&idRestorana="+idRestorana+"&email="+encodeURIComponent(email)+"&ocena="+ocena)
            .map((response: Response) => {
                return <any> response.json();
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

import {OnInit, Component} from "@angular/core";
import {Notificator} from "../services/notification.service";
import {RestoranService} from "../services/restorani.service";
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";
/**
 * Created by Stefan on 2/27/2017.
 */


@Component({
    selector: 'dodavanjereona',
    templateUrl: 'app/dodavanjeReona/dodavanjeReona.component.html',

})

export class DodatiReonComponent implements OnInit{

    id : number;
    opis : string;
    idRestorana : number;
    emailMenazderaRestorana : string;

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan => {
          if(ulogovan!= null)  this.emailMenazderaRestorana = ulogovan.email;
        });
    }

    constructor(private _notificator : Notificator,private _router: Router,private _restoranService : RestoranService,private _loginService : LoginService) {
        /*  this._restoranService.getManagerRestoranID(this.emailMenazderaRestorana).subscribe((id:any)=>{
         this.idRestorana = id;
         console.log(this.idRestorana);
         }); */

    }

    addReon(){
        this._restoranService.addReon({ emailMenadzeraRestorana :this.emailMenazderaRestorana, id : this.id, opis : this.opis}).subscribe(response =>{
            if(response.Succes == true)  this._notificator.notifySuccess("Usposno dodat zaposlen");
            else this._notificator.notifyError("Greska");
        });
    }


}
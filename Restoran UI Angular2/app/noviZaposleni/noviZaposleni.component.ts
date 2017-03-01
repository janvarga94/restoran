import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from '@angular/router';
import {LoginService} from '../services/login.service';
import {RestoranService} from "../services/restorani.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {Notificator} from "../services/notification.service";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";
/**
 * Created by Stefan on 2/23/2017.
 */


@Component({
    selector: 'novizaposleni',
    templateUrl: 'app/noviZaposleni/noviZaposleni.component.html',

})

export class NoviZaposleniComponent implements OnInit{

    routeEmail : string;

    oldPassword : string = '';
    typedOldPassword : string = '';

    update : boolean;

    ime : string = '';
    prezime : string = '';
    email : string = '';
    password : string = '';
    passwordRepeat : string = '';
    konfekcijski : any;
    obuca : any;
    jobs = [
        {id: 0, name: "Konobar"},
        {id: 1, name: "Kuvar"},
        {id: 2, name: "Sanker"}
    ];
    selectedJob : string;
    idRestorana : any;
    emailMenazderaRestorana : string;

    oldIdRestorana : number;

    constructor(private _notificator : Notificator, private route: ActivatedRoute, private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService, private _zaposleniService : ZaposleniService, private _zaposleniDetailService : ZaposleniDetailService ){

    }

    ngOnInit(): void {

        this.route.params.subscribe(params => {
            var tempMail = params['email'];
            if (tempMail != undefined && tempMail != null) {
                console.log("tempMail postoji");
                this.routeEmail = atob(params['email']);
                console.log(this.email);

                this._zaposleniDetailService.getZaposlen(this.routeEmail).subscribe(zaposlen => {
                    this.email = zaposlen[0];
                    this.oldPassword = zaposlen[1];
                    this.ime = zaposlen[2];
                    this.prezime = zaposlen[3];
                    this.konfekcijski = zaposlen[4];
                    this.oldIdRestorana = zaposlen[5];
                    this.obuca = zaposlen[6];

                    this.update = true;

                });
            } else {
                console.log("tempMail ne postoji");
                this.jobs = [
                    {id: 0, name: "Konobar"},
                    {id: 1, name: "Kuvar"},
                    {id: 2, name: "Sanker"}
                ];
                this.update = false;
                this._loginService.ulogovan.subscribe(ulogovan =>{
                    if (ulogovan)
                        this.emailMenazderaRestorana = ulogovan.email;

                });


            }

        });

    }

    addZaposlenog() {
      /*  this._restoranService.getManagerRestoranID(this.emailMenazderaRestorana).subscribe((id:any)=>{
            this.idRestorana = id;
            console.log(this.idRestorana);

            this._zaposleniService.addZaposlen({idRestorana : this.idRestorana,ime: this.ime,prezime : this.prezime,email :this.email,pass : this.password,konfenkcijskiBroj :this.konfekcijski,velicinaObuce : this.obuca,job : this.selectedJob}).subscribe(response =>{
                this._notificator.notifySuccess("Usposno dodat zaposlen");
            });

        }); */
      console.log("oldPassword : "+this.oldPassword);
        console.log("typedOldPassword : "+this.typedOldPassword);
        console.log("Password new : "+this.password);
        console.log("Password repeat: "+this.passwordRepeat);
        console.log("Ulogovan: "+this.emailMenazderaRestorana);
      if (this.update) {
          if (this.typedOldPassword != this.oldPassword) { return; }
          if (this.password != this.passwordRepeat) { return; }
          console.log("should do update");
          this._zaposleniService.updateZaposlen({emailM : ''+this.oldIdRestorana+'',ime: this.ime,prezime : this.prezime,email :this.email,pass : this.password,konfenkcijskiBroj :this.konfekcijski,velicinaObuce : this.obuca,selectedJob : ''}).subscribe(response =>{
              if(response.Success == true)  this._notificator.notifySuccess("Usposno promenjen zaposlen");
              else this._notificator.notifyError("Greska");
          });
      } else {
          if (this.password != this.passwordRepeat) { return; }

          this._zaposleniService.addZaposlen({emailM : this.emailMenazderaRestorana ,ime: this.ime,prezime : this.prezime,email :this.email,pass : this.password,konfenkcijskiBroj :this.konfekcijski,velicinaObuce : this.obuca,selectedJob : this.selectedJob['name']}).subscribe(response =>{
              if(response.Success == true)  this._notificator.notifySuccess("Usposno dodat zaposlen");
              else this._notificator.notifyError("Greska");
          });
      }


        console.log(this.emailMenazderaRestorana);

    }

    getBase(url : string) {
        return btoa(url);
    }

}

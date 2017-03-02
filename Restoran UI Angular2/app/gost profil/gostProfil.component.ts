import { RezervacijaService } from './../services/rezervacija.service';
import { PrijateljstvoService } from './../services/prijateljstvo.service';
import { IUlogovan } from './../models/ulogovan';
import { Notificator } from './../services/notification.service';
import { GostiService } from './../services/gosti.service.';
import { Component } from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {RestoranService} from "../services/restorani.service";
import {LoginResponse} from "../models/loginResponse";
import {LoginService} from "../services/login.service";


@Component({
    templateUrl: './app/gost profil/gostProfil.component.html'
})
export class GostProfilComponent {

    search : string = '';

    gost : IUlogovan = null;

    prijatelji : any[] = [];
    nepozvaniUPrijateljstvo : any[] = [];
    pozvaniUPrijateljstvo : any[] = [];
    gostPozvanUPrijateljstvoOd : any[] = [];
    poziviURestorane : any[] = [];

    search1 = '';
    search2 = '';
    search3 = '';
    search4 = '';

    private _prijatelji : any[] = [];
    private _nepozvaniUPrijateljstvo : any[] = [];
    private _pozvaniUPrijateljstvo : any[] = [];
    private _gostPozvanUPrijateljstvoOd : any[] = [];

    constructor(private _rezervacijaService : RezervacijaService ,private _loginService : LoginService, private _gostService : GostiService, private _notificator : Notificator, private _prijateljstvoService : PrijateljstvoService) {

    }

    ngOnInit() : void{
        this._loginService.ulogovan.subscribe(ulogovan =>{
            this.gost = ulogovan;

            if(ulogovan == null) return;

            console.log(ulogovan.uloga);

            this._prijateljstvoService.GetPrijateljeOf(ulogovan.email).subscribe(prijatelji => {
                this._prijatelji = prijatelji;
                this.IzmenaListe();
            });


            this._prijateljstvoService.GetNepozvaneUPrijateljstvo(ulogovan.email).subscribe(nePrijatelji => {
                this._nepozvaniUPrijateljstvo = nePrijatelji;
                this.IzmenaListe();
            });

             this._prijateljstvoService.GetPozvaneUPrijateljstvo(ulogovan.email).subscribe(oniKojimaJePoslatZahtev => {
                this._pozvaniUPrijateljstvo = oniKojimaJePoslatZahtev;
                this.IzmenaListe();
            });

              this._prijateljstvoService.GetGostPozvanUPrijateljstvoOd(ulogovan.email).subscribe(pozivaociUPrijateljstvo => {
                this._gostPozvanUPrijateljstvoOd = pozivaociUPrijateljstvo;
                this.IzmenaListe();
            });

            this._rezervacijaService.getPoziveURestorane(ulogovan.email).subscribe(pozivi => {
                this.poziviURestorane = pozivi;
            });


        });

    }

    IzmenaListe(){
        this.prijatelji = this._prijatelji.filter( p => (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(this.search2.toLowerCase()) > -1);
        this.pozvaniUPrijateljstvo = this._pozvaniUPrijateljstvo.filter( p => (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(this.search1.toLowerCase()) > -1);
        this.nepozvaniUPrijateljstvo = this._nepozvaniUPrijateljstvo.filter( p => (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(this.search3.toLowerCase()) > -1);
        this.gostPozvanUPrijateljstvoOd = this._gostPozvanUPrijateljstvoOd.filter( p => (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(this.search4.toLowerCase()) > -1);
    }

    modifyGosta(): void{
         this._gostService.ModifyGosta(this.gost['ime'], this.gost['prezime'], this.gost['email']).subscribe(response => {
             if(response.Success){
                 this._notificator.notifySuccess("Uspesna izmena!");
             }else{
                 this._notificator.notifyInfo("Izmena nije izvrsena: " + response.Message);
             }
         })
    }


    posaljiZahtev(kome : any,index : any): void{
        console.log(index);
        this._prijateljstvoService.PosaljiZahtev(this.gost['email'],kome.email).subscribe(response => {
             if(response.Success){
                 this._notificator.notifySuccess("Zahtev poslat");
                 
                 this._nepozvaniUPrijateljstvo.splice(index,1);
                 this._pozvaniUPrijateljstvo.push(kome);
                 this.IzmenaListe();
             }else{
                 this._notificator.notifyInfo("Problem: " + response.Message);
             }
        })
    }

     prihvatiZahtev(kome : any,index : any): void{
        this._prijateljstvoService.PrihvatiZahtev(kome.email,this.gost['email']).subscribe(response => {
             if(response.Success){
                 this._notificator.notifySuccess("Prijateljstvo napravljeno :)");

                 this._gostPozvanUPrijateljstvoOd.splice(index,1);
                 this._prijatelji.push(kome);
                 this.IzmenaListe();
             }else{
                 this._notificator.notifyInfo("Problem: " + response.Message);
             }
        })
    }

     prekiniZahtev(kome : any,index : any): void{
        this._prijateljstvoService.PrekiniZahtev(this.gost['email'],kome.email).subscribe(response => {
             if(response.Success){
                 this._notificator.notifySuccess("Zahtev prekinut");

                 this._pozvaniUPrijateljstvo.splice(index,1);
                 this._nepozvaniUPrijateljstvo.push(kome);
                 this.IzmenaListe();
             }else{
                 this._notificator.notifyInfo("Problem: " + response.Message);
             }
        })
    }

     prekiniPrijateljstvo(kome : any,index : any): void{
        this._prijateljstvoService.PrekiniPrijateljstvo(this.gost['email'],kome.email).subscribe(response => {
             if(response.Success){
                 this._notificator.notifySuccess("Prijateljstvo prekinuto");

                 this._prijatelji.splice(index,1);
                 this._nepozvaniUPrijateljstvo.push(kome);
                 this.IzmenaListe();
             }else{
                 this._notificator.notifyInfo("Problem: " + response.Message + " Verovatno ste prijatelja pozvali u restoran pa prvo obrisite poziv.");
             }
        })
    }

    odbijZahtev(kome : any,index : any): void{
        this._prijateljstvoService.PrekiniZahtev(this.gost['email'],kome.email).subscribe(response => {
             if(response.Success){
                 this._notificator.notifySuccess("Zahtev odbijen");

                 this._gostPozvanUPrijateljstvoOd.splice(index,1);
                 this._nepozvaniUPrijateljstvo.push(kome);
                 this.IzmenaListe();
             }else{
                 this._notificator.notifyInfo("Problem: " + response.Message);
             }
        })
    }

    getVreme(vreme : any){
        return new Date(vreme).toLocaleDateString() + " " + new Date(vreme).toLocaleTimeString();
    }

    prihvatiPozivURestoran(idPoziva : any){
        this._rezervacijaService.prihvatiOdbij(idPoziva, true).subscribe(resp => {
            if(resp['Success'] == true){
                this._notificator.notifySuccess("Poziv prihvacen!");

            }else{
                this._notificator.notifyError(resp["Message"]);                
            }
        });
    }
    odbijPozivURestoran(idPoziva : any){
        this._rezervacijaService.prihvatiOdbij(idPoziva, false).subscribe(resp => {
            if(resp['Success'] == true){
                this._notificator.notifyInfo("Poziv odbijen");
                
            }else{
                this._notificator.notifyError(resp["Message"]);                
            }
        });
    }

}

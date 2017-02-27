import { Notificator } from './../services/notification.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RezervacijaService } from './../services/rezervacija.service';
import { LoginService } from './../services/login.service';
import { PrijateljstvoService } from './../services/prijateljstvo.service';
import { Component, OnInit } from '@angular/core';

import {RestoranService} from '../services/restorani.service';
import {IRestoran} from '../models/restoran';
import { Observable } from 'rxjs/Observable';


@Component({
    selector: 'restorani',
    templateUrl: 'app/rezervacija/rezervacija.component.html',
    styles: ['.disabledElement {  pointer-events:none; opacity:0.5 }']

})
export class RezervacijaComponent implements OnInit{
    
    danas : Date = new Date(Date.now());
    casDolaska : Number;
    minutDolaska : Number;
    duzinaBoravka : any;
    myDate: any;
    odabraniSto : any;

    ulogovan : any = null;

    stage : Number = 1;

    _stoloviResponse : any[] = [];
    stolovi : any[] = [
        { zauzet : false },
         { zauzet : true  },
         { zauzet : true  },
         { zauzet : false },
          { zauzet : false },
         { zauzet : true },
         { zauzet : true},
         {  zauzet : false },
    ];


    _pozvaniPrijatelji : any[] = [];
    _nepozvaniPrijatelji : any[] = [];

    pozvaniPrijatelji : any[] = [];
    nepozvaniPrijatelji : any[] = [];
    
    _prijatelji : any[] = [];
    _search1 : String = "";

    _odabraniDatum: any;

    constructor( private _router: Router, private _notificator : Notificator,private route: ActivatedRoute, private _rezervacijaService : RezervacijaService,private _restoranService : RestoranService, private _loginService : LoginService ,private _prijateljstvoService : PrijateljstvoService) {
        this._odabraniDatum = new Date();
    }

    ngOnInit() : void{
        this.casDolaska = 12;
        this.minutDolaska = 0;
        this.duzinaBoravka = 2;
        this.danas = new Date(Date.now());
        
        this._loginService.ulogovan.subscribe(user => {
            this.ulogovan = user;
            if(user != null){
                this._prijateljstvoService.GetPrijateljeOf(user.email).subscribe(prijatelji =>{
                    this._prijatelji = prijatelji;
                    this.nepozvaniPrijatelji = this._nepozvaniPrijatelji = prijatelji;
                });
            }
        });
         this.route.params.subscribe(params => {
            this._rezervacijaService.getStolovi(params['idRestorana']).subscribe(stolovi => {
                this._stoloviResponse = stolovi;
                this.stolovi = stolovi.map(sto => { return { idStola : sto.idStola, zauzet : false}});
            });
        });
       
    }

    proveriZauzetostStolovaZaOdabraniDolazakOdlazak(){
        this._odabraniDatum.setHours(this.casDolaska);
        this._odabraniDatum.setMinutes(this.minutDolaska);
        var dolazakGosta =  this._odabraniDatum.getTime();
        var odlazakGosta =   this._odabraniDatum.getTime() + this.duzinaBoravka*60*60*1000;
        this.stolovi = this._stoloviResponse.map(sto => {
            var slobodneZauzetosti = sto.zauzetost
                .filter((z:any) => { 
                    var pocetak = new Date(z.pocetak).getTime();
                    var kraj = new Date(z.kraj).getTime();

                    return (pocetak < dolazakGosta && kraj < dolazakGosta) || (pocetak > odlazakGosta && kraj > odlazakGosta);
                });
               
            
            return {
                idStola : sto.idStola,
                zauzet : slobodneZauzetosti.length  != sto.zauzetost.length
            };
        });
                        
    }



    set search1(e){
        this._search1 = e;
        this.pozvaniPrijatelji = this._pozvaniPrijatelji.filter(p => {
            return (p.email + " " + p.ime + " " + p.prezime).toLowerCase().indexOf(e.toLowerCase()) > -1;
        });
         this.nepozvaniPrijatelji = this._nepozvaniPrijatelji.filter(p => {
            return (p.email + " " + p.ime + " " + p.prezime).toLowerCase().indexOf(e.toLowerCase()) > -1;
        });
    }

    get search1(){
        return this._search1;
    }

    set odabraniDatum(e){
        e = e.split('-');
        let d = new Date(Date.UTC(e[0], e[1]-1, e[2]));
        this._odabraniDatum.setFullYear(d.getUTCFullYear(), d.getUTCMonth(), d.getUTCDate());
    }

    get odabraniDatum(){
        return this._odabraniDatum.toISOString().substring(0, 10);
    }

    odaberiSto(sto:any){
        this.odabraniSto = sto;
        this.stage = 3;
    }

    pozovi(index : any){
        this._pozvaniPrijatelji.push(this.nepozvaniPrijatelji[index]);
        this._nepozvaniPrijatelji.splice(index,1);
        this.search1 = this.search1;
    }

    nepozovi(index : any){
        this._nepozvaniPrijatelji.push(this.pozvaniPrijatelji[index]);
        this._pozvaniPrijatelji.splice(index,1);
        this.search1 = this.search1;
    }

    rezervisi(){
        this._rezervacijaService.rezervisi(
            {   pocetak: this._odabraniDatum.getTime(),
                kraj: this._odabraniDatum.getTime() + + this.duzinaBoravka*60*60*1000,
                rezervant: this.ulogovan.email,
                idStola: this.odabraniSto.idStola,
                pozvaniPrijatelji: this._pozvaniPrijatelji.map(p => p.email),
            }
        ).subscribe(response => {
            if(response['Success'] == true){
                this._notificator.notifySuccess("Uspesno data rezervacija");
                this._router.navigate(['/rezervacije']);
            }else{
                this._notificator.notifyError(response['Message']);
            }
        });
    }

 }

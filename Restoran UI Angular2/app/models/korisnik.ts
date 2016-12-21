import {Uloga} from './uloga';

export interface IKorisnik {
    email : string,
    idRestorana : number,
    ime : string,
    prezime : string,
    lozinka : string,
    aktiviran : boolean,
    idGosta : number
}


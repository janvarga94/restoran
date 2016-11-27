
import {IJelo} from './jelo';

export interface IRestoran {
    naziv : string,
    vrsta : string,
    jelovnik : IJelo[],
    konfiguracija : string,
    ocena : number
}


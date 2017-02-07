import { Pipe, PipeTransform } from '@angular/core';
/*
 * Raise the value exponentially
 * Takes an exponent argument that defaults to 1.
 * Usage:
 *   value | exponentialStrength:exponent
 * Example:
 *   {{ 2 |  exponentialStrength:10}}
 *   formats to: 1024
*/
@Pipe({name: 'limitDuzineListe'})
export class LimitDuzineListe implements PipeTransform {
  transform(value: any[], duzina: number): any[] {
    if(value.length > duzina )
      return value.slice(0,duzina);
    else
      return value;
  }
}
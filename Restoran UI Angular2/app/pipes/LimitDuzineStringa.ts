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
@Pipe({name: 'limitDuzineStringa'})
export class LimitDuzineStringa implements PipeTransform {
  transform(value: string, duzina: number): string {
    if(value.length > duzina )
      return value.substr(0, duzina) + "...";
    else
      return value;
  }
}
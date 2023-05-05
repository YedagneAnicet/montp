import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'firstNameFormat'
})
export class FirstNameFormatPipe implements PipeTransform {

  transform(value: string): string {
    const words = value.split(' ');
    const formattedWords = words.map(word => {
      const firstLetter = word.charAt(0).toUpperCase();
      const restOfWord = word.slice(1).toLowerCase();
      return `${firstLetter}${restOfWord}`;
    });

    return formattedWords.join(' ');
  }

}

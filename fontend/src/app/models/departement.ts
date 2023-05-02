export class Departement {
  id?: number;
  code?: string;
  designation?: string;

  constructor(id?:number,code ?: string,designation ?: string) {
    this.code = code;
    this.designation = designation
  }
}

import { Departement } from "./departement"

export class Personne {
  id ?: number
  nom ?: string
  prenoms ?: string
  age ?: number
  departement ?: Departement

  constructor(id ?: number,nom ?: string,prenoms ?: string, age ?: number,departement ?: Departement) {
    this.id = id;
    this.age = age;
    this.nom = nom;
    this.prenoms = prenoms;
    this.departement = departement;
  }
}

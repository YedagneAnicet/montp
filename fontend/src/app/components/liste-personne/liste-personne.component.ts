import { Component, OnInit } from '@angular/core';
import { MessageService, } from 'primeng/api';
import { Personne } from 'src/app/models/personne';
import { DepartementService } from 'src/app/services/departement.service';
import { PersonneService } from 'src/app/services/personne.service';

@Component({
  selector: 'app-liste-personne',
  templateUrl: './liste-personne.component.html',
  styleUrls: ['./liste-personne.component.scss'],
  providers: [MessageService],
})
export class ListePersonneComponent implements OnInit {
  first = 0; // attribuer lié à la pagination
  rows = 10; // attribuer lié à la pagination

  visible!: boolean;
  submitted!: boolean;
  personne : any;
  listePersonne: any;
  btnText : any;

  ListeDepartement : any;
  SelectDepartement : any;
  departement : any;

  cols: any[] = [];
  agesOptions!: any[];

  constructor(
    private _personneService: PersonneService,
    private _messageService: MessageService,
    private _departementService : DepartementService
  ) {}

  ngOnInit() {
    this.getListePersonne();
    this.cols = [
      {
        field: 'nom',
        header: 'Nom',
      },
      {
        field: 'age',
        header: 'Age',
      },
    ];

    this.agesOptions = [
      { label: 'Mineur', value: () => (this.personne.age < 18 ? 'mineur' : 'majeur') },
      { label: 'Majeur', value: () => (this.personne.age >= 18 ? 'majeur' : 'mineur') },
    ];
  }

  // Cette fonction permet d'afficher le formulaire de saisie de personne
  showDialog() {
    this.visible = true;
    this.submitted = false;
    this.personne = {};
    this.btnText = 'Ajouter';
  }

  // getListeDepartement() {
  //   this._departementService.getAllDerpartement().subscribe({
  //     next: (reponse: any) => {
  //       this.ListeDepartement = reponse;
  //       console.log(reponse);
  //     },
  //     error: (error: any) => {
  //       console.log(error);
  //     },
  //   });
  // }

  //     filterdepartement(event:any) {
  //       let filtered: any[] = [];
  //       let query = event.query;

  //       for (let i = 0; i < this.ListeDepartement.length; i++) {
  //           let departement = this.ListeDepartement[i];
  //           if (departement.designation.toLowerCase().indexOf(query.toLowerCase()) == 0) {
  //               filtered.push(departement.designation);
  //           }
  //       }

  //       this.ListeDepartement = filtered;
  //   }


  // Cette fonction permet de récupérer la liste des personnes depuis le service
  getListePersonne() {
    this._personneService.getPersonnes().subscribe({
      next: (reponse: any) => {
        this.listePersonne = reponse;
        console.log(reponse);
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  // Cette fonction permet de pré-remplir le formulaire avec les données d'une personne existante pour la modifier
  editPersonne(personne: Personne) {
    this.personne = { ...personne };
    this.visible = true;
    this.btnText = 'Modifier';
  }

  // Cette fonction permet de supprimer une personne de la liste
  deletePersonne(id: number) {
    this._personneService.deletePersonne(id).subscribe({
      next: (reponse: any) => {
        this.getListePersonne();
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  // Cette fonction permet d'ajouter ou de modifier une personne
  savePersonne(id: number, personne: Personne) {
    this.submitted = true;
    // Vérification des champs du formulaire
    if (!personne.nom || !personne.prenoms || !personne.age) {
      this._messageService.add({
        key: 'tc',
        severity: 'error',
        summary: 'Erreur',
        detail: 'Veuillez remplir tous les champs obligatoires.',
        life: 3000,
      });
      return;
    }

    if (this.btnText === 'Ajouter') {
      this._personneService.createPersonne(personne).subscribe({
        complete: () => {
          this.getListePersonne();
          this._messageService.add({
            key: 'tc',
            severity: 'success',
            summary: 'Success',
            detail: 'Personne Ajoutée',
            life: 3000,
          });
        },
        error: (error: any) => {
          console.log(error);
        },
      });
    } else {
      const index = this.listePersonne.findIndex(
        (p: { id: number }) => p.id === id
      );
      this._personneService.updatePersonne(id, personne).subscribe({
        complete: () => {
          this.listePersonne[index] = personne;
          this._messageService.add({
            key: 'tc',
            severity: 'success',
            summary: 'Success',
            detail: 'Personne modifiée',
            life: 3000,
          });
        },
        error: (error: any) => {
          console.log(error);
        },
      });
    }

    this.visible = false;
  }
}

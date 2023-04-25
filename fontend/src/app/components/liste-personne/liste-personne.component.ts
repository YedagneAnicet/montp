import { Component, OnInit } from '@angular/core';
import { MessageService, } from 'primeng/api';
import { Departement } from 'src/app/models/departement';
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
  first = 0;
  rows = 10;

  visible!: boolean;
  submitted!: boolean;

  btnText !: any;

  personne : any;
  listePersonne: any;

  ListeDepartement !: [];
  departement !: Departement;

  cols: any[] = [];

  constructor(
    private _personneService: PersonneService,
    private _messageService: MessageService,
    private _departementService : DepartementService
  ) {}

  ngOnInit() {
    this.getListePersonne();
    this.getListeDepartement();
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
  }

  // Cette fonction permet d'afficher le formulaire de saisie de personne
  showDialog() {
    this.visible = true;
    this.submitted = false;
    this.personne = {};
    this.btnText = 'Ajouter';
  }

  // Cette fonction permet de récupérer la liste des departements depuis le service
  getListeDepartement() {
    this._departementService.getDepartements().subscribe({
      next: (reponse: any) => {
        this.ListeDepartement = reponse;
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }


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
    if (!personne.nom || !personne.prenoms || !personne.age || !personne.departement) {
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

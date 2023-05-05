import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListePersonneComponent } from './liste-personne.component';
import {PersonneService} from "../../services/personne.service";
import {DepartementService} from "../../services/departement.service";
import {ConfirmationService, MessageService} from "primeng/api";
import { HttpClientModule} from "@angular/common/http";
import {ConfirmDialog, ConfirmDialogModule} from "primeng/confirmdialog";
import {DialogModule} from "primeng/dialog";
import {ToastModule} from "primeng/toast";
import {TableModule} from "primeng/table";
import {TreeModule} from "primeng/tree";
import {TreeTableModule} from "primeng/treetable";
import {ToolbarModule} from "primeng/toolbar";
import {of} from "rxjs";
import {Departement} from "../../models/departement";
import {Personne} from "../../models/personne";

describe('ListePersonneComponent', () => {
  let component: ListePersonneComponent;
  let fixture: ComponentFixture<ListePersonneComponent>;

  const personneServiceStub = jasmine.createSpyObj('PersonneService', ['getPersonnes','createPersonne','updatePersonne','deletePersonne']);
  const confirmationServiceStub = jasmine.createSpyObj('ConfirmationService', ['confirm']);
  const messageServiceStub = jasmine.createSpyObj('MessageService', ['add']);
  const departementServiceStub = jasmine.createSpyObj('DepartementService',['getDepartements'])
  const confirmDialogSpy = jasmine.createSpyObj('ConfirmDialog', ['accept', 'reject']);


  beforeEach(async () => {

    await TestBed.configureTestingModule({
      declarations: [ListePersonneComponent],
      imports: [HttpClientModule,
        ConfirmDialogModule, DialogModule, ToastModule, TableModule, TreeModule, TreeTableModule, ToolbarModule
      ],
      providers: [
        {provide: PersonneService, useValue: personneServiceStub},
        {provide : MessageService, useValue: messageServiceStub},
        {provide : DepartementService, useValue: departementServiceStub},
        {provide : ConfirmationService, useValue: confirmationServiceStub},
        {provide: ConfirmDialog, useValue: confirmDialogSpy}

      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ListePersonneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  fit('should set listePersonne property with the response from PersonneService', () => {
    // GIVEN
    const mockDepartement = new Departement(1,'ABJ',"Abidjan")
    const mockPersonnes = [new Personne(1,"Yedagne","Anicet",22,mockDepartement), new Personne(2,"Bamba","Wingnemila",24,mockDepartement)];
    personneServiceStub.getPersonnes.and.returnValue(of(mockPersonnes));

    // WHEN
    component.getListePersonne();

    // THEN
    expect(component.listePersonne).toEqual(mockPersonnes);
  });

})

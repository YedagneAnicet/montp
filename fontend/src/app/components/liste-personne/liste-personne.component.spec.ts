import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListePersonneComponent } from './liste-personne.component';
import {PersonneService} from "../../services/personne.service";
import {DepartementService} from "../../services/departement.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {Personne} from "../../models/personne";
import {Departement} from "../../models/departement";
import {of} from "rxjs";
import {HttpClientModule} from "@angular/common/http";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DialogModule} from "primeng/dialog";
import {ToastModule} from "primeng/toast";
import {TableModule} from "primeng/table";
import {TreeModule} from "primeng/tree";
import {TreeTableModule} from "primeng/treetable";
import {ToolbarModule} from "primeng/toolbar";

describe('ListePersonneComponent', () => {
  let component: ListePersonneComponent;
  let fixture: ComponentFixture<ListePersonneComponent>;

  let personneService : PersonneService;

  let messageServiceStub = jasmine.createSpyObj('MessageService', ['add']);
  let personneServiceStub = jasmine.createSpyObj('PersonneService', ['getPersonnes','createPersonne','updatePersonne','deletePersonne']);
  let departementServiceStub = jasmine.createSpyObj('DepartementService', ['getDepartements']);
  let confirmationServiceStub = jasmine.createSpyObj('ConfirmationService', ['add']);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListePersonneComponent ],
      imports:[HttpClientModule,
        ConfirmDialogModule,DialogModule,ToastModule,TableModule,TreeModule,TreeTableModule,ToolbarModule
      ],
      providers: [
        {provide: personneServiceStub, useValue: PersonneService},
        {provide : messageServiceStub, useValue: MessageService},
        {provide : departementServiceStub, useValue: DepartementService},
        {provide : confirmationServiceStub, useValue: ConfirmationService},
        ConfirmationService
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListePersonneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    personneService =TestBed.inject(PersonneService)
  });

  fit('should tester getListePersonne', () => {
    //given
    let departement = new Departement(1, 'abj','abidjan');
    let personne = new Personne(1, 'yedagne', 'anicet', 22, departement);
    let personnes = [personne];
    spyOn(personneService, 'getPersonnes').and.returnValue(of(personnes));


    // personneServiceStub.getPersonnes.and.returnValue(of(personnes));

    //when
    component.getListePersonne();

    //then
    // expect(personneServiceStub.getPersonnes).toHaveBeenCalled();
    expect(component.listePersonne).toEqual(personnes);
  });


});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListePersonneComponent } from './liste-personne.component';

describe('ListePersonneComponent', () => {
  let component: ListePersonneComponent;
  let fixture: ComponentFixture<ListePersonneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListePersonneComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListePersonneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

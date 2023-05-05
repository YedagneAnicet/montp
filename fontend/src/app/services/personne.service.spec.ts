import { TestBed } from '@angular/core/testing';

import { PersonneService } from './personne.service';
import {HttpClientModule} from "@angular/common/http";

describe('PersonneService', () => {
  let service: PersonneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations:[],
      imports:[HttpClientModule]
    });
    service = TestBed.inject(PersonneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

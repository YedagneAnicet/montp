import { TestBed } from '@angular/core/testing';

import { DepartementService } from './departement.service';
import {HttpClientModule} from "@angular/common/http";

describe('DepartementService', () => {
  let service: DepartementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports :[HttpClientModule]
    });
    service = TestBed.inject(DepartementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

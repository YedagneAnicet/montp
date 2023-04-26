package com.eburtis.montp.Repository;

import com.eburtis.montp.Domain.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}

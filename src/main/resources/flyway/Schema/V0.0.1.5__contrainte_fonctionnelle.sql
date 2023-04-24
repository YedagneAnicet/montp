ALTER TABLE personne
ADD departement_id int,
ADD CONSTRAINT fk_departement_id FOREIGN KEY (departement_id) REFERENCES departement(id);

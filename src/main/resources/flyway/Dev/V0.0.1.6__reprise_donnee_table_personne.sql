UPDATE personne SET departement_id = CASE id
    WHEN 1 THEN 1
    WHEN 2 THEN 2
    WHEN 3 THEN 1
    WHEN 4 THEN 3
    WHEN 5 THEN 2
    WHEN 6 THEN 3
    ELSE departement_id
END;
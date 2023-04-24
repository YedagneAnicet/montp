package com.eburtis.montp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculTest {
    private Calcul calcul;

    @BeforeEach
    public void setUp() {
        calcul = new Calcul(5, 3);
    }

    @Test
    @DisplayName("Test de l'addition")
    public void testAdditionner() {
        float result = calcul.additionner(calcul.a, calcul.b);
        Assertions.assertEquals(8, result);
    }

    @Test
    @DisplayName("Test de la soustraction")
    public void testSoustraire() {
        float result = calcul.soustraire(calcul.a, calcul.b);
        Assertions.assertEquals(2, result);
    }

    @Test
    @DisplayName("Test de la multiplication")
    public void testMultiplier() {
        float result = calcul.multiplier(calcul.a, calcul.b);
        Assertions.assertEquals(15, result);
    }

    @Test
    @DisplayName("Test de la division")
    public void testDiviser() throws Exception {
        float result = calcul.diviser(calcul.a, calcul.b);
        Assertions.assertEquals(1.6666666f, result, 0.00001f);
    }

    @Test
    @DisplayName("Test de la division par zéro")
    public void testDiviserParZero() {
        Assertions.assertThrows(Exception.class, () -> calcul.diviser(calcul.a, 0));
    }

    @Test
    @DisplayName("Test du carré")
    public void testCarre() {
        float result = calcul.carre(calcul.a);
        Assertions.assertEquals(25, result);
    }

    @Test
    @DisplayName("Test de l'identité remarquable")
    public void testIdentiteRemarquable() {
        float result = calcul.identiteRemarquable(calcul.a, calcul.b);
        Assertions.assertEquals(64, result);
    }
}

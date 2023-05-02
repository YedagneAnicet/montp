package com.eburtis.montp;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("CalculTest")
@DisplayName("test unitaire pour la classe calcul")
class CalculTest {
    private Calcul calcul;

    @BeforeEach
    public void setUp() {
        calcul = new Calcul(5, 3);
    }

    @Test
    @DisplayName("Test de l'addition")
    void testAdditionner() {
        float result = calcul.additionner(calcul.a, calcul.b);
        Assertions.assertEquals(8, result);
    }

    @Test
    @DisplayName("Test de la soustraction")
    void testSoustraire() {
        float result = calcul.soustraire(calcul.a, calcul.b);
        Assertions.assertEquals(2, result);
    }

    @Test
    @DisplayName("Test de la multiplication")
    void testMultiplier() {
        float result = calcul.multiplier(calcul.a, calcul.b);
        Assertions.assertEquals(15, result);
    }

    @Test
    @DisplayName("Test de la division")
    void testDiviser() throws Exception {
        float result = calcul.diviser(calcul.a, calcul.b);
        Assertions.assertEquals(1.6666666f, result, 0.00001f);
    }

    @Test
    @DisplayName("Test de la division par zéro")
    void testDiviserParZero() {
        Assertions.assertThrows(Exception.class, () -> calcul.diviser(calcul.a, 0));
    }

    @Test
    @DisplayName("Test du carr��")
    void testCarre() {
        float result = calcul.carre(calcul.a);
        Assertions.assertEquals(25, result);
    }

    @Test
    @DisplayName("Test de l'identité remarquable")
    void testIdentiteRemarquable() {
        float result = calcul.identiteRemarquable(calcul.a, calcul.b);
        Assertions.assertEquals(64, result);
    }
}
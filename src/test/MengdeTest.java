package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mengde.JavaSetToMengde;
import mengde.LenketMengde;
import mengde.MengdeADT;
import mengde.TabellMengde;

class MengdeTest {

	  
    private MengdeADT<Integer> tabellMengde;
    private MengdeADT<Integer> lenketMengde;
    private MengdeADT<Integer> javaSetMengde;

    @BeforeEach
    void setUp() {
        tabellMengde = new TabellMengde<>();
        lenketMengde = new LenketMengde<>();
        javaSetMengde = new JavaSetToMengde<>();
    }

    @Test
    void testErTom() {
        assertTrue(tabellMengde.erTom());
        assertTrue(lenketMengde.erTom());
        assertTrue(javaSetMengde.erTom());
    }

    @Test
    void testLeggTilOgInneholder() {
        tabellMengde.leggTil(1);
        lenketMengde.leggTil(1);
        javaSetMengde.leggTil(1);

        assertTrue(tabellMengde.inneholder(1));
        assertTrue(lenketMengde.inneholder(1));
        assertTrue(javaSetMengde.inneholder(1));
    }

    @Test
    void testFjern() {
        tabellMengde.leggTil(2);
        lenketMengde.leggTil(2);
        javaSetMengde.leggTil(2);

        assertEquals(2, tabellMengde.fjern(2));
        assertEquals(2, lenketMengde.fjern(2));
        assertEquals(2, javaSetMengde.fjern(2));

        assertFalse(tabellMengde.inneholder(2));
        assertFalse(lenketMengde.inneholder(2));
        assertFalse(javaSetMengde.inneholder(2));
    }

    @Test
    void testUnion() {
        tabellMengde.leggTil(1);
        lenketMengde.leggTil(1);
        javaSetMengde.leggTil(1);

        MengdeADT<Integer> unionTabell = tabellMengde.union(lenketMengde);
        MengdeADT<Integer> unionLenket = lenketMengde.union(javaSetMengde);
        MengdeADT<Integer> unionSet = javaSetMengde.union(tabellMengde);

        assertTrue(unionTabell.inneholder(1));
        assertTrue(unionLenket.inneholder(1));
        assertTrue(unionSet.inneholder(1));
    }

    @Test
    void testSnitt() {
        tabellMengde.leggTil(3);
        tabellMengde.leggTil(4);
        lenketMengde.leggTil(4);
        javaSetMengde.leggTil(4);
        javaSetMengde.leggTil(5);

        MengdeADT<Integer> snittTabell = tabellMengde.snitt(javaSetMengde);
        MengdeADT<Integer> snittLenket = lenketMengde.snitt(javaSetMengde);
        MengdeADT<Integer> snittSet = javaSetMengde.snitt(tabellMengde);

        assertTrue(snittTabell.inneholder(4));
        assertTrue(snittLenket.inneholder(4));
        assertTrue(snittSet.inneholder(4));
        assertFalse(snittTabell.inneholder(3));
        assertFalse(snittSet.inneholder(5));
    }
}

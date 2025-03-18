package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mengde.MengdeADT;
import mengde.TabellMengde;

class MengdeTest {

	  private TabellMengde<Integer> mengde1;
	    private TabellMengde<Integer> mengde2;
	    private TabellMengde<Integer> tomMengde;

	    @BeforeEach
	    void setUp() {
	        mengde1 = new TabellMengde<>();
	        mengde2 = new TabellMengde<>();
	        tomMengde = new TabellMengde<>();

	        mengde1.leggTil(1);
	        mengde1.leggTil(2);
	        mengde1.leggTil(3);

	        mengde2.leggTil(3);
	        mengde2.leggTil(4);
	        mengde2.leggTil(5);
	    }

	    @Test
	    void testErTom() {
	        assertTrue(tomMengde.erTom());
	        assertFalse(mengde1.erTom());
	    }

	    @Test
	    void testInneholder() {
	        assertTrue(mengde1.inneholder(1));
	        assertFalse(mengde1.inneholder(4));
	    }

	    @Test
	    void testErDelmengdeAv() {
	        TabellMengde<Integer> mengde3 = new TabellMengde<>();
	        mengde3.leggTil(1);
	        mengde3.leggTil(2);

	        assertTrue(mengde3.erDelmengdeAv(mengde1));
	        assertFalse(mengde1.erDelmengdeAv(mengde2));
	    }

	    @Test
	    void testErLik() {
	        TabellMengde<Integer> mengde3 = new TabellMengde<>();
	        mengde3.leggTil(1);
	        mengde3.leggTil(2);
	        mengde3.leggTil(3);

	        assertTrue(mengde1.erLik(mengde3));
	        assertFalse(mengde1.erLik(mengde2));
	    }

	    @Test
	    void testErDisjunkt() {
	        assertTrue(mengde1.erDisjunkt(tomMengde));
	        assertFalse(mengde1.erDisjunkt(mengde2));
	    }

	    @Test
	    void testSnitt() {
	        MengdeADT<Integer> snitt = mengde1.snitt(mengde2);
	        assertTrue(snitt.inneholder(3));
	        assertFalse(snitt.inneholder(1));
	        assertFalse(snitt.inneholder(4));
	    }

	    @Test
	    void testUnion() {
	        MengdeADT<Integer> union = mengde1.union(mengde2);
	        assertTrue(union.inneholder(1));
	        assertTrue(union.inneholder(2));
	        assertTrue(union.inneholder(3));
	        assertTrue(union.inneholder(4));
	        assertTrue(union.inneholder(5));
	        assertEquals(5, union.antallElementer());
	    }

	    @Test
	    void testMinus() {
	        MengdeADT<Integer> diff = mengde1.minus(mengde2);
	        assertTrue(diff.inneholder(1));
	        assertTrue(diff.inneholder(2));
	        assertFalse(diff.inneholder(3));
	    }

	    @Test
	    void testLeggTilOgFjern() {
	        mengde1.leggTil(10);
	        assertTrue(mengde1.inneholder(10));
	        
	        mengde1.fjern(10);
	        assertFalse(mengde1.inneholder(10));
	    }
	}
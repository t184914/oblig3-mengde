package mengde;

import java.util.Arrays;

public  class TabellMengde<T> implements MengdeADT<T> {
	    private static final int DEFAULT_CAPACITY = 10;
	    private T[] elements;
	    private int size;

	    @SuppressWarnings("unchecked")
	    public TabellMengde() {
	        elements = (T[]) new Object[DEFAULT_CAPACITY];
	        size = 0;
	    }

	    private void ensureCapacity() {
	        if (size == elements.length) {
	            elements = Arrays.copyOf(elements, elements.length * 2);
	        }
	    }

	    @Override
	    public boolean erTom() {
	        return size == 0;
	    }

	    @Override
	    public boolean inneholder(T element) {
	        for (int i = 0; i < size; i++) {
	            if (elements[i].equals(element)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    @Override
	    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
	        for (int i = 0; i < size; i++) {
	            if (!annenMengde.inneholder(elements[i])) {
	                return false;
	            }
	        }
	        return true;
	    }

	    @Override
	    public boolean erLik(MengdeADT<T> annenMengde) {
	        return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
	    }

	    @Override
	    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
	        for (int i = 0; i < size; i++) {
	            if (annenMengde.inneholder(elements[i])) {
	                return false;
	            }
	        }
	        return true;
	    }

	    @Override
	    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
	        MengdeADT<T> snittMengde = new TabellMengde<>();
	        for (int i = 0; i < size; i++) {
	            if (annenMengde.inneholder(elements[i])) {
	                snittMengde.leggTil(elements[i]);
	            }
	        }
	        return snittMengde;
	    }

	    @Override
	    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
	        MengdeADT<T> unionMengde = new TabellMengde<>();
	        for (int i = 0; i < size; i++) {
	            unionMengde.leggTil(elements[i]);
	        }
	        T[] annenTabell = annenMengde.tilTabell();
	        for (T element : annenTabell) {
	            unionMengde.leggTil(element);
	        }
	        return unionMengde;
	    }

	    @Override
	    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
	        MengdeADT<T> diffMengde = new TabellMengde<>();
	        for (int i = 0; i < size; i++) {
	            if (!annenMengde.inneholder(elements[i])) {
	                diffMengde.leggTil(elements[i]);
	            }
	        }
	        return diffMengde;
	    }

	    @Override
	    public void leggTil(T element) {
	        if (!inneholder(element)) {
	            ensureCapacity();
	            elements[size++] = element;
	        }
	    }

	    @Override
	    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
	        T[] annenTabell = annenMengde.tilTabell();
	        for (T element : annenTabell) {
	            leggTil(element);
	        }
	    }

	    @Override
	    public T fjern(T element) {
	        for (int i = 0; i < size; i++) {
	            if (elements[i].equals(element)) {
	                T fjernetElement = elements[i];
	                elements[i] = elements[--size]; // Erstatt med siste element
	                elements[size] = null;
	                return fjernetElement;
	            }
	        }
	        return null;
	    }

	    @Override
	    public T[] tilTabell() {
	        return Arrays.copyOf(elements, size);
	    }

	    @Override
	    public int antallElementer() {
	        return size;
	    }
	}
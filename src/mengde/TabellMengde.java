package mengde;

import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {
	
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
	public boolean erTom() {
		return size == 0;
	}
	
	public boolean inneholder (T element) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		for (int i = 0; i < size; i++) {
			if (!annenMengde.inneholder(elements[i])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean erLik(MengdeADT<T> annenMengde) {
		return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
	}
	
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for (int i = 0; i < size; i ++) {
			if (annenMengde.inneholder(elements[i])) {
				return false;
			}
		}
		return true;
	}
	
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde){
		MengdeADT<T> snittMengde = new TabellMengde<>();
		for (int i = 0; i < size; i++) {
			if (annenMengde.inneholder(elements[i])) {
				snittMengde.leggTil(elements[i]);
			}
		}
		return snittMengde;
	}
	
	public MengdeADT<T> union(MengdeADT<T> annenMengde){
		MengdeADT<T> unionMengde = new TabellMengde<>();
		for(int i = 0; i < size; i++) {
			unionMengde.leggTil(elements[i]);
		}
		T[] annenTabell = annenMengde.tilTabell();
		for (T element : annenTabell) {
			unionMengde.leggTil(element);
		}
		return unionMengde;
	}
	

}

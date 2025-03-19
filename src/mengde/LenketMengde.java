package mengde;

import node.LinearNode;

public class LenketMengde<T> implements MengdeADT<T> {
	
	private LinearNode<T> head;
    private int size;

    public LenketMengde() {
        head = null;
        size = 0;
    }

    @Override
    public boolean erTom() {
        return size == 0;
    }

    @Override
    public boolean inneholder(T element) {
        LinearNode<T> current = head;
        while (current != null) {
            if (current.getElement().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        LinearNode<T> current = head;
        while (current != null) {
            if (!annenMengde.inneholder(current.getElement())) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        LinearNode<T> current = head;
        while (current != null) {
            if (annenMengde.inneholder(current.getElement())) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeADT<T> snittMengde = new LenketMengde<>();
        LinearNode<T> current = head;
        while (current != null) {
            if (annenMengde.inneholder(current.getElement())) {
                snittMengde.leggTil(current.getElement());
            }
            current = current.getNext();
        }
        return snittMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeADT<T> unionMengde = new LenketMengde<>();
        LinearNode<T> current = head;
        while (current != null) {
            unionMengde.leggTil(current.getElement());
            current = current.getNext();
        }
        for (T element : annenMengde.tilTabell()) {
            unionMengde.leggTil(element);
        }
        return unionMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeADT<T> diffMengde = new LenketMengde<>();
        LinearNode<T> current = head;
        while (current != null) {
            if (!annenMengde.inneholder(current.getElement())) {
                diffMengde.leggTil(current.getElement());
            }
            current = current.getNext();
        }
        return diffMengde;
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            LinearNode<T> newNode = new LinearNode<>(element);
            newNode.setNext(head);
            head = newNode;
            size++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T element : annenMengde.tilTabell()) {
            leggTil(element);
        }
    }

    @Override
    public T fjern(T element) {
        if (head == null) return null;

        if (head.getElement().equals(element)) {
            T fjernetElement = head.getElement();
            head = head.getNext();
            size--;
            return fjernetElement;
        }

        LinearNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getElement().equals(element)) {
                T fjernetElement = current.getNext().getElement();
                current.setNext(current.getNext().getNext());
                size--;
                return fjernetElement;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        @SuppressWarnings("unchecked")
        T[] tabell = (T[]) new Object[size];
        LinearNode<T> current = head;
        int i = 0;
        while (current != null) {
            tabell[i++] = current.getElement();
            current = current.getNext();
        }
        return tabell;
    }

    @Override
    public int antallElementer() {
        return size;
    }
}
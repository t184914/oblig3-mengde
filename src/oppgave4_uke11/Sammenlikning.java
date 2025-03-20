package oppgave4_uke11;

import java.util.*;

public class Sammenlikning {
	public static void main(String[] args) {
		final int ANTALL_ELEMENT = 100000;
		final int SØK_ELEMENT = 10000;
		final int MODULO = 1000000;
		final int STEG = 45713;
		
		HashSet<Integer> hashSet = new HashSet<>();
		Integer[] tallArray = new Integer[ANTALL_ELEMENT];
		
		int tall = 376;
		for (int i=0; i < ANTALL_ELEMENT; i++) {
			hashSet.add(tall);
			tallArray[i] = tall;
			tall = (tall + STEG) % MODULO;
		}
		
		Arrays.sort(tallArray);
		
		
		Random random = new Random();
		int[] søkTall = new int[SØK_ELEMENT];
		for (int i = 0; i < SØK_ELEMENT; i++) {
			søkTall[i] = random.nextInt(MODULO);
		}
		
		
		
		//Tidsmåling for søk i HashSet
		long startTime = System.nanoTime();
		int funnHashSet = 0;
		for (int søk : søkTall) {
			if (hashSet.contains(søk)) {
				funnHashSet++;
			}
		}
		
		long endTime = System.nanoTime();
		long hashSetTid = (endTime - startTime) / 1_000_000;  //gjør om til millisekund
		
		
		//Tidsmåling for binærsøk
		startTime = System.nanoTime();
		int funnBinarySearch = 0;
		for (int søk : søkTall) {
			if (Arrays.binarySearch(tallArray, søk) >= 0) {
				funnBinarySearch++;
			}
		}
		endTime = System.nanoTime();
		long binarySearchTid = (endTime - startTime) / 1_000_000; //til ms
		
		
		System.out.println("HashSet: " + funnHashSet +" treff på " + hashSetTid + " ms");
		System.out.println("Binærsøk: " + funnBinarySearch + " treff på " + binarySearchTid + " ms");
	}

}


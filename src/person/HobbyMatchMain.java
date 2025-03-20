package person;

import java.util.HashSet;
import java.util.Set;

public class HobbyMatchMain {
	public static double match(Person a, Person b) {
	    Set<String> felles = new HashSet<>(a.getHobbyer());
	    felles.retainAll(b.getHobbyer()); // 

	    Set<String> kunHosA = new HashSet<>(a.getHobbyer());
	    kunHosA.removeAll(b.getHobbyer()); 

	    Set<String> kunHosB = new HashSet<>(b.getHobbyer());
	    kunHosB.removeAll(a.getHobbyer());

	    int antallFelles = felles.size();
	    int antallKunHosDenEne = kunHosA.size();
	    int antallKunHosDenAndre = kunHosB.size();
	    int antallTotalt = a.getHobbyer().size() + b.getHobbyer().size();

	    if (antallTotalt == 0) {
	        return 1.0; 
	    }

	    return (antallFelles - (antallKunHosDenEne + antallKunHosDenAndre)) / (double) antallTotalt;
	}


    public static void main(String[] args) {
        Person arne = new Person("Arne", "jakt", "sykling", "venner", "data");
        Person brit = new Person("Brit", "sykling", "strikking", "venner", "data");
        Person carl = new Person("Carl", "gaming", "data", "sykling");

        double matchAB = match(arne, brit);
        double matchAC = match(arne, carl);
        double matchBC = match(brit, carl);

        System.out.println("Match mellom Arne og Brit: " + matchAB);
        System.out.println("Match mellom Arne og Carl: " + matchAC);
        System.out.println("Match mellom Brit og Carl: " + matchBC);
    }
}


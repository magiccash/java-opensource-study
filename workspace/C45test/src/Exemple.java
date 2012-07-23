import java.util.*;

public class Exemple {
 
    private Vector exemple = new Vector(1000);

    /** 
     * Constructeur d'un exemple sous  forme de String, les differentes caracteristiques separees par des virgules
     */
    Exemple(String st) {
	String[] tab = st.split(",");
	int size = tab.length;
	for (int i = 0 ; i < size ; i++) {
	    exemple.add(tab[i].trim());
	}
    }

    public Vector getExemple(){
	return exemple;
    }

    public boolean isPos() {
	String st = ((String)(exemple.lastElement())).toLowerCase();
	return st.equals("yes") || st.equals("y") || st.equals("positive") || st.equals("oui") || st.equals("o");
    }

    public boolean isNeg() {
	String st = ((String)(exemple.lastElement())).toLowerCase();
	return st.equals("no") || st.equals("n") || st.equals("negative") || st.equals("non");
    }

    public String element(int pos){
	return (String)exemple.elementAt(pos);
    }

    public int size() {
	return exemple.size();
    }

    public boolean sameExemple(Exemple ex) {
	boolean ret = true;
	if (this.size() == ex.size()){
	    Iterator it = this.getExemple().iterator();
	    int pos = 0;
	    while (it.hasNext() && ret) {
		String st = (String)it.next();
		if (!st.equals(ex.element(pos))) {
		    ret = false;
		}
		pos++;
	    }
	}
	else {
	    ret = false;
	}
	return ret;
    }

    public String toString(){
	String ret = "";
	Iterator it = exemple.iterator();
	while (it.hasNext()){
	    String st = (String)(it.next());
	    ret += st+" ";
	}
	return ret;
    }
}

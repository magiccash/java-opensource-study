import java.util.*;
 
public class Attribut {
   
    private String attribut;
    private Vector values = new Vector();

    Attribut(String attribut, String val) {
	this.attribut = attribut.trim();
	String[] tab = val.split(",");
	int size = tab.length;
	for (int i = 0 ; i < size ; i++) {
	    values.add((tab[i]).trim());
	}
    }

    public boolean sameAttribut(Attribut at) {
	return attribut.equals(at.getAttribut());
    }

    public String getAttribut() {
	return this.attribut;
    }

    public Vector getValues() {
	return this.values;
    }

    public String getValuesA(){
	String ret = "";
	Iterator it = this.values.iterator();
	while (it.hasNext()) {
	    ret += ((String)it.next())+"\n";
	}
	return ret;
    }

    public String toString() {
	String ret = "";
	ret += this.attribut+" { ";
	Iterator it = values.iterator();
	while (it.hasNext()) {
	    String st = (String)it.next();
	    ret += st+" ";
	}
	ret += "}";
	return ret; 
    }
}

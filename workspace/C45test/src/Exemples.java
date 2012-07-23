import java.util.*;

public class Exemples{

    private Vector exemples;
    
    Exemples() {
	this.exemples = new Vector();
    }
     
    Exemples(Vector vect) {
	this.exemples = vect;
    }

    public void add(Exemple ex) {
	exemples.add(ex);
    }

    public int getSize() {
	return this.exemples.size();
    }

    public Vector getExemples() {
	return this.exemples;
    }

    public Vector getPos(){
	Iterator it = exemples.iterator();
	Vector ret = new Vector();
	while (it.hasNext()) {
	    Exemple ex = (Exemple)(it.next());
	    if (ex.isPos()) {
		ret.add(ex);
	    }
	}
	return ret;
    }
    
    public int getNPos(){
    	int numPos = 0;
    	Iterator it = exemples.iterator();
    	while (it.hasNext()) {
    	    Exemple ex = (Exemple)(it.next());
    	    if (ex.isPos()) {
    		numPos++;
    	    }
    	}
    	return numPos;
    }
    
    public Vector getNeg(){
	Iterator it = exemples.iterator();
	Vector ret = new Vector();
	while (it.hasNext()) {
	    Exemple ex = (Exemple)(it.next());
	    if (ex.isNeg()) {
		ret.add(ex);
	    }
	}
	return ret;
    }

    public String toString(){
	String ret = "";
	Iterator it = exemples.iterator();
	while (it.hasNext()){
	    Exemple ex = (Exemple)it.next();
	    ret += ""+ex+"\n";
	}
	return ret;
    }
}

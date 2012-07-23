import java.util.*;

public class Attributs {
  
    private Vector attributs;

    Attributs() {
	attributs = new Vector();
    }

    Attributs(Vector vect) {
	this.attributs = vect;
    }
    
    public Vector getAttributs() {
	return attributs;
    }

    public void add(Attribut attr) {
	this.attributs.add(attr);
    }

    public int size() {
	return attributs.size();
    }

    /**
     * methode permettant de recuperer le concept a apprendre par rapport a un ensemble d'attributs
     */
    public String getConcept() {
	return ((Attribut)this.attributs.lastElement()).getAttribut();
    }

    /**
     * methode permettant de recuperer sous forme de chaine de caracteres avec retour a la ligne
     * l'ensemble des attributs
     */
    public String getAttributsA() {
	String ret = "";
	Iterator it = this.attributs.iterator();
	while (it.hasNext()) {
	    Attribut at = (Attribut)it.next();
	    ret += at.getAttribut()+"\n";
	}
	return ret;
    }
    public Vector getAttributsAllVector() {
    Vector ret = new Vector();
    Iterator it = this.attributs.iterator();
    while (it.hasNext()) {
    	Attribut at = (Attribut)it.next();
    	ret.add(at.getAttribut());
    }
    return ret;
    }
    /**
     * methode permettant de recuperer l'ensemble des valeurs d'un attribut sous forme de chaine de caracteres 
     * avec retour a la ligne
     */
    public String getAttributValues(String attr){
	Attribut at = this.getAttributOfAttribut(attr);
	String ret = "";
	if (at != null) {
	    ret = at.getValuesA();
	}
	return ret;
    }

    /**
     * methode permettant de recuperer l'ensemble des valeurs d'un attribut sous forme de vector
     * 
     */
    public Vector getAttributValues1(String attr){
	Attribut at = this.getAttributOfAttribut(attr);
	Vector ret = new Vector();
	if (at != null) {
	    ret = at.getValues();
	}
	return ret;
    }

    /**
     * methode permettant de recuperer l'index d'un attribut dans l'ensemble des attributs
     */
    public int indexOfAttribut(String attr) {
	int i = 0;
	boolean b = true;
	Iterator it = this.attributs.iterator();
	while (it.hasNext() && b) {
	    Attribut at = (Attribut)it.next();
	    if (at.getAttribut().equals(attr)) {
		b = false;
	    }
	    else{
		i++;
	    }
	}
	return i;
    }
    
    /**
     * methode permettant de recuperer un attribut a partir de son nom
     */
    public Attribut getAttributOfAttribut(String attr) {
	Attribut ret = null;
	boolean b = true;
	Iterator it = this.attributs.iterator();
	while (it.hasNext() && b) {
	    Attribut at = (Attribut)it.next();
	    if (at.getAttribut().equals(attr)) {
		b = false;
		ret = at;
	    }
	}
	return ret;
    }

    public String toString() {
	Iterator it = this.attributs.iterator();
	String ret = "";
	while (it.hasNext()) {
	    Attribut at = (Attribut)it.next();
	    ret += at+"\n";
	}
	return ret;
    }
}

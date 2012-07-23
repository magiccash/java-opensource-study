import java.util.*;
 
public class Arbre {
 
	private Vector noeuds;
	

    Arbre() {
	noeuds = new Vector();
    }

    public Vector getNoeuds() {
	return noeuds;
    }

    public void add(Noeud noeud) {
	this.noeuds.add(noeud);
    }
    
    /**
     * methode ajouter un noeud 
     * @param noeud
     */
    public void addNoeud(Noeud noeud) {
    this.noeuds.addElement(noeud);
    }
    
    /**
     * methode pour obtenir le nombre de noeud
     * @return le nombre de noeud
     */
    public int size() {
	return this.noeuds.size();
    }

    /**
     * methode permettant de recuperer sous forme de chaine de caracteres avec retour a la ligne
     * l'ensemble des noeuds
     */
    public String getArbre() {
	String ret = "";
	Iterator it = this.noeuds.iterator();
	while (it.hasNext()) {
	    Noeud at = (Noeud)it.next();
	    ret += at.getNoeud()+"\n";
	    ret += at.getFilsString();
	}
	return ret;
    }

}

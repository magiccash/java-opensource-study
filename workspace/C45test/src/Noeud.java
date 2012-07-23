import java.util.*;

public class Noeud {
 
	private String noeud;           // noeud sous la forme  outlook=sunny
	private Vector fils;           //  sous la forme '|     humidity=high |     humidity=normal'
	private String filsAttrName;  // le nom d'attribut de son fils
	private Vector positif;      //  les exemple positifs qui saitisfait jusqu'a maintenant
	private Vector negatif;		//   les exemple negatifs qui saitisfait jusqu'a maintenant
	private Vector attrL;	   //    les attributs ce que sont utilisables maintenant
    
	public Noeud() {
    this.fils = new Vector();
    this.positif = new Vector();
    this.negatif = new Vector();
    this.attrL = new Vector();
    this.filsAttrName = "";
    this.noeud = "";
    }
	
	public void setNoeud(String noeudTmp) {
	this.noeud = noeudTmp;
	}
	
	public void setPosV(Vector pos) {
	this.positif = (Vector)pos.clone();
	}
	
	public void setNegV(Vector neg) {
	this.negatif = (Vector)neg.clone();
	}
	
	/**
	 * methode pour donner il y a reste combien des attribut on peut utiliser 
	 * @param attr est l'ensemble des attribut sous la forme Vector
	 */
	public void setAttrL(Vector attr) {
	this.attrL = (Vector)attr.clone();
	}

	public void setfilsAttrName(String attr) {
	this.filsAttrName = attr;
	}
	
	public String getfilsAttrName() {
	return this.filsAttrName;
	}
	
	public Vector getAttrL() {
	return this.attrL;
	}
	
	public Vector getPosV() {
	return this.positif;
	}
	
	public Vector getNegV() {
	return this.negatif;
	}
	
	public void addFils(String fils) {
	this.fils.add(fils);
	}
	
	public String getNoeud() {
	return this.noeud;
	}
	 
	public Vector getFils() {
	return this.fils;
	}
	
	public String getFilsString() {
	String literal = "";
	Vector fils = getFils();
	Iterator it = fils.iterator();
	while (it.hasNext()) {
    		String st = (String)it.next();
    		literal +=st;
		}
	return literal;
	}
}


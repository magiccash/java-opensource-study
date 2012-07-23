import java.util.*;

public class Data{ 
       
	private Attributs attributs;
    private String concept;
	private Exemples exemples;
    
    /**
     * constructeur d'une liste d'exemples a partir d'un fichier de forme specifique
     * @param file est le nom complet du fichier
     */
    Data(String file){   
	exemples = new Exemples();
	attributs = new Attributs();
	try{
	    LectureFichier lf = new LectureFichier();
	    Vector vect = lf.lectureFichierVectSpecial(file,"@attribute");
	    Iterator it = vect.iterator();
	    while (it.hasNext()) { 
		String st = (String)it.next();	    
		st = st.replaceFirst("@[a-z]* ","");
		String[] attr = st.split(" [{]");
		Attribut at = new Attribut(attr[0],attr[1].replaceAll("}",""));
		attributs.add(at);
	    }
	    
	    vect = lf.lectureFichierVectNonSpecial(file,"%@");
	    it = vect.iterator();
	    while (it.hasNext()) {
		String st = (String)it.next();
		Exemple ex = new Exemple(st);
		exemples.add(ex);
	    }	
	    this.concept = attributs.getConcept();
	}
	catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("erreur de lecture format");}
    }

    /**
     * methode permettant de recuperer le concept a apprendre par rapport a un ensemble d'attributs
     */
    public String getConcept() {
	return concept;
    }

    public Vector getAttributsV() {
	return this.attributs.getAttributs();
    }

    public Vector getExemplesV() {
	return this.exemples.getExemples();
    }

    public Attributs getAttributs() {
	return this.attributs;
    }

    public Exemples getExemples() {
	return this.exemples;
    }

    /**
     * methode permettant de recuperer l'ensemble des valeurs d'un attribut sous forme de chaine de caracteres 
     * avec retour a la ligne
     */
    public String getAttributValues(String attr){
	return this.attributs.getAttributValues(attr);
    }

    /**
     * methode permettant de recuperer l'ensemble des valeurs d'un attribut sous forme de vector 
     * 
     */
    public Vector getAttributValues1(String attr){
	return this.attributs.getAttributValues1(attr);
    }

    /**
     * methode permettant de recuperer l'index d'un attribut dans l'ensemble des attributs
     */
    public int indexOfAttribut(String attr) {
	return this.attributs.indexOfAttribut(attr);
    }

    /**
     * methode permettant de recuperer un attribut a partir de son nom
     */
    public Attribut getAttributOfAttribut(String attr) {
	return this.attributs.getAttributOfAttribut(attr);
    }

    /**
     * methode permettant de recuperer tous les exemples positifs
     */
    public Vector getPos(){
	return this.exemples.getPos();
    }
    
    /**
     * methode permettant de recuperer le number des exemples positifs
     * @return est le number des exemples positifs
     */
    public int getNPos(){
    	return this.exemples.getNPos();
    }
    
    /**
     * methode permettant de recuperer tous les exemples negatifs
     */
    public Vector getNeg(){
    	return this.exemples.getNeg();
    }
}

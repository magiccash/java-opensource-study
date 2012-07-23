import java.util.*;
import java.io.*;
 
public class Apprentissage{
  
    private Data data;
    private Gain gainM;
    private Vector eRegles;
    private Vector posNonCouv;
    private double Entropie;

    Apprentissage(String file) {
	this.data = new Data(file);
	this.gainM = new GainAsk();
    }

    Apprentissage(String file, Gain gain) {
	this.data = new Data(file);
	this.gainM = gain;
	this.setEntropie(this.data);
    }
    
    public void setGain(Gain ga) {
	this.gainM = ga;
    }

    public void setData(Data dt) {
	this.data = dt;
    }

    public void setEntropie(Data dt) {
    	Vector positif = data.getPos();
    	Vector negatif = data.getNeg();
    	int gp = positif.size();
    	int gn = negatif.size();
    	this.Entropie = entropie(gp, gn);
    }
    
    public double getEntropie() {
        return Entropie;
    }
    
    /**
     * methode pour calculer l'Entropie
     * @param p est le nombre des exemples positifs
     * @param n est le nombre des exemples negatifs
     * @return entropie
     */
    public double entropie(int p,int n) {
    	float p1 = (float)p;
    	float n1 = (float)n;
    	if ((p1!=0)&&(n1!=0)) {
    		return (float)(- (p1/(p1+n1))*(Math.log(p1/(p1+n1))/Math.log(2.0)) - (n1/(p1+n1))*(Math.log(n1/(p1+n1))/Math.log(2.0)));	
    	} 
    	else {
    		return 0;
    		 }
    		
    	}
    
    /**
     * methode pour retourne le gain d'un attribut
     * @param attr est le nom d'un attribut
     * @param pos est le Vector exemples positifs
     * @param neg est le Vector exemples negatifs
     */
    public double calculGain(String attr, Vector pos, Vector neg) {
    int pg = pos.size();
    int ng = neg.size();
    Vector attrV = getAttributValues1(attr);
    Iterator it = attrV.iterator();
    double entropie = 0, gainAttribut = 0, Ent = 0;
	while (it.hasNext()) {
		String litteral = "";
	    litteral = attr+"="+(String)it.next();
	    Vector satisfaitLPos = satisfait(litteral,pos);
		Vector satisfaitLNeg = satisfait(litteral,neg);
		int p = satisfaitLPos.size();
		int n = satisfaitLNeg.size();
		entropie = entropie + (float)(p+n)/(float)(pg+ng)*entropie(p,n);
	}
	Ent = entropie(pg, ng);
	gainAttribut = (Ent -entropie);
    return gainAttribut;
    }
    
   /**
    * methode permettant de recuperer le valeur de Gain pour chaque attribut
    * @param attr
    * @return le Gain par chaque attribut
    */ 
    public double calculGain(String attr) {
        int pg = data.getPos().size();
        int ng = data.getNeg().size();
        Vector attrV = getAttributValues1(attr);
        Iterator it = attrV.iterator();
        double entropie = 0, gainAttribut = 0, Ent = 0;
    	while (it.hasNext()) {
    		String litteral = "";
    	    litteral = attr+"="+(String)it.next();
    	    Vector satisfaitLPos = satisfait(litteral,data.getPos());
    		Vector satisfaitLNeg = satisfait(litteral,data.getNeg());
    		int p = satisfaitLPos.size();
    		int n = satisfaitLNeg.size();
    		entropie = entropie + (float)(p+n)/(float)(pg+ng)*entropie(p,n);
    	}
    	Ent = entropie(pg, ng);
    	gainAttribut = (Ent -entropie);
        return gainAttribut;
        }
    
    /**
     * methode pour construire l'arbre par l'attribut ce que choisi par l'utilisateur
     * @param attrName
     * @return l'Arbre decision
     */
    public String construArbreParUtilisateur(String attrName){
    	Vector pos = data.getPos();
    	Vector neg = data.getNeg();
    	Vector postemp = new Vector();
    	Vector negtemp = new Vector();
    	Attribut attribut = data.getAttributOfAttribut(attrName);
        String Resultat = "";
    	Vector attrAll = getAttributsAllVector();
    	attrAll.remove(attrAll.lastElement());
    	Vector values = attribut.getValues();
    	Iterator it = values.iterator();
    	attrAll.removeElement(attrName);
    	Arbre arbre = new Arbre();
    	while (it.hasNext()) {
    		Vector attrAllTemp =  (Vector)attrAll.clone();
    		String st = (String)it.next();
    		String lit = attribut.getAttribut()+"="+st;
    		postemp = satisfait(lit,pos);
    		negtemp = satisfait(lit,neg);
    		Iterator itt = attrAllTemp.iterator();
    		Noeud NoeudRacine = new Noeud();
			NoeudRacine.setNoeud(lit);
		//	Resultat += NoeudRacine.getNoeud();
    		if(postemp.size()!=0 && negtemp.size()!=0) {
    			NoeudRacine.setNegV(negtemp);
    			NoeudRacine.setPosV(postemp);
    			double maxGain= 0;
    			String literal = "";
    			String literaltmp = "";
    			String removeAttr = "";
    			while (itt.hasNext()) {
    				String stt = (String)itt.next();
    				double gainAttribut = calculGain(stt, postemp, negtemp);
    				if(gainAttribut > maxGain && gainAttribut >0) {
    					maxGain = gainAttribut;
    					Attribut MaxGainAttribut = data.getAttributOfAttribut(stt);
    					removeAttr =stt;
    					Vector valeurs = MaxGainAttribut.getValues();
    					Iterator ittt = valeurs.iterator();
    					literaltmp = "";
    					while (ittt.hasNext()) {
    			    		String sttt = (String)ittt.next();
    			    		literaltmp += "|     "+MaxGainAttribut.getAttribut()+"="+sttt+"\n";
    					}
    				} 
		    	}
    			attrAllTemp.removeElement(removeAttr);
    			literal += literaltmp;
    			NoeudRacine.addFils(literal);
    			NoeudRacine.setfilsAttrName(removeAttr);
    			NoeudRacine.setAttrL(attrAllTemp);
    		} else if(postemp.size()==0 && negtemp.size()!=0) {
    		  NoeudRacine.setPosV(postemp);
    		  }else if(postemp.size()!=0 && negtemp.size()==0) {
    			  NoeudRacine.setNegV(postemp);
    		  }
    		arbre.addNoeud(NoeudRacine);
    	}
    	Vector noeuds = arbre.getNoeuds();
    	Arbre arbreFinal = arbre;
    	Iterator itNoeud = noeuds.iterator();
    	Resultat = arbreFinal.getArbre();
        while(itNoeud.hasNext()) {
    	Noeud noeud = (Noeud)itNoeud.next();
    	boolean test = sonFilsIsFeuille(noeud);
    	if(!test){
    		Resultat +=constru(noeud, arbreFinal);
    		}
        }
    	return Resultat;
        }
    /**
     * methode pour construire toutes les noeuds
     * @param noe sont les noeuds ce qu'on a crée par le premier etape 
     * @param arbre de décision
     * @return expression d'arbre de décision
     */
    public String constru(Noeud noe, Arbre arbre) {
    	String ResultatTmp = "";
    	Vector pos = noe.getPosV();
        Vector neg = noe.getNegV();
        Vector attrL = noe.getAttrL();
        String noeudName = noe.getNoeud();
        String attrName = noe.getfilsAttrName();
        boolean SonFilsisFeuille = sonFilsIsFeuille(noe);
		if(!SonFilsisFeuille) {	
			Attribut attribut = data.getAttributOfAttribut(attrName);
	    	Vector values = attribut.getValues();
	    	Iterator itt = values.iterator();
	       	attrL.removeElement(attrName);
	       	while (itt.hasNext()) {
	       		Vector postemp = new Vector();
		    	Vector negtemp = new Vector();
	       		Vector attrAllTemp =  (Vector)attrL.clone();
	    		String st = (String)itt.next();
	    		String lit = attribut.getAttribut()+"="+st;
	    		postemp = satisfait(lit,pos);
	    		negtemp = satisfait(lit,neg);
	    		Iterator ittt = attrAllTemp.iterator();
	    		if(postemp.size()!=0 && negtemp.size()!=0) {
	    			Noeud noeudProchaine = new Noeud();
	    			noeudProchaine.setNoeud(noeudName+"&&"+lit);
	    			noeudProchaine.setNegV(negtemp);
	    			noeudProchaine.setPosV(postemp);
	    			double maxGain= 0;
	    			String literal = "";
	    			String literaltmp = "";
	    			String removeAttr = "";
	    			while (ittt.hasNext()) {
	    				String sttt = (String)ittt.next();
	    				double gainAttribut = calculGain(sttt, postemp, negtemp);
	    				if(gainAttribut > maxGain && gainAttribut >0) {
	    					maxGain = gainAttribut;
	    					Attribut MaxGainAttribut = data.getAttributOfAttribut(sttt);
	    					removeAttr =sttt;
	    					Vector valeurs = MaxGainAttribut.getValues();
	    					Iterator itttt = valeurs.iterator();
	    					literaltmp = "";
	    					while (itttt.hasNext()) {
	    			    		String stttt = (String)itttt.next();
	    			    		literaltmp += "|     "+MaxGainAttribut.getAttribut()+"="+stttt+"\n";
	    					}
	    				} 
			    	}
	    			Vector attrAllTemp1 = (Vector)attrAllTemp.clone();
	    			attrAllTemp1.removeElement(removeAttr);
	    			literal += literaltmp;
	    			noeudProchaine.addFils(literal);
	    			noeudProchaine.setfilsAttrName(removeAttr);
	    			noeudProchaine.setAttrL(attrAllTemp1);
	    			Arbre arbrehe = new Arbre();
	    			arbrehe.add(noeudProchaine);
	    			ResultatTmp +=arbrehe.getArbre();
    	    		if(!sonFilsIsFeuille(noeudProchaine)){
    	    			ResultatTmp +=constru(noeudProchaine, arbrehe);
    	    		}
	    		}
	    	
	      	}
	       
	}
    return ResultatTmp;  
    }
    
    /**
     * methode pour verifier si le noeud est un feuille ou non
     * @param noeud
     * @return true si il est un feuille, sinon return false
     */
    public boolean sonFilsIsFeuille(Noeud noe) {
    	boolean retfils = false;
    	Vector posfils = noe.getPosV();
        Vector negfils = noe.getNegV();
        String attrNamefils = noe.getfilsAttrName();
        if(attrNamefils.equals("")) {
        	retfils = true;
        } else {
        	Attribut attributfils = data.getAttributOfAttribut(attrNamefils);
        	Vector valuesfils = attributfils.getValues();
        	Iterator itfils = valuesfils.iterator();
        	while (itfils.hasNext()) {
    		String stfils = (String)itfils.next();
    		String litfils = noe.getfilsAttrName()+"="+stfils;
    		Vector postempfils = new Vector();
    		Vector negtempfils = new Vector();
    		postempfils = satisfait(litfils,posfils);
    		negtempfils = satisfait(litfils,negfils);
    	    if (postempfils.size()!=0 && negtempfils.size()!=0) {
    	    return false;
    	    } else retfils = true;
        }
        }
    	return retfils;
        }
    
    /**
     * methode permettant d'apprendre un concept, le dernier des attributs
     * ceci a partir d'exmples , cet esemble de donnees etant contenu dans l'objet de type Exemples attribut de cette classe
     * @return Vector regle le veteur des regles permettant de conclure sur le concept
     */
    public Vector apprendre(){
	posNonCouv = new Vector();
	Vector pos = data.getPos();
	Vector neg = data.getNeg();
	Vector regle = new Vector();
	while (!pos.isEmpty()) {
	    String conditions_regle = "";
	    Vector neg2 = (Vector)neg.clone();
	    Vector pos2 = (Vector)pos.clone();
	    while (!neg2.isEmpty()) {
		String litteral = "";
		Vector attrCondR = getAttributCondRegle(conditions_regle);		
		litteral = this.maxGain(pos2,neg2,attrCondR);
		if (litteral.equals("")) {
		    neg2.removeAllElements();
		    conditions_regle = "";
		}
		else {
		    if (!conditions_regle.equals("")) {
			conditions_regle += " && ";
		    }		
		    conditions_regle += litteral;
		    this.retirer_non_satisfaisable(neg2,litteral);
		    this.retirer_non_satisfaisable(pos2,litteral);
		}
	    } 
	    if (conditions_regle.equals("")) {
		posNonCouv.addAll(pos);
		pos.removeAllElements();
		regle.add("Le gain utilisé ne permet pas d'apprendre le concept");
	    }
	    else {
		regle.add(conditions_regle);
		this.retirer_satisfaisable(pos,conditions_regle);
	    }
	}
	this.eRegles = regle;
	return regle;
    }

    public Vector getVectPosNonCouv() {
	return posNonCouv;
    }

    public Vector getAttributCondRegle(String cond) {
	Vector ret = new Vector();
	if (!cond.equals("")) {
	    String[] list = cond.split("&&");
	    int i = 0;
	    while (i < list.length) {	    	    
		String st = ((list[i].split("="))[0]).trim();
	    Attribut at = data.getAttributOfAttribut(st);
		ret.add(at);
		//System.out.print(st);
		i++;
	    }
	}
	return ret;
    }

    /**
     * methode permettant d'obtenir la liste des exemples contenus dans le vecteur vect
     */
    public String getVectExString(Vector vect) {
	Iterator it = vect.iterator();
	String ret = "";
	while (it.hasNext()) {
	    Exemple st = (Exemple)it.next();
	    ret += st+"\n";
	}
	return ret;
    }

    /**
     * @param String litteral un litteral 
     * @param Vector vect un vecteur d'exemples
     * methode permettant de retourner les exmples qui verifient le litteral
     */
    public Vector satisfait(String litteral, Vector vect) {
	Vector ret = new Vector();
	String premierePartie = (litteral.split("="))[0];
	String secondePartie = (litteral.split("="))[1];
	int place = data.indexOfAttribut(premierePartie);
	Iterator it = vect.iterator();
	while (it.hasNext()) {
	    Exemple ex = (Exemple)it.next();
	    if (ex.getExemple().elementAt(place).equals(secondePartie)) {
		ret.add(ex);
	    }
	}
	return ret;
    }
    
    /**
     * methode qui a partir d'un vecteur d'exmples et d'un ensemble de litteraux sous la forme 'humidity=normal && windy=FALSE'
     * supprime du vecteur les exemples qui satisfont l'ensemble des litteraux
     */
    public void retirer_satisfaisable(Vector vect, String litteral){
    String[] lit = litteral.split(" && ");
	int size = lit.length;
	Vector vectB = (Vector)vect.clone();
	for (int i = 0 ; i < size ; i++) {
	    String premierePartie = (lit[i].split("="))[0];
	    String secondePartie = (lit[i].split("="))[1];
	    int index = data.indexOfAttribut(premierePartie);
	    Iterator it = vectB.iterator();
	    while (it.hasNext()) {
		Exemple ex = (Exemple)it.next();
		if (!ex.element(index).equals(secondePartie)){
		    it.remove();
		}
	    }
	}
	this.delVect(vectB,vect);
    }


    /**
     * methode qui verifie si l'exemple appartient au vecteur d'exemples
     */
    public boolean exAppartient(Vector vect, Exemple ex) {
	Iterator it = vect.iterator();
	boolean ret = false;
	while (it.hasNext() && !ret) {
	    Exemple ex2 = (Exemple)it.next();
	    if (ex.sameExemple(ex2)) {
		ret = true;
	    }
	}	  
	return ret;
    }

    /**
     * methode qui verifie si l'exemple appartient au vecteur d'exemples
     */
    public boolean exAppartientA(Vector vect, Attribut at) {
	Iterator it = vect.iterator();
	boolean ret = false;
	while (it.hasNext() && !ret) {
	    Attribut at2 = (Attribut)it.next();
	    if (at.sameAttribut(at2)) {
		ret = true;
	    }
	}	  
	return ret;
    }

    /**
     * supprime les elements du vect1 de vect2, des vecteurs d'exemples
     */
    public void delVect(Vector vect1, Vector vect2) {
	Iterator it = vect2.iterator();
	while (it.hasNext()) {
	    Exemple ex = (Exemple)it.next();
	    if (exAppartient(vect1,ex)) {
		it.remove();
	    }
	}
    }

    /**
     * supprime les elements du vect1 de vect2, des vecteurs d'exemples
     */
    public void delVectA(Vector vect1, Vector vect2) {
	Iterator it = vect2.iterator();
	while (it.hasNext()) {
	    Attribut at = (Attribut)it.next();
	    if (exAppartientA(vect1,at)) {
		it.remove();
	    }
	}
    }


    /**
     * methode qui retire du vecteur vect l'ensemble des exemples qui ne satisfont pas le litteral de la forme 'truc=machin'
     */
    public void retirer_non_satisfaisable(Vector vect, String litteral){
	String premierePartie = (litteral.split("="))[0];
	String secondePartie = (litteral.split("="))[1];
//	Attribut attribut = data.getAttributOfAttribut(premierePartie);
	int index = data.indexOfAttribut(premierePartie);
	Iterator it = vect.iterator();
	while (it.hasNext()) {
	    Exemple ex = (Exemple)it.next();
	    if (!ex.element(index).equals(secondePartie)){
		it.remove();
	    }
	}
    }

    /**
     * methode qui retourne le gain apporte par le litteral par rapport aux exmples positifs et negatifs passes en prarmetre
     */
    public double appelGain(String litteral, Vector pos, Vector neg) {
    int gp = pos.size();
	int gn = neg.size();
	Vector satisfaitLPos = this.satisfait(litteral,pos);
	Vector satisfaitLNeg = this.satisfait(litteral,neg);
	int p = satisfaitLPos.size();
	int n = satisfaitLNeg.size();
	return gainM.gain(p,n,gp,gn);
    }
    
    /**
     * methode qui retourne le litteral qui maximise le gain par rapport a des exmples positifs et negatifs passes en parametre
     */
    public String maxGain(Vector pos, Vector neg, Vector vectD) {
	Vector attribut = (Vector)data.getAttributsV().clone();
	attribut.remove(attribut.lastElement());
	this.delVectA(vectD,attribut);
	double maxVal = Double.NEGATIVE_INFINITY;
	String maxLit = "";
	Iterator it = attribut.iterator();
	while (it.hasNext()) {
	    Attribut attr = (Attribut)it.next();
	    Vector vectC = (Vector)attr.getValues();
	    Iterator itS = vectC.iterator();
	    while (itS.hasNext()) {
		String st = (String)itS.next();
		String lit = attr.getAttribut()+"="+st;
		double ga = this.appelGain(lit,pos,neg);	
	    if (ga > maxVal && ga > 0) {
		maxVal = ga;
		maxLit = lit;
		    }
	    }
	}
	return maxLit;
    }

    public String getAttributValues(String st){
	return data.getAttributValues(st);
    }

    public Vector getAttributValues1(String st){
    return data.getAttributValues1(st);
    }
    
    public String getExemples(){
	return data.getExemples().toString();
    }

    public String getAttribut(){
       	return data.getAttributs().toString();
    }
    
    public String getAttributsA() {
	return data.getAttributs().getAttributsA();
    }
    
    public Vector getAttributsAllVector() {
    return data.getAttributs().getAttributsAllVector();
    }
    
    public String getConcept() {
	return data.getConcept();
    }

    public int getExemplesSize() {
	return this.data.getExemples().getSize();
    }
    
    public int getNPos() {
    return this.data.getNPos();
    }
    /**
     * methode pour obtenir le nombre des exemples negatifs
     * 
     */
    public int getNNeg() {
    return (this.data.getExemples().getSize()-this.data.getNPos());
    }
    
    public void affichRegles (){
	System.out.println("ensemble de regles permettant de conclure sur le concept : "+this.data.getConcept());
    Iterator it = eRegles.iterator();
	while (it.hasNext()) {
	    String st = (String)it.next();
	    System.out.println("----"+st);
		}
    }

    public String getRegles(){
	String ret = "";
	Iterator it = eRegles.iterator();
	while (it.hasNext()) {
	    String st = (String)it.next();
	    ret +=st+"\n";
		}
	return ret;
    }
    
    public Data getData() {
    return this.data;
    }
    /***********************************************************/
    public String choix(Vector attribut, Vector positif, Vector negatif) throws IOException{
		int choix = 0;
		int i = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;		
		String attr = "";
		String choixAttribut = "";
    	Iterator it = attribut.iterator();
		System.out.println("Les attributs:");
		System.out.println("\n--------------------------------------------------\n");
		while(it.hasNext()){
			attr = (String)it.next();
			//double gainAttribut = calculGain(attr, positif, negatif);
			System.out.println((i)+": "+attr+"--->Gain est :"+calculGain(attr, positif, negatif));
			i++;
		}
		System.out.println("\n--------------------------------------------------\n");
		System.out.println("Choisir l'attribut: ");
		str = br.readLine();
		try{
			choix = Integer.parseInt(str);
			choixAttribut = (String)attribut.get(choix);
		}catch(NumberFormatException e){
			choix = -1;
			System.out.println("Vous devez taper NUMERO, essayer une autre fois, SVP !");
		}
		System.out.println("--------------------------------------------------");
		
		return choixAttribut;
	}

    
    public void construArbreParUtilisateur2(String attrName) throws IOException{
		
    	Vector pos = data.getPos();
    	Vector neg = data.getNeg();
    	Vector postemp = new Vector();
    	Vector negtemp = new Vector();
    	Attribut attribut = data.getAttributOfAttribut(attrName);
        String Resultat = "";
    	Vector attrAll = getAttributsAllVector();
    	attrAll.remove(attrAll.lastElement());
    	Vector values = attribut.getValues();
    	Iterator it = values.iterator();
    	attrAll.removeElement(attrName);
    	Arbre arbre = new Arbre();
    	while (it.hasNext()) {
    		Vector attrAllTemp =  (Vector)attrAll.clone();
    		String st = (String)it.next();
    		String lit = attribut.getAttribut()+"="+st;
    		postemp = satisfait(lit,pos);
    		negtemp = satisfait(lit,neg);
    		//Iterator itt = attrAllTemp.iterator();
    		Noeud NoeudRacine = new Noeud();
			NoeudRacine.setNoeud(lit);
    		if(postemp.size()!=0 && negtemp.size()!=0) {
    			NoeudRacine.setNegV(negtemp);
    			NoeudRacine.setPosV(postemp);
    			//double maxGain= 0;
    			String literal = "";
    			String literaltmp = "";
    			String removeAttr = "";
    			System.out.print(lit+"\n"+"|  ?\n");
    			removeAttr = choix(attrAllTemp, postemp, negtemp);
    			Attribut MaxGainAttribut = data.getAttributOfAttribut(removeAttr);
    			Vector valeurs = MaxGainAttribut.getValues();
				Iterator ittt = valeurs.iterator();
				while (ittt.hasNext()) {
		    		String sttt = (String)ittt.next();
		    		literaltmp += "|     "+MaxGainAttribut.getAttribut()+"="+sttt+"\n";
				}
    			/*
    			while (itt.hasNext()) {
    				String stt = (String)itt.next();
    				double gainAttribut = calculGain(stt, postemp, negtemp);
    				if(gainAttribut > maxGain && gainAttribut >0) {
    					maxGain = gainAttribut;
    					Attribut MaxGainAttribut = data.getAttributOfAttribut(stt);
    					removeAttr =stt;
    					Vector valeurs = MaxGainAttribut.getValues();
    					Iterator ittt = valeurs.iterator();
    					literaltmp = "";
    					while (ittt.hasNext()) {
    			    		String sttt = (String)ittt.next();
    			    		literaltmp += "|     "+MaxGainAttribut.getAttribut()+"="+sttt+"\n";
    					}
    				} 
		    	}*/	
		    	attrAllTemp.removeElement(removeAttr);
    			literal += literaltmp;
    			NoeudRacine.addFils(literal);
    			NoeudRacine.setfilsAttrName(removeAttr);
    			NoeudRacine.setAttrL(attrAllTemp);
    		} else if(postemp.size()==0 && negtemp.size()!=0) {
    		  NoeudRacine.setPosV(postemp);
    		  }else if(postemp.size()!=0 && negtemp.size()==0) {
    			  NoeudRacine.setNegV(postemp);
    		  }
    		arbre.addNoeud(NoeudRacine);
    	}
    	Vector noeuds = arbre.getNoeuds();
    	Arbre arbreFinal = arbre;
    	Iterator itNoeud = noeuds.iterator();
    	Resultat = arbreFinal.getArbre();
    	//System.out.print(Resultat);
        while(itNoeud.hasNext()) {
    	Noeud noeud = (Noeud)itNoeud.next();
    	boolean test = sonFilsIsFeuille(noeud);
    	if(!test){
    		Resultat +=constru2(noeud, arbreFinal);
    		}
        }
        System.out.print(Resultat); 
        }
   
    public String constru2(Noeud noe, Arbre arbre) throws IOException {
    	String ResultatTmp = "";
    	Vector pos = noe.getPosV();
        Vector neg = noe.getNegV();
        Vector attrL = noe.getAttrL();
        String noeudName = noe.getNoeud();
        String attrName = noe.getfilsAttrName();
        boolean SonFilsisFeuille = sonFilsIsFeuille(noe);
		if(!SonFilsisFeuille) {	
			Attribut attribut = data.getAttributOfAttribut(attrName);
	    	Vector values = attribut.getValues();
	    	Iterator itt = values.iterator();
	       	attrL.removeElement(attrName);
	       	while (itt.hasNext()) {
	       		Vector postemp = new Vector();
		    	Vector negtemp = new Vector();
	       		Vector attrAllTemp =  (Vector)attrL.clone();
	    		String st = (String)itt.next();
	    		String lit = attribut.getAttribut()+"="+st;
	    		postemp = satisfait(lit,pos);
	    		negtemp = satisfait(lit,neg);
	    		Iterator ittt = attrAllTemp.iterator();
	    		if(postemp.size()!=0 && negtemp.size()!=0) {
	    			Noeud noeudProchaine = new Noeud();
	    			noeudProchaine.setNoeud(noeudName+"&&"+lit);
	    			noeudProchaine.setNegV(negtemp);
	    			noeudProchaine.setPosV(postemp);
	    		
	    		//	double maxGain= 0;
	    			String literal = "";
	    			String literaltmp = "";
	    			String removeAttr = "";
	    			System.out.print(noeudName+"&&"+lit+"\n"+"|  ?\n");
	    			removeAttr = choix(attrL, pos, neg);
	    			Attribut MaxGainAttribut = data.getAttributOfAttribut(removeAttr);
	    			Vector valeurs = MaxGainAttribut.getValues();
					Iterator ittttt = valeurs.iterator();
					while (ittttt.hasNext()) {
			    		String sttttt = (String)ittttt.next();
			    		literaltmp += "|     "+MaxGainAttribut.getAttribut()+"="+sttttt+"\n";
					}
	    			/*
	    			while (ittt.hasNext()) {
	    				String sttt = (String)ittt.next();
	    				double gainAttribut = calculGain(sttt, postemp, negtemp);
	    				if(gainAttribut > maxGain && gainAttribut >0) {
	    					maxGain = gainAttribut;
	    					Attribut MaxGainAttribut = data.getAttributOfAttribut(sttt);
	    					removeAttr =sttt;
	    					Vector valeurs = MaxGainAttribut.getValues();
	    					Iterator itttt = valeurs.iterator();
	    					literaltmp = "";
	    					while (itttt.hasNext()) {
	    			    		String stttt = (String)itttt.next();
	    			    		literaltmp += "|     "+MaxGainAttribut.getAttribut()+"="+stttt+"\n";
	    					}
	    				} 
			    	}*/
	    			Vector attrAllTemp1 = (Vector)attrAllTemp.clone();
	    			attrAllTemp1.removeElement(removeAttr);
	    			literal += literaltmp;
	    			noeudProchaine.addFils(literal);
	    			noeudProchaine.setfilsAttrName(removeAttr);
	    			noeudProchaine.setAttrL(attrAllTemp1);
	    			Arbre arbrehe = new Arbre();
	    			arbrehe.add(noeudProchaine);
	    			ResultatTmp +=arbrehe.getArbre();
    	    		if(!sonFilsIsFeuille(noeudProchaine)){
    	    			ResultatTmp +=constru2(noeudProchaine, arbrehe);
    	    		}
	    		}
	    	
	      	}
	       
	}
    return ResultatTmp;  
    }
    }


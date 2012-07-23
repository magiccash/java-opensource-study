import java.io.*;
import java.util.*;

public class LectureFichier{
 
    private String racine = "";

    LectureFichier(){
	racine = "";
    }

    LectureFichier(String racine){
	this.racine = racine;
    }
    
    public String lectureLigne(String file, int ligne){
	String line = ""; 
	try {
	    BufferedReader in = new BufferedReader(new FileReader(racine+""+file));
	    int index=0; 
	    while (((line=in.readLine()) != null) && (index < ligne)){
		index++;
	    }
	    in.close(); 
	}
	catch(java.io.FileNotFoundException e){System.out.println("Il n'a y pas de fichier");}
	catch(java.io.IOException e){System.out.println("erreur de lecture");}
	return line;
    } 

    public String lectureFichier(String file){
	String line = ""; 
	String ligne = "";
	try {
	    BufferedReader in = new BufferedReader(new FileReader(racine+""+file));
	    while ((line=in.readLine()) != null){
		ligne += line+"\n";
	    }
	    in.close(); 
	}
	catch(java.io.FileNotFoundException e){System.out.println("Il n'a y pas de fichier");}
	catch(java.io.IOException e){System.out.println("erreur de lecture");}
	return ligne;
    }

    public String[] lectureFichierTab(String file){
	String line = ""; 
	int nb = nbLigne(file);
	String[] ligne = new String[nb];
	int i =0;
	try {
	    BufferedReader in = new BufferedReader(new FileReader(racine+""+file));
	    while ((line=in.readLine()) != null){
		ligne[i] = line;
		i++;
	    }
	    in.close(); 
	}
	catch(java.io.FileNotFoundException e){System.out.println("Il n'a pas de fichier");}
	catch(java.io.IOException e){System.out.println("erreur de lecture");}
	return ligne;
    }

    public Vector lectureFichierVect(String file){
	String line = ""; 
	Vector ret = new Vector();
	try {
	    BufferedReader in = new BufferedReader(new FileReader(racine+""+file));
	    while ((line=in.readLine()) != null){
		ret.add(line);
	    }
	    in.close(); 
	}
	catch(java.io.FileNotFoundException e){System.out.println("Il n'a pas de fichier");}
	catch(java.io.IOException e){System.out.println("erreur de lecture");}
	
	return ret;
    }

    /**
     * lire un fichier dans un vecteur, les lignes ne commencant pas par les caracteres de mat sont prises en compte
     */
    public Vector lectureFichierVectNonSpecial(String file, String mat) {
	String line = ""; 
	int nb = nbLigne(file);
	Vector ret = new Vector(nb);
	try {
	    BufferedReader in = new BufferedReader(new FileReader(racine+""+file));
	    while ((line=in.readLine()) != null){
		if (!line.matches("^["+mat+"].*") && !line.matches("^ *") && !line.matches("")) {
		    ret.add(line);		    
		}
	    }
	    in.close(); 
	}
	catch(java.io.FileNotFoundException e){System.out.println("Il n'a pas de fichier");}
	catch(java.io.IOException e){System.out.println("erreur de lecture");}
	return ret;
    }

    /**
     * lire un fichier dans un vecteur, les lignes commencants par mat sont prit en compte
     */
    public Vector lectureFichierVectSpecial(String file, String mat) {
	String line = ""; 
	int nb = nbLigne(file);
	Vector ret = new Vector(nb);
	try {
	    BufferedReader in = new BufferedReader(new FileReader(racine+""+file));
	    while ((line=in.readLine()) != null){
		if (line.matches("^"+mat+".*")) {
		    ret.add(line);
		}
	    }
	    in.close(); 
	}
	catch(java.io.FileNotFoundException e){System.out.println("Il n'a pas de fichier");}
	catch(java.io.IOException e){System.out.println("erreur de lecture");}
	return ret;
    } 

    public int nbLigne(String file){
	int nb = 0;
	try {
	    BufferedReader in = new BufferedReader(new FileReader(racine+""+file));
	    while ((in.readLine()) != null){
		nb++;
	    }
	    in.close(); 
	}
	catch(java.io.FileNotFoundException e){System.out.println("Il n'a pas de fichier");}
	catch(java.io.IOException e){System.out.println("erreur de lecture");}
	return nb;
    }
}

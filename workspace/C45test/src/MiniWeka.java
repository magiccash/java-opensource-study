import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
 
public class MiniWeka extends JFrame implements ActionListener{
  
    private JPanel pan =  new JPanel();
    private MenuBar mb = new MenuBar();
    private Menu menuFile = new Menu(" File ");
    private Menu menuTools = new Menu(" Tools ");
    private Menu menuHelp = new Menu(" Help ");
    private MenuItem menuOpen = new MenuItem(" open file...");
    private MenuItem menuQuit = new MenuItem(" quit ");
    private MenuItem menuAppr = new MenuItem(" Apprendre ");
    private MenuItem menuAbout = new MenuItem(" About mini-weka... ");
    private JButton butAttr = new JButton("Arbre-->");

    private JScrollPane scrollex;
    private JScrollPane scrollat1;
    private JScrollPane scrollat2;
    private JScrollPane scrollre;
    private JScrollPane scrollexRestP;
    
    private JLabel gainL = new JLabel("Resultat");
    private JTextField gain = new JTextField(30);

    private JLabel conceptL = new JLabel("concept");

    private JLabel attributL = new JLabel("attributs");
    private JTextArea attributT1 = new JTextArea(10,5);
    private JTextArea attributT2 = new JTextArea(10,15);

    private JLabel exempleL = new JLabel("exemples");
    private JTextArea exempleT = new JTextArea(10,10);

    private JLabel exempleRestPL = new JLabel("exemples positifs non couvert");
    private JTextArea exempleRestPT = new JTextArea(10,10);

    private JTextArea regle = new JTextArea(10,30);

    private JFileChooser fileC = new JFileChooser();

    private GridBagLayout gridBag = new GridBagLayout();
    private GridBagConstraints constraints = new GridBagConstraints();

    private Apprentissage appr ;
   // private ApprendreAttribut apprA;
  
    MiniWeka() {
	super("mini-weka");
	this.setSize(900,500);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	menuOpen.addActionListener(this);
	menuQuit.addActionListener(this);
	menuAppr.addActionListener(this);
	fileC.addActionListener(this);
	menuAppr.addActionListener(this);
	butAttr.addActionListener(this);
	menuAbout.addActionListener(this);
	
	scrollex = new JScrollPane(exempleT,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scrollat1 = new JScrollPane(attributT1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
	scrollat2 = new JScrollPane(attributT2,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scrollre = new JScrollPane(regle,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scrollexRestP = new JScrollPane(exempleRestPT,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	attributT1.setEditable(false);
	attributT2.setEditable(false);
	exempleT.setEditable(false);
	exempleRestPT.setEditable(false);	
	regle.setEditable(false);
	gain.setEditable(false);
	
	gain.setText("couverture les regles");

	Color c = new Color(200,200,220);

	attributT1.setLineWrap(false);
	attributT2.setLineWrap(true);
	exempleT.setLineWrap(true);
	exempleRestPT.setLineWrap(true);	
	regle.setLineWrap(true);

	pan.setLayout(gridBag);

        buildConstraints(constraints,0,0,3,1,0,10);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(conceptL,constraints);
        pan.add(conceptL);

        buildConstraints(constraints,0,1,3,1,0,10);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(attributL,constraints);
        pan.add(attributL);

        buildConstraints(constraints,0,3,1,1,24,20);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(scrollat1,constraints);
        pan.add(scrollat1);

        buildConstraints(constraints,1,3,1,1,2,20);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(butAttr,constraints);
        pan.add(butAttr);

        buildConstraints(constraints,2,3,1,1,24,0);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(scrollat2,constraints);
        pan.add(scrollat2);

        buildConstraints(constraints,0,4,3,1,0,20);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(exempleL,constraints);
        pan.add(exempleL);

        buildConstraints(constraints,0,5,3,1,0,20);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(scrollex,constraints);
        pan.add(scrollex);

        buildConstraints(constraints,3,0,3,1,50,0);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(gainL,constraints);
        pan.add(gainL);

        buildConstraints(constraints,3,1,3,1,0,0);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(gain,constraints);
        pan.add(gain);

        buildConstraints(constraints,3,2,3,2,0,0);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(scrollre,constraints);
        pan.add(scrollre);

        buildConstraints(constraints,3,4,3,1,0,0);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(exempleRestPL,constraints);
        pan.add(exempleRestPL);
	
        buildConstraints(constraints,3,5,3,1,0,0);
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(scrollexRestP,constraints);
        pan.add(scrollexRestP);

	setMenuBar(mb);
	this.addMenuBar();
	setContentPane(pan);
	
	show();
    }

    public void addMenuBar() {
	menuFile.add(menuOpen);
	menuFile.addSeparator();
	menuFile.add(menuQuit);
	menuTools.add(menuAppr);
	menuHelp.add(menuAbout);
	mb.add(menuFile);
	mb.add(menuTools);
	mb.add(menuHelp);
	
    }

    public void buildConstraints(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy){
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = wx;
        gbc.weighty = wy;
    }

    public void actionPerformed(ActionEvent evt){
	Object src = evt.getSource();
        if (src == menuOpen) {
	    fileC.showOpenDialog(pan);
	}
	if (src == fileC) {       	
	    String file = fileC.getSelectedFile().getAbsolutePath();
	    Gain g; 
	    g = new GainAsk(); 
	    appr = new Apprentissage(file,g);
	    conceptL.setText("concept -- "+appr.getConcept());
	   
	    Vector attrV = appr.getAttributsAllVector();
	    attrV.remove(attrV.lastElement());
	    Iterator it = attrV.iterator();
	    double gainExpression;
	    String gainAll = "";
	    String attrString = "";
	    while (it.hasNext()) {
	    String attrthis = (String)it.next();
		gainExpression =appr.calculGain(attrthis);
		gainAll +=gainExpression + "\n";
		attrString += attrthis + "\n";
		//System.out.println((i+1)+": "+attrthis+" ----->valeur de Gain est: "+gainExpression);
		//i++;

	    }
	    //attributT1.setText(appr.getAttributsA());
	    attributT1.setText(attrString);
	    exempleT.setText(appr.getExemples()+"\n**********************************\n"+
	    				"Total de exemples positives  :"+appr.getNPos()+"\n"+
	    				"Total de exemples negatives :"+appr.getNNeg()+
	    				"\n**********************************\n"+"Pour chaque attribut le valeur de Gain est suivant:\n\n"+gainAll);
	    regle.setText("");
	    exempleRestPT.setText("");
	    attributT2.setText("");
	    exempleL.setText("exemples - "+appr.getExemplesSize());
	    /*********************************************************/
	    //appr.construArbreParUtilisateur2();

	    
	}
	/**
	 * Affichier l'arbre de décision par la methode ce que on a appris dans le cours
	 */
	if (src == menuAppr) {
	    if (!conceptL.getText().equals("concept")) {   
		Gain g;
		g = new GainAsk();
		appr.setGain(g);
		appr.apprendre();
		regle.setText(appr.getRegles());
		exempleRestPT.setText(appr.getVectExString(appr.getVectPosNonCouv()));
	    }
	}
	if (src == menuQuit) {
	    this.dispose();
	    System.exit(0);  
	}
	if (src == butAttr) {	    
		String st = attributT1.getSelectedText();	    
	    if (st != null) {	
	    	attributT2.setText(appr.construArbreParUtilisateur(st));
	    	try {
				appr.construArbreParUtilisateur2(st);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	if (src == menuAbout) {
		String message = "Comment utiliser Mini-weka:\n"+
						"*** Appuyer 'File-->Open file' pour charger un fichier\n"+
						"*** Appuyer 'Tools-->Apprendre' pour implémenter l'algorithme de couverture\n"+
						"*** Choisir le attribut et puis appuyer 'Arbre-->' pour implémenter l'algorithme Arbres de décision\n";
		JOptionPane.showMessageDialog(null,message);
	}
	
	}
      

    public static void main(String[] args){
        MiniWeka i = new MiniWeka();
        
    }

}

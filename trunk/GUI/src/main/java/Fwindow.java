/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Fwindow.java
 *
 * Created on Nov 17, 2011, 7:34:55 AM
 */
// VOIR POUR CHANGER LA PILE ET LA METTRE DANS LE BON SENS ! 
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.tree.TreeModel;

 
public class Fwindow extends javax.swing.JFrame {
    
    private boolean fileExists = false;
    private String pathFile = "";
    private File currentFile = null;
Interpreteur interminijaja;
Interpreteur_Jajacode interjajacode;
Compilateur_minijaja compilo  = new Compilateur_minijaja(); 
boolean firstParser = true;
    // Delcaration de la memoire
    Pile pile;
    Tas_Tas tas;
    private JTextArea textSortie;
    private JTextArea textErreurs;
    private TextAreaLineNumber taln;
    Vector<String> pilemjj;
    Vector<String> tasmjj;
    Vector<String> pilejjc;
    Vector<String> tasjjc;

    
    
    
    /** Creates new form Fwindow */
    public Fwindow() {
        initComponents();
 
        JScrollPane panel1 = new JScrollPane();
        JScrollPane panel2 = new JScrollPane();
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        jTabbedPane1.addTab("Sortie", null, panel1, "Sortie");
        jTabbedPane1.addTab("Erreurs", null, panel2, "Erreurs");
        
        textSortie = new JTextArea();
        textErreurs = new JTextArea();

        textSortie.setEditable(false);
        textErreurs.setEditable(false);
        panel1.getViewport().add(textSortie);
        panel2.getViewport().add(textErreurs);
        
        jButton1.setIcon(new ImageIcon("src/main/java/icons/New.png"));
        jButton2.setIcon(new ImageIcon("src/main/java/icons/Addmjj.png"));
        jButton3.setIcon(new ImageIcon("src/main/java/icons/Save.png"));
        jButton4.setIcon(new ImageIcon("src/main/java/icons/Playmjj.png"));
        jButton5.setIcon(new ImageIcon("src/main/java/icons/old-go-bottommjj.png"));
        jButton6.setIcon(new ImageIcon("src/main/java/icons/old-go-downmjj.png"));
        jButton7.setIcon(new ImageIcon("src/main/java/icons/Stopmjj.png"));
        jButton8.setText("");
        jButton8.setIcon(new ImageIcon("src/main/java/icons/Up.png"));
        jButton9.setText("");
        jButton9.setIcon(new ImageIcon("src/main/java/icons/Playjjc.png"));
        jButton10.setText("");
        jButton10.setIcon(new ImageIcon("src/main/java/icons/old-go-bottomjjc.png"));
        jButton11.setText("");
        jButton11.setIcon(new ImageIcon("src/main/java/icons/old-go-downjjc.png"));
        jButton12.setText("");
        jButton12.setIcon(new ImageIcon("src/main/java/icons/Stopjjc.png"));
        
        jMenuItem2.setIcon(new ImageIcon("src/main/java/icons/New2.png"));
        jMenuItem1.setIcon(new ImageIcon("src/main/java/icons/Add2mjj.png"));
        jMenuItem3.setIcon(new ImageIcon("src/main/java/icons/Save2.png"));
        jMenuItem4.setIcon(new ImageIcon("src/main/java/icons/Play2mjj.png"));
        jMenuItem5.setIcon(new ImageIcon("src/main/java/icons/old-go-bottom2mjj.png"));
        jMenuItem6.setIcon(new ImageIcon("src/main/java/icons/old-go-down2mjj.png"));
        jMenuItem7.setIcon(new ImageIcon("src/main/java/icons/Stop2mjj.png"));
        jMenuItem8.setIcon(new ImageIcon("src/main/java/icons/Up2.png"));
        jMenuItem9.setIcon(new ImageIcon("src/main/java/icons/Play2jjc.png"));
        jMenuItem10.setIcon(new ImageIcon("src/main/java/icons/old-go-bottom2jjc.png"));
        jMenuItem11.setIcon(new ImageIcon("src/main/java/icons/old-go-down2jjc.png"));
        jMenuItem12.setIcon(new ImageIcon("src/main/java/icons/Stop2jjc.png"));

        taln = new TextAreaLineNumber();
        taln.jsp.setSize(jPanel2.getSize());

        jPanel2.add(taln.jsp);


       /* pilemjj = new Vector<String>();
        tasmjj = new Vector<String>();
        pilejjc = new Vector<String>();
        tasjjc = new Vector<String>();*/

       /* jList1.setListData(pilejjc);
        jList2.setListData(tasjjc);
        jList3.setListData(pilemjj);
        jList4.setListData(tasmjj);*/
       // interminijaja = new Interpreteur(jList3,jList4, textSortie, textErreurs);
        interjajacode = new Interpreteur_Jajacode(jList1,jList2, textSortie, textErreurs);
    }


    public void nouveauFichier()
    {
        if(fileExists)
        {
            Object[] options = {"Oui", "Non"};
            JOptionPane dial = new JOptionPane();
            int n = dial.showOptionDialog(this,
            "Voulez-vous enregistrer le fichier ?",
            "Enregistrer le fichier",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        
            if(n == 0)
                sauvegarder();
        }
        
        taln.jta.setText("");
        aff_sortie("Creation d'un nouveau fichier");
        fileExists = false;
        pathFile = "";
        currentFile = null;
    }

    public void ouvrirFichier()
    {
        if(fileExists)
        {
            Object[] options = {"Oui", "Non"};
            JOptionPane dial = new JOptionPane();
            int n = dial.showOptionDialog(this,
            "Voulez-vous enregistrer le fichier ?",
            "Enregistrer le fichier",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        
            if(n == 0)
                sauvegarder();
        }
        
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fichier = fc.getSelectedFile();
            currentFile = fichier;
            pathFile = fichier.getAbsolutePath();

            String chaine = "";
           try{
			InputStream ips=new FileInputStream(fichier);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				chaine+=ligne+"\n";
			}
			br.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}


           taln.jta.setText(chaine);
            aff_sortie("Ouverture de " + fichier.getName());
            fileExists = true;
        } else {
        }
    }

   

    public void sauvegarder()
    {
      if(fileExists)
      {
        try
	{
		FileWriter fw = new FileWriter(pathFile, false);
		BufferedWriter output = new BufferedWriter(fw);
		output.write(taln.jta.getText().replaceAll("\r", "\n"));
		output.flush();
		output.close();
		aff_sortie(currentFile.getName() + " enregistre");
	}
	catch(Exception e){
            aff_sortie("Erreur enregistrement " + currentFile.getName());
        }
      }
      else
      {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File fichier = fc.getSelectedFile();
            pathFile = fichier.getAbsolutePath();
            currentFile = fichier;

            String chaine = "";
           try{
               FileWriter fw = new FileWriter(pathFile, false);
		BufferedWriter output = new BufferedWriter(fw);
		output.write(taln.jta.getText().replaceAll("\r", "\n"));
		output.flush();
		output.close();
                fileExists = true;
		aff_sortie(currentFile.getName() + " enregistre");
           }
           catch (Exception e){
			System.out.println(e.toString());
		}
        }


      }
    }

    public void executer()
    {
        if(taln.jta.getText().isEmpty()){
            aff_sortie("Debut de l'interpretation");
            aff_sortie("Aucun fichier a interpreter");
            aff_sortie("Fin de l'interpretation");
        } else {
             sauvegarder();
        jButton4.setEnabled(false);
        jMenuItem4.setEnabled(false);

        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);

        jMenuItem5.setEnabled(true);
        jMenuItem6.setEnabled(true);
        jMenuItem7.setEnabled(true);
            aff_sortie("Debut de l'interpretation");
           // try {
                Vector<String> pilev = new Vector<String>();
                Vector<String> tas = new Vector<String>();

                // Permet d'instancier le parser et l'interpreteur
                //interminijaja.parse(pathFile);
                //Thread th_minijaja = new Thread(interminijaja);
                //th_minijaja.start();


                  interminijaja = new Interpreteur(jList3,jList4, textSortie, textErreurs);
                  interminijaja.setSettings(pathFile, firstParser);
                  interminijaja.pause = true;
                    interminijaja.start();
                
                 firstParser = false;

                 // Partie pour Jajacode !!!!
              /*  System.out.println("PATH : "+pathFile);
                    interjajacode.setSettings(pathFile, firstParser);
                if(firstParser)
                    interjajacode.start();
                else
                    interjajacode.run();

                 firstParser = false;*/


                 // Fin de partie pour Jajacode !

                // On recupere la pile une premiere fois
               /* tas = interminijaja.tas.get_Tas();
                pilev = interminijaja.pile.get_PileV();
                
                    for (int i=0; i< tas.size(); i++) {
                        tasmjj.add(tas.elementAt(i).toString());
                    }
                
                    for (int i=0; i< pilev.size(); i++) {
                        pilemjj.add(pilev.elementAt(i).toString());
                    }*/
                                       
/*
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Fwindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Fwindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MiniJajaVisitorException ex) {
                Logger.getLogger(Fwindow.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }

        jList1.updateUI();
        jList2.updateUI();
        jList3.updateUI();
        jList4.updateUI();
    }
    
    private void executerjjc()
    {
         if(taln.jta.getText().isEmpty()){
            aff_sortie("Debut de l'interpretation");
            aff_sortie("Aucun fichier a interpreter");
            aff_sortie("Fin de l'interpretation");
        } else {
             sauvegarder();
        jButton9.setEnabled(false);
       jMenuItem9.setEnabled(false);

        jButton10.setEnabled(true);
        jButton11.setEnabled(true);
        jButton12.setEnabled(true);

        jMenuItem10.setEnabled(true);
        jMenuItem11.setEnabled(true);
        jMenuItem12.setEnabled(true);
         interjajacode.setSettings(pathFile, firstParser);
                if(firstParser)
                    interjajacode.start();
                else
                    interjajacode.run();

                 firstParser = false;
        
         }
    }

    public void stop()
    {
        aff_sortie("Fin de l'interpretation");
        interminijaja.stopp();
        jButton4.setEnabled(true);
        jMenuItem4.setEnabled(true);

        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);

        jMenuItem5.setEnabled(false);
        jMenuItem6.setEnabled(false);
        jMenuItem7.setEnabled(false);

        jList1.clearSelection();
        jList2.clearSelection();
        jList3.clearSelection();
        jList4.clearSelection();
        
        jList1.clearSelection();
        jList2.clearSelection();
        jList3.clearSelection();
        jList4.clearSelection();

        jList1.updateUI();
        jList2.updateUI();
        jList3.updateUI();
        jList4.updateUI();
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        nouveauFichier();
    }                                          

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        ouvrirFichier();
    }                                          

    
    // bouton enregistrer
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
      sauvegarder();
    }                                          

                                      

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        nouveauFichier();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        ouvrirFichier();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        sauvegarder();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
        ProchaineInstruction();
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        stop();
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        executer();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)  {
        executer();
    }


    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
        stop();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void aff_sortie(String message) {
        GregorianCalendar heure = new GregorianCalendar();
        textSortie.append("[Infos] [" + heure.getTime().getHours() + ":" + heure.getTime().getMinutes()+ "] : " + message+"\n");
    }
    
    private void aff_erreurs(String message) {
        GregorianCalendar heure = new GregorianCalendar();
        textErreurs.append("[Erreur] [" + heure.getTime().getHours() + ":" + heure.getTime().getMinutes()+ "] : " + message+"\n");
        textErreurs.updateUI();
    }
     

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    void ProchaineInstruction()
    {
        //taln.ProchaineInstruction();
        interminijaja.next();
    }
    
    void prochaineinstructionjjc()
    {
        interjajacode.next();
    }
    
    void stopjjc()
    {
        interjajacode.stopp();
    }
    
    private void compiler()
    {
        sauvegarder();
        try {
            //compilo = new Compilateur_minijaja(new FileReader(new File(pathFile)));
            try {
                String s;
                s = compilo.compile_MiniJaja(new FileReader(new File(pathFile)), firstParser);
                jTextArea1.setText(s);
                
                firstParser = false;
                
            } catch(MiniJajaVisitorException e) {
                System.err.println(e.toString());
            }
        } catch(FileNotFoundException ex) {
            Logger.getLogger(Fwindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1062, 640));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new java.awt.Dimension(236, 200));

        jButton1.setToolTipText("Nouveau fichier (Ctrl + N)");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setToolTipText("Ouvrir un fichier (Ctrl + O)");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setToolTipText("Enregistrer (Ctrl + S)");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator1);

        jButton4.setToolTipText("Executer (F5)");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        jButton4.getAccessibleContext().setAccessibleDescription("");

        jButton5.setToolTipText("Continuer jusqu'au prochain point d'arret (F5)");
        jButton5.setEnabled(false);
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setToolTipText("Passer a l'instruction suivante (F11)");
        jButton6.setEnabled(false);
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setToolTipText("Arreter l'execution (Shift + F5)");
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);
        jButton7.getAccessibleContext().setAccessibleDescription("");

        jToolBar1.add(jSeparator3);

        jButton8.setText("Compiler");
        jButton8.setToolTipText("Compiler");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);
        jToolBar1.add(jSeparator4);

        jButton9.setText("jButton9");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton10.setText("jButton10");
        jButton10.setEnabled(false);
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton10);

        jButton11.setText("jButton11");
        jButton11.setEnabled(false);
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton12.setText("jButton12");
        jButton12.setEnabled(false);
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);

        jTabbedPane1.setAutoscrolls(true);

        jList1.setToolTipText("Pile Jajacode");
        jScrollPane3.setViewportView(jList1);

        jList2.setToolTipText("Tas Jajacode");
        jScrollPane4.setViewportView(jList2);

        jList3.setToolTipText("Pile Minijaja");
        jScrollPane5.setViewportView(jList3);

        jList4.setToolTipText("Tas Minijaja");
        jScrollPane6.setViewportView(jList4);

        jLabel1.setText("Pile Minijaja");

        jLabel2.setText("Pile Jajacode");

        jLabel3.setText("Tas Minijaja");

        jLabel4.setText("Tas Jajacode");

        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jMenu1.setText("Fichier");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Nouveau fichier");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Ouvrir un fichier");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Enregistrer");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Executer");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem4.setText("Executer le code Minijaja");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem5.setText("Continuer jusqu'au prochain point d'arret");
        jMenuItem5.setEnabled(false);
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        jMenuItem6.setText("Passer a l'instruction suivante");
        jMenuItem6.setEnabled(false);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProchaineInstruction(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem7.setText("Arreter l'execution");
        jMenuItem7.setEnabled(false);
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);
        jMenu2.add(jSeparator2);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem8.setText("Compiler");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);
        jMenu2.add(jSeparator5);

        jMenuItem9.setText("Executer le code Jajacode");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Continuer jusqu'au prochain point d'arret");
        jMenuItem10.setEnabled(false);
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Passer a l'instruction suivante");
        jMenuItem11.setEnabled(false);
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Arreter l'execution");
        jMenuItem12.setEnabled(false);
        jMenu2.add(jMenuItem12);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1224, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                .addGap(27, 27, 27)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentResized
        // TODO add your handling code here:
         // taln.jsp.setSize(jPanel2.getSize());
         repaint();
    }//GEN-LAST:event_jPanel2ComponentResized

    private void ProchaineInstruction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProchaineInstruction
        // TODO add your handling code here:
        ProchaineInstruction();
    }//GEN-LAST:event_ProchaineInstruction

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        compiler();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        compiler();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        executerjjc();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        executerjjc();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        prochaineinstructionjjc();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        stopjjc();
    }//GEN-LAST:event_jButton12ActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}

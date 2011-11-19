/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Fwindow.java
 *
 * Created on Nov 17, 2011, 7:34:55 AM
 */

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


/**
 *
 * @author ubuntu1
 */
 
public class Fwindow extends javax.swing.JFrame {
    private boolean fileExists = false;
    private String openFile = "";

    // Delcaration de la memoire
    Pile pile;
    Tas_Tas tas;

    /** Creates new form Fwindow */
    public Fwindow() {
        initComponents();

        // on cree les icones pour les tabpanel
        ImageIcon warning = createImageIcon("icons/warning.jpg");
            ImageIcon error = createImageIcon("icons/error.gif");

  
        JComponent panel1 = new JPanel();
        JComponent panel2 = new JPanel();
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        jTabbedPane1.addTab("Sortie", warning, panel1, "Sortie");
        jTabbedPane1.addTab("Erreurs", error, panel2, "Erreurs");
        
        textSortie = new JTextArea();

        // Permet de rendre le texte area non ediable
        textSortie.setEditable(false);
        textSortie.setAlignmentX(LEFT_ALIGNMENT);
        panel1.add(textSortie);

        //jEditTextArea1.setTokenMarker(new JavaTokenMarker());

        taln = new TextAreaLineNumber();
        taln.jsp.setSize(jPanel2.getSize());

        jPanel2.add(taln.jsp);


        pilemjj = new Vector<String>();
        tasmjj = new Vector<String>();
        pilejjc = new Vector<String>();
        tasjjc = new Vector<String>();

        jList1.setListData(pilejjc);
        jList2.setListData(tasjjc);
        jList3.setListData(pilemjj);
        jList4.setListData(tasmjj);
    }


    public void nouveauFichier()
    {
        taln.jta.setText("");
        aff_sortie("> Creation d'un nouveau fichier");
        fileExists = false;
        openFile = "";
    }

    public void ouvrirFichier()
    {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fichier = fc.getSelectedFile();
            openFile = fichier.getAbsolutePath();

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
            aff_sortie("> Ouverture de " + fichier.getName());
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
		FileWriter fw = new FileWriter(openFile, false);
		BufferedWriter output = new BufferedWriter(fw);
		output.write(taln.jta.getText().replaceAll("\r", "\n"));
		output.flush();
		output.close();
		aff_sortie("> Fichier enregistre");
	}
	catch(Exception e){
	aff_sortie("> Erreur enregistrement fichier");
        }
      }
      else
      {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File fichier = fc.getSelectedFile();
            openFile = fichier.getAbsolutePath();

            String chaine = "";
           try{
               FileWriter fw = new FileWriter(openFile, false);
		BufferedWriter output = new BufferedWriter(fw);
		output.write(taln.jta.getText().replaceAll("\r", "\n"));
		output.flush();
		output.close();
                fileExists = true;
		aff_sortie("> Fichier enregistre");
           }
           catch (Exception e){
			System.out.println(e.toString());
		}
        }


      }
    }

    public void executer()
    {
        jButton4.setEnabled(false);
        jMenuItem4.setEnabled(false);

        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);

        jMenuItem5.setEnabled(true);
        jMenuItem6.setEnabled(true);
        jMenuItem7.setEnabled(true);

        jList1.updateUI();
        jList2.updateUI();
        jList3.updateUI();
        jList4.updateUI();
    }

    public void stop()
    {
        jButton4.setEnabled(true);
        jMenuItem4.setEnabled(true);

        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);

        jMenuItem5.setEnabled(false);
        jMenuItem6.setEnabled(false);
        jMenuItem7.setEnabled(false);

        pilemjj.clear();
        tasmjj.clear();
        pilejjc.clear();
        tasjjc.clear();

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
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        stop();
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        executer();
    }

    // Ma petite methode a garder car je voudrais implementer des icone devant certaine lignes de texte Merci !
    private ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    protected static ImageIcon createImageIcon(String path) {
        URL imgURL = Fwindow.class.getResource(path);
        System.out.println(imgURL);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find image in system: " + path);
            return null;
        }
    }


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)  {
        if(taln.jta.getText().isEmpty()){
            System.out.println("ya rien ");
            aff_sortie("[Informations] : Aucun fichier.");
          // Merci de laisser le code !
            //ImageIcon icon = createImageIcon("icons/Stop2.png", "sa marche de la foluieeeee");
            //label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
          // Fin de merci de laisser le code
        } else {
            aff_sortie("[Informations] : On balance l'interpreteur.");
            try {
                Vector<String> pilev = new Vector<String>();
                Vector<String> tas = new Vector<String>();
                executer();
                String lefile = "un file de fou duingue";


                // Permet d'instancier le parser et l'interpreteur
                Inter interminijaja = new Inter();
                interminijaja.parse(lefile);

                // On recupere la pile une premiere fois
                tas = interminijaja.tas.get_Tas();
                pilev = interminijaja.pile.get_PileV();
                
                    for (int i=0; i< tas.size(); i++) {
                        tasmjj.add(tas.elementAt(i).toString());
                    }
                
                    for (int i=0; i< pilev.size(); i++) {
                        pilemjj.add(pilev.elementAt(i).toString());
                    }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Fwindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Fwindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MiniJajaVisitorException ex) {
                Logger.getLogger(Fwindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
        stop();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void aff_sortie(String message) {
        textSortie.append(message+"\n");
    }
    
    private JTextArea textSortie;
    private TextAreaLineNumber taln;
    Vector<String> pilemjj;
    Vector<String> tasmjj;
    Vector<String> pilejjc;
    Vector<String> tasjjc;
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
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
        jToolBar2 = new javax.swing.JToolBar();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jProgressBar1 = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1062, 640));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new java.awt.Dimension(236, 200));

        jButton1.setText("new");
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

        jButton2.setText("+");
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

        jButton3.setText("save");
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

        jButton4.setText("exe");
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

        jButton5.setText("conti");
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

        jButton6.setText("contia");
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

        jButton7.setText("stop");
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

        jScrollPane1.setViewportView(jTree1);

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
            .addGap(0, 482, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setFocusable(false);

        jLabel5.setText("Voici une barre du bas ^^ et le truc a cote : progress bar !!!");
        jToolBar2.add(jLabel5);
        jToolBar2.add(jSeparator3);
        jToolBar2.add(jProgressBar1);

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
        jMenuItem4.setText("Executer");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);
        jMenu2.add(jSeparator2);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem5.setText("Continuer jusqu'au prochain point d'arret");
        jMenuItem5.setEnabled(false);
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        jMenuItem6.setText("Passer a l'instruction suivante");
        jMenuItem6.setEnabled(false);
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

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
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
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addGap(27, 27, 27)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentResized
        // TODO add your handling code here:
         // taln.jsp.setSize(jPanel2.getSize());
         repaint();
    }//GEN-LAST:event_jPanel2ComponentResized

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fwindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}

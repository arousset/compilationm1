import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
/*import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;*/

public class TextAreaLineNumber extends JComponent implements MouseInputListener, DocumentListener, FocusListener{

        public JTextArea jta;
	private JTextArea lines;
        public JScrollPane jsp;
        public int breakPoint[];
        public int PointToPointPosition = 0;
        public int nbBreakPoint = 0;

	public TextAreaLineNumber(){
		//super();

                breakPoint = new int[50];
                for(int i = 0; i < 50;i++)
                    breakPoint[i] = 0;
            
		/* Set up JtextArea */
		jta = new JTextArea();
		jta.getDocument().addDocumentListener(this);

		/* Set up Highlighter */
		//highlighter = jta.getHighlighter();

		/* Set up line numbers */
		lines = new JTextArea("    1");
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setEditable(false);
		lines.addMouseListener(this);
		lines.addFocusListener(this);

		/* Set up scroll pane */
		jsp = new JScrollPane();
		jsp.getViewport().add(jta);
		jsp.setRowHeaderView(lines);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jta.setText("");
	}

	/* Document Listener Events */
	public void changedUpdate(DocumentEvent de) {
		lines.setText(getText());
	}
	public void insertUpdate(DocumentEvent de) {
		lines.setText(getText());
	}
	public void removeUpdate(DocumentEvent de) {
		lines.setText(getText());
	}
	public String getText(){
		int caretPosition = jta.getDocument().getLength();
		Element root = jta.getDocument().getDefaultRootElement();
                boolean exists = false;
                for(int j = 0; j < 50;j++)
                {
                    if(breakPoint[j] == 1)
                        exists = true;
                }
                String text = "";
                if(exists)
                    {
                        if(1 == PointToPointPosition)
                            text += "[+] ";
                        else
                            text += "[  ] ";
                    }
                    else
                    {
                        if(1 == PointToPointPosition)
                            text += " +  ";
                        else
                            text += "    "; 
                    }
                text += "1\n";
                exists = false;
		for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++)
                {
                    for(int j = 0; j < 50;j++)
                     {
                         if(breakPoint[j] == i)
                              exists = true;
                       }
                    /*if(exists)
			text += "[+] " + i + "\n";
                    else
                        text += "     " + i + "\n";*/
                    if(exists)
                    {
                        if(i == PointToPointPosition)
                            text += "[+] ";
                        else
                            text += "[  ] ";
                    }
                    else
                    {
                        if(i == PointToPointPosition)
                            text += " +  ";
                        else
                            text += "    "; 
                    }
                    text += i + "\n";
                    exists = false;
                }
		return text;
	}
        
        void ProchaineInstruction()
        {
            PointToPointPosition++;
            lines.setText(getText());
            jsp.repaint();
        }

	/* Mouse Listener Events */
	public void mouseClicked(MouseEvent me) {

			try {
				int caretPos = lines.getCaretPosition();
				int lineOffset = lines.getLineOfOffset(caretPos);
				if(lines.getText().charAt(caretPos-1) == '\n')
					lineOffset--;
				/*highlighter.addHighlight(jta.getLineStartOffset(lineOffset),
										 jta.getLineEndOffset(lineOffset), (HighlightPainter) new MyHilighter(Color.cyan));*/
                                boolean exists = false;
                                boolean stop = false;
                                for(int i = 0; i < 50;i++)
                                 {
                                     if(!stop)
                                     {
                                         if(breakPoint[i] == lineOffset+1)
                                         {
                                             breakPoint[i] = 0;
                                             stop = true;
                                             exists = true;
                                         }
                                         
                                     }
                                 }
                                stop = false;
                                if(!exists)
                                {
                                    for(int i = 0; i < 50;i++)
                                    {
                                        if(!stop)
                                        {
                                            if(breakPoint[i] == 0)
                                            {
                                                breakPoint[i] = lineOffset+1;
                                                stop = true;
                                            }
                                        }
                                    }
                                }
                                System.out.println(lineOffset);
                                printTabBreak();
                                lines.setText(getText());
                                jsp.repaint();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
	}
        
        public void printTabBreak()
        {
            String s = "[";
            for(int i = 0; i < 50;i++)
            {
                s += breakPoint[i]+", ";
            }
            s +="]";
            System.out.println(s);
        }
        
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseDragged(MouseEvent me) {}
	public void mouseMoved(MouseEvent me) {}

	/* Focus Listener Events for line numbers*/
	public void focusGained(FocusEvent fe) {}
	public void focusLost(FocusEvent fe) {
		//highlighter.removeAllHighlights();
	}

}

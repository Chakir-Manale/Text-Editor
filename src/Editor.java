import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;


public class Editor extends JFrame  implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JTextPane zoneText;
	private JMenuBar menubar;
	private JMenu file,edit;
	private JMenuItem nouveau,save,open,TextColor,item,help;
	private StyledDocument doc;
	private JLabel statusInfo,words;
	private int count=0;
	private JPanel panel;
	public Editor() {
		
		//Composantes:
		zoneText=new JTextPane();
		menubar = new JMenuBar();
		file=new JMenu("file");
		edit=new JMenu("edit");
		doc = new DefaultStyledDocument();
		panel=new JPanel(new GridLayout(1,2));
		
		//creating icons 
		ImageIcon i=new ImageIcon("help.jpg");
		ImageIcon i1= new ImageIcon("new.png");
	    ImageIcon i2= new ImageIcon("open.jpg");
	    ImageIcon i3= new ImageIcon("save.png");
		
		  i1.setImage(i1.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH));
		  i2.setImage(i2.getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH));
		  i3.setImage(i3.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH));
		  i.setImage(i.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH));
		
		  //create menuItems :
		  help=new JMenuItem("help");
		  help.setIcon(i);
		  nouveau=new JMenuItem("new");
		  nouveau.setIcon(i1);
		  save=new JMenuItem("save");
		  save.setIcon(i3);
		  open=new JMenuItem("open");
		  open.setIcon(i2);
		  TextColor=new JMenuItem("Text Color");
		  
		  //fonts 
		  JMenu font = new JMenu( "Font" );
		    font.add( item = new JMenuItem( "12" ) );
		    item.addActionListener(
		      new StyledEditorKit.FontSizeAction( "font-size-12", 12 ) );
		    font.add( item = new JMenuItem( "24" ) );
		    item.addActionListener(
		      new StyledEditorKit.FontSizeAction( "font-size-24", 24 ) );
		    font.add( item = new JMenuItem( "36" ) );
		    item.addActionListener( 
		      new StyledEditorKit.FontSizeAction( "font-size-36", 36 ) );
		    font.addSeparator();
		    font.add( item = new JMenuItem( "Serif" ) );
		    item.setFont( new Font( "Serif", Font.PLAIN, 12 ) );
		    item.addActionListener(
		      new StyledEditorKit.FontFamilyAction( "font-family-Serif", "Serif" ) );
		    font.add(item = new JMenuItem ("SansSerif"));
		    item.setFont( new Font( "SansSerif", Font.PLAIN, 12 ) );
		    item.addActionListener(new
		      StyledEditorKit.FontFamilyAction (
		        "font-family-SansSerif", "SansSerif"));
		    font.add(item = new JMenuItem ("Monospaced"));
		    item.setFont( new Font( "Monospaced", Font.PLAIN, 12 ) );
		    item.addActionListener( 
		      new StyledEditorKit.FontFamilyAction( "font-family-Monospaced", 
		                                            "Monospaced" ) );
		    font.addSeparator();
		    font.add( item = new JMenuItem( "Bold" ) );
		    item.setFont( new Font( "Serif", Font.BOLD, 12 ) );
		    item.addActionListener( new StyledEditorKit.BoldAction() );
		    font.add( item = new JMenuItem( "Italic" ) );
		    item.setFont( new Font( "Serif", Font.ITALIC, 12 ) );
		    item.addActionListener( new StyledEditorKit.ItalicAction() );
		  
		//ajout des items:
		file.add(nouveau);
		file.add(save);
		file.add(open);
		
		
		edit.add(TextColor);
		
		statusInfo = new JLabel();
		words = new JLabel( WordCount());
		//scrollPane
		JScrollPane scrollPane =new JScrollPane(zoneText);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//listeners
		nouveau.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		TextColor.addActionListener(this);
		help.addActionListener(this);
		
		edit.add(font);
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);
		
		zoneText.addKeyListener(new KeyAdapter() {
			 @Override
		        public void keyPressed(KeyEvent e) {
		     words.setText(WordCount());    		
			 }
	            });
		
			panel.add(words);
			panel.add(statusInfo);
			
			
		
		
		add(menubar,BorderLayout.NORTH);
		add(scrollPane,BorderLayout.CENTER);
		add( panel, BorderLayout.SOUTH );
		  
		 
		
		
		
		//Frame :
			setTitle("MyEditor");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			setLocationRelativeTo(null);
			setVisible(true);
			//pack();
			setSize(500,500);
				
	}

	public String WordCount() {
		
		    String l1,l2;
		int s = zoneText.getText().split("\\s+").length ;
        int c = zoneText.getText().length();
        l1="Words " + s;
        l2="Characters " + c;
    return l1 + " " +l2 ;
	}
	
	public static void main(String[] args) {

//		try {
//	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//	      } catch (Exception e) {}
	    new Editor();
//
		      }


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource().equals(open)) {
			JFileChooser chooser = new JFileChooser();
	
			FileNameExtensionFilter filter = new FileNameExtensionFilter("fichier","txt", "java" );
		 	chooser.setFileFilter(filter);
		       
		 	chooser.setApproveButtonText("Ok" ); //intitule du bouton
		 	//chooser.showOpenDialog(null); //affiche la boite de dialogue
		 	
		 		 
		 		 //si en clique sur open on affiche un fichier txt deja existe
		 		 if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		 	{      File selectedFile = chooser.getSelectedFile();
	                String filepath = selectedFile.getPath();
	 
	                    try {
	                     
	                        BufferedReader br = new BufferedReader(new FileReader(filepath));
	                        String line = "";
	                        String text = "";
	 
	                        while( (line=br.readLine()) != null )
	                            text += line + "\n";
	 
	                        zoneText.setText(text);
	                        br.close();
	                    }
	                    catch(IOException ioe) {
	                       JOptionPane.showMessageDialog(null, ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
		            //si un fichier est selectionné, récupérer le fichier puis son path et l'afficher dans la zone de texte
		 	
		 	else
		         {
		            System.out.println("Annuler" );
		         }
		
		}
	if(e.getSource().equals(TextColor)) {
		Color color = JColorChooser.showDialog( this, "Color Chooser", 
                Color.cyan );
		if (color != null) 
		{
			MutableAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setForeground( attr, color );
			 zoneText.setCharacterAttributes( attr, false ); 
}
		
	}
	if(e.getActionCommand()=="new") {
		{
		    zoneText.setStyledDocument (doc=new DefaultStyledDocument());
		    statusInfo.setText("");
		    words.setText("words:0  characters : 0");
		  }
		  
	}		
	
	if(e.getActionCommand()=="save")  {
		try
		{
			FileOutputStream fos = new FileOutputStream( "output.txt" );
			ObjectOutputStream oos = new ObjectOutputStream( fos );
			oos.writeObject( doc );
			oos.flush();
			oos.close();
			statusInfo.setText( "Saved to disk" );
		} 
		catch( IOException exp ) 
		{
			statusInfo.setText( "Unable to save" );
			exp.printStackTrace();
		}
		
		}
	if(e.getActionCommand()=="help")  {
		JOptionPane.showMessageDialog(this, "Thanks for choosing MyEditor "
				+ "with this app u can open/save txt files, u can also change color and font  "
				+ "Hope u enjoy this !");
		}
	}
}	
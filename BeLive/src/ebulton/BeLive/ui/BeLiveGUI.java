package ebulton.BeLive.ui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ebulton.BeLive.BeLive;
import ebulton.BeLive.user.User;

/**
 * This class is the GUI for the BeLive application
 * @author ElstanB
 */
public class BeLiveGUI implements ActionListener, MouseMotionListener, MouseListener {
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 500;
	
	private static final int SCREEN_LOCATION_X = 200;
	private static final int SCREEN_LOCATION_Y = 100;
	
	private JFrame frmStartUp;

	private JPanel pnlStartUp;
    private JButton btnReturningUser;
    private JButton btnNewUser;
    private JPanel pnlReturnInfo;
    private JLabel lblReturnFirstName;
    private JTextField txtReturnFirstName;
    private JLabel lblReturnLastName;
    private JTextField txtReturnLastName;
    private JButton btnReturnInfoEnter;
    
    private JFrame frmBeLive;
    
    private JPanel pnlInformation;
    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JLabel lblLastName;
    private JTextField txtLastName;
    private JLabel lblAge;
    private JTextField txtAge;
    private JLabel lblWeight;
    private JTextField txtWeight;
    private JLabel lblHeight;
    private JTextField txtHeight;
    private JLabel lblGender;
    private JPanel pnlRadBtn;
    private JRadioButton rbtnMale;
    private JRadioButton rbtnFemale;
    private JLabel lblLocation;
    private JButton btnOk;
    private JButton btnFindTreatments;
    private JButton btnEdit;
    
    private JPanel pnlDiagram;
    private JLabel lblDiagramInstructions;
    private ImageIcon image = new ImageIcon("image/Diagram.gif");
    private JLabel lblDiagram = new JLabel(image);
    private JLabel lblSelection;
    private JLabel lblSymptoms;
    private JTextArea txtaSymptoms;
    private JPanel pnlSymptoms;
    
    private JPanel pnlDescription;
    private JTextArea txtaDescription;
    private JPanel pnlOrientation;
    private JLabel lblOrientation;
    private JCheckBox ckbFront;
    private JCheckBox ckbBack;
    
    private JFrame frmTreatments;
    private JTable tblTreatments;
    private JLabel lblTreatmentTitle;
    private JPanel pnlTreatmentInfo;
    private JScrollPane scpTreatmentScrollTable;
    private JButton btnExit;
    
    private JFrame frmMoreInfo;
    private JPanel pnlName;
    private JLabel lblTreatmentName;
    private JPanel pnlApplication;
    private JPanel pnlInstructions;
    private ImageIcon treatmentImage = new ImageIcon("image/Treatment.gif");
    private JLabel lblTreatmentApplication = new JLabel(treatmentImage);
    private JLabel lblApplicationInstructions;
    private JTextArea txtaApplicationInstructions;
    private JPanel pnlOtherInfo;
    private JLabel lblTreatmentDescription;
    private JTextArea txtaTreatmentDescription;
    private JLabel lblTreatmentLocation;
    private JTextArea txtaTreatmentLocation;
    private JButton btnGoBack;
    
    private BeLive application;
    
    private User user;
    
    private int bodyPart;
    
    private int orientation;
    
    private String injuryDescription;
    
    private String symptoms;
    
    private String[][] treatments;
    
    public BeLiveGUI(){
    	
    	/** START UP WINDOW */
    	
    	frmStartUp = new JFrame("BeLive");
    	frmStartUp.setBackground(Color.WHITE);
    	frmStartUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmStartUp.setSize(WIDTH/2, HEIGHT/2 + 50);
    	frmStartUp.setLocation(SCREEN_LOCATION_X*2, SCREEN_LOCATION_Y*2);
    	
    	btnNewUser = new JButton("New User");
    	btnReturningUser = new JButton("Returning User");
    	
    	btnNewUser.addActionListener(this);								//ActionListener for new User button
    	btnReturningUser.addActionListener(this);						//ActionListener for returning User button
    	
    	pnlReturnInfo = new JPanel();
    	GridLayout gLayout = new GridLayout(2, 3);
    	gLayout.setHgap(2);;
    	pnlReturnInfo.setLayout(gLayout);
    	lblReturnFirstName = new JLabel("Enter First Name: ");
    	txtReturnFirstName = new JTextField(10);
    	lblReturnLastName = new JLabel("Enter Last Name: ");
    	txtReturnLastName = new JTextField(10);
    	btnReturnInfoEnter = new JButton("Enter");
    	btnReturnInfoEnter.addActionListener(this);
    	
    	pnlReturnInfo.add(lblReturnFirstName);
    	pnlReturnInfo.add(txtReturnFirstName);
    	pnlReturnInfo.add(new JLabel(""));
    	pnlReturnInfo.add(lblReturnLastName);
    	pnlReturnInfo.add(txtReturnLastName);
    	pnlReturnInfo.add(btnReturnInfoEnter);
    	pnlReturnInfo.setBorder(BorderFactory.createTitledBorder("Returning Information"));
    	pnlReturnInfo.setVisible(false);
    	
    	pnlStartUp = new JPanel();
    	
//    	GroupLayout layout = new GroupLayout(pnlStartUp);
//    	layout.setAutoCreateGaps(true);
//    	layout.setAutoCreateContainerGaps(true);
//    	layout.setVerticalGroup(
//	    	layout.createSequentialGroup()
//	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//	    		.addComponent(btnNewUser)
//	    		.addComponent(btnReturningUser)
//	    		.addComponent(pnlReturnInfo))
//    	);

    	pnlStartUp.add(btnNewUser);
    	pnlStartUp.add(btnReturningUser);
    	pnlStartUp.add(pnlReturnInfo);
    	
    	SpringLayout layout = new SpringLayout();
    	pnlStartUp.setLayout(layout);
    	layout.putConstraint(SpringLayout.WEST, btnNewUser, WIDTH/4 - 43, SpringLayout.WEST, pnlStartUp);
    	layout.putConstraint(SpringLayout.NORTH, btnNewUser, HEIGHT/4 - 100, SpringLayout.NORTH, pnlStartUp);
    	layout.putConstraint(SpringLayout.WEST, btnReturningUser, WIDTH/4 - 57, SpringLayout.WEST, pnlStartUp);
    	layout.putConstraint(SpringLayout.NORTH, btnReturningUser, HEIGHT/4 - 50, SpringLayout.NORTH, pnlStartUp);
    	layout.putConstraint(SpringLayout.WEST, pnlReturnInfo, WIDTH/4 - 165, SpringLayout.WEST, pnlStartUp);
    	layout.putConstraint(SpringLayout.NORTH, pnlReturnInfo, HEIGHT/4, SpringLayout.NORTH, pnlStartUp);
    	
    	pnlStartUp.setVisible(true);
    	
    	frmStartUp.add(pnlStartUp);

    	frmStartUp.setVisible(true);
        //frmBeLive.setVisible(true);
    	
    }
    
    /**
     * Creates the new User frame
     */
    private void newUser() {
    	frmBeLive = new JFrame();
    	frmBeLive.setBackground(Color.WHITE);
    	frmBeLive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmBeLive.setTitle("BeLive");
    	frmBeLive.setSize(WIDTH, HEIGHT);
    	frmBeLive.setLocation(SCREEN_LOCATION_X, SCREEN_LOCATION_Y);
        
    	frmBeLive.setLayout(new BorderLayout());
        
        pnlInformation = new JPanel();
        lblFirstName = new JLabel("   First Name:");
        txtFirstName = new JTextField(10);
        lblLastName = new JLabel("   Last Name:");
        txtLastName = new JTextField(10);
        lblAge = new JLabel("   Age:");
        txtAge = new JTextField(3);
        lblWeight = new JLabel("   Weight in lbs (Optional):");
        txtWeight = new JTextField(5);
        lblHeight = new JLabel("   Height in inches (Optional):");
        txtHeight = new JTextField(5);
        lblGender = new JLabel("   Gender:");
        rbtnMale = new JRadioButton("Male");
        rbtnFemale = new JRadioButton("Female");
        rbtnMale.addActionListener(this);
        rbtnFemale.addActionListener(this);
        btnOk = new JButton("Ok");
        btnOk.addActionListener(this);
        
        pnlInformation.setLayout(new GridLayout(8,2));
        pnlInformation.add(lblFirstName);
        pnlInformation.add(txtFirstName);
        pnlInformation.add(lblLastName);
        pnlInformation.add(txtLastName);
        pnlInformation.add(lblAge);
        pnlInformation.add(txtAge);
        pnlInformation.add(lblWeight);
        pnlInformation.add(txtWeight);
        pnlInformation.add(lblHeight);
        pnlInformation.add(txtHeight);
        pnlInformation.add(lblGender);
        pnlRadBtn = new JPanel();
        pnlRadBtn.add(rbtnMale);
        pnlRadBtn.add(rbtnFemale);
        pnlInformation.add(pnlRadBtn);
        pnlInformation.add(btnOk);
        pnlInformation.setBorder(BorderFactory.createTitledBorder("Information"));
        
        Image diagram = image.getImage();
        Image newDiagram = diagram.getScaledInstance(WIDTH/4, HEIGHT-110, Image.SCALE_SMOOTH);
        image = new ImageIcon(newDiagram);
        lblDiagram = new JLabel(image);
        lblDiagram.addMouseListener(this);
        lblDiagram.addMouseMotionListener(this);
        
        //ADD OPTION TO DRAW INJURY ON IMAGE OR POINTS WHERE THE USER HAS CLICKED
        
        //lblDiagram.setBorder();
        
//        JLabel invisHand = new JLabel("O");
//        invisHand.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlDiagram = new JPanel();
        FlowLayout fLayout = new FlowLayout();
        fLayout.setHgap(WIDTH/2);
        pnlDiagram.setLayout(fLayout);
        //invisHand.setLocation(lblDiagram.getWidth()/2, lblDiagram.getHeight()/2);
        
//        JLayeredPane invisLayer = new JLayeredPane();
//        invisLayer.add(lblDiagram, new Integer(1));
//        invisLayer.add(invisHand, new Integer(1), 0);
//        invisLayer.setBorder(BorderFactory.createTitledBorder("Invisible Layer"));
        
        lblSelection = new JLabel("Selected area for Treatment: ");
        lblDiagramInstructions = new JLabel("Please select the area where you need treatment");
        
        pnlDiagram.add(lblDiagramInstructions);
        pnlDiagram.add(lblDiagram);
        pnlDiagram.add(lblSelection);
        //lblDiagram.addMouseListener();
        
        btnFindTreatments = new JButton("Find Treatments");
        btnFindTreatments.addActionListener(this);					//Action Listener attached to the find treatments button
        
        pnlDescription = new JPanel();
        pnlDescription.setBorder(BorderFactory.createTitledBorder("Injury Description"));
        pnlDescription.setLayout(new BoxLayout(pnlDescription, BoxLayout.Y_AXIS));
        txtaDescription = new JTextArea(15, 20);
        //NEW SYMPTOMS FIELD
        lblSymptoms = new JLabel("Symptoms/Sensations: ");
        txtaSymptoms = new JTextArea(5,20);
        pnlSymptoms = new JPanel();
        pnlOrientation = new JPanel();
        pnlOrientation.setLayout(new GridLayout(3, 1));
        lblOrientation = new JLabel("Body Orientation: ");
        ckbFront = new JCheckBox("Front");
        ckbBack = new JCheckBox("Back");
        
        pnlDescription.add(txtaDescription);
        //NEW SYMPTOMS FIELD
        pnlSymptoms.add(lblSymptoms);
        pnlSymptoms.add(txtaSymptoms);
        pnlDescription.add(pnlSymptoms);
        pnlOrientation.add(lblOrientation);
        pnlOrientation.add(ckbFront);
        pnlOrientation.add(ckbBack);
        pnlDescription.add(pnlOrientation);
        pnlDescription.setVisible(false);
        
        frmBeLive.add(pnlInformation, BorderLayout.WEST);
        frmBeLive.add(pnlDiagram, BorderLayout.CENTER);
        frmBeLive.add(pnlDescription, BorderLayout.EAST);
        frmBeLive.add(btnFindTreatments, BorderLayout.SOUTH);
        //frmBeLive.add(invisHand, 1);
        //frmBeLive.setContentPane(invisLayer);
//        frmBeLive.add(invisHand);
//        frmBeLive.add(pnlInformation);
//        frmBeLive.add(lblDiagram);
        
        //Not sure if changing visibility is the best method for closing the startup window but going with it for now
        frmStartUp.setVisible(false);
        System.out.println("Closing Start Up Window");
        frmBeLive.setVisible(true);
        System.out.println("Opening New User Window");
    }
    
    /**
     * Creates the returning User frame
     */
    private void returningUser(String firstName, String lastName) {
    	
    	application = new BeLive(firstName, lastName);
    	
    	user = application.findUser(firstName, lastName);
    	
    	frmBeLive = new JFrame();
    	frmBeLive.setBackground(Color.WHITE);
    	frmBeLive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmBeLive.setTitle("BeLive");
    	frmBeLive.setSize(WIDTH, HEIGHT);
    	frmBeLive.setLocation(SCREEN_LOCATION_X, SCREEN_LOCATION_Y);
        
    	frmBeLive.setLayout(new BorderLayout());
    	
    	pnlInformation = new JPanel();
        lblFirstName = new JLabel("   First Name:");
        txtFirstName = new JTextField(10);
        txtFirstName.setText(user.getFirstName());
        txtFirstName.setEditable(false);
        lblLastName = new JLabel("   Last Name:");
        txtLastName = new JTextField(10);
        txtLastName.setText(user.getLastName());
        txtLastName.setEditable(false);
        lblAge = new JLabel("   Age:");
        txtAge = new JTextField(3);
        txtAge.setText(user.getAge() + "");
        lblWeight = new JLabel("   Weight in lbs (Optional):");
        txtWeight = new JTextField(5);
        txtWeight.setText(user.getWeight() + "");
        lblHeight = new JLabel("   Height in inches (Optional):");
        txtHeight = new JTextField(5);
        txtHeight.setText(user.getHeight() + "");
        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(this);
        lblGender = new JLabel("   Gender:");
        rbtnMale = new JRadioButton("Male");
        rbtnFemale = new JRadioButton("Female");
        if(user.isGender()) {
        	rbtnMale.setSelected(true);
        }
        else {
        	rbtnFemale.setSelected(false);
        }
        rbtnMale.setEnabled(false);
        rbtnFemale.setEnabled(false);
        
        pnlInformation.setLayout(new GridLayout(8,2));
        pnlInformation.add(lblFirstName);
        pnlInformation.add(txtFirstName);
        pnlInformation.add(lblLastName);
        pnlInformation.add(txtLastName);
        pnlInformation.add(lblAge);
        pnlInformation.add(txtAge);
        pnlInformation.add(lblWeight);
        pnlInformation.add(txtWeight);
        pnlInformation.add(lblHeight);
        pnlInformation.add(txtHeight);
        pnlInformation.add(lblGender);
        pnlRadBtn = new JPanel();
        pnlRadBtn.add(rbtnMale);
        pnlRadBtn.add(rbtnFemale);
        pnlInformation.add(pnlRadBtn);
        pnlInformation.add(btnEdit);
        pnlInformation.setBorder(BorderFactory.createTitledBorder("Information"));
        
        Image diagram = image.getImage();
        Image newDiagram = diagram.getScaledInstance(WIDTH/4, HEIGHT-110, Image.SCALE_SMOOTH);
        image = new ImageIcon(newDiagram);
        lblDiagram = new JLabel(image);
        lblDiagram.addMouseListener(this);
        lblDiagram.addMouseMotionListener(this);

        pnlDiagram = new JPanel();
        FlowLayout fLayout = new FlowLayout();
        fLayout.setHgap(WIDTH/2);
        pnlDiagram.setLayout(fLayout);
        
        lblSelection = new JLabel("Selected area for Treatment: ");
        lblDiagramInstructions = new JLabel("Please select the area where you need treatment");
        
        pnlDiagram.add(lblDiagramInstructions);
        pnlDiagram.add(lblDiagram);
        pnlDiagram.add(lblSelection);
        
        btnFindTreatments = new JButton("Find Treatments");
        btnFindTreatments.addActionListener(this);					//Action Listener attached to the find treatments button
        
        pnlDescription = new JPanel();
        pnlDescription.setBorder(BorderFactory.createTitledBorder("Injury Description"));
        pnlDescription.setLayout(new BoxLayout(pnlDescription, BoxLayout.Y_AXIS));
        txtaDescription = new JTextArea(12, 20);
        //NEW SYMPTOMS FIELD
        lblSymptoms = new JLabel("Symptoms/Sensations: ");
        txtaSymptoms = new JTextArea(5,20);
        pnlSymptoms = new JPanel();
//        pnlSymptoms.setLayout(new BoxLayout(pnlSymptoms, BoxLayout.Y_AXIS));
        pnlOrientation = new JPanel();
        pnlOrientation.setLayout(new GridLayout(3, 1));
        lblOrientation = new JLabel("Body Orientation: ");
        ckbFront = new JCheckBox("Front");
        ckbBack = new JCheckBox("Back");
        
        pnlDescription.add(txtaDescription);
        //NEW SYMPTOMS FIELD
        pnlSymptoms.add(lblSymptoms);
        pnlSymptoms.add(txtaSymptoms);
        pnlDescription.add(pnlSymptoms);
        pnlOrientation.add(lblOrientation);
        pnlOrientation.add(ckbFront);
        pnlOrientation.add(ckbBack);
        pnlDescription.add(pnlOrientation);
        pnlDescription.setVisible(false);
        
        frmBeLive.add(pnlInformation, BorderLayout.WEST);
        frmBeLive.add(pnlDiagram, BorderLayout.CENTER);
        frmBeLive.add(pnlDescription, BorderLayout.EAST);
        frmBeLive.add(btnFindTreatments, BorderLayout.SOUTH);
        
        //Not sure if changing visibility is the best method for closing the startup window but going with it for now
        frmStartUp.setVisible(false);
        System.out.println("Closing Start Up Window");
        frmBeLive.setVisible(true);
        System.out.println("Opening New User Window");
    }
    
    private void treatments() {
    	frmTreatments = new JFrame();
    	frmTreatments.setBackground(Color.WHITE);
    	frmTreatments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmTreatments.setTitle("BeLive");
    	frmTreatments.setSize(WIDTH, HEIGHT);
    	frmTreatments.setLocation(SCREEN_LOCATION_X, SCREEN_LOCATION_Y);
    	
//    	int tblLength = treatments.length;
//    	
//    	for(int j = 1; j < treatments.length + 1; j++) {
//	    	if(treatments[j-1][0] == null && treatments[j-1][1] == null && treatments[j-1][2] == null && treatments[j-1][3] == null) {
//	    		tblLength--;
//	    	}
//    	}
    	
    	/**
    	 * Table Model for the Treatments Table of the BeLive Application
    	 * Modeled from Java Oracle Tutorials TableDemo.java
    	 * @author ElstanB
    	 *
    	 */
    	class TreatmentTableModel extends AbstractTableModel {
    		/**
			 * Serial Version UID for table model
			 */
			private static final long serialVersionUID = 1L;
			
			/** Name of the columns in the table */
			String[] columnNames = {"Name", "Description",
									"Uses", "Location", "Symptoms"};
			
			/** Data in the table */
    		Object[][] data = treatments;

			/**
			 * Returns the number of rows in the table
			 * @return the number of rows in the table
			 */
			public int getRowCount() {
				return data.length;
			}

			/**
			 * The number of columns in the table
			 * @return the number of columns in the table
			 */
			public int getColumnCount() {
				return columnNames.length;
			}
			
			/**
			 * Returns the names of the column
			 * @param col the column thats name will be returned
			 * @return the name at the specified column
			 */
			public String getColumnName (int col) {
				return columnNames[col];
			}

			/**
			 * Returns the value at the cell
			 * @param rowIndex the row of the cell
			 * @param columnIndex the column of the cell
			 * @return the data at the cell
			 */
			public Object getValueAt(int rowIndex, int columnIndex) {
				return data[rowIndex][columnIndex];
			}
			
			/**
			 * Sets the value at a specific cell in the table
			 * @param value the value to set
			 * @param row the row of the cell to set
			 * @param col the column of the cell to set
			 */
			public void setValueAt(Object value, int row, int col) {
				data[row][col] = value;
			}
    		
    	}
    	
    	tblTreatments = new JTable(new TreatmentTableModel());
    	tblTreatments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tblTreatments.setPreferredScrollableViewportSize(new Dimension(500, 300));
    	tblTreatments.setFillsViewportHeight(true);
//    	tblTreatments.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				System.out.println(e.getFirstIndex());
//			}
//    		
//    	});
//    	tblTreatments.setBounds(SCREEN_LOCATION_X*2, SCREEN_LOCATION_Y*2, WIDTH, HEIGHT/2);
    	tblTreatments.getColumnModel().getColumn(0).setPreferredWidth(20);
    	tblTreatments.getColumnModel().getColumn(1).setPreferredWidth(50);
    	tblTreatments.getColumnModel().getColumn(2).setPreferredWidth(20);
    	tblTreatments.getColumnModel().getColumn(3).setPreferredWidth(50);
    	tblTreatments.getColumnModel().getColumn(4).setPreferredWidth(50);
    	tblTreatments.getTableHeader().setReorderingAllowed(false);
    	tblTreatments.setToolTipText("Double-click on treatment to recieve more info");
    	tblTreatments.addMouseListener(this);
//    	tblTreatments.setBorder(BorderFactory.createLineBorder(Color.black));
//    	tblTreatments.setValueAt("Index", 0, 0);
//    	tblTreatments.setValueAt("Name", 0, 1);
//    	tblTreatments.setValueAt("Description", 0, 2);
//    	tblTreatments.setValueAt("Uses", 0, 3);
//    	tblTreatments.setValueAt("Location", 0, 4);
//    	for(int i = 1; i < tblLength + 1; i++) {
//			tblTreatments.setValueAt(i, i, 0);
//    		tblTreatments.setValueAt(treatments[i-1][0], i, 1);
//    		tblTreatments.setValueAt(treatments[i-1][1], i, 2);
//    		tblTreatments.setValueAt(treatments[i-1][2], i, 3);
//    		tblTreatments.setValueAt(treatments[i-1][3], i, 4);
//    	}
    	
    	scpTreatmentScrollTable = new JScrollPane(tblTreatments);
    	scpTreatmentScrollTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Number of Treatments: " + tblTreatments.getModel().getRowCount()));
//    	scpTreatmentScrollTable.setColumnHeaderView(new JTableHeader(tblTreatments.getColumnModel()));;//FIGURE THIS OUT
    	pnlTreatmentInfo = new JPanel();
    	FlowLayout fLayout = new FlowLayout();
    	fLayout.setHgap(WIDTH/2);
    	pnlTreatmentInfo.setLayout(fLayout);
    	
    	//ADD SELECT OPTION TO ROWS SO USERS CAN LOOK AT TREATMENTS IN GREATER DETAIL
    	
    	lblTreatmentTitle = new JLabel("Treatments");
    	lblTreatmentTitle.setFont(new Font("Arial", Font.BOLD, 20));
//    	lblTreatmentCount = new JLabel("Number of Treatments: " + treatments.length);
//    	lblTreatmentCount.setFont(new Font("Arial", Font.ITALIC, 12));
    	
    	pnlTreatmentInfo.add(lblTreatmentTitle);
//    	pnlTreatmentInfo.add(lblTreatmentCount);
    	
    	btnExit = new JButton("Exit");
    	btnExit.addActionListener(this);
    	
//    	FlowLayout fLayout = new FlowLayout();
//    	fLayout.setVgap(HEIGHT/10);
//    	fLayout.setHgap(WIDTH/2);
//    	tblTreatments.setAutoscrolls(true);
//    	frmTreatments.setLayout(fLayout);
    	BorderLayout bLayout = new BorderLayout();
    	bLayout.setVgap(50);
    	bLayout.setHgap(50);
    	frmTreatments.setLayout(bLayout);
    	frmTreatments.add(pnlTreatmentInfo, BorderLayout.NORTH);
    	frmTreatments.add(new JLabel(" "), BorderLayout.EAST);
    	frmTreatments.add(new JLabel(" "), BorderLayout.WEST);
    	frmTreatments.add(scpTreatmentScrollTable, BorderLayout.CENTER);
    	frmTreatments.add(btnExit, BorderLayout.SOUTH);
//    	tblTreatments.setEnabled(false);
    	
    	frmBeLive.setVisible(false);
        System.out.println("Closing New/Returning User Window");
    	frmTreatments.setVisible(true);
        System.out.println("Opening Treatments Window");
    	
    }
    
    /**
     * Main method that calls the GUI so it will run
     * @param args the arguments from the user when running which is not used
     */
    public static void main(String[] args){
        //Call the GUI
        new BeLiveGUI();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button;
		
		if(e.getSource().getClass() == JButton.class) {
			button = (JButton) e.getSource();
			if(button.getText().equals("New User")) {
				newUser();
			}
			else if (button.getText().equals("Returning User")){
				pnlReturnInfo.setVisible(true);
			}
			else if (button.getText().equals("Ok")) {
				String firstName = txtFirstName.getText();
				String lastName = txtLastName.getText();
				int age = 0;
				int weight = 0;
				int height = 0;
				boolean gender = false;
				boolean exception = false;
				try {
					age = Integer.parseInt(txtAge.getText());
				}
				catch(Exception g) {
					exception = true;
					JOptionPane.showMessageDialog(frmBeLive, "Invalid age.");
				}
				try {
					weight = Integer.parseInt(txtWeight.getText());
				}
				catch(Exception g) {
					exception = true;
					JOptionPane.showMessageDialog(frmBeLive, "Invalid weight.");
				}
				try {
					height = Integer.parseInt(txtHeight.getText());
				}
				catch(Exception g) {
					exception = true;
					JOptionPane.showMessageDialog(frmBeLive, "Invalid height.");
				}
				if(rbtnMale.isSelected()) {
					gender = true;
				}
				try {
					System.out.println("Attempting to create user");
					user = new User(firstName, lastName, gender, age, height, weight, "");
					System.out.println("Created user");
				}
				catch(Exception g) {
					exception = true;
					JOptionPane.showMessageDialog(frmBeLive, g.getMessage());
				}
				if(!exception) {
					pnlInformation.setVisible(false);
					pnlDescription.setVisible(true);
				}
				
			}
			else if (button.getText().equals("Enter")) {
				System.out.println("Checking Returning User Information");
				try {
					returningUser(txtReturnFirstName.getText(), txtReturnLastName.getText());
				}
				catch(IllegalArgumentException f) {
					JOptionPane.showMessageDialog(frmStartUp, f.getMessage());
				}
				
			}
			else if(button.getText().equals("Edit")) {
				System.out.println("Editing User Information");
				boolean exception = false;
				try {
					user.setAge(Integer.parseInt(txtAge.getText()));
					user.setHeight(Integer.parseInt(txtHeight.getText()));
					user.setWeight(Integer.parseInt(txtWeight.getText()));
					user.setEdited(true);
					application.editUser(user);
				}
				catch (IllegalArgumentException f) {
					exception = true;
					JOptionPane.showMessageDialog(frmBeLive, f.getMessage());
				}
				if(!exception) {
					pnlInformation.setVisible(false);
					pnlDescription.setVisible(true);
				}
			}
			else if(button.getText().equals("Go Back")){
				System.out.println("Closing More Treatment Info Window");
				frmMoreInfo.setVisible(false);
				System.out.println("Opening Treatment Window");
				frmTreatments.setVisible(true);
			}
			else if(button.getText().equals("Exit")) {
				System.out.println("Closing all windows");
				System.exit(0);
			}
			else {
				System.out.println("Finding Treatments");
				if(button.getText().equals("Find Treatments")) {
					boolean exception = false;
					
					if(!(ckbFront.isSelected() && ckbBack.isSelected())) {
						exception = true;
						JOptionPane.showMessageDialog(frmBeLive, "Please select an orientation before finding the treaments.");
					}
					if(txtaDescription.getText() == "") {
						exception = true;
						JOptionPane.showMessageDialog(frmBeLive, "Please enter a description before finding treaments.");
					}
					

					if(ckbFront.isSelected() && ckbBack.isSelected()) {
						orientation = 0;
					}
					else if(ckbFront.isSelected() && !(ckbBack.isSelected())) {
						orientation = 1;
					}
					else {
						orientation = 2;
					}
					injuryDescription = txtaDescription.getText();
					symptoms = txtaSymptoms.getText();
					System.out.println((symptoms));
					System.out.println((injuryDescription));
					try {
						application = new BeLive(user, bodyPart, orientation, injuryDescription, symptoms);
					}
					catch(Exception g) {
						JOptionPane.showMessageDialog(frmBeLive, g.getMessage());
					}
					
					try {
						treatments = application.findTreatment();
					}
					catch(Exception h) {
						exception = true;
						JOptionPane.showMessageDialog(frmBeLive, h.getMessage());
					}
					if(!exception) {
						treatments();
					}
				}
				
			}
		}
		else {
			if(rbtnMale.isSelected()) {
				rbtnFemale.setSelected(false);
			}
			if(rbtnFemale.isSelected()) {
				rbtnMale.setSelected(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(user == null) {
			JOptionPane.showMessageDialog(frmBeLive, "Please fill out User information before selecting treatement area");
		}
		if((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth() - 60) &&
				(e.getPoint().getY() > 0 && e.getPoint().getY() < image.getIconHeight()/4)) {
				bodyPart = 1;
				lblSelection.setText("Selected area for Treatment: Head/Neck");
		}
		if((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth() - 60) &&
				(e.getPoint().getY() > image.getIconHeight()/4 && e.getPoint().getY() < image.getIconHeight()/2 + 35)) {
				bodyPart = 2;
				lblSelection.setText("Selected area for Treatment: Chest");
		}
		if(((e.getPoint().getX() > 0 && e.getPoint().getX() < image.getIconWidth()/5 + 8) || 
			(e.getPoint().getX() > image.getIconWidth() - 60 && e.getPoint().getX() < image.getIconWidth())) &&
			(e.getPoint().getY() > image.getIconHeight()/4 && e.getPoint().getY() < image.getIconHeight()/2)) {
				bodyPart = 3;
				lblSelection.setText("Selected area for Treatment: Arm");
		}
		if(((e.getPoint().getX() > 0 && e.getPoint().getX() < image.getIconWidth()/5 + 8) || 
			(e.getPoint().getX() > image.getIconWidth() - 60 && e.getPoint().getX() < image.getIconWidth())) &&
			(e.getPoint().getY() > image.getIconHeight()/2 && e.getPoint().getY() < image.getIconHeight()/2 + 35)) {
				bodyPart = 4;
				lblSelection.setText("Selected area for Treatment: Hand/Wrist");
		}
		if(((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth()/2 - 10) || 
			(e.getPoint().getX() > image.getIconWidth()/2 + 10 && e.getPoint().getX() < image.getIconWidth() - 60)) &&
			(e.getPoint().getY() > image.getIconHeight()/2 + 35 && e.getPoint().getY() < image.getIconHeight() - 35)) {
				bodyPart = 5;
				lblSelection.setText("Selected area for Treatment: Leg");
		}
		if(((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth()/2 - 10) || 
			(e.getPoint().getX() > image.getIconWidth()/2 + 10 && e.getPoint().getX() < image.getIconWidth() - 60)) &&
			(e.getPoint().getY() > image.getIconHeight() - 35 && e.getPoint().getY() < image.getIconHeight())) {
				bodyPart = 6;
				lblSelection.setText("Selected area for Treatment: Foot/Ankle");
		}
		
		// More info on treatments if treatment is double clicked
		if(e.getClickCount() == 2 && !frmBeLive.isVisible()) {
//			int row = tblTreatments.getSelectedRow();
			openTreatment();
			System.out.println("Opening More Treatment Info Window");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("Mouse Entered");
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth() - 60) &&
				(e.getPoint().getY() > 0 && e.getPoint().getY() < image.getIconHeight()/4)) {
				lblDiagram.setToolTipText("Head/Neck");
		}
		if((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth() - 60) &&
				(e.getPoint().getY() > image.getIconHeight()/4 && e.getPoint().getY() < image.getIconHeight()/2 + 35)) {
				lblDiagram.setToolTipText("Chest");
		}
		if(((e.getPoint().getX() > 0 && e.getPoint().getX() < image.getIconWidth()/5 + 8) || 
			(e.getPoint().getX() > image.getIconWidth() - 60 && e.getPoint().getX() < image.getIconWidth())) &&
			(e.getPoint().getY() > image.getIconHeight()/4 && e.getPoint().getY() < image.getIconHeight()/2)) {
				lblDiagram.setToolTipText("Arm");
		}
		if(((e.getPoint().getX() > 0 && e.getPoint().getX() < image.getIconWidth()/5 + 8) || 
			(e.getPoint().getX() > image.getIconWidth() - 60 && e.getPoint().getX() < image.getIconWidth())) &&
			(e.getPoint().getY() > image.getIconHeight()/2 && e.getPoint().getY() < image.getIconHeight()/2 + 35)) {
				lblDiagram.setToolTipText("Hand/Wrist");
		}
		if(((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth()/2 - 10) || 
			(e.getPoint().getX() > image.getIconWidth()/2 + 10 && e.getPoint().getX() < image.getIconWidth() - 60)) &&
			(e.getPoint().getY() > image.getIconHeight()/2 + 35 && e.getPoint().getY() < image.getIconHeight() - 35)) {
				lblDiagram.setToolTipText("Leg");
		}
		if(((e.getPoint().getX() > image.getIconWidth()/5 + 8 && e.getPoint().getX() < image.getIconWidth()/2 - 10) || 
			(e.getPoint().getX() > image.getIconWidth()/2 + 10 && e.getPoint().getX() < image.getIconWidth() - 60)) &&
			(e.getPoint().getY() > image.getIconHeight() - 35 && e.getPoint().getY() < image.getIconHeight())) {
				lblDiagram.setToolTipText("Foot/Ankle");
		}
	}
    
	private void openTreatment() {
		System.out.println("Closing Treatment Window");
		frmTreatments.setVisible(false);
		frmMoreInfo = new JFrame();
    	frmMoreInfo.setBackground(Color.WHITE);
    	frmMoreInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmMoreInfo.setTitle("BeLive");
    	frmMoreInfo.setSize(WIDTH, HEIGHT);
    	frmMoreInfo.setLocation(SCREEN_LOCATION_X, SCREEN_LOCATION_Y);
    	
    	frmMoreInfo.setLayout(new BorderLayout(10,10));
    	pnlName = new JPanel();
    	lblTreatmentName = new JLabel((String) tblTreatments.getValueAt(tblTreatments.getSelectedRow(), 0));
    	lblTreatmentName.setFont(new Font("Arial", Font.BOLD, 20));
    	pnlApplication = new JPanel();
    	FlowLayout fLayout = new FlowLayout();
        fLayout.setHgap(WIDTH/4);
    	pnlApplication.setLayout(fLayout);
    	pnlInstructions = new JPanel();
    	pnlInstructions.setLayout(new BoxLayout(pnlInstructions, BoxLayout.Y_AXIS));
    	lblApplicationInstructions = new JLabel("How to Apply the Treatment: ");
    	txtaApplicationInstructions = new JTextArea(20, 20);
    	txtaApplicationInstructions.setEditable(false);
    	pnlOtherInfo = new JPanel();
    	pnlOtherInfo.setLayout(new GridLayout(2,2));
    	lblTreatmentDescription = new JLabel("Description of the Treatement: ");
    	txtaTreatmentDescription = new JTextArea((String) tblTreatments.getValueAt(tblTreatments.getSelectedRow(), 1));
    	txtaTreatmentDescription.setEditable(false);
    	lblTreatmentLocation = new JLabel("Where to find the Treatment: ");
    	txtaTreatmentLocation = new JTextArea((String) tblTreatments.getValueAt(tblTreatments.getSelectedRow(), 3));
    	txtaTreatmentLocation.setEditable(false);
    	btnGoBack = new JButton("Go Back");
    	btnGoBack.addActionListener(this);
    	
    	pnlName.add(lblTreatmentName);
    	
    	pnlInstructions.add(lblApplicationInstructions);
    	pnlInstructions.add(txtaApplicationInstructions);
    	pnlApplication.add(new JLabel("Image of treatment")); //Should be lblTreatmentApplication
    	pnlApplication.add(pnlInstructions);
    	
    	pnlOtherInfo.add(lblTreatmentDescription);
    	pnlOtherInfo.add(txtaTreatmentDescription);
    	pnlOtherInfo.add(lblTreatmentLocation);
    	pnlOtherInfo.add(txtaTreatmentLocation);
    	pnlApplication.add(pnlOtherInfo);
    	
    	frmMoreInfo.add(pnlName, BorderLayout.NORTH);
    	frmMoreInfo.add(pnlApplication, BorderLayout.CENTER);
    	frmMoreInfo.add(btnGoBack, BorderLayout.SOUTH);
    	frmMoreInfo.setVisible(true);
    	
	}
	
}
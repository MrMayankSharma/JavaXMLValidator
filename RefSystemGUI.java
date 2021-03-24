/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Statement;
import java.beans.XMLEncoder;
import java.util.*;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.poi.util.SystemOutLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.io.File;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author saemundur
 */

public class RefSystemGUI extends JFrame implements ActionListener,KeyListener {
	
	Object[] cols = {"Title","Author","Year","Publication_Name","DOI","Date",
			"Journal_Name","Journal_Volume","Journal_Issue","Conference_Name","Conference_Location"
			,"Book_Title","Book_Editor"};
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable(model);
	private JScrollPane sp = new JScrollPane(table); 
	
	private JTextField title = new JTextField(20);
	private JTextField author = new JTextField(20);
	private JTextField year = new JTextField(20);
	private JTextField nameOfPub = new JTextField(20);
	private JTextField DOI = new JTextField(20);
	private JTextField date = new JTextField(20);
	private JTextField nameOfJour = new JTextField(20);
	private JTextField volumeOfJour = new JTextField(20);
	private JTextField issueOfJour = new JTextField(20);
	private JTextField nameConf = new JTextField(20);
	private JTextField locConf = new JTextField(20);
	private JTextField bookTitle = new JTextField(20);
	private JTextField bookEditor = new JTextField(20);
	private JTextField xmlForValidation = new JTextField(20);
	private JTextField xsdForValidation = new JTextField(20);
	private JTextArea result = new JTextArea(10, 50);
	
	
	private JLabel labopr = new JLabel("Select an Operation: ");
	private JLabel labtitle = new JLabel("Title: ");
	private JLabel labtype = new JLabel("Type Of Publication: ");
	private JLabel labauthor = new JLabel("Author: ");
	private JLabel labyear = new JLabel("Year: ");
	private JLabel labnameOfPub = new JLabel("Name Of Publication: ");
	private JLabel labDOI = new JLabel("Digital Object Identifier: ");
	private JLabel labdate = new JLabel("Date: ");
	private JLabel labnameOfJour = new JLabel("Journal Name: ");
	private JLabel labvolumeOfJour = new JLabel("Journal Volume: ");
	private JLabel labissueOfJour = new JLabel("Journal Issue: ");
	private JLabel labnameConf = new JLabel("Conference Name: ");
	private JLabel lablocConf = new JLabel("Conference Location: ");
	private JLabel labbookTitle = new JLabel("Book Title: ");
	private JLabel labbookEditor = new JLabel("Book Editor: ");
	private JLabel labSearchOption = new JLabel("Search By: ");
	
	
	private JButton AddButton= new JButton("Add");	
	private JButton SearchButton= new JButton("Search");
	private JButton ImportButton= new JButton("Import");
	private JButton ExportButton= new JButton("Export");	
	private JButton SearchExportButton= new JButton("Export Searched Data");	
	private JButton ViewTableButton = new JButton("View Full Table");
	private JButton ValidatorButton = new JButton("Validate XML against XSD");
	private JButton xmlSelectorButton = new JButton("Select XML");
	private JButton xsdSelectorButton = new JButton("Select XSD");

	UtilDateModel model1 = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model1);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

	String SearchOptions[]= {"Name Of Publication","Journal Name","Conference Location"};
	final JComboBox<String> srchop = new JComboBox<String>(SearchOptions);
	String Operation[]= {"Add","Search","Import","Export","Validate XML"};
    final JComboBox<String> opr = new JComboBox<String>(Operation);
	String typeOfPublication[]= {"Journal Papers","Conference Papers","Book Chapters"};
    final JComboBox<String> TOP = new JComboBox<String>(typeOfPublication);
    
    String xsdPath = null;
    String xmlPath = null;
    
    File xsdToValidate,xmlToValidate;
    
    public static void main(String[] args) {
    	
        RefSystemGUI applic = new RefSystemGUI();
        
    }
    
    public RefSystemGUI() {
        super("Bibliography");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,3,3);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=0.5;
        add(labopr,gbc);
        gbc.gridx++;
        add(opr,gbc);
        gbc.gridx++;
        add(labSearchOption,gbc);
        gbc.gridx++;
        add(srchop,gbc);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        gbc.gridx=0;
        gbc.gridy++;
        add(labtitle,gbc);
        gbc.gridx++;
        add(title,gbc);
        gbc.gridx++;
        add(labtype,gbc);
        gbc.gridx++;
        add(TOP,gbc);
        gbc.gridx++;
        add(labauthor,gbc);
        gbc.gridx++;
        add(author,gbc);
        gbc.gridx++;
        add(labdate,gbc);
        gbc.gridx++;
        add(datePicker,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy++;
        add(labyear,gbc);
        gbc.gridx++;
        add(year,gbc);
        gbc.gridx++;
        add(labnameOfPub,gbc);
        gbc.gridx++;
        add(nameOfPub,gbc);
        gbc.gridx++;
        add(labDOI,gbc);
        gbc.gridx++;
        add(DOI,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy++;
        add(labnameOfJour,gbc);
        gbc.gridx++;
        add(nameOfJour,gbc);
        gbc.gridx++;
        add(labvolumeOfJour,gbc);
        gbc.gridx++;
        add(volumeOfJour,gbc);
        gbc.gridx++;
        add(labissueOfJour,gbc);
        gbc.gridx++;
        add(issueOfJour,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy++;
        add(labnameConf,gbc);
        gbc.gridx++;
        add(nameConf,gbc);
        gbc.gridx++;
        add(lablocConf,gbc);
        gbc.gridx++;
        add(locConf,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy++;
        add(labbookTitle,gbc);
        gbc.gridx++;
        add(bookTitle,gbc);
        gbc.gridx++;
        add(labbookEditor,gbc);
        gbc.gridx++;
        add(bookEditor,gbc);
        
        
        gbc.gridx=0;
        gbc.gridx++;
        gbc.gridy++;
        add(AddButton,gbc);
        gbc.gridx++;
        add(SearchButton,gbc);
        gbc.gridx++;
        add(ImportButton,gbc);
        gbc.gridx++;
        add(ExportButton,gbc);
        gbc.gridx++;
        gbc.gridx++;
        gbc.gridx++;
        add(ViewTableButton,gbc);
        
        gbc.gridx=0;
        gbc.gridy+=2;
        gbc.gridx++;
        add(xmlForValidation,gbc);
        gbc.gridx++;
        add(xmlSelectorButton,gbc);
        gbc.gridx++;
        add(xsdForValidation,gbc);
        gbc.gridx++;
        add(xsdSelectorButton,gbc);
        gbc.gridx++;
        add(ValidatorButton,gbc);
        
        gbc.gridx=0;
        gbc.gridy++;
        
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridwidth = 8;
        gbc.gridheight = 4;
        model.setColumnIdentifiers(cols);
        gbc.fill = GridBagConstraints.BOTH;
        add(sp,gbc);
        
        srchop.addActionListener(this);
        opr.addActionListener(this);
        TOP.addActionListener(this);
        AddButton.addActionListener(this);
        SearchButton.addActionListener(this);
        ImportButton.addActionListener(this);
        ExportButton.addActionListener(this);
        SearchExportButton.addActionListener(this);
        ViewTableButton.addActionListener(this);
        xmlSelectorButton.addActionListener(this);
        xsdSelectorButton.addActionListener(this);
        ValidatorButton.addActionListener(this);
        
        year.addKeyListener(this);
        
        srchop.setEnabled(false);
        title.setEnabled(true);
        TOP.setEnabled(true);
        author.setEnabled(true);
        year.setEnabled(true);
        nameOfPub.setEnabled(true);
        DOI.setEnabled(true);
        date.setEnabled(true);
        nameOfJour.setEnabled(true);
        volumeOfJour.setEnabled(true);
        issueOfJour.setEnabled(true);
        nameConf.setEnabled(false);
        locConf.setEnabled(false);
        bookTitle.setEnabled(false);
        bookEditor.setEnabled(false);
        xmlForValidation.setEnabled(false);
        xsdForValidation.setEnabled(false);
        AddButton.setEnabled(true);
        SearchButton.setEnabled(false);
        ImportButton.setEnabled(false);
        ExportButton.setEnabled(false);
        ValidatorButton.setEnabled(false);
        xmlSelectorButton.setEnabled(false);
        xsdSelectorButton.setEnabled(false);
        ViewTableButton.setEnabled(true);

        setSize(1080, 1080);
        setLocationByPlatform(true);
        pack();
        setVisible(true);
        BlankDisplay();
    } //setLayout
    
    
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event) {
        String message = "";
        if(event.getSource()==srchop) {
        	
    		if(srchop.getSelectedItem().toString() == "Name Of Publication") {
        		BlankDisplay();
                srchop.setEnabled(true);
                title.setEnabled(false);
                TOP.setEnabled(false);
                author.setEnabled(false);
                year.setEnabled(false);
                nameOfPub.setEnabled(true);
                DOI.setEnabled(false);
                datePicker.setEnabled(false);
                nameOfJour.setEnabled(false);
                volumeOfJour.setEnabled(false);
                issueOfJour.setEnabled(false);
                nameConf.setEnabled(false);
                locConf.setEnabled(false);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(false);
                SearchButton.setEnabled(true);
                ImportButton.setEnabled(false);
                ExportButton.setEnabled(false);
                SearchExportButton.setEnabled(false);
        		
        	}else if(srchop.getSelectedItem().toString() == "Journal Name") {
        		BlankDisplay();
        		srchop.setEnabled(true);
                title.setEnabled(false);
                TOP.setEnabled(false);
                author.setEnabled(false);
                year.setEnabled(false);
                nameOfPub.setEnabled(false);
                DOI.setEnabled(false);
                datePicker.setEnabled(false);
                nameOfJour.setEnabled(true);
                volumeOfJour.setEnabled(false);
                issueOfJour.setEnabled(false);
                nameConf.setEnabled(false);
                locConf.setEnabled(false);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(false);
                SearchButton.setEnabled(true);
                ImportButton.setEnabled(false);
                ExportButton.setEnabled(false);
                SearchExportButton.setEnabled(false);

        	}else {
        		BlankDisplay();
        		srchop.setEnabled(true);
                title.setEnabled(false);
                TOP.setEnabled(false);
                author.setEnabled(false);
                year.setEnabled(false);
                nameOfPub.setEnabled(false);
                DOI.setEnabled(false);
                datePicker.setEnabled(false);
                nameOfJour.setEnabled(false);
                volumeOfJour.setEnabled(false);
                issueOfJour.setEnabled(false);
                nameConf.setEnabled(false);
                locConf.setEnabled(true);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(false);
                SearchButton.setEnabled(true);
                ImportButton.setEnabled(false);
                ExportButton.setEnabled(false);
                SearchExportButton.setEnabled(false);
        		
        	}
    	}
        else if(event.getSource()==opr) {
    		if(opr.getSelectedItem().toString() == "Add") {
    			srchop.setEnabled(false);
                title.setEnabled(true);
                TOP.setEnabled(true);
                author.setEnabled(true);
                year.setEnabled(true);
                nameOfPub.setEnabled(true);
                DOI.setEnabled(true);
                datePicker.setEnabled(true);
                nameOfJour.setEnabled(true);
                volumeOfJour.setEnabled(true);
                issueOfJour.setEnabled(true);
                nameConf.setEnabled(false);
                locConf.setEnabled(false);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(true);
                SearchButton.setEnabled(false);
                ImportButton.setEnabled(false);
                ExportButton.setEnabled(false);
                ValidatorButton.setEnabled(false);
                xmlSelectorButton.setEnabled(false);
                xsdSelectorButton.setEnabled(false);

        		
        	}else if(opr.getSelectedItem().toString() == "Search") {
        		
                BlankDisplay();
                srchop.setEnabled(true);
                title.setEnabled(false);
                TOP.setEnabled(false);
                author.setEnabled(false);
                year.setEnabled(false);
                nameOfPub.setEnabled(true);
                DOI.setEnabled(false);
                datePicker.setEnabled(false);
                nameOfJour.setEnabled(false);
                volumeOfJour.setEnabled(false);
                issueOfJour.setEnabled(false);
                nameConf.setEnabled(false);
                locConf.setEnabled(false);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(false);
                SearchButton.setEnabled(true);
                ImportButton.setEnabled(false);
                ExportButton.setEnabled(false);
                ValidatorButton.setEnabled(false);
                xmlSelectorButton.setEnabled(false);
                xsdSelectorButton.setEnabled(false);
        		
        	}else if(opr.getSelectedItem().toString() == "Import") {
        		BlankDisplay();
        		srchop.setEnabled(false);
        		nameOfJour.setEnabled(false);
        		locConf.setEnabled(false);
        		nameOfPub.setEnabled(false);
        		SearchButton.setEnabled(false);
        		title.setEnabled(false);
                TOP.setEnabled(false);
                author.setEnabled(false);
                year.setEnabled(false);
                DOI.setEnabled(false);
                datePicker.setEnabled(false);
                volumeOfJour.setEnabled(false);
                issueOfJour.setEnabled(false);
                nameConf.setEnabled(false);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(false);
                ImportButton.setEnabled(true);
                ExportButton.setEnabled(false);
                ValidatorButton.setEnabled(false);
                xmlSelectorButton.setEnabled(false);
                xsdSelectorButton.setEnabled(false);
        		
        	}else if(opr.getSelectedItem().toString() == "Export"){
        		srchop.setEnabled(false);
        		nameOfJour.setEnabled(false);
        		locConf.setEnabled(false);
        		nameOfPub.setEnabled(false);
        		title.setEnabled(false);
                TOP.setEnabled(false);
                author.setEnabled(false);
                year.setEnabled(false);
                DOI.setEnabled(false);
                datePicker.setEnabled(false);
                volumeOfJour.setEnabled(false);
                issueOfJour.setEnabled(false);
                nameConf.setEnabled(false);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(false);
                ImportButton.setEnabled(false);
        		SearchButton.setEnabled(false);
        		ExportButton.setEnabled(true);
                ValidatorButton.setEnabled(false);
                xmlSelectorButton.setEnabled(false);
                xsdSelectorButton.setEnabled(false);
                BlankDisplay();
        	}
        	else if(opr.getSelectedItem().toString() == "Validate XML") {
        		srchop.setEnabled(false);
        		nameOfJour.setEnabled(false);
        		locConf.setEnabled(false);
        		nameOfPub.setEnabled(false);
        		title.setEnabled(false);
                TOP.setEnabled(false);
                author.setEnabled(false);
                year.setEnabled(false);
                DOI.setEnabled(false);
                datePicker.setEnabled(false);
                volumeOfJour.setEnabled(false);
                issueOfJour.setEnabled(false);
                nameConf.setEnabled(false);
                bookTitle.setEnabled(false);
                bookEditor.setEnabled(false);
                AddButton.setEnabled(false);
                ImportButton.setEnabled(false);
        		SearchButton.setEnabled(false);
        		ExportButton.setEnabled(false);
        		ValidatorButton.setEnabled(true);
                xmlSelectorButton.setEnabled(true);
                xsdSelectorButton.setEnabled(true);
                BlankDisplay();
        	}
    	}else if(event.getSource()==TOP) {
    		if(TOP.getSelectedItem().toString() == "Journal Papers") {
        		nameOfJour.setEnabled(true);
        		volumeOfJour.setEnabled(true);
        		issueOfJour.setEnabled(true);
        		nameConf.setEnabled(false);
        		locConf.setEnabled(false);
        		bookTitle.setEnabled(false);
        		bookEditor.setEnabled(false);
        		nameConf.setText("");
        		locConf.setText("");
        		bookTitle.setText("");
        		bookEditor.setText("");
        		
        	}else if(TOP.getSelectedItem().toString() == "Conference Papers") {
        		nameOfJour.setEnabled(false);
        		volumeOfJour.setEnabled(false);
        		issueOfJour.setEnabled(false);
        		nameConf.setEnabled(true);
        		locConf.setEnabled(true);
        		bookTitle.setEnabled(false);
        		bookEditor.setEnabled(false);
        		nameOfJour.setText("");
        		volumeOfJour.setText("");
        		issueOfJour.setText("");
        		bookTitle.setText("");
        		bookEditor.setText("");
        		
        	}else {
        		nameOfJour.setEnabled(false);
        		volumeOfJour.setEnabled(false);
        		issueOfJour.setEnabled(false);
        		nameConf.setEnabled(false);
        		locConf.setEnabled(false);
        		bookTitle.setEnabled(true);
        		bookEditor.setEnabled(true);
        		nameOfJour.setText("");
        		volumeOfJour.setText("");
        		issueOfJour.setText("");
        		nameConf.setText("");
        		locConf.setText("");
        		
        	}
    	}else if(event.getSource()==AddButton) {
    		if ( title.getText().isEmpty() & author.getText().isEmpty() & year.getText().isEmpty() & nameOfPub.getText().isEmpty() & DOI.getText().isEmpty() & (datePicker.getJFormattedTextField().getText()
 == null)) {
        		message = " Please fill in atleast one of the fields";
        	}else {
        		
        		String ttl = title.getText();
                String PubType = TOP.getSelectedItem().toString();
                String auth = author.getText();
                int yr;
                if(!year.getText().isEmpty()) {
                    yr = Integer.parseInt(year.getText());
                }else {
                	yr = 2021;
                }
                String PubName= nameOfPub.getText();
                String doi = DOI.getText();
                String dt =  datePicker.getJFormattedTextField().getText();
                String JourName = nameOfJour.getText();
                String JourVol = volumeOfJour.getText();
                String JourIssue = issueOfJour.getText();
                String ConfName = nameConf.getText();
                String ConfLoc = locConf.getText();
                String bkTitle = bookTitle.getText();
                String bkEditor = bookEditor.getText();
                
                if( datePicker.getJFormattedTextField().getText() == null || datePicker.getJFormattedTextField().getText().isEmpty() ) {
                	
                	datePicker.getJFormattedTextField().setText("01/01/2021");
                	dt = (String) datePicker.getJFormattedTextField().getText();
                	
                }
                if(ttl.isEmpty()) {
                	ttl = " ";
                }else if(auth.isEmpty()){
                	auth = " ";
                }else if(PubName.isEmpty()) {
                	PubName = " ";
                }else if(doi.isEmpty()) {
                	doi = " ";
                }else if(JourName.isEmpty()) {
                	JourName = " ";
                }else if(JourVol.isEmpty()) {
                	JourVol = " ";
                }else if(JourIssue.isEmpty()) {
                	JourIssue = " ";
                }else if(ConfName.isEmpty()) {
                	ConfName = " ";
                }else if(ConfLoc.isEmpty()) {
                	ConfLoc = " ";
                }else if(bkTitle.isEmpty()) {
                	bkTitle = " ";
                }else if(bkEditor.isEmpty()) {
                	bkEditor = " ";
                }
            	
                model.addRow(new Object[] {ttl,auth,yr,PubName,doi,dt,JourName,JourVol,JourIssue,ConfName,ConfLoc,bkTitle,bkEditor});
         		System.out.println("Data Entry Successful");
                JOptionPane.showMessageDialog(this, "DATA ADDED TO TABLE","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
        		BlankDisplay();
        	}
    	}else if(event.getSource()==ImportButton) {
    		JFileChooser jf = new JFileChooser();
    		jf.setDialogTitle("Please select an csv file to import");
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File","csv");
    		jf.setFileFilter(filter);
    		int fileChosen = jf.showOpenDialog(null);
    		if (fileChosen==JFileChooser.APPROVE_OPTION) {
    			String filePath = jf.getSelectedFile().getAbsolutePath();
    			try {
					@SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(new FileReader(filePath));
					Object[] lines = br.lines().toArray();
					for (int i = 1; i < lines.length ; i++ ) {
						String[] row = lines[i].toString().split(",");
						model.addRow(row);
					}
                    JOptionPane.showMessageDialog(this, "IMPORT SUCCESSFUL","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		    		br.close();	
    			}catch(FileNotFoundException e) {
    				e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
    			}catch(@SuppressWarnings("hiding") IOException e) {
    				e.printStackTrace();
    			}	
                System.out.println("Import Successful");

    		}
    	}else if(event.getSource() == ExportButton) {
    		 for(int i=0; i<table.getRowCount();i++) {
             	for(int j=0; j<table.getColumnCount();j++) { 
             		if (table.getValueAt(i, j) == null) {
            			table.setValueAt( " ", i, j);
            		}}}
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setMultiSelectionEnabled(true);
            fileChooser.setDialogTitle("Export the data to");
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Document","xml"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File","txt"));
            fileChooser.setAcceptAllFileFilterUsed(true);
            int userSelection = fileChooser.showSaveDialog(this);
            if(userSelection == JFileChooser.APPROVE_OPTION){
                	File fileToSave = fileChooser.getSelectedFile();
                	String ftsEx = fileToSave.getPath();
                	int index = ftsEx.lastIndexOf('.');
               		String ext = ftsEx.substring(index + 1);                	
               		
               		if(ext.equals("xml")) {
               			xmlPath = ftsEx;
               		}
               		if(ext.equals("xsd")) {
               			xsdPath = ftsEx;
               		}
               		try {
                        //write txt document
                   		if(ext.equals("txt")) {
                   		FileWriter fw = new FileWriter(fileToSave);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (int i = 0; i < table.getRowCount(); i++) {
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                bw.write(table.getValueAt(i, j).toString()+" ");
                            }
                            bw.newLine();                    
                            }
                        JOptionPane.showMessageDialog(this, "EXPORT SUCCESSFUL","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                        bw.close();
                        fw.close();
                   		}
                   		
                   		
                   		//write xml document
                   		else if(ext.equals("xml")) {
                        	try {
                                DocumentBuilderFactory dbFactory =
                                DocumentBuilderFactory.newInstance();
                                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                                Document doc = dBuilder.newDocument();

                                Element rootElement = doc.createElement("Bibliography");
                                doc.appendChild(rootElement);
                                
                                for(int i=0; i<table.getRowCount();i++) {
                                	Element data = doc.createElement("row");
                                	rootElement.appendChild(data);
                                	for(int j=0; j<table.getColumnCount();j++) { 

                              				Element ele = doc.createElement(table.getColumnName(j).toString());
                                			data.appendChild(ele);
                                			ele.appendChild(doc.createTextNode(table.getValueAt(i, j).toString()));

                                	}
                                }
                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                Transformer transformer = transformerFactory.newTransformer();
                                DOMSource source = new DOMSource(doc);
                                StreamResult result = new StreamResult(fileToSave);
                                transformer.transform(source, result);
                                JOptionPane.showMessageDialog(this, "EXPORT SUCCESSFUL","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                                System.out.println("Export Successful");
                        	} catch (Exception e) {
                                e.printStackTrace();
                             }}
                   		}
             catch (IOException ex) {
                       JOptionPane.showMessageDialog(this, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);                    
                }
                    
            }
            
    	}else if(event.getSource()==ValidatorButton) {             
    	
    		try {SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                    Schema schema = factory.newSchema(xsdToValidate);
                    Validator validator = schema.newValidator();
					validator.validate(new StreamSource(xmlToValidate));
	   	
        	} catch (SAXException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            JOptionPane.showMessageDialog(this, "XML VALIDATED AGAINST XSD","INFORMATION",JOptionPane.INFORMATION_MESSAGE);	
            System.out.println("Validation successful");
    	}else if(event.getSource()==xmlSelectorButton) {             
        	
    		JFileChooser xmlfileChooser = new JFileChooser();
            xmlfileChooser.setDialogTitle("Choose XSD file");
            xmlfileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            xmlfileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML File","xml"));
            xmlfileChooser.setAcceptAllFileFilterUsed(true);
            int userSelection = xmlfileChooser.showSaveDialog(this);
            if(userSelection == JFileChooser.APPROVE_OPTION){
            	xmlToValidate = xmlfileChooser.getSelectedFile();
            	xmlPath = xmlToValidate.getPath();
            	xmlForValidation.setText(xmlPath);
         	}
        	
        }else if(event.getSource()==xsdSelectorButton) {             
            	
        		
        	JFileChooser xsdfileChooser = new JFileChooser();
                xsdfileChooser.setDialogTitle("Choose XSD file");
                xsdfileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                xsdfileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XSD File","xsd"));
                xsdfileChooser.setAcceptAllFileFilterUsed(true);
                int userSelection = xsdfileChooser.showSaveDialog(this);
                if(userSelection == JFileChooser.APPROVE_OPTION){
                	xsdToValidate = xsdfileChooser.getSelectedFile();
                	xsdPath = xsdToValidate.getPath();
                	xsdForValidation.setText(xsdPath);
                	
             	}
        
        }else if(event.getSource()==SearchButton) {
    		
    	
    		DefaultTableModel Model = (DefaultTableModel)table.getModel(); 
			TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(Model);
			table.setRowSorter(tr);

    		if(srchop.getSelectedItem().toString() == "Name Of Publication") {
    			
    			tr.setRowFilter(RowFilter.regexFilter(nameOfPub.getText().trim()));
        		
        	}else if(TOP.getSelectedItem().toString() == "Journal Name") {
    		
        		tr.setRowFilter(RowFilter.regexFilter(nameOfJour.getText().trim()));

        	}else {

    			tr.setRowFilter(RowFilter.regexFilter(locConf.getText().trim()));
        	
        	}
    		    		
    		table.setAutoCreateRowSorter(true);
    		table.setRowSorter(tr);
    		
    		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
    		int columnIndexToSort = 1;
    		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
    		tr.setSortKeys(sortKeys);
            System.out.println("Search Successful");

	
    	}else if(event.getSource() == ViewTableButton) {
    		
    		DefaultTableModel Model = (DefaultTableModel)table.getModel(); 
			TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(Model);
			table.setRowSorter(tr);
			nameOfJour.setText("");
			tr.setRowFilter(RowFilter.regexFilter(nameOfJour.getText()));

			table.setAutoCreateRowSorter(true);
    		table.setRowSorter(tr);
    		 	}
    	result.setText(message);
    	BlankDisplay();
    } //actionPerformed
   
    public void BlankDisplay() {
    	title.setText("");
        author.setText("");
        year.setText("");
        nameOfPub.setText("");
        DOI.setText("");
        date.setText("");
        nameOfJour.setText("");
        volumeOfJour.setText("");
        issueOfJour.setText("");
        nameConf.setText("");
        locConf.setText("");
        bookTitle.setText("");
        bookEditor.setText("");
        result.setText("");
    }

    
    @Override
	public void keyPressed(KeyEvent ev) {
		// TODO Auto-generated method stub
		}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		char c = event.getKeyChar();
    	if(((c >= '0' && c <= '9') && (year.getText().length() < 4)) || c == KeyEvent.VK_BACK_SPACE) {
        	year.setEditable(true);
        }else {
        	year.setEditable(false);
        }		
    }
	
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "dd/MM/yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }
	}
}
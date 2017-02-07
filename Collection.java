import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.InputMismatchException;
import java.awt.event.MouseAdapter;


public class Collection extends JFrame
{
    private String name;
    private int numConsignments;
    private Map<String,ArrayList> consignmentCollections;
    private Map <String,Buyer> buyerDetails;
    private Map <String, Boolean> collectionStatus;
    private BuyerCreator creator;
    private JComboBox buyerSelector;
    private int selectedRow;                            //holds the row selected on the buyerNameList table
    private JTable buyerNameList;                       //list on the BuyerCreator GUI
    private JTable consignmentList;                     //list on the CollectionManagement GUI
    private DefaultTableModel model;
    
    
    //file loadig and saving related instance variales
    private FileReader collectionFile;
    private BufferedReader collectionReader;

    
    private List consignmentListDisplay;
    private JLabel heading;
    private JPanel headingHolder;
    private JPanel buttonHolder;
    private JPanel componentHolder;
    
    private JButton createCollectionBtn;
    private JButton loadCollectionBtn;
    private JButton consignmentManagementBtn;
    private JButton closeBtn;
    public Collection()
    {
        super("Collections");
        displayGUI();
        addListeners();
        selectedRow = -1;
        collectionStatus = loadCollectionStatus();
        
    }
    
    private void addListeners()
    {
        createCollectionBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                name = JOptionPane.showInputDialog(null, "Please enter the name of the collection");
                if (name.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter a name for the collection");
                }
                else
                {
                    if(!consignmentCollections.containsKey(name))
                    {
                        consignmentCollections.put(name, new ArrayList<Consignment>());
                        collectionStatus.put(name,false);
                        consignmentListDisplay.add(name);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"A collection by that name already exists");
                    }
                }
            }
        
        });
        
        loadCollectionBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String currentLine = "";
                
                try
                {
                    collectionFile = new FileReader(new File("collection.txt"));
                    collectionReader =  new BufferedReader(collectionFile);
                    name = collectionReader.readLine();
                    
                    if(!consignmentCollections.containsKey(name))
                    {
                        consignmentListDisplay.add(name);
                        
                        numConsignments = Integer.parseInt(collectionReader.readLine());
                        consignmentCollections.put(name, new ArrayList<Consignment>());
                        collectionStatus.put(name,false);
                    }
                    else
                    {
                        throw (new InputMismatchException());
                    }
                    
                    
                    while((currentLine = collectionReader.readLine()) != null)
                    {
                        if(currentLine.equalsIgnoreCase("ART"))
                        {
                            String paintingName = collectionReader.readLine();
                            System.out.println(paintingName);
                            String disc = collectionReader.readLine();
                            System.out.println(disc);
                            String artist = collectionReader.readLine();
                            
                            System.out.println(artist);
                            double length = Double.parseDouble(collectionReader.readLine());
                            System.out.println(length);
                            double width = Double.parseDouble(collectionReader.readLine());
                            System.out.println(width);
                            double height = Double.parseDouble(collectionReader.readLine());
                            System.out.println(height);
                            
                            String date = collectionReader.readLine();
                            System.out.println(date);
                            
                            consignmentCollections.get(name).add( new Art(paintingName,disc,artist,length, width,height,date));
                            
                        }
                        
                        if(currentLine.equalsIgnoreCase("JEWELRY"))
                        {
                            String jewelName = collectionReader.readLine();
                            System.out.println(jewelName);
                            String disc = collectionReader.readLine();
                            System.out.println(disc);
                            String metal = collectionReader.readLine();
                            System.out.println(metal);
                            String gemStone = collectionReader.readLine();
                            System.out.println(gemStone);
                            String country = collectionReader.readLine();
                            System.out.println(country);
                            
                            consignmentCollections.get(name).add( new Jewelry(jewelName,disc,metal,gemStone, country));
                            
                        }
                        
                        if(currentLine.equalsIgnoreCase("AUTO"))
                        {
                            String carName = collectionReader.readLine();
                            System.out.println(carName);
                            String disc = collectionReader.readLine();
                            System.out.println(disc);
                            String make = collectionReader.readLine();
                            System.out.println(make);
                            String model = collectionReader.readLine();
                            System.out.println(model);
                            String year = collectionReader.readLine();
                            System.out.println(year);
                            double km = Double.parseDouble(collectionReader.readLine());
                            System.out.println(km);
                            
                            consignmentCollections.get(name).add( new Automotive(carName,disc,make,model, year,km));
                            
                        }
                        
                        if(currentLine.equalsIgnoreCase("PROPERTY"))
                        {
                            String propName = collectionReader.readLine();
                            System.out.println(propName);
                            String disc = collectionReader.readLine();
                            System.out.println(disc);
                            String address = collectionReader.readLine();
                            System.out.println(address);
                            String style = collectionReader.readLine();
                            System.out.println(style);
                            
                            consignmentCollections.get(name).add( new Property(propName,disc,address,style));
                            
                        }
                    }
                     
                }
                catch(FileNotFoundException fnf)
                {
                    JOptionPane.showMessageDialog(null,"There are no collections to load");
                }
                catch(NumberFormatException nf)
                {
                    JOptionPane.showMessageDialog(null,"There has been a problem reading the file");
                }
                catch(IOException io)
                {
                    io.printStackTrace();
                }
                catch(InputMismatchException im)
                {
                    JOptionPane.showMessageDialog(null,"This collection has already been loaded");
                }
            }
        });
        
        consignmentManagementBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                      name = consignmentListDisplay.getSelectedItem();
                      if(collectionStatus.get(name))
                        {
                            JOptionPane.showMessageDialog(null,"This Collection is closed.");
                        }
                        else
                        {
                            new CollectionManagement();
                        }
                    
                }
                catch(NullPointerException np)
                {
                    JOptionPane.showMessageDialog(null,"Please select a collection first");
                }
            }
        });
        
        addWindowListener(new SavingClass());
        closeBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }

    public void displayGUI()
    {
             
        //GUI Specifications
        consignmentListDisplay = new List(10);
            
        
        componentHolder = new JPanel();
        headingHolder = new JPanel();
        buttonHolder = new JPanel();
        heading = new JLabel("Collection List");
        createCollectionBtn = new JButton("Create Collection");
        loadCollectionBtn = new JButton("  Load Collection  ");
        consignmentManagementBtn = new JButton("Cons Management");
        closeBtn = new JButton("Close");
        
        
        consignmentCollections = loadConsignmentCollection();          //loading existing collection of consignments in the event that it has been saved.
        for(String i: consignmentCollections.keySet())
        {
            consignmentListDisplay.addItem(i);
        } 
        
        componentHolder.setLayout(new BorderLayout());
        headingHolder.add(heading);
        componentHolder.add(headingHolder,BorderLayout.NORTH);
        componentHolder.add(consignmentListDisplay, BorderLayout.CENTER);
        buttonHolder.add(loadCollectionBtn);
        buttonHolder.add(createCollectionBtn);
        buttonHolder.add(consignmentManagementBtn);
        buttonHolder.add(closeBtn);
        componentHolder.add(buttonHolder, BorderLayout.SOUTH);
        
        add(componentHolder);
        setVisible(true);
        setSize(600,250);
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
    }
    
    private class CollectionManagement extends JFrame
    {
        
        private Object[] columnNames = {"Consignment", "Highest Bid","Buyer"};
        private Object rawData[][];
        
        private JPanel componentHolder;
        private JPanel buttonHolder;
        private JPanel headingHolder;
        
        private JButton assignBuyerBtn;
        private JButton increaseBidBtn;
        private JButton closeCollectionBtn;
        private JButton backBtn;
        
        private JPanel buyerSelectorHolder;
        private JPanel frameSouth;
        private JLabel comboLabel;
        
        private JLabel heading;
        
        private JPanel consignmentType;
        
        private JRadioButton radArt;
        private JRadioButton radJewelry;
        private JRadioButton radAutomotive;
        private JRadioButton radProperty;
        private ButtonGroup types;
        
        private JPanel propertyDetails;
        
        
        public CollectionManagement()
        {
            super("Consignment Manager");
            rawData = new Object[0][3];
            displayGUI();
            addEventListeners();
            
        }
                
        private void setRawData()
        {
            model = new DefaultTableModel(rawData,columnNames);
            ArrayList<Consignment> consignments = consignmentCollections.get(name);
            for(Consignment i : consignments)
            {
                model.addRow(new String[]{i.getName(),i.getCurrentBidAmount()+"","No buyer assigned"});
                
            }
        }
        
        private void addEventListeners()
        {
            assignBuyerBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    { 
                        
                        if(buyerSelector.getSelectedItem().equals(""))
                        {
                            throw (new NullPointerException());
                        }
                        
                        model.setValueAt(buyerSelector.getSelectedItem(),selectedRow,2);
                        String chosenConsignmentName = model.getValueAt(selectedRow,0).toString();
                        
                        for(Object i: consignmentCollections.get(name))
                        {
                            Consignment y = (Consignment)i;
                            if(chosenConsignmentName.equals(y.getName()))
                            {
                                y.setBuyer(buyerDetails.get(buyerSelector.getSelectedItem()));
                            }
                        }
                        
                    }
                    catch(NullPointerException np)
                    {
                        new BuyerCreator();
                    }
                    catch(IndexOutOfBoundsException iob)
                    {
                        JOptionPane.showMessageDialog(null,"Please select a Consignment first. Note that a higlighted row is not necessarily selected. \nA blue inner border must appear in a cell to ensure that the row is selected");
                    }
                    finally
                    {
                        selectedRow = -1;
                    }
                }
            
            });
            
            increaseBidBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        if(model.getValueAt(selectedRow,2).equals("No buyer assigned"))
                        {
                            JOptionPane.showMessageDialog(null,"A buyer must be assigned before a bid can be placed");
                        }
                        else
                        {
                            double newBid = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter new bid amount"));
                            String chosenConsignmentName = model.getValueAt(selectedRow,0).toString();
                        
                            for(Object i: consignmentCollections.get(name))
                            {
                                Consignment y = (Consignment)i;
                                if(chosenConsignmentName.equals(y.getName()))
                                {
                                    y.setCurrentBid(newBid);
                                }
                            }
                            
                            model.setValueAt(newBid,selectedRow,1);
                        }
                    }
                    catch(InputMismatchException ime)
                    {
                        JOptionPane.showMessageDialog(null,"New bid amount must be greater than the previous bid");
                    }
                    catch(NumberFormatException nfe)
                    {
                        JOptionPane.showMessageDialog(null,"Please enter positive numbers only.");
                    }
                    
                    catch(IndexOutOfBoundsException iob)
                    {
                        JOptionPane.showMessageDialog(null,"Please select a consignent with a buyer. A row is selected if one of its cells has a blue inner border");
                    }
                }
            });
            
            backBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    setVisible(false);
                    dispose();
                }
            });
            
            closeCollectionBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    new ShowReport();
                    setVisible(false);
                    collectionStatus.put(name,true);
                }
            });
            consignmentList.addMouseListener(new ListEvent());
            radProperty.addActionListener(new RadioListener());
            radAutomotive.addActionListener(new RadioListener());
            radJewelry.addActionListener(new RadioListener());
            radArt.addActionListener(new RadioListener());
        }
        
        private void displayGUI()
        {
            //*******************GUI***********************
            setRawData();
            
            consignmentList = new JTable(model);
            setLayout(new BorderLayout());
            
            headingHolder = new JPanel();
            heading = new JLabel("Consignment List");
            headingHolder.add(heading);
            add(headingHolder,BorderLayout.NORTH);
          
            buyerSelector = new JComboBox();
            //buyerSelector.addItem("Katleho");
            buyerSelector.setEditable(false);
            buyerSelector.setPreferredSize(new Dimension(200,20));
            comboLabel = new JLabel("Choose Buyer");
            comboLabel.setLabelFor(buyerSelector);
            frameSouth = new JPanel();
            
            frameSouth.setLayout(new GridLayout(3,1,5,5));
            buyerSelectorHolder = new JPanel();
            buyerSelectorHolder.add(comboLabel);
            buyerSelectorHolder.add(buyerSelector);
            
            backBtn = new JButton("Back");
            assignBuyerBtn = new JButton("Assign Buyer");
            increaseBidBtn = new JButton("Inrease Bid");
            closeCollectionBtn = new JButton("Close collection");
            
            buttonHolder = new JPanel();
            buttonHolder.add(backBtn);
            buttonHolder.add(assignBuyerBtn);
            buttonHolder.add(increaseBidBtn);
            buttonHolder.add(closeCollectionBtn);
            
            
            consignmentType = new JPanel();
            radArt = new JRadioButton("Art");
            radJewelry = new JRadioButton("Jewelry");
            radAutomotive = new JRadioButton("Automotive");
            radProperty = new JRadioButton("Property");
            types = new ButtonGroup();
            
            consignmentType.add(radArt);
            consignmentType.add(radJewelry);
            consignmentType.add(radAutomotive);
            consignmentType.add(radProperty);
            types.add(radArt);
            types.add(radJewelry);
            types.add(radAutomotive);
            types.add(radProperty);
            
            frameSouth.add(consignmentType);
            frameSouth.add(buyerSelectorHolder);
            frameSouth.add(buttonHolder);
            
            add(frameSouth, BorderLayout.SOUTH);
            add(new JScrollPane(consignmentList), BorderLayout.CENTER);
            
            setVisible(true);
            setSize(600,450);
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        }
        
        private class RadioListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == radProperty)
                {
                    new PropertyMaker();
                }
                
                if(e.getSource() == radAutomotive)
                {
                    new AutomotiveMaker();
                }
                
                if(e.getSource() == radJewelry)
                {
                    new JewelryMaker();
                }
                
                if(e.getSource() == radArt)
                {
                    new ArtMaker();
                }
            }
        }
    }
    
    private class BuyerCreator extends JFrame
    {
       
        
        //components for GUI
        
        private JPanel componentHolder;                             //holds all the componets on the Buyer Window
        private JPanel buttons;
        private JButton addBuyerBtn;
        private JButton removeBuyerBtn;
        private JButton doneBtn;
        
        private JTextField nameTF;
        private JTextField addressTF;
        private JTextField phoneNumTF;
        private JTextField emailTF;
        
        private JLabel nameLabel;
        private JLabel addressLabel;
        private JLabel phoneNumLabel;
        private JLabel emailLabel;
        
        private JPanel dataEntry;
        private JPanel dataEntryHolder;
        private DefaultTableModel model;
        
        private String columnHeadings[] = {"Name","Address","Phone Number","Email"};
        
        
        private BuyerCreator()
        {
            super("Buyer Details");
            displayGUI();
            addListeners();
            buyerDetails = new HashMap<String, Buyer>();
        }
        
        private void displayGUI()
        {
            //**********************************************GUI*****************************************
            //component related instantiations
            //buyerListHeading = new JLabel("List Of Buyers");
           
            
            componentHolder = new JPanel();
            buttons = new JPanel();
            addBuyerBtn = new JButton("Add Buyer");
            removeBuyerBtn = new JButton("Remove Buyer");
            doneBtn = new JButton("Done");
            
            //adding buttons to buttons panel
            buttons.add(doneBtn);
            
            //rawData = new Object[10][4];
            model = new DefaultTableModel(columnHeadings,0);  
            buyerNameList = new JTable(model);
            
            //componets for data entry panel
            nameLabel = new JLabel("Name") ;
            addressLabel = new JLabel("Address");
            phoneNumLabel = new JLabel("Phone Number");
            emailLabel = new JLabel("Email");
            
            nameTF = new JTextField();
            addressTF = new JTextField();
            phoneNumTF = new JTextField();
            emailTF = new JTextField();
            
            dataEntry = new JPanel();
            GridLayout grid = new GridLayout(5,2);              
           
            dataEntry.setLayout(grid);
            dataEntry.add(nameLabel);
            dataEntry.add(nameTF);
            dataEntry.add(addressLabel);
            dataEntry.add(addressTF);
            dataEntry.add(phoneNumLabel);
            dataEntry.add(phoneNumTF);
            dataEntry.add(emailLabel );
            dataEntry.add(emailTF);
            dataEntry.add(addBuyerBtn);
            dataEntry.add(removeBuyerBtn);
            dataEntry.setSize(300,100);
            
            dataEntryHolder = new JPanel();
            dataEntryHolder.setLayout(new BorderLayout());
            dataEntryHolder.add(dataEntry, BorderLayout.NORTH);
            //configuring layout manager classes
            setLayout(new BorderLayout());
            componentHolder.setLayout(new GridLayout(3,1));
            
            //adding componets
            //add(buyerListHeading,BorderLayout.NORTH);
            componentHolder.add(dataEntryHolder);
            componentHolder.add(new JScrollPane(buyerNameList));
            componentHolder.add(buttons);
            
            add(componentHolder);
            setSize(400,450);
            setLocation(700,300);
            setVisible(true);
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            //******************************************************************************************
        }
        
        private void addListeners() 
        {
            //adding listeners
            addBuyerBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        if(buyerDetails.keySet().contains(nameTF.getText()))
                        {
                            throw (new InputMismatchException());
                        }
                        String[] details = {nameTF.getText(),addressTF.getText(),phoneNumTF.getText(),emailTF.getText()};
                        try
                        {
                            for(String i:details)
                            {
                                if(i.equals(""))
                                {
                                    throw(new NullPointerException());
                                }
                            }
                            buyerDetails.put(nameTF.getText(),new Buyer(nameTF.getText(),addressTF.getText(),phoneNumTF.getText(),emailTF.getText()));
                            model.addRow(details);
                            nameTF.setText("");
                            addressTF.setText("");
                            phoneNumTF.setText("");
                            emailTF.setText("");
                        }   
                        catch(NullPointerException npe)
                        {
                            JOptionPane.showMessageDialog(null, "Please complete every field");
                        }
                    }
                    catch(InputMismatchException im)
                    {
                        JOptionPane.showMessageDialog(null, "That buyer has already been added");
                    }
                }
            });
            
            removeBuyerBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                   try
                   {
                        buyerDetails.remove(model.getValueAt(selectedRow,0));
                        model.removeRow(selectedRow);
                        
                    }
                    catch(ArrayIndexOutOfBoundsException aiob)
                    {
                        JOptionPane.showMessageDialog(null, "Please select a buyer first");
                        
                    }
                    finally
                    {
                        selectedRow = -1;
                    }
                }
            });
            
            buyerNameList.addMouseListener(new ListEvent()); 

            doneBtn.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        if(buyerDetails.size()==0)
                        {
                            throw(new NullPointerException());
                        }
                        else
                        {
                            Iterator buyerAdder = buyerDetails.keySet().iterator();
                            while(buyerAdder.hasNext())
                            {
                                 buyerSelector.addItem(buyerAdder.next());
                            }
                            
                            //save(consignmentCollections,buyerDetails);
                            
                            setVisible(false);
                            
                        };
                    }
                    catch(NullPointerException np)
                    {
                        JOptionPane.showMessageDialog(null, "At least one buyer must be added");
                    }
                    finally
                    {
                        selectedRow = -1;
                    }
                }
            });
        }
    }
    
    private class ListEvent extends MouseAdapter
    {
            public void mouseClicked(MouseEvent e)
            {
                if(e.getSource() == buyerNameList)
                {
                    selectedRow = buyerNameList.rowAtPoint(e.getPoint());
                }
                
                if(e.getSource() == consignmentList)
                {
                    selectedRow = consignmentList.rowAtPoint(e.getPoint());
                }
            }
    }
    
    private class PropertyMaker extends JFrame
    {
        private JPanel details;
        private JTextField nameTF;
        private JTextField descriptionTF;
        private JTextField addressTF;
        private JTextField styleTF;
        private JLabel nameLabel;
        private JLabel descriptionLabel;
        private JLabel addressLabel;
        private JLabel styleLabel;
        private JPanel buttons;
        private JButton done;
        
        private PropertyMaker()
        {
            super("Property Consignment details");
            displayGUI();
            addListeners();
        }
        
        private void displayGUI()
        {
            details = new JPanel();
            nameTF = new JTextField();
            descriptionTF = new JTextField();
            addressTF = new JTextField();
            styleTF = new JTextField();
            
            buttons = new JPanel();
            done = new JButton("Done");
            buttons.add(done);
            
            nameLabel= new JLabel("Name");
            descriptionLabel= new JLabel("Description");
            addressLabel= new JLabel("Address");
            styleLabel= new JLabel("Style");
            
            details.setLayout(new GridLayout(4,2,5,5));
            details.add(nameLabel);
            details.add(nameTF);
            details.add(descriptionLabel);
            details.add(descriptionTF);
            details.add(addressLabel);
            details.add(addressTF);
            details.add(styleLabel);
            details.add(styleTF);
            
            setLayout(new BorderLayout());
            add(details, BorderLayout.CENTER);
            add(buttons,BorderLayout.SOUTH);
            setSize(350,200);
            setVisible(true);
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            
        }
        
        private void addListeners()
        {
            done.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        String[] details = new String[] {nameTF.getText(),descriptionTF.getText(),addressTF.getText(),styleTF.getText()};
                        for(String i: details)
                        {
                            if(i.equals(""))
                            {
                                throw (new NullPointerException());
                            }
                        }
                        
                        consignmentCollections.get(name).add(new Property(nameTF.getText(),descriptionTF.getText(),addressTF.getText(),styleTF.getText()));
                        String[] newRow = new String[]{nameTF.getText(),"0.0","No Buyer Assigned"};
                        model.addRow(newRow);
                        
                        //save(consignmentCollections,buyerDetails);
                        
                        setVisible(false);
                    }
                    catch(NullPointerException np)
                    {
                        JOptionPane.showMessageDialog(null,"Please complete all fields");
                    }
                }
            });
        }
    }
    
    private class AutomotiveMaker extends JFrame
    {
        private JPanel details;
        private JTextField nameTF;
        private JTextField descriptionTF;
        private JTextField makeTF;
        private JTextField modelTF;
        private JTextField yearTF;
        private JTextField kiloTF;
        private JLabel nameLabel;
        private JLabel descriptionLabel;
        private JLabel makeLabel;
        private JLabel modelLabel;
        private JLabel yearLabel;
        private JLabel kiloLabel;
        private JPanel buttons;
        private JButton done;
        
        private AutomotiveMaker()
        {
            super("Automotive Details");
            displayGUI();
            addListeners();
        }
        
        private void displayGUI()
        {
            details = new JPanel();
            nameTF = new JTextField();
            descriptionTF = new JTextField();
            makeTF = new JTextField();
            modelTF = new JTextField();
            yearTF = new JTextField();
            kiloTF = new JTextField();
            
            buttons = new JPanel();
            done = new JButton("Done");
            buttons.add(done);
            
            nameLabel= new JLabel("Name");
            descriptionLabel= new JLabel("Description");
            makeLabel= new JLabel("Make");
            modelLabel= new JLabel("Model");
            yearLabel= new JLabel("Year");
            kiloLabel= new JLabel("Kilometers");
            
            details.setLayout(new GridLayout(6,2,5,5));
            details.add(nameLabel);
            details.add(nameTF);
            details.add(descriptionLabel);
            details.add(descriptionTF);
            details.add(makeLabel);
            details.add(makeTF);
            details.add(modelLabel);
            details.add(modelTF);
            details.add(yearLabel);
            details.add(yearTF);
            details.add(kiloLabel);
            details.add(kiloTF);
            
            setLayout(new BorderLayout());
            add(details, BorderLayout.CENTER);
            add(buttons,BorderLayout.SOUTH);
            setSize(350,250);
            setVisible(true);
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            
        }
        
        private void addListeners()
        {
            done.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        String[] details = new String[] {nameTF.getText(),descriptionTF.getText(),makeTF.getText(),modelTF.getText(),yearTF.getText(),kiloTF.getText()};
                        for(String i: details)
                        {
                            if(i.equals(""))
                            {
                                throw (new NullPointerException());
                            }
                        }
                        
                        double kilometers = Double.parseDouble(kiloTF.getText());
                        double yearTester = Double.parseDouble(yearTF.getText());
                        consignmentCollections.get(name).add(new Automotive(nameTF.getText(),descriptionTF.getText(),makeTF.getText(),modelTF.getText(),yearTF.getText(),kilometers));
                        String[] newRow = new String[]{nameTF.getText(),"0.0","No Buyer Assigned"};
                        model.addRow(newRow);
                        
                        //save(consignmentCollections,buyerDetails);
                        
                        setVisible(false);
                    }
                    catch(NullPointerException np)
                    {
                        JOptionPane.showMessageDialog(null,"Please complete all fields");
                    }
                    catch(NumberFormatException nf)
                    {
                        JOptionPane.showMessageDialog(null,"Please use numeric values for the kilometers and the year");
                    }
                }
            });
        }
    }
    
    private class JewelryMaker extends JFrame
    {
        private JPanel details;
        private JTextField nameTF;
        private JTextField descriptionTF;
        private JTextField metalTF;
        private JTextField gemTF;
        private JTextField countryTF;
        
        private JLabel nameLabel;
        private JLabel descriptionLabel;
        private JLabel metalLabel;
        private JLabel gemLabel;
        private JLabel countryLabel;
        private JLabel dateLabel;
        private JPanel buttons;
        private JButton done;
        
        private JewelryMaker ()
        {
            super("Property Consignment details");
            displayGUI();
            addListeners();
        }
        
        private void displayGUI()
        {
            details = new JPanel();
            nameTF = new JTextField();
            descriptionTF = new JTextField();
            metalTF = new JTextField();
            gemTF = new JTextField();
            countryTF = new JTextField();
           
            
            buttons = new JPanel();
            done = new JButton("Done");
            buttons.add(done);
            
            nameLabel= new JLabel("Name");
            descriptionLabel= new JLabel("Description");
            metalLabel= new JLabel("Type Of Metal");
            gemLabel= new JLabel("Style");
            countryLabel= new JLabel("Country");
            
            details.setLayout(new GridLayout(5,2,5,5));
            details.add(nameLabel);
            details.add(nameTF);
            details.add(descriptionLabel);
            details.add(descriptionTF);
            details.add(metalLabel);
            details.add(metalTF);
            details.add(gemLabel);
            details.add(gemTF);
            details.add(countryLabel);
            details.add(countryTF);
            
            setLayout(new BorderLayout());
            add(details, BorderLayout.CENTER);
            add(buttons,BorderLayout.SOUTH);
            setSize(350,200);
            setVisible(true);
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        }
        
        private void addListeners()
        {
            done.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        String[] details = new String[] {nameTF.getText(),descriptionTF.getText(),metalTF.getText(),gemTF.getText(),countryTF.getText()};
                        for(String i: details)
                        {
                            if(i.equals(""))
                            {
                                throw (new NullPointerException());
                            }
                        }
                        
                        consignmentCollections.get(name).add(new Jewelry(nameTF.getText(),descriptionTF.getText(),metalTF.getText(),gemTF.getText(),countryTF.getText()));
                        String[] newRow = new String[]{nameTF.getText(),"0.0","No Buyer Assigned"};
                        model.addRow(newRow);
                        
                        setVisible(false);
                    }
                    catch(NullPointerException np)
                    {
                        JOptionPane.showMessageDialog(null,"Please complete all fields");
                    }
                }
            });
        }
    }
    
    private class ArtMaker extends JFrame
    {
        private JPanel details;
        private JTextField nameTF;
        private JTextField descriptionTF;
        private JTextField artistTF;
        private JTextField widthTF;
        private JTextField heightTF;
        private JTextField lengthTF;
        private JTextField dateTF;
        
        private JLabel nameLabel;
        private JLabel descriptionLabel;
        private JLabel  artistLabel;
        private JLabel widthLabel;
        private JLabel heightLabel;
        private JLabel lengthLabel;
        private JLabel dateLabel;
        private JPanel buttons;
        private JButton done;
        
        private ArtMaker()
        {
            super("Art Details");
            displayGUI();
            addListeners();
        }
        
        private void displayGUI()
        {
            details = new JPanel();
            nameTF = new JTextField();
            descriptionTF = new JTextField();
            artistTF = new JTextField();
            widthTF = new JTextField();
            heightTF = new JTextField();
            lengthTF = new JTextField();
            dateTF = new JTextField();
            
            buttons = new JPanel();
            done = new JButton("Done");
            buttons.add(done);
            
            nameLabel= new JLabel("Name");
            descriptionLabel= new JLabel("Description");
            artistLabel= new JLabel("Artist");
            widthLabel= new JLabel("Width");
            heightLabel= new JLabel("Height");
            lengthLabel= new JLabel("Length");
            dateLabel= new JLabel("Date");
            
            details.setLayout(new GridLayout(7,2,5,5));
            details.add(nameLabel);
            details.add(nameTF);
            details.add(descriptionLabel);
            details.add(descriptionTF);
            details.add(artistLabel);
            details.add(artistTF);
            details.add(widthLabel);
            details.add(widthTF);
            details.add(heightLabel);
            details.add(heightTF);
            details.add(lengthLabel);
            details.add(lengthTF);
            details.add(dateLabel);
            details.add(dateTF);
            
            setLayout(new BorderLayout());
            add(details, BorderLayout.CENTER);
            add(buttons,BorderLayout.SOUTH);
            setSize(350,250);
            setVisible(true);
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        }
        
        private void addListeners()
        {
            done.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        String[] details = new String[] {nameTF.getText(),descriptionTF.getText(),artistTF.getText(),widthTF.getText(),heightTF.getText(),lengthTF.getText(),dateTF.getText()};
                        for(String i: details)
                        {
                            if(i.equals(""))
                            {
                                throw (new NullPointerException());
                            }
                        }
                        
                        double lengthChecker = Double.parseDouble(lengthTF.getText());
                        double widthChecker = Double.parseDouble(widthTF.getText());
                        double heightChecker = Double.parseDouble(heightTF.getText());
                        
                        consignmentCollections.get(name).add(new Art(nameTF.getText(),descriptionTF.getText(),artistTF.getText(),lengthChecker ,widthChecker,heightChecker,dateTF.getText()));
                        String[] newRow = new String[]{nameTF.getText(),"0.0","No Buyer Assigned"};
                        model.addRow(newRow);
                        
                        //save(consignmentCollections,buyerDetails);
                        
                        setVisible(false);
                    }
                    catch(NullPointerException np)
                    {
                        JOptionPane.showMessageDialog(null,"Please complete all fields");
                    }
                    catch(NumberFormatException nf)
                    {
                        JOptionPane.showMessageDialog(null,"Please use numeric values for the kilometers and the year");
                    }
                }
            });
        }
    }
    
    private class ShowReport extends JFrame
    {
       private JPanel mainHolder;
       private JPanel soldHeadingHolder;
       private JLabel soldHeading;
       
       private JPanel soldTableAndHeading;
       private Object[][] soldTableData;
       private DefaultTableModel soldTableModel;
       private JTable soldTable;
       private JScrollPane soldView;
       
       private JPanel unsoldHeadingHolder;
       private JLabel unsoldHeading;
       private JPanel unsoldTableAndHeading;
       
       private Object[][] unsoldTableData;
       private DefaultTableModel unsoldTableModel;
       private JTable unsoldTable;
       private JScrollPane unsoldView;
       
       private JPanel bothTables;
       private JPanel doneButtonHolder;
       private JButton closeReportBtn;
       
       private ShowReport()
       {
           super("Auction Report");
           displayGUI();
           addEventListeners();
       }
       
       private void displayGUI()
       {
           fillTables();
           soldHeadingHolder = new JPanel();
           soldHeading = new JLabel("Sold Consignments");
           soldHeadingHolder.add(soldHeading);
           
           soldTableAndHeading = new JPanel();
           soldTableAndHeading.setLayout(new BorderLayout());
           soldTableAndHeading.add(soldHeadingHolder,BorderLayout.NORTH);
           
           soldTable = new JTable(soldTableModel);
           
           soldTableAndHeading.add(new JScrollPane(soldTable),BorderLayout.CENTER);
           
           unsoldTableAndHeading = new JPanel();
           unsoldTableAndHeading.setLayout(new BorderLayout());
           
           unsoldHeadingHolder = new JPanel();
           unsoldHeading = new JLabel("Unsold Consignments");
           unsoldHeadingHolder.add(unsoldHeading);
           unsoldTableAndHeading.setLayout(new BorderLayout());
           unsoldTableAndHeading.add(unsoldHeadingHolder,BorderLayout.NORTH);
           unsoldTable = new JTable(unsoldTableModel);
           
           unsoldTableAndHeading.add(new JScrollPane(unsoldTable),BorderLayout.CENTER);
           
           bothTables = new JPanel();
           bothTables.setLayout(new GridLayout(2,1));
           bothTables.add(soldTableAndHeading);
           bothTables.add(unsoldTableAndHeading);
           
           doneButtonHolder = new JPanel();
           closeReportBtn = new JButton("Close Report");
           doneButtonHolder.add(closeReportBtn);
           mainHolder = new JPanel();
           mainHolder.setLayout(new BorderLayout());
           mainHolder.add(bothTables,BorderLayout.CENTER);
           mainHolder.add(doneButtonHolder,BorderLayout.SOUTH);
           
           add(mainHolder);
           setSize(600,450);
           setVisible(true);
           
           Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
           this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       }
       
       private void fillTables()
       {
           soldTableData = new Object[0][4];
           unsoldTableData = new Object[0][4];
           String[] tableHeadings = {"Name","Description","Sold At","Buyer"};
           
           soldTableModel = new DefaultTableModel(soldTableData,tableHeadings);
           unsoldTableModel = new DefaultTableModel(unsoldTableData,tableHeadings);
           ArrayList<Consignment> consignments = consignmentCollections.get(name);
           String[] currentRow = {"","","",""};
           
           for(Consignment i : consignments)
           {
               try
               {
                   currentRow = new String[]{i.getName(),i.getDescription(),i.getCurrentBidAmount()+"",i.getBuyer().getName()};
                   soldTableModel.addRow(currentRow);
               }
               catch(NullPointerException np)
               {
                   currentRow = new String[]{i.getName(),i.getDescription(),i.getCurrentBidAmount()+"","No buyer"};
                   unsoldTableModel.addRow(currentRow);
               }
           }
       }
       
       private void addEventListeners()
       {
           closeReportBtn.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                   setVisible(false);
                   dispose();
               }
           });
       }
    }
    

    private static void save(Map<String,ArrayList> consignments, Map<String, Boolean> closedStatuses)
    {
        FileOutputStream fileOutConsignments = null;
        ObjectOutputStream consignmentsOut = null;
        
        try
        {
            fileOutConsignments = new FileOutputStream(new File("consignments.dat"));
            consignmentsOut = new ObjectOutputStream(fileOutConsignments);
            consignmentsOut.writeObject(consignments);
            
            fileOutConsignments = new FileOutputStream(new File("collectionStatuses.dat"));
            consignmentsOut = new ObjectOutputStream(fileOutConsignments);
            consignmentsOut.writeObject(closedStatuses);
        }
        catch(FileNotFoundException f)
        {
            f.printStackTrace();
        }
        catch(StreamCorruptedException e)
        {
            e.printStackTrace();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
        finally
        {
            try
            {
                if(fileOutConsignments != null)
                {
                    fileOutConsignments.close();
                }
                if(consignmentsOut != null)
                {
                    consignmentsOut.close();
                }
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
    
    private static HashMap<String,ArrayList> loadConsignmentCollection()
    {
        File fileToLoad = new File("consignments.dat");
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        HashMap loadedCollection = new HashMap<String,ArrayList>();
        
        try
        {
            if(fileToLoad.canRead() && fileToLoad.isFile())
            {
                fileIn = new FileInputStream(fileToLoad);
                objectIn = new ObjectInputStream(fileIn);
                loadedCollection = (HashMap<String,ArrayList>)objectIn.readObject();
            }            
        }
        catch(FileNotFoundException f)
        {
            f.printStackTrace();
        }
        catch(StreamCorruptedException e)
        {
            e.printStackTrace();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
        catch(ClassNotFoundException c)
        {
            c.printStackTrace();
        }
        catch(ClassCastException cce)
        {
            //cce.printStackTrace();
        }
        finally
        {
            try
            {
                if(fileIn != null)
                {
                    fileIn.close();
                }
                
                if(objectIn != null)
                {
                    objectIn.close();
                }
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            finally
            {
                return loadedCollection;
            }
        } 
    }
    
    private static HashMap<String,Boolean> loadCollectionStatus()
    {
        File fileToLoad = new File("collectionStatuses.dat");
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        HashMap loadedCollection = new HashMap<String,Boolean>();
        
        try
        {
            if(fileToLoad.canRead() && fileToLoad.isFile())
            {
                fileIn = new FileInputStream(fileToLoad);
                objectIn = new ObjectInputStream(fileIn);
                loadedCollection = (HashMap<String,Boolean>)objectIn.readObject();
            }            
        }
        catch(FileNotFoundException f)
        {
            f.printStackTrace();
        }
        catch(StreamCorruptedException e)
        {
            e.printStackTrace();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
        catch(ClassNotFoundException c)
        {
            c.printStackTrace();
        }
        catch(ClassCastException cce)
        {
            cce.printStackTrace();
        }
        finally
        {
            try
            {
                if(fileIn != null)
                {
                    fileIn.close();
                }
                
                if(objectIn != null)
                {
                    objectIn.close();
                }
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            finally
            {
                return loadedCollection;
            }
        } 
    }
    
    private class SavingClass extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            save(consignmentCollections, collectionStatus);
        }
    }
    
}
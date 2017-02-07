import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import java.io.*;


public class Buyer extends JFrame implements Serializable
{
    //instance variables
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    
    
    public Buyer() 
    {
        super("Buyer Details");
        //initialising instance variables
        
        name = "";
        address = "";
        phoneNumber = "";
        email = "";
        

    }
    
    public Buyer(String n, String a, String p, String e)
    {
        name = n;
        address = a;
        phoneNumber = p;
        email = e;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String a)
    {
        address = a;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public void setPhonenNumber(String p)
    {
        phoneNumber = p;
    }
    
    public String getPhonenNumber()
    {
        return phoneNumber;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String toString()
    {
        String details = "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber + "Email: " + email;
        return details;
    }
}
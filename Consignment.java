import java.util.InputMismatchException;
import java.io.*;
public class Consignment implements Serializable
{
    private String name;
    private double currentBidAmount;
    private Buyer customer;
    private String description;
    
    public Consignment()
    {
        name = "";
        currentBidAmount = 0;
        description = "";
    }
    
    public Consignment(String n, String d)
    {
        name = n;
        currentBidAmount = 0;
        description = d;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getCurrentBidAmount()
    {
        return currentBidAmount;
    }
    
    public void setBuyer(Buyer b)
    {
        customer = new Buyer(b.getName(),b.getAddress(),b.getPhoneNumber(),b.getEmail());
    }
    
    public void setCurrentBid(double d) throws InputMismatchException
    {
        if(d > currentBidAmount )
        {
            currentBidAmount = d; 
        }
        else
        {
            throw (new InputMismatchException());
        }
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public Buyer getBuyer() throws NullPointerException
    {
        Buyer duplicate = new Buyer(customer.getName(),customer.getAddress(),customer.getPhoneNumber(),customer.getEmail());
        return duplicate;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public void setDescription(String d)
    {
        description = d;
    }
    
    public String toString()
    {
        String attributes = "Name: "+name+"\nDescription: "+ description + "Current Bid Price: " + currentBidAmount +"Buyer" + customer.toString();
        return attributes;
    }
}
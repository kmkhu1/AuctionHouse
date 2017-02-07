public class Property extends Consignment
{
    private String address;
    private String style;
    
    public Property()
    {
        super();
        address = "";
        style = "";
    }
    
     public Property(String n, String d, String a, String s)
    {
        super(n,d);
        address = a;
        style = s;
    } 
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String a)
    {
        address = a;
    }
    
    public void setStyle(String s)
    {
        style  = s;
    }
    
    public String getStyle()
    {
        return style;
    }
    
    public String toString()
    {
        return super.toString() + "\nAddress: " + address + "\nStyle: " + style;
    }
}
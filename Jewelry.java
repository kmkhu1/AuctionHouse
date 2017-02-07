public class Jewelry extends Consignment
{
    private String metalType;
    private String gemStone;
    private String countryOfOrigin;
    
    public Jewelry()
    {
        super();
        metalType = "";
        gemStone = "";
        countryOfOrigin = "";  
    }
    
    public Jewelry(String name, String disc, String m, String g, String c)
    {
        super(name,disc);
        metalType = m;
        gemStone = g;
        countryOfOrigin = c;  
    }
    
    public String getMetalType()
    {
        return metalType;
    }
    
    public String getGemStone()
    {
        return gemStone;
    }
    
    public String getCountryOfOrigin()
    {
        return countryOfOrigin;
    }
    
    public void setMetalType(String m)
    {
        metalType = m;
    }
    
    public void setGemStone(String m)
    {
        gemStone = m;
    }
    
    public void setCountryOfOrigin(String m)
    {
        countryOfOrigin = m;
    }
    
    public String toString()
    {
        return super.toString() + metalType + countryOfOrigin + gemStone;
    }
}
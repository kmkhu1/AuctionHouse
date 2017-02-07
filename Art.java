public class Art extends Consignment 
{
    
    private String artist;
    private double width;
    private double height;
    private double length;
    private String date;      //be sure to find a better way to store the date 
    
    public Art()
    {
        super();
        artist = "";
        width =  0;
        height = 0;
        length = 0;
        date = "";
    }
    
    public Art(String n, String d, String a, double l,double w,double h, String y)
    {
        super(n,d);
        artist = a;
        length = l;
        width = w;
        height = h;
        date = y;
        
    }
    
    public double getWidth()
    {
        return width;
    }
    
    public double getHeight()
    {
        return height;
    }
    
    public double getLength()
    {
        return length;
    }
    
    public String getArtist()
    {
        return artist;
    }
    
     public String getDate()
    {
        return date;
    }
    
    public void setWidth(double d)
    {
        width = d;
    }
    
    public void setLength(double d)
    {
        length = d;
    }
    
    public void setHeight(double d)
    {
        height = d;
    }
    
    public void setArtist(String d)
    {
        artist = d;
    }
    
    public void setDate(String d)
    {
        date = d;
    }
    
    public String toString()
    {
       return super.toString() + length + artist + height + date + width; 
    }
}
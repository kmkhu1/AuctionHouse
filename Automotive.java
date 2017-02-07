public class Automotive extends Consignment
{
    private String make;
    private String model;
    private String yearMade;   
    private double kilometers;
    
    public Automotive()
    {
        super();
        make = "";
        model = "";
        yearMade = "";
        kilometers = 0;
    }
    
    public Automotive(String n, String d, String m, String mo, String y, double k)
    {
        super(n,d);
        make = m;
        model = mo;
        yearMade = y;
        kilometers = k;
    }
    
    public String getMake()
    {
        return make;
    }
    
    public String getModel()
    {
        return model;
    }
    
        public String getYear()
    {
        return yearMade;
    }
    
    public double getKilometers()
    {
        return kilometers;
    }
    
    public void setMake(String m)
    {
        make = m;
    }
    
    public void setModel(String m)
    {
        model = m;
    }
    
        public void setYear(String y)
    {
        yearMade = y;
    }
    
    public void setKilometers(double k)
    {
        kilometers = k;
    }
    
    public String toString()
    {
        return super.toString() + make + model + yearMade + kilometers;
    }
}
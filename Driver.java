import java.util.InputMismatchException;
import java.io.*;

public class Driver
{
    public static void main(String[] args)
    {
        new Collection();
    }
    /*
    private static void save(Collection cm)
    {
        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;
        File fileToSave = new File("system.dat");
        
        try
        {
            fileOut = new FileOutputStream(fileToSave);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(cm);
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
                if(fileOut != null)
                {
                    fileOut.close();
                }
                if(objectOut != null)
                {
                    objectOut.close();
                }
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
    
    private static Collection loadCollection()
    {
        File fileToLoad = new File("system.dat");
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        Collection loadedObject = null;
        
        try
        {
            if(fileToLoad.canRead() && fileToLoad.isFile())
            {
                fileIn = new FileInputStream(fileToLoad);
                objectIn = new ObjectInputStream(fileIn);
                loadedObject = (Collection)objectIn.readObject();
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
                return loadedObject;
            }
        } 
    }
    */
}
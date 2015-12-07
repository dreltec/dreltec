package be.peter.archive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.peter.archive.Archive">Peter Geuens</a>
 */
public class Archive
{
  public static final String PATH = "c:\\temp\\pdfcards\\data\\multiverseids.csv";
  public static final String IMAGESPATH = "c:\\temp\\pdfcards\\data\\";

  private static Map<String, String> multiVerseIds= new HashMap<String, String>();
  private static boolean hasChanges;
  private static boolean isLoaded;

  private static void load()
  {
    File file = new File(PATH);
    if ( !file.exists() )
      return;
    BufferedReader in = null;
    try
      {
      in = new BufferedReader(new FileReader(file));
      boolean more = true;
      String line;
      while ( more )
        {
        line = in.readLine();
        if ( line != null )
          {
          String [] values = line.split(";");
          if ( values.length == 2 )
            multiVerseIds.put(values[0], values[1]);
          else
            {
            System.out.println("Illegal line = "+line);
            }
          }
        else
          more = false;
        }
      hasChanges = false;
      isLoaded = true;
      }
    catch (IOException e)
      {
      throw  new RuntimeException(e);
      }
    finally
      {
      if ( in != null )
        try
          {
          in.close();
          }
        catch (IOException e)
          {
          e.printStackTrace();
          }
      }

  }

  public static void save()
  {
    if  (!hasChanges )
      return;
    String lineSep = System.getProperty("line.separator");
    PrintWriter exportWriter = null;
    try
      {
      File file = new File(PATH);
      if ( !file.exists() )
         file.createNewFile();
      exportWriter = new PrintWriter(file);
      for (Map.Entry<String, String> entry : multiVerseIds.entrySet())
        {
        exportWriter.write(entry.getKey());
        exportWriter.write(";");
        exportWriter.write(entry.getValue());
        exportWriter.write(lineSep);
        }
      }
    catch (FileNotFoundException e)
      {
      e.printStackTrace();
      }
    catch (IOException e)
      {
      e.printStackTrace();
      }
    finally
      {
      if ( exportWriter != null )
        exportWriter.close();
      }
  }
  private static String makeName( String cardName, String multiVerseId)
  {
   cardName = cardName.replaceAll(" ","");
   cardName = cardName.replaceAll("//","");
    return cardName+"_"+multiVerseId+".jpg";
  }
  public static void storeImage( String cardName, String multiVerseId, byte[] bytes ) throws IOException
  {
    Path p = Paths.get(IMAGESPATH, makeName(cardName, multiVerseId));
//    if ( !p.toFile().exists())
//      p.toFile().createNewFile();
    Files.write(p, bytes, StandardOpenOption.CREATE);

  }

  public static boolean containsImage( String cardName,String multiVerseId )
  {
    File file = new File(IMAGESPATH+makeName(cardName, multiVerseId));
    return file.exists();
  }

  public static byte[] getImage( String cardName, String multiVerseId ) throws IOException
  {
    Path p = Paths.get(IMAGESPATH, makeName(cardName, multiVerseId));
    return Files.readAllBytes(p);
  }

  public static byte[] getImage( String locationArt ) throws IOException
  {
    Path p = Paths.get(IMAGESPATH, locationArt);
    return Files.readAllBytes(p);
  }

  public static boolean containsCard( String cardName )
  {
    if ( !isLoaded )
      load();
    return multiVerseIds.containsKey(cardName);
  }

  public static String getCardId( String cardName )
  {
    return multiVerseIds.get(cardName);
  }

  public static void addCardId( String cardName, String id )
  {
    multiVerseIds.put(cardName, id);
    hasChanges = true;
  }
}

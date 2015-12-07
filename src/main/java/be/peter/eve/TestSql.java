package be.peter.eve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.peter.eve.TestSql">Peter Geuens</a>
 */
public class TestSql
{
  private Connection connection;

  public static void main( String[] args ) throws SQLException
  {
    TestSql test = new TestSql();
  }
  public TestSql() throws SQLException
  {
   doIt();
  }

  public void doIt() throws SQLException
  {
    try
      {
      makeConnection();
      String [] items  = { "Ares", "Ogre II"};
      for (String it : items)
        {
        InvType invType =  findItemByName(it);
        System.out.println("invType = "+invType);
        Collection<SkillLevel> skillLevels = findRequiredSkills(invType);
        for (SkillLevel skillLevel : skillLevels)
          {
          System.out.println("skillLevel = "+skillLevel);
          }

        }
      }
    finally
      {
      closeConnection();
      }
  }

  private Collection<SkillLevel> findRequiredSkills(InvType invType) throws SQLException
  {
    Map<String, Attribute> attributeMap = findAttributesForName(invType.typeName);
    Collection<Attribute> values = attributeMap.values();
    List<SkillLevel> skillLevels  = new ArrayList<SkillLevel>();
    for (Attribute value : values)
      {
//      System.out.println("Attribute = "+value);
      if ( !value.attributeName.contains("Level") )
        {
        InvType item = findItem(value.intValue);
        int level = attributeMap.get(value.attributeName+"Level").intValue;
        skillLevels.add(new SkillLevel(item, level));
        skillLevels.addAll(findRequiredSkills(item));
        }
      }
    return skillLevels;
  }

  private InvType findItemByName( String name ) throws SQLException
  {
    String query = "SELECT * "+
        "  FROM [ebs_DATADUMP].[dbo].[invTypes] as invType \n"+
        "  WHERE invType.typeName = '"+name+"' ";
    ResultSet rs = getData(query);
    if ( rs == null )
      return null;
    if ( !rs.next() )
      return null;
    int typeId = rs.getInt("typeID");
    String typeName = rs.getString("typeName");
    return new InvType(typeId, typeName);
  }

  private InvType findItem( int typeId ) throws SQLException
  {
    String query = "SELECT * "+
        "  FROM [ebs_DATADUMP].[dbo].[invTypes] as invType \n"+
        "  WHERE invType.typeID = '"+typeId+"' ";
    ResultSet rs = getData(query);
    Map<Integer, Attribute> attributeMap = new HashMap<Integer, Attribute>();
    if ( rs == null )
      return null;
    if ( !rs.next() )
      return null;
    String typeName = rs.getString("typeName");
    return new InvType(typeId, typeName);
  }

  private Map<Integer, Attribute> findAttributesForId( int typeId ) throws SQLException
  {
    String query = "SELECT * "+
        "  FROM [ebs_DATADUMP].[dbo].[invTypes] as invType \n"+
        "  JOIN [ebs_DATADUMP].[dbo].[dgmTypeAttributes] as typeAttrib on invType.typeID = typeAttrib.typeID\n"+
        "  JOIN [ebs_DATADUMP].[dbo].[dgmAttributeTypes] as attrib on attrib.attributeID = typeAttrib.attributeID\n"+
        "  WHERE invType.typeID = '"+typeId+"' ";
    ResultSet rs = getData(query);
    Map<Integer, Attribute> attributeMap = new HashMap<Integer, Attribute>();
    if ( rs == null )
      return attributeMap;
    if ( !rs.next() )
      return attributeMap;
    do
      {
      String attributeName = rs.getString("attributeName");
      if ( attributeName.startsWith("requiredSkill") )
        {
        int id = rs.getInt("attributeID");
        attributeMap.put(id, new Attribute(id, attributeName,
                                           rs.getInt("valueInt"), rs.getInt("valueFloat")));

        }

      } while ( rs.next() );
    return attributeMap;
  }

  private Map<String,Attribute> findAttributesForName( String item ) throws SQLException
  {
    String query = "SELECT * "+
        "  FROM [ebs_DATADUMP].[dbo].[invTypes] as invType \n"+
        "  JOIN [ebs_DATADUMP].[dbo].[dgmTypeAttributes] as typeAttrib on invType.typeID = typeAttrib.typeID\n"+
        "  JOIN [ebs_DATADUMP].[dbo].[dgmAttributeTypes] as attrib on attrib.attributeID = typeAttrib.attributeID\n"+
        "  WHERE typeName = '"+item+"' ";
    ResultSet rs = getData(query);
    Map<String, Attribute> attributeMap = new HashMap<String, Attribute>();
    if ( rs == null )
      return attributeMap ;
    if ( !rs.next() )
      return attributeMap;
    do
      {
      String attributeName = rs.getString("attributeName");
      if ( attributeName.startsWith("requiredSkill") )
        {
        int id = rs.getInt("attributeID");
        attributeMap.put(attributeName, new Attribute(id, attributeName,
                                         rs.getInt("valueInt"), rs.getInt("valueFloat")));

        }

      } while ( rs.next() );
    return attributeMap;
  }

  private ResultSet getData( String query )
  {
    try
      {


      try
        {
        return  connection.prepareStatement(query).executeQuery();

        }
      catch (Exception e)
        {
        System.err.println("Cannot connect to database server");
        e.printStackTrace();
        }
      }
    catch (Exception ex)
      {
      ex.printStackTrace();
      }
    return null;
  }


  private void makeConnection()
  {
    try
      {
      String url = "jdbc:jtds:sqlserver://127.0.0.1:1344;instance=SQLEXPRESS;DatabaseName=ebs_DATADUMP";

      try
        {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        connection = DriverManager.getConnection(url, USER, PASS);
        }
      catch (Exception e)
        {
        System.err.println("Cannot connect to database server");
        e.printStackTrace();
        }
      }
    catch (Exception ex)
      {
      ex.printStackTrace();
      }
  }

  private void closeConnection()
  {
    try
      {
      if ( connection != null )
        connection.close();
        }
      catch (Exception e)
        {
        System.err.println("Cannot connect to database server");
        e.printStackTrace();
        }
  }













  private String USER = "sa";
  private String PASS = "Drel_Nar";
}

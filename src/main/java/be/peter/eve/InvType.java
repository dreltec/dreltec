package be.peter.eve;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.peter.eve.Item">Peter Geuens</a>
 */
public class InvType
{
  public InvType( int typeID, String typeName )
  {
    this.typeID = typeID;
    this.typeName = typeName;
  }

  public int typeID;
  public String typeName;

  @Override
  public String toString()
  {
    return "InvType{"+
        "typeID="+typeID+
        ", typeName='"+typeName+'\''+
        '}';
  }
}

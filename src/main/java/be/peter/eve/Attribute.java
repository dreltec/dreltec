package be.peter.eve;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.peter.eve.Attribute">Peter Geuens</a>
 */
public class Attribute
{
  public Attribute( int attributeId, String attributeName, int intValue, float floatValueName )
  {
    this.attributeId = attributeId;
    this.attributeName = attributeName;
    this.intValue = intValue;
    this.floatValueName = floatValueName;
  }

  public int attributeId;
  public String attributeName;
  public int  intValue;
  public float floatValueName;

  @Override
  public String toString()
  {
    return "Attribute{"+
        "attributeId="+attributeId+
        ", attributeName='"+attributeName+'\''+
        ", intValue="+intValue+
        ", floatValueName="+floatValueName+
        '}';
  }
}

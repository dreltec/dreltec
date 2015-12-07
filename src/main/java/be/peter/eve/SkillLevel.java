package be.peter.eve;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.peter.eve.SkillLevel">Peter Geuens</a>
 */
public class SkillLevel
{
  public SkillLevel( InvType invType,  int level )
  {
    this.invType = invType;
    this.level = level;
  }

  public InvType invType;
  public int level;


  @Override
  public String toString()
  {
    return "SkillLevel{"+
        "invType="+invType+
        ", level="+level+
        '}';
  }
}

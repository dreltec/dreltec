package be.dreltec.util;


import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.util.I18NUtil">Peter Geuens</a>
 */
public class I18NUtil
{
  private static ResourceBundle bundle = ResourceBundle.getBundle("be/dreltec/resources/dreltec_resources");

  public I18NUtil()
  {
    bundle = ResourceBundle.getBundle("be/dreltec/resources/dreltec_resources");
  }

  public static void main( String[] args )
  {
    System.out.println("getString(\"demo.text\") = "+getString("demo.text"));
    System.out.println("getString(\"demo.text2\") = "+getString("demo.text2","success"));

  }

  public static String getString( String key, Object... parameters )
  {
    return MessageFormat.format(bundle.getString(key), parameters);
  }
}

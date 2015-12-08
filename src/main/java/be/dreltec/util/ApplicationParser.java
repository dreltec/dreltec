package be.dreltec.util;

import com.beimin.eveapi.parser.ApiAuthorization;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.util.ApplicationParser">Peter Geuens</a>
 */
public class ApplicationParser
{
  public static final String [] EXAMPLES = new String[]
      {
          "4870577 qzuRNBxnCWNLP7yZB2WmO2f9UrjoCOrphky8Ou0Y3Jv9xqdcaj15JRM8xiy0xym9 Hi, I am a newbie looking to get a taste of Faction Warfare. I really want to feel like i''m doing something in EVE rather than just running missions. I am also interested in AMD to learn fleet command, but I feel I should experience FW as just a participant. Thank you.",
          "Key ID: 4870009 Verification Code: yr6383p7pTynGCPa55zCxKgHpFBWw5j9241RfW2Yl2ML8rCORPcvGSk3QyBJz6Rf Access Mask: 1073741823 140m sp, can fly most sub cap excelent, all T3 and T2 and all faction ships",
          "4870577 qzuRNBxnCWNLP7yZB2WmO2f9UrjoCOrphky8Ou0Y3Jv9xqdcaj15JRM8xiy0xym9 Hi, I am a newbie looking to get a taste of Faction Warfare. I really want to feel like i''m doing something in EVE rather than just running missions. I am also interested in AMD to learn fleet command, but I feel I should experience FW as just a participant. Key ID: 4870009 Verification Code: yr6383p7pTynGCPa55zCxKgHpFBWw5j9241RfW2Yl2ML8rCORPcvGSk3QyBJz6Rf Access Mask: 1073741823 140m sp, can fly most sub cap excelent, all T3 and T2 and all faction ships",

      };

  public static void main( String[] args )
  {
    for (String example : EXAMPLES)
      {
      for (ApiAuthorization authorization : getKeys(example))
        {
        System.out.println("authorization = "+authorization);
        }

      }
  }

  public static Set<ApiAuthorization> getKeys(String application)
  {
    Set<ApiAuthorization> keys = new HashSet<ApiAuthorization>();
    int key = -1;
    String vCode = null;
    for (String part : application.split(" "))
      {
      if ( part.length() == 7 )
        {
        try
          {
          key = Integer.parseInt(part);
          }
        catch (NumberFormatException e)
          {
          //key = -1;
          }
        }
      if ( part.length() == 64 )
        vCode = part;
      if ( vCode != null && key != -1 )
        {
        keys.add(new ApiAuthorization(key, vCode));
        key = -1;
        vCode = null;
        }
      }
    return keys;
  }

}

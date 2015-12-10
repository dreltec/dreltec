package be.dreltec.util;

import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.model.account.ApiKeyInfo;
import com.beimin.eveapi.parser.ApiAuthorization;
import com.beimin.eveapi.parser.pilot.CharacterSheetParser;
import com.beimin.eveapi.response.pilot.CharacterSheetResponse;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.util.EveCharacter">Peter Geuens</a>
 */
public class EveCharacter
{
  public ApiKeyInfo apiKeyInfo;
  public ApiAuthorization apiAuthorization;

  public CharacterSheetResponse charSheetResponse;

  private boolean isCharSheetResponseLoaded;
  private Set<Exception> parseExceptions;

  public EveCharacter( ApiKeyInfo apiKeyInfo, ApiAuthorization apiAuthorization )
  {
    this.apiKeyInfo = apiKeyInfo;
    if ( apiAuthorization.getCharacterID() == null )
      throw new IllegalArgumentException(I18NUtil.getString("error.character.id.required"));
    this.apiAuthorization = apiAuthorization;

  }


  public void load()
  {
   loadCharSheetResponse();
  }

  private void loadCharSheetResponse()
  {
    try
      {
      CharacterSheetParser parser = new CharacterSheetParser();
      charSheetResponse = parser.getResponse(apiAuthorization);
      isCharSheetResponseLoaded = true;
      }
    catch (ApiException e)
      {
      if ( parseExceptions == null )
        parseExceptions = new HashSet<Exception>();
      parseExceptions.add(e);
      }
  }
}

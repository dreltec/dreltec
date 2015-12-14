package be.dreltec.util;

import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.parser.eve.CharacterInfoParser;
import com.beimin.eveapi.response.eve.CharacterInfoResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author drelnar
 */
public class PublicCharacterUtil
{
  private Map<Long, CharacterInfoResponse> characterCache= new HashMap<Long, CharacterInfoResponse>();

  public static CharacterInfoResponse loadCharacter(long characterId) throws ApiException
  {
    CharacterInfoParser parser = new CharacterInfoParser();
    return parser.getResponse(characterId);

  }

  public CharacterInfoResponse getCharInfo(long characterId) throws ApiException
  {
    if ( ! characterCache.containsKey(characterId) )
      characterCache.put(characterId, loadCharacter(characterId) );
    return characterCache.get(characterId);
  }
}

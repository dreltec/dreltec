package be.dreltec.apichecker;

import be.dreltec.util.EveCharacter;
import com.beimin.eveapi.model.account.ApiKeyInfo;
import com.beimin.eveapi.parser.ApiAuthorization;

import java.util.concurrent.Callable;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.apichecker.CharacterLoader">Peter Geuens</a>
 */
public class CharacterLoader implements Callable<EveCharacter>
{
  private ApiKeyInfo apiKeyInfo;
  private ApiAuthorization charApi;

  public CharacterLoader( ApiKeyInfo apiKeyInfo, ApiAuthorization charApi )
  {
    this.apiKeyInfo = apiKeyInfo;
    this.charApi = charApi;
  }

  public EveCharacter call() throws Exception
  {
    EveCharacter character = new EveCharacter(apiKeyInfo, charApi);
    character.load();
    return character;
  }


}

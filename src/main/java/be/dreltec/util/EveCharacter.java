package be.dreltec.util;

import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.model.account.ApiKeyInfo;
import com.beimin.eveapi.parser.ApiAuthorization;
import com.beimin.eveapi.parser.eve.CharacterInfoParser;
import com.beimin.eveapi.parser.pilot.CharacterSheetParser;
import com.beimin.eveapi.parser.pilot.KillLogParser;
import com.beimin.eveapi.parser.pilot.MailMessagesParser;
import com.beimin.eveapi.parser.pilot.StandingsParser;
import com.beimin.eveapi.response.eve.CharacterInfoResponse;
import com.beimin.eveapi.response.pilot.CharacterSheetResponse;
import com.beimin.eveapi.response.pilot.MailMessagesResponse;
import com.beimin.eveapi.response.shared.KillLogResponse;
import com.beimin.eveapi.response.shared.StandingsResponse;

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
  public MailMessagesResponse   mailMessagesResponse;
  public KillLogResponse        killLogResponse;
  public StandingsResponse      standingsResponse;
  public CharacterInfoResponse  charInfoResponse;

  private boolean isCharSheetResponseLoaded;
  private boolean isMailResponseLoaded;
  private boolean isKillLogResponseLoaded;
  private boolean isStandingsResponseLoaded;
  private boolean isCharInfoResponseLoaded;

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
    loadMails();
    loadKillLogs();
    loadCharInfo();
    loadStandings();
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

  private void loadMails()
  {
    try
      {
      MailMessagesParser messagesParser = new MailMessagesParser();
      mailMessagesResponse = messagesParser.getResponse(apiAuthorization);
      isMailResponseLoaded = true;
      }
    catch (ApiException e)
      {
      if ( parseExceptions == null )
        parseExceptions = new HashSet<Exception>();
      parseExceptions.add(e);
      }
  }

  private void loadKillLogs()
  {
    try
      {
      KillLogParser parser = new KillLogParser();
      killLogResponse = parser.getResponse(apiAuthorization);
      isKillLogResponseLoaded = true;
      }
    catch (ApiException e)
      {
      if ( parseExceptions == null )
        parseExceptions = new HashSet<Exception>();
      parseExceptions.add(e);
      }
  }

  private void loadStandings()
  {
    try
      {
      StandingsParser standingsParser = new StandingsParser();
      standingsResponse = standingsParser.getResponse(apiAuthorization);
      isStandingsResponseLoaded = true;
      }
    catch (ApiException e)
      {
      if ( parseExceptions == null )
        parseExceptions = new HashSet<Exception>();
      parseExceptions.add(e);
      }
  }

  private void loadCharInfo()
  {
    try
      {
      CharacterInfoParser infoParser = new CharacterInfoParser();
      charInfoResponse = infoParser.getResponse(apiAuthorization);
      isCharInfoResponseLoaded = true;
      }
    catch (ApiException e)
      {
      if ( parseExceptions == null )
        parseExceptions = new HashSet<Exception>();
      parseExceptions.add(e);
      }
  }

  @Override
  public String toString()
  {
    return charSheetResponse.getName();
  }
}

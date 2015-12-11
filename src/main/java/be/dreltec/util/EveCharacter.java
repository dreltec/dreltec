package be.dreltec.util;

import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.model.account.ApiKeyInfo;
import com.beimin.eveapi.model.pilot.Notification;
import com.beimin.eveapi.model.pilot.NotificationText;
import com.beimin.eveapi.parser.ApiAuthorization;
import com.beimin.eveapi.parser.eve.CharacterInfoParser;
import com.beimin.eveapi.parser.pilot.*;
import com.beimin.eveapi.response.eve.CharacterInfoResponse;
import com.beimin.eveapi.response.pilot.CharacterSheetResponse;
import com.beimin.eveapi.response.pilot.MailMessagesResponse;
import com.beimin.eveapi.response.pilot.NotificationTextsResponse;
import com.beimin.eveapi.response.pilot.NotificationsResponse;
import com.beimin.eveapi.response.shared.ContractsResponse;
import com.beimin.eveapi.response.shared.KillLogResponse;
import com.beimin.eveapi.response.shared.StandingsResponse;
import com.beimin.eveapi.response.shared.WalletJournalResponse;
import com.beimin.eveapi.response.shared.WalletTransactionsResponse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.util.EveCharacter">Peter Geuens</a>
 */
public class EveCharacter
{
  public ApiKeyInfo apiKeyInfo;
  public ApiAuthorization apiAuthorization;

  public CharacterSheetResponse charSheetResponse;
  public CharacterInfoResponse  charInfoResponse;
  public MailMessagesResponse   mailMessagesResponse;
  public Set<Notification>      notifications;
  public WalletJournalResponse  walletJournalResponse;
  public WalletTransactionsResponse walletTransactionsResponse;
  public KillLogResponse        killLogResponse;
  public ContractsResponse      contractsResponse;

  public StandingsResponse      standingsResponse;
  private boolean isCharSheetResponseLoaded;
  private boolean isCharInfoResponseLoaded;
  private boolean isMailResponseLoaded;
  private boolean isNotificationsResponseLoaded;
  private boolean isWalletJournalResponseLoaded;
  private boolean isWalletTransactionsResponseLoaded;
  private boolean isKillLogResponseLoaded;
  private boolean isContractsResponseLoaded;

  private boolean isStandingsResponseLoaded;
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
    loadNotifications();
    loadWalletJournal();
    loadWalletJournal();
    loadWalletTransactions();
    loadKillLogs();
    loadCharInfo();
    loadContracts();
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

  private void loadWalletJournal()
  {
    try
      {
      WalletJournalParser parser = new WalletJournalParser();
      walletJournalResponse = parser.getResponse(apiAuthorization);
      isWalletJournalResponseLoaded = true;
      }
    catch (ApiException e)
      {
      if ( parseExceptions == null )
        parseExceptions = new HashSet<Exception>();
      parseExceptions.add(e);
      }
  }

  private void loadWalletTransactions()
  {
    try
      {
      WalletTransactionsParser parser = new WalletTransactionsParser();
      walletTransactionsResponse = parser.getResponse(apiAuthorization);
      isWalletJournalResponseLoaded = true;
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

  private void loadContracts()
  {
    try
      {
      ContractsParser parser = new ContractsParser();
      contractsResponse = parser.getResponse(apiAuthorization);
      isContractsResponseLoaded = true;
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

  private void loadNotifications()
  {
    try
      {
      NotificationsParser  notificationsParser = new NotificationsParser();
      NotificationTextsParser  notificationsTextParser = new NotificationTextsParser();
      NotificationsResponse notificationsResponse = notificationsParser.getResponse(apiAuthorization);
      NotificationTextsResponse notificationTextsResponse = notificationsTextParser.getResponse(apiAuthorization);
      notifications = notificationsResponse.getAll();
      Set<NotificationText> notificationTexts = notificationTextsResponse.getAll();

      //todo documentation says its need a list of id's to be passed along so might not work as intended
      Map<Long, NotificationText> texts =  new HashMap<Long,NotificationText>();
      for (NotificationText text : notificationTexts)
        {
        texts.put(text.getNotificationID(), text);
        }
//      for (Notification notification : notifications)
//        {
//        notification.NotificationType
//        }
      isNotificationsResponseLoaded = true;
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

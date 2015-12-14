package be.dreltec.util;

import com.beimin.eveapi.model.pilot.Notification;
import com.beimin.eveapi.response.eve.CharacterInfoResponse;

/**
 * @author drelnar
 */
public class EveNotification
{
   public Notification.NotificationType type;

   public String text;
  private CharacterInfoResponse sender;

  public void setSender( CharacterInfoResponse sender )
  {
    this.sender = sender;
  }
}

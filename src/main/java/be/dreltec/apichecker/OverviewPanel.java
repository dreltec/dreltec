package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;
import be.dreltec.util.EveCharacter;

import javax.swing.*;

/**
 * @author drelnar
 */
public class OverviewPanel extends GridPanel implements CharacterDetailpanel
{
  private JLabel detailLabel =new JLabel();

  public OverviewPanel()
  {
  createComponents();
  layoutComponents();
  }

  public void createComponents()
  {

  }

  public void layoutComponents(  )
  {
    startGrid();
    addRow(detailLabel);
    addFillerRow();
  }

  public void load( EveCharacter character)
  {
    detailLabel.setText(character.charInfoResponse.getCharacterName());
  }

}

package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;
import be.dreltec.util.EveCharacter;
import com.beimin.eveapi.model.account.*;
import com.beimin.eveapi.model.shared.Standing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * @author drelnar
 */
public class StandingsPanel extends GridPanel implements CharacterDetailpanel
{
  private JLabel detailLabel =new JLabel();

  private JTable apiTable;

  public StandingsPanel()
  {
  createComponents();
  layoutComponents();
  }

  public void createComponents()
  {
    Vector<String> columnNames = new Vector<String>();
    columnNames.addAll(Arrays.asList("Group", "Standing"));
    DefaultTableModel model = new DefaultTableModel(new Vector(), columnNames){
      @Override
      public Class<?> getColumnClass( int columnIndex )
      {
        if ( columnIndex == 0 )
          return String.class;
        else
          return Double.class;
      }
    };
    apiTable = new JTable(model);
    apiTable.setAutoCreateRowSorter(true);
    List<RowSorter.SortKey> list = new ArrayList<RowSorter.SortKey>();
    list.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
    apiTable.getRowSorter().setSortKeys(list);


  }

  public void layoutComponents(  )
  {
    startGrid();
    addRow(detailLabel);
    addScrollPane(apiTable);
    addFillerRow();
  }

  public void load( EveCharacter character)
  {
    while( ((DefaultTableModel) apiTable.getModel() ).getRowCount()> 0 )
      {
      ( (DefaultTableModel) apiTable.getModel() ).removeRow(0);
      }
    if ( character.isStandingsResponseLoaded )
      {

      for (Standing standing : character.standingsResponse.getFactionStandings())
        {
        Vector data = new Vector<String>();
        data.add(standing.getFromName());
        data.add(standing.getStanding() );
        ( (DefaultTableModel) apiTable.getModel() ).addRow(data);
        }

      }
  }

}

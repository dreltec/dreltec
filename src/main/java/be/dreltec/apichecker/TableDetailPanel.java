package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;
import be.dreltec.util.EveCharacter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author drelnar
 */
public abstract class TableDetailPanel extends GridPanel implements CharacterDetailpanel
{
  private JLabel detailLabel =new JLabel();

  private JTable apiTable;

  public TableDetailPanel()
  {
  createComponents();
  layoutComponents();
  }

  public abstract List<String> getColumnNames();
  public abstract Class getClassForColumn(int columnIndex);

  public abstract List<Vector> getData( EveCharacter character );

  public abstract int getSortedColumn();

  public SortOrder getSortOrder()
  {
    return SortOrder.DESCENDING;
  }

  public void createComponents()
  {
    Vector<String> columnNames = new Vector<String>();
    columnNames.addAll(getColumnNames());

    DefaultTableModel model = new DefaultTableModel(new Vector(), columnNames){
      @Override
      public Class<?> getColumnClass( int columnIndex )
      {
        return getClassForColumn(columnIndex);
      }
    };
    apiTable = new JTable(model);
    apiTable.setAutoCreateRowSorter(true);
    List<RowSorter.SortKey> list = new ArrayList<RowSorter.SortKey>();
    list.add(new RowSorter.SortKey(getSortedColumn(), getSortOrder() ));
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
    List<Vector> data  = getData(character);
    for (Vector vector : data)
      {
      ( (DefaultTableModel) apiTable.getModel() ).addRow(vector);
      }
  }

}

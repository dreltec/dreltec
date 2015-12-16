package be.dreltec.apichecker;

import be.dreltec.util.EveCharacter;
import com.beimin.eveapi.model.shared.Standing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * @author drelnar
 */
public class StandingsPanel extends TableDetailPanel
{
  @Override
  public List<String> getColumnNames()
  {
    return Arrays.asList("Group", "Standing");
  }

  @Override
  public Class getClassForColumn( int columnIndex )
  {
    if ( columnIndex == 0 )
      return String.class;
    else
      return Double.class;
  }

  @Override
  public List<Vector> getData( EveCharacter character )
  {
    List<Vector> list = new ArrayList<Vector>();
    if ( character.isStandingsResponseLoaded )
      {

      for (Standing standing : character.standingsResponse.getFactionStandings())
        {
        Vector data = new Vector<String>();
        data.add(standing.getFromName());
        data.add(standing.getStanding());
        list.add(data);
        }

      }
    return list;
  }

  @Override
  public int getSortedColumn()
  {
    return 1;
  }

}

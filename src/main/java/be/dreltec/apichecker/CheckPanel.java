package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;
import be.dreltec.util.EveCharacter;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.model.account.*;
import com.beimin.eveapi.model.account.Character;
import com.beimin.eveapi.parser.ApiAuthorization;
import com.beimin.eveapi.parser.account.ApiKeyInfoParser;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.apichecker.Main">Peter Geuens</a>
 */

public class CheckPanel extends GridPanel
{

  private Set<CharacterDetailpanel> detailPanels = new HashSet<CharacterDetailpanel>();
  private OverviewPanel overviewPanel = new OverviewPanel();
  private StandingsPanel standingsPanel = new StandingsPanel();

  public CheckPanel(Set<ApiAuthorization> apiAuthorisations)
  {

    createComponents();
    layoutComponents(apiAuthorisations);
  }



  private static ApiKeyInfo getApiKey( ApiAuthorization apiAuthorization )
  {
    ApiKeyInfoParser parser = new ApiKeyInfoParser();
    try
      {
      return parser.getResponse(apiAuthorization).getApiKeyInfo();
      }
    catch (ApiException e)
      {
      e.printStackTrace();
      }
    return null;
  }

  public void createComponents()
  {
    detailPanels.add(overviewPanel);
    detailPanels.add(standingsPanel);
  }

  public void layoutComponents( Set<ApiAuthorization> apiAuthorisations)
  {
    DefaultMutableTreeNode top =
        new DefaultMutableTreeNode("header");
    createNodes(apiAuthorisations, top);
    JTree jtree = new JTree(top);
    jtree.setCellRenderer(new DefaultTreeCellRenderer()
    {
    });
    jtree.addTreeSelectionListener(new TreeSelectionListener()
    {
      public void valueChanged( TreeSelectionEvent e )
      {
        if ( ( (DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent() ).getUserObject() instanceof EveCharacter )
          {
          for (CharacterDetailpanel panel : detailPanels)
            {
            panel.load((EveCharacter) ( (DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent() ).getUserObject());
            }
          }
      }
    });

    setLayout(new BorderLayout());
    add(jtree, BorderLayout.WEST);
    GridPanel panel = new GridPanel();
    panel.startGrid();
//    panel.addRow(getLabel("header component"));
//    panel.setWeightsX(1.0);
//    panel.setWeightY(1.0);
    panel.setFills(GridBagConstraints.BOTH);
    panel.setWeightsX(1.0);
    panel.setWeightY(1.0);
    setFills(GridBagConstraints.BOTH);
    panel.addRow(panel.makeTabbedPane());
    panel.addTab("Overview", overviewPanel );
    panel.addTab("Standings", standingsPanel );
    panel.stopTabbedPane();
    panel.addRow(getLabel("footer component"));
    add(panel, BorderLayout.CENTER);
  }

  private void createNodes( Set<ApiAuthorization> apiAuthorisations, DefaultMutableTreeNode top )
  {
    Map<String, Set<EveCharacter>> chars =  loadCharacters(apiAuthorisations);

    for (Map.Entry<String, Set<EveCharacter>> stringSetEntry : chars.entrySet())
      {
      DefaultMutableTreeNode account = new DefaultMutableTreeNode(stringSetEntry.getKey());
      top.add(account);
      for (EveCharacter eveCharacter : stringSetEntry.getValue())
        {
        DefaultMutableTreeNode character = new DefaultMutableTreeNode(eveCharacter);
        account.add(character);
        }
      }


  }


  public static Map<String, Set<EveCharacter>> loadCharacters( Set<ApiAuthorization> apiAuthorisations )
  {
    final ExecutorService executor = Executors.newFixedThreadPool(6);
    final CompletionService<EveCharacter> cservice = new ExecutorCompletionService<EveCharacter>(executor);
    for (ApiAuthorization authorisation : apiAuthorisations)
      {
      ApiKeyInfo info = getApiKey(authorisation);
      for (Character character : info.getEveCharacters())
        {
        ApiAuthorization charKey = new ApiAuthorization(authorisation.getKeyID(), character.getCharacterID(), authorisation.getVCode());
        cservice.submit(new CharacterLoader(info, charKey));
        }
      }
    executor.shutdown();
    try
      {
      for (int cnt = 0; cnt < 3 && !executor.awaitTermination(10, TimeUnit.SECONDS); cnt++)
        {
        System.err.println("[WARN ]: VoRetriever: still waiting for Executor");
        }
      Map<String, Set<EveCharacter>> map = new HashMap<String, Set<EveCharacter>>();

      for (Future<EveCharacter> future; ( future = cservice.poll() ) != null; )
        {
        EveCharacter character = future.get();
        if ( !map.containsKey(""+character.apiAuthorization.getKeyID()) )
          map.put(""+character.apiAuthorization.getKeyID(), new HashSet<EveCharacter>());
        map.get(""+character.apiAuthorization.getKeyID()).add(character);
        }
      return map;
      }
    catch (InterruptedException e)
      {
      e.printStackTrace();
      }
    catch (ExecutionException e)
      {
      e.printStackTrace();
      }

    return new HashMap<String, Set<EveCharacter>>();
  }

}
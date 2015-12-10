package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;
import be.dreltec.gui.SmallWebButton;
import be.dreltec.util.ApplicationParser;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.model.account.ApiKeyInfo;
import com.beimin.eveapi.model.account.Character;
import com.beimin.eveapi.parser.ApiAuthorization;
import com.beimin.eveapi.parser.account.ApiKeyInfoParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.apichecker.Main">Peter Geuens</a>
 */

public class Step1Panel extends GridPanel
{
  private Main main;

  private JTextField applicationField;
  private JTextField apiKeyField;
  private JTextField vCodeField;

  private JTable apiTable;
  private Set<ApiAuthorization> apiKeys= new HashSet<ApiAuthorization>();

  public Step1Panel( Main main)
  {
    this.main = main;
    createComponents();
    layoutComponents();
  }

  public void createComponents()
  {
    applicationField = getTextField(50);
    applicationField.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        parseApplication();
      }
    });

    apiKeyField = getTextField(50);
    apiKeyField.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        checkKey();
      }
    });
    vCodeField = getTextField(50);
    vCodeField.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        checkKey();
      }
    });

    Vector<String> columnNames= new Vector<String>();
    columnNames.addAll(Arrays.asList("key", "vCode", "mask", "type", "names"));
    apiTable= new JTable(new Vector(), columnNames);

  }

  public void layoutComponents()
  {
    startGrid();
    addRow(getLabel("Auto:"), applicationField, new SmallWebButton("\\/", getAL(applicationField)));
    addRow(getLabel("Api:"), apiKeyField, new SmallWebButton("?", getAL(apiKeyField)));
    addRow(getLabel("vCode:"), vCodeField, new SmallWebButton("?", getAL(vCodeField)));
    setFills(GridBagConstraints.EAST);
    SmallWebButton button = new SmallWebButton("Start Api Check");
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        main.startParsing(apiKeys );
      }
    });
    addRow(button, getDummy(), getDummy());
//    addFillerRow();
//    addFillerCol();
    addScrollPane(apiTable);
//    JScrollPane scrollPane = new JScrollPane(apiTable);
    apiTable.setFillsViewportHeight(true);

  }

  private void parseApplication()
  {
    Set<ApiAuthorization> apiAuthorizations = ApplicationParser.getKeys(applicationField.getText());
    for (ApiAuthorization api : apiAuthorizations)
      {
      checkApiAuthorization(api);
      }

  }

  private void checkKey()
  {
    try
      {
      int key = Integer.parseInt(apiKeyField.getText() );
      if ( vCodeField.getText()!=null && vCodeField.getText().length() > 0 )
        {
        checkApiAuthorization(new ApiAuthorization(key,vCodeField.getText()));
        return;
        }
      }
    catch (NumberFormatException e)
      {
      e.printStackTrace();
      }
    System.out.println("Nothing to check");

  }

  private void checkApiAuthorization( ApiAuthorization apiAuthorization )
  {
    Vector<String> data = new Vector<String>();
    data.add(Integer.toString(apiAuthorization.getKeyID()));
    data.add(apiAuthorization.getVCode());

    ApiKeyInfo info = getApiKey(apiAuthorization);
    if ( info != null )
      {
      data.add(Long.toString(info.getAccessMask()));
      data.add(info.getType().toString());
      String characters = "";
      for (Character character : info.getEveCharacters())
        {
        if ( characters.length() > 0 )
          characters += ",";
        characters += character.getName();
        }
      data.add(characters);
      }
    else
      {
      data.add("error");
      data.add("error");
      data.add("error");
      }

    ( (DefaultTableModel) apiTable.getModel() ).addRow(data);
    apiKeys.add(apiAuthorization);

  }

  private ApiKeyInfo getApiKey( ApiAuthorization apiAuthorization)
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


  private ActionListener getAL( final JTextField field )
  {
    return new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        field.postActionEvent();
      }
    };
  }

}
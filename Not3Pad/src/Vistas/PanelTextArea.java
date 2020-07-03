/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.BorderLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author davidf
 */
public class PanelTextArea extends JPanel {

    public JTextArea textArea;
    public JScrollPane scrollPane;
    public static JPopupMenu popMenu;
    public JMenuItem pop_Boton1;
    public JMenuItem pop_Boton2;
    public JMenuItem pop_Boton3;

    public PanelTextArea() {

        //Se utilizará BORDERLAYOUT, para situar el TextArea en este panel
        this.setLayout(new BorderLayout());

        //Instanciamos el TextArea y lo agregamos al ScrollPane
        this.textArea = new JTextArea();
        this.scrollPane = new JScrollPane(textArea);
        //Instanciamos el Popup y los menus
        this.popMenu = new JPopupMenu();
        this.pop_Boton1 = new JMenuItem("BOTON1");
        this.pop_Boton2 = new JMenuItem("BOTON2");
        this.pop_Boton3 = new JMenuItem("BOTON3");

        //Agregamos TextArea a la capa.
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(scrollPane);
        //Menú POP-UP AGREGANDO Botones al PopupMenu
        this.popMenu.add(pop_Boton1);
        popMenu.add(pop_Boton2);
        popMenu.addSeparator();
        popMenu.add(pop_Boton3);

        //setBounds(0, 20,3000,480);
    }

}

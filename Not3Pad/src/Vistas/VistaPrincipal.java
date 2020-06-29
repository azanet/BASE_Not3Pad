/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author davidf
 */
public class VistaPrincipal extends JFrame {

    //Establecemos un objeto de "dimension", que le pasaremos al metodo
    //"setMinimunSize() para establecer el tamaño mínimo que podrá tener nuestra aplicación
    public static JPanel panelBase;

//Creando constructor
    public VistaPrincipal() {

       // super("Not3Pad");
       //Seteamos TITULO a la ventana principal 
       setTitle("Not3Pad");
        panelBase = new JPanel();


    }

}//Fin de la vista principal


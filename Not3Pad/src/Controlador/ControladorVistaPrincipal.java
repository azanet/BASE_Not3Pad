/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MetodosPrincipal;
import Vistas.PanelPrincipal;
import Vistas.PanelTextArea;


import Vistas.VistaPrincipal;
import static Vistas.VistaPrincipal.panelBase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;

import javax.swing.JOptionPane;


/**
 *
 * @author davidf
 */
public class ControladorVistaPrincipal {

      
    
    public final VistaPrincipal vistaPrincipal;
    private final MetodosPrincipal metodosPrincipal;
    public final PanelPrincipal panelPrincipal;
    public final PanelTextArea panelTextArea;

          
    Color color;
    Font fuente;
    int tamanio_fuente = 15;
    String nombre_fuente = "Liberation Mono";
    
    String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal, MetodosPrincipal metodosPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.metodosPrincipal = metodosPrincipal;
        this.panelPrincipal= new PanelPrincipal();
        this.panelTextArea= new PanelTextArea();

         //Agregamos a vista principal su propio panelBase
         vistaPrincipal.setContentPane(vistaPrincipal.panelBase);
         
        //Confiduramos el layout del panelPrincipal a Border, y agregamos los paneles
         vistaPrincipal.panelBase.setLayout(new BorderLayout());
         
         //Seteamos el tamaño del panel que contiene el MENU (ya que si no no se muestra correctamente el componente)
         //, dandole de ancho el mismo que tenga de X el panelBase 
         panelPrincipal.setPreferredSize(new Dimension(panelBase.getX(),20));
         //Agregamos el panel que contiene el MENU
       vistaPrincipal.panelBase.add(panelPrincipal, BorderLayout.PAGE_START);
       //Agregamos el panel que contiene el TextArea
        vistaPrincipal.panelBase.add(panelTextArea, BorderLayout.CENTER);
      
        vistaPrincipal.pack();

        //Ejecutamos el método iniciar que iniciará todos los componentes
        Iniciar();
    }

    private void Iniciar() {

     
        panelPrincipal.nuevo.addActionListener(new OyenteNuevo());
        panelPrincipal.abrir.addActionListener(new OyenteAbrir());
        panelPrincipal.guardar.addActionListener(new OyenteGuardar());
        panelPrincipal.guardarComo.addActionListener(new OyenteGuardarComo());
        panelPrincipal.renombrar.addActionListener(new OyenteRenombrar());
        panelPrincipal.ir_A.addActionListener(new OyenteIrA());
        panelPrincipal.deshacer.addActionListener(new OyenteDeshacer());
        panelPrincipal.rehacer.addActionListener(new OyenteRehacer());
        panelPrincipal.copiar.addActionListener(new OyenteCopiar());
        panelPrincipal.pegar.addActionListener(new OyentePegar());
        panelPrincipal.cortar.addActionListener(new OyenteCortar());
        panelPrincipal.insertarFecha.addActionListener(new OyenteInsertarFecha());
        panelPrincipal.buscar.addActionListener(new OyenteBuscar());
        panelPrincipal.buscarYreemplazar.addActionListener(new OyenteBuscarYReemplazar());
        panelPrincipal.imprimir_configurando.addActionListener(new OyenteImprimirConfigurando());
        panelPrincipal.imprimir_directo.addActionListener(new OyenteImprimirDirecto());
        panelPrincipal.acercaDe.addActionListener(new OyenteAcercaDe());
        panelPrincipal.salir.addActionListener(new OyenteSalir());
        panelPrincipal.colorBackground.addActionListener(new OyenteColorBackground());
        panelPrincipal.colorFuente.addActionListener(new OyenteColorFuente());
        panelPrincipal.colorSeleccion.addActionListener(new OyenteColorSeleccion());
        panelPrincipal.colorTextoSeleccionado.addActionListener(new OyenteColorTextoSeleccionado());

        //JPOPUPMENU Agregamos los botones el JPopupMenu y les pasamos su correspondiente Listener
        panelTextArea.pop_Boton1.addActionListener(new OyentePopBoton1());
        panelTextArea.pop_Boton2.addActionListener(new OyentePopBoton2());
        panelTextArea.pop_Boton3.addActionListener(new OyentePopBoton3());

        // Se añade el JPopupMenu al J-ITEM donde queremos que se muestre 
        //Debemos implementar todos los metodos de MouseListener porque
        // no sabemos a priori que evento usara el sistema operativo para
        // mostrar los menus.
        panelTextArea.textArea.addMouseListener(new OyenteRatonPopupMenu());

        //Añadiendo y cargando ComboBox de TAMAÑO LETRA
        panelPrincipal.comboBox.addItem(tamanio_fuente); //Agregaremos este primer elemento con el valor inicial que tendrá
        for (int i = 0; i < 100; i++) {
            panelPrincipal.comboBox.addItem(i);

        }
        // Accion a realizar cuando el JComboBox cambia de item seleccionado.
        panelPrincipal.comboBox.addActionListener(new OyenteComboTamanio());

        ///AÑADIENDO COMBOBOX PARA ESTILO DE LETRA
        
        //Mostrar un listado con las fuentes DISPONIBLES
        panelPrincipal.comboBoxStyle.addItem("Liberation Mono"); //Agregaremos este primer elemento con el valor inicial que tendrá
        //Recorremos el array de FontNames para rellenar el comboBox con todos los estilos de letra disponibles 
        for (String fontName : fontNames) {
            panelPrincipal.comboBoxStyle.addItem(fontName);
        }
   
        panelPrincipal.comboBoxStyle.addActionListener(new OyenteComboStyle());
    
        
        
        
        ///PREPARAMOS el ESTILO del Texto (abrá que pasarle tambien el tamaño ya que el método lo requiere)
        fuente = new Font(nombre_fuente, Font.PLAIN, tamanio_fuente);
        //Le pasamos al 'textarea' la fuente(tamaño y estilo) creada anteriormente
        panelTextArea.textArea.setFont(fuente);
        
        vistaPrincipal.setBounds(0,0, 600, 500);
        
        //Con este método haremos que la pantalla salga JUSTO EN EL CENTRO
        vistaPrincipal.setLocationRelativeTo(null);
        //Hacemos visible la vista Principal      
        vistaPrincipal.setVisible(true);
    }//Fin de iniciar

    
    
    ///////////////////////////////////////////////////////////////////////
    ///////// OYENTE DEL RATÓN para POPUP //////////////////////////////
    
    class OyenteRatonPopupMenu implements MouseListener {
    @Override
            public void mouseReleased(MouseEvent e) {
                muestraMenu(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                muestraMenu(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                muestraMenu(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                muestraMenu(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                muestraMenu(e);
            }

            
                /**
             * Método que muestra el menú.
             *
             * @param e
             */
            private void muestraMenu(MouseEvent e) {
                // isPopupTrigger() indica si es el evento de raton
                // por defecto en el sistema operativo para mostrar
                // el menu.
                if (e.isPopupTrigger()) {
                    //Se mostraŕá el JPopupMenu con el metodo "show" y solicitando los parámetros X e Y
                    panelTextArea.popMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
    
    
    }////Fin del OyenteRatonpoup
    
    
    
    
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////   
    class OyenteComboTamanio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            //Almacenamos los datos del combobox en la variable instanciada, para utilizarla cuando sea necesario
            tamanio_fuente = Integer.parseInt(panelPrincipal.comboBox.getSelectedItem().toString());

            fuente = new Font(nombre_fuente, Font.PLAIN, tamanio_fuente);
            panelTextArea.textArea.setFont(fuente); // sólo va a cambiar el tamaño a 12 puntos
            
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteComboStyle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            //Almacenamos los datos del combobox en la variable instanciada, para utilizarla cuando sea necesario
            nombre_fuente = panelPrincipal.comboBoxStyle.getSelectedItem().toString();

            fuente = new Font(nombre_fuente, Font.PLAIN, tamanio_fuente);
            panelTextArea.textArea.setFont(fuente); // sólo va a cambiar el tamaño a 12 puntos

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteNuevo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            JOptionPane.showMessageDialog(panelTextArea.textArea, "que diseeeee", "dialogo TEST", JOptionPane.INFORMATION_MESSAGE);
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteAbrir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
   panelPrincipal.edicion.setVisible(false);
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
panelPrincipal.edicion.setVisible(true);
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteGuardarComo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteRenombrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteIrA implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteDeshacer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteRehacer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteCopiar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyentePegar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteCortar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteInsertarFecha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteBuscar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteBuscarYReemplazar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteImprimirConfigurando implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteImprimirDirecto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteAcercaDe implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteSalir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorBackground implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            color = JColorChooser.showDialog(panelTextArea.textArea, "Elige un color", Color.WHITE);
            panelTextArea.textArea.setBackground(color);
            panelTextArea.textArea.setOpaque(true);

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorFuente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            //CAMBIAR EL COLOR DE LA "LETRA"
            color = JColorChooser.showDialog(panelTextArea.textArea, "Elige un color", Color.BLACK);
            panelTextArea.textArea.setForeground(color);
            //Color del "caret" (puntero)
            panelTextArea.textArea.setCaretColor(color);

        }//Fin action performed
    }//Fin del OyenteCOPIAR

    ////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorSeleccion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            //CAMBIAR EL COLOR DE LA "LETRA"
            color = JColorChooser.showDialog(panelTextArea.textArea, "Elige un color", Color.BLACK);
            //Color de SLECCION
            panelTextArea.textArea.setSelectionColor(color);

        }//Fin action performed
    }//Fin del OyenteCOPIAR

    ////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorTextoSeleccionado implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            //CAMBIAR EL COLOR DE LA "LETRA"
            color = JColorChooser.showDialog(panelTextArea.textArea, "Elige un color", Color.BLACK);
            //Color de TEXTO Seleccionado
            panelTextArea.textArea.setSelectedTextColor(color);

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////    
///////////////////  POP-UP MENU  //////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////   
    class OyentePopBoton1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyentePopBoton2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyentePopBoton3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

}

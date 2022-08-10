package reto5.view;

import reto5.controller.ReportesController;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import reto5.model.vo.*;

public class ReportesView extends JFrame implements ActionListener {

    private static ReportesController controller;
        private JMenuBar menuBar;
        private JMenu menu;
        private JMenuItem primerInf, segundoInf, tercerInf;
        private JTable tabla;
        private DefaultTableModel modelo;
        private JLabel lblTitulo, lblConsulta;


        public ReportesView(){
            controller = new ReportesController();
            menu();
            etiqueta1();
            etiqueta2();
            tabla();
        }

        public void tabla(){
            tabla = new JTable(modelo);
            tabla.setPreferredScrollableViewportSize(new Dimension(500,200));
            add(tabla);
            JScrollPane pane = new JScrollPane(tabla);
            add(pane);   
        }

        public void etiqueta1(){
            lblTitulo = new JLabel("El Informe Consultado es:");
            lblTitulo.setPreferredSize(new Dimension(500, 30));
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
            add(lblTitulo);
        }
        public void etiqueta2(){
            lblConsulta = new JLabel();
            lblConsulta.setPreferredSize(new Dimension(500, 30)); 
            lblConsulta.setFont(new Font("Arial", Font.BOLD, 14));
            add(lblConsulta);
        }

        public void menu(){
            menuBar = new JMenuBar();   
            setJMenuBar(menuBar);
            menu = new JMenu("Informes");
            menuBar.add(menu);
            primerInf = new JMenuItem("Primer informe");
            segundoInf = new JMenuItem("Segundo informe");
            tercerInf = new JMenuItem("Tercer informe");
            menu.add(primerInf);
            menu.add(segundoInf);
            menu.add(tercerInf);
            primerInf.addActionListener(this);
            segundoInf.addActionListener(this);
            tercerInf.addActionListener(this);
        }

        public void primerInforme() {
            try{
                List<ListaLideresVo> lideres = controller.listarLideres();
                //Creeación del modelo:
                modelo = new DefaultTableModel();
                modelo.addColumn("Id Lider");
                modelo.addColumn("Nombre");
                modelo.addColumn("Apellido");
                modelo.addColumn("Ciudad");
                for(ListaLideresVo lider: lideres){
                    Object[] fila = new Object[4];
                    fila[0]= lider.getId();
                    fila[1]= lider.getNombre();
                    fila[2]= lider.getApellido();
                    fila[3]= lider.getCiudad();   
                    modelo.addRow(fila);                 
                }
                tabla.setModel(modelo);
                modelo.fireTableDataChanged(); //actualizo el modelo de la tabla
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        public void segundoInforme() {
            try{
                List<ProyectosVo> proyectos = controller.listarProyectos();
                modelo = new DefaultTableModel();
                modelo.addColumn("Id proyecto");
                modelo.addColumn("Constructora");
                modelo.addColumn("Habitaciones");
                modelo.addColumn("Ciudad");
                for(ProyectosVo proyecto: proyectos){
                    Object[] fila = new Object[4];
                    fila[0]= proyecto.getId();
                    fila[1]= proyecto.getConstructora();
                    fila[2]= proyecto.getHabitaciones();
                    fila[3]= proyecto.getCiudad();   
                    modelo.addRow(fila);                 
                }
                tabla.setModel(modelo);
                modelo.fireTableDataChanged();
                
            }
            catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }    
    }

    public void tercerInforme() {
        try{
            List<ComprasProveedorVo> proveedores = controller.listarProveedores();
            modelo = new DefaultTableModel();
            modelo.addColumn("Id compra");
            modelo.addColumn("Constructora");
            modelo.addColumn("Banco Vinculado ");
            
            for(ComprasProveedorVo proveedor: proveedores){
                Object[] fila = new Object[3];
                fila[0]= proveedor.getId();
                fila[1]= proveedor.getConstructora();
                fila[2]= proveedor.getBanco();   
                modelo.addRow(fila);                 
            }
            tabla.setModel(modelo);
            modelo.fireTableDataChanged();
            
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    
}

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == primerInf){
                primerInforme();
                lblConsulta.setText("1.Informe de líderes por ciudades:");
            }
            if(e.getSource() == segundoInf){
                segundoInforme();
                lblConsulta.setText("2.Informe de proyectos de Casas Campestres en la Costa Atlantica:");
            }
            if(e.getSource() == tercerInf){
                tercerInforme();
                lblConsulta.setText("3. Informe Compras Realizadas al Proveedor Homecenter:");
            }

            
        }
    
}

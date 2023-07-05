package vista;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaInicio extends JFrame {

    public JLabel version;

    private JPanel panelPrincipal;
    private JPanel lineaSeparadora;
    private JPanel lineaSeparadora2;
    private JPanel lineaSeparadora3;
    private JLabel etiquetaFiltro;
    private final String[] filtros = {"Nombre", "Tel/Cel", "Cédula", "Dirección", "Cumpleaños"};
    private JLabel etiquetaNombre;
    private JLabel etiquetaApellidos;
    private JLabel etiquetaMiDirectorio;
    private JLabel etiquetaTelefono;
    private JLabel etiquetaCedula;
    private JLabel etiquetaDireccion;
    private JLabel etiquetaFechaNacimiento;

    public JScrollPane barraDesplazamiento;
    public JTextField cajaBuscar;
    public JTextField cajaNombre;
    public JTextField cajaApellidos;
    public JTextField cajaTelefono;
    public JTextField cajaCedula;
    public JTextField cajaDirrecion;
    public JComboBox listaDesplegableFiltro;
    public JButton botonBuscar;
    public JButton botonSeleccionarFoto;
    public JButton botonCrear;
    public JButton botonEditar;
    public JButton botonEliminar;
    public JButton botonLimpiar;
    public JButton botonAceptar;
    public JButton botonCancelar;
    public DefaultTableModel modelo;
    public JTable tablaContactos;
    public JDateChooser calendario;
    public JLabel etiquetaFoto;

    public VentanaInicio() {

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        setTitle("Mi Directorio"); // Establecer titulo de la ventana
        setSize(720, 480); // Establecer resolución de la pantalla (480p)
        setLocationRelativeTo(null); // Establecer ubicación de la pantalla en el centro
        setResizable(false); //Bloquer redimensión de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Establecer que el programa finaliza al cerrar la pestaña

        panelPrincipal = new JPanel(); //Crear el panel principal      
        panelPrincipal.setLayout(null); //Desactivar Layout
        getContentPane().add(panelPrincipal); //Agregamos el panel a la ventana

        panelPrincipal.setBackground(Color.decode("#F58C35")); //Asignamos color naranja al fondo de la ventana

        //Crear etiqueta "Filtrar por:"
        panelPrincipal.add(etiquetaFiltro = new JLabel("Filtrar por:"));
        etiquetaFiltro.setBounds(10, 10, 80, 20);
        etiquetaFiltro.setForeground(Color.BLACK);
        etiquetaFiltro.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear lista desplegable "Filtro"
        panelPrincipal.add(listaDesplegableFiltro = new JComboBox(filtros));
        listaDesplegableFiltro.setBounds(10, 35, 100, 20);
        listaDesplegableFiltro.setForeground(Color.BLACK);
        listaDesplegableFiltro.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear caja de texto "Buscar"
        panelPrincipal.add(cajaBuscar = new JTextField(""));
        cajaBuscar.setBounds(120, 35, 100, 20);
        cajaBuscar.setForeground(Color.BLACK);
        cajaBuscar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear botón "Buscar"
        panelPrincipal.add(botonBuscar = new JButton("Buscar"));
        botonBuscar.setBounds(230, 35, 80, 20);
        botonBuscar.setForeground(Color.BLACK);
        botonBuscar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear linea separadora
        panelPrincipal.add(lineaSeparadora = new JPanel());
        lineaSeparadora.setLayout(null);
        lineaSeparadora.setBackground(Color.BLACK);
        lineaSeparadora.setBounds(330, 20, 2, 400);

        //Crear tabla de contactos
        modelo = new DefaultTableModel();

        tablaContactos = new JTable(modelo);
        tablaContactos.setBounds(15, 65, 290, 350);
        tablaContactos.getTableHeader().setReorderingAllowed(false); //Establecer que no se puedan mover las columnas
        panelPrincipal.add(tablaContactos);

        barraDesplazamiento = new JScrollPane(tablaContactos, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        barraDesplazamiento.setBounds(15, 65, 290, 350);
        panelPrincipal.add(barraDesplazamiento);

        //Crear etiqueta para foto de contacto
        etiquetaFoto = new JLabel();
        etiquetaFoto.setOpaque(true);
        etiquetaFoto.setBackground(Color.decode("#CACAC9"));
        etiquetaFoto.setBounds(350, 35, 80, 100);
        etiquetaFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPrincipal.add(etiquetaFoto);

        //Crear etiqieta para logo del programa
        panelPrincipal.add(etiquetaMiDirectorio = new JLabel("Mi Directorio"));
        etiquetaMiDirectorio.setBounds(465, 65, 180, 25);
        etiquetaMiDirectorio.setForeground(Color.BLACK);
        etiquetaMiDirectorio.setFont(new Font("Comic Sans MS", Font.BOLD, 28));

        //Crear botón para seleccionar foto
        panelPrincipal.add(botonSeleccionarFoto = new JButton("seleccionar"));
        botonSeleccionarFoto.setBounds(350, 145, 80, 15);
        botonSeleccionarFoto.setForeground(Color.BLACK);
        botonSeleccionarFoto.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
        botonSeleccionarFoto.setBorder(null);

        //Crear etiqueta "Nombre(s)"
        panelPrincipal.add(etiquetaNombre = new JLabel("Nombre(s):*"));
        etiquetaNombre.setBounds(345, 180, 85, 20);
        etiquetaNombre.setForeground(Color.BLACK);
        etiquetaNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear caja de texto "Nombre(s)"
        panelPrincipal.add(cajaNombre = new JTextField(""));
        cajaNombre.setBounds(430, 180, 250, 20);
        cajaNombre.setForeground(Color.BLACK);
        cajaNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear etiqueta "Apellidos"
        panelPrincipal.add(etiquetaApellidos = new JLabel("Apellidos:*"));
        etiquetaApellidos.setBounds(345, 205, 85, 20);
        etiquetaApellidos.setForeground(Color.BLACK);
        etiquetaApellidos.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear caja de texto "Nombre(s)"
        panelPrincipal.add(cajaApellidos = new JTextField(""));
        cajaApellidos.setBounds(430, 205, 250, 20);
        cajaApellidos.setForeground(Color.BLACK);
        cajaApellidos.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear linea separadora 2
        panelPrincipal.add(lineaSeparadora2 = new JPanel());
        lineaSeparadora2.setLayout(null);
        lineaSeparadora2.setBackground(Color.BLACK);
        lineaSeparadora2.setBounds(365, 240, 300, 2);

        //Crear etiqueta "Tel/Cel"
        panelPrincipal.add(etiquetaTelefono = new JLabel("Tel/Cel:*"));
        etiquetaTelefono.setBounds(405, 250, 80, 20);
        etiquetaTelefono.setForeground(Color.BLACK);
        etiquetaTelefono.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear caja de texto "Tel/Cel"
        panelPrincipal.add(cajaTelefono = new JTextField(""));
        cajaTelefono.setBounds(360, 270, 140, 20);
        cajaTelefono.setForeground(Color.BLACK);
        cajaTelefono.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear etiqueta "Cédula"
        panelPrincipal.add(etiquetaCedula = new JLabel("Cédula:"));
        etiquetaCedula.setBounds(570, 250, 80, 20);
        etiquetaCedula.setForeground(Color.BLACK);
        etiquetaCedula.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear caja de texto "Cédula"
        panelPrincipal.add(cajaCedula = new JTextField(""));
        cajaCedula.setBounds(530, 270, 130, 20);
        cajaCedula.setForeground(Color.BLACK);
        cajaCedula.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear etiqueta "Dirección"
        panelPrincipal.add(etiquetaDireccion = new JLabel("Dirección:"));
        etiquetaDireccion.setBounds(400, 295, 80, 20);
        etiquetaDireccion.setForeground(Color.BLACK);
        etiquetaDireccion.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear caja de texto "Dirección"
        panelPrincipal.add(cajaDirrecion = new JTextField(""));
        cajaDirrecion.setBounds(360, 315, 140, 20);
        cajaDirrecion.setForeground(Color.BLACK);
        cajaDirrecion.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear etiqueta "Fecha de nacimiento"
        panelPrincipal.add(etiquetaFechaNacimiento = new JLabel("Fecha de nacimiento:"));
        etiquetaFechaNacimiento.setBounds(530, 295, 150, 20);
        etiquetaFechaNacimiento.setForeground(Color.BLACK);
        etiquetaFechaNacimiento.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear caja de calendario
        panelPrincipal.add(calendario = new JDateChooser());
        calendario.setBounds(530, 315, 130, 20);
        calendario.setForeground(Color.BLACK);
        calendario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        calendario.setDateFormatString("yyyy-MM-dd");

        //Crear linea separadora 3
        panelPrincipal.add(lineaSeparadora3 = new JPanel());
        lineaSeparadora3.setLayout(null);
        lineaSeparadora3.setBackground(Color.BLACK);
        lineaSeparadora3.setBounds(365, 350, 300, 2);

        //Crear botón "Crear"
        panelPrincipal.add(botonCrear = new JButton("Crear"));
        botonCrear.setBounds(420, 370, 90, 20);
        botonCrear.setForeground(Color.BLACK);
        botonCrear.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear botón "Editar"
        panelPrincipal.add(botonEditar = new JButton("Editar"));
        botonEditar.setBounds(515, 370, 90, 20);
        botonEditar.setForeground(Color.BLACK);
        botonEditar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        botonEditar.setEnabled(false);

        //Crear botón "Eliminar"
        panelPrincipal.add(botonEliminar = new JButton("Eliminar"));
        botonEliminar.setBounds(420, 395, 90, 20);
        botonEliminar.setForeground(Color.BLACK);
        botonEliminar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        botonEliminar.setEnabled(false);

        //Crear botón "Limpiar"
        panelPrincipal.add(botonLimpiar = new JButton("Limpiar"));
        botonLimpiar.setBounds(515, 395, 90, 20);
        botonLimpiar.setForeground(Color.BLACK);
        botonLimpiar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        //Crear botón "Aceptar"
        panelPrincipal.add(botonAceptar = new JButton("Aceptar"));
        botonAceptar.setBounds(420, 370, 90, 20);
        botonAceptar.setForeground(Color.BLACK);
        botonAceptar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        botonAceptar.setVisible(false);

        //Crear botón "Cancelar"
        panelPrincipal.add(botonCancelar = new JButton("Cancelar"));
        botonCancelar.setBounds(515, 370, 100, 20);
        botonCancelar.setForeground(Color.BLACK);
        botonCancelar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        botonCancelar.setVisible(false);

        //Version del programa
        panelPrincipal.add(version = new JLabel("1.0"));
        version.setBounds(685, 425, 20, 10);
        version.setForeground(Color.BLACK);

    }

}

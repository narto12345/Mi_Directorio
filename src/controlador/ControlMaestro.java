package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Contacto;
import modelo.MetodosSQL;
import vista.VentanaInicio;

public class ControlMaestro implements ActionListener, MouseListener {
    
    VentanaInicio ventanaInicio;
    Contacto contacto;
    MetodosSQL metodosSQL;
    
    private boolean tablaFuncional = true;
    
    public ControlMaestro(VentanaInicio ventanaInicio, Contacto contacto, MetodosSQL metodosSQL) {
        
        this.ventanaInicio = ventanaInicio;
        this.contacto = contacto;
        this.metodosSQL = metodosSQL;
        
    }

    //Crear metodo para iniciar
    public void iniciar() {
        
        metodosSQL.cargaTabla("Nombre", "");
        ventanaInicio.tablaContactos.setModel(contacto.getModeloTabla());
        ventanaInicio.botonLimpiar.addActionListener(this); //Establecer acción al botón "Limpiar"
        ventanaInicio.botonBuscar.addActionListener(this); //Establecer acción al botón "Buscar"
        ventanaInicio.listaDesplegableFiltro.addActionListener(this); //Establecer acción al botón "Limpiar"
        ventanaInicio.tablaContactos.addMouseListener(this); //Establecer accion al momento de cliquear un registro en la tabla
        ventanaInicio.botonSeleccionarFoto.addActionListener(this); //Establecer acción al botón "Seleccionar"
        ventanaInicio.botonCrear.addActionListener(this); //Establecer acción al botón "Crear"
        ventanaInicio.botonEditar.addActionListener(this);//Establecer acción al botón "Editar"
        ventanaInicio.botonEliminar.addActionListener(this); //Establecer accion al botón "Eliminar"
        ventanaInicio.botonCancelar.addActionListener(this); //Establecer accion al botón "Cancelar"
        ventanaInicio.botonAceptar.addActionListener(this); //Establecer accion al botón "Aceptar"

        this.ventanaInicio.setVisible(true);//Hacer visible la campatalla de inicio

    }

    //Metodo para limpliar elementos de la ventana
    private void limpiarObjecto(Boolean total) {
        
        Date fecha = null;
        
        if (total) {
            metodosSQL.cargaTabla("Nombre", "");
            ventanaInicio.listaDesplegableFiltro.setSelectedItem("Nombre");
            metodosSQL.cargaTabla("Nombre", "");
            ventanaInicio.tablaContactos.setModel(contacto.getModeloTabla());
            ventanaInicio.cajaBuscar.setText("");
            ventanaInicio.tablaContactos.setEnabled(true);
            ventanaInicio.listaDesplegableFiltro.setEnabled(true);
            ventanaInicio.cajaBuscar.setEnabled(true);
            ventanaInicio.botonBuscar.setEnabled(true);
            tablaFuncional = true;
            
        }

        // Reinciar cajas y etiqueta de foto
        ventanaInicio.etiquetaFoto.setIcon(new ImageIcon(getClass().getResource("")));
        ventanaInicio.cajaNombre.setText("");
        ventanaInicio.cajaApellidos.setText("");
        ventanaInicio.cajaTelefono.setText("");
        ventanaInicio.cajaCedula.setText("");
        ventanaInicio.cajaDirrecion.setText("");
        ventanaInicio.calendario.setDate(fecha);

        // Desbloquear botones "Enable"
        ventanaInicio.botonCrear.setEnabled(true);
        ventanaInicio.botonLimpiar.setEnabled(true);
        ventanaInicio.botonSeleccionarFoto.setEnabled(true);

        // Bloquear cajas "Enable"
        ventanaInicio.botonEditar.setEnabled(false);
        ventanaInicio.botonEliminar.setEnabled(false);
        ventanaInicio.calendario.setEnabled(true);

        // Desbloquear cajas "Edición"
        ventanaInicio.cajaNombre.setEditable(true);
        ventanaInicio.cajaApellidos.setEditable(true);
        ventanaInicio.cajaCedula.setEditable(true);
        ventanaInicio.cajaTelefono.setEditable(true);
        ventanaInicio.cajaDirrecion.setEditable(true);

        // Reiniciar botónes Crear, Editar, Eliminar y Limpiar
        ventanaInicio.botonAceptar.setVisible(false);
        ventanaInicio.botonCancelar.setVisible(false);
        ventanaInicio.botonCrear.setVisible(true);
        ventanaInicio.botonEditar.setVisible(true);
        ventanaInicio.botonEliminar.setVisible(true);
        ventanaInicio.botonLimpiar.setVisible(true);

        // Limpiar objecto "contacto"
        this.limpiarContacto();
        
    }

    // Metodo para volver a null los valores de los atributos del objecto "contacto"
    private void limpiarContacto() {
        
        contacto.setNombre(null);
        contacto.setApellidos(null);
        contacto.setFoto(null);
        contacto.setFotoDB(null);
        contacto.setCedula(null);
        contacto.setTelelefono(null);
        contacto.setDireccion(null);
        contacto.setCumpleaños(null);
        contacto.setCumpleañosSalida(null);
        
    }

    //Metodos de acciones para los botónes y lista desplegable
    @Override
    public void actionPerformed(ActionEvent e) {

        //Botón "Limpiar"
        if (e.getSource() == ventanaInicio.botonLimpiar) {
            this.limpiarObjecto(true);
        }

        //Botón "Buscar"
        if (e.getSource() == ventanaInicio.botonBuscar) {
            metodosSQL.cargaTabla((String) ventanaInicio.listaDesplegableFiltro.getSelectedItem(), ventanaInicio.cajaBuscar.getText());
            ventanaInicio.tablaContactos.setModel(contacto.getModeloTabla());
            this.limpiarObjecto(false);
        }

        //Lista desplegable "Filtro"
        if (e.getSource() == ventanaInicio.listaDesplegableFiltro) {
            ventanaInicio.cajaBuscar.setText("");
        }

        //Botón "Seleccionar"
        if (e.getSource() == ventanaInicio.botonSeleccionarFoto) {
            
            JFileChooser seleccionarFoto = new JFileChooser();
            seleccionarFoto.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            // Crear filtros para el "JFileChooser"
            FileNameExtensionFilter filtroArchivos = new FileNameExtensionFilter("*png, *jpg, *jpeg", "png", "jpg", "jpeg");
            seleccionarFoto.setFileFilter(filtroArchivos);
            
            int opcionSeleccionada = seleccionarFoto.showOpenDialog(ventanaInicio);
            
            if (opcionSeleccionada == JFileChooser.APPROVE_OPTION) { // Si el usuario selecciono "Aceptar"

                File foto = seleccionarFoto.getSelectedFile(); // Obtener el archivo seleccionado

                contacto.setFoto(foto); // Eviamos el archivo al objecto "contacto"

                String ruta = foto.getAbsolutePath(); //Obtener ruta del archivo seleccionado

                ImageIcon imagenContacto = new ImageIcon(ruta);
                ventanaInicio.etiquetaFoto.setIcon(new ImageIcon(imagenContacto.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
                
            }
            
        }

        //Botón "Crear"
        if (e.getSource() == ventanaInicio.botonCrear) {
            
            contacto.setNombre(ventanaInicio.cajaNombre.getText());
            contacto.setApellidos(ventanaInicio.cajaApellidos.getText());
            contacto.setTelelefono(ventanaInicio.cajaTelefono.getText());
            contacto.setCedula(ventanaInicio.cajaCedula.getText());
            contacto.setDireccion(ventanaInicio.cajaDirrecion.getText());

            //Enviar fecha de cumpleaños al objecto "contacto"
            if (ventanaInicio.calendario.getDate() == null) {
                contacto.setCumpleaños(null);
            } else {
                Date fecha = ventanaInicio.calendario.getDate();
                DateFormat cumpleaños = new SimpleDateFormat("yyyy-MM-dd");
                contacto.setCumpleaños(cumpleaños.format(fecha));
            }

            //Validaciones de inserción
            if (this.ValidarCamposObligatorios()) {
                if (metodosSQL.crearContacto()) {
                    JOptionPane.showMessageDialog(null, "El contacto ha sido agregado.");
                    this.limpiarObjecto(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El contacto no ha sido agregado.");
                    this.limpiarObjecto(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor diligenciar los campos obligatotios.");
            }
            
        }

        //Botón "Editar"
        if (e.getSource() == ventanaInicio.botonEditar) {

            //Desbloqueos y bloqueos corresponfientes
            ventanaInicio.tablaContactos.setEnabled(false);
            ventanaInicio.listaDesplegableFiltro.setEnabled(false);
            ventanaInicio.cajaBuscar.setEnabled(false);
            ventanaInicio.botonBuscar.setEnabled(false);
            
            ventanaInicio.botonAceptar.setVisible(true);
            ventanaInicio.botonCancelar.setVisible(true);
            
            ventanaInicio.botonCrear.setVisible(false);
            ventanaInicio.botonEditar.setVisible(false);
            ventanaInicio.botonEliminar.setVisible(false);
            ventanaInicio.botonLimpiar.setVisible(false);
            
            ventanaInicio.cajaNombre.setEditable(true);
            ventanaInicio.cajaApellidos.setEditable(true);
            ventanaInicio.cajaTelefono.setEditable(true);
            ventanaInicio.cajaCedula.setEditable(true);
            ventanaInicio.cajaDirrecion.setEditable(true);
            ventanaInicio.botonSeleccionarFoto.setEnabled(true);
            ventanaInicio.calendario.setEnabled(true);
            
            tablaFuncional = false;
            
        }

        //Botón "Cancelar"
        if (e.getSource() == ventanaInicio.botonCancelar) {
            this.limpiarObjecto(true);
        }

        //Botón "Aceptar"
        if (e.getSource() == ventanaInicio.botonAceptar) {
            
            contacto.setNombre(ventanaInicio.cajaNombre.getText());
            contacto.setApellidos(ventanaInicio.cajaApellidos.getText());
            contacto.setTelelefono(ventanaInicio.cajaTelefono.getText());
            contacto.setCedula(ventanaInicio.cajaCedula.getText());
            contacto.setDireccion(ventanaInicio.cajaDirrecion.getText());

            //Enviar fecha de cumpleaños al objecto "contacto"
            if (ventanaInicio.calendario.getDate() == null) {
                contacto.setCumpleaños(null);
            } else {
                Date fecha = ventanaInicio.calendario.getDate();
                DateFormat cumpleaños = new SimpleDateFormat("yyyy-MM-dd");
                contacto.setCumpleaños(cumpleaños.format(fecha));
            }

            //Validaciones de inserción
            if (this.ValidarCamposObligatorios()) {
                if (metodosSQL.editarContacto()) {
                    JOptionPane.showMessageDialog(null, "El contacto ha sido modificado");
                    this.limpiarObjecto(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El contacto no ha sido modificado.");
                    this.limpiarObjecto(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor diligenciar los campos obligatotios.");
            }
            
        }

        //Botón "Eliminar"
        if (e.getSource() == ventanaInicio.botonEliminar) {
            
            if (metodosSQL.eliminarContacto()) {
                JOptionPane.showMessageDialog(null, "El contacto fue eliminado.");
            } else {
                JOptionPane.showMessageDialog(null, "El contacto no pudo ser eliminado.");
            }
            
            this.limpiarObjecto(true);
            
        }
        
    }

    //Metodo para enviar la fila seleccionada a los campos correspondientes
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (tablaFuncional) {
            
            this.limpiarObjecto(false);
            
            metodosSQL.seleccionarTabla(ventanaInicio.tablaContactos.getSelectedRow()); //Enviar la fila seleccionada de la tabla a meotdo que se encarga de mandar todos los datos de la DB al objecto "contacto"

            ventanaInicio.cajaNombre.setText(contacto.getNombre());
            ventanaInicio.cajaApellidos.setText(contacto.getApellidos());
            ventanaInicio.cajaCedula.setText(contacto.getCedula());
            ventanaInicio.cajaTelefono.setText(contacto.getTelelefono());
            ventanaInicio.cajaDirrecion.setText(contacto.getDireccion());
            ventanaInicio.calendario.setDate(contacto.getCumpleañosSalida());

            //Insertar la imagen en el etiqueta "etiquetaFoto"
            if (contacto.getFotoDB() != null) {
                ImageIcon imagenContacto = new ImageIcon(contacto.getFotoDB());
                ventanaInicio.etiquetaFoto.setIcon(new ImageIcon(imagenContacto.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
            }

            //Bloquear botones
            ventanaInicio.botonCrear.setEnabled(false);
            ventanaInicio.botonSeleccionarFoto.setEnabled(false);

            //Desbloquear cajas
            ventanaInicio.botonEditar.setEnabled(true);
            ventanaInicio.botonEliminar.setEnabled(true);

            //Bloquear cajas
            ventanaInicio.cajaNombre.setEditable(false);
            ventanaInicio.cajaApellidos.setEditable(false);
            ventanaInicio.cajaCedula.setEditable(false);
            ventanaInicio.cajaTelefono.setEditable(false);
            ventanaInicio.cajaDirrecion.setEditable(false);
            ventanaInicio.calendario.setEnabled(false);
            
        }
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public boolean ValidarCamposObligatorios() {
        
        return !(ventanaInicio.cajaNombre.getText().equals("") || ventanaInicio.cajaApellidos.getText().equals("") || ventanaInicio.cajaTelefono.getText().equals(""));
    }
    
}

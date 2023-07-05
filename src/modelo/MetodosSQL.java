package modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

public class MetodosSQL {

    Contacto contacto = null;

    private ArrayList<Integer> identificadorOculto = new ArrayList<>(); //Declaramos el arreglo dinamico que nos ayduará a identificar el id de cada registro 

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Conexion con = null;
    private Connection conexion = null;

    public MetodosSQL(Contacto contacto) {
        this.contacto = contacto;
    }

    //Establecer un modelo en base a a los filtros recibidos
    public void cargaTabla(String filtro, String busqueda) {

        identificadorOculto.clear();

        String segundaColumna = null;

        try {

            switch (filtro) {
                case "Nombre":
                    filtro = "apellidos";
                    segundaColumna = "Apellidos";
                    busqueda = "where nombre like '%" + busqueda + "%' or apellidos like '%" + busqueda + "%'";

                    break;
                case "Tel/Cel":
                    filtro = "telefono";
                    segundaColumna = "Tel/Cel";
                    if (!"".equals(busqueda)) {
                        busqueda = "where telefono like '%" + busqueda + "%'";
                    }
                    break;
                case "Cédula":
                    filtro = "cedula";
                    segundaColumna = "Cédula";
                    if (!"".equals(busqueda)) {
                        busqueda = "where cedula like '%" + busqueda + "%'";
                    }
                    break;
                case "Dirección":
                    filtro = "direccion";
                    segundaColumna = "Dirección";
                    if (!"".equals(busqueda)) {
                        busqueda = "where direccion like '%" + busqueda + "%'";
                    }
                    break;
                case "Cumpleaños":
                    filtro = "cumpleaños";
                    segundaColumna = "Cumpleaños";
                    if (!"".equals(busqueda)) {
                        busqueda = "where cumpleaños like '%" + busqueda + "%'";
                    }
                    break;
            }

            DefaultTableModel modeloTabla = new DefaultTableModel() { // Creamos el modelo
                @Override
                public boolean isCellEditable(int row, int column) { //Establecer que los campos sean no editables
                    return false;
                }
            };

            con = new Conexion();
            conexion = con.getConnection();

            ps = (PreparedStatement) conexion.prepareStatement("select idContacto,nombre," + filtro + " from contacto " + busqueda);
            rs = ps.executeQuery();

            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn(segundaColumna);

            while (rs.next()) {

                Object fila[] = new Object[2];

                for (int i = 0; i < 2; i++) {

                    if (rs.getObject(i + 2) == null || rs.getObject(i + 2).equals("")) {
                        fila[i] = "No hay registro";
                    } else {
                        fila[i] = rs.getObject(i + 2);
                    }

                }

                identificadorOculto.add(rs.getInt(1));

                modeloTabla.addRow(fila);

            }

            contacto.setModeloTabla(modeloTabla); // Establecer el modelo en el obejecto "contacto"

            busqueda = "";

            conexion.close();

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }

    }

    // Establecer los datos en el objecto "contacto", en base a la fila seleccionada
    public void seleccionarTabla(Integer filaSeleccionada) {

        try {

            con = new Conexion();
            conexion = con.getConnection();

            BufferedImage fotoDB = null;

            ps = (PreparedStatement) conexion.prepareStatement("select * from contacto where idContacto = ?");

            ps.setInt(1, identificadorOculto.get(filaSeleccionada));

            rs = ps.executeQuery();

            while (rs.next()) {

                contacto.setIdContacto(rs.getInt("idContacto"));
                contacto.setNombre(rs.getString("nombre"));
                contacto.setApellidos(rs.getString("apellidos"));
                contacto.setTelelefono(rs.getString("telefono"));
                contacto.setCedula(rs.getString("cedula"));
                contacto.setDireccion(rs.getString("direccion"));
                contacto.setCumpleañosSalida(rs.getDate("cumpleaños"));

                //Trasformar imagen de binario a "BufferedImage"
                if (rs.getBinaryStream("foto") != null) {
                    InputStream fotoBinario = rs.getBinaryStream("foto");
                    fotoDB = ImageIO.read(fotoBinario);
                    contacto.setFotoDB(fotoDB);
                }

            }

            conexion.close();

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }

    }

    public boolean crearContacto() {

        try {

            if (contacto.getFoto() == null) {
                return this.crearContactoSinFoto();
            } else {
                return this.crearContactoConFoto();
            }

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }
        return false;

    }

    public boolean crearContactoConFoto() {

        try {

            con = new Conexion();
            conexion = con.getConnection();

            FileInputStream imagenEntrada = new FileInputStream(contacto.getFoto());

            ps = (PreparedStatement) conexion.prepareStatement("insert contacto (nombre,apellidos,foto,telefono,cedula,direccion,cumpleaños) values(?,?,?,?,?,?,?)");

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getApellidos());
            ps.setBinaryStream(3, imagenEntrada, (int) contacto.getFoto().length());
            ps.setString(4, contacto.getTelelefono());
            ps.setString(5, contacto.getCedula());
            ps.setString(6, contacto.getDireccion());
            ps.setString(7, contacto.getCumpleaños());

            int resultadoInserción = ps.executeUpdate();

            if (resultadoInserción > 0) {
                conexion.close();
                return true;
            } else {
                conexion.close();
                return false;
            }

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }

        return false;

    }

    public boolean crearContactoSinFoto() {
        try {

            con = new Conexion();
            conexion = con.getConnection();

            ps = (PreparedStatement) conexion.prepareStatement("insert contacto (nombre,apellidos,telefono,cedula,direccion,cumpleaños) values(?,?,?,?,?,?)");

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getApellidos());
            ps.setString(3, contacto.getTelelefono());
            ps.setString(4, contacto.getCedula());
            ps.setString(5, contacto.getDireccion());
            ps.setString(6, contacto.getCumpleaños());

            int resultadoInserción = ps.executeUpdate();

            if (resultadoInserción > 0) {
                conexion.close();
                return true;
            } else {
                conexion.close();
                return false;
            }

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }
        return false;
    }

    public boolean eliminarContacto() {

        try {

            con = new Conexion();
            conexion = con.getConnection();

            ps = (PreparedStatement) conexion.prepareStatement("delete from contacto where idContacto=?");
            ps.setInt(1, contacto.getIdContacto());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                conexion.close();
                return true;
            } else {
                conexion.close();
                return false;
            }

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }
        return false;
    }

    public boolean editarContacto() {

        try {

            if (contacto.getFoto() == null) {
                return this.editarContactoSinFotoModificada();
            } else {
                return this.editarContactoFotoModificada();
            }

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }
        return false;

    }

    public boolean editarContactoFotoModificada() {

        try {

            con = new Conexion();
            conexion = con.getConnection();

            FileInputStream imagenEntrada = new FileInputStream(contacto.getFoto());

            ps = (PreparedStatement) conexion.prepareStatement("update contacto set nombre=?,apellidos=?,foto=?,telefono=?,cedula=?,direccion=?,cumpleaños=?  where idContacto = ?;");

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getApellidos());
            ps.setBinaryStream(3, imagenEntrada, (int) contacto.getFoto().length());
            ps.setString(4, contacto.getTelelefono());
            ps.setString(5, contacto.getCedula());
            ps.setString(6, contacto.getDireccion());
            ps.setString(7, contacto.getCumpleaños());

            ps.setInt(8, contacto.getIdContacto());

            int resultadoInserción = ps.executeUpdate();

            if (resultadoInserción > 0) {
                conexion.close();
                return true;
            } else {
                conexion.close();
                return false;
            }

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }

        return false;

    }

    public boolean editarContactoSinFotoModificada() {
        try {

            con = new Conexion();
            conexion = con.getConnection();

            ps = (PreparedStatement) conexion.prepareStatement("update contacto set nombre=?,apellidos=?,telefono=?,cedula=?,direccion=?,cumpleaños=?  where idContacto = ?;");

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getApellidos());
            ps.setString(3, contacto.getTelelefono());
            ps.setString(4, contacto.getCedula());
            ps.setString(5, contacto.getDireccion());
            ps.setString(6, contacto.getCumpleaños());

            ps.setInt(7, contacto.getIdContacto());

            int resultadoInserción = ps.executeUpdate();

            if (resultadoInserción > 0) {
                conexion.close();
                return true;
            } else {
                conexion.close();
                return false;
            }

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }

        return false;
    }

}

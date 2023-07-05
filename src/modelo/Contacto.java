package modelo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class Contacto {

    int idContacto;
    File foto = null;
    BufferedImage fotoDB = null;
    String nombre = null;
    String apellidos = null;
    String telelefono = null;
    String cedula = null;
    String direccion = null;
    String cumpleaños = null;
    Date cumpleañosSalida = null;
    DefaultTableModel modeloTabla = null;

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelelefono() {
        return telelefono;
    }

    public void setTelelefono(String telelefono) {
        this.telelefono = telelefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCumpleaños() {
        return cumpleaños;
    }

    public void setCumpleaños(String cumpleaños) {
        this.cumpleaños = cumpleaños;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {

        this.modeloTabla = modeloTabla;
    }

    public BufferedImage getFotoDB() {
        return fotoDB;
    }

    public void setFotoDB(BufferedImage fotoDB) {
        this.fotoDB = fotoDB;
    }

    public Date getCumpleañosSalida() {
        return cumpleañosSalida;
    }

    public void setCumpleañosSalida(Date cumpleañosSalida) {
        this.cumpleañosSalida = cumpleañosSalida;
    }

}

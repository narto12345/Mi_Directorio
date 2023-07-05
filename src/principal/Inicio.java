package principal;

/**
*
* @Author  Nicolas Santiago Sosa Jimenez
* @Version 1.0
* @Date    2022-04-20
*/

import controlador.ControlMaestro;
import modelo.Contacto;
import modelo.MetodosSQL;
import vista.VentanaInicio;

public class Inicio {

    public static void main(String[] args) {

        //Crear Vistas
        VentanaInicio ventanaInicial = new VentanaInicio();

        //Crear Modelos
        Contacto contacto = new Contacto();
        MetodosSQL metodosSQL = new MetodosSQL(contacto);

        //Crear controlador
        ControlMaestro controlador = new ControlMaestro(ventanaInicial, contacto, metodosSQL);

        //Iniciar programa
        controlador.iniciar();

    }

}

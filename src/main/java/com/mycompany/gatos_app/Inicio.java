package com.mycompany.gatos_app;

import javax.swing.JOptionPane;

/* Creamos un nuevo proyecto
Agregamos las dependencias "com.squareup.okhttp" y "gson". Esto se puede hacer
de dos formas:
a) Clic derecho en carpeta Dependencies, Add Dependency..., Query y tecleamos el
nombre de la dependencia.
b) Vamos a https://mvnrepository.com/ y en Search tecleamos el nombre de la 
dependencia, escogemos la version, copiamos el codigo para Maven y lo pegamos 
dentro de <dependencies> en el pom.xml
*/

public class Inicio {
    
    public static void main(String[] args) {
        //
        int opcionMenu = -1;
        //Array de tipo String con los botones del menu
        String[] botones = {"1. Ver gatos", "2. Salir"}; 
        
        //Menu Principal
        do{
            //Cuadro de dialogo usando JOptionPane y el metodo showInputDialog, que
            //recibe como parametro nulo el componente padre, Titulo dentro de la ventana,
            //Titulo de la ventana, tipo de mensaje, tipo de respuesta nulo, menu que
            //mostrara que es el array botones y la opcion por defecto que estara seleccionada.
            //Todo lo anterior se castea a un String
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos java", 
                    "Menu Principal", JOptionPane.INFORMATION_MESSAGE, null, 
                    botones, botones[0]);
            
            //Validamos la opcion seleccionada por el usuario
            for(int i=0; i<botones.length; i++){
                if(opcion.equals(botones[i])){
                    opcionMenu = i;
                }
            }
            
            switch(opcionMenu){
                case 0:
                    GatosService.verGatos();
                    break;
                default:
                    break;
            }
        } while(opcionMenu != 1);
    }
}

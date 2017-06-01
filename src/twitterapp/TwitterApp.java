
package twitterapp;

import javax.swing.JOptionPane;

/**
 *
 * @author jquesadaabeijon
 */
public class TwitterApp {

    public static void main(String[] args){
 
           /**
            * Interfaz de usuario de la aplicación de Twitter.
            */
            int op;
            Methods tw = new Methods();
            tw.keys();
            do{
            op= Integer.parseInt(JOptionPane.showInputDialog("1. Recuperar timeline \n2. Publicar Estado \n3. Enviar un mensaje directo \n4. Buscar por hastag o palabras \n5. Salir"));
            switch(op){
            case 1:
                tw.timeline();
                break;
            case 2:
                tw.status(JOptionPane.showInputDialog("Escribe tu nuevo estado: "));
                break;
            case 3:
                tw.sendMessage(JOptionPane.showInputDialog("Escribe el nombre de la persona a quién va dirigido el mensaje: "),JOptionPane.showInputDialog("Escribe el mensaje que quieres enviar: "));
                break;
            case 4:
                tw.searchHastag(JOptionPane.showInputDialog("Introduce el elemento a buscar: "));
                break;
            case 5:
                System.exit(0); 
                break;
        }
    }while(op!=0);
    }
      
                
    }

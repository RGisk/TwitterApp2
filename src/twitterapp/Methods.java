
package twitterapp;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author jquesadaabeijon
 */

public class Methods {
    
    private Twitter twitter;
    private ConfigurationBuilder cb;
    
    /**
     * A través del objeto 'ConfigurationBuilder' se introducen las claves que vamos a 
     * utilizar para conectar con la cuenta de Twitter.
     */
    public void keys(){
            cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true);
//                    .setOAuthConsumerKey("***")
//                    .setOAuthConsumerSecret("***")
//                    .setOAuthAccessToken("***")
//                    .setOAuthAccessTokenSecret("***");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter = tf.getInstance();
    }
    /**
     * Recupera el 'timeline' de la cuenta de Twitter y muestra las
     * últimas actualizaciones.
     */
    public void timeline(){
        List<Status> statuses=null;
        String mensaje="Mostrando timeline. \n";
        Status aux=null;
        try {
            statuses = twitter.getHomeTimeline();
            System.out.println("Actualizar");
            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ":" +
                        status.getText());
            }   } catch (TwitterException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<Status>miIterator=statuses.iterator();
        while(miIterator.hasNext()){
            aux=miIterator.next();
            mensaje+=aux.getUser().getName()+","+aux.getText()+"\n-\n";
            System.out.println(mensaje);
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
    /**
     * Publicar un estado en la cuenta de Twitter.
     * @param lastStatus 
     */
    public void status(String lastStatus){
        List<Status> statuses=null;
        try {
            Status status = twitter.updateStatus(lastStatus);
            System.out.println("El estado se actualizó correctamente a [" + status.getText() + "].");
        } catch (TwitterException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Enviar un mensaje directo a una persona de la lista de seguidos/te siguen
     * de la cuenta de Twitter.
     */
    public void sendMessage(String destination, String message){
        try {
            DirectMessage dispatch;
            dispatch = twitter.sendDirectMessage(destination,message);
            System.out.println("Has enviado el mensaje: "+ dispatch.getText() + " a @" + dispatch.getRecipientScreenName());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Búsqueda por 'hastag' o palabras de un mensaje.
     * @param search 
     */
    public void searchHastag(String search){
        try {
            Query query = new Query(search);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }   } catch (TwitterException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}

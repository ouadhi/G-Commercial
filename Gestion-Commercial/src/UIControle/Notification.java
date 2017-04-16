
package UIControle;

import javafx.scene.control.Alert;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class Notification {
    
    public static void Addnotification () {
        String title = "Ajoute";
        String message = "l'operation est terminé avec succès ";
        NotificationType type  = NotificationType.SUCCESS ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
    }
    
    public static void Updatenotification (){
        String title = "Modification";
        String message = "l'operation est terminé avec succès ";
        NotificationType type  = NotificationType.SUCCESS ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
    }
    
    public static void Deletenotification(){
        String title = "Suppression";
        String message = "l'operation est terminé avec succès ";
        NotificationType type  = NotificationType.SUCCESS ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
        
    }
    
    public static void errorNotification(){
    String title = "problème";
        String message = " vérifier tous les champs s'il vous plait ";
        NotificationType type  = NotificationType.WARNING ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
     }
    public static void errorNotification(String msg){
    String title = "problème";
        String message = msg;
        NotificationType type  = NotificationType.WARNING ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
     }
    public static void errorNotificationUserExists(){
    String title = "problème";
        String message = "L'utilisateur que vous avez specifie exit deja";
        NotificationType type  = NotificationType.WARNING ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
     }
    
    
    public static  Alert deleteAlert () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("suppression  ");
        alert.setContentText("voulez vous vraiment effectuer cette opération ??");
        
        return  alert  ; 
    }
    
    
    public static  Alert updateAlert () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Modification  ");
        alert.setContentText("voulez vous vraiment effectuer cette opération ??");
        
        return  alert  ; 
    }
    
    public static void login_notification  () {
        String title = "Bonjour";
        String message = " Bonjour ";
        NotificationType type  = NotificationType.SUCCESS  ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
       
    }
    
    public static void champVideNotification () {   
        String title = "Verification";
        String message = "" ;
        NotificationType type  = NotificationType.ERROR ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
    }
    
    
    public static void fauxNotification () {   
        String title = "Verification";
        String message = Erreurmsg.getPassword_user() ;
        NotificationType type  = NotificationType.ERROR  ; 
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(4));
    }
    
    
}

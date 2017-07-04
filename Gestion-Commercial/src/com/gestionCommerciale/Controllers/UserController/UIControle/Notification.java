
package com.gestionCommerciale.Controllers.UserController.UIControle;

import javafx.scene.control.Alert;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {

	public static void Addnotification() {
		String title = "Ajoute";
		String message = "l'operation est termin\u00E9 avec succ\u00E8s ";
		NotificationType type = NotificationType.SUCCESS;
		TrayNotification tray = new TrayNotification();
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(type);
		tray.showAndDismiss(Duration.seconds(4));
	}

	public static Alert deleteAlert() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation ");
		alert.setHeaderText("suppression  ");
		alert.setContentText("voulez vous vraiment effectuer cette op\u00E9ration ??");

		return alert;
	}

	public static void Deletenotification() {
		String title = "Suppression";
		String message = "l'operation est termin\u00E9 avec succ\u00E8s ";
		NotificationType type = NotificationType.SUCCESS;
		TrayNotification tray = new TrayNotification();
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(type);
		tray.showAndDismiss(Duration.seconds(4));

	}

	public static void errorNotification() {
		String title = "probl\u00E8me";
		String message = " v\u00E9rifier tous les champs s'il vous plait ";
		NotificationType type = NotificationType.WARNING;
		TrayNotification tray = new TrayNotification();
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(type);
		tray.showAndDismiss(Duration.seconds(4));
	}

	public static void Updatenotification() {
		String title = "Modification";
		String message = "l'operation est termin\u00E9 avec succ\u00E8s ";
		NotificationType type = NotificationType.SUCCESS;
		TrayNotification tray = new TrayNotification();
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(type);
		tray.showAndDismiss(Duration.seconds(4));
	}

}

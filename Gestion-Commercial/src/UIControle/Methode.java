package UIControle;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.gestionCommerciale.HibernateSchema.User;
import com.jfoenix.controls.JFXTextField;
import java.awt.Robot;
import java.text.DecimalFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Methode {

//    public static Double DoubleFormat(double f) {
//        BigDecimal bd = new BigDecimal(f);
//        bd = bd.setScale(2, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }
//    public static String DoubleFormat(double f) {
//        BigDecimal bd = new BigDecimal(f);
//        bd = bd.setScale(2, RoundingMode.HALF_UP);
//        DecimalFormat df = new DecimalFormat("##.00");
//        return df.format(bd).replace(".00", "0.00");
//    }
//    public static String DoubleFormat4(double f) {
//        BigDecimal bd = new BigDecimal(f);
//        bd = bd.setScale(4, RoundingMode.HALF_UP);
//        DecimalFormat df = new DecimalFormat("##.0000");
//        return df.format(bd).replace(".00", "0.00");
//    }
    public static String DoubleFormat(double f) {
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        DecimalFormat df = new DecimalFormat("##.00");
        String sdb = df.format(bd).replace(",", ".");
        if (sdb.startsWith(".")) {
            return sdb.replace(".", "0.");
        } else {
            return sdb;
        }
    }

    public static String DoubleFormat4(double f) {
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        DecimalFormat df = new DecimalFormat("##.0000");
        String sdb = df.format(bd).replace(",", ".");
        if (sdb.startsWith(".")) {
            return sdb.replace(".", "0.");
        } else {
            return sdb;
        }
    }
    public static Stage getStage(ActionEvent event) {

        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static Stage getStageMouses(MouseEvent event) {

        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static void setOnlyDouble(JFXTextField field, int max) {

        field.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0," + max + "}([\\.]\\d{0,2})?")) {
                    field.setText(oldValue);
                }

            }
        });

    }
    public static void setOnlyDouble4(JFXTextField field, int max) {

        field.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0," + max + "}([\\.]\\d{0,4})?")) {
                    field.setText(oldValue);
                }

            }
        });

    }

    public static void setOnlyInteger(JFXTextField field, int max) {

        field.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0," + max + "}?")) {
                    field.setText(oldValue);
                }

            }
        });

    }

    public static void setOnlyNumbre(JFXTextField field) {

        field.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    field.setText(oldValue);
                }

            }
        });

    }

    public static void setSelectedMouseClick(JFXTextField field) {

        field.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                field.selectAll();
            }
        });

    }

    public static void setsizeString(JFXTextField text, int maxtaille) {
        text.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > maxtaille) {
                    text.setText(oldValue);
                }

            }
        });
    }

    public static void SetUpper(JFXTextField field, int max) {
        field.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > max) {
                field.setText(oldValue);
            } else {
                field.setText(newValue.toUpperCase());
            }

        });

    }

    public static void setZeroRemoved(JFXTextField field) {
        field.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (field.getText().isEmpty()) {
                    field.setText("0.00");
                    field.selectAll();
                }
            }
        });

    }

    public static void showMenuItem(MenuButton menu, Label label) {
        if (User.isAdministrateur()) {
            menu.setVisible(true);
            label.setVisible(true);
        } else {
            menu.setVisible(false);
            label.setVisible(false);
        }
    }

    public static void moveFocus(Node rootNode) {
        try {
            rootNode.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                Robot eventRobot = new Robot();

                @Override
                public void handle(KeyEvent KV) {

                    if (KV.getCode() == KeyCode.ENTER) {
                        eventRobot.keyPress(java.awt.event.KeyEvent.VK_TAB);
                        eventRobot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                        KV.consume();
                    }
                }
            });

        } catch (Exception e) {
        }

    }

}

package ajudantes;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.List;

public abstract class MascaraCampos {
    private static List<KeyCode> ignoreKey;
    private Alert alert;

    public static void maxField(final TextField textField, final Integer length) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > length)
                    textField.setText(oldValue);
            }
        });
    }

    public static void maxField(final DatePicker datePicker, final Integer length) {
        datePicker.getEditor().textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > length)
                    datePicker.getEditor().setText(oldValue);
            }
        });
    }

    private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Posiciona o cursor sempre a direita.
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    private static void positionCaret(final DatePicker datePicker) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());

            }
        });
    }

    // Mascara para campos
    public static void loginField(final TextField textField) {

        maxField(textField,10);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = textField.getText();
                    value = value.replaceAll("[A-Z]","");
                    value = value.replaceAll("[0-9]","");
                    value = value.replaceFirst("(\\d)","$1");
                    textField.setText(value);
                    positionCaret(textField);
                }
            }
        });
    }

    public  static  void palavraField(final TextField textField) {

        maxField(textField, 40);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if(newValue.intValue()<41) {
                    String value = textField.getText();
                    value = value.replaceAll("[\\s]","");
                    value = value.replaceAll("[0-9]","");
                    value = value.replaceFirst("\\d","$1");
                    textField.setText(value);
                    positionCaret(textField);
                }
            }
        });
    }

    public static void textoField(final TextField textField) {

        maxField(textField, 150);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue() < 151) {
                    String value = textField.getText();
                    //value = value.replaceAll("[0-9]", "");
                    value = value.replaceFirst("\\d", "$1");
                    textField.setText(value);
                    positionCaret(textField);
                }

            }
        });
    }

    public static void dataField(final DatePicker datePicker) {
        maxField(datePicker, 10);

        datePicker.getEditor().lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue() <  11) {
                    String value = datePicker.getEditor().getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
                    datePicker.getEditor().setText(value);
                    positionCaret(datePicker);
                }

            }
        });
    }

    public static void telFixoField(TextField textField) {
        maxField(textField,14);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if(newValue.intValue()<15) {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]","");
                    value = value.replaceFirst("(\\d{2})(\\d{4})(\\d)","($1) $2-$3");
                    textField.setText(value);
                }
            }
        });
    }

    public static void telMovelField(TextField textField) {
        maxField(textField, 16);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if(newValue.intValue()<17) {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]","");
                    value = value.replaceFirst("(\\d{2})(\\d{1})(\\d{4})(\\d)","($1) $2 $3-$4");
                    textField.setText(value);
                }
            }
        });
    }

}

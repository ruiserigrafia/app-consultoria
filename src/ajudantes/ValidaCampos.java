package ajudantes;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public abstract class ValidaCampos {

    public static void validarData(DatePicker datePicker) throws Exception {
        boolean data;
        //DatePicker novaData;
        if(data = datePicker.getEditor().getText().matches("\\d{2}/\\d{2}/\\d{4}") != true) {
            throw new Exception("Informe a data no formato dd/mm/aaaa");
        } else {
            int dia = Integer.parseInt(datePicker.getEditor().getText().substring(0,2));
            int mes = Integer.parseInt(datePicker.getEditor().getText().substring(3,5));
            int ano = Integer.parseInt(datePicker.getEditor().getText().substring(6,10));
            if(dia < 0 || dia > 31) {
                throw new Exception("Informe para dia um número entre 00 e 32");
            } else if(mes < 1 || mes > 12) {
                throw  new Exception("Informe para mês um número entre 00 e 13");
            } else if(ano < 1899) {
                throw  new Exception("Informe para ano um número apartir 1900");
            }
        }
    }

    public static void validarTelFixo(TextField textFieldFixo) throws Exception {
        boolean telFixo;
    }

}

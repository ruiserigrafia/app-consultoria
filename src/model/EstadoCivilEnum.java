package model;

public enum EstadoCivilEnum {

    CASADO('C', "CASADO"),SOLTEIRO('S', "SOLTEIRO"),DIVORCIADO('D',"DIVORCIADO"),VIUVO('V',"VIÙVO");

    private final char codigo;
    private final String tipo;

    private EstadoCivilEnum(char codigo, String tipo) {
        this .codigo = codigo;
        this.tipo = tipo;
    }

    public char getCodigo() {
        return  codigo;
    }

    public String getTipo() {
        return tipo;
    }

     public char porTipo(String tipo) {
        for(EstadoCivilEnum estadoCivilEnum : EstadoCivilEnum.values()) {
            if(tipo == estadoCivilEnum.tipo) return codigo;
        }
        throw new IllegalArgumentException("Tipo inválido");
     }

    public String porCodigo(char codigo) {
        for(EstadoCivilEnum estadoCivilEnum : EstadoCivilEnum.values()) {
            if(codigo == estadoCivilEnum.codigo) return tipo;
        }
        throw  new IllegalArgumentException("Código inválido");
    }



}

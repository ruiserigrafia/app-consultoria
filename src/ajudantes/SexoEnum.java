package ajudantes;

public enum SexoEnum {

    MASCULINO('M',"Masculino"),FEMININO('F',"Feminino");

    private final char codigo;
    private final String tipo;

    SexoEnum(char codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public char getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public char porTipo(String tipo) {
        for(SexoEnum sexoEnum : SexoEnum.values()) {
            if(tipo == sexoEnum.tipo) return codigo;
        }
        throw new IllegalArgumentException("Tipo inválido");
    }

    public String porCodigo(char codigo) {
        for(SexoEnum sexoEnum: SexoEnum.values()) {
            if(codigo == sexoEnum.codigo) return tipo;
        }
        throw new IllegalArgumentException("Código inválido");
    }

}

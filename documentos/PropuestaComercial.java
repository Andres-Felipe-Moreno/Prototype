public class PropuestaComercial implements Documento {
    private String numeroPropuesta;
    private String cliente;
    private String descripcionServicio;
    private double valor;
    private String vigencia;

    public PropuestaComercial(String numeroPropuesta, String cliente, String descripcionServicio, double valor, String vigencia) {
        this.numeroPropuesta = numeroPropuesta;
        this.cliente = cliente;
        this.descripcionServicio = descripcionServicio;
        this.valor = valor;
        this.vigencia = vigencia;
    }

    // Constructor de copia para el patrón Prototype
    public PropuestaComercial(PropuestaComercial otra) {
        this.numeroPropuesta = otra.numeroPropuesta;
        this.cliente = otra.cliente;
        this.descripcionServicio = otra.descripcionServicio;
        this.valor = otra.valor;
        this.vigencia = otra.vigencia;
    }

    @Override
    public Documento clonar() {
        return new PropuestaComercial(this);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("=== PROPUESTA COMERCIAL ===");
        System.out.println("N° Propuesta: " + numeroPropuesta);
        System.out.println("Cliente: " + cliente);
        System.out.println("Descripción: " + descripcionServicio);
        System.out.println("Valor: $" + valor);
        System.out.println("Vigencia: " + vigencia);
        System.out.println("---------------------------");
    }

    // Getters y Setters
    public void setNumeroPropuesta(String numeroPropuesta) { this.numeroPropuesta = numeroPropuesta; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setDescripcionServicio(String descripcionServicio) { this.descripcionServicio = descripcionServicio; }
    public void setValor(double valor) { this.valor = valor; }
    public void setVigencia(String vigencia) { this.vigencia = vigencia; }
}
public class Contrato implements Documento {
    private String numeroContrato;
    private String contratista;
    private String entidadContratante;
    private double valor;
    private String duracion;

    public Contrato(String numeroContrato, String contratista, String entidadContratante, double valor, String duracion) {
        this.numeroContrato = numeroContrato;
        this.contratista = contratista;
        this.entidadContratante = entidadContratante;
        this.valor = valor;
        this.duracion = duracion;
    }

    // Constructor de copia para el patrón Prototype
    public Contrato(Contrato otro) {
        this.numeroContrato = otro.numeroContrato;
        this.contratista = otro.contratista;
        this.entidadContratante = otro.entidadContratante;
        this.valor = otro.valor;
        this.duracion = otro.duracion;
    }

    @Override
    public Documento clonar() {
        return new Contrato(this);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("=== CONTRATO DE PRESTACIÓN DE SERVICIOS ===");
        System.out.println("N° Contrato: " + numeroContrato);
        System.out.println("Contratista: " + contratista);
        System.out.println("Entidad: " + entidadContratante);
        System.out.println("Valor: $" + valor);
        System.out.println("Duración: " + duracion);
        System.out.println("-------------------------------------------");
    }

    // Getters y Setters
    public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }
    public void setContratista(String contratista) { this.contratista = contratista; }
    public void setEntidadContratante(String entidadContratante) { this.entidadContratante = entidadContratante; }
    public void setValor(double valor) { this.valor = valor; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
}
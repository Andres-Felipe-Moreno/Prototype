public class Factura implements Documento {
    private String numeroFactura;
    private String cliente;
    private String fecha;
    private double subtotal;
    private double impuestos;

    public Factura(String numeroFactura, String cliente, String fecha, double subtotal, double impuestos) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
    }

    // Constructor de copia para el patrón Prototype
    public Factura(Factura otra) {
        this.numeroFactura = otra.numeroFactura;
        this.cliente = otra.cliente;
        this.fecha = otra.fecha;
        this.subtotal = otra.subtotal;
        this.impuestos = otra.impuestos;
    }

    @Override
    public Documento clonar() {
        return new Factura(this);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("=== FACTURA ELECTRÓNICA ===");
        System.out.println("N° Factura: " + numeroFactura);
        System.out.println("Cliente: " + cliente);
        System.out.println("Fecha: " + fecha);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Impuestos: $" + impuestos);
        System.out.println("Total: $" + (subtotal + impuestos));
        System.out.println("---------------------------");
    }

    // Getters y Setters
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public void setImpuestos(double impuestos) { this.impuestos = impuestos; }
}
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static GestorPlantillas gestor = new GestorPlantillas();
    private static ArrayList<Documento> documentosGenerados = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarPlantillasIniciales();

        int opcion = 0;
        do {
            System.out.println("\n==========================================");
            System.out.println("          SISTEMA DE DOCUMENTOS");
            System.out.println("==========================================");
            System.out.println("1. Consultar plantillas disponibles");
            System.out.println("2. Clonar plantilla y personalizar");
            System.out.println("3. Consultar documentos generados");
            System.out.println("4. Registrar nueva plantilla");
            System.out.println("5. Eliminar plantilla o documento");
            System.out.println("6. Salir");

            opcion = pedirEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    gestor.mostrarPlantillas();
                    break;
                case 2:
                    clonarYPersonalizar();
                    break;
                case 3:
                    consultarDocumentos();
                    break;
                case 4:
                    registrarNuevaPlantilla();
                    break;
                case 5:
                    menuEliminar();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número del 1 al 6.");
            }
        } while (opcion != 6);
    }

    private static void cargarPlantillasIniciales() {
        gestor.agregarPlantilla(new Contrato("CNT-000", "Por definir", "Empresa X", 1000.0, "12 meses"));
        gestor.agregarPlantilla(new Factura("FAC-000", "Cliente Genérico", "01/01/2026", 100.0, 19.0));
        gestor.agregarPlantilla(new PropuestaComercial("PROP-000", "Cliente Prospecto", "Desarrollo de Software", 5000.0, "30 días"));
    }

    private static void clonarYPersonalizar() {
        if (gestor.getCantidadPlantillas() == 0) {
            System.out.println("No hay plantillas para clonar.");
            return;
        }

        gestor.mostrarPlantillas();
        int indice = pedirEntero("\nIngrese el número de opción de la plantilla a clonar: ");

        Documento copia = gestor.obtenerClon(indice);

        if (copia == null) {
            System.out.println("El número ingresado no corresponde a ninguna plantilla.");
            return;
        }

        System.out.println("\n--- PERSONALIZANDO EL DOCUMENTO CLONADO ---");

        if (copia instanceof Contrato) {
            Contrato c = (Contrato) copia;
            System.out.print("Nuevo número de contrato: ");
            c.setNumeroContrato(scanner.nextLine());
            System.out.print("Nombre del contratista: ");
            c.setContratista(scanner.nextLine());
            System.out.print("Entidad contratante: ");
            c.setEntidadContratante(scanner.nextLine());
            c.setValor(pedirDouble("Nuevo valor: "));
            System.out.print("Nueva duración: ");
            c.setDuracion(scanner.nextLine());
        } else if (copia instanceof Factura) {
            Factura f = (Factura) copia;
            System.out.print("Nuevo número de factura: ");
            f.setNumeroFactura(scanner.nextLine());
            System.out.print("Nombre del cliente: ");
            f.setCliente(scanner.nextLine());
            System.out.print("Nueva fecha: ");
            f.setFecha(scanner.nextLine());
            f.setSubtotal(pedirDouble("Nuevo subtotal: "));
            f.setImpuestos(pedirDouble("Nuevos impuestos: "));
        } else if (copia instanceof PropuestaComercial) {
            PropuestaComercial p = (PropuestaComercial) copia;
            System.out.print("Nuevo número de propuesta: ");
            p.setNumeroPropuesta(scanner.nextLine());
            System.out.print("Nombre del cliente: ");
            p.setCliente(scanner.nextLine());
            System.out.print("Descripción del servicio: ");
            p.setDescripcionServicio(scanner.nextLine());
            p.setValor(pedirDouble("Nuevo valor: "));
            System.out.print("Nueva vigencia: ");
            p.setVigencia(scanner.nextLine());
        }

        documentosGenerados.add(copia);
        System.out.println("\nDocumento clonado y modificado exitosamente.");
    }

    private static void consultarDocumentos() {
        if (documentosGenerados.isEmpty()) {
            System.out.println("\nNo hay documentos generados todavía.");
            return;
        }
        System.out.println("\n==========================================");
        System.out.println("     DOCUMENTOS GENERADOS EN MEMORIA");
        System.out.println("==========================================");
        for (int i = 0; i < documentosGenerados.size(); i++) {
            System.out.println("Copia #" + (i + 1) + " (Índice [" + i + "]):");
            documentosGenerados.get(i).mostrarInformacion();
        }
    }

    private static void registrarNuevaPlantilla() {
        System.out.println("\nSeleccione el tipo de plantilla a registrar:");
        System.out.println("1. Contrato");
        System.out.println("2. Factura");
        System.out.println("3. Propuesta Comercial");
        int tipo = pedirEntero("Opción: ");

        switch (tipo) {
            case 1:
                System.out.println("\n--- DATOS DE LA NUEVA PLANTILLA DE CONTRATO ---");
                System.out.print("Número de contrato: ");
                String numContrato = scanner.nextLine();
                System.out.print("Contratista base: ");
                String contratista = scanner.nextLine();
                System.out.print("Entidad contratante base: ");
                String entidad = scanner.nextLine();
                double valorContrato = pedirDouble("Valor base del contrato: ");
                System.out.print("Duración base: ");
                String duracion = scanner.nextLine();

                gestor.agregarPlantilla(new Contrato(numContrato, contratista, entidad, valorContrato, duracion));
                System.out.println("Plantilla de Contrato registrada exitosamente.");
                break;

            case 2:
                System.out.println("\n--- DATOS DE LA NUEVA PLANTILLA DE FACTURA ---");
                System.out.print("Número de factura base: ");
                String numFactura = scanner.nextLine();
                System.out.print("Cliente base: ");
                String clienteFactura = scanner.nextLine();
                System.out.print("Fecha base (dd/mm/aaaa): ");
                String fecha = scanner.nextLine();
                double subtotal = pedirDouble("Subtotal base: ");
                double impuestos = pedirDouble("Impuestos base: ");

                gestor.agregarPlantilla(new Factura(numFactura, clienteFactura, fecha, subtotal, impuestos));
                System.out.println("Plantilla de Factura registrada exitosamente.");
                break;

            case 3:
                System.out.println("\n--- DATOS DE LA NUEVA PLANTILLA DE PROPUESTA ---");
                System.out.print("Número de propuesta base: ");
                String numPropuesta = scanner.nextLine();
                System.out.print("Cliente base: ");
                String clientePropuesta = scanner.nextLine();
                System.out.print("Descripción del servicio base: ");
                String descripcion = scanner.nextLine();
                double valorPropuesta = pedirDouble("Valor base: ");
                System.out.print("Vigencia base: ");
                String vigencia = scanner.nextLine();

                gestor.agregarPlantilla(new PropuestaComercial(numPropuesta, clientePropuesta, descripcion, valorPropuesta, vigencia));
                System.out.println("Plantilla de Propuesta Comercial registrada exitosamente.");
                break;

            default:
                System.out.println("Tipo de plantilla no válido.");
        }
    }

    // --- MÉTODOS PARA ELIMINAR ELEMENTOS ---

    private static void menuEliminar() {
        System.out.println("\n--- ELIMINAR REGISTROS ---");
        System.out.println("1. Eliminar una plantilla base");
        System.out.println("2. Eliminar un documento generado (copia)");
        System.out.println("3. Volver al menú principal");

        int subOpcion = pedirEntero("Seleccione una opción: ");

        switch (subOpcion) {
            case 1:
                eliminarPlantilla();
                break;
            case 2:
                eliminarDocumentoGenerado();
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void eliminarPlantilla() {
        if (gestor.getCantidadPlantillas() == 0) {
            System.out.println("No hay plantillas para eliminar.");
            return;
        }

        gestor.mostrarPlantillas();
        int pos = pedirEntero("\nIngrese la opción de la plantilla a eliminar: ");

        if (gestor.eliminarPlantilla(pos)) {
            System.out.println("Plantilla eliminada exitosamente.");
        } else {
            System.out.println("Índice inválido. No se pudo eliminar.");
        }
    }

    private static void eliminarDocumentoGenerado() {
        if (documentosGenerados.isEmpty()) {
            System.out.println("No hay documentos generados para eliminar.");
            return;
        }

        consultarDocumentos();
        int pos = pedirEntero("\nIngrese el índice del documento generado a eliminar: ");

        if (pos >= 0 && pos < documentosGenerados.size()) {
            documentosGenerados.remove(pos);
            System.out.println("Documento generado eliminado exitosamente.");
        } else {
            System.out.println("Índice inválido. No se pudo eliminar.");
        }
    }

    // --- MÉTODOS DE CONTROL DE ERRORES DE ENTRADA ---

    private static int pedirEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número entero (ej: 1, 2, 3). Intente de nuevo.");
            }
        }
    }

    private static double pedirDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número decimal (ej: 1500.50). Intente de nuevo.");
            }
        }
    }
}
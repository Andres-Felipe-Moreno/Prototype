import java.util.ArrayList;

public class GestorPlantillas {
    private ArrayList<Documento> plantillas = new ArrayList<>();

    public void agregarPlantilla(Documento doc) {
        plantillas.add(doc);
    }

    public void mostrarPlantillas() {
        if (plantillas.isEmpty()) {
            System.out.println("No hay plantillas registradas.");
            return;
        }
        System.out.println("\n--- PLANTILLAS REGISTRADAS ---");
        for (int i = 0; i < plantillas.size(); i++) {
            System.out.println("Opción [" + i + "]:");
            plantillas.get(i).mostrarInformacion();
        }
    }

    public Documento obtenerClon(int indice) {
        if (indice >= 0 && indice < plantillas.size()) {
            return plantillas.get(indice).clonar();
        }
        return null;
    }

    // Método para eliminar plantilla por índice
    public boolean eliminarPlantilla(int indice) {
        if (indice >= 0 && indice < plantillas.size()) {
            plantillas.remove(indice);
            return true;
        }
        return false;
    }

    public int getCantidadPlantillas() {
        return plantillas.size();
    }
}
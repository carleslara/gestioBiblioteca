package gestioBiblioteca;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Llibre> llibres;
    
    public Biblioteca() {
        this.llibres = new ArrayList<>();
    }
    
    public void afegirLlibre(Llibre llibre) { 
        llibres.add(llibre); 
    }
    
    public void eliminarLlibre(Llibre llibre) { 
        llibres.remove(llibre); 
    }
    
    public Llibre buscarLlibre(String titol) {
        for (Llibre llibre : llibres) {
            if (llibre.getTitol().equalsIgnoreCase(titol)) {
                return llibre;
            }
        }
        return null;
    }
    
    public Llibre buscarLlibreSenseAccents(String titol) {
        String titolSenseAccent = removeAccents(titol);
        for (Llibre llibre : llibres) {
            if (removeAccents(llibre.getTitol()).equalsIgnoreCase(titolSenseAccent)) {
                return llibre;
            }
        }
        return null;
    }
    
    private String removeAccents(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                         .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    public List<Llibre> getLlibres() { 
        return llibres; 
    }
}
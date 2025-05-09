package gestioBiblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public class GestorBiblioteca {
    private List<Prestec> prestecs;
    private static final int MAX_PRESTECS = 3;
    
    public GestorBiblioteca() {
        this.prestecs = new ArrayList<>();
    }
    
    public void prestarLlibre(Usuari usuari, Llibre llibre) {
        if (usuari.getLlibresPrestats().size() >= MAX_PRESTECS) {
            System.out.println(usuari.getNom() + " ha arribat al màxim de llibres prestats.");
            return;
        }
        
        if (llibre.esPrestat()) {
            System.out.println("Aquest llibre ja està prestat.");
            return;
        }
        
        llibre.prestar();
        Prestec prestec = new Prestec(usuari, llibre, LocalDate.now());
        prestecs.add(prestec);
        usuari.afegirLlibre(llibre);
        System.out.println(usuari.getNom() + " ha agafat el llibre: " + llibre.getTitol());
    }
    
    public void retornarLlibre(Usuari usuari, Llibre llibre) {
        if (!usuari.getLlibresPrestats().contains(llibre)) {
            System.out.println(usuari.getNom() + " no té aquest llibre per retornar.");
            return;
        }
        llibre.retornar();
        usuari.retornarLlibre(llibre);
        System.out.println(usuari.getNom() + " ha retornat el llibre: " + llibre.getTitol());
    }
    
    public List<Prestec> consultarHistorial(Usuari usuari) {
        List<Prestec> historial = new ArrayList<>();
        for (Prestec p : prestecs) {
            if (p.getUsuari().equals(usuari)) {
                historial.add(p);
            }
        }
        return historial;
    }
    
    public void mostrarEstadistiques() {
        Map<String, Integer> comptadorLlibres = new HashMap<>();
        Map<String, Integer> comptadorUsuaris = new HashMap<>();
        Map<String, Integer> comptadorCategories = new HashMap<>();
        
        for (Prestec p : prestecs) {
            String llibre = p.getLlibre().getTitol();
            comptadorLlibres.put(llibre, comptadorLlibres.getOrDefault(llibre, 0) + 1);
            
            String userNom = p.getUsuari().getNom();
            comptadorUsuaris.put(userNom, comptadorUsuaris.getOrDefault(userNom, 0) + 1);
            
            String categoria = p.getLlibre().getCategoria();
            comptadorCategories.put(categoria, comptadorCategories.getOrDefault(categoria, 0) + 1);
        }
        
        System.out.println("Estadístiques dels préstecs:");
        System.out.println("\nLlibres més prestats:");
        comptadorLlibres.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " préstecs"));
        
        System.out.println("\nLectors més actius:");
        comptadorUsuaris.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " préstecs"));
        
        System.out.println("\nPréstecs per categoria:");
        comptadorCategories.forEach((categoria, count) -> 
            System.out.println(categoria + ": " + count + " préstecs"));
    }
}

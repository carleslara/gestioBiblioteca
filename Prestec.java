package gestioBiblioteca2;

import java.time.LocalDate;

public class Prestec {
    private Usuari usuari;
    private Llibre llibre;
    private LocalDate dataPrestec;
    private LocalDate dataRetorn;
    
    public Prestec(Usuari usuari, Llibre llibre, LocalDate dataPrestec) {
        this.usuari = usuari;
        this.llibre = llibre;
        this.dataPrestec = dataPrestec;
        // Data de retorn esperada: 2 setmanes després
        this.dataRetorn = dataPrestec.plusWeeks(2);
    }
    
    public Usuari getUsuari() { 
        return usuari; 
    }
    
    public Llibre getLlibre() { 
        return llibre; 
    }
    
    public LocalDate getDataPrestec() { 
        return dataPrestec; 
    }
    
    public LocalDate getDataRetorn() { 
        return dataRetorn; 
    }
    
    @Override
    public String toString() {
        return "Prestec: " + llibre.getTitol() + " prestat a " + usuari.getNom() + 
               " el " + dataPrestec + ", a retornar abans de " + dataRetorn;
    }
}
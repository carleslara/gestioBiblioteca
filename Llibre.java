package gestioBiblioteca;

public class Llibre {
    private String titol;
    private String autor;
    private boolean prestat;
    private String categoria;
    
    public Llibre(String titol, String autor, String categoria) {
        this.titol = titol;
        this.autor = autor;
        this.categoria = categoria;
        this.prestat = false;
    }
    
    public String getTitol() { 
        return titol; 
    }
    
    public String getAutor() { 
        return autor; 
    }
    
    public boolean esPrestat() { 
        return prestat; 
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public void prestar() { 
        prestat = true; 
    }
    
    public void retornar() { 
        prestat = false; 
    }
    
    @Override
    public String toString() {
        return titol + " de " + autor + " (Categoria: " + categoria + ") " + 
               (prestat ? "(En préstec)" : "(Disponible)");
    }
}

package gestioBiblioteca;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Llibre llibre1 = new Llibre("1984", "George Orwell", "Novel·la");
        Llibre llibre2 = new Llibre("El petit príncep", "Antoine de Saint-Exupéry", "Novel·la");
        Llibre llibre3 = new Llibre("La història de la Humanitat", "Will Durant", "Història");
        
        biblioteca.afegirLlibre(llibre1);
        biblioteca.afegirLlibre(llibre2);
        biblioteca.afegirLlibre(llibre3);
        
        Usuari usuari1 = new Usuari("Carla");
        Usuari usuari2 = new Usuari("Joan");
        
        GestorBiblioteca gestor = new GestorBiblioteca();
        
        Scanner scanner = new Scanner(System.in);
        int opcio;
        do {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Prestar llibre");
            System.out.println("2. Retornar llibre");
            System.out.println("3. Consultar historial d'un usuari");
            System.out.println("4. Buscar llibre (tenint en compte accents)");
            System.out.println("5. Buscar llibre (sense tenir en compte accents)");
            System.out.println("6. Mostrar tots els llibres de la biblioteca");
            System.out.println("7. Mostrar estadístiques de préstecs");
            System.out.println("0. Sortir");
            System.out.print("Selecciona una opció: ");
            
            opcio = Integer.parseInt(scanner.nextLine());
            
            switch(opcio) {
                case 1:
                    System.out.print("Nom de l'usuari: ");
                    String nomUsuari = scanner.nextLine();
                    Usuari usuari = nomUsuari.equalsIgnoreCase("Carla") ? usuari1 : usuari2;
                    System.out.print("Títol del llibre: ");
                    String titol = scanner.nextLine();
                    Llibre llibre = biblioteca.buscarLlibre(titol);
                    if (llibre == null) {
                        System.out.println("No s'ha trobat el llibre amb aquest títol.");
                    } else {
                        gestor.prestarLlibre(usuari, llibre);
                    }
                    break;
                case 2:
                    System.out.print("Nom de l'usuari: ");
                    nomUsuari = scanner.nextLine();
                    usuari = nomUsuari.equalsIgnoreCase("Carla") ? usuari1 : usuari2;
                    System.out.print("Títol del llibre a retornar: ");
                    titol = scanner.nextLine();
                    llibre = biblioteca.buscarLlibre(titol);
                    if (llibre == null) {
                        System.out.println("No s'ha trobat el llibre amb aquest títol.");
                    } else {
                        gestor.retornarLlibre(usuari, llibre);
                    }
                    break;
                case 3:
                    System.out.print("Nom de l'usuari: ");
                    nomUsuari = scanner.nextLine();
                    usuari = nomUsuari.equalsIgnoreCase("Carla") ? usuari1 : usuari2;
                    List<Prestec> historial = gestor.consultarHistorial(usuari);
                    if (historial.isEmpty()) {
                        System.out.println("No hi ha historial de préstecs per a " + usuari.getNom());
                    } else {
                        System.out.println("Historial de préstecs per a " + usuari.getNom() + ":");
                        for (Prestec p : historial) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Introdueix el títol del llibre: ");
                    titol = scanner.nextLine();
                    llibre = biblioteca.buscarLlibre(titol);
                    if (llibre != null) {
                        System.out.println("Llibre trobat: " + llibre);
                    } else {
                        System.out.println("No s'ha trobat cap llibre amb aquest títol.");
                    }
                    break;
                case 5:
                    System.out.print("Introdueix el títol del llibre (sense tenir en compte accents): ");
                    titol = scanner.nextLine();
                    llibre = biblioteca.buscarLlibreSenseAccents(titol);
                    if (llibre != null) {
                        System.out.println("Llibre trobat: " + llibre);
                    } else {
                        System.out.println("No s'ha trobat cap llibre amb aquest títol (sense accents).");
                    }
                    break;
                case 6:
                    System.out.println("Llibres de la biblioteca:");
                    for (Llibre lib : biblioteca.getLlibres()) {
                        System.out.println(lib);
                    }
                    break;
                case 7:
                    gestor.mostrarEstadistiques();
                    break;
                case 0:
                    System.out.println("Sortint del sistema...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
                    break;
            }
        } while (opcio != 0);
        
        scanner.close();
    }
}

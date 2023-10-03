import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjetosFicheroCittMauro {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner teclat = new Scanner(System.in);
        boolean condi = true;

        ObjetosFicheroCittMauro obj = new ObjetosFicheroCittMauro();
        File fichero = new File("/home/alumne/Escriptori/ficheroCoche/coche.txt");

        while (condi) {
            int opcion = obj.menu();

            switch (opcion) {
                case 1:
                    System.out.println("Si presionas esto, el archivo se reiniciará con valores por defecto. ¿Seguro que quieres guardar objetos?[S/N]");
                    char respuestaCaso1 = teclat.next().toLowerCase().charAt(0);
                    if (respuestaCaso1 == 's'){
                        obj.crearObjetos(fichero);
                    } else if(respuestaCaso1 == 'n') {
                        System.out.println("Operación cancelada");
                    } else {
                        System.out.println("Ingrese una respuesta válida");
                    }

                    break;
                case 2:
                    obj.leerObjetos(fichero);
                    break;
                case 3:
                    System.out.println("¿Qué marca desea buscar?");
                    String marca = teclat.next();
                    obj.buscarObjetos(fichero, marca);
                    break;
                case 4:
                    boolean condiCrear = obj.crearCocheYGuardar(fichero);
                    if (condiCrear){
                        System.out.println("El coche ha sido creado.");
                    }
                    break;
                  case 5:
                      System.out.println("Adiós");
                      condi = false;
                      break;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        }
    }

    public void crearObjetos(File fichero) throws IOException {
        Coche coche;

        String[] marcas = {"Toyota", "Honda", "Ford", "Chevrolet", "Toyota", "Nissan", "Volkswagen", "BMW", "Mercedes-Benz"};
        String[] modelos = {"Corolla", "Civic", "Focus", "Cruze", "Camry", "Altima", "Jetta", "X5", "C-Class"};
        int[] anios = {2020, 2019, 2021, 2018, 2020, 2022, 2023, 2017, 2016};
        String[] matriculas = {"ABC123", "DEF456", "GHI789", "JKL012", "MNO345", "PQR678", "STU901", "VWX234", "YZA567"};

        List<Coche> coches = new ArrayList<>();

        for (int i = 0; i < marcas.length; i++) {
            coche = new Coche(marcas[i], modelos[i], anios[i], matriculas[i]);
            coches.add(coche);
        }

        ObjectOutputStream DataOutCoche = new ObjectOutputStream(new FileOutputStream(fichero));
        DataOutCoche.writeObject(coches);
        DataOutCoche.close();
    }

    public void leerObjetos(File fichero) throws IOException, ClassNotFoundException {
        FileInputStream ficheroIn = new FileInputStream(fichero);
        ObjectInputStream DataInCoche = new ObjectInputStream(ficheroIn);

        try {
            List<Coche> coches = (List<Coche>) DataInCoche.readObject();
            for (Coche coche : coches) {
                System.out.println("Marca: " + coche.getMarca() + "\nModelo: " + coche.getModelo() + "\nAño: " + coche.getYear() + "\nMatricula: " + coche.getMatricula() + "\n*****");
            }
        } catch (EOFException eo) {
            DataInCoche.close();
        }
    }

    public void buscarObjetos(File fichero, String marca) throws IOException, ClassNotFoundException {
        FileInputStream ficheroIn = new FileInputStream(fichero);
        ObjectInputStream DataInCoche = new ObjectInputStream(ficheroIn);

        try {
            List<Coche> coches = (List<Coche>) DataInCoche.readObject();
            boolean condi = false;

            for (Coche coche : coches) {
                if (coche.getMarca().equalsIgnoreCase(marca)) {
                    System.out.println("Marca: " + coche.getMarca() + "\nModelo: " + coche.getModelo() + "\nAño: " + coche.getYear() + "\nMatricula: " + coche.getMatricula() + "\n*****");
                    condi = true;
                }
            }

            if (!condi) {
                System.out.println("El coche no existe en el fichero");
            }
        } catch (EOFException eo) {
            DataInCoche.close();
        }
    }

    public int menu(){
        Scanner teclat = new Scanner(System.in);

        System.out.println("1 - Guardar objetos");
        System.out.println("2 - Imprimir objetos");
        System.out.println("3 - Buscar coches por marca");
        System.out.println("4 - Agregar un coche nuevo");
        System.out.println("5 - Salir");

        System.out.println("Seleccione la opción deseada: ");
        int opt = teclat.nextInt();

        return opt;
    }

    public boolean crearCocheYGuardar(File file) throws IOException, ClassNotFoundException {
        Scanner teclat = new Scanner(System.in);

        System.out.println("Ingrese la marca del coche: ");
        String marcaCoche = teclat.next();
        System.out.println("Ingrese el modelo: ");
        String modeloCoche = teclat.next();
        System.out.println("Ingrese el año del coche: ");
        int yearCoche = teclat.nextInt();
        System.out.println("Ingrese la matrícula: ");
        String matriculaCoche = teclat.next();

        Coche coche = new Coche(marcaCoche, modeloCoche, yearCoche, matriculaCoche);

        FileInputStream ficheroIn = new FileInputStream(file);
        ObjectInputStream DataInCoche = new ObjectInputStream(ficheroIn);

        List<Coche> coches;
        try {
            coches = (List<Coche>) DataInCoche.readObject();
        } catch (EOFException e) {
            coches = new ArrayList<>();
        }

        coches.add(coche);

        try {
        ObjectOutputStream DataOutCoche = new ObjectOutputStream(new FileOutputStream(file));
        DataOutCoche.writeObject(coches);
        DataOutCoche.close();
        return true;
        } catch (EOFException e) {
            return false;
        }
    }
}

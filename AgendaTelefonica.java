import java.util.Scanner;

// Clase para representar un contacto
class Contacto {
    String nombre;
    String telefono;

    // Constructor para inicializar un contacto con un nombre y teléfono
    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }
}

// Clase principal para gestionar la agenda de teléfonos
public class AgendaTelefonica {
    private static final int MAX_CONTACTOS = 10; // Número máximo de contactos en la agenda
    private Contacto[] contactos; // Arreglo para almacenar los contactos
    private int cantidadContactos; // Contador para llevar la cuenta de los contactos almacenados
    private Scanner scanner; // Objeto Scanner para leer la entrada del usuario

    // Constructor de la clase AgendaTelefonica
    public AgendaTelefonica() {
        contactos = new Contacto[MAX_CONTACTOS]; // Inicializa el arreglo de contactos
        cantidadContactos = 0; // Inicializa la cantidad de contactos a cero
        scanner = new Scanner(System.in); // Inicializa el objeto Scanner para leer la entrada del usuario
    }

// Método para agregar un contacto a la agenda
public void agregarContacto(String nombre, String telefono) {
    // Verifica si ya existe un contacto con el mismo nombre
    boolean encontrado = buscarContactoPorNombre(nombre);
    if (encontrado) {
        System.out.println("¡Advertencia! Ya existe un contacto con ese nombre en la agenda.");
        scanner.nextLine(); // Consumir el salto de línea
    }    
    if (cantidadContactos < MAX_CONTACTOS) {
        contactos[cantidadContactos++] = new Contacto(nombre, telefono);
        System.out.println("Contacto agregado correctamente.");
    } else {
        System.out.println("La agenda está llena, no se puede agregar más contactos.");
    }
}


    // Método para buscar contactos por nombre
    public boolean buscarContactoPorNombre(String nombre) {
        boolean encontrado = false;
        System.out.println("Resultados de la búsqueda:");
        // Recorre el arreglo de contactos y compara el nombre con el nombre buscado
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].nombre.equalsIgnoreCase(nombre)) {
                encontrado = true;
                System.out.println("Nombre: " + contactos[i].nombre + ", Teléfono: " + contactos[i].telefono);
            }
        }
        // Si no se encuentra ningún contacto con el nombre buscado, muestra un mensaje
        if (!encontrado) {
            System.out.println("No se encontraron contactos con ese nombre.");
        }
        return encontrado; // Devuelve true si se encontró al menos un contacto con el nombre buscado
    }

    // Método para modificar los datos de un contacto
    public void modificarContacto(String nombre, String nuevoTelefono) {
        boolean encontrado = false;
        // Recorre el arreglo de contactos y busca el contacto por nombre
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].nombre.equalsIgnoreCase(nombre)) {
                encontrado = true;
                // Modifica el teléfono del contacto encontrado
                contactos[i].telefono = nuevoTelefono;
                System.out.println("Los datos del contacto han sido modificados correctamente.");
                break;
            }
        }
        // Si no se encontró ningún contacto con el nombre especificado, muestra un mensaje
        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
        }
    }

    // Método para eliminar un contacto de la agenda
    public void eliminarContacto(String nombre) {
        boolean encontrado = false;
        // Recorre el arreglo de contactos y busca el contacto por nombre
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].nombre.equalsIgnoreCase(nombre)) {
                encontrado = true;
                // Elimina el contacto moviendo los elementos restantes hacia la izquierda
                for (int j = i; j < cantidadContactos - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                cantidadContactos--; // Reduce la cantidad de contactos en la agenda
                System.out.println("El contacto ha sido eliminado correctamente.");
                break;
            }
        }
        // Si no se encontró ningún contacto con el nombre especificado, muestra un mensaje
        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
        }
    }

    // Método para mostrar todos los contactos en la agenda
    public void mostrarContactos() {
        // Si la agenda está vacía, muestra un mensaje
        if (cantidadContactos == 0) {
            System.out.println("La agenda está vacía.");
        } else {
            System.out.println("Lista de contactos:");
            // Recorre el arreglo de contactos y muestra el nombre y teléfono de cada contacto
            for (int i = 0; i < cantidadContactos; i++) {
                System.out.println("Nombre: " + contactos[i].nombre + ", Teléfono: " + contactos[i].telefono);
            }
        }
    }

    // Método para vaciar todos los contactos de la agenda
    public void vaciarAgenda() {
        // Pide confirmación al usuario antes de vaciar la agenda
        System.out.println("¿Está seguro de que desea vaciar la agenda? (s/n)");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            // Crea un nuevo arreglo de contactos y restablece la cantidad de contactos a cero
            contactos = new Contacto[MAX_CONTACTOS];
            cantidadContactos = 0;
            System.out.println("La agenda ha sido vaciada correctamente.");
        } else {
            System.out.println("Operación cancelada. La agenda no ha sido modificada.");
        }
    }

    // Método principal que ejecuta el programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgendaTelefonica agenda = new AgendaTelefonica();

        int opcion;
        // Ciclo principal que muestra el menú y procesa las opciones del usuario
        do {
            System.out.println("\nMENU");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Buscar contacto por nombre");
            System.out.println("4. Modificar contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Vaciar agenda");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese nombre del contacto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese teléfono del contacto: ");
                    String telefono = scanner.nextLine();
                    agenda.agregarContacto(nombre, telefono);
                    break;
                case 2:
                    agenda.mostrarContactos();
                    break;
                case 3:
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese nombre del contacto a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    agenda.buscarContactoPorNombre(nombreBuscar);
                    break;
                case 4:
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese nombre del contacto a modificar: ");
                    String nombreModificar = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono: ");
                    String nuevoTelefono = scanner.nextLine();
                    agenda.modificarContacto(nombreModificar, nuevoTelefono);
                    break;
                case 5:
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese nombre del contacto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    agenda.eliminarContacto(nombreEliminar);
                    break;
                case 6:
                    agenda.vaciarAgenda();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 7); // El ciclo se ejecuta hasta que el usuario elija salir

        scanner.close(); // Cierra el objeto Scanner al finalizar el programa
    }
}

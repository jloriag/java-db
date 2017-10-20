package clases;

import java.sql.ResultSet;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws Exception {
        Gestor gestor;
        gestor = Gestor.getInstance();

        Scanner scan = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("1) Agregar Curso");
            System.out.println("2) Mostrar Curso");
            System.out.println("3) Salir");
            opcion = scan.nextInt();
            scan.nextLine();
            if (opcion == 1) {
                System.out.println("Digite el nombre del curso");
                gestor.trans("INSERT INTO curso (descripcion, estado) VALUES ('" + scan.nextLine() + "', b'1');");
            } else if (opcion == 2) {
                System.out.println("Mostrando informacion de cursos");
                ResultSet datos = gestor.query("SELECT * FROM curso;");
                while (datos.next()) {
                    System.out.print(datos.getString("id"));
                    System.out.print(" ");
                    System.out.print(datos.getString("descripcion"));
                    System.out.print(" ");
                    System.out.print(datos.getString("estado"));
                    System.out.println("");
                }
            }
        } while (opcion != 3);

    }

}

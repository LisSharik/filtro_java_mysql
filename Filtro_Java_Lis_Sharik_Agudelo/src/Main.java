import controller.ClienteController;
import controller.CompraController;
import controller.ProductoController;
import controller.TiendaController;
import database.Configdb;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args){

        TiendaController objTiendaController = new TiendaController();
        ClienteController objClienteController = new ClienteController();
        ProductoController objProductoController = new ProductoController();
        CompraController objCompraController = new CompraController();

        boolean flag = true;

        while (flag){
            String opcion = JOptionPane.showInputDialog("""
                    =============== Menu ===============                 
                    1. Administrador Clientes
                    2. Ver tiendas
                    3. Administrador Productos
                    4. Administrador Compras
                    5. Salir
                  
                    """);


            switch (opcion){
                case "1":
                    objClienteController.menu();
                    break;

                case "2":
                    objTiendaController.getAll();
                    break;

                case "3":
                    objProductoController.menu();
                    break;

                case "4":
                    objCompraController.menu();
                    break;

                case "5":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null,"Cancelado");
                    flag = false;
                    break;


                default:
                    JOptionPane.showMessageDialog(null,"Opcion no valida");
                    break;
            }



        }

    }


}

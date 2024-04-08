package controller;

import entity.Cliente;
import entity.Producto;
import entity.Tienda;
import model.ClienteModel;
import model.ProductoModel;

import model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class ProductoController {
    ProductoModel objProductoModel = new ProductoModel();
    TiendaController objTiendaController= new TiendaController();

    public ProductoController() {
        this.objProductoModel = new ProductoModel();
    }
    public static ProductoModel instanceModel(){
        return new ProductoModel();
    }

    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }


    public String getAll(List listObject){
        String list = " ============ Lista Productos ============\n";
        for(Object obj: listObject){
            Producto objProducto = (Producto) obj;
            list += objProducto.toString() +  "\n";
        }

        return list;
    }


    public void insert(){
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del producto");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el precio del producto"));
        Object[] options = objTiendaController.instanceModel().findAll().toArray();
        Tienda objSelected = (Tienda) JOptionPane.showInputDialog(
                null,
                "Selecciona una tienda",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el stock del producto"));

        instanceModel().insert(new Producto(nombre,precio,stock,objSelected.getIdTienda(),objSelected));

    }

    public void delete(){
        Object[] options = instanceModel().findAll().toArray();
        Producto objSelected = (Producto) JOptionPane.showInputDialog(
                null,
                "Selecciona un producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objSelected);

    }

    public void update(){
        Object[] options = instanceModel().findAll().toArray();
        Producto objSelected = (Producto) JOptionPane.showInputDialog(
                null,
                "Selecciona un producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSelected.setNombre(JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre" , objSelected.getNombre()));
        objSelected.setPrecio(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el nuevo precio" , objSelected.getPrecio())));

        Object[] optionsTiendas = objTiendaController.instanceModel().findAll().toArray();
        Tienda objTienda = (Tienda) JOptionPane.showInputDialog(
                null,
                "Selecciona una tienda",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsTiendas,
                optionsTiendas[0]
        );

        objSelected.setId_tienda(objTienda.getIdTienda());
        objSelected.setObjTienda(objTienda);

        objSelected.setStock(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el nuevo stock" , objSelected.getStock())));

        instanceModel().update(objSelected);
    }

    public void foundByTienda(){
        String search = JOptionPane.showInputDialog("Ingresa el nombre la tienda que quieres buscar sus productos");
        JOptionPane.showMessageDialog(null,getAll(instanceModel().findByTienda(search)));
    }

    public void foundByNombre(){
        String search = JOptionPane.showInputDialog("Ingresa el nombre del producto a buscar");
        JOptionPane.showMessageDialog(null,getAll(instanceModel().findByNombre(search)));
    }
    public void menu(){
        boolean flag = true;

        while (flag){
            String opcion = JOptionPane.showInputDialog("""
                    =============== Administrador Productos ===============
                    1. Lista de Productos
                    2. Agregar un Producto
                    3. Actualizar un Producto
                    4. Eliminar un Producto
                    5. Buscar por productos de una tienda
                    6. Buscar por porducto
                    7. Salir
                  
                    """);


            switch (opcion){
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.insert();
                    break;

                case "3":
                    this.update();
                    break;

                case "4":
                    this.delete();
                    break;

                case "5":
                    this.foundByTienda();
                    break;

                case "6":
                    this.foundByNombre();
                    break;

                case "7":
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

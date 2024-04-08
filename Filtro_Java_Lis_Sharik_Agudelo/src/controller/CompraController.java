package controller;

import entity.Cliente;
import entity.Compra;
import entity.Producto;
import entity.Tienda;
import model.CompraModel;
import model.ProductoModel;

import javax.swing.*;
import java.util.List;

public class CompraController {
    CompraModel objCompraModel = new CompraModel();
    TiendaController objTiendaController= new TiendaController();
    ProductoController objProductoController= new ProductoController();
    ClienteController objClienteController= new ClienteController();

    public CompraController() {
        this.objCompraModel = new CompraModel();
    }

    public static CompraModel instanceModel(){
        return new CompraModel();
    }
    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }


    public String getAll(List listObject){
        String list = " ============ Lista Compras ============\n";
        for(Object obj: listObject){
            Compra objCompra = (Compra) obj;
            list += objCompra.toString() +  "\n___________________________________________________________\n";
        }

        return list;
    }

    public void insert(){
        Object[] optionsClientes = objClienteController.instanceModel().findAll().toArray();
        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(
                null,
                "Selecciona un cliente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsClientes,
                optionsClientes[0]
        );


        Object[] optionsProductos = ProductoController.instanceModel().findAll().toArray();
        Producto objProducto = (Producto) JOptionPane.showInputDialog(
                null,
                "Selecciona un producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsProductos,
                optionsProductos[0]
        );
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad del producto"));
        Compra newCompra = new Compra(cantidad,objCliente.getIdCliente(), objCliente, objProducto.getIdProducto(),objProducto);


        if (instanceModel().validateStock(newCompra)){
                objProducto.setStock(objProducto.getStock() - cantidad);
                instanceModel().insert(newCompra);
                objProductoController.instanceModel().update(objProducto);
                JOptionPane.showMessageDialog(null,newCompra.toString());


       }else {
           JOptionPane.showMessageDialog(null, "No fue posible realizar la compra");
       }



    }

    public void delete(){
        Object[] options = instanceModel().findAll().toArray();
        Compra objSelected = (Compra) JOptionPane.showInputDialog(
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
        Object[] optionsCompras = instanceModel().findAll().toArray();
        Compra objCompra = (Compra) JOptionPane.showInputDialog(
                null,
                "Selecciona un producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsCompras,
                optionsCompras[0]
        );

        Object[] optionsClientes = objClienteController.instanceModel().findAll().toArray();
        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(
                null,
                "Selecciona un cliente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsClientes,
                optionsClientes[0]
        );

        objCompra.setId_cliente(objCliente.getIdCliente());
        objCompra.setObjCliente(objCliente);

        Object[] optionsProductos = ProductoController.instanceModel().findAll().toArray();
        Producto objProducto = (Producto) JOptionPane.showInputDialog(
                null,
                "Selecciona un producto",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsProductos,
                optionsProductos[0]
        );

        objCompra.setId_producto(objProducto.getIdProducto());
        objCompra.setObjProducto(objProducto);

        objCompra.setCantidad(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la nueva cantidad", objCompra.getCantidad())));

        instanceModel().update(objCompra);

    }
    public void foundByProducto(){
        String search = JOptionPane.showInputDialog("Ingresa el producto de la compra");
        JOptionPane.showMessageDialog(null,getAll(instanceModel().findByProducto(search)));
    }


    public void menu(){
        boolean flag = true;

        while (flag){
            String opcion = JOptionPane.showInputDialog("""
                    =============== Administrador Compras ===============
                    1. Lista de Compras
                    2. Agregar una Compra
                    3. Actualizar una Compra
                    4. Eliminar una Compra
                    5. Buscar compras por producto
                    6. Salir
                  
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
                    this.foundByProducto();
                    break;

                case "6":
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

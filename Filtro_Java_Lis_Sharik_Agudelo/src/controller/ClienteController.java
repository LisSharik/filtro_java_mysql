package controller;

import entity.Cliente;
import model.ClienteModel;
import model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class ClienteController {
    ClienteModel objClienteModel = new ClienteModel();

    public ClienteController() {
        this.objClienteModel = new ClienteModel();
    }

    public static ClienteModel instanceModel(){
        return new ClienteModel();
    }


    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }


    public String getAll(List listObject){
        String list = " ============ Lista Clientes ============\n";
        for(Object obj: listObject){
            Cliente objCliente = (Cliente) obj;
            list += objCliente.toString() +  "\n";
        }

        return list;
    }

    public void insert(){
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del cliente");
        String apellido = JOptionPane.showInputDialog("Ingresa el apellido del cliente");
        String ubicacion = JOptionPane.showInputDialog("Ingresa el ubicacion del cliente");

        instanceModel().insert(new Cliente(nombre,apellido,ubicacion));

    }

    public void delete(){
        Object[] options = instanceModel().findAll().toArray();
        Cliente objSelected = (Cliente) JOptionPane.showInputDialog(
                null,
                "Selecciona un cliente",
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
        Cliente objSelected = (Cliente) JOptionPane.showInputDialog(
                null,
                "Selecciona un cliente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSelected.setNombre(JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre" , objSelected.getNombre()));
        objSelected.setApellido(JOptionPane.showInputDialog(null, "Ingresa el nuevo apellido" , objSelected.getApellido()));
        objSelected.setEmail(JOptionPane.showInputDialog(null, "Ingresa el nuevo email" , objSelected.getEmail()));

        instanceModel().update(objSelected);

    }

    public void foundByNombre(){
        String search = JOptionPane.showInputDialog("Ingresa el nombre del cliente a buscar");
        JOptionPane.showMessageDialog(null,getAll(instanceModel().findByNombre(search)));
    }
    public void menu(){
        boolean flag = true;

        while (flag){
            String opcion = JOptionPane.showInputDialog("""
                    =============== Administrador Clientes ===============
                    1. Lista de clientes
                    2. Agregar un cliente
                    3. Actualizar un cliente
                    4. Eliminar un cliente
                    5. Buscar por nombre
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
                    this.foundByNombre();
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

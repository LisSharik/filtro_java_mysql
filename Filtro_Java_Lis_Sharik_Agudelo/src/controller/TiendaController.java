package controller;

import entity.Cliente;
import entity.Tienda;
import model.ClienteModel;
import model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class TiendaController {
    TiendaModel objTiendaModel = new TiendaModel();

    public TiendaController(){
        this.objTiendaModel = new TiendaModel();
    }
    public static TiendaModel instanceModel(){
        return new TiendaModel();
    }

    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }


    public String getAll(List listObject){
        String list = " ============ Lista Tiendas ============\n";
        for(Object obj: listObject){
            Tienda objTienda = (Tienda) obj;
            list += objTienda.toString() +  "\n";
        }

        return list;
    }


//    public void menu(){
//        boolean flag = true;
//
//        while (flag){
//            String opcion = JOptionPane.showInputDialog("""
//                    =============== Administrador Tiendas ===============
//                    1. Lista de Tiendas
//                    2. Salir
//
//                    """);
//
//
//            switch (opcion){
//                case "1":
//                    this.getAll();
//                    break;
//
//                case "2":
//                    flag = false;
//                    break;
//
//                case null:
//                    JOptionPane.showMessageDialog(null,"Cancelado");
//                    flag = false;
//                    break;
//
//
//                default:
//                    JOptionPane.showMessageDialog(null,"Opcion no valida");
//                    break;
//            }
//
//
//
//        }
//
//
//    }
}

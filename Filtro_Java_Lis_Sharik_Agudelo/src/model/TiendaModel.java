package model;

import database.CRUD;
import database.Configdb;
import entity.Cliente;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TiendaModel {

    public List<Object> findAll() {
        ArrayList<Object> listTiendas = new ArrayList<>();
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "SELECT * FROM tienda ORDER BY tienda.id_tienda;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Tienda objTienda = new Tienda();
                objTienda.setIdTienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbucacion(objResult.getString("tienda.ubucacion"));

                listTiendas.add(objTienda);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return listTiendas;
    }
}

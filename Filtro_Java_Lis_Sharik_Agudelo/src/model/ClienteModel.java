package model;

import database.CRUD;
import database.Configdb;
import entity.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements CRUD {


    @Override
    public Object insert(Object object) {
        Connection objConnection = Configdb.openConnection();
        Cliente objCliente = (Cliente) object;

        try {
            String sql = "INSERT INTO cliente (nombre, apellido,email) VALUES (?, ?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCliente.setIdCliente(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Cliente agregado exitosamente");


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return objCliente;
    }

    @Override
    public boolean update(Object object) {
        Cliente objCliente = (Cliente) object;
        boolean isUpdated = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ? WHERE id_cliente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3,objCliente.getEmail());

            objPrepare.setInt(4, objCliente.getIdCliente());

            if (objPrepare.executeUpdate() > 0 ){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isUpdated;


    }

    @Override
    public boolean delete(Object object) {
        Cliente objCliente = (Cliente) object;
        boolean isDeleted = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "DELETE FROM cliente WHERE cliente.id_cliente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objCliente.getIdCliente());

            if(objPrepare.executeUpdate() > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "La eliminacion fue exitosa");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isDeleted;

    }

    @Override
    public List<Object> findAll() {
        ArrayList<Object> listClientes = new ArrayList<>();
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "SELECT * FROM cliente ORDER BY cliente.id_cliente;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cliente objCliente = new Cliente();
                objCliente.setIdCliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                listClientes.add(objCliente);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return listClientes;
    }

    public List<Object> findByNombre(String search) {
        ArrayList<Object> listClientes = new ArrayList<>();
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "SELECT * FROM cliente\n" +
                    "WHERE cliente.nombre LIKE '%"+ search +"%'\n" +
                    "ORDER BY cliente.id_cliente;\n";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cliente objCliente = new Cliente();
                objCliente.setIdCliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                listClientes.add(objCliente);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return listClientes;
    }
}

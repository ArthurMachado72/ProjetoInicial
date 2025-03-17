package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true",
                    "luck",
                    "0397154#_!54179330_#!WSX_yhn#!"
            );

            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco de dados: " + erro.getMessage());
        }

        return conn;
    }
}

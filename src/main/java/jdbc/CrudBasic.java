package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author clayton
 * CRUD Básico com JDBC
 * Faculdade de Tecnologia Senac Ponta Grossa
 * 2024-1
 */
public class CrudBasic {

    public static void create() {

        String nome = "Joabe";
        String telefone = "(11)91999-9999";
        String email = "joabe@gmail.com";
        LocalDate dataNasc = LocalDate.parse("2001-09-11");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", null)) {
            PreparedStatement insert;

            insert = conn.prepareStatement("INSERT INTO contato (nome,telefone,email,datanasc) VALUES (?,?,?,?)");
            insert.setString(1, nome);
            insert.setString(2, telefone);
            insert.setString(3, email);
            insert.setDate(4, java.sql.Date.valueOf(dataNasc));
            insert.executeUpdate();

            System.out.println("'Cadastro' realizado com sucesso!");

            conn.close();

        } catch (SQLException ex) {
            System.out.println("Erro na SQL");
        }
    }

    public static void readAll() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", null)) {
            ResultSet rsTelefones = conn.createStatement().executeQuery("SELECT * FROM contato");
            while (rsTelefones.next()) {
                System.out.println("Nome: " + rsTelefones.getString("nome"));
            }

        } catch (SQLException ex) {
            System.out.println("Erro na SQL");
        }
    }

    public static void update() {

        String nome = "Joana";
        String telefone = "(11)91999-9999";
        String email = "joana@gmail.com";
        LocalDate dataNasc = LocalDate.parse("2001-09-11");
        int id = 1;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", null)) {
            PreparedStatement update;

            update = conn.prepareStatement("UPDATE contato SET nome= ?, telefone= ?, email= ?, datanasc= ? WHERE id= ?");
            update.setString(1, nome);
            update.setString(2, telefone);
            update.setString(3, email);
            update.setDate(4, java.sql.Date.valueOf(dataNasc));
            update.setInt(5, id);
            update.executeUpdate();

            System.out.println("Cadastro 'atualizado' com sucesso!");

            conn.close();

        } catch (SQLException ex) {
            System.out.println("Erro na SQL");
        }

    }

    public static void delete() {

        int id = 1;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", null)) {
            PreparedStatement delete;

            delete = conn.prepareStatement("DELETE FROM contato WHERE id= ?");
            delete.setInt(1, id);
            delete.executeUpdate();

            System.out.println("Cadastro 'deletado' com sucesso!");

            conn.close();

        } catch (SQLException ex) {
            System.out.println("Erro na SQL");
        }

    }

    public static void main(String[] args) {
        
        create();
        
        readAll();
        
        update();
        
        delete();
    }
}

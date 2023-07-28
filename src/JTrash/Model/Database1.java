package JTrash.Model;

import java.sql.*;

public class Database1 {
    private Utente user;
    private static final String URL = "jdbc:mysql://localhost:3306/JTrash";
    private static final String USERNAME = "JTrash";
    private static final String PASSWORD = "JTrash";
    private Connection connection;

    public Database1(){

        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addData(Utente u){
        try{
            Statement stm = connection.createStatement();
            String query = "INSERT INTO JTrash (id, usernames, passwords, giocate, vinte, perse, livello) VALUES ('"+u.getId()+"', " +
                            "'"+u.getUsername()+"', '"+u.getPassword()+"', '"+u.getGiocate()+"', " +
                            "'"+u.getVinte()+"', '"+u.getPerse()+"', '"+u.getLivello()+"')";
            stm.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String retrieveUsername(String username){
       try{
           Statement stm = connection.createStatement();
           String query = "SELECT usernames FROM JTrash WHERE usernames = '"+username+"'";
           ResultSet rs = stm.executeQuery(query);
           if(rs.next()){
               return rs.getString("usernames");
           }
       }
         catch(SQLException e){
              e.printStackTrace();
         }
       return null;
    }

    public String retrievePassword(String password){
       try{
           Statement stm = connection.createStatement();
           String query = "SELECT passwords FROM JTrash WHERE passwords = '"+password+"'";
           ResultSet rs = stm.executeQuery(query);
           if(rs.next()){
               return rs.getString("passwords");
           }
       }
         catch(SQLException e){
              e.printStackTrace();
         }
       return null;
    }
}

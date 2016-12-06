import java.sql.*;
import java.util.LinkedList;

/**
 * Created by Marcel on 06.12.2016.
 */

public class DB{

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DB(){
        setConnection();
        setStatement();
    }

    public void setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/mlekston","mlekston","rxiKPw0T");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }  catch(Exception e){e.printStackTrace();}
    }

    public void setStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAll(){
        try {
            resultSet = statement.executeQuery("SELECT * FROM books");
            while(resultSet.next()){
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<ResultSet> search(String pattern){
        try {
            LinkedList<ResultSet> results = new LinkedList<>();
            resultSet = statement.executeQuery("SELECT * FROM books");
            while(resultSet.next()){
                if (resultSet.getString(1).equals(pattern) || resultSet.getString(3).equals(pattern)){
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        System.out.print(resultSet.getString(i) + " ");
                    }
                    System.out.println();
                    results.add(resultSet);
                }
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(String pattern){
        try {
            String argumentAuthor = "DELETE FROM books WHERE author = " + "'" + pattern + "'";
            String argumentIsbn = "DELETE FROM books WHERE isbn = " + "'" + pattern + "'";
            statement.execute(argumentAuthor);
            //statement.execute(argumentIsbn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
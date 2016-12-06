import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Marcel on 06.12.2016.
 */
public class Main {

    public static void main(String[] args) {
        DB db = new DB();
        db.delete("Marcel Proust");
    }
}
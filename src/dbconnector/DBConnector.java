import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        try {
            // appsettings.json laden
            String json = new String(Files.readAllBytes(Paths.get("appsettings.json")));
            JSONObject config = new JSONObject(json);

            dbUrl = config.getString("dbUrl");
            dbUser = config.getString("dbUser");
            dbPassword = config.getString("dbPassword");
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der Datenbankkonfiguration: " + e.getMessage());
        }
    }

    public static Connection connect() {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception e) {
            System.err.println("Datenbankverbindung fehlgeschlagen: " + e.getMessage());
            return null;
        }
    }
}

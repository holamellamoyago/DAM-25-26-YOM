package persistencia;

import java.sql.Connection;

public class EmpresaDAO2 {

    private Connection conn;

    public EmpresaDAO2(Connection conn) {
        this.conn = conn;
    }
}

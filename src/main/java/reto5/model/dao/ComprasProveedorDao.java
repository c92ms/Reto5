package reto5.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import reto5.model.vo.ComprasProveedorVo;
import reto5.utils.JDBCUtilities;


public class ComprasProveedorDao {

    public List<ComprasProveedorVo> listar() throws SQLException{
        List<ComprasProveedorVo> respuesta = new ArrayList<ComprasProveedorVo>();
        Connection conn = JDBCUtilities.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        String consulta = "SELECT ID_Compra, p.constructora, p.Banco_Vinculado FROM Compra c JOIN proyecto p ON c.ID_Proyecto = p.ID_Proyecto WHERE proveedor = 'Homecenter' AND p.Ciudad = 'Salento'";
        try{
            stm = conn.createStatement();
            rs = stm.executeQuery(consulta);
            while(rs.next()){
                ComprasProveedorVo vo = new ComprasProveedorVo();
                vo.setId(rs.getInt("id_compra"));
                vo.setConstructora(rs.getString("constructora"));
                vo.setBanco(rs.getString("banco_vinculado"));
                
                respuesta.add(vo);
            }
        }
        finally{
            if (rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(conn !=null){
                conn.close();
            }
        }
        return respuesta;
    }
    
}

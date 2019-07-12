

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDB {
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void jdbcprocess2(String sql) throws Exception{
        String url = "jdbc:mysql://localhost:3306/alimusic?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "131337";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement s=conn.createStatement();
        int i=s.executeUpdate(sql);
        if(i > 0){
            System.out.println("执行成功！");
        }else{
            System.out.println("执行失败");
        }
        conn.close();
    }
}

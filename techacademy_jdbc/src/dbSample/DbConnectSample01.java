package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DbConnectSample01 {

    public static void main(String[] args) {
        
        try {
        // 1. ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Select文の実行と結果を格納／代入
            String sql = "SELECT * FROM country LIMIT 50";
        
        
        try (
        // 2. DBと接続する
            Connection con = DriverManager.getConnection (
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "SHINSOTUno-jumko-1515"
               );

        // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
            Statement stmt = con.createStatement();
                
            ResultSet rs = stmt.executeQuery(sql);){

        // 6. 結果を表示する
            while( rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
        }   
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }
    }
}

package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.security.sasl.SaslException;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

public class DbConnectSample01 {

    public static void main(String[] args) {
        
        Connection con = null;
        Statement stmt = null;
        Resultset rs = null;
        
        try {
        // 1. ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
        

        // 2. DBと接続する
            Connection con = DriverManager.getConnection (
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "SHINSOTUno-jumko-1515"
               );

        // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
            stmt = con.createStatement();

        // 4, 5. Select文の実行と結果を格納／代入
            String sql = "SELECT * FROM country LIMIT 50";
            rs = stmt.executeQuery(sql);

        // 6. 結果を表示する
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに以上が発生しました。");
            e.printStackTrace();
        }finally {
            
        // 7. 接続を閉じる
            
        if ( rs != null) {
             try {
                 rs.close();
             } catch (SQLException e) {
                 System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                 e.printStackTrace();
             }
        }
            
        }
        if ( stmt != null) {
             try {
                 stmt.close();
             } catch (SQLException e) {
                 System.err.println("Statementを閉じるときにエラーが生じました。");
                 e.printStackTrace();
        }
            
        }
        if ( con != null) {
             try {
                 con.close();
             } catch (SQLException e) {
                 System.err.println("データベース切断時にエラーが出ました。");
                 e.printStackTrace();
             }
        }
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//insert插入测试
public class T03InsertStatement {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        Statement statement = null;
        //因为插入不会返回结果集，所以ResultSet不需要

        String url = "jdbc:mysql://localhost/test_db?user=root&&password=xuehuafeiwu&serverTimezone=GMT%2B8";
        String sql = "insert into Student values(3,'肖文斌','管理学院')";
        try {
            conn = DriverManager.getConnection(url);
            statement = conn.createStatement();
            statement.executeUpdate(sql);//insert update delete都是使用这个方法 查询使用executeQuary（）

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}


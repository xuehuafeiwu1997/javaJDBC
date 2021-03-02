import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//事务的例子
public class T06Transaction {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost/test_db?user=root&&password=xuehuafeiwu&serverTimezone=GMT%2B8";
        String sql1 = "update account_test set account_balance = 50 where account_name = '张三'";
        String sql2 = "update account_test set account_balance = 150 where account_name = '李四'";
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection(url);
            statement = conn.createStatement();
            //禁止自动提交
            conn.setAutoCommit(false);
            //如果sql2语句有错，sql1没错的话，sql1是一个事务，运行到这里，会提交到数据库中修改
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //出错的话就回滚
            try {
                conn.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

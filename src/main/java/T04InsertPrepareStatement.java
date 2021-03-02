import java.sql.*;

//将插入操作的代码中使用Statement替换为PreparedStatement
public class T04InsertPrepareStatement {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
//        Statement statement = null;
        PreparedStatement preparedStatement = null;
        //因为插入不会返回结果集，所以ResultSet不需要

        String url = "jdbc:mysql://localhost/test_db?user=root&&password=xuehuafeiwu&serverTimezone=GMT%2B8";
        String sql = "insert into Student values(?,?,?)";
        try {
            conn = DriverManager.getConnection(url);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,4);
            preparedStatement.setString(2,"左泽宇");
            preparedStatement.setString(3,"管理学院");
            preparedStatement.executeUpdate();//insert update delete都是使用这个方法 查询使用executeQuary（）

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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

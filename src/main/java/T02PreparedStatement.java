import java.sql.*;

public class T02PreparedStatement {
    public static void main(String[] args) {
        //将驱动加载进去
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//两种写法，这是第一种写法
//            //第二中写法,尽量不使用这种写法，尽量不要创建对象
//            new com.mysql.cj.jdbc.Driver();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //连接数据库
        String url = "jdbc:mysql://localhost/test_db?user=root&&password=xuehuafeiwu&serverTimezone=GMT%2B8";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = DriverManager.getConnection(url);

            String sql = "select * from Student where id > ? and id < ?";
            //创建一条语句对象
//            stmt = conn.createStatement();
            ps = conn.prepareStatement(sql);
            try {
                ps.setInt(1,0);
                ps.setInt(2,2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(id + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //不论成不成功，最终都在这里关闭资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

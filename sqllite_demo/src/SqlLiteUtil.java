import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * db连接
 * @author dongbao
 *
 */
public class SqlLiteUtil {
	private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:D:\\dbfile\\test.db";

    public static void main(String args[]) {
        
    	//db创建
    	dbcreate();
    	//db读取
    	dbread();
    }
    /**
     * db创建
     */
	private static void dbcreate() {
		List<Map<String,Object>>list = new ArrayList<>();
    	Map<String,Object>map1 = new ConcurrentHashMap<>();
    	map1.put("username", "东宝");
    	map1.put("password", "666666");
    	Map<String,Object>map2 = new ConcurrentHashMap<>();
    	map2.put("username", "dong");
    	map2.put("password", "123456");
    	list.add(map1);
    	list.add(map2);
    	try {
			create("test", list);
			System.out.println("db创建成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * db读取
     */
	private static void dbread() {
		// load the sqlite-JDBC driver using the current class loader
        Connection connection = null;
        try {
            connection = createConnection();
            connect(connection);
            System.out.println("db读取成功!");
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
	}
    
    // 创建Sqlite数据库连接
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Class_Name);
        return DriverManager.getConnection(DB_URL);
    }
    /**
     * 连接数据库读取数据
     * @param connection
     * @throws SQLException
     */
    public static void connect(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //设置超时时间
        //statement.setQueryTimeout(30); // set timeout to 30 sec.
        // 执行查询语句
        ResultSet rs = statement.executeQuery("select * from user");
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println("用户名 = " + username + "  密码 = " + password);
        }
    }
    
    public static boolean create(String filename,List<Map<String,Object>> list) throws IOException{
		//db文件地址
		File dbBase =  new File("D:/dbfile");
		String tableName1 = "user";
		if(!dbBase.exists()){
			dbBase.mkdir();
		}
		File dbfile =  new File(dbBase.getAbsoluteFile()+"/"+filename+".db");
		if(!dbfile.exists()){
			dbfile.createNewFile();
		}
		Statement stat = null;
		Connection conn = null;
		PreparedStatement prest = null;

		try{ 
	         //连接SQLite的JDBC
	         Class.forName("org.sqlite.JDBC");
	         
	         //建立一个数据库名db的连接，如果不存在就在当前目录下创建之
	         System.out.println(dbfile.getAbsolutePath());
	         //jdbc:sqlite:后边的是.db文件的存储位置(绝对路径)
	         conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile.getAbsolutePath());
	         
	         stat = conn.createStatement();
	         //首先清空数据表
	         stat.executeUpdate("drop table if exists "+tableName1);
	         //创建表结构
	         stat.executeUpdate( "create table " + tableName1 +"(username text,password text )");
	         //如果传入的list是个0就没必要执行批量插入了
	         boolean doBatch = list.size()!=0;
	         //批量插入数据
	         if(doBatch){
		         String sql = "INSERT INTO "+tableName1+" VALUES(?,?)";   
		         prest =conn.prepareStatement(sql);
		         
		         for(Map<String, Object> po:list){
		        	 prest.setString(1, po.get("username")+"");
		        	 prest.setString(2, po.get("password")+"");
		        	 prest.addBatch();
		         }
		         conn.setAutoCommit(false);
		         prest.executeBatch();
		         conn.setAutoCommit(true);
	         }
	         //结束数据库的连接 
	         stat.close();
	         if(doBatch)
	         prest.close();
	         conn.close();
        }
        catch( Exception e ){
	         dbfile.delete();
	         e.printStackTrace();
	         return false;
        }finally{
        	  //结束数据库的连接 
				/*try {
					if(conn!=null) conn.close();
					if(stat!=null) stat.close();
					if(prest!=null) prest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}*/
        }
		return true;
	}
}

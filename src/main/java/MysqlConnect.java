import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MysqlConnect {
    private static String Driver;
    private static String URL;
    private static String User;
    private static String PassWord;
    private static  Connection connect;
    static{
        try {
            Properties properties=new Properties();
            InputStream cin=MysqlConnect.class.getClassLoader().getResourceAsStream("mysql.properties");
            properties.load(cin);
            Driver= properties.getProperty("Driver");
            URL=properties.getProperty("URL");
            User= properties.getProperty("User");
            PassWord= properties.getProperty("PassWord");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MysqlConnect() throws ClassNotFoundException, SQLException {
        Class.forName(Driver);
        connect=DriverManager.getConnection(URL,User,PassWord);
    }
    public boolean findUser(userCheck user){
        try{
            String sqlID="select id from id where id_number= ? ";
            PreparedStatement search= connect.prepareStatement(sqlID);
            search.setString(1,user.getID());
            ResultSet resId= search.executeQuery();
            String id="";
            boolean hasId=false;
            while(resId.next()){
                hasId=true;
                id= resId.getString(1);
            }
            if(hasId) {
                String sqlPassWord="select password from password where id= ? ";
                PreparedStatement searchCode= connect.prepareCall(sqlPassWord);
                searchCode.setString(1,id);
                ResultSet resCode= searchCode.executeQuery();
                String passWord="";
                while(resCode.next()){
                    passWord=resCode.getString(1);
                    if(passWord.equals(user.getPassWord()))
                        return true;
                }
            }
            else
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean checkID(userCheck user)  {
        boolean hasId=false;
        String sqlID="select id from id where id_number= ? ";
        PreparedStatement search= null;
        try {
            search = connect.prepareStatement(sqlID);
            search.setString(1,user.getID());
            ResultSet resId= search.executeQuery();
            String id="";

            while(resId.next()) {
                hasId = true;
                id = resId.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hasId;
    }
        public boolean registerID(userCheck user){
        try{
            String insertID="insert into id values(0,?)";
            String insertCode="insert into password values(0,?)";
            PreparedStatement insertId= connect.prepareCall(insertID);
            PreparedStatement insertPassWord=connect.prepareCall(insertCode);
            insertId.setString(1, user.getID());
            insertPassWord.setString(1, user.getPassWord());
            insertId.executeUpdate();
            insertPassWord.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}


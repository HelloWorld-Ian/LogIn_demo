import java.sql.SQLException;

public class mysqlTest {
    public boolean testCheck(userCheck user) throws SQLException, ClassNotFoundException {
        MysqlConnect mysql=new MysqlConnect();
        if(mysql.findUser(user))
            return true;
        else return false;

    }

    public static void main(String[] args) {
        userCheck test=new userCheck();
        test.setID("1234567");
        test.setPassWord("1234567");
        try {
            //System.out.println(new mysqlTest().testCheck(test));
            System.out.println(new MysqlConnect().checkID(test));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

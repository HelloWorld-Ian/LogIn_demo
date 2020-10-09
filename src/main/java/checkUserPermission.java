import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/checkUserPermission")
public class checkUserPermission extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userCheck userCheck=new userCheck();
        try {
            BeanUtils.populate(userCheck,req.getParameterMap());
            userCheck.setPassWord(req.getParameter("PassWord"));
            System.out.println(userCheck.getID());
            System.out.println(userCheck.getPassWord());
            MysqlConnect connect=new MysqlConnect();
            if(connect.findUser(userCheck)){
                System.out.println("yes");
                  resp.sendRedirect("/success.html");
            }
            else{
                System.out.println("no");
                  resp.sendRedirect("/fail.html");
            }
        } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

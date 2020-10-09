import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         userCheck user=new userCheck();
        try {
            BeanUtils.populate(user,req.getParameterMap());
            MysqlConnect connect= new MysqlConnect();
            user.setPassWord(req.getParameter("PassWord"));
            if(connect.checkID(user)) {
                resp.sendRedirect("/registerIDIdentical.html");
            }else {
                if (connect.registerID(user)) {
                    resp.sendRedirect("/registerSuccess.html");
                } else {
                    resp.sendRedirect("/registerFail.html");
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

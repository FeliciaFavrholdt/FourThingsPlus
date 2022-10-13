package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddItem", value = "/additem")
public class AddItem extends HttpServlet {
    private final ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        int newItemId = ItemFacade.insertItem(name, user.getUsername(), connectionPool);
        Item newItem = ItemFacade.getItemById(newItemId, connectionPool);
        request.setAttribute("item", newItem);
        List<Item> itemList = ItemFacade.getItems(connectionPool);
        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
    }
}
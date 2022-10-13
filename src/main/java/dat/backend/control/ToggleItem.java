package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "toggleitem", value = "/toggleitem")
public class ToggleItem extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        ItemFacade.toggleItem(item_id, connectionPool);
        List<Item> itemList = ItemFacade.getItems(connectionPool);
        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);

    }
}

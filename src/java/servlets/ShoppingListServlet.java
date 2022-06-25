package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // load the session or create a new session if session doesn't exists
        HttpSession session = request.getSession();
        
        // if user clicked the logout link
        if(request.getParameter("action") != null){
            if(request.getParameter("action").equals("logout")){
                session.invalidate();
                request.setAttribute("logout", true); 
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        }
        // if user is already logged in
        if(session.getAttribute("username") != null){
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        
        // load up the JSP and stop the code call
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // load the session or create a new session if session doesn't exists
        HttpSession session = request.getSession();
        
        // Create an arreayList and initialize it with null
        ArrayList<String> theList = null;
        
        // check if there is an arrayList already present in this session. If so, copy down this arrayList into the arrayList object
        if(session.getAttribute("list") != null){
            theList = (ArrayList<String>) session.getAttribute("list");
        }
        // if not, create new arrayList
        else{
            theList = new ArrayList<String>();
        }
        
        // check if user submitted the username form from
        if(request.getParameter("action").equals("register")){
            String username = request.getParameter("username");
        
            if(username == null || username.equals("")){
                request.setAttribute("loginError", true);
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        // check if user submitted the add item form
        else if(request.getParameter("action").equals("add")){
            
            String item = request.getParameter("itemName");
            
            if(item == null || item.equals("")){
                request.setAttribute("inputError", true);
                // load the JSP and stop the code call
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
            
            theList.add(item);
            session.setAttribute("added", true);
        }
        // check if user submitted the delete item form
        else if(request.getParameter("action").equals("delete")){
            String toDelete = request.getParameter("itemButton");
            
            if(toDelete == null || toDelete.equals("")){
                request.setAttribute("deleteError", true);
                // load the JSP and stop the code call
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
            
            theList.remove(toDelete);
            if(theList.size() == 0){
                session.setAttribute("added", false);
            }
        }
        
        // save the list into the current session and redirect the user to shoppingList JSP and stop the code call.
        session.setAttribute("list", theList);
        response.sendRedirect("ShoppingList");
        return;
    }
}
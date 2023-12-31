package com.usermanagement.Controller;

import com.usermanagement.model.User;
import com.usermanagement.service.IUserService;
import com.usermanagement.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManagementServlet", urlPatterns = "/UserManagement")
public class UserManagementServlet extends HttpServlet {
    private static final IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "sort":
                sortByName(request, response);
                break;
            case "add":
                showAddingForm(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                showList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "searchByCountry":
                searchByCountry(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "searchByName":
                searchByName(request, response);
                break;
            default:
                showList(request, response);
        }
    }


    private void showAddingForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/add.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }



    private void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        List<User> userList = service.searchByName(name);
        request.setAttribute("userList", userList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        service.update(new User(id, name, email, country));
        try {
            response.sendRedirect("/UserManagement");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/add.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        if (service.findById(id) != null) {
            request.setAttribute("message", "This ID already exist!");
        } else {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            service.add(new User(id, name, email, country));
            request.setAttribute("message", "Add successfully");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.delete(id);
        try {
            response.sendRedirect("/UserManagement");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortByName(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        List<User> userList = service.getSortList();
        request.setAttribute("userList", userList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        List<User> userList = service.getList();
        request.setAttribute("userList", userList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void searchByCountry(HttpServletRequest request, HttpServletResponse response) {
        String country = request.getParameter("country");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        List<User> userList = service.searchByCountry(country);
        request.setAttribute("userList", userList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}

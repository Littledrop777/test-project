package by.academy.it;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "applicationServlet", urlPatterns = "/info")
public class ApplicationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        String start = "<html><head><title>Information</title></head><body>";
        String button = "<form action='/task2_10' method='post'>" +
                "<input type='submit' name='print' value='try again'>" +
                "</form>";
        String end = "</body></html>";
        String errorMessage = "<h2 align='center'>%s</h2>";

        if (name.equals("")) {
            out.println(start);
            out.println(String.format(errorMessage, "Please, enter your name"));
            out.println(button);
            out.println(end);
        } else if (phone.equals("") && email.equals("")) {
            out.println(start);
            out.println(String.format(errorMessage, "Please, enter at least your phone number or email"));
            out.println(button);
            out.println(end);
        } else {
            out.println(start);
            out.println("<h2 align=\"center\">Welcome, " + name + "!</h2>");
            if (!phone.equals("")) {
                out.println("<h2 align=\"center\">phone: " + phone + "</h2>");
            }
            if (!email.equals("")) {
                out.println("<h2 align=\"center\">email: " + email + "</h2>");
            }
            out.println(end);
        }
    }
}

package by.academy.it;


import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "browserServlet", urlPatterns = "/browser")
public class BrowserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        String header = req.getHeader("User-Agent");
        Browser browser = Browser.getBrowser(header);
        out.println("<html><head><title>Application Servlet</title></head>");
        out.println("<body><h1 align='center'>Welcome user from " + browser.toString() + "</h1>");
        out.println("</body></html>");
    }
}

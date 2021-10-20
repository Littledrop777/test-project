package by.academy.it.oldImplWithLib;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "applicationServlet", urlPatterns = "/hello")
public class ApplicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        String browserName = userAgent.getBrowser().getName();
        out.println("<html><head><title>Application Servlet</title></head>");
        out.println("<body><h1 align='center'>Welcome user from " + browserName + "</h1>");
        out.println("</body></html>");
    }
}

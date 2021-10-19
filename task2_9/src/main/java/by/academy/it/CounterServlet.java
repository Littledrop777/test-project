package by.academy.it;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

public class CounterServlet extends HttpServlet {

    private String filePath;
    private String fileName;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        filePath = config.getInitParameter("logFilePath");
        fileName = config.getInitParameter("logFileName");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        File outputDir = new File(filePath);

        try {
            if (!outputDir.exists()) {
                if (!outputDir.mkdirs()) {
                    throw new FileNotFoundException();
                }
            }
            int count;
            if (readCount() == 0) {
                count = 0;
            } else {
                count = readCount();
            }
            count++;
            out.println("<html><head><title>Counter Servlet</title></head>");
            out.println("<body><h1 align='center'>Total number of visits</h1>");
            out.println("<h2 align='center'>" + count + "</h2>");
            out.println("</body></html>");
            writeCount(count);
        } catch (FileNotFoundException e) {
            out.printf("Could not find or create such directory: %s", filePath);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private int readCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + File.separator + fileName))) {
            count = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private void writeCount(int count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + File.separator + fileName))) {
            writer.write(count + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

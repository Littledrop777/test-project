package by.academy.it;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@WebServlet(name = "counterServlet", urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {
    private String filePath;
    private int count;

    @Override
    public void init() throws ServletException {
        super.init();
        this.filePath = getServletContext().getInitParameter("logFile").replace("\\", File.separator);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File outputDir = new File(filePath);

        try {
            if (!outputDir.getParentFile().exists()) {
                if (!outputDir.getParentFile().mkdirs()) {
                    throw new FileNotFoundException();
                }
            }else{
                if (readCount() == 0) {
                    count = 0;
                } else {
                    count = readCount();
                }
            }
            resp.setContentType("image/jpeg");
            count++;
            BufferedImage image = new BufferedImage(700,500, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setFont(new Font("Serif", Font.ITALIC, 70));
            graphics.setColor(Color.ORANGE);
            graphics.drawString("Total number of visits",50, 150);
            graphics.drawString(String.valueOf(count), 300, 300);
            ServletOutputStream out = resp.getOutputStream();
            ImageIO.write(image, "jpeg", out);
            writeCount(count);
        } catch (FileNotFoundException e) {
            PrintWriter writer = resp.getWriter();
            writer.printf("Could not find or create such directory: %s", outputDir.getParent());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private int readCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            count = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private void writeCount(int count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(count + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

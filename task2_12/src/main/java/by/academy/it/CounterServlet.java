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
        filePath = getServletContext().getRealPath("/logFile.txt");
        if (readCount() == 0) {
            count = 0;
        } else {
            count = readCount();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        ServletOutputStream out = resp.getOutputStream();
        count++;
        BufferedImage image = new BufferedImage(500,200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC, 120));
        graphics.setColor(Color.ORANGE);
        graphics.drawString(String.valueOf(count), 180, 130);
        ImageIO.write(image, "jpeg", out);
        writeCount(count);
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

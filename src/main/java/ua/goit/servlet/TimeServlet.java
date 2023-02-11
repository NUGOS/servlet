package ua.goit.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        String timezone = request.getParameter("timezone");
        if (timezone != null) {
            now = ZonedDateTime.now(ZoneId.of(timezone.replace(" ", "+")));
        }
        String formattedDate = now.format(formatter);


        writer.println("<html>");
        writer.println("<head><title>Current Time</title></head>");
        writer.println("<body>");
        writer.println("<h1>" + formattedDate + "</h1>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }
}
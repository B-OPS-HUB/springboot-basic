package dev.practice.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ServletApplication {

    public static void main(String[] args) {

        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String name = req.getParameter("name");

                    //                    resp.setStatus(200);
                    resp.setStatus(HttpStatus.OK.value());
//                    resp.setHeader("Content-Type", "text/plain;charset=UTF-8");
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println("Hello " + name + "!");
                }
            }).addMapping("/hello");// /hello로 요청이 들어오면 해당 서블릿이 처리 하겠다는것
        });
        webServer.start(); // 그냥 톰캣 실행 부분
        // test -> http -v :8080
        // servlet 만든후 http -v :8080/hello -> 200나오면 성공
        // http -v ":8080/hello?name=Spring"
    }

}

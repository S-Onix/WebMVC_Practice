package org.example;

import org.example.calculate.Calculator;
import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet(interface) -> GenericServlet(extends / service() 부분을 제외한 나머지는 기초적으로 구현되어 있음) -> HttpServlet 구조로 이루어짐
 * */
@WebServlet("/calculate")
public class CalculatorServlet implements Servlet {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorServlet.class);
    private ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // resource init
        logger.info("init");
        this.servletConfig = servletConfig;
    }

    // /calculate?operand1=11&operator=*&operand2=22
    // [?] 이하 쿼리스트링 값을 request에서 getParameter로 가져올 수 잇음
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        logger.info("service");
        String operand1 = request.getParameter("operand1");
        String operator = request.getParameter("operator");
        String operand2 = request.getParameter("operand2");

        logger.info("operand1 :: {}", operand1);
        logger.info("operator :: {}", operator);
        logger.info("operand2 :: {}", operand2);

        double result = Calculator.calculateByInterface(
                new PositiveNumber(Integer.parseInt(operand1))
                , operator
                , new PositiveNumber(Integer.parseInt(operand2))
        );

        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(result);


    }
    @Override
    public void destroy() {
        // resource release
        logger.info("destroy");
    }
    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }
    @Override
    public String getServletInfo() {
        return null;
    }


}

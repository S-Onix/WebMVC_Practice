package org.example;

import org.example.calculate.Calculator;
import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet(interface) -> GenericServlet(abstract class / service() 부분을 제외한 나머지는 기초적으로 구현되어 있음) -> HttpServlet(abstract class) 구조로 이루어짐
 *
 * GenericServlet과 HttpServlet의 차이점
 *   - Http Method를 구분하여 호출이 가능하다.
 *
 * 주의사항
 *   - URL 인코딩(=퍼센트 인코딩)
 *      - URL로 사용할 수 없는 문자(예약어, Non-ASCII 문자(한글)등) 을 사용할 수 있도록 인코딩함
 *      - 인코딩된 문자는 triplet(세개가 한 세트)로 인코딩 되며 각각을 % 다음에 두 개의 16진수로 표현한다.
 * */
@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("service");
        String operand1 = request.getParameter("operand1");
        String operator = request.getParameter("operator");
        String operand2 = request.getParameter("operand2");


        /* operator 가 + 인 경우에는 예약어이기 때문에 에러가 발생함
         * 따라서 URL 인코딩을 통해 + 를 %2b로 변경되어야함
         * */
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
}

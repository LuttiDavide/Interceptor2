package co.develhope.interceptor2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    private final List<Month> months;

    public MonthInterceptor() {
        months = new ArrayList<>();

        months.add(new Month(1, "January", "Gennaio", "Januar"));
        months.add(new Month(2, "February", "Febbraio", "Februar"));
        months.add(new Month(3, "March", "Marzo", "März"));
        months.add(new Month(4, "April", "Aprile", "April"));
        months.add(new Month(5, "May", "Maggio", "Mai"));
        months.add(new Month(6, "June", "Giugno", "Juni"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberStr = request.getHeader("monthNumber");

        if (monthNumberStr == null || monthNumberStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Month number is required.");
            return false;
        }

        int monthNumber = Integer.parseInt(monthNumberStr);
        Month selectedMonth = findMonth(monthNumber);

        request.setAttribute("selectedMonth", selectedMonth);
        return true;
    }

    public Month findMonth(int monthNumber) {
        for (Month month : months) {
            if (month.getMonthNumber() == monthNumber) {
                return month;
            }
        }
        return new Month(monthNumber, "nope", "nope", "nope");
    }

}

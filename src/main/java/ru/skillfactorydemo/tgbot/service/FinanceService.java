package ru.skillfactorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skillfactorydemo.tgbot.entity.Income;
import ru.skillfactorydemo.tgbot.entity.Spend;
import ru.skillfactorydemo.tgbot.repository.IncomeRepository;
import ru.skillfactorydemo.tgbot.repository.SpendRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinanceService {

    private static final String ADD_INCOME = "/addincome";
    private static final String ADD_SPEND = "/addspend";
    private static final String GET_INCOME_HISTORY = "/getincomehistory";
    private static final String GET_SPEND_HISTORY = "/getspendhistory";

    @Value("${spring.datasource.url}")
    private String JDBC;
    @Value("${spring.datasource.password}")
    private String PASS;
    @Value("${spring.datasource.username}")
    private String USER;
    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;

    public String getOperations(String operationType, Long chatId) {
        String message = "История отсутствует";
        if (GET_INCOME_HISTORY.equalsIgnoreCase(operationType))
        {
            try
            {
                Class.forName("org.postgresql.Driver");
                String sql = "SELECT income FROM incomes where chat_id = " + chatId;
                Connection con = DriverManager.getConnection(JDBC,USER,PASS);
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()){
                    message = rs.getString("income")+ "\n";
                    while(rs.next())
                    {
                        message = message + rs.getString("income")+ "\n";
                    }
                }
                rs.close();
                statement.close();
                con.close();

            }
            catch (SQLException | ClassNotFoundException e)
            {
                log.error("Возникла ошибка запроса", e);
            }
        }else
        {
            try
            {
                Class.forName("org.postgresql.Driver");
                String sql = "SELECT spend FROM spend where chat_id = " + chatId;
                Connection con = DriverManager.getConnection(JDBC,USER,PASS);
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()){
                    message = rs.getString("spend")+ "\n";
                    while(rs.next())
                    {
                        message = message + rs.getString("spend")+ "\n";
                    }
                }
                rs.close();
                statement.close();
                con.close();
            }
            catch (SQLException | ClassNotFoundException e)
            {
                log.error("Возникла ошибка запроса", e);
            }
        }
        return message;
    }


    public String addFinanceOperation(String operationType, String price, Long chatId) {
        String message = "";
        if (ADD_INCOME.equalsIgnoreCase(operationType)) {
            Income income = new Income();
            income.setChatId(chatId);
            income.setIncome(new BigDecimal(price));
            incomeRepository.save(income);
            message = "Доход в размере " + price + " был успешно добавлен";
        } else if (ADD_SPEND.equalsIgnoreCase(operationType)){
            Spend spend = new Spend();
            spend.setChatId(chatId);
            spend.setSpend(new BigDecimal(price));
            spendRepository.save(spend);
            message = "Расход в размере " + price + " был успешно добавлен";
        }
        return message;
    }
}

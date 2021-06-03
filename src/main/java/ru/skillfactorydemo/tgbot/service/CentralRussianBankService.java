package ru.skillfactorydemo.tgbot.service;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.ws.client.core.WebServiceTemplate;
import ru.skillfactorydemo.tgbot.dto.GetCursOnDateXML;
import ru.skillfactorydemo.tgbot.dto.GetCursOnDateXmlResponse;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CentralRussianBankService extends WebServiceTemplate{

    @Value("${cbr.url}")
    private String cbrUrl;

    //метод для получения данных
    public List<ValuteCursOnDate> getCurrenciesFromCbr() throws DatatypeConfigurationException {
        final GetCursOnDateXML getCursOnDateXml = new GetCursOnDateXML();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        getCursOnDateXml.setOnDate(xmlGregCal);

        GetCursOnDateXmlResponse response = (GetCursOnDateXmlResponse)marshalSendAndReceive(cbrUrl, getCursOnDateXml);

        if (response == null) {
            throw new IllegalStateException("Could not get response from CBR Service");
        }

        final List<ValuteCursOnDate> courses = response.getGetCursOnDateXmlResult().getValuteData();
        courses.forEach(course -> course.setName(course.getName().trim()));
        return courses;
    }
}

package ru.skillfactorydemo.tgbot.dto;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
@XmlRootElement(name = "GetCursOnDate",namespace = "http://web.cbr.ru/")
public class GetCursOnDateXml {

    @XmlElement(name = "On_date",required = true,namespace = "http://web.cbr.ru/") //Указание на то в каком теге XML должно быть данное поле
    @XmlSchemaType(name = "dataTime") //указание типа данных в XML
    protected XMLGregorianCalendar onDate;
}

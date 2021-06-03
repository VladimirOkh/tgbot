package ru.skillfactorydemo.tgbot.dto;


import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetCursOnDateXml",namespace = "http://web.cbr.ru/")
public class GetCursOnDateXml {


    @XmlElement(name = "on_Date",required = true,namespace = "http://web.cbr.ru/") //Указание на то в каком теге XML должно быть данное поле
    protected XMLGregorianCalendar on_Date;
}

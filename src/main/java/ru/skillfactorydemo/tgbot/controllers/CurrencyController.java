package ru.skillfactorydemo.tgbot.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfactorydemo.tgbot.service.CentralRussianBankService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/get")
public class CurrencyController {

    private final CentralRussianBankService centralRussianBankService;

    @GetMapping("/String")
    public String getString(){
        return "Test get mapping";
    }



    @GetMapping ("/Currencies")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        return centralRussianBankService.getCurrenciesFromCbr();
    }
}

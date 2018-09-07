package io.cfapps.weektwo.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cfapps.weektwo.models.StockJPAService;
import io.cfapps.weektwo.repository.Dataset;
import io.cfapps.weektwo.repository.Stock;
import io.cfapps.weektwo.repository.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

@RestController
public class LoQToDbController {

    private List<Stock> stockList;

    private StockJPAService stockJPAService;

    @Autowired
    public void setStockJPAService(StockJPAService stockJPAService) {
        this.stockJPAService = stockJPAService;
    }


    @PostMapping("/load")
    public @ResponseBody ResponseEntity<?> load(@RequestBody(required=false) Stock stock) {
        ObjectMapper objectMapper = new ObjectMapper();


            try {
                URL url = new URL(Dataset.getURL());
                stockList = objectMapper.readValue(url, new TypeReference<List<Stock>>() {
                });
                for (Stock stocks : stockList) {
                    stockJPAService.save(stocks);
                }
            } catch(IOException e) {
                System.err.println(e);
            }


        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }


    @GetMapping("/{searchSymbol}/{searchDate}")
    public @ResponseBody ResponseEntity<?> get(@PathVariable String searchSymbol, @PathVariable String searchDate) {

        if(searchDate.split("=")[0].equalsIgnoreCase("date")) {
            List<Object[]> object = stockJPAService.byDate(searchSymbol, searchDate.split("=")[1]);
            Summary summary = getSummary(object);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        }

        else if(searchDate.split("=")[0].equalsIgnoreCase("month")) {

            List<Object[]> object = stockJPAService.byMonth(searchSymbol, searchDate.split("=")[1]);
            Summary summary = getSummary(object);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        }
        else return null;
    }

    private Summary getSummary(List<Object[]> object) {
        Summary summary = new Summary();
        summary.setSymbol((String.valueOf(object.get(0)[0])));
        summary.setMaxPrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(object.get(0)[1]))));
        summary.setMinPrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(object.get(0)[2]))));
        summary.setClosingPrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(object.get(0)[3]))));
        summary.setTotalVolume(Integer.parseInt(String.valueOf(object.get(0)[4])));
        return summary;
    }


}

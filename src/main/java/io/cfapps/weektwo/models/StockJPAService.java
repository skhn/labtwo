package io.cfapps.weektwo.models;

import io.cfapps.weektwo.repository.Stock;
import io.cfapps.weektwo.repository.StockDAO;
import io.cfapps.weektwo.repository.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockJPAService {

@Autowired
//Dependency Injection
    private StockDAO stockDAO;


    public void save(Stock stock) { stockDAO.save(stock); }

    public List<Object[]> byDate(String searchSymbol, String searchDate) {return stockDAO.findByDate(searchSymbol, searchDate);}
    public List<Object[]> byMonth(String searchSymbol, String searchDate) {return stockDAO.findByMonth(searchSymbol, searchDate);}
}

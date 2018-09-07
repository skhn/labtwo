package io.cfapps.weektwo.repository;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.math.BigDecimal;

import static io.cfapps.weektwo.repository.StockDAO.QueryMonth;

@Repository
@Transactional
public class Summary {


    BigDecimal maxPrice;
    BigDecimal minPrice;
    BigDecimal closingPrice;
    Integer totalVolume;
    String symbol;


    public Summary() {
    }

    @JsonCreator
    public Summary( @JsonProperty String symbol,
                    @JsonProperty BigDecimal maxPrice,
                    @JsonProperty BigDecimal minPrice,
                    @JsonProperty BigDecimal closingPrice,
                    @JsonProperty Integer totalVolume) {

        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.closingPrice = closingPrice;
        this.totalVolume = totalVolume;
        this.symbol = symbol;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

package io.cfapps.weektwo.repository;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

/**
 * Class to form a single List item
 */


@Entity
@Table(name = "stocks", uniqueConstraints = {
        @UniqueConstraint(columnNames = "stock_id")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "stock_id", unique = true, nullable = false)
    private int id;


    @Column(name = "symbol", nullable = false, length = 4)
    private String symbol;

    private BigDecimal price;

    private Integer volume;

    @Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)


    private Calendar date;


    public Stock() {}

    public Stock(String symbol, BigDecimal price, int volume, Date date) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        setDate(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Date date) {

        TimeZone tz = TimeZone.getTimeZone("CST");

        Date dateValue = date;
        Calendar calValue = Calendar.getInstance(tz);
        calValue.setTime(dateValue);
        this.date=calValue;
    }


    @Override
    public String toString() {
        return getSymbol() + "," + getPrice() + "," + getVolume() + "," + getDate();
    }
}

package io.cfapps.weektwo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static io.cfapps.weektwo.repository.StockDAO.QueryMonth;

@NamedNativeQueries({
        @NamedNativeQuery(
                name            = "queryFetchRequiredByMonth",
                resultSetMapping= "StockToSummaryMapping",
                query           = QueryMonth
        )
})


@SqlResultSetMapping(
        name = "StockToSummaryMapping",
        classes = @ConstructorResult(
                targetClass = Summary.class,
                columns = {
                        @ColumnResult(name = "symbol"),
                        @ColumnResult(name = "max_price", type = BigDecimal.class),
                        @ColumnResult(name = "min_price", type = BigDecimal.class),
                        @ColumnResult(name = "closing_price", type = BigDecimal.class),
                        @ColumnResult(name = "total_volume", type = Integer.class)
                }
        )
)


public interface StockDAO extends JpaRepository<Stock, Long> {


    String QueryDate = "SELECT t0.symbol, max_price, min_price, closing_price, total_volume FROM " +


"(SELECT symbol, max(price) as max_price, min(price) as min_price, sum(volume) as total_volume " +
    "FROM stocks " +
    "WHERE date LIKE :searchDate% AND symbol = :searchSymbol) AS t0 " +

    "INNER JOIN " +

            "(SELECT symbol, price as closing_price " +
                    "FROM stocks " +
                    "WHERE date LIKE :searchDate% AND symbol = :searchSymbol " +
                    "ORDER BY date DESC " +
                    "LIMIT 1) AS t1 " +

    "ON " +

    "t0.symbol = t1.symbol";



    String QueryMonth = "SELECT t0.symbol, max_price, min_price, closing_price, total_volume FROM " +


            "(SELECT symbol, max(price) as max_price, min(price) as min_price, sum(volume) as total_volume " +
            "FROM stocks " +
            "WHERE MONTH(date) = :searchDate AND symbol = :searchSymbol) AS t0 " +

            "INNER JOIN " +

            "(SELECT symbol, price as closing_price " +
            "FROM stocks " +
            "WHERE MONTH(date) = :searchDate AND symbol = :searchSymbol " +
            "ORDER BY date DESC " +
            "LIMIT 1) AS t1 " +

            "ON " +

            "t0.symbol = t1.symbol";


    @Query(value = QueryDate, nativeQuery = true)
   List<Object[]> findByDate(@Param("searchSymbol") String symbol, @Param("searchDate") String searchDate);


    @Query(value = QueryMonth, nativeQuery = true)
    List<Object[]> findByMonth(@Param("searchSymbol") String symbol, @Param("searchDate") String searchDate);


//    @Query(name = "queryFetchRequiredByMonth", nativeQuery = true)
//    List<Summary> queryFetchRequiredByMonth(@Param("searchSymbol") String symbol, @Param("searchDate") String searchDate);


}



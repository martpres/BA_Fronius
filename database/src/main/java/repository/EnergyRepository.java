package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Repository
@Transactional(readOnly = true)
public class EnergyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final String queryEnergy = "WITH ordered_points AS (" +
            " SELECT" +
            " primary_timestamp," +
            " $Cm," +
            " LEAD(primary_timestamp) OVER (ORDER BY primary_timestamp) AS next_timestamp," +
            " LEAD($Cm) OVER (ORDER BY primary_timestamp) AS next_ac_power_inverter" +
            " FROM params" +
            " WHERE primary_timestamp BETWEEN :startDate AND :endDate AND $Cm IS NOT NULL$Aw" +
            ")," +
            " trapezoids AS (" +
            " SELECT" +
            " primary_timestamp," +
            " $Cm," +
            " next_timestamp," +
            " next_ac_power_inverter," +
            " EXTRACT(EPOCH FROM (next_timestamp - primary_timestamp)) AS time_difference," +
            " (($Cm + next_ac_power_inverter) / 2.0) * EXTRACT(EPOCH FROM (next_timestamp - primary_timestamp)) AS trapezoid_area" +
            " FROM ordered_points" +
            " WHERE next_timestamp IS NOT NULL" +
            " )" +
            " SELECT SUM(trapezoid_area)$Pn AS total_area" +
            " FROM trapezoids;";


    public Double calculateEnergy( String columnName,
                                    ZonedDateTime startDate,
                                    ZonedDateTime endDate){
        return calculateEnergy(columnName, startDate, endDate, null, false);
    }

    public Double calculatePositiveEnergy( String columnName,
                                   ZonedDateTime startDate,
                                   ZonedDateTime endDate){
        final String additionalWhere = " AND $Cm > 0 ";
        return calculateEnergy(columnName, startDate, endDate, additionalWhere, false);
    }

    public Double calculateNegativeEnergy( String columnName,
                                           ZonedDateTime startDate,
                                           ZonedDateTime endDate){
        final String additionalWhere = " AND $Cm < 0 ";
        return calculateEnergy(columnName, startDate, endDate, additionalWhere, true);
    }

    private Double calculateEnergy( String columnName,
                                   ZonedDateTime startDate,
                                   ZonedDateTime endDate,
                                   String additionalWhere,
                                   Boolean pn
                                    ){
        String cquery = queryEnergy;
        if (additionalWhere == null) {
            cquery = cquery.replace("$Aw", "");
        } else {
            cquery = cquery.replace("$Aw", additionalWhere);
        }
        if (pn) {
            cquery = cquery.replace("$Pn", " *-1");
        } else {
            cquery = cquery.replace("$Pn", "");
        }
        cquery = cquery.replaceAll("\\$Cm", columnName);
        Query query = entityManager.createNativeQuery(cquery);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return (Double) query.getSingleResult();
    }

}



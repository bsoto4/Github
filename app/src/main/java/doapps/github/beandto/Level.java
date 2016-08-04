package doapps.github.beandto;

import java.util.Date;

/**
 * Created by Luis alberto on 1/08/2016.
 */
public class Level {
    private String id ;
    private String record ;
    private Date dateRecord ;
    private String sectorId ;
    private double max ;
    private double prom ;
    private double min ;

    private String dateRecordString ;

    /*
    public Level(String id, String record, Date dateRecord, String sectorId, double max, double prom, double min) {
        this.id = id;
        this.record = record;
        this.dateRecord = dateRecord;
        this.sectorId = sectorId;
        this.max = max;
        this.prom = prom;
        this.min = min;
    }
    */


    public String getDateRecordString() {
        return dateRecordString;
    }

    public void setDateRecordString(String dateRecordString) {
        this.dateRecordString = dateRecordString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(Date dateRecord) {
        this.dateRecord = dateRecord;
    }

    public String getSectorId() {
        return sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getProm() {
        return prom;
    }

    public void setProm(double prom) {
        this.prom = prom;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}

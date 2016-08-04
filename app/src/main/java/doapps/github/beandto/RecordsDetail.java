package doapps.github.beandto;

import java.util.ArrayList;

/**
 * Created by Luis alberto on 1/08/2016.
 */
public class RecordsDetail {

    public  ArrayList<Level> recordsLastYear ;
    public ArrayList<Level> recordsCurrentYear ;
    private String riverName ;
    private String sectorName ;

    public RecordsDetail(ArrayList<Level> recordsLastYear, ArrayList<Level> recordsCurrentYear) {
        this.recordsLastYear = recordsLastYear;
        this.recordsCurrentYear = recordsCurrentYear;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public ArrayList<Level> getRecordsLastYear() {
        return recordsLastYear;
    }

    public void setRecordsLastYear(ArrayList<Level> recordsLastYear) {
        this.recordsLastYear = recordsLastYear;
    }

    public ArrayList<Level> getRecordsCurrentYear() {
        return recordsCurrentYear;
    }

    public void setRecordsCurrentYear(ArrayList<Level> recordsCurrentYear) {
        this.recordsCurrentYear = recordsCurrentYear;
    }
}

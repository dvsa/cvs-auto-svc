package model.testresults;

public class Location {

    private String vertical;
    private String horizontal;
    private String lateral;
    private String longitudinal;
    private Integer rowNumber;
    private Integer seatNumber;
    private Integer axleNumber;


    public String getVertical() {
        return vertical;
    }

    public Location setVertical(String vertical) {
        this.vertical = vertical;
        return this;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public Location setHorizontal(String horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    public String getLateral() {
        return lateral;
    }

    public Location setLateral(String lateral) {
        this.lateral = lateral;
        return this;
    }

    public String getLongitudinal() {
        return longitudinal;
    }

    public Location setLongitudinal(String longitudinal) {
        this.longitudinal = longitudinal;
        return this;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Location setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public Location setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public Integer getAxleNumber() {
        return axleNumber;
    }

    public Location setAxleNumber(Integer axleNumber) {
        this.axleNumber = axleNumber;
        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "vertical='" + vertical + '\'' +
                ", horizontal='" + horizontal + '\'' +
                ", lateral='" + lateral + '\'' +
                ", longitudinal='" + longitudinal + '\'' +
                ", rowNumber=" + rowNumber +
                ", seatNumber=" + seatNumber +
                ", axelNumber=" + axleNumber +
                '}';
    }
}

package model.defects;

import java.util.List;

public class Location {

    private List<String> vertical;
    private List<String> horizontal;
    private List<String> lateral;
    private List<String> longitudinal;
    private List<Integer> rowNumber;
    private List<Integer> seatNumber;
    private List<Integer> axleNumber;

    public List<String> getVertical() {
        return vertical;
    }

    public Location setVertical(List<String> vertical) {
        this.vertical = vertical;
        return this;
    }

    public List<String> getHorizontal() {
        return horizontal;
    }

    public Location setHorizontal(List<String> horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    public List<String> getLateral() {
        return lateral;
    }

    public Location setLateral(List<String> lateral) {
        this.lateral = lateral;
        return this;
    }

    public List<String> getLongitudinal() {
        return longitudinal;
    }

    public Location setLongitudinal(List<String> longitudinal) {
        this.longitudinal = longitudinal;
        return this;
    }

    public List<Integer> getRowNumber() {
        return rowNumber;
    }

    public Location setRowNumber(List<Integer> rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    public List<Integer> getSeatNumber() {
        return seatNumber;
    }

    public Location setSeatNumber(List<Integer> seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public List<Integer> getAxleNumber() {
        return axleNumber;
    }

    public Location setAxleNumber(List<Integer> axleNumber) {
        this.axleNumber = axleNumber;
        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "vertical=" + vertical +
                ", horizontal=" + horizontal +
                ", lateral=" + lateral +
                ", longitudinal=" + longitudinal +
                ", rowNumber=" + rowNumber +
                ", seatNumber=" + seatNumber +
                ", axleNumber=" + axleNumber +
                '}';
    }
}

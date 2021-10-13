package src.Model.Floor;

public class Tile {

    private String coordinate;
    private String tileType;
    private int dirt;
    private boolean isChargingStation;
    

    public Tile(String coordinate, String tileType, int dirt, boolean isChargingStation ){

        this.tileType = tileType;
        this.dirt = dirt;
        this.isChargingStation = isChargingStation;
        this.coordinate = coordinate;

    }


    /**
     * @return String return the tileType
     */
    public String getTileType() {
        return tileType;
    }

    /**
     * @param tileType the tileType to set
     */
    public void setTileType(String tileType) {
        this.tileType = tileType;
    }

    /**
     * @return int return the dirt
     */
    public int getDirt() {
        return dirt;
    }

    /**
     * @param dirt the dirt to set
     */
    public void setDirt(int dirt) {
        this.dirt = dirt;
    }

    /**
     * @return boolean return the isChargingStation
     */
    public boolean isIsChargingStation() {
        return isChargingStation;
    }

    /**
     * @param isChargingStation the isChargingStation to set
     */
    public void setIsChargingStation(boolean isChargingStation) {
        this.isChargingStation = isChargingStation;
    }

    /**
     * @return String return the coordinate
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

}
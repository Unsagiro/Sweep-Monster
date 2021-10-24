package src;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tile {

    @SerializedName("tile")
    @Expose
    private String tile;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("upTile")
    @Expose
    private String upTile;
    @SerializedName("rightTile")
    @Expose
    private String rightTile;
    @SerializedName("downTile")
    @Expose
    private String downTile;
    @SerializedName("leftTile")
    @Expose
    private String leftTile;
    @SerializedName("obstacleType")
    @Expose
    private String obstacleType;
    @SerializedName("chargingStation")
    @Expose
    private String chargingStation;
    @SerializedName("floorType")
    @Expose
    private String floorType;
    @SerializedName("dirt")
    @Expose
    private String dirt;

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getX() {return x;}
    public int getXVal() {return Integer.parseInt(x);}
    public void setX() {this.x = x;}

    public String getY() {return y;}
    public int getYVal() {return Integer.parseInt(y);}
    public void setY() {this.x = y;}

    public String getUpTile() {
        return upTile;
    }

    public void setUpTile(String upTile) {
        this.upTile = upTile;
    }

    public String getRightTile() {
        return rightTile;
    }

    public void setRightTile(String rightTile) {
        this.rightTile = rightTile;
    }

    public String getDownTile() {
        return downTile;
    }

    public void setDownTile(String downTile) {
        this.downTile = downTile;
    }

    public String getLeftTile() {
        return leftTile;
    }

    public void setLeftTile(String leftTile) {
        this.leftTile = leftTile;
    }

    public String getObstacleType() {
        return obstacleType;
    }

    public void setObstacleType(String obstaclePresent) {
        this.obstacleType = obstacleType;
    }

    public String getChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(String chargingStation) {
        this.chargingStation = chargingStation;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public String getDirt() {
        return dirt;
    }

    public void setDirt(String dirt) {
        this.dirt = dirt;
    }

}
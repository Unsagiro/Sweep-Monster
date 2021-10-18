package src;


import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;


public class Tile {

@SerializedName("tile")
@Expose
private String tile;
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
@SerializedName("obstaclePresent")
@Expose
private String obstaclePresent;
@SerializedName("doorPresent")
@Expose
private String doorPresent;
@SerializedName("stairsEdge")
@Expose
private String stairsEdge;
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

public String getObstaclePresent() {
        return obstaclePresent;
    }

public void setObstaclePresent(String obstaclePresent) {
        this.obstaclePresent = obstaclePresent;
    }

public String getDoorPresent() {
        return doorPresent;
    }

public void setDoorPresent(String doorPresent) {
        this.doorPresent = doorPresent;
    }

public String getStairsEdge() {
        return stairsEdge;
    }

public void setStairsEdge(String stairsEdge) {
        this.stairsEdge = stairsEdge;
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
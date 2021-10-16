package src.Model.Floor;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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

}
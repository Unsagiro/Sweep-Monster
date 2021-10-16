package src.Model.Floor;


import java.util.List;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TilesArray {

@SerializedName("TilesArray")
@Expose
private List<Tile> tilesArray = null;

public List<Tile> getTilesArray() {
return tilesArray;
}

public void setTilesArray(List<Tile> tilesArray) {
this.tilesArray = tilesArray;
}

}
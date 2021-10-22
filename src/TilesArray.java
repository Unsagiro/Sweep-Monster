package src;


import java.util.ArrayList;
import java.util.List;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TilesArray {

    @SerializedName("TilesArray")
    @Expose
    private List<Tile> tilesArray = new ArrayList<>();

    public List<Tile> getTilesArray() {
        return tilesArray;
    }

    public void setTilesArray(List<Tile> tilesArray) {
        this.tilesArray = tilesArray;
    }

    public Tile getTile(int i) {return tilesArray.get(i);}

    public int getScale() {return (int)Math.sqrt(tilesArray.size());}

    public int getTotal() {return tilesArray.size();}

    //help get particular tile info
    public Tile getTileInfo(int x, int y){return tilesArray.get(getScale()*x+y);}
}
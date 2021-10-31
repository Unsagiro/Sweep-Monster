package src;




import src.Tile;

import java.io.Serializable;

public class FloorPlanTile implements Serializable {
    
private Tile  topTile;
private Tile  rightTile;
private Tile  bottomTile;
private Tile  leftTile;
private Tile tile;


    public FloorPlanTile(Tile tile, Tile topTile, Tile rightTile, Tile bottomTile, Tile leftTile)
        {
            this.topTile = topTile;
            this.rightTile = rightTile;
            this.bottomTile = bottomTile;
            this.leftTile = leftTile;
            this.tile = tile;

        }

    /**
     * @return Tile return the topTile
     */
    public Tile getTopTile() {
        return topTile;
    }

    /**
     * @param topTile the topTile to set
     */
    public void setTopTile(Tile topTile) {
        this.topTile = topTile;
    }

    /**
     * @return Tile return the rightTile
     */
    public Tile getRightTile() {
        return rightTile;
    }

    /**
     * @param rightTile the rightTile to set
     */
    public void setRightTile(Tile rightTile) {
        this.rightTile = rightTile;
    }

    /**
     * @return Tile return the bottomTile
     */
    public Tile getBottomTile() {
        return bottomTile;
    }

    /**
     * @param bottomTile the bottomTile to set
     */
    public void setBottomTile(Tile bottomTile) {
        this.bottomTile = bottomTile;
    }

    /**
     * @return Tile return the leftTile
     */
    public Tile getLeftTile() {
        return leftTile;
    }

    /**
     * @param leftTile the leftTile to set
     */
    public void setLeftTile(Tile leftTile) {
        this.leftTile = leftTile;
    }

    /**
     * @return Tile return the tile
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * @param tile the tile to set
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

}
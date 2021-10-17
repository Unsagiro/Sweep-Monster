import src.Direction;

import java.util.*;

public class sweepMonster_00 {
    private static final int MAX_BATTERY = 250;
    private static final int MAX_DIRT_CAPACITY = 50;

    private int currentBattery;
    private int currentDirtCapacity;
    private Direction currentDirection;
    private int startX;
    private int startY;

    private Set<Integer> cleanedPosition = new HashSet<>();
    // return false means SweepMonster can't start
    public boolean init(int currentBattery, int currentDirtCapacity, Direction currentDirection, int startX, int startY) {
        setCurrentBattery(currentBattery);
        setCurrentDirtCapacity(currentDirtCapacity);
        setCurrentDirection(currentDirection);

        setStartX(startX);
        setStartY(startY);
        if (currentBattery <= 0){
            return false;
        }

        if (currentDirtCapacity <= 0) {
            return false;
        }

        return true;
    }

    public void run (int[][] floor) {
        int n = floor.length;
        int m = floor[0].length;

        Stack<Integer> stack = new Stack<>();

        stack.add(startY * m + startX);
        // DFS
        while (stack.empty() != false) {
            int position = stack.pop();
            int x = position % m;
            int y = position / m;
            if (floor[y][x] == 1) { // if this grid can be accessible
                if (cleanedPosition.contains(position)) { // if has been cleaned, skip
                    continue;
                }
                // TODO Clean it

                int batteryConsume = 1;
                currentBattery -= batteryConsume;
                int dirtCapacityConsume = 1;
                currentDirtCapacity -= dirtCapacityConsume;
                cleanedPosition.add(y * m + x);
                int nextPosition = findNextPositionAndDirection(floor, x, y);
                if (nextPosition < 0) {
                    // return to start point
                    break;
                } else {
                    stack.add(nextPosition);
                }
            }
        }

        // TODO return to the start
    }

    public int getCurrentBattery() {
        return currentBattery;
    }

    public void setCurrentBattery(int currentBattery) {
        this.currentBattery = currentBattery;
    }

    public int getCurrentDirtCapacity() {
        return currentDirtCapacity;
    }

    public void setCurrentDirtCapacity(int currentDirtCapacity) {
        this.currentDirtCapacity = currentDirtCapacity;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    // if find next available position then return position value
    // if not, return -1
    private int findNextPositionAndDirection (int[][] floor, int curX, int curY) {
        int n = floor.length;
        int m = floor[0].length;

        // search for up grid
        if (curY - 1 >= 0 && floor[curY - 1][curX] == 1) {
            setCurrentDirection(Direction.NORTH);
            return curY * m + curX;
        }
        // search for left grid
        if (curX - 1 >= 0 && floor[curY][curX - 1] == 1) {
            setCurrentDirection(Direction.WEST);
            return curY * m + curX;
        }
        // search for down grid
        if (curY + 1 < n && floor[curY + 1][curX] == 1) {
            setCurrentDirection(Direction.SOUTH);
            return curY * m + curX;
        }
        // search for right grid
        if (curX + 1 < m && floor[curY][curX + 1] == 1) {
            setCurrentDirection(Direction.EAST);
            return curY * m + curX;
        }

        return -1;
    }
}


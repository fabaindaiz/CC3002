package model.map;

import java.util.*;

/**
 * This class represents the map where the units are located and the game is played.
 * <p>
 * The field is an undirected graph composed of <i>Location</i> nodes where the weight of every edge
 * of the graph is 1.
 * Since all cells of the map should be reachable, the graph must be connected.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Field implements IField {

    private Map<String, Location> map = new HashMap<>();
    private Random random = new Random();
    private StringBuilder builder = new StringBuilder();

    private int sideSquare;
    private int maxSize;

    @Override
    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    @Override
    public int getSize() {
        return map.size();
    }

    @Override
    public int getSideSquare() {
        return sideSquare;
    }

    @Override
    public void generateMap(int mapSize) {
        map.clear();
        maxSize = mapSize;
        double mult;
        if (mapSize <= 500)
            mult = 1.5;
        else if (mapSize <= 2000)
            mult = 2.0;
        else if (mapSize <= 6000)
            mult = 2.5;
        else
            mult = 3.0;
        sideSquare = (int) ((Math.sqrt(mapSize) + 1) * mult);

        recursiveMap(sideSquare / 2, sideSquare / 2, 0);
    }

    private void recursiveMap(int x, int y, int step) {
        if (getCell(x, y).getRow() == x || getCell(x, y).getColumn() == y) return;
        if (step > sideSquare * Math.log10(maxSize) || map.size() >= maxSize) return;
        addCells(true, new Location(x, y));

        int a1[] = {x - 1, y}, a2[] = {x + 1, y}, a3[] = {x, y - 1}, a4[] = {x, y + 1};
        ArrayList<int[]> order = new ArrayList<int[]>(List.of(a1, a2, a3, a4));
        if (x - 1 < 0) order.remove(a1);
        if (x + 1 >= sideSquare) order.remove(a2);
        if (y - 1 < 0) order.remove(a3);
        if (y + 1 >= sideSquare) order.remove(a4);

        int randomInt;
        while (map.size() < maxSize && order.size() > 1) {
            randomInt = (int) (random.nextFloat() * order.size());
            int[] next = order.get(randomInt);
            order.remove(randomInt);
            recursiveMap(next[0], next[1], step + 1);
        }
    }

    @Override
    public void printMap() {
        for (int i = 0; i < sideSquare; i++) {
            for (int j = 0; j < sideSquare; j++) {
                if (getCell(i, j).getRow() < 0 || getCell(i, j).getRow() < 0) System.out.print("  ");
                else if (i == sideSquare /2 && j == sideSquare / 2)
                    System.out.print("X ");
                else {
                    //System.out.print(" ("+ i +","+ j +")");
                    System.out.print(getCell(i, j).getNeighbours().size() + " ");
                    //System.out.print("x ");
                }
            }
            System.out.println("");
        }
        System.out.println("sideSquare> " + sideSquare + " step> " + Math.log10(maxSize) + " mapSize> " + getSize());
        System.out.println("");
    }

    @Override
    public void clearMap() {
        map = new HashMap<>();
    }

    @Override
    public void addCells(final boolean connectAll, final Location... cells) {
        for (Location cell : cells) {
            addCell(cell);
            Location[] adjacentCells = getAdjacentCells(cell);
            for (Location adjacentCell : adjacentCells) {
                if (connectAll || random.nextDouble() > 1.0 / 2 || cell.getNeighbours().size() < 2) { //Original: 3 y 1
                    addConnection(cell, adjacentCell);
                }
            }
        }
    }

    /**
     * Adds a cell to the map
     *
     * @param cell the location to be added
     */
    private void addCell(final Location cell) {
        map.put(cell.toString(), cell);
    }

    /**
     * Gets the possible adjacent cells to a given cell
     *
     * @param cell the location of the current cell
     * @return an array of the adjacent cells
     */
    private Location[] getAdjacentCells(final Location cell) {
        int row = cell.getRow(),
                col = cell.getColumn();
        return new Location[]{getCell(row - 1, col), getCell(row + 1, col), getCell(row, col - 1),
                getCell(row, col + 1)};
    }

    /**
     * Creates a connection between 2 cells
     */
    private void addConnection(Location cell1, Location cell2) {
        cell1.addNeighbour(cell2);
    }

    @Override
    public Location getCell(final int row, final int col) {
        String id = generateID(row, col);
        return map.getOrDefault(id, new InvalidLocation());
    }

    /**
     * Creates a map key from a row and a column
     *
     * @param row the row of the cell
     * @param col the column of the cell
     * @return a string of the form (row, col)
     */
    private String generateID(final int row, final int col) {
        builder.setLength(0);
        builder.append("(").append(row).append(", ").append(col).append(")");
        return builder.toString();
    }

    protected Map<String, Location> getMap() {
        return Map.copyOf(map);
    }

    /**
     * Checks if the map is connected using BFS.
     *
     * @return true if the map is connected, false otherwise.
     */
    public boolean isConnected() {
        Set<Location> visitedNodes = new HashSet<>();
        Queue<Location> toVisit = new LinkedList<>();
        toVisit.add(map.entrySet().iterator().next().getValue());
        while (!toVisit.isEmpty()) {
            if (visitedNodes.size() == map.size()) {
                return true;
            }
            Location currentNode = toVisit.poll();
            for (Location neighbour :
                    currentNode.getNeighbours()) {
                if (!visitedNodes.contains(neighbour)) {
                    visitedNodes.add(neighbour);
                    toVisit.add(neighbour);
                }
            }
        }
        return false;
    }

    /**
     * Removes a connection from two locations of the field
     */
    protected void removeConnection(final Location cell1, final Location cell2) {
        if (cell1.getNeighbours().size() > 1 && cell2.getNeighbours().size() > 1) {
            cell1.removeNeighbour(cell2);
        }
    }

    /**
     * Checks if two cells of the map are connected
     */
    protected boolean checkConnection(final Location cell1, final Location cell2) {
        return cell1.isNeighbour(cell2);
    }

}

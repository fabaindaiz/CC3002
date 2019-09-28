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
public class Field {

    private Map<String, Location> map = new HashMap<>();
    private Random random = new Random();
    private StringBuilder builder = new StringBuilder();
    int sideSquare;

    /**
     * Establece una semilla para el generador de numero aleatorios
     *
     * @param seed semilla a configurar
     */
    public void setSeed(long seed){ random.setSeed(seed); }

    /**
     * @return tamaño del mapa generado
     */
    public int getSize() { return map.size(); }

    /**
     * Genera un mapa aleatoria para la partida de un tamaño específico
     *
     * @param mapSize tamaño del mapa
     */
    public void generateMap (int mapSize) {
        sideSquare = (int) (Math.sqrt(mapSize) + 2);
        generateMapGaussian(mapSize);
    }

    public void generateMapGaussian (int mapSize) {
        int x, y;
        boolean mode = true;
        while (getSize() < (3*mapSize)/4) {
            x = (int) ((random.nextGaussian()*sideSquare/8)+sideSquare/2);
            y = (int) ((random.nextGaussian()*sideSquare/8)+sideSquare/2);
            if (x >= 0 && x < sideSquare && y >= 0 && y < sideSquare) {
                addCellForGenerator(mode, (x - 1), y, 0, sideSquare);
                addCellForGenerator(mode, (x + 1), y, 0, sideSquare);
                addCellForGenerator(mode, x, (y - 1), 0, sideSquare);
                addCellForGenerator(mode, x, (y + 1), 0, sideSquare);
                addCellForGenerator(true, x, y, 0, sideSquare);
            }
        }
        while (getSize() < mapSize) {
            x = (int) ((random.nextGaussian()*sideSquare/8)+(sideSquare)/2);
            y = (int) ((random.nextGaussian()*sideSquare/8)+(sideSquare)/2);
            addCellForGenerator(mode , x, y, 0, sideSquare);
        }
    }

    public void generateMapRecursive (int mapSize) {
        sideSquare = (int) (Math.sqrt(mapSize) + 2);
        revursiveMap(sideSquare/2, sideSquare/2, true);
    }

    public void revursiveMap (int x, int y, boolean mod) {
        boolean mode = true;
        addCellForGenerator(mode, x, y, 0, sideSquare);
    }

    public void addCellForGenerator(boolean connectAll, int x, int y, int minPosition, int maxPosition) {
        if (getCell(x, y).getRow() == -1)
            if(x >= minPosition && x < maxPosition && y >= minPosition && y < maxPosition)
                addCells(connectAll, new Location(x,y));
    }

    /**
     * Imprime el mapa en pantalla para facilitar el testeo
     */
    public void printMap() {
        int size = 0;
        for (int i = 0; i< sideSquare; i++) {
            for (int j = 0; j< sideSquare; j++) {
                if (getCell(i,j).getRow() == -1 ) System.out.print("  ");
                else {
                    size++;
                    System.out.print(getCell(i,j).getNeighbours().size() +" ");
                }
            }
            System.out.println("");
        }
        System.out.println("realSize> "+ size +" mapSize> "+ getSize() );
    }

    /**
     * Add cells to the map.
     *
     * @param connectAll a flag that indicates if all the cells should be connected to it's neighbours
     * @param cells      the locations that are going to be added to the map
     */
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

    /**
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the Location that represents the cell at (row, col)
     */
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

    public Map<String, Location> getMap() {
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
    public void removeConnection(final Location cell1, final Location cell2) {
        if (cell1.getNeighbours().size() > 1 && cell2.getNeighbours().size() > 1) {
            cell1.removeNeighbour(cell2);
        }
    }

    /**
     * Checks if two cells of the map are connected
     */
    public boolean checkConnection(final Location cell1, final Location cell2) {
        return cell1.isNeighbour(cell2);
    }

}

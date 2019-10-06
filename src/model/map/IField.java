package model.map;

/**
 * This interface represents a Field.
 * <p>
 * Este metodo es usado para agrupar la documentación y para facilitar algunas implementaciones
 *
 * @author Fabián Díaz
 * @since 2.0
 */
public interface IField {

    /**
     * Establece una semilla para el generador de numero aleatorios
     *
     * @param seed semilla a configurar
     */
    void setSeed(long seed);

    /**
     * @return tamaño del mapa generado
     */
    int getSize();

    /**
     * @return El tamaño de un lado del cuadrado del mapa
     */
    int getSideSquare();

    /**
     * Genera un mapa aleatoria para la partida de un tamaño específico
     * Idealmente usar solo tamaños inferiores a 5000
     *
     * @param mapSize tamaño del mapa
     */
    void generateMap(int mapSize);

    /**
     * Imprime el mapa en pantalla para facilitar el testeo
     */
    void printMap();

    void clearMap();

    /**
     * Add cells to the map.
     *
     * @param connectAll a flag that indicates if all the cells should be connected to it's neighbours
     * @param cells      the locations that are going to be added to the map
     */
    void addCells(boolean connectAll, Location... cells);

    /**
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the Location that represents the cell at (row, col)
     */
    Location getCell(int row, int col);
}

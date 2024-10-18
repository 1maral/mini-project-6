package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Maral Bat-Erdene
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  T[][] contents;
  private int height;
  private int width;
  private T defVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int width, int height, T def) {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } // if
    this.contents = (T[][]) new Object[height][width];
    this.height = height;
    this.width = width;
    this.defVal = def;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        this.contents[y][x] = (T) def;
      } // for(width)
    } // for(height)
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    if (this.contents[row][col] == null) {
      return this.defVal;
    } else {
      return this.contents[row][col];
    } // if/else
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    this.contents[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Resizes the contents array by adding a new row or column.
   *
   * @param field A string indicating whether to add a row or a column. 
   * 
   * @return A new 2D array with the increased size, containing the original values 
   *         from oldArray.
   */
  @SuppressWarnings("unchecked")
  private T[][] resize(String field) {
    int newHeight = this.height;
    int newWidth = this.width;
    // Create a new array with increased size
    if (field.equals("row")) {
      newHeight++;
    } else {
      newWidth++;
    } // if/else
    T[][] newArray = (T[][]) new Object[newHeight][newWidth];
    // Copy existing values to the new array
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        newArray[y][x] = this.contents[y][x];
      } // for (width)
    } // for (height)
    return newArray;
  } // resize(String)

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row) {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException();
    } // if
    this.contents = this.resize("row");
    // Shift all the values in the array
    for (int i = this.height - 1; i >= row; i--) {                
      this.contents[i + 1] = this.contents[i];
    } // for

    this.contents[row] = (T[]) new Object[this.width];
    for (int x = 0; x < this.width; x++) {
      this.contents[row][x] = this.defVal;
    } // for(width)
    this.height++;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException();
    } // if
    if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if

    this.contents = this.resize("row");
    // Shift all the values in the array
    for (int i = this.height - 1; i >= row; i--) {                
      this.contents[i + 1] = this.contents[i];
    } // for
    
    // Create a new array Object for row
    this.contents[row] = (T[]) new Object[this.width];
    for (int x = 0; x < this.width; x++) {
      this.contents[row][x] = vals[x];
    } // for(width)
    this.height++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException();
    } // if
  
    this.contents = this.resize("column");
    // Shift the values in all of the array
    for (int y = 0; y < this.height; y++) {
      for (int x = this.width - 1; x >= col; x--) {
        this.contents[y][x + 1] = this.contents[y][x];
      } // for(width)
    } // for(height)

    for (int y = 0; y < this.height; y++) {
      this.contents[y][col] = this.defVal;
    } // for(height)
    this.width++;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if

    this.contents = this.resize("column");
    // Shift the values in all of the array
    for (int y = 0; y < this.height; y++) {
      for (int x = this.width - 1; x >= col; x--) {
        this.contents[y][x + 1] = this.contents[y][x];
      } // for(width)
    } // for(height)

    for (int y = 0; y < this.height; y++) {
      this.contents[y][col] = vals[y];
    } // for(height)
    this.width++;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row >= this.height) {
      throw new IndexOutOfBoundsException();
    } // if

    // Shift all the values in the array
    for (int i = row; i < this.height - 1; i++) {                
      this.contents[i] = this.contents[i + 1];
    } // for
    
    this.contents[this.height - 1] = null;
    this.height--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException();
    } // if
  
    // Shift the values in all of the array
    for (int y = 0; y < this.height; y++) {
      for (int x = col; x < this.width - 1; x++) {
        this.contents[y][x] = this.contents[y][x + 1];
      } // for(width)
    } // for(height)

    for (int y = 0; y < this.height; y++) {
      this.contents[y][this.width - 1] = null;
    } // for(height)
    this.width--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throws IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
        // Checks to see if the rows and columns are appropriate.
        if (startRow < 0 || startRow >= this.height || startCol < 0 || startCol >= this.width) {
          throw new IndexOutOfBoundsException();
        } // if
        if (endRow < 0 || endRow > this.height || endCol < 0 || endCol > this.width) {
          throw new IndexOutOfBoundsException();
        } // if

        for (int y = startRow; y < endRow; y++) {
          for (int x = startCol; x < endCol; x++) {
            this.contents[y][x] = val;
          } // for(width)
        } // for(height)
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
        // Checks to see if the rows and columns are appropriate.
        if (startRow < 0 || startRow >= this.height || startCol < 0 || startCol >= this.width) {
          throw new IndexOutOfBoundsException();
        } // if
        if (endRow < 0 || endRow > this.height || endCol < 0 || endCol > this.width) {
          throw new IndexOutOfBoundsException();
        } // if
        // While within bounds of the matrix.
        
        while ((startRow < endRow) && (startCol < endCol)) {
          // Set the value at the current row and column.
          this.contents[startRow][startCol] = val;
          // Move to the next row and column based on deltaRow and deltaCol.
          startRow += deltaRow;
          startCol += deltaCol;
          // If we've reached the boundaries, break.
          if ((deltaRow > 0 && startRow >= endRow) || (deltaRow < 0 && startRow < endRow)) {
            break;
          } // if
          if ((deltaCol > 0 && startCol >= endCol) || (deltaCol < 0 && startCol < endCol)) {
              break;
          } // if
        } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix).
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    Matrix<T> copyMatrix = new MatrixV0<> (this.width, this.height, this.defVal);
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        copyMatrix.set(y, x, this.get(y, x));
      } // for(width)
    } // for(height)
    return copyMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    // Check if the other object is an instance of MatrixV0
    if (!(other instanceof MatrixV0)) {
      return false;
    } // if

    // Cast the other object to Matrix
    Matrix otherMatrix = (Matrix) other;

    // Check dimensions
    if (this.height != otherMatrix.height() || this.width != otherMatrix.width()) {
        return false;
    } // if

    // Looping over all the values to compare elements
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        if (!this.get(y, x).equals(otherMatrix.get(y, x))) {
          // Return false if any elements are not equal
          return false;
        } // if
      } // for(width)
    } // for(height)
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0

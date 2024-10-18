package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * A variety of tests for the Matrix class.
 *
 * @author Maral Bat-Erdene
 */
class TestFromMaral {
  // Test Constructor with default value
  @Test
  public void testConstructorWithDefaultValue() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 0);
    assertEquals(0, matrix.get(0, 0));
    assertEquals(0, matrix.get(2, 2));
  } // testConstructorWithDefaultValue

  // Test Constructor without default value (null)
  @Test
  public void testConstructorWithoutDefaultValue() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3);
    assertNull(matrix.get(0, 0));
  } // testConstructorWithoutDefaultValue

  // Test set() method with valid indices
  @Test
  public void testSetValidIndices() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 0);
    matrix.set(0, 0, 5);
    assertEquals(Integer.valueOf(5), matrix.get(0, 0));
  } // testSetValidIndices

  // Test height() method
  @Test
  public void testHeight() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 4, 0);
    assertEquals(4, matrix.height());
  } // testHeight

  // Test width() method
  @Test
  public void testWidth() {
    MatrixV0<Integer> matrix = new MatrixV0<>(5, 3, 0);
    assertEquals(5, matrix.width());
  } // testWidth

  // Test insertRow() method with specific values
  @Test
  public void testInsertRowWithValues() throws ArraySizeException {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 0);
    Integer[] vals = {10, 11, 12};
    matrix.insertRow(1, vals);
    assertEquals(4, matrix.height());
    assertEquals(Integer.valueOf(10), matrix.get(1, 0));
  } // testInsertRowWithValues

  // Test insertCol() method with default value
  @Test
  public void testInsertColWithDefaultValue() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 0);
    matrix.insertCol(1);
    assertEquals(4, matrix.width());
    assertEquals(0, matrix.get(0, 1));
  } // testInsertColWithDefaultValue

  // Test deleteRow() method
  @Test
  public void testDeleteRow() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 0);
    matrix.deleteRow(1);
    assertEquals(2, matrix.height());
    assertEquals(0, matrix.get(1, 0));
  } // testDeleteRow

  // Test deleteCol() method
  @Test
  public void testDeleteCol() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 0);
    matrix.deleteCol(1);
    assertEquals(2, matrix.width());
    assertEquals(0, matrix.get(0, 1));
  } // testDeleteCol

  // Test fillRegion() method
  @Test
  public void testFillRegion() {
    MatrixV0<Integer> matrix = new MatrixV0<>(5, 5, 0);
    matrix.fillRegion(1, 1, 4, 4, 7);
    assertEquals(7, matrix.get(2, 2));
    assertEquals(0, matrix.get(0, 0));
  } // testFillRegion

  @Test
  public void testFillLineHorizontal() {
    // Fill a horizontal line on row 2, from column 1 to 3 with value 7
    MatrixV0<Integer> matrix = new MatrixV0<>(4, 4, 0);
    matrix.fillLine(2, 1, 0, 1, 3, 4, 7);
    assertEquals(7, matrix.get(2, 1));
    assertEquals(7, matrix.get(2, 2));
    assertEquals(7, matrix.get(2, 3));
    assertEquals(0, matrix.get(2, 0));
  } // testFillLineHorizontal

  @Test
  public void testFillLineDiagonal() {
    // Fill a diagonal line from (0,0) to (3,3) with value 9
    MatrixV0<Integer> matrix = new MatrixV0<>(4, 4, 0);
    matrix.fillLine(0, 0, 1, 1, 4, 4, 9);
    assertEquals(9, matrix.get(0, 0));
    assertEquals(9, matrix.get(1, 1));
    assertEquals(9, matrix.get(2, 2));
    assertEquals(9, matrix.get(3, 3));
    assertEquals(0, matrix.get(0, 1));
    assertEquals(0, matrix.get(0, 2));
    assertEquals(0, matrix.get(0, 3));
  } // testFillLineDiagonal
} // TestFromMaral

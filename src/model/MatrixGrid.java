package model;

import java.util.Arrays;


public class MatrixGrid {
    private Box first;
    private int numColumns;
    private int numRows;

    public MatrixGrid(int m, int n) {
        numColumns = m;
        numRows = n;
        createGrid();
        //createMatrix();
    }

    public Box getFirst() {
        return this.first;
    }

    public int getNumColumns() {
        return this.numColumns;
    }

    public int getNumRows() {
        return this.numRows;
    }

    /* public void createMatrix(){
        int id =  1;
        first = new Box(0, 0, id);
        Box lastCreatedBox= first;

        int cols = 1;
        int row = 0;

        createCols(lastCreatedBox, row, cols, id);

        
    }

    public void createCols (Box lastCreatedBox, int row, int cols, int id){
        if(row < numRows){

            lastCreatedBox = createRows(lastCreatedBox, row, cols, id);
           
            lastCreatedBox = createUpper(lastCreatedBox, row,cols, id);
            row++;

            createCols(lastCreatedBox, row, cols, id);
        }
    }

    public Box createRows(Box lastCreatedBox, int row, int cols, int id){
        if(cols < numColumns){

            if(row%2==0){
                lastCreatedBox = createNext(lastCreatedBox, row, cols, id);
                cols++;
                id++;
            } else{
                lastCreatedBox = createBefore(lastCreatedBox, row, cols, id);
                cols++;row++;
                id++;
            }

            createRows(lastCreatedBox, row, cols, id);
        }

        return lastCreatedBox;
    }

    public Box createNext(Box sideBox, int r , int c , int id){
        Box newBox = new Box(r, c, id );
        newBox.setPrevious(sideBox);
        sideBox.setNext(newBox);

        return newBox;
    }

    public Box createBefore(Box sideBox, int r , int c , int id){
        Box newBox = new Box(r, c, id );
        newBox.setNext(sideBox);
        sideBox.setPrevious(newBox);

        return newBox;
    }

    public Box createUpper(Box sideBox, int r , int c , int id){
        Box newBox = new Box(r, c, id );
        newBox.setBelow(sideBox);
        sideBox.setAbove(newBox);

        return newBox;
    } */

    /* Box actual = first;

        for (int i = 0; i < numRows; i++){

            for(int j = 0 ; j < numColumns ; j++){
                System.out.println(actual.getColumn() + " c + r " + actual.getRow());
                if(i%2 != 0 ){
                    actual = actual.getNext();
                } else {
                    actual = actual.getbefore();
                }
            }
            actual = actual.getBelow();

        } */



    public void createGrid() {
        int size = numColumns * numRows;
        int id = (numRows % 2 == 0) ? size : (size) - numColumns + 1;

        first = new Box(0, 0, id);

        //System.out.println(id + " Primera");

        createRows(0, 0, first);
    }

    public void createRows(int i, int j, Box currentFirstRow) {

        Box up = null;
        //createColumn(i, 0, currentFirstRow, currentFirstRow.getAbove()); /// old
        createColumn(i, 0, currentFirstRow, up);
        up =  currentFirstRow;
        
        
        i++;
        if (i < numRows) {

            int size = numColumns * (numRows-i);

            int id = (numRows % 2 == 0) ?  (size - numColumns + 1) : size ;

            //System.out.println(id);

            Box downFirstRow = new Box(i, 0, id); // estaba con i+1


            downFirstRow.setAbove(currentFirstRow);
            currentFirstRow.setBelow(downFirstRow);
            createRows(i, 0, downFirstRow); // j-1
            
        }
    }

    // Impar es suma desde la primea box que se crea
    // Par es resta desde la primera box de la fila, al multiplicar el tamaño de esa
    // sección de matriz

    public void createColumn(int i, int j, Box previous, Box above) {
        //System.out.println(j + " < " + numColumns);
        //System.out.println(previous.getId() + 1 + " Previo");
        j++;
        if (j < numColumns) {

            int id = (i % 2 == 0) ? previous.getId() - 1 : previous.getId() + 1 ;
            
            Box current = new Box(i, j, id);

            //System.out.println(id);

            current.setPrevious(previous);
            previous.setNext(current);

            if (above != null) {
                

                current.setAbove(above);
                above.setBelow(current);
                if (above.getNext() != null) {
                    above = above.getNext();
                }
            }
            
            createColumn(i, j, current, above);
        }
    }

    public void showMatriz() {

        /* Box actual = first;

        for (int i = 0; i < numRows; i++){

            for(int j = 0 ; j < numColumns ; j++){
                System.out.println(actual.getColumn() + " c + r " + actual.getRow());
                actual = actual.getNext();
            }
            actual = actual.getBelow();

        } */

        Box actual = first;
        int[][] mat = new int[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            Box primeroDeLinea = actual;

            for (int j = 0; j < numColumns; j++) {
                
                //System.out.println(i + " " + j);
                if (actual != null) {
                    mat[i][j] = actual.getId();
                    actual = actual.getNext();
                } else{
                    mat[i][j] = -1;
                }
            }

            actual = primeroDeLinea.getBelow();
            //actual = primeroDeLinea.getAbove();

        }

        for (int[] is : mat) {
            System.out.println(Arrays.toString(is));
        }
       

    } 

}

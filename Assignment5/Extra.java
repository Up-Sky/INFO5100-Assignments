import java.util.ArrayList;
import java.util.List;

public class Extra {
	/*Extra Credit: Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order. (Score 2)*/
	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
        if (matrix.length == 0) {
            return list;
        }
        int Up = 0;
        int Down = matrix.length-1;
        int Left = 0;
        int Right = matrix[0].length - 1;
        
        while (Up <= Down && Left <= Right) {
            // left -> Right
            for (int j = Left; j <= Right; j ++) {
                list.add(matrix[Up][j]);
            }
            Up++;
            // Up -> Down
            for (int j = Up; j <= Down; j ++) {
                list.add(matrix[j][Right]);
            }
            Right--;
            if (Up <= Down) {
                // Right -> Left
                for (int j = Right; j >= Left; j --) {
                    list.add(matrix[Down][j]);
                }
            }
            Down--;
            if (Left <= Right) {
                // Down -> Up
                for (int j = Down; j >= Up; j --) {
                    list.add(matrix[j][Left]);
                }
            }
            Left ++;
        }
        return list;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[3][3];
		int count = 1;
		for(int i = 0; i< 3; i++)
			for(int j = 0; j < 3; j ++) {
				matrix[i][j] = count;
				count += 1;
			}
		
		List<Integer> list = spiralOrder(matrix);
		for(int i = 0; i < list.size(); i++)
			System.out.print(list.get(i) + " ");
	}
}

import java.util.ArrayList;

public class midterm {
	
	//Question 1
	public int[] reverseEvenIndices(int[] nums) {
		int tmp, n =nums.length;
		if(n % 2 == 0)
			n -= 1;
		for(int i = 0; i < n / 2; i += 2) {
			tmp = nums[i];
			nums[i] = nums[n - 1 - i];
			nums[n - 1 - i] = tmp;
		}
		return nums;
	}
	
	//Question 2
	public int arrangeCoins(int n) {
		//1 + 2 + ... + n = n * (n + 1) / 2
		for(int i = 1; i < n; i ++) {
			if(i * (i + 1) / 2 > n)
				return (i - 1);
		}
		return n;
	}
	
	//Question 3
	public int minMoves(int[] nums) {
		/*increasing (n - 1) elements by 1 and try to find minimum moves <=> decreasing the maximum one element by 1 each time.
		 * At last all elements will be equal to the minimum element. So just sum up (every element - the minimum element).
		 * The result would be minMoves. 
		 */
		int n = nums.length, minValue = Integer.MAX_VALUE, minMoves = 0;
		if(n < 2)
			return 0;
		for(int i = 0; i < n; i++) {
			if(nums[i] < minValue)
				minValue = nums[i];
		}
		for(int i = 0; i < n; i++)
			minMoves += (nums[i] - minValue);
		
		return minMoves;
	}
	
	//Question 4
	public int countNumberOfPossibleWays(int m, int n, int x) {
		//This method suppose every dice is different. For example: 1 1 2 and 2 1 1 are two possible ways to reach sum 4.
		if(x < n || x > m * n)
			return 0;
		if(n == 1)
			return 1;
		int sum = 0;
		for(int i = 1; i <= m; i++)
			sum += countNumberOfPossibleWays(m, n - 1, x - i);
		return sum;
	}
}

//Question 5
class Cell {
	int x, y;
	Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}

class Solution {
	/*I understand this is a backtracking question, however, I think there may be more than one resulting paths of a maze.
	 * So I define an ArrayList<ArrayList<Cell>> solution to save all possible paths, and return the first ArrayList element as return value. 
	 * If the size of solution is 0, it will return an empty ArrayList<Cell>().
	 */
	public ArrayList<Cell> findPath(int [][] maze) {
		ArrayList<ArrayList<Cell>> solution = new ArrayList<ArrayList<Cell>>();
		addPath(maze, solution, new ArrayList<Cell>(), 0, 0);
		//System.out.println(solution);
		if(solution.size() > 0)
			return solution.get(0);
		return new ArrayList<Cell>();
		
	}
	public void addPath(int [][] maze, ArrayList<ArrayList<Cell>> solution, ArrayList<Cell> path, int i, int j) {
		int n = maze.length;
		if(i == n - 1 && j == n - 1) {
			path.add(new Cell(i, j));
			solution.add(path);
			return;
		}
		else if(isAvailable(maze, i, j)) {
			path.add(new Cell(i, j));
			addPath(maze, solution, new ArrayList<Cell>(path), i + 1, j);
			addPath(maze, solution, path, i, j + 1);
		}
		else
			return;
			
	}
	public boolean isAvailable(int [][] maze, int i, int j) {
		int n = maze.length;
		if(i < n - 1 && j <= n - 1 && maze[i][j] == 1 && maze[i + 1][j] == 1)
			return true;
		if(j < n - 1 && i <= n - 1 && maze[i][j] == 1 && maze[i][j + 1] == 1)
			return true;
		return false;
	}
}

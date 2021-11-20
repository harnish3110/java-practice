package interview.google;

import java.util.Collection;
import java.util.Stack;

public class AllPaths {
    public static int ROW;
    public static int COL;

    public Point start = null;


    private static class Point {
        int row;
        int col;
        char val;

        Point(int x, int y, char val) {
            this.row = x;
            this.col = y;
            this.val = val;
        }
    }

    private static class PointPath {
        int distance;
        Point point;

        PointPath(int dist, Point p) {
            this.point = p;
            this.distance = dist;
        }

    }

    public void findStartPoint(char[][] grid) {
        Outer:
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (grid[i][j] == 's') {
                    start = new Point(i, j, grid[i][j]);
                    break Outer;
                }
            }
        }
    }

    public void startSearchWithStack(char[][] grid) {
        if (grid == null || grid.length == 0) {
            System.out.println("No GRID Found");
            return;
        }

        //Find the source in the grid
        ROW = grid.length;
        COL = grid[0].length;

        findStartPoint(grid);

        // UP
        if (isValid(start.row, start.col - 1, grid)) {
            System.out.println("DIRECTION UP");
            boolean[][] visited = new boolean[ROW][COL];
            visited[start.row][start.col] = true;
            Stack<PointPath> stack = new Stack<>();
            stack.add(new PointPath(0, new Point(start.row, start.col, grid[start.row][start.col])));
            PointPath point = new PointPath(1, new Point(start.row, start.col - 1, grid[start.row][start.col - 1]));
            findPaths(grid, stack, point, visited);
            System.out.println("-------------------------------");
        }

        // DOWN
        if (isValid(start.row + 1, start.col, grid)) {
            System.out.println("DIRECTION DOWN");
            Stack<PointPath> stack = new Stack<>();
            boolean[][] visited = new boolean[ROW][COL];
            visited[start.row][start.col] = true;
            stack.add(new PointPath(0, new Point(start.row, start.col, grid[start.row][start.col])));
            PointPath point = new PointPath(1, new Point(start.row + 1, start.col, grid[start.row + 1][start.col]));
            findPaths(grid, stack, point, visited);
            System.out.println("-------------------------------");
        }

        // RIGHT
        if (isValid(start.row, start.col + 1, grid)) {
            System.out.println("DIRECTION RIGHT");
            Stack<PointPath> stack = new Stack<>();
            boolean[][] visited = new boolean[ROW][COL];
            visited[start.row][start.col] = true;
            stack.add(new PointPath(0, new Point(start.row, start.col, grid[start.row][start.col])));
            PointPath point = new PointPath(1, new Point(start.row, start.col + 1, grid[start.row][start.col + 1]));
            findPaths(grid, stack, point, visited);
            System.out.println("-------------------------------");
        }

        // LEFT
        if (isValid(start.row - 1, start.col, grid)) {
            System.out.println("DIRECTION LEFT");
            Stack<PointPath> stack = new Stack<>();
            boolean[][] visited = new boolean[ROW][COL];
            visited[start.row][start.col] = true;
            stack.add(new PointPath(0, new Point(start.row, start.col, grid[start.row][start.col])));
            PointPath point = new PointPath(1, new Point(start.row - 1, start.col, grid[start.row - 1][start.col]));
            findPaths(grid, stack, point, visited);
            System.out.println("-------------------------------");
        }


    }

    public void findPaths(char[][] grid, Stack<PointPath> path, PointPath current, boolean[][] visited) {
        int x = current.point.row;
        int y = current.point.col;
        int dist = current.distance;

        // if the last cell is reached, print the route

        //Empty Path returning
        if (path.isEmpty()) return;


        path.add(current);

        if (grid[x][y] == 'd') {
            printPath(path);
//            path.clear();
            return;
        }

        PointPath newPoint;

        // move right
        if (isValid(x, y + 1, grid, visited)) {

            newPoint = new PointPath(dist + 1, new Point(x, y + 1, grid[x][y + 1]));
            findPaths(grid, path, newPoint, visited);
        }

        // move down
        if (isValid(x + 1, y, grid, visited)) {
            visited[x + 1][y] = true;
            newPoint = new PointPath(dist + 1, new Point(x + 1, y, grid[x + 1][y]));
            findPaths(grid, path, newPoint, visited);
        }

        // move left
        if (isValid(x, y - 1, grid, visited)) {
            visited[x][y - 1] = true;
            newPoint = new PointPath(dist + 1, new Point(x, y - 1, grid[x][y - 1]));
            findPaths(grid, path, newPoint, visited);
        }

        // move up
        if (isValid(x - 1, y, grid, visited)) {
            visited[x - 1][y] = true;
            newPoint = new PointPath(dist + 1, new Point(x - 1, y, grid[x - 1][y]));
            findPaths(grid, path, newPoint, visited);
        }

        // backtrack: remove the current cell from the path
        path.pop();
    }

    public boolean isValid(int x, int y, char[][] grid, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != '0' && !visited[x][y];
    }

    public boolean isValid(int x, int y, char[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != '0';
    }

    public void printPath(Collection<PointPath> path) {
        char[][] mat = new char[ROW][COL];
        /*
        path.forEach(pointPath -> {
        System.out.println("Value= " + pointPath.point.val + "Row: " + pointPath.point.row + "  Col: " + pointPath.point.col);
        System.out.println("Distance = " + pointPath.distance);
        });
        */
        for (PointPath pointPath : path) {
            mat[pointPath.point.row][pointPath.point.col] = 1;
        }
        for (int i = 0; i < ROW; i++) {
            System.out.print("{ ");
            for (int j = 0; j < COL; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println(" }");
        }

    }

    public static void main(String[] args) {
        char[][] grid = {
                {'0', 's', '*', '*', '*'},
                {'*', '*', '*', '0', '*'},
                {'*', '0', '*', '0', '*'},
                {'*', '0', '*', '0', '*'},
                {'*', '*', '*', '0', 'd'}};


        new AllPaths().startSearchWithStack(grid);
    }
}

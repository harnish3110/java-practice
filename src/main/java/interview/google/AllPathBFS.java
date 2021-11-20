package interview.google;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class AllPathBFS {
    public Point start;
    public Point end;
    public char[][] grid, grid_copy;
    public int ROW, COL;
    public final int WALL = '0';

    public static final char DOWN = 8595;
    public static final char UP = 8593;
    public static final char LEFT = 8592;
    public static final char RIGHT = 8594;


    public AllPathBFS(char[][] grid) {

        this.start = new Point(-1, -1, 'S', -1);
        this.end = new Point(-1, -1, 'D', -1);
        this.grid = grid;
        // Making a copy of the provided grid
        this.ROW = grid.length;
        this.COL = grid[0].length;

        this.grid_copy = new char[this.ROW][this.COL];
//        for (int i = 0; i < this.ROW; i++) {
//            if (this.COL >= 0) System.arraycopy(grid[i], 0, this.grid_copy[i], 0, this.COL);
//        }
    }

    public static AllPathBFS createInstance(char[][] grid) {
        if (grid == null || grid.length == 0) return null;
        else {
            return new AllPathBFS(grid);
        }
    }

    private static class Point {
        int x, y, distance;
        char value;

        Point(int x, int y, char value, int dist) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.distance = dist;
        }
    }

    private void findStartPoint() {
        Outer:
        for (int i = 0; i < this.ROW; i++) {
            for (int j = 0; j < this.COL; j++) {
                if (this.grid[i][j] == 's' || this.grid[i][j] == 'S') {
                    this.start.x = i;
                    this.start.y = j;
                    this.start.distance = 0;
                    break Outer;
                }
            }
        }
    }

    public void findShortestDistance() {
        if (this.grid == null || this.grid.length == 0) {
            System.out.println("NO SOLUTION");
            return;
        }
        this.findStartPoint();

        Queue<Point> queue;
        boolean[][] valid;
        Point point;

        // Right
        if (this.isValid(this.start.x, this.start.y + 1, null)) {
            System.out.println("Starting RIGHT");
            queue = new LinkedList<>();
            valid = new boolean[this.ROW][this.COL];
            valid[this.start.x][this.start.y] = true;
            point = new Point(this.start.x, this.start.y + 1, RIGHT, 1);
//            queue.add(start);
            queue.add(point);
            this.findPath(queue, valid);
            System.out.println("----------------------------");
        }

        // Down
        if (this.isValid(this.start.x + 1, this.start.y, null)) {
            System.out.println("Starting DOWN");
            queue = new LinkedList<>();
            valid = new boolean[this.ROW][this.COL];
            valid[this.start.x][this.start.y] = true;
            point = new Point(this.start.x + 1, this.start.y, DOWN, 1);
            queue.add(start);
            queue.add(point);
            this.findPath(queue, valid);
            System.out.println("----------------------------");
        }

        // LEFT
        if (this.isValid(this.start.x, this.start.y - 1, null)) {
            System.out.println("Starting LEFT");
            queue = new LinkedList<>();
            valid = new boolean[this.ROW][this.COL];
            valid[this.start.x][this.start.y] = true;
            point = new Point(this.start.x, this.start.y - 1, LEFT, 1);
            queue.add(start);
            queue.add(point);
            this.findPath(queue, valid);
            System.out.println("----------------------------");
        }

        // UP
        if (this.isValid(this.start.x - 1, this.start.y, null)) {
            System.out.println("Starting UP");
            queue = new LinkedList<>();
            valid = new boolean[this.ROW][this.COL];
            valid[this.start.x][this.start.y] = true;
            point = new Point(this.start.x - 1, this.start.y, UP, 1);
            queue.add(start);
            queue.add(point);
            this.findPath(queue, valid);
            System.out.println("----------------------------");
        }

    }

    private void findPath(Queue<Point> queue, boolean[][] valid) {
        while (!queue.isEmpty()) {
            Point point = queue.remove();
            valid[point.x][point.y] = true;
            if (this.grid[point.x][point.y] == 'D' || this.grid[point.x][point.y] == 'd') {
                //Done
                this.end.x = point.x;
                this.end.y = point.y;
                this.end.distance = point.distance;
                System.out.println("The distance is = " + this.end.distance);
                System.out.println("Row: " + (this.end.x + 1) + " Col: " + (this.end.y + 1));
                break;
            }

            // Right
            if (this.isValid(point.x, point.y + 1, valid)) {
                queue.add(new Point(point.x, point.y + 1, RIGHT, point.distance + 1));
            }

            //Down
            if (this.isValid(point.x + 1, point.y, valid)) {
                queue.add(new Point(point.x + 1, point.y, DOWN, point.distance + 1));
            }

            // Left
            if (this.isValid(point.x, point.y - 1, valid)) {
                queue.add(new Point(point.x, point.y - 1, LEFT, point.distance + 1));
            }

            // Up
            if (this.isValid(point.x - 1, point.y, valid)) {
                queue.add(new Point(point.x - 1, point.y, UP, point.distance + 1));
            }
        }

    }

    private boolean isValid(int x, int y, boolean[][] visited) {
        if (visited != null) {
            return x >= 0 && y >= 0 && x < this.ROW && y < this.COL && this.grid[x][y] != this.WALL && !visited[x][y];
        } else {
            return x >= 0 && y >= 0 && x < this.ROW && y < this.COL && this.grid[x][y] != this.WALL;
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'0', '0', '*', '*', '*'},
                {'*', '*', '*', '0', '*'},
                {'s', '0', '*', '0', '*'},
                {'*', '0', '*', '0', '*'},
                {'*', '*', '*', '0', 'd'}};

        AllPathBFS bfs = new AllPathBFS(grid);
        bfs.findShortestDistance();
    }
}

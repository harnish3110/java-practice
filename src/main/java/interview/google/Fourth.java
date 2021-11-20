package interview.google;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*Given a matrix of N*M order. Find the shortest distance from a source cell to a destination cell, traversing through limited cells only. Also you can move only up, down, left and right. If found output the distance else -1.
s represents ‘source’
d represents ‘destination’
* represents cell you can travel
0 represents cell you can not travel
This problem is meant for single source and destination.
* */

public class Fourth {

    public static List<Integer> result = null;

    public class QItem {
        int row;
        int col;
        int dist;

        public QItem(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public int minDistance(char[][] grid) {
        QItem source = new QItem(0, 0, 0);

        // To keep track of visited QItems. Marking
        // blocked cells as visited.
        // Finding the start point
        firstLoop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                // Finding source
                if (grid[i][j] == 's') {
                    source.row = i;
                    source.col = j;
                    break firstLoop;
                }
            }
        }

        // applying BFS on matrix cells starting from source
        Queue<QItem> queue = new LinkedList<>();
        queue.add(new QItem(source.row, source.col, 0));

        result = new ArrayList<>();

        boolean[][] visited
                = new boolean[grid.length][grid[0].length];
        visited[source.row][source.col] = true;

        while (!queue.isEmpty()) {
            QItem p = queue.remove();

            // Destination found;
            if (grid[p.row][p.col] == 'd') {
                result.add(p.dist);
//                return p.dist;
            }

            // moving up
            if (isValid(p.row - 1, p.col, grid, visited)) {
                queue.add(new QItem(p.row - 1, p.col,
                        p.dist + 1));
                visited[p.row - 1][p.col] = true;
            }

            // moving down
            if (isValid(p.row + 1, p.col, grid, visited)) {
                queue.add(new QItem(p.row + 1, p.col,
                        p.dist + 1));
                visited[p.row + 1][p.col] = true;
            }

            // moving left
            if (isValid(p.row, p.col - 1, grid, visited)) {
                queue.add(new QItem(p.row, p.col - 1,
                        p.dist + 1));
                visited[p.row][p.col - 1] = true;
            }

            // moving right
            if (isValid(p.row, p.col + 1, grid,
                    visited)) {
                queue.add(new QItem(p.row, p.col + 1,
                        p.dist + 1));
                visited[p.row][p.col + 1] = true;
            }
        }
        return -1;
    }

    // checking where it's valid or not
    private boolean isValid(int x, int y,
                            char[][] grid,
                            boolean[][] visited) {
        return x >= 0 && y >= 0 && x < grid.length
                && y < grid[0].length && grid[x][y] != '0'
                && !visited[x][y];

        /*
        if (x >= 0 && y >= 0 && x < grid.length
                && y < grid[0].length && grid[x][y] != '0'
                && visited[x][y] == false) {
            return true;
        }
        return false;
        */


    }

    public static void main(String[] srgs) {
        Fourth f = new Fourth();
        char[][] grid = {{'0', '*', '0', 's'},
                {'*', '0', '*', '*'},
                {'0', '*', '*', '*'},
                {'d', '*', '*', '*'}};

        char[][] grid2 = {
                {'s', '0', '*', '*', '*'},
                {'*', '*', '*', '0', '*'},
                {'*', '0', '*', '0', '*'},
                {'*', '0', '*', '0', '*'},
                {'*', '*', '*', '0', 'd'}};

//        System.out.println(f.minDistance(grid));
//        System.out.println(Fourth.result);

        System.out.println(f.minDistance(grid2));
        System.out.println(Fourth.result);


    }

}

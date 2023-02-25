package com.wil.practice.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathWithMaxScore {

    private static final int INF = -999;
    private static final int valMod = (int)Math.pow(10, 9)+7;
    int n;

    private static final int[][] dirs = new int[][]{{-1,0}, {0,-1}, {-1,-1}};

    /**
     * Dynamic programming
     * @param board
     * @return
     */
    public int[] getMaxScoreAndPathNum(List<String> board) {
        n = board.size();
        char[][] charArr = new char[n][n];
        for(int i =0; i<n; i++) {
            charArr[i] = board.get(i).toCharArray();
        }

        int[][] scores = new int[n][n];
        int[][] paths = new int[n][n];
        for (int i=0; i<n; i++) {
            for(int j=0; j<n; j++){
                if(charArr[i][j] == 'X') {
                    scores[i][j] = INF;
                    paths[i][j] = 0;
                    continue;
                } else if(charArr[i][j] == 'E') {
                    scores[i][j] = 0;
                    paths[i][j] = 1;
                    continue;
                }

                for (int[] dir:dirs) {
                    if(i+dir[0]<0 || j+dir[1]<0 || charArr[i+dir[0]][j+dir[1]] == 'X') {
                        continue;
                    }
                    int score = charArr[i][j] == 'S'?0: charArr[i][j] - '0';
                    score += scores[i+dir[0]][j+dir[1]];
                    if(score>scores[i][j]) {
                        scores[i][j] = score;
                        paths[i][j] = paths[i+dir[0]][j+dir[1]];
                    } else if(score == scores[i][j]) {
                        paths[i][j] += paths[i+dir[0]][j+dir[1]];
                        paths[i][j] %= valMod;
                    }
                }
            }
        }
        int[] result = new int[]{scores[n-1][n-1], paths[n-1][n-1]};
        return result;
    }

    /**
     * Using Memory Searching
     * @param board
     * @return
     */
    public int[] findPathByMemoSearch(List<String> board) {
            n = board.size();
            char[][] charArr = new char[n][n];
            for (int i=0; i<n; i++) {
                charArr[i] = board.get(i).toCharArray();
            }
            int[][] scores = new int [n][n];
            int[][] paths = new int[n][n];
            int[] result = dfs(n-1, n-1, charArr, scores, paths);
            return result[1]==0?new int[]{0,0}:result;
    }

    private int[] dfs(int endX, int endY, char[][] charArr, int[][]scores, int[][]paths) {
        if (charArr[endX][endY] == 'E') {
            scores[endX][endY] = 0;
            paths[endX][endY] = 1;
            return new int[]{0, 1};
        }
        if(charArr[endX][endY] == 'X') {
            scores[endX][endY] = 0;
            paths[endX][endY] = 0;
            return new int[] {0, 0};
        }
        if(scores[endX][endY]!=0 || paths[endX][endY]!=0) {
            return new int[]{scores[endX][endY], paths[endX][endY]};
        }

        for (int[] dir : dirs) {
            if (endX+dir[0]<0 || endY+dir[1]<0 || charArr[endX+dir[0]][endY+dir[1]] == 'X') {
                continue;
            } else {
                int score = charArr[endX][endY] == 'S' ? 0 : charArr[endX][endY] - '0';
                int[] nextMove = dfs(endX + dir[0], endY + dir[1], charArr, scores, paths);
                int tmpScore = nextMove[0] + score;
                if (tmpScore > scores[endX][endY]) {
                    scores[endX][endY] = tmpScore;
                    scores[endX][endY] %= valMod;
                    paths[endX][endY] = nextMove[1];
                } else if (tmpScore == scores[endX][endY]) {
                    paths[endX][endY] += nextMove[1];
                    paths[endX][endY] %= valMod;
                }
            }
        }
        return new int[] {scores[endX][endY], paths[endX][endY]};
    }

    /**
     * Dynamic programming
     * @param board
     * @return
     */
    public int[] findPathWithMaxScore(List<String> board) {
        n = board.size();
        char[][] charArray = new char[n][n];
        for(int i =0; i<n; i++) {
            charArray[i] = board.get(i).toCharArray();
        }
        int[] scores = new int[n*n];
        int[] paths = new int[n*n];

        for(int i = 0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int index = map2Dto1D(i, j);
                if(i==0 && j==0){
                    paths[index] = 1;
                    scores[index] = 0;
                    continue;
                }

                if(charArray[i][j] == 'X')  {
                    scores[index] = INF;
                    paths[index] = 0;
                    continue;
                }
                //char - '0'  -> int
                int score = (i==n-1 && j==n-1)? 0:charArray[i][j]-'0';
                int maxScore = INF;
                int pathNum = 0;

                int tmpScore;
                int tmpPathNum;
                int[] tmpStat;
                if(i-1>=0) {
                    tmpScore = scores[map2Dto1D(i-1, j)] + score;
                    tmpPathNum = paths[map2Dto1D(i-1, j)];
                    tmpStat = getMaxStat(tmpScore, tmpPathNum, maxScore, pathNum);
                    maxScore = tmpStat[0];
                    pathNum = tmpStat[1];
                }

                if(j-1 >=0) {
                    tmpScore = scores[map2Dto1D(i, j-1)] + score;
                    tmpPathNum = paths[map2Dto1D(i, j-1)];
                    tmpStat = getMaxStat(tmpScore, tmpPathNum, maxScore, pathNum);
                    maxScore = tmpStat[0];
                    pathNum = tmpStat[1];
                }

                if(i-1 >=0 && j-1>=0) {
                    tmpScore = scores[map2Dto1D(i-1, j-1)] + score;
                    tmpPathNum = paths[map2Dto1D(i-1, j-1)];
                    tmpStat = getMaxStat(tmpScore, tmpPathNum, maxScore, pathNum);
                    maxScore = tmpStat[0];
                    pathNum = tmpStat[1];
                }

                scores[index] = maxScore<0? INF:maxScore;
                paths[index] = pathNum;

            }
        }
        int[] result = new int[2];
        if(scores[map2Dto1D(n-1, n-1)] == INF) {
            result[0] = 0;
            result[1] = 0;
        } else {
            result[0] = scores[map2Dto1D(n-1, n-1)];
            result[1] = paths[map2Dto1D(n-1, n-1)];
        }
        return result;
    }

    int[] getMaxStat(int tmpScore, int tmpPathNum, int maxScore, int pathNum) {
        int[] tmpStat = new int[] {maxScore, pathNum};
        if(tmpScore>maxScore) {
            tmpStat[0] = tmpScore;
            tmpStat[1] = tmpPathNum;
        } else if (tmpScore == maxScore && tmpScore != INF) {
            tmpStat[1] += tmpPathNum;
        }
        tmpStat[1] %= valMod;
        return tmpStat;
    }

    int map2Dto1D(int x, int y) {
        return x*n+y;
    }


    public static void main(String[] args) {
        List<String> board = new ArrayList<>();
        board.add("E23");
        board.add("2X2");
        board.add("12S");
        List<String> board2 = new ArrayList<>();
        board2.add("E12");
        board2.add("1X1");
        board2.add("21S");
        PathWithMaxScore pathMax = new PathWithMaxScore();
        System.out.println(Arrays.toString(pathMax.findPathWithMaxScore(board)));
        System.out.println(Arrays.toString(pathMax.findPathWithMaxScore(board2)));
        List<String> board3 = new ArrayList<>();
        board3.add("E11");
        board3.add("XXX");
        board3.add("11S");
        PathWithMaxScore pathMax2 = new PathWithMaxScore();
        System.out.println(Arrays.toString(pathMax2.findPathByMemoSearch(board3)));
    }

}

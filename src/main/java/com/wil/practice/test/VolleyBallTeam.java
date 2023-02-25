package com.wil.practice.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lu Jiang
 */
public class VolleyBallTeam {
    String teamName;
    ArrayList<String> gameResultsForSeason;
    double gamePoints;
    int numOfGames;

    public VolleyBallTeam(String teamName, double gamePoints, int numOfGames) {
        if(teamName!=null && !teamName.isEmpty() && gamePoints >=0 && numOfGames >=0) {
            this.teamName = teamName;
            this.gamePoints = gamePoints;
            this.numOfGames = numOfGames;
        } else {
            throw new IllegalArgumentException("Illegal values for VolleyBallTeam's field(s)");
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void readResultsFromTxtFile () {
        String fileName = "teamResults.txt";
        if(fileName!=null && !fileName.isEmpty()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                List<String> lines = br.lines().collect(Collectors.toList());
                for(String res : lines) {
                    if(!res.equalsIgnoreCase("won") && !res.equalsIgnoreCase("lost") && !res.equalsIgnoreCase("draw") && !res.equalsIgnoreCase("no game played")) {
                        throw new IllegalArgumentException("Illegal result of the current game: " + res);
                    } else {
                        gameResultsForSeason.addAll(lines);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void countPoints () {
        for(String res : gameResultsForSeason) {
            if(!res.equalsIgnoreCase("no game played")) {
                if(res.equalsIgnoreCase("win")) {
                    gamePoints +=1;
                }
                if(res.equalsIgnoreCase("draw")) {
                    gamePoints +=0.5;
                }
                numOfGames +=1;
            }
        }
    }

    public String getSeasonAverage() {
        if(numOfGames<1) {
            return "no games played in this season";
        }
        double res = (gamePoints / numOfGames) * 100;
        if(res > 50) {
            return "winning season";
        } else {
            return "losing season";
        }
    }
}

import java.util.Arrays;

import javax.swing.JOptionPane;

//SEASON 5 (10 matches)

public class AYTOlogicS4 {

    public static int[][] elimMatches(int[][] pM) {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (pM[r][c] == 1) { // if there's a perfect match, start eliminating
                    for (int r2 = 0; r2 < 10; r2++) {
                        for (int c2 = 0; c2 < 10; c2++) {
                            if ((r == r2) ^ (c == c2)) {
                                if (pM[r2][c2] == 1) {
                                    //CONTRADICTION!!!
                                }
                                pM[r2][c2] = -1;
                            }
                        }
                    }
                }
            }
        }
        return pM;
    }

    public static boolean canElimMatches(int[][] pM) {
        boolean isPossible = true;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (pM[r][c] == 1) { // if there's a perfect match, start eliminating
                    for (int r2 = 0; r2 < 10; r2++) {
                        for (int c2 = 0; c2 < 10; c2++) {
                            if ((r == r2) ^ (c == c2)) {
                                if (pM[r2][c2] == 1) {
                                    isPossible = false;
                                }
                                // pM[r2][c2] = -1;
                            }
                        }
                    }
                }
            }
        }
        return isPossible;
    }

    public static int[] elimCeremonies(int[] cer) {
        for (int i = 0; i < 10; i++) {
            if ((0 <= cer[i]) && (cer[i] < 11)) { // if value is between 0 and 10 (aka unconfirmed)
                // make no-match
                cer[i] = -cer[i]-100;
            }
        }
        return cer;
    }

    public static int[][] findPerf(int[][] pM) {
        int sum = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                sum += pM[row][col];
            }
            if (sum == -9) { // if there are 10 no-matches for the row (guy) => perfect match!
                for (int r = 0; r < 10; r++) {
                    for (int c = 0; c < 10; c++) {
                        if (pM[r][c] == 0) {
                            pM[r][c] = 1;
                        }
                    }
                }
            }
        }

        return pM;
    }

    public static int[] findPerfCer(int[] cer) {
        for (int i = 0; i < cer.length; i++) {
            if ((0<=cer[i]) && (cer[i]<100)) {
                cer[i] += 100; // make perfect match
            }
        }
        return cer;
    }

    public static int numPerf(int[] matchUp) {
        int num = 0;
        for (int i = 0; i < matchUp.length; i++) {
            if (matchUp[i]>=100) {
                num++;
            }
        }
        return num;
    }

    public static int numNo(int[] matchUp) {
        int num = 0;
        for (int i = 0; i < matchUp.length; i++) {
            if (matchUp[i]<=-100) {
                num++;
            }
        }
        return num;
    }

    public static int[][] cerFromMatches(int[][] ceremonies, int[][] matches) {
        for (int r = 0; r < ceremonies.length; r++) {
            for (int c = 0; c < 10; c++) {
                int a = ceremonies[r][c];
                if (a >= 100){
                    a-= 100;}
                else if (a < 0){
                    a = -a-100;}
                // if match is perfect, add 100 if not already done
                if (ceremonies[r][c]<100 && (matches[c][a]==1)) { // column = guy's index, value in ceremonies array = girl's index (stripped)
                    ceremonies[r][c] += 100;
                }
                // else if no-match, negate if not already done
                else if (ceremonies[r][c]>=0 && (matches[c][a]==-1)) {
                    ceremonies[r][c] = -ceremonies[r][c]-100;
                }
            }
        }
        return ceremonies;
    }

    public static int[][] matchesFromCer(int[][] matches, int[][] ceremonies) {
        for (int r = 0; r < ceremonies.length; r++) {
            for (int c = 0; c < 11; c++) {
                if (ceremonies[r][c]>=100) { // if perfect match
                    matches[c][ceremonies[r][c]-100] = 1; // column = guy's index, value in ceremonies array = girl's index
                }
                else if (ceremonies[r][c]<0) { // else if no-match
                    matches[c][-ceremonies[r][c]-100] = -1;
                }
            }
        }
        return matches;
    }

    public static void printEverything(int[][] matches, String[] guys, String[] girls, int[][] ceremonies) {
        for(int i = 0; i < 10; i++)
            System.out.print("\t"+girls[i]);
        System.out.println();
        for(int r = 0; r < 10; r++) {
            System.out.print(guys[r]+"\t");
            for(int c = 0; c < 10; c++) {
                System.out.print(matches[r][c]+"\t");
            }
            System.out.println();
        }
        // print ceremonies array
        System.out.print("\nWEEK:");
        for (int i = 0; i < 10; i++) {
            System.out.print("\t"+guys[i]);
        }
        System.out.print("\tBEAMS:\n");
        for (int r = 0; r < ceremonies.length; r++) {
            System.out.print(r+1+"-");
            for (int c = 0; c < 11; c++) {
                System.out.print("\t" + ceremonies[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] guys = {
                "Asaf", // 0
                "Cam", // 1
                "Cameron", // 2
                "Giovani", // 3
                "John", // 4
                "Morgan", // 5
                "Prosper", // 6
                "Sam", // 7
                "Stephen", // 8
                "Tyler", // 9
        };
        String[] girls = {
                "Alyssa", // 0
                "Camille", // 1
                "Emma", // 2
                "Fran", // 3
                "Julia", // 4
                "Kaylen", // 5
                "Mikala", // 6
                "Nicole", // 7
                "Tori", // 8
                "Victoria", // 9
        };

        // 2-d array of -1,0,1 values corresponding to each couple status
        // -1: no-match
        // 0: undetermined
        // 1: perfect match
        int[][] matches = new int[10][10];

        // 2-d array of int ranging from -10 to 10
        // last (11th) column is number of beams (ceremonies[row][10])
        // other columns are girl's index (since guy's index is the row index)
        // negate and subtract 100 if no-match, add 100 if perfect match
        // hard-coded initial data from match-up ceremonies
        int[][] ceremonies = {
                {3, 9, 6, 5, 2, 4, 1, 0, 7, 8, 3}, // week 1
                {1, 4, 6, 5, 7, 0, 2, 3, 8, 9, 3}, // week 2
                {1, 7, 6, 5, 9, 3, 2, 0, 8, 4, 4}, // week 3
                {1, 2, 6, 5, 9, 8, 7, 0, 4, 3, 4}, // week 4
                {1, 2, 6, 3, 8, 4, 9, 0, 7, 5, 4}, // week 5
                {1, 9, 6, 3, 2, 8, 5, 0, 4, 7, 4}, // week 6
                {3, 7, 6, 2, 5, 8, 9, 0, 4, 1, 4} // week 7
        };

        // hard-coded initial data from truth booths RESET
        matches[6][8] = -1;
        matches[4][4] = -1;
        matches[2][6] = 1;
        matches[0][8] = -1;
        matches[3][5] = -1;
        matches[7][0] = 1;
        matches[1][9] = -1;


        do {
            // updates array to eliminate possibilities due to perfect matches
            matches = elimMatches(matches);
            // updates array to find perfect matches due to eliminations
            matches = findPerf(matches);
        } while (!Arrays.equals(elimMatches(matches),matches)); // loop while new matches are found

        // init: update ceremony values from matches data
        ceremonies = cerFromMatches(ceremonies, matches);

        // loop for each week
        // find number of perfect matches in week
        // if beam number == number perfect matches, eliminate/negate in ceremonies week of array
        // if number no matches == 10, last unconfirmed is perfect
        for (int week = 0; week < ceremonies.length; week++) {
            int perfMatches = numPerf(ceremonies[week]);
            int noMatches = numNo(ceremonies[week]);
            if (ceremonies[week][10] == perfMatches) {
                ceremonies[week] = elimCeremonies(ceremonies[week]); // make all unconfirmed into no-matches
            }
            else if (noMatches == 9) {
                ceremonies[week] = findPerfCer(ceremonies[week]); // make last unconfirmed into perfect match
            }
        }

        // update matches array from ceremonies data and ceremonies array from matches data (use method).. until no change
        do {
            matches = matchesFromCer(matches, ceremonies); // update matches
            ceremonies = cerFromMatches(ceremonies, matches); // update ceremonies
        } while (!Arrays.equals(matches, matchesFromCer(matches, ceremonies)) || !Arrays.equals(ceremonies,cerFromMatches(ceremonies, matches))); // while no change

        /*
         * TO TEST DIFFERENT POSSIBILITIES**************************************************************
         * Open a POPUP to enter possible matches (indexes)
         * 1. already confirmed perfect/no match - return IMPOSSIBLE
         * 2. repeat above process -- insert CONTRADICTION condition, return IMPOSSIBLE if contradicts
         * keep track of impossible matches -- update matches & ceremonies arrays
         * update arrays until no change
         */


        // print init matches array
        printEverything(matches, guys, girls, ceremonies);

        int[][] testMatches = new int[10][10];
        for (int i = 0; i < matches.length; i++){
            for (int j = 0; j < matches[0].length; j++) {
                testMatches[i][j] = matches[i][j];
            }
        }
        int[][] testCeremonies = new int[ceremonies.length][ceremonies[0].length];
        for (int i = 0; i < ceremonies.length; i++){
            for (int j = 0; j < ceremonies[0].length; j++) {
                testCeremonies[i][j] = ceremonies[i][j];
            }
        }
        String testCouple = JOptionPane.showInputDialog(null,
                "Enter a indices of couple\nEx: \"09\" for Asaf & Victoria",
                "Test couple",
                JOptionPane.QUESTION_MESSAGE);

        int guy = Integer.parseInt(testCouple)/10;
        int girl = Integer.parseInt(testCouple)%10;
        testMatches[guy][girl] = 1;
        boolean isPossible = true;
        do {
            if(canElimMatches(testMatches)){
                // updates array to eliminate possibilities due to perfect matches
                testMatches = elimMatches(testMatches);
                // updates array to find perfect matches due to eliminations
                testMatches = findPerf(testMatches);
            }
            else {
                matches[guy][girl] = -1;
                isPossible = false;
                break;
            }
        } while (!Arrays.equals(elimMatches(testMatches),testMatches)); // loop while new matches are found

        if (isPossible) {
            for (int week = 0; week < testCeremonies.length; week++) {
                int perfMatches = numPerf(testCeremonies[week]);
                int noMatches = numNo(testCeremonies[week]);
                if (testCeremonies[week][10] == perfMatches) {
                    ceremonies[week] = elimCeremonies(testCeremonies[week]); // make all unconfirmed into no-matches
                }
                else if (noMatches == 9) {
                    testCeremonies[week] = findPerfCer(testCeremonies[week]); // make last unconfirmed into perfect match
                }
            }
        }
        
        printEverything(matches, guys, girls, ceremonies);
    }

}

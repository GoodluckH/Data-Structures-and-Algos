import edu.princeton.cs.algs4.In;

public class KMP {
    private static final int R = 65536;
    private String pat;
    private int[][] dfa;
    private int M;

    public KMP(String pat) {
        this.pat = pat;
        M = pat.length();
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];  // copy over the mismatch case from state X
            dfa[pat.charAt(j)][j] = j + 1; // set the next state for current char in pat
            X = dfa[pat.charAt(j)][X];    // update X;
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M;
        else return N;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String txt = in.readAll();
        in = new In();
        while (!in.isEmpty()) {
            String pat = in.readLine();
            KMP kmp = new KMP(pat);
            int start = kmp.search(txt);
            if (start == txt.length()) {
                System.out.println("No match!");
            }
            else {
                String output = txt.substring(Math.max(0, start - 20), Math.min(txt.length(), start + pat.length()+19));
                System.out.println(output);
            }
            
        } 
    }
}

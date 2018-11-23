package sample;

/**
 * Created by nguyennghi on 11/18/18 10:37 AM
 */
public class RuleHelper {
    public static boolean horseMoveValid(int r, int c, int _r, int _c, int[][] board) {

        if (r - 2 == _r && c + 1 == _c && board[r - 1][c] == 0)
            return true;
        if (r - 2 == _r && c - 1 == _c && board[r - 1][c] == 0)
            return true;
        if (r - 1 == _r && c + 2 == _c && board[r][c + 1] == 0)
            return true;
        if (r - 1 == _r && c - 2 == _c && board[r][c - 1] == 0)
            return true;
/////////////////////////////////////////////////////////////////////////
        if (r + 2 == _r && c + 1 == _c && board[r + 1][c] == 0)
            return true;
        if (r + 2 == _r && c - 1 == _c && board[r + 1][c] == 0)
            return true;
        if (r + 1 == _r && c + 2 == _c && board[r + 1][c + 1] == 0)
            return true;
        if (r + 1 == _r && c - 2 == _c & board[r + 1][c - 1] == 0)
            return true;
        return false;

    }



    public static boolean kingMoveValid(int r, int c, int _r, int _c, int[][] board) {

        if (_r < 7 || _c < 3 || _c > 5)
            return false;
        if (Math.abs(r - _r) == 1 && c == _c)
            return true;
        return Math.abs((_c - c)) == 1 && r == _r;

    }

    public static boolean guardMoveValid(int r, int c, int _r, int _c, int[][] board) {
        if (_r < 7 || _c < 3 || _c > 5)
            return false;

        return Math.abs(_r - r) == 1 && Math.abs(_c - c) == 1;
    }

    public static boolean elephantMoveValid(int r, int c, int _r, int _c, int[][] board) {
        if(_r<5)
            return false;
        if (Math.abs(_r - r) != 2)
            return false;
        if (Math.abs(_c - c) != 2)
            return false;

        if (board[(_r + r) / 2][(_c + c) / 2] == 0)
            return true;


        return false;
    }

    public static boolean carMoveValid(int r, int _r, int c, int _c, int[][] board) {
        if (_r != r && _c != c)
            return false;

        if (_r != r) {
            if (_r > r) {
                for (int i = r+1; i < _r; i++)
                    if (board[i][_c] != 0)
                        return false;
                return true;
            } else {
                for (int i = _r+1; i < r; i++)
                    if (board[i][_c] != 0)
                        return false;
                return true;
            }
        } else {
            if (_c > c) {
                for (int i2 = c+1; i2 < _c; i2++)
                    if (board[_r][i2] != 0)
                        return false;
                return true;
            } else {
                for (int i2 = _c+1; i2 < c; i2++)
                    if (board[_r][i2] != 0)
                        return false;
                return true;
            }
        }
    }
    public static boolean canonMoveValid(int r, int c, int _r, int _c, int[][] board) {
        if (_r != r && _c != c)
            return false;

        if (board[_r][_c] == 0) {
            return carMoveValid(r, _r, c, _c, board);
        }

        if (board[_r][_c] > 0) {
            if (_r != r) {
                if (_r > r) {
                    int n = 0;
                    for (int i = r+1; i < _r; i++)
                        if (board[i][_c] != 0)
                            n += 1;
                    return n == 1;
                } else {
                    int n = 0;
                    for (int i = _r+1; i < r; i++)
                        if (board[i][_c] != 0)
                            n += 1;
                    return n == 1;
                }
            } else {
                if (_c > c) {
                    int n = 0;
                    for (int i2 = c+1; i2 < _c; i2++)
                        if (board[i2][_r] != 0)
                            n += 1;
                    return n == 1;
                } else {
                    int n = 0;
                    for (int i2 = _c+1; i2 < c; i2++)
                        if (board[r][i2] != 0)
                            n += 1;
                    return n == 1;
                }
            }
        }
        return false;
    }

    public static boolean chotMoveValid(int r, int c, int _r, int _c, int[][] board)
    {
        if (_r <= 4)
            return Math.abs(_r - r) <= 1 && Math.abs(_c - c) <= 1;
        else
            return Math.abs(_r - r) <= 1 && Math.abs(_c - c) <= 1 && _r <= r;
    }
}

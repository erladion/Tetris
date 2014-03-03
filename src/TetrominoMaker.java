/**
 * Created by johja118 on 2014-02-10.
 */
public class TetrominoMaker
{
    public static int getNumberOfTypes(){
        return (SquareType.values().length - 2);
    }

    public static Poly getPoly(int n){
        switch(n){
            case 0:
                return createI();
            case 1:
                return createO();
            case 2:
                return createT();
            case 3:
                return createS();
            case 4:
                return createZ();
            case 5:
                return createJ();
            case 6:
                return createL();
            default:
                return null;
        }
    }

    //    0     1     2	    3
    //0 (0,0) (0,1) (0,2) (0,3)
    //1 (1,0) (1,1) (1,2) (1,3)
    //2 (2,0) (2,1) (2,2) (2,3)
    //3 (3,0) (3,1) (3,2) (3,3)


    //    0     1     2	    3
    //0 (0,0)
    //1 (1,0)
    //2 (2,0)
    //3 (3,0)
    public static Poly createI(){
        SquareType[][] I = new SquareType[4][4];
        I[3][0] = SquareType.I;
        I[2][0] = SquareType.I;
        I[1][0] = SquareType.I;
        I[0][0] = SquareType.I;
        return new Poly(I);
    }

    //    0     1     2	    3
    //0 (0,0) (0,1)
    //1 (1,0) (1,1)
    //2
    //3
    public static Poly createO(){
        SquareType[][] O = new SquareType[2][2];
        O[1][0] = SquareType.O;
        O[0][0] = SquareType.O;
        O[0][1] = SquareType.O;
        O[1][1] = SquareType.O;
        return new Poly(O);
    }

    //    0     1     2	    3
    //0
    //1       (1,1)
    //2 (2,0) (2,1) (2,2)
    //3
    public static Poly createT(){
        SquareType[][] T = new SquareType[3][3];
        T[2][0] = SquareType.T;
        T[2][1] = SquareType.T;
        T[2][2] = SquareType.T;
        T[1][1] = SquareType.T;
        return new Poly(T);
    }

    //    0     1     2	    3
    //0
    //1       (1,1) (1,2)
    //2 (2,0) (2,1)
    //3
    public static Poly createS(){
        SquareType[][] S = new SquareType[3][3];
        S[2][0] = SquareType.S;
        S[2][1] = SquareType.S;
        S[1][1] = SquareType.S;
        S[1][2] = SquareType.S;
        return new Poly(S);
    }

    //    0     1     2	    3
    //0
    //1 (1,0) (1,1)
    //2       (2,1) (2,2)
    //3
    public static Poly createZ(){
        SquareType[][] Z = new SquareType[3][3];
        Z[1][0] = SquareType.Z;
        Z[1][1] = SquareType.Z;
        Z[2][1] = SquareType.Z;
        Z[2][2] = SquareType.Z;
        return new Poly(Z);
    }

    //    0     1     2	    3
    //0             (0,2)
    //1             (1,2)
    //2       (2,1) (2,2)
    //3
    public static Poly createJ(){
        SquareType[][] J = new SquareType[3][3];
        J[0][2] = SquareType.J;
        J[1][2] = SquareType.J;
        J[2][2] = SquareType.J;
        J[2][1] = SquareType.J;
        return new Poly(J);
    }

    //    0     1     2	    3
    //0 (0,0)
    //1 (1,0)
    //2 (2,0) (2,1)
    //3
    public static Poly createL(){
        SquareType[][] L = new SquareType[3][3];
        L[0][0] = SquareType.L;
        L[1][0] = SquareType.L;
        L[2][0] = SquareType.L;
        L[2][1] = SquareType.L;
        return new Poly(L);
    }
}

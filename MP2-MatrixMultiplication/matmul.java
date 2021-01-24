public class matmul implements Runnable {
    private int row;
    private double [][] A;
    private double [][] B;
    private double [][] result;

    public matmul(int row, int col, double [][]A, double [][]B, double [][] result){
        this.row=row;
        this.A=A;
        this.B=B;
        this.result=result;
    }
    //function to be executed inside the thread.
    public void run(){
        for(int i=0;i<4;i++){
            for (int j=0;j<20;j++){
                for (int k=0;k<20;k++){
                    result[row+i][j]+=A[row+i][k]*B[k][j];
                }
            }
        }
    }
}

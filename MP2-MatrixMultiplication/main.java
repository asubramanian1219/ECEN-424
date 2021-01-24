import java.util.Random;
public class main{

    public static int r=20;
    public static int c=20;

    public static double [][] A=new double [r][c];
    public static double [][] B=new double [r][c];
    public static double [][] C=new double [r][c];


    public static Thread [] Threads=new Thread[5];
//populate matrices A and B with random values
    public static void main(String[] args){
        Random rand=new Random();
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                A[i][j]=rand.nextInt(2);
                B[i][j]=rand.nextInt(2);
                //A[i][j]=1;
                //B[i][j]=1;
            }
        }
        //Print matrices A and B
        System.out.println();
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(j==19){
                    System.out.print(A[i][j]);
                }
                else {
                    System.out.print(A[i][j] + ", ");
                }
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(j==19){
                    System.out.print(B[i][j]);
                }
                else {
                    System.out.print(B[i][j] + ", ");
                }
            }
            System.out.println();
        }
        //Start 5 threads to multiply matrices
        System.out.println();
        for (int i =0; i<5;i++){
            Threads[i]=new Thread(new matmul(i*4,20,A,B,C));
            Threads[i].start();
        }
        //join threads
        for(int i = 0; i<5;i++){
            try {
                Threads[i].join();
            }
            catch(Exception ex){
                System.out.println("Caught exception");
            }
        }
        //print result
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(j==19){
                    System.out.print(C[i][j]);
                }
                else {
                    System.out.print(C[i][j] + ", ");
                }
            }
            System.out.println();
        }

    }



}

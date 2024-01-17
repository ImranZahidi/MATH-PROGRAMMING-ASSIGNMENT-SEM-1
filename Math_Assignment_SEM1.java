/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
/*  MUHAMMAD IMRAN ZAHIDI BIN MOHD KHALIL 2318165
    MUHAMMAD IKHWAN BIN MAT SUHAIRI 2317327
    MUHAMMAD ILHAM BIN MOHD HISHAM 2316961
*/
/**
 *
 * @author ASUS
 */
public class Math_Assignment_SEM1 {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter the degree of the recurrence relation (1 or 2): ");
        int degree = input.nextInt();

        if (degree == 1) {
            solveDegree1(input);
        } else if (degree == 2) {
            solveDegree2(input);
        } else {
            System.out.println("Invalid degree. Please enter 1 or 2.");
        }

    }
    
    public static void solveDegree1(Scanner input){
        System.out.print("Enter initial value a0: ");
        double a0=input.nextDouble();
        System.out.print("Enter coefficient c: ");
        double c=input.nextDouble();
        System.out.print("Enter number of terms (n): ");
        int n=input.nextInt();
        double[] sequence=SequenceDegree1(a0,c,n);
        TransferToFile("degree1.txt",sequence);
        Properties(sequence);
    }
    
    public static void solveDegree2(Scanner input){
        System.out.print("Enter initial value a1: ");
        double a0=input.nextDouble();
        System.out.print("Enter initial value a2: ");
        double a1=input.nextDouble();
        System.out.print("Enter coefficient c1: ");
        double c1=input.nextDouble();
        System.out.print("Enter coefficient c2: ");
        double c2=input.nextDouble();
        System.out.print("Enter number of terms (n): ");
        int n=input.nextInt();
        double[] sequence=SequenceDegree2(a0,a1,c1,c2,n);
        TransferToFile("degree2.txt",sequence);
        Properties(sequence);
    }
    
    public static double[] SequenceDegree1(double a0,double c,int n){
        double[] degree1=new double[n];
        degree1[0]=a0;
        for(int i=1;i<n;i++){
            degree1[i]=c*degree1[i-1];
        }
        return degree1;
    }
    
    public static double[] SequenceDegree2(double a0,double a1,double c1,double c2,int n){
        double[] degree2=new double[n];
        degree2[0]=a0;
        degree2[1]=a1;
        for(int i=2;i<n;i++){
            degree2[i]=c1*degree2[i-1]+c2*degree2[i-2];
        }
        return degree2;
    }
    
    public static void TransferToFile(String fileName,double[] sequence){
        try(FileWriter writer=new FileWriter(fileName)){
            for(double value : sequence){
                writer.write(value+"\n");
            }
        }
        catch(IOException e){
            
        }
        System.out.println("Sequence Saved!");
    }
    
    public static void Properties(double[] function){      

         boolean increasing = isIncreasing(function);
        boolean decreasing = isDecreasing(function);
        boolean constant = isConstant(function, 0.001); // Tolerance value for constant behavior
        boolean upDown = isUpDown(function);
        boolean converging = isConverging(function,0.001);
        boolean diverging = isDiverging(converging);
        System.out.println("Increasing: " + increasing);
        System.out.println("Decreasing: " + decreasing);
        System.out.println("Constant: " + constant);
        System.out.println("Up-Down: " + upDown);
        System.out.println("Diverging: " + diverging);
        System.out.println("Converging: " + converging);
    }
     public static boolean isIncreasing(double[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i] >= data[i - 1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDecreasing(double[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i] >= data[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isConstant(double[] data, double tolerance) {
        for (int i = 1; i < data.length; i++) {
            if (Math.abs(data[i] - data[i - 1]) > tolerance) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUpDown(double[] data) {
        boolean increasing = false;
        boolean decreasing = false;

        for (int i = 1; i < data.length; i++) {
            if (data[i] > data[i - 1]) {
                increasing = true;
            } else if (data[i] < data[i - 1]) {
                decreasing = true;
            }
        }

        return increasing && decreasing;
    }

    public static boolean isDiverging(boolean converging) {
        boolean diverging;
        if(converging==true)
            diverging=false;
        else
            diverging=true;
        
        return diverging;    
    }

    public static boolean isConverging(double[] data,double tolerance) {
        boolean converging=true;
    
        for(int i=0;i<data.length-2;i++){
            if(data[i+2]-data[i+1]<data[i+1]-data[i])
                converging=true;
        }
        for(int i=0;i<data.length-2;i++){
            if(data[i+2]-data[i+1]<data[i+1]-data[i])
                converging=false;
        }
        return converging;
    }
}


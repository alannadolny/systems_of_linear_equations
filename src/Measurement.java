import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Variables.*;
import org.ejml.simple.SimpleMatrix;

public class Measurement<T extends Operations<T>> {
    void saveToCSV(MyMatrix<T> results, String fileToSave) {
        List<List<BigDecimal>> tempResult = results.getMatrixWithNumbers();
        StringBuilder toFile = new StringBuilder("element,result\n");
        int counter = 1;
        for (int i = 0; i < results.countRows(); i++) {
            for (int j = 0; j < results.countColumns(); j++) {
                toFile.append(String.valueOf(counter) +
                        ',' + tempResult.get(i).get(j) +
                        '\n');
                counter++;
            }
        }
        try {
            Path fileName = Path.of(
                    "C:\\Users\\gruby\\Desktop\\ug\\II rok\\algorytmy numeryczne\\projekt_2\\data\\" + fileToSave + ".csv"
            );
            Files.writeString(fileName, toFile);
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    void saveToCSVLib(SimpleMatrix results, String fileToSave) {
        StringBuilder toFile = new StringBuilder("element,result\n");
        int counter = 1;
        for (int i = 0; i < results.numRows(); i++) {
            for (int j = 0; j < results.numCols(); j++) {
                toFile.append(String.valueOf(counter) +
                        ',' + results.get(i, j) +
                        '\n');
                counter++;
            }
        }
        try {
            Path fileName = Path.of(
                    "C:\\Users\\gruby\\Desktop\\ug\\II rok\\algorytmy numeryczne\\projekt_2\\data\\" + fileToSave + ".csv"
            );
            Files.writeString(fileName, toFile);
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    void operationTests(Integer matrixSize, List<T> A, List<T> A2, List<T> B, List<T> C, List<T> X, String type) {
        List<String> times = new ArrayList<>();

        System.out.println("obliczanie dla " + matrixSize + " elementow " + type);

        List<T> elementsToMatrixA = A.subList(0, matrixSize * matrixSize);
        List<T> elementsToMatrixA2 = A2.subList(0, matrixSize * matrixSize);
        List<T> elementsToMatrixB = B.subList(0, matrixSize * matrixSize);
        List<T> elementsToMatrixC = C.subList(0, matrixSize * matrixSize);
        List<T> elementsToVectorX = X.subList(0, matrixSize * matrixSize);


        MyMatrix<T> matrixA = new MyMatrix<>(matrixSize, elementsToMatrixA);
        MyMatrix<T> matrixA2 = new MyMatrix<>(matrixSize, elementsToMatrixA2);
        MyMatrix<T> matrixB = new MyMatrix<>(matrixSize, elementsToMatrixB);
        MyMatrix<T> matrixC = new MyMatrix<>(matrixSize, elementsToMatrixC);
        MyMatrix<T> vectorX = new MyMatrix<>(1, elementsToVectorX);

        //testowanie A * X

        long start = System.currentTimeMillis();
        MyMatrix<T> AX = new MyMatrix<>(new MyMatrix<>(matrixA).multiplyMatrix(vectorX));
        long stop = System.currentTimeMillis();
        times.add(String.valueOf(stop - start));


        saveToCSV(AX, "AX" + matrixSize + type);
        System.out.println("1/3 policzono");

        //testowanie (A + B + C) * X

        start = System.currentTimeMillis();
        MyMatrix<T> AB = new MyMatrix<>(new MyMatrix<>(matrixA).addMatrix(matrixB));
        MyMatrix<T> ABC = new MyMatrix<>(new MyMatrix<>(AB).addMatrix(matrixC));
        MyMatrix<T> ABCX = new MyMatrix<>(new MyMatrix<>(ABC).multiplyMatrix(vectorX));
        stop = System.currentTimeMillis();
        times.add(String.valueOf(stop - start));

        saveToCSV(ABCX, "ABCX" + matrixSize + type);
        System.out.println("2/3 policzono");

        //A * (B * C)

        start = System.currentTimeMillis();
        MyMatrix<T> BC = new MyMatrix<>(new MyMatrix<>(matrixA2).multiplyMatrix(matrixB));
        MyMatrix<T> ABC2 = new MyMatrix<>(new MyMatrix<>(BC).multiplyMatrix(matrixC));
        stop = System.currentTimeMillis();
        times.add(String.valueOf(stop - start));

        saveToCSV(ABC2, "ABC" + matrixSize + type);

        System.out.println(times);
        System.out.println("Przygotowano dane dla " + matrixSize + " elementow typu " + type);
    }

    void gaussTest(int matrixSize, List<T> A, List<T> A2, List<T> A3, List<T> X, List<T> X2, List<T> X3, List<T> copyA, List<T> copyA2, List<T> copyA3, List<T> copyVector, List<T> copyVector2, List<T> copyVector3, String type) {

        // oryginalna - (wynik * macierz)

        List<String> times = new ArrayList<>();

        System.out.println("rozwiazywanie dla " + matrixSize + " elementow");

        Gauss<T> Gauss = new Gauss<>();

        MyMatrix<T> matrix = new MyMatrix<>(matrixSize, A);
        MyMatrix<T> vector = new MyMatrix<>(1, X);

        MyMatrix<T> matrixCopy = new MyMatrix<>(matrixSize, copyA);
        MyMatrix<T> vectorCopy = new MyMatrix<>(1, copyVector);

        MyMatrix<T> matrix2 = new MyMatrix<>(matrixSize, A2);
        MyMatrix<T> vector2 = new MyMatrix<>(1, X2);

        MyMatrix<T> matrix2Copy = new MyMatrix<>(matrixSize, copyA2);
        MyMatrix<T> vector2Copy = new MyMatrix<>(1, copyVector2);

        MyMatrix<T> matrix3 = new MyMatrix<>(matrixSize, A3);
        MyMatrix<T> vector3 = new MyMatrix<>(1, X3);

        MyMatrix<T> matrix3Copy = new MyMatrix<>(matrixSize, copyA3);
        MyMatrix<T> vector3Copy = new MyMatrix<>(1, copyVector3);

        long start = System.currentTimeMillis();
        MyMatrix<T> solutionsG = Gauss.G(new MyMatrix<>(matrix), new MyMatrix<>(vector), "G");
        long stop = System.currentTimeMillis();
        times.add(String.valueOf(stop - start));

        System.out.println("1 / 3 policzono");

        start = System.currentTimeMillis();
        MyMatrix<T> solutionsPG = Gauss.G(new MyMatrix<>(matrix2), new MyMatrix<>(vector2), "PG");
        stop = System.currentTimeMillis();
        times.add(String.valueOf(stop - start));

        System.out.println("2 / 3 policzono");

        start = System.currentTimeMillis();
        MyMatrix<T> solutionsFG = Gauss.G(new MyMatrix<>(matrix3), new MyMatrix<>(vector3), "FG");
        stop = System.currentTimeMillis();
        times.add(String.valueOf(stop - start));

        saveToCSV(vectorCopy.subtractMatrix(matrixCopy.multiplyMatrix(solutionsG)), "G" + matrixSize + type);
        saveToCSV(vector2Copy.subtractMatrix(matrix2Copy.multiplyMatrix(solutionsPG)), "PG" + matrixSize + type);
        saveToCSV(vector3Copy.subtractMatrix(matrix3Copy.multiplyMatrix(solutionsFG)), "FG" + matrixSize + type);
        System.out.println(times);
        System.out.println("Obliczono dane dla " + matrixSize + " " + type);

    }

    static void measure(int elements) {
        Measurement<MyDouble> measurementD = new Measurement<>();
        Measurement<MyFloat> measurementFl = new Measurement<>();
        Measurement<MyFractions> measurementFr = new Measurement<>();

        List<Integer> toMatrixesA = new ArrayList<>();
        List<Integer> toMatrixesB = new ArrayList<>();
        List<Integer> toMatrixesC = new ArrayList<>();
        List<Integer> toMatrixesX = new ArrayList<>();

        Random rand = new Random();
        int max = (int) (Math.pow(2, 16) - 1);
        int min = (int) -(Math.pow(2, 16));
        for (int i = 0; i <= elements * elements; i++) {
            toMatrixesA.add(rand.nextInt((max - min) + 1) + min);
            toMatrixesB.add(rand.nextInt((max - min) + 1) + min);
            toMatrixesC.add(rand.nextInt((max - min) + 1) + min);
            toMatrixesX.add(rand.nextInt((max - min) + 1) + min);
        }

        List<MyDouble> ADouble = new ArrayList<>();
        List<MyDouble> A2Double = new ArrayList<>();
        List<MyDouble> BDouble = new ArrayList<>();
        List<MyDouble> CDouble = new ArrayList<>();
        List<MyDouble> XDouble = new ArrayList<>();

        for (int i = 0; i < elements * elements; i++) {
            ADouble.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
            A2Double.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
            BDouble.add(new MyDouble(toMatrixesB.get(i) / Math.pow(2, 16)));
            CDouble.add(new MyDouble(toMatrixesC.get(i) / Math.pow(2, 16)));
            XDouble.add(new MyDouble(toMatrixesX.get(i) / Math.pow(2, 16)));

        }
        measurementD.operationTests(elements, ADouble, A2Double, BDouble, CDouble, XDouble, "double");

        List<MyFractions> AFractions = new ArrayList<>();
        List<MyFractions> A2Fractions = new ArrayList<>();
        List<MyFractions> BFractions = new ArrayList<>();
        List<MyFractions> CFractions = new ArrayList<>();
        List<MyFractions> XFractions = new ArrayList<>();

        for (int i = 0; i < elements * elements; i++) {
            final BigInteger denumerator = new BigInteger(String.valueOf(65536));
            AFractions.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), denumerator));
            A2Fractions.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), denumerator));
            BFractions.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesB.get(i))), denumerator));
            CFractions.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesC.get(i))), denumerator));
            XFractions.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesX.get(i))), denumerator));

        }
        measurementFr.operationTests(elements, AFractions, A2Fractions, BFractions, CFractions, XFractions, "Fractions");

        List<MyFloat> AFloat = new ArrayList<>();
        List<MyFloat> A2Float = new ArrayList<>();
        List<MyFloat> BFloat = new ArrayList<>();
        List<MyFloat> CFloat = new ArrayList<>();
        List<MyFloat> XFloat = new ArrayList<>();

        for (int i = 0; i < elements * elements; i++) {
            AFloat.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
            A2Float.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
            BFloat.add(new MyFloat((float) (toMatrixesB.get(i) / Math.pow(2, 16))));
            CFloat.add(new MyFloat((float) (toMatrixesC.get(i) / Math.pow(2, 16))));
            XFloat.add(new MyFloat((float) (toMatrixesX.get(i) / Math.pow(2, 16))));

        }
        measurementFl.operationTests(elements, AFloat, A2Float, BFloat, CFloat, XFloat, "float");

        final double[][] ALibrary = new double[elements][elements];
        final double[][] BLibrary = new double[elements][elements];
        final double[][] CLibrary = new double[elements][elements];
        final double[][] XLibrary = new double[elements][1];

        for (int i = 0; i < elements; i++) {
            for (int j = 0; j < elements; j++) {
                ALibrary[i][j] = toMatrixesA.get(i * elements + j) / Math.pow(2, 16);
                BLibrary[i][j] = toMatrixesB.get(i * elements + j) / Math.pow(2, 16);
                CLibrary[i][j] = toMatrixesC.get(i * elements + j) / Math.pow(2, 16);
            }
        }

        for (int i = 0; i < elements; i++) {
            XLibrary[i][0] = (toMatrixesX.get(i) / Math.pow(2, 16));
        }

        SimpleMatrix matrixALibrary = new SimpleMatrix(ALibrary);
        SimpleMatrix matrixBLibrary = new SimpleMatrix(BLibrary);
        SimpleMatrix matrixCLibrary = new SimpleMatrix(CLibrary);
        SimpleMatrix matrixXLibrary = new SimpleMatrix(XLibrary);

        long start = System.currentTimeMillis();
        SimpleMatrix AXLibrary = matrixALibrary.mult(matrixXLibrary);
        long stop = System.currentTimeMillis();

        measurementD.saveToCSVLib(AXLibrary, "AX" + elements + "Library");

        System.out.println("A * X, biblioteka " + elements + " elementow: " + (stop - start));

        start = System.currentTimeMillis();
        SimpleMatrix ABLibrary = matrixALibrary.plus(matrixBLibrary);
        SimpleMatrix ABCLibrary = ABLibrary.plus(matrixCLibrary);
        SimpleMatrix ABCXLibrary = ABCLibrary.mult(matrixXLibrary);
        stop = System.currentTimeMillis();

        measurementD.saveToCSVLib(ABCXLibrary, "ABCX" + elements + "Library");

        System.out.println("(A + B + C) * X, biblioteka " + elements + " elementow: " + (stop - start));

        start = System.currentTimeMillis();
        SimpleMatrix BCLibrary = matrixALibrary.mult(matrixBLibrary);
        SimpleMatrix ABCLibrary2 = BCLibrary.mult(matrixCLibrary);
        stop = System.currentTimeMillis();

        measurementD.saveToCSVLib(ABCLibrary2, "ABC" + elements + "Library");

        System.out.println("A * (B * C), biblioteka " + elements + " elementow: " + (stop - start));

        ADouble = new ArrayList<>();
        List<MyDouble> ADoubleCopy = new ArrayList<>();
        List<MyDouble> ADouble2 = new ArrayList<>();
        List<MyDouble> ADouble3 = new ArrayList<>();
        List<MyDouble> ADouble2Copy = new ArrayList<>();
        List<MyDouble> ADouble3Copy = new ArrayList<>();
        XDouble = new ArrayList<>();
        List<MyDouble> XDoubleCopy = new ArrayList<>();
        List<MyDouble> XDouble2 = new ArrayList<>();
        List<MyDouble> XDouble3 = new ArrayList<>();
        List<MyDouble> XDouble2Copy = new ArrayList<>();
        List<MyDouble> XDouble3Copy = new ArrayList<>();

        for (int i = 0; i < elements * elements; i++) {
            ADouble.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
            ADoubleCopy.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
            ADouble2.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
            ADouble3.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
            ADouble2Copy.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
            ADouble3Copy.add(new MyDouble(toMatrixesA.get(i) / Math.pow(2, 16)));
        }

        for (int i = 0; i < elements; i++) {
            XDouble.add(new MyDouble(toMatrixesX.get(i) / Math.pow(2, 16)));
            XDoubleCopy.add(new MyDouble(toMatrixesX.get(i) / Math.pow(2, 16)));
            XDouble2.add(new MyDouble(toMatrixesX.get(i) / Math.pow(2, 16)));
            XDouble3.add(new MyDouble(toMatrixesX.get(i) / Math.pow(2, 16)));
            XDouble2Copy.add(new MyDouble(toMatrixesX.get(i) / Math.pow(2, 16)));
            XDouble3Copy.add(new MyDouble(toMatrixesX.get(i) / Math.pow(2, 16)));
        }

        measurementD.gaussTest(elements, ADouble, ADouble2, ADouble3, XDouble, XDouble2, XDouble3, ADoubleCopy, ADouble2Copy, ADouble3Copy, XDoubleCopy, XDouble2Copy, XDouble3Copy, "double");

        AFloat = new ArrayList<>();
        List<MyFloat> AFloatCopy = new ArrayList<>();
        List<MyFloat> AFloat2 = new ArrayList<>();
        List<MyFloat> AFloat3 = new ArrayList<>();
        List<MyFloat> AFloat2Copy = new ArrayList<>();
        List<MyFloat> AFloat3Copy = new ArrayList<>();
        XFloat = new ArrayList<>();
        List<MyFloat> XFloatCopy = new ArrayList<>();
        List<MyFloat> XFloat2 = new ArrayList<>();
        List<MyFloat> XFloat3 = new ArrayList<>();
        List<MyFloat> XFloat2Copy = new ArrayList<>();
        List<MyFloat> XFloat3Copy = new ArrayList<>();

        for (int i = 0; i < elements * elements; i++) {
            AFloat.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
            AFloatCopy.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
            AFloat2.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
            AFloat3.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
            AFloat2Copy.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
            AFloat3Copy.add(new MyFloat((float) (toMatrixesA.get(i) / Math.pow(2, 16))));
        }

        for (int i = 0; i < elements; i++) {
            XFloat.add(new MyFloat((float) (toMatrixesX.get(i) / Math.pow(2, 16))));
            XFloatCopy.add(new MyFloat((float) (toMatrixesX.get(i) / Math.pow(2, 16))));
            XFloat2.add(new MyFloat((float) (toMatrixesX.get(i) / Math.pow(2, 16))));
            XFloat3.add(new MyFloat((float) (toMatrixesX.get(i) / Math.pow(2, 16))));
            XFloat2Copy.add(new MyFloat((float) (toMatrixesX.get(i) / Math.pow(2, 16))));
            XFloat3Copy.add(new MyFloat((float) (toMatrixesX.get(i) / Math.pow(2, 16))));
        }

        measurementFl.gaussTest(elements, AFloat, AFloat2, AFloat3, XFloat, XFloat2, XFloat3, AFloatCopy, AFloat2Copy, AFloat3Copy, XFloatCopy, XFloat2Copy, XFloat3Copy, "Float");

        AFractions = new ArrayList<>();
        List<MyFractions> AFractionsCopy = new ArrayList<>();
        List<MyFractions> AFractions2 = new ArrayList<>();
        List<MyFractions> AFractions3 = new ArrayList<>();
        List<MyFractions> AFractions2Copy = new ArrayList<>();
        List<MyFractions> AFractions3Copy = new ArrayList<>();
        XFractions = new ArrayList<>();
        List<MyFractions> XFractionsCopy = new ArrayList<>();
        List<MyFractions> XFractions2 = new ArrayList<>();
        List<MyFractions> XFractions3 = new ArrayList<>();
        List<MyFractions> XFractions2Copy = new ArrayList<>();
        List<MyFractions> XFractions3Copy = new ArrayList<>();

        for (int i = 0; i < elements * elements; i++) {
            AFractionsCopy.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), new BigInteger(String.valueOf(65536))));
            AFractions.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), new BigInteger(String.valueOf(65536))));
            AFractions2.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), new BigInteger(String.valueOf(65536))));
            AFractions3.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), new BigInteger(String.valueOf(65536))));
            AFractions2Copy.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), new BigInteger(String.valueOf(65536))));
            AFractions3Copy.add(new MyFractions(new BigInteger(String.valueOf(toMatrixesA.get(i))), new BigInteger(String.valueOf(65536))));
        }

        for (int i = 0; i < elements; i++) {
            XFractionsCopy.add(new MyFractions((new BigInteger(String.valueOf(toMatrixesX.get(i)))), new BigInteger(String.valueOf(65536))));
            XFractions.add(new MyFractions((new BigInteger(String.valueOf(toMatrixesX.get(i)))), new BigInteger(String.valueOf(65536))));
            XFractions2.add(new MyFractions((new BigInteger(String.valueOf(toMatrixesX.get(i)))), new BigInteger(String.valueOf(65536))));
            XFractions3.add(new MyFractions((new BigInteger(String.valueOf(toMatrixesX.get(i)))), new BigInteger(String.valueOf(65536))));
            XFractions2Copy.add(new MyFractions((new BigInteger(String.valueOf(toMatrixesX.get(i)))), new BigInteger(String.valueOf(65536))));
            XFractions3Copy.add(new MyFractions((new BigInteger(String.valueOf(toMatrixesX.get(i)))), new BigInteger(String.valueOf(65536))));
        }

        measurementFr.gaussTest(elements, AFractions, AFractions2, AFractions3, XFractions, XFractions2, XFractions3, AFractionsCopy, AFractions2Copy, AFractions3Copy, XFractionsCopy, XFractions2Copy, XFractions3Copy, "Fractions");

        for (int i = 0; i < elements; i++) {
            for (int j = 0; j < elements; j++) {
                ALibrary[i][j] = toMatrixesA.get(i * elements + j) / Math.pow(2, 16);
            }
        }

        for (int i = 0; i < elements; i++) {
            XLibrary[i][0] = (toMatrixesX.get(i) / Math.pow(2, 16));
        }

        matrixALibrary = new SimpleMatrix(ALibrary);
        matrixXLibrary = new SimpleMatrix(XLibrary);

        start = System.currentTimeMillis();
        SimpleMatrix GLibrary = matrixALibrary.solve(matrixXLibrary);
        stop = System.currentTimeMillis();

        measurementD.saveToCSVLib(GLibrary, "G" + elements + "Library");

        System.out.println("G, biblioteka " + elements + " elementow: " + (stop - start));
    }

    public static void main(String[] args) {
        measure(50);
        measure(100);
        measure(150);
        measure(200);
        measure(250);
        measure(300);
        //measure(500);
    }
}
import org.junit.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import Variables.*;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void test_adding_matrix_primitives() {
        List<MyDouble> matrixElements = new ArrayList<>();
        matrixElements.add(new MyDouble(1D));
        matrixElements.add(new MyDouble(2D));
        matrixElements.add(new MyDouble(3D));
        matrixElements.add(new MyDouble(4D));
        matrixElements.add(new MyDouble(5D));
        matrixElements.add(new MyDouble(6D));
        matrixElements.add(new MyDouble(7D));
        matrixElements.add(new MyDouble(8D));
        matrixElements.add(new MyDouble(9D));
        MyMatrix<MyDouble> matrix = new MyMatrix<>(3, matrixElements);
        MyMatrix<MyDouble> matrixAfterAdding = new MyMatrix<>(matrix.addMatrix(matrix));
        List<List<BigDecimal>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add((new BigDecimal("2.0")));
        expectedResult.get(0).add((new BigDecimal("4.0")));
        expectedResult.get(0).add((new BigDecimal("6.0")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(1).add((new BigDecimal("8.0")));
        expectedResult.get(1).add((new BigDecimal("10.0")));
        expectedResult.get(1).add((new BigDecimal("12.0")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(2).add((new BigDecimal("14.0")));
        expectedResult.get(2).add((new BigDecimal("16.0")));
        expectedResult.get(2).add((new BigDecimal("18.0")));
        assertEquals("matrix.addMatrix(matrix, matrix), primitives", matrixAfterAdding.getMatrixWithNumbers(), expectedResult);
    }

    @Test
    public void test_subtract_matrix_primitives() {
        List<MyDouble> matrixElements = new ArrayList<>();
        matrixElements.add(new MyDouble(1D));
        matrixElements.add(new MyDouble(2D));
        matrixElements.add(new MyDouble(3D));
        matrixElements.add(new MyDouble(4D));
        matrixElements.add(new MyDouble(5D));
        matrixElements.add(new MyDouble(6D));
        matrixElements.add(new MyDouble(7D));
        matrixElements.add(new MyDouble(8D));
        matrixElements.add(new MyDouble(9D));
        MyMatrix<MyDouble> matrix = new MyMatrix<>(3, matrixElements);
        MyMatrix<MyDouble> matrixAfterSubtracting = new MyMatrix<>(matrix.subtractMatrix(matrix));
        List<List<BigDecimal>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add((new BigDecimal("0.0")));
        expectedResult.get(0).add((new BigDecimal("0.0")));
        expectedResult.get(0).add((new BigDecimal("0.0")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(1).add((new BigDecimal("0.0")));
        expectedResult.get(1).add((new BigDecimal("0.0")));
        expectedResult.get(1).add((new BigDecimal("0.0")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(2).add((new BigDecimal("0.0")));
        expectedResult.get(2).add((new BigDecimal("0.0")));
        expectedResult.get(2).add((new BigDecimal("0.0")));
        assertEquals("matrix.subtractMatrix(matrix, matrix), primitives", matrixAfterSubtracting.getMatrixWithNumbers(), expectedResult);
    }

    @Test
    public void test_subtract_vectors_primitives() {
        List<MyDouble> matrixElements = new ArrayList<>();
        matrixElements.add(new MyDouble(1D));
        matrixElements.add(new MyDouble(2D));
        matrixElements.add(new MyDouble(3D));
        MyMatrix<MyDouble> matrix = new MyMatrix<>(1, matrixElements);
        MyMatrix<MyDouble> matrixAfterSubtracting = new MyMatrix<>(matrix.subtractMatrix(matrix));
        List<List<BigDecimal>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add((new BigDecimal("0.0")));
        expectedResult.get(0).add((new BigDecimal("0.0")));
        expectedResult.get(0).add((new BigDecimal("0.0")));

        assertEquals("matrix.subtractMatrix(matrix, matrix), primitives", matrixAfterSubtracting.getMatrixWithNumbers(), expectedResult);
    }

    @Test
    public void test_adding_matrix_fractions() {
        List<MyFractions> matrixElements = new ArrayList<>();
        matrixElements.add(new MyFractions(1L));
        matrixElements.add(new MyFractions(2L));
        matrixElements.add(new MyFractions(3L));
        matrixElements.add(new MyFractions(4L));
        matrixElements.add(new MyFractions(5L));
        matrixElements.add(new MyFractions(6L));
        matrixElements.add(new MyFractions(7L));
        matrixElements.add(new MyFractions(8L));
        matrixElements.add(new MyFractions(9L));
        MyMatrix<MyFractions> matrix = new MyMatrix<>(3, matrixElements);
        MyMatrix<MyFractions> matrixAfterAdding = new MyMatrix<>(matrix.addMatrix(matrix));
        List<List<BigDecimal>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add((new BigDecimal("2.0000000000000000")));
        expectedResult.get(0).add((new BigDecimal("4.0000000000000000")));
        expectedResult.get(0).add((new BigDecimal("6.0000000000000000")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(1).add((new BigDecimal("8.0000000000000000")));
        expectedResult.get(1).add((new BigDecimal("10.0000000000000000")));
        expectedResult.get(1).add((new BigDecimal("12.0000000000000000")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(2).add((new BigDecimal("14.0000000000000000")));
        expectedResult.get(2).add((new BigDecimal("16.0000000000000000")));
        expectedResult.get(2).add((new BigDecimal("18.0000000000000000")));
        assertEquals("matrix.addMatrix(matrix, matrix), fractions", matrixAfterAdding.getMatrixWithNumbers(), expectedResult);
    }

    @Test
    public void test_multiplying_matrix_primitives() {
        List<MyDouble> matrixElements = new ArrayList<>();
        matrixElements.add(new MyDouble(1D));
        matrixElements.add(new MyDouble(2D));
        matrixElements.add(new MyDouble(3D));
        matrixElements.add(new MyDouble(4D));
        matrixElements.add(new MyDouble(5D));
        matrixElements.add(new MyDouble(6D));
        matrixElements.add(new MyDouble(7D));
        matrixElements.add(new MyDouble(8D));
        matrixElements.add(new MyDouble(9D));
        MyMatrix<MyDouble> matrix = new MyMatrix<>(3, matrixElements);
        MyMatrix<MyDouble> matrixAfterMultiplying = new MyMatrix<>(matrix.multiplyMatrix(matrix));
        List<List<BigDecimal>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add((new BigDecimal("30.0")));
        expectedResult.get(0).add((new BigDecimal("36.0")));
        expectedResult.get(0).add((new BigDecimal("42.0")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(1).add((new BigDecimal("66.0")));
        expectedResult.get(1).add((new BigDecimal("81.0")));
        expectedResult.get(1).add((new BigDecimal("96.0")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(2).add((new BigDecimal("102.0")));
        expectedResult.get(2).add((new BigDecimal("126.0")));
        expectedResult.get(2).add((new BigDecimal("150.0")));
        assertEquals("matrix.multiplayMatrix(matrix, matrix), primitives", matrixAfterMultiplying.getMatrixWithNumbers(), expectedResult);
    }

    @Test
    public void test_multiplying_matrix_fractions() {
        List<MyFractions> matrixElements = new ArrayList<>();
        matrixElements.add(new MyFractions(1L));
        matrixElements.add(new MyFractions(2L));
        matrixElements.add(new MyFractions(3L));
        matrixElements.add(new MyFractions(4L));
        matrixElements.add(new MyFractions(5L));
        matrixElements.add(new MyFractions(6L));
        matrixElements.add(new MyFractions(7L));
        matrixElements.add(new MyFractions(8L));
        matrixElements.add(new MyFractions(9L));
        MyMatrix<MyFractions> matrix = new MyMatrix<>(3, matrixElements);
        MyMatrix<MyFractions> matrixAfterMultiplying = new MyMatrix<>(matrix.multiplyMatrix(matrix));
        List<List<BigDecimal>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add((new BigDecimal("30.0000000000000000")));
        expectedResult.get(0).add((new BigDecimal("36.0000000000000000")));
        expectedResult.get(0).add((new BigDecimal("42.0000000000000000")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(1).add((new BigDecimal("66.0000000000000000")));
        expectedResult.get(1).add((new BigDecimal("81.0000000000000000")));
        expectedResult.get(1).add((new BigDecimal("96.0000000000000000")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(2).add((new BigDecimal("102.0000000000000000")));
        expectedResult.get(2).add((new BigDecimal("126.0000000000000000")));
        expectedResult.get(2).add((new BigDecimal("150.0000000000000000")));
        assertEquals("matrix.multiplyMatrix(matrix, matrix), fractions", matrixAfterMultiplying.getMatrixWithNumbers(), expectedResult);
    }

    @Test
    public void test_multiplying_matrix_by_vector_fractions() {
        List<MyFractions> matrixElements = new ArrayList<>();
        List<MyFractions> vectorElements = new ArrayList<>();
        matrixElements.add(new MyFractions(1L));
        matrixElements.add(new MyFractions(2L));
        matrixElements.add(new MyFractions(3L));
        matrixElements.add(new MyFractions(4L));
        matrixElements.add(new MyFractions(5L));
        matrixElements.add(new MyFractions(6L));
        matrixElements.add(new MyFractions(7L));
        matrixElements.add(new MyFractions(8L));
        matrixElements.add(new MyFractions(9L));
        vectorElements.add(new MyFractions(5L));
        vectorElements.add(new MyFractions(11L));
        vectorElements.add(new MyFractions(12L));
        MyMatrix<MyFractions> matrix = new MyMatrix<>(3, matrixElements);
        MyMatrix<MyFractions> vector = new MyMatrix<>(1, vectorElements);
        MyMatrix<MyFractions> matrixAfterMultplying = new MyMatrix<>(matrix.multiplyMatrix(vector));
        List<List<BigDecimal>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add((new BigDecimal("63.0000000000000000")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(1).add((new BigDecimal("147.0000000000000000")));
        expectedResult.add(new ArrayList<>());
        expectedResult.get(2).add((new BigDecimal("231.0000000000000000")));
        assertEquals("matrix.multiplyMatrix(matrix, vector), fractions", matrixAfterMultplying.getMatrixWithNumbers(), expectedResult);
    }

    @Test
    public void test_normal_gauss_primitives() {
        List<MyDouble> matrixElements = new ArrayList<>();
        List<MyDouble> vectorsElements = new ArrayList<>();

        matrixElements.add(new MyDouble(1.2));
        matrixElements.add(new MyDouble(2.6));
        matrixElements.add(new MyDouble(-0.1));
        matrixElements.add(new MyDouble(1.5));
        matrixElements.add(new MyDouble(4.5));
        matrixElements.add(new MyDouble(9.8));
        matrixElements.add(new MyDouble(-0.4));
        matrixElements.add(new MyDouble(5.7));
        matrixElements.add(new MyDouble(0.1));
        matrixElements.add(new MyDouble(-0.1));
        matrixElements.add(new MyDouble(-0.3));
        matrixElements.add(new MyDouble(-3.5));
        matrixElements.add(new MyDouble(4.5));
        matrixElements.add(new MyDouble(-5.2));
        matrixElements.add(new MyDouble(4.2));
        matrixElements.add(new MyDouble(-3.4));

        vectorsElements.add(new MyDouble(13.15));
        vectorsElements.add(new MyDouble(49.84));
        vectorsElements.add(new MyDouble(-14.08));
        vectorsElements.add(new MyDouble(-46.51));

        MyMatrix<MyDouble> matrix = new MyMatrix<>(4, matrixElements);
        MyMatrix<MyDouble> vector = new MyMatrix<>(1,vectorsElements);

        List<List<BigDecimal>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        results.get(0).add(BigDecimal.valueOf(4.1));
        results.add(new ArrayList<>());
        results.get(1).add(BigDecimal.valueOf(-2.4));
        results.add(new ArrayList<>());
        results.get(2).add(BigDecimal.valueOf((3.2)));
        results.add(new ArrayList<>());
        results.get(3).add(BigDecimal.valueOf((-1.3)));

        Gauss<MyDouble> G = new Gauss<>();
        MyMatrix<MyDouble> solution = G.G(matrix, vector, "G");

        assertEquals("G, primitives, first", results.get(3).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(0).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("G, primitives, second", results.get(2).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(1).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("G, primitives, third", results.get(1).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(2).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("G, primitives, fourth", results.get(0).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(3).get(0).setScale(5, RoundingMode.HALF_UP));

    }

    @Test
    public void test_normal_gauss_fractions() {
        List<MyFractions> matrixElements = new ArrayList<>();
        List<MyFractions> vectorsElements = new ArrayList<>();

        matrixElements.add(new MyFractions(new BigInteger("12"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("26"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("15"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("45"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("98"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-4"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("57"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-3"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-35"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("45"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-52"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("42"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-34"), BigInteger.TEN));

        vectorsElements.add(new MyFractions(new BigInteger("1315"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("4984"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("-1408"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("-4651"), new BigInteger("100")));

        MyMatrix<MyFractions> matrix = new MyMatrix<>(4, matrixElements);
        MyMatrix<MyFractions> vector = new MyMatrix<>(1, vectorsElements);

        List<List<BigDecimal>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        results.get(0).add(BigDecimal.valueOf(4.1));
        results.add(new ArrayList<>());
        results.get(1).add(BigDecimal.valueOf(-2.4));
        results.add(new ArrayList<>());
        results.get(2).add(BigDecimal.valueOf((3.2)));
        results.add(new ArrayList<>());
        results.get(3).add(BigDecimal.valueOf((-1.3)));

        Gauss<MyFractions> G = new Gauss<>();
        MyMatrix<MyFractions> solution = G.G(matrix, vector, "G");

        assertEquals("G, fraction, first", results.get(3).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(0).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("G, fraction, second", results.get(2).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(1).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("G, fraction, third", results.get(1).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(2).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("G, fraction, fourth", results.get(0).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(3).get(0).setScale(5, RoundingMode.HALF_UP));

    }


    @Test
    public void test_partial_gauss_primitives() {
        List<MyDouble> matrixElements = new ArrayList<>();
        List<MyDouble> vectorsElements = new ArrayList<>();

        matrixElements.add(new MyDouble(1.2));
        matrixElements.add(new MyDouble(2.6));
        matrixElements.add(new MyDouble(-0.1));
        matrixElements.add(new MyDouble(1.5));
        matrixElements.add(new MyDouble(4.5));
        matrixElements.add(new MyDouble(9.8));
        matrixElements.add(new MyDouble(-0.4));
        matrixElements.add(new MyDouble(5.7));
        matrixElements.add(new MyDouble(0.1));
        matrixElements.add(new MyDouble(-0.1));
        matrixElements.add(new MyDouble(-0.3));
        matrixElements.add(new MyDouble(-3.5));
        matrixElements.add(new MyDouble(4.5));
        matrixElements.add(new MyDouble(-5.2));
        matrixElements.add(new MyDouble(4.2));
        matrixElements.add(new MyDouble(-3.4));

        vectorsElements.add(new MyDouble(13.15));
        vectorsElements.add(new MyDouble(49.84));
        vectorsElements.add(new MyDouble(-14.08));
        vectorsElements.add(new MyDouble(-46.51));

        MyMatrix<MyDouble> matrix = new MyMatrix<>(4, matrixElements);
        MyMatrix<MyDouble> vector = new MyMatrix<>(1, vectorsElements);

        List<List<BigDecimal>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        results.get(0).add(BigDecimal.valueOf(4.1));
        results.add(new ArrayList<>());
        results.get(1).add(BigDecimal.valueOf(-2.4));
        results.add(new ArrayList<>());
        results.get(2).add(BigDecimal.valueOf((3.2)));
        results.add(new ArrayList<>());
        results.get(3).add(BigDecimal.valueOf((-1.3)));

        Gauss<MyDouble> G = new Gauss<>();
        MyMatrix<MyDouble> solution = G.G(matrix, vector, "PG");

        assertEquals("PG, primitives, first", results.get(3).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(0).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("PG, primitives, second", results.get(2).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(1).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("PG, primitives, third", results.get(1).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(2).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("PG, primitives, fourth", results.get(0).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(3).get(0).setScale(5, RoundingMode.HALF_UP));

    }

    @Test
    public void test_partial_gauss_fractions() {
        List<MyFractions> matrixElements = new ArrayList<>();
        List<MyFractions> vectorsElements = new ArrayList<>();

        matrixElements.add(new MyFractions(new BigInteger("12"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("26"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("15"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("45"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("98"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-4"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("57"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-3"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-35"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("45"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-52"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("42"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-34"), BigInteger.TEN));

        vectorsElements.add(new MyFractions(new BigInteger("1315"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("4984"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("-1408"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("-4651"), new BigInteger("100")));

        MyMatrix<MyFractions> matrix = new MyMatrix<>(4, matrixElements);
        MyMatrix<MyFractions> vector = new MyMatrix<>(1, vectorsElements);

        List<List<BigDecimal>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        results.get(0).add(BigDecimal.valueOf(4.1));
        results.add(new ArrayList<>());
        results.get(1).add(BigDecimal.valueOf(-2.4));
        results.add(new ArrayList<>());
        results.get(2).add(BigDecimal.valueOf((3.2)));
        results.add(new ArrayList<>());
        results.get(3).add(BigDecimal.valueOf((-1.3)));

        Gauss<MyFractions> G = new Gauss<>();
        MyMatrix<MyFractions> solution = G.G(matrix, vector, "PG");

        assertEquals("PG, fraction, first", results.get(3).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(0).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("PG, fraction, second", results.get(2).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(1).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("PG, fraction, third", results.get(1).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(2).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("PG, fraction, fourth", results.get(0).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(3).get(0).setScale(5, RoundingMode.HALF_UP));

    }

    @Test
    public void test_full_gauss_primitives() {
        List<MyDouble> matrixElements = new ArrayList<>();
        List<MyDouble> vectorsElements = new ArrayList<>();

        matrixElements.add(new MyDouble(1.2));
        matrixElements.add(new MyDouble(2.6));
        matrixElements.add(new MyDouble(-0.1));
        matrixElements.add(new MyDouble(1.5));
        matrixElements.add(new MyDouble(4.5));
        matrixElements.add(new MyDouble(9.8));
        matrixElements.add(new MyDouble(-0.4));
        matrixElements.add(new MyDouble(5.7));
        matrixElements.add(new MyDouble(0.1));
        matrixElements.add(new MyDouble(-0.1));
        matrixElements.add(new MyDouble(-0.3));
        matrixElements.add(new MyDouble(-3.5));
        matrixElements.add(new MyDouble(4.5));
        matrixElements.add(new MyDouble(-5.2));
        matrixElements.add(new MyDouble(4.2));
        matrixElements.add(new MyDouble(-3.4));

        vectorsElements.add(new MyDouble(13.15));
        vectorsElements.add(new MyDouble(49.84));
        vectorsElements.add(new MyDouble(-14.08));
        vectorsElements.add(new MyDouble(-46.51));

        MyMatrix<MyDouble> matrix = new MyMatrix<>(4, matrixElements);
        MyMatrix<MyDouble> vector = new MyMatrix<>(1, vectorsElements);

        List<List<BigDecimal>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        results.get(0).add(BigDecimal.valueOf(4.1));
        results.add(new ArrayList<>());
        results.get(1).add(BigDecimal.valueOf(-2.4));
        results.add(new ArrayList<>());
        results.get(2).add(BigDecimal.valueOf((3.2)));
        results.add(new ArrayList<>());
        results.get(3).add(BigDecimal.valueOf((-1.3)));

        Gauss<MyDouble> G = new Gauss<>();
        MyMatrix<MyDouble> solution = G.G(matrix, vector, "FG");

        assertEquals("FG, primitives, first", results.get(3).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(0).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("FG, primitives, second", results.get(2).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(1).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("FG, primitives, third", results.get(1).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(2).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("FG, primitives, fourth", results.get(0).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(3).get(0).setScale(5, RoundingMode.HALF_UP));

    }

    @Test
    public void test_full_gauss_fractions() {
        List<MyFractions> matrixElements = new ArrayList<>();
        List<MyFractions> vectorsElements = new ArrayList<>();

        matrixElements.add(new MyFractions(new BigInteger("12"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("26"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("15"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("45"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("98"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-4"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("57"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-1"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-3"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-35"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("45"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-52"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("42"), BigInteger.TEN));
        matrixElements.add(new MyFractions(new BigInteger("-34"), BigInteger.TEN));

        vectorsElements.add(new MyFractions(new BigInteger("1315"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("4984"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("-1408"), new BigInteger("100")));
        vectorsElements.add(new MyFractions(new BigInteger("-4651"), new BigInteger("100")));

        MyMatrix<MyFractions> matrix = new MyMatrix<>(4, matrixElements);
        MyMatrix<MyFractions> vector = new MyMatrix<>(1, vectorsElements);

        List<List<BigDecimal>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        results.get(0).add(BigDecimal.valueOf(4.1));
        results.add(new ArrayList<>());
        results.get(1).add(BigDecimal.valueOf(-2.4));
        results.add(new ArrayList<>());
        results.get(2).add(BigDecimal.valueOf((3.2)));
        results.add(new ArrayList<>());
        results.get(3).add(BigDecimal.valueOf((-1.3)));

        Gauss<MyFractions> G = new Gauss<>();
        MyMatrix<MyFractions> solution = G.G(matrix, vector, "G");

        assertEquals("FG, fraction, first", results.get(3).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(0).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("FG, fraction, second", results.get(2).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(1).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("FG, fraction, third", results.get(1).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(2).get(0).setScale(5, RoundingMode.HALF_UP));
        assertEquals("FG, fraction, fourth", results.get(0).get(0).setScale(5, RoundingMode.HALF_UP), solution.getMatrixWithNumbers().get(3).get(0).setScale(5, RoundingMode.HALF_UP));

    }
}

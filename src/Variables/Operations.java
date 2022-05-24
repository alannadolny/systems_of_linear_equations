package Variables;

import java.math.BigDecimal;

public interface Operations<T> {
    void add(T a);

    void subtract(T a);

    void multiply(T a);

    void divide(T a);

    T initialize(T a);

    T absolute();

    T initializeWithZero();

    Integer compare(T a);

    BigDecimal returnValue();
}
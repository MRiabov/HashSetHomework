import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarSetTest {

    private CarSet carSet;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 100; i++) {
            carSet.add(new Car("Brand"+i, i));
        }
    }

    @Test
    void whenAdd3SameObjectsThenSizeIncreaseBy1() {
        assertEquals(100,carSet.size());
        assertTrue(carSet.add(new Car("Brand",1 )));
        assertFalse(carSet.add(new Car("Brand",1 )));
        assertFalse(carSet.add(new Car("Brand",1 )));
        assertEquals(101,carSet.size());
    }
    @Test
    void whenClearSize0() {
        carSet.clear();
        assertEquals(30,carSet.size());
    }

    @Test
    void whenRemoveForSecondTimeChangeNothing() {
        assertTrue(carSet.remove(new Car("Brand30",30)));
        assertEquals(99,carSet.size());
        assertFalse(carSet.remove(new Car("Brand30",30)));
        assertEquals(99,carSet.size());

    }

    @Test
    void sizeShouldBeOneHundred() {
        assertEquals(100,carSet.size());
    }

}
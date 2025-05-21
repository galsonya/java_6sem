import org.example.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тесты для класса {@link Injector}.
 */
public class InjectorTest {
    private Injector injector;

    /**
     * Метод, выполняющийся перед каждым тестом, инициализирует экземпляр Injector.
     */
    @Before
    public void setUp() throws Exception {
        injector = new Injector();
    }

    /**
     * Тест для проверки внедрения зависимостей с использованием OtherImpl.
     */
    @Test
    public void testInjectWithOtherImpl() throws Exception {
        SomeBean bean = injector.inject(new SomeBean());
        assertNotNull(bean);
        assertNotNull(bean.field1);
        assertNotNull(bean.field2);
        assertTrue(bean.field1 instanceof OtherImpl);
        assertTrue(bean.field2 instanceof SODoer);
    }
}

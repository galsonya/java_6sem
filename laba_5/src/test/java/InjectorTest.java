import org.example.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InjectorTest {
    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = new Injector();
    }

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

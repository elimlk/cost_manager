
package test;
import Model.CostItem;
import Model.CostManagerException;
import Model.Currency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CostItemTest {
    @BeforeEach
    void setUp() throws CostManagerException {
        CostItem costItem1 = new CostItem(0,"test unit (set day)","test", Currency.ILS.toString(),"2011-02-31");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setDay() {
    }
}

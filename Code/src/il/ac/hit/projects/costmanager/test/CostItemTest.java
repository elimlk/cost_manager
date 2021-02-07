/*
Submitted by:
Itamar Yacoby 203023338
Eliran Malka 302830617
Idan Shturm 208535674
 */

package il.ac.hit.projects.costmanager.test;
import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.model.CostManagerException;
import il.ac.hit.projects.costmanager.model.DerbyDBModel;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CostItemTest {

    CostItem item;
    @BeforeEach
    void setUp() throws CostManagerException {
        DerbyDBModel db = new DerbyDBModel();
        db.initDB();

        item = new CostItem("20","junit test",
                "Food",
                "ILS",
                "2020-02-12");
    }


    @Test
    void CostItemConstructorTest() {
        assertThrows(CostManagerException.class,
                ()->{
                    CostItem item = new CostItem("20","junit test",
                            "Food",
                            "ILS",
                            "2020-11-33");
                });
    }

    @Test
    void isDateValid() {
        assertTrue(CostItem.isDateValid("1992-10-12"));
        assertTrue(CostItem.isDateValid("2000-02-29"));
        assertFalse(CostItem.isDateValid("1992-13-12"));
        assertTrue(CostItem.isDateValid("0992-10-12"));
        assertFalse(CostItem.isDateValid("1992,10,12"));

    }

    @Test
    void setSum() {
        assertThrows(CostManagerException.class,
                ()->{
                    item.setSum("-20");
                });
        assertThrows(CostManagerException.class,
                ()->{
                    item.setSum("0");
                });
    }


    @Test
    void setCategoryFaultyInput() {
        assertThrows(CostManagerException.class,
                ()->{
                    item.setCategory("testing");
                });
    }

    @Test
    void setCategoryValidInput() throws CostManagerException {
        item.setCategory("Cars");
        item.setCategory("Food");
    }
}
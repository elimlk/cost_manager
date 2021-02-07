package il.ac.hit.projects.costmanager.test;

import il.ac.hit.projects.costmanager.model.CostManagerException;
import il.ac.hit.projects.costmanager.model.DerbyDBModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DerbyDBModelTest {

    DerbyDBModel dbModel;

    @BeforeEach
    void setUp() {
        dbModel = new DerbyDBModel();
        dbModel.initDB();

    }

    @Test
    void addCategory() {
        assertThrows(CostManagerException.class,
                ()->{
                    dbModel.addCategory("Food");
                });
    }
}
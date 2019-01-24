package hackerrank;

import static junit.framework.TestCase.assertEquals;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleUnitTest {
    private static FurnitureOrder furnitureFactory;

    @BeforeClass
    public static void instantiate() {
        furnitureFactory = new FurnitureOrder();
    }

    @Test
    public void _01_getChairCount() {
        assertEquals(0, furnitureFactory.getTypeCount(Furniture.CHAIR));
    }

    @Test
    public void _02_orderFourChairs() {
        furnitureFactory.addToOrder(Furniture.CHAIR, 4);
        assertEquals(4, furnitureFactory.getTypeCount(Furniture.CHAIR));
    }

    @Test
    public void _03_orderThreeCouches() {
        furnitureFactory.addToOrder(Furniture.COUCH, 3);
        assertEquals(3, furnitureFactory.getTypeCount(Furniture.COUCH));
    }

    @Test
    public void _04_orderedChairsCost() {
        assertEquals(400.0f, furnitureFactory.getTypeCost(Furniture.CHAIR));
    }

    @Test
    public void _05_orderedTablesCost() {
        assertEquals(0.0f, furnitureFactory.getTypeCost(Furniture.TABLE));
    }

    @Test
    public void _06_orderedCouchesCost() {
        assertEquals(1500.0f, furnitureFactory.getTypeCost(Furniture.COUCH));
    }

    @Test
    public void _07_totalOrderCost() {
        furnitureFactory.addToOrder(Furniture.TABLE, 6);
        assertEquals(3400.0f, furnitureFactory.getTotalOrderCost());
    }
}

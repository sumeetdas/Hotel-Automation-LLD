package hotelauto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import hotelauto.corridors.MainCorridor;
import hotelauto.corridors.SubCorridor;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;
import hotelauto.powerstrategy.MainCorridorDefaultPowerStrategy;
import hotelauto.powerstrategy.SubCorridorPowerOffStrategy;
import hotelauto.vo.Floor;
import hotelauto.vo.Hotel;

@RunWith(value = JUnit4.class)
public class SubCorridorPowerOffStrategyTest {

    private HotelAutomation hotelAutomation = null;

    @Before
    public void setUp() throws CloneNotSupportedException {
        final Hotel hotel = new Hotel();

        Floor firstFloor = new Floor("Floor 1");
        hotel.addFloor(firstFloor);
        
        MainCorridor mainCorridor1 = new MainCorridor("Main Corridor 1", new MainCorridorDefaultPowerStrategy());
        
        AC mainAc = new AC("AC");
        Light mainLight = new Light("Light 1");
        
        mainCorridor1.addEquipment(mainAc);
        mainCorridor1.addEquipment(mainLight);
        
        firstFloor.addCorridor(mainCorridor1);

        SubCorridor subCorridor1 = new SubCorridor("Sub Corridor 1",  new SubCorridorPowerOffStrategy());

        AC acSub1 = new AC("AC");
        Light lightSub1 = new Light("Light 1");

        subCorridor1.addEquipment(acSub1);
        subCorridor1.addEquipment(lightSub1);
        firstFloor.addCorridor(subCorridor1);

        SubCorridor subCorridor2 = new SubCorridor("Sub Corridor 2", new SubCorridorPowerOffStrategy());

        AC acSub2 = new AC("AC");
        Light lightSub2 = new Light("Light 2");

        subCorridor2.addEquipment(acSub2);
        subCorridor2.addEquipment(lightSub2);
        firstFloor.addCorridor(subCorridor2);

        // second floor
        Floor secondFloor = firstFloor.clone();
        hotel.addFloor(secondFloor);

        secondFloor.setName("Floor 2");

        hotelAutomation =  new HotelAutomation(hotel);
    }

    @Test
    public void testInitialHotelState() {
        System.out.println("Yahoo!");
        System.out.println(hotelAutomation.getHotel().toString());
    }
    
}

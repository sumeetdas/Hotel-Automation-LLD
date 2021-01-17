package hotelauto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import hotelauto.corridors.MainCorridor;
import hotelauto.corridors.SubCorridor;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;
import hotelauto.powerstrategy.MainCorridorDefaultPowerStrategy;
import hotelauto.powerstrategy.SubCorridorPowerOffStrategy;
import hotelauto.vo.Floor;
import hotelauto.vo.Hotel;

@RunWith(value = JUnit4.class)
public class HotelAutomationTest {

    private HotelAutomation hotelAutomation = null;

    @Before
    public void setUp() {
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
    public void testFloorClone() {
        Floor secondFloor = hotelAutomation.getHotel().getFloors().get(1); 
        Floor thirdFloor = hotelAutomation.getHotel().getFloors().get(0).clone();
        thirdFloor.addCorridor(thirdFloor.getCorridors().get(1).clone());
        
        assertEquals("Second floor should have three corridors", 3, secondFloor.getCorridors().size());
        assertEquals("Third floor should have four corridors", 4, thirdFloor.getCorridors().size());
    }

    @Test
    public void testInitialHotelState() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\t\t\tFloor 1\n");
        sb.append("Main Corridor 1 AC: ON  Light 1: ON  \n");
        sb.append("Sub Corridor 1 AC: ON  Light 1: OFF  \n");
        sb.append("Sub Corridor 2 AC: ON  Light 2: OFF  \n");
        sb.append("\t\t\tFloor 2\n");
        sb.append("Main Corridor 1 AC: ON  Light 1: ON  \n");
        sb.append("Sub Corridor 1 AC: ON  Light 1: OFF  \n");
        sb.append("Sub Corridor 2 AC: ON  Light 2: OFF  \n");

        Assert.assertEquals("Initial state should be as expected", sb.toString(), hotelAutomation.getHotel().toString());
    }
    
    @Test
    public void testMovementInFloor1SubCorridor1() throws InterruptedException {
        hotelAutomation.getSensorService().receiveSignal("Floor 1.Sub Corridor 1", SignalTypeEnum.MOVEMENT);
        Floor firstFloor = hotelAutomation.getHotel().getFloors().get(0);
        assertTrue("AC should be OFF and Light should be ON", firstFloor.toString().contains("Sub Corridor 1 AC: OFF  Light 1: ON"));
        Thread.sleep(60_100);
        hotelAutomation.getSensorService().receiveSignal("Floor 1.Sub Corridor 1", SignalTypeEnum.NO_MOVEMENT);
        Assert.assertTrue("AC should be ON and Light should be OFF", firstFloor.toString().contains("Sub Corridor 1 AC: ON  Light 1: OFF"));
    }
}

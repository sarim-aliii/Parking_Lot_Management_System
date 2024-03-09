import java.util.ArrayList;
import java.util.List;

class Vehicle{
    String type;
    String registration;
    String color;
    Vehicle(String type, String registration, String color){
        this.type = type;
        this.registration = registration;
        this.color = color;
    }
}

class Slot{
    String type;
    Vehicle vehicle;                   // type of vehicle parked in the slot
    String ticketId;

    public Slot(String type) {
        this.type = type;
        this.vehicle = null;
        this.ticketId = null;
    }
}
class ParkingLot {
    String parkingLotId;
    List<List<Slot>> slots;  // This is a list of all the slots in the parking lot.
                           // The list of lists represents slots on multiple floors.
                         //The floors and slots are numbered according to the list index.

    ParkingLot(String parkingLotId, int numOfFloors, int numOfSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        slots = new ArrayList<>();
        for (int i=0; i<numOfFloors; i++){
            slots.add(new ArrayList<>());
            List<Slot> floorSlots = slots.get(i);
            // On each floor, the first slot goes to truck, the next two go to bike,
            // and the rest are for cars.
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));
            for (int j=3; j<numOfSlotsPerFloor; j++){
                floorSlots.add(new Slot("car"));
            }
        }
    }

    public String parkVehicle(String type, String regNo, String color){
        Vehicle vehicle = new Vehicle(type, regNo, color);
        // To determine whether a slot is empty or not,
        // check if the slot can take the given vehicle type and
        // if the slot's vehicle field is null
        for (int i=0; i<slots.size(); i++){
            for (int j=0; j<slots.get(i).size(); j++){
                Slot slot = slots.get(i).get(j);
                if (slot.type == type && slot.vehicle == null){
                    slot.vehicle = vehicle;
                    slot.ticketId = generateTicketId(i+1, j+1);
                    return slot.ticketId;
                }
            }
        }
        System.out.println("We don't have an empty slot to park your vehicle");
        return null;
    }
    // This method does not need to be exposed outside the class, so it's private.
    private String generateTicketId(int flr, int slno){
        return parkingLotId + "_" + flr + "_" + slno;
    }

    // To un-park the vehicle, parse the ticket id to get the floor and slot number where the car is parked.
    public void unPark(String ticketId){
        String [] extract = ticketId.split("_");
        // Surround the parsing logic with a try-catch
        // in case the user passes an invalid ticket id.
       try {
           int floorIndex = Integer.parseInt(extract[1])-1;     // Array index starts from 0
           int slotIndex = Integer.parseInt(extract[2])-1;
           for (int i=0; i<slots.size(); i++){
               for (int j=0; j<slots.get(i).size(); j++){
                   if (i == floorIndex && j == slotIndex){
                       Slot slot = slots.get(i).get(j);
                       slot.vehicle = null;
                       slot.ticketId = null;
                       System.out.println("Unparked vehicle");
                   }
               }
           }
       } catch (Exception e){
           e.printStackTrace();
       }
    }
    public int getNoOfOpenSlots(String type){
        int count = 0;
        for (List<Slot> floor : slots){
            for (Slot slot : floor){
                if (slot.vehicle == null && slot.type.equals(type)){
                    count++;
                }
            }
        }
        System.out.printf("No. of open slots for %s: "+ count, type);
        System.out.println();
        return count;
    }
    public void displayOpenSlots(String type){
        for (int i=0; i<slots.size(); i++){
            for (int j=0; j<slots.get(i).size(); j++){
                Slot slot = slots.get(i).get(j);
                if (slot.vehicle == null && slot.type.equals(type)){
                    System.out.println("Floor: " + (i+1) + " Slot: " + (j+1));
                }
            }
        }
    }
    public void displayOccupiedSlots(String type){
        System.out.printf("Occupied slots for %s\n", type);
        for (int i=0; i<slots.size(); i++){
            for (int j=0; j<slots.get(i).size(); j++){
                Slot slot = slots.get(i).get(j);
                if (slot.vehicle != null && slot.type.equals(type)){
                    System.out.println("Floor: " + (i+1) + " Slot: " + (j+1));
                }
            }
        }
    }
}

public class main {
    public static void main(String[] args) {
        int numOfFloors = 4;
        int numOfSlotsPerFloor = 6;
        ParkingLot parkingLot = new ParkingLot("PR1234", numOfFloors, numOfSlotsPerFloor);

        parkingLot.getNoOfOpenSlots("car");
        String ticket1 = parkingLot.parkVehicle("car", "MH-03", "red");
        String ticket2 = parkingLot.parkVehicle("car", "MH-04", "purple");

        parkingLot.displayOccupiedSlots("car");

        // un-park
//        parkingLot.unPark(ticket2);
//        parkingLot.displayOccupiedSlots("car");

        // park truck
//        parkingLot.displayOpenSlots("truck");
//        parkingLot.parkVehicle("truck", "MH-01", "black");
//        parkingLot.displayOccupiedSlots("truck");
    }
}

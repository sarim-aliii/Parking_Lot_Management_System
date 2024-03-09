# Parking_Lot_Management_System
We are going to build a simple command-line application for a parking lot management system.

 ****   Project Requirements  ****
Our goal is to create an application for a parking lot system. As everyone visualizes projects differently, there may be many features that come into your mind.

But before jumping into the implementation, we first need to lay down specific requirements. It's always important to start with the basic requirements first, so that's what we'll do here.

Here is a list of all the requirements:

A parking lot should have a parking lot ID, number of floors, and number of slots on each floor.

Each slot in the parking lot has a type of vehicle that can park there. Valid types are car, bike, and truck. You can include any vehicle type as per your needs. You can also decide which slots should go to each type. Here, I am assigning the first slot on each floor to a truck, the next two for bikes, and rest for cars.

When a vehicle pulls into the parking lot, the application takes the vehicle's type, registration number, and its color. You can also have additional details like the vehicle's name, the driver's name, and so on.

After that, a slot is assigned to the vehicle. The selection strategy could be anything you want. Here, we will choose the lowest floor and earliest available slot.

When a vehicle is assigned a slot, the app should return a ticket. The ticket should be a string of the form <parking lot id>_<floor no>_<slot no>. For example, the vehicle parked on floor 2, slot 5 would be PR123_2_5.

To unpark the vehicle, the user should present the valid ticket. After that, the vehicle is removed from the slot.

The app should be able to display the number of available slots and a list of available and unavailable slots for a specific vehicle type.

**************   Java Classes : 
Let's understand how we are going to design the application. Since it is a command line application, we are only going to write the business logic of a parking lot and display outputs on a console.

We will have separate classes representing the entities of our application: Vehicle and Slot. Then, we'll create a ParkingLot class that contains the core business logic of our application. Our main driver class will interact with this class for all the functionalities.

Now, let's define our classes. Each class will have a constructor to initialize their fields.

Vehicle class: type, registration, color (All are of string type)

Slot class: type (string)
            vehicle (Vehicle): type of vehicle parked in the slot
            ticketId (string): ticket id assigned to the vehicle parked in this slot, initially null.

ParkingLot class:   parkingLotId (string)
                    slots (List<List<Slot>>): This is a list of all the slots in the parking lot. 
                    The list of lists represents slots on multiple floors. 
                    The floors and slots are numbered according to the list index.
                    Constructor: ParkingLot(parkingLotId, nfloors, noOfSlotsPerFlr)

Methods in ParkingLot class:
1. parkVehicle(type, regNo, color): takes all the parameters of a vehicle, assigns a slot, and returns the ticket
2. unPark(ticketId): takes the ticket id and removes the vehicle from the slot
3. getNoOfOpenSlots(type): returns the number of slots for vehicle type
4. displayOpenSlots(type): displays all open slots for vehicle type
5. displayOccupiedSlots(type): displays all occupied slots for vehicle type

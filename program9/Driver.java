package program9;

public class Driver {
    public static void main(String[] args) {

        SalaryManager manager = new SalaryManager();

        manager.create("unit1.txt");
        manager.create("unit2.txt");

        manager.addTo("unit1.txt", "unit1a.txt", 1001, 50000, 3);
        manager.addTo("unit1a.txt", "unit1b.txt", 1003, 60000, 6);
        manager.addTo("unit1b.txt", "unit1.txt", 1005, 70000, 8);

        manager.addTo("unit2.txt", "unit2a.txt", 1002, 55000, 4);
        manager.addTo("unit2a.txt", "unit2b.txt", 1003, 65000, 5);
        manager.addTo("unit2b.txt", "unit2.txt", 1006, 80000, 10);

        System.out.println("Unit 1:");
        manager.display("unit1.txt");

        System.out.println("\nUnit 2:");
        manager.display("unit2.txt");

        manager.addService("unit1.txt", "unit1_service.txt");

        System.out.println("\nAfter adding service:");
        manager.display("unit1_service.txt");

        int raised = manager.raise("unit1.txt", "unit1_raise.txt", 5, 10);

        System.out.println("\nNumber of raises: " + raised);
        manager.display("unit1_raise.txt");

        manager.mergeFiles("unit1.txt", "unit2.txt", "merged.txt");

        System.out.println("\nMerged File:");
        manager.display("merged.txt");

        boolean removed = manager.removeFrom("merged.txt", "removed.txt", 1003);

        System.out.println("\nRemoved 1003? " + removed);
        manager.display("removed.txt");
    }
}
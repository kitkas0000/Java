
package fruitshop;

public class main {

    public static void main(String[] args) {
        // TODO code application logic here
        Manager manager = new Manager();
        Validation validation = new Validation();
        manager.generateFruit();
        while (true) {
            System.out.println("1. Create Fruit");
            System.out.println("2. View orders");
            System.out.println("3. Shopping (for buyer)");
            System.out.println("4. Exit");
            int choice = validation.inputInt("Enter choice:", 1, 4);
            switch (choice) {
                case 1:
                    manager.createFruit();
                    break;
                case 2:
                    manager.viewOrder();
                    break;
                case 3:
                    manager.shopping();
                    break;
                case 4:
                    return;
            }
        }
    }

}

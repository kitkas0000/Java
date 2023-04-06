package CarPrj.Classes;

import CarPrj.*;
import CarPrj.Classes.*;
import CarPrj.Classes.Com.*;
import java.io.*;
import java.util.*;

public class CarManager {

    public static void main(String[] args) throws IOException {
        int choice;
        boolean checkSuccessful;
        String fileCarsName = "D:\\Code\\Java\\CarPrj\\CarPrj\\src\\CarPrj\\cars.txt";
        String fileBrandsName = "D:\\Code\\Java\\CarPrj\\CarPrj\\src\\CarPrj\\brands.txt";
        BrandList brandList = new BrandList(); // Create an empty BrandList
        CarList carList = new CarList(brandList); // Create an empty CarList
        brandList.loadFromFile(fileBrandsName); // Load brands from the file brands.txt to brandList
        carList.loadFromFile(fileCarsName); // Load cars from the file cars.txt to carList
        String bID, brandCarID;
        ArrayList<String> ops = new ArrayList<String>(11); // Create ArrayList ops of strings containing options of the
                                                           // program

        ops.add("\nCar managing Program");
        ops.add("1 - List all brands");
        ops.add("2 - Add a new brand");
        ops.add("3 - Search a brand based on its ID");
        ops.add("4 - Update a brand");
        ops.add("5 - Save brands to the file, named brands.txt");
        ops.add("6 - List all cars in ascending order of brand names");
        ops.add("7 - List cars based on a part of an input brand name");
        ops.add("8 - Add a car");
        ops.add("9 - Remove a car based on its ID");
        ops.add("10 - Update a car based on its ID");
        ops.add("11 - Save cars to file, named cars.txt");

        Menu menu = new Menu(); // Create a menu

        do {
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.print("Input brand ID: ");
                    bID = new Scanner(System.in).nextLine();
                    if (brandList.searchID(bID) == -1) {
                        System.out.print("Brand ID not found !");
                    } else {
                        System.out.println(brandList.get(brandList.searchID(bID)).toString());
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile(fileBrandsName);
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    System.out.print("Input brand: ");
                    String brandName = new Scanner(System.in).nextLine();
                    carList.printBasedBrandName(brandName);
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    checkSuccessful = carList.removeCar();
                    if (checkSuccessful) {
                        System.out.println("Car removed successfully !");
                    } else {
                        System.out.println("Not Found !");
                    }
                    break;
                case 10:
                    checkSuccessful = carList.updateCar();
                    if (checkSuccessful) {
                        System.out.println("Car updated successfully !");
                    } else {
                        System.out.println("Car removed unsuccessfully !");
                    }
                    break;
                case 11:
                    carList.saveToFile(fileCarsName);
                    break;
            }
        } while ((choice > 0) && (choice <= 11));
    }
}

package CarPrj.Classes.Com;

import java.io.*;
import java.util.*;

public class BrandList extends ArrayList<Brand> {

    private String brandID, brandName, soundBrand;
    private double price;
    Menu menu = new Menu();
    Scanner scanner = new Scanner(System.in);
    PrintWriter pw;
    BufferedReader br;
    BrandList brandList;
    String[] arr;
    String line;
    

    public boolean loadFromFile(String fileName) throws IOException {
        try {
            br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while ((line != null)) {
                arr = line.split(",");
                brandID = arr[0].trim();
                brandName = arr[1].trim();
                soundBrand = arr[2].split(":")[0].trim();
                price = Double.parseDouble(arr[2].split(":")[1].trim());
                this.add(new Brand(brandID, brandName, soundBrand, price));
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found !");
        }
        return false;
    }

    public boolean saveToFile(String fileName) {
        try {
            pw = new PrintWriter(new FileWriter(fileName));
            for (Brand i : this) {
                pw.println(i);
            }
            pw.close();
            System.out.println("Save Successfully");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Open the file based on the filename to write data in line-by-line text format
    public int searchID(String bID) {
        for (int i = 0; i < this.size(); i++) {
            if (bID.equals(this.get(i).getBrandID())) {
                return i;
            }
        }
        return -1;
    }

    //Transform the list to a menu, the user will choose a brand from this menu
    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }

    //Add a new Brand to the list
    public void addBrand() {
        boolean checkBrandID = false;
        //System.out.println("Test: " + this.get(2).getBrandID());
        do {
            System.out.print("Input brand ID: ");
            brandID = scanner.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (brandID.equals(this.get(i).getBrandID())) {
                    checkBrandID = true;
                    System.out.println("This brand ID is existed. Try another one!");
                    break;
                } else {
                    checkBrandID = false;
                }
            }
        } while (checkBrandID == true);
        do {
            System.out.print("Input brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.println("The brand name must not be null. Try again !");
        } while (true);
        do {
            System.out.print("Input sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.println("The sound brand must not be null. Try again !");
        } while (true);
        do {
            System.out.print("Input price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("The price must not be null. Try again !");
                }
            } catch (NumberFormatException e) {
                System.out.println("The price must be a number. Try again !");
                price = 0;
            }
        } while (price == 0);
        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("Brand has added successfully");
    }

    //Update brand_name, sound_brand, price of an existed brand
    public boolean updateBrand() {
        int pos;
        String updatedID;
        System.out.print("Input brand ID to updated: ");
        updatedID = scanner.nextLine();
        pos = searchID(updatedID);
        if (pos >= 0) {

            do {
                System.out.print("Input brand name: ");
                brandName = scanner.nextLine();
                if (brandName.equals("") != true) {
                    break;
                }
                System.out.println("The name must not be null. Try again !");
            } while (true);
            do {
                System.out.print("Input sound manufacturer: ");
                soundBrand = scanner.nextLine();
                if (soundBrand.equals("") != true) {
                    break;
                }
                System.out.println("The sound brand must not be null. Try again !");
            } while (true);
            do {
                System.out.print("Input price: ");
                try {
                    price = Double.parseDouble(scanner.nextLine());
                    if (price <= 0) {
                        System.out.println("The price must not be null. Try again !");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The price must be a number. Try again !");
                    price = 0;
                }
            } while (price == 0);
            this.get(pos).setUpdatedBrand(brandName, soundBrand, price);
            System.out.println("Brand has added successfully");
            return true;
        } else {
            System.out.println("Not Found !");
        }
        return false;

    }

    //Show the list of the brands
    public void listBrands() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }
}

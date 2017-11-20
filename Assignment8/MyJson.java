package Assignment8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class MyJson {
	private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
		FileReader reader = new FileReader(file);
		Scanner in = new Scanner(reader);
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		String[] data;

		in.nextLine();
		while (in.hasNextLine()) {
			data = in.nextLine().split("~");
			Vehicle vehicle = new Vehicle(data);
			vehicles.add(vehicle);
		}
		reader.close();
		return vehicles;

	}
	
	public static String getJsonString(ArrayList<Vehicle> vehicles) {
		String jsonData = "{\r\n\"" + vehicles.get(0).webId + "\" : [\r\n";
		for(int i = 0; i < vehicles.size(); i++) {
			jsonData += "{\r\n";
			jsonData = jsonData + "\"id\" : \"" + vehicles.get(i).id + "\",\r\n";
			jsonData = jsonData + "\"category\" : \"" + vehicles.get(i).category + "\",\r\n";
			jsonData = jsonData + "\"year\" : \"" + vehicles.get(i).year + "\",\r\n";
			jsonData = jsonData + "\"make\" : \"" + vehicles.get(i).make + "\",\r\n";
			jsonData = jsonData + "\"model\" : \"" + vehicles.get(i).model + "\",\r\n";
			jsonData = jsonData + "\"trim\" : \"" + vehicles.get(i).trim + "\",\r\n";
			jsonData = jsonData + "\"type\" : \"" + vehicles.get(i).type + "\",\r\n";
			jsonData = jsonData + "\"price\" : \"" + vehicles.get(i).price + "\",\r\n";
			jsonData = jsonData + "\"photo\" : \"" + vehicles.get(i).photo + "\"\r\n";
			if(i < vehicles.size() - 1)
				jsonData += "},\r\n";
			else
				jsonData += "}\r\n";
		}
		jsonData += "]\r\n}\r\n";
		
		
		return jsonData;
	}
	
	public static void writeToFile(String inputToWrite, String filePath) throws IOException {
		File outFile;
		if(filePath == null)
			outFile = new File("camino_out.txt");
		else
			outFile = new File(filePath + "camino_out.txt");
		FileWriter writer = new FileWriter(outFile);
		writer.write(inputToWrite);
		writer.close();
		
	}

	public static void main(String[] args) throws IOException {
	    File file = new File("camino.txt");
	    ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
	    String s = getJsonString(vehicles);
	    writeToFile(s, file.getParent());
	}

}

class Vehicle{

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
       switch (cat){
           case "used": return USED;
           case "new": return NEW;
           case "certified": return CERTIFIED;
           default: throw new IllegalArgumentException();
       }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "NEW";
            case USED: return "USED";
            case CERTIFIED: return "CERTIFIED";
            default: throw new IllegalArgumentException();
        }
    }
}
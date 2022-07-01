package myapp.core;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, Map<String, String>> ownerMap = new HashMap<>();
    private static String[] headerArray = new String[4];

    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("repository\\testfile.csv")))) {

            String input = scanner.nextLine();
            headerArray = input.split(",");

//            Map<String, Map<String, String>> ownerMap = new HashMap<>();

            int pos = 0;
            String owner = "Owner";
            while (scanner.hasNextLine()) {
                Map<String, String> map = new HashMap<>();
                int loop = 0;
                String[] temp = scanner.nextLine().split(",");
                for (String temp1 : temp) {
                    map.put(headerArray[loop], temp1);
                    loop++;
                }
                ownerMap.put(owner + " " + pos, map);
                pos++;
            }

            System.out.println(ownerMap);

        }

        try (BufferedWriter docFile = new BufferedWriter(new FileWriter("repository\\testfile_output.txt"))){

                for (String key : ownerMap.keySet()) {
                    for (String value : ownerMap.get(key).values()) {
                        docFile.write(key + "," + value + "\n");
                    }

                }
        }
        
    }
    
}
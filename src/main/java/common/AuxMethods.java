package common;

import org.javatuples.Pair;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class AuxMethods {
    private static String objectRepositoryPath = "src/main/objectRepository/";
    private static Yaml yaml = new Yaml();

    public static Pair getSelectorFromFile(String testObject) {
        Pair<String, String> elementSelector = null;
        String filePath = objectRepositoryPath + testObject + ".yaml";

        try {
            Reader reader = new FileReader(filePath);
            Map<String, Object> obj = yaml.load(reader);
            elementSelector = new Pair<String, String>(obj.keySet().toArray()[0].toString(), obj.values().toArray()[0].toString());
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(elementSelector);
        return elementSelector;
    }


}

import edu.duke.*;
import java.util.*;

public class GladLibMap{
    private HashMap<String, ArrayList<String>> myMap; 
    private ArrayList<String> words;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        words = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String s: labels){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String sub = getSubstitute(w.substring(first+1,last));
        while (true){
            int index = words.indexOf(sub);
            if (index == -1){
                words.add(sub);
                break;
            }
            else {
                sub = getSubstitute(w.substring(first + 1, last));
            }    
        }
        return sub;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            words.clear();
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n" + "Number of words replaced: " + words.size());
        System.out.println("Total number of words to pick from " + totalWordsInMap());
        System.out.println("Total words considered " + totalWordsConsidered());
    }
    
    private int totalWordsInMap(){
        int i = 0;
        for (String s: myMap.keySet()){
            i += myMap.get(s).size();
        }
        return i;
    }
    
    private int totalWordsConsidered(){
        int i = 0;
        ArrayList<String> al = new ArrayList<String>();
        FileResource fr = new FileResource("data/madtemplate2.txt");
        for (String s: fr.words()){
            if (s != processWord(s)){
                String a = s.substring(s.indexOf("<") + 1, s.indexOf(">"));
                if (a == "number"){
                    continue;
                }
                if (al.contains(a) == false){
                    al.add(a);
                }
            }
        }
        for (String b: al){
            i += myMap.get(b).size();
        }
        return i;
    }
}

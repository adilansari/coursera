import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class MP1 {
    Random generator;
    String userName;
    String inputFileName;
    String delimiters = " \t,;.?!-:@[](){}_*/";
    String[] stopWordsArray = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
            "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
            "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
            "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
            "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each",
            "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than",
            "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};

    void initialRandomGenerator(String seed) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update(seed.toLowerCase().trim().getBytes());
        byte[] seedMD5 = messageDigest.digest();

        long longSeed = 0;
        for (int i = 0; i < seedMD5.length; i++) {
            longSeed += ((long) seedMD5[i] & 0xffL) << (8 * i);
        }

        this.generator = new Random(longSeed);
    }

    Integer[] getIndexes() throws NoSuchAlgorithmException {
        Integer n = 10000;
        Integer number_of_lines = 50000;
        Integer[] ret = new Integer[n];
        this.initialRandomGenerator(this.userName);
        for (int i = 0; i < n; i++) {
            ret[i] = generator.nextInt(number_of_lines);
        }
        return ret;
    }

    public MP1(String userName, String inputFileName) {
        this.userName = userName;
        this.inputFileName = inputFileName;
    }

    public String[] process() throws Exception {
        String[] ret = new String[20];
        ArrayList<String> inputTokens = this.readInputFile();
        ArrayList<String> stopWords = new ArrayList<String>(Arrays.asList(stopWordsArray));
        HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();

        for (String str: inputTokens) {
            StringTokenizer tokens = new StringTokenizer(str, delimiters);
            while(tokens.hasMoreTokens()){
                String word = tokens.nextToken().toLowerCase().trim();
                if (stopWords.contains(word)){
                    continue;
                }

                Integer frequency = 0;
                if (frequencyMap.containsKey(word)) {
                    frequency = frequencyMap.get(word);
                }
                frequencyMap.put(word, frequency+1);
            }
        }

        TreeMap<String, Integer> sortedMap = SortByValue(frequencyMap);

        Iterator itr = sortedMap.keySet().iterator();
        int retIndex = 0;
        while(itr.hasNext()){
            if (retIndex == 20){
                break;
            }
            String key = (String) itr.next();
            int val = frequencyMap.get(key);
            System.out.println(key + " "+ val);
            retIndex++;
        }

        return ret;
    }

    public static TreeMap<String, Integer> SortByValue
            (HashMap<String, Integer> map) {
        ValueComparator vc =  new ValueComparator(map);
        TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
        sortedMap.putAll(map);
        return sortedMap;
    }

    public ArrayList<String> readInputFile() throws Exception{
        ArrayList<String> inputTokens = new ArrayList<String>();
        ArrayList<Integer> indicesToRead = new ArrayList<Integer>(Arrays.asList(getIndexes()));

        // try {
            // File file = new File(this.inputFileName);
            // FileReader fileReader = new FileReader(file);
            // BufferedReader bufferedReader = new BufferedReader(fileReader);
        //     while ((line = bufferedReader.readLine()) != null) {
        //         if (indicesToRead.contains(index)) {
        //             inputTokens.add(line);
        //         }
        //         index++;
        //     }
        //     fileReader.close();
        // }catch (IOException e) {
        //     e.printStackTrace();
        // }
            Scanner sc = new Scanner(new File(this.inputFileName));
            String line;
            int index = 0;
            while(sc.hasNext()){
                if (indicesToRead.contains(index)){
                    String word = sc.next();
                    inputTokens.add(word);
                    System.out.println(word + " "+ index);
                }
                index++;
            }
            sc.close();

        // 9060 objects

        return inputTokens;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.out.println("MP1 <User ID>");
        }
        else {
            String userName = args[0];
            String inputFileName = "./input.txt";
            MP1 mp = new MP1(userName, inputFileName);
            String[] topItems = mp.process();
            for (String item: topItems){
                System.out.println(item);
            }
        }
    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> map;

    public ValueComparator(Map<String, Integer> base) {
        this.map = base;
    }

    public int compare(String a, String b) {
        if (map.get(a) >= map.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}

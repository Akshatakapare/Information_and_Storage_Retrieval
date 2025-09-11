import java.io.*;
import java.util.*;

public class Conflation {
    static List<String> stopWords = Arrays.asList("is", "am", "are", "the", "and", "a", "an", "in", "on", "at", "of", "for", "to", "with", "by");

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int ch;

        do {
            System.out.println("\n--- Conflation Algorithm Menu ---");
            System.out.println("1. Display the file");
            System.out.println("2. Remove Stop Words");
            System.out.println("3. Suffix Stripping (Stemming)");
            System.out.println("4. Count Frequency");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    displayFile();
                    break;
                case 2:
                    removeStopWords();
                    break;
                case 3:
                    suffixStripping();
                    break;
                case 4:
                    countFrequency();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (ch != 5);
    }

    // 1. Display file content
    public static void displayFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Input.txt"));
        String line;
        System.out.println("\n--- File Content ---");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    // 2. Remove stop words
    public static void removeStopWords() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("NoStopWords.txt"));
        String line;
        System.out.println("\n--- Removing Stop Words ---");

        while ((line = br.readLine()) != null) {
            String[] words = line.toLowerCase().split("\\W+");
            for (String word : words) {
                if (!stopWords.contains(word) && !word.trim().isEmpty()) {
                    bw.write(word + " ");
                }
            }
            bw.newLine();
        }

        br.close();
        bw.close();
        System.out.println("Output saved to 'NoStopWords.txt'");
    }

    // 3. Suffix Stripping (very basic stemming)
    public static void suffixStripping() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("NoStopWords.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("StrippedWords.txt"));
        String line;

        System.out.println("\n--- Suffix Stripping ---");
        while ((line = br.readLine()) != null) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                String stemmed = word;
                if (word.endsWith("ing") && word.length() > 4)
                    stemmed = word.substring(0, word.length() - 3);
                else if (word.endsWith("ed") && word.length() > 3)
                    stemmed = word.substring(0, word.length() - 2);
                else if (word.endsWith("es") && word.length() > 3)
                    stemmed = word.substring(0, word.length() - 2);
                else if (word.endsWith("s") && word.length() > 2)
                    stemmed = word.substring(0, word.length() - 1);

                bw.write(stemmed + " ");
            }
            bw.newLine();
        }

        br.close();
        bw.close();
        System.out.println("Output saved to 'StrippedWords.txt'");
    }

    // 4. Word frequency count
    public static void countFrequency() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("StrippedWords.txt"));
        HashMap<String, Integer> freqMap = new HashMap<>();
        String line;

        System.out.println("\n--- Word Frequency ---");
        while ((line = br.readLine()) != null) {
            String[] words = line.toLowerCase().split("\\W+");
            for (String word : words) {
                if (!word.trim().isEmpty()) {
                    freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        br.close();
    }
}
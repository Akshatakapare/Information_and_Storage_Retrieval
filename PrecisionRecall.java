import java.util.*;

public class PrecisionRecall {

    public static void main(String[] args) {
        // Sample input
        String[] answerSetA = {"D1", "D2", "D3", "D4"};   // Retrieved docs
        String[] relevantDocs = {"D2", "D4", "D5"};       // Relevant docs

        // Convert to sets
        Set<String> A = new HashSet<>(Arrays.asList(answerSetA));
        Set<String> Rq1 = new HashSet<>(Arrays.asList(relevantDocs));

        // Find intersection (relevant retrieved)
        Set<String> intersection = new HashSet<>(A);
        intersection.retainAll(Rq1);

        int truePositives = intersection.size();
        int retrieved = A.size();
        int relevant = Rq1.size();

        // Calculate precision and recall
        double precision = (double) truePositives / retrieved;
        double recall = (double) truePositives / relevant;

        // Output
        System.out.println("Answer Set A: " + A);
        System.out.println("Relevant Docs Rq1: " + Rq1);
        System.out.println("Retrieved & Relevant (A ∩ Rq1): " + intersection);
        System.out.println("Precision = " + precision);
        System.out.println("Recall = " + recall);
    }
}

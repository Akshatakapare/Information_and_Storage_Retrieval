import java.util.*;

public class FMeasureEMeasure {

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

        // Precision and Recall
        double precision = (double) truePositives / retrieved;
        double recall = (double) truePositives / relevant;

        // F-measure (harmonic mean)
        double fMeasure = (precision + recall > 0) ? (2 * precision * recall) / (precision + recall) : 0.0;

        // E-measure (1 - F)
        double eMeasure = 1 - fMeasure;

        // Output
        System.out.println("Answer Set A: " + A);
        System.out.println("Relevant Docs Rq1: " + Rq1);
        System.out.println("Retrieved & Relevant (A ∩ Rq1): " + intersection);
        System.out.printf("Precision = %.2f\n", precision);
        System.out.printf("Recall = %.2f\n", recall);
        System.out.printf("F-measure = %.2f\n", fMeasure);
        System.out.printf("E-measure = %.2f\n", eMeasure);
    }
}

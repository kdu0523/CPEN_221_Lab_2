import java.util.HashSet;

public class DNA {

    String sequence;
    String DNAsq = sequence;

    public DNA (String sequence) {

        //Leave only ATGC in the new string from raw DNA data
        this.sequence = sequence.replaceAll("[^ATGC]]", "");

    }

    public boolean checkvalid (String sequence) {
        //Check if sequence are complete codons
        return this.sequence.length() % 3 == 0;
    }

    public void errorassign () {
        if (!checkvalid(sequence)) {
            throw new IllegalArgumentException("Invalid DNA sequence");

        }
    }

    public double totalMass (String sequence){

        //Initiate all values needed
        final double adenine = 135.128;
        final double cytosine = 111.103;
        final double thymine = 125.107;
        final double guanine = 151.128;

        double tempmass = 0;

        // for each loop to check each of nucleotides in the filtered sequence
        for (char nucleotides : sequence.toCharArray()) {
            switch (nucleotides) { // case for all ATGC
                case 'A':
                    tempmass += adenine; // if case as such add corresponding mass values
                    break;
                case 'G':
                    tempmass += guanine;
                    break;
                case 'C':
                    tempmass += cytosine;
                    break;
                case 'T':
                    tempmass += thymine;
                    break;
            }

        }

        double junkmass = (DNAsq.length() - sequence.length()) * 100; // junk mass consideration

        return Math.round((tempmass + junkmass) * 10 / 10); //answer as both mass from ATGC and junk

    }

    public int nucleotideCount(char c) {

        //Initialzes all counters needed for ATGC amount
        int numa = 0;
        int numc = 0;
        int numg = 0;
        int numt = 0;

        for (char nucleotides : sequence.toCharArray()) { //For each loop to go through all of filtered sequence
            switch (nucleotides) {
                case 'A':
                    numa += 1; //If case true, then add counter by one
                    break;
                case 'G':
                    numg += 1;
                    break;
                case 'C':
                    numc += 1;
                    break;
                case 'T':
                    numt += 1;
                    break;
            }

        }

        return switch (c) { // return case by case with char c
            case 'A' -> numa;
            case 'C' -> numc;
            case 'T' -> numt;
            case 'G' -> numg;
            default -> 0;
        };

    }

    boolean isProtein(String nucleotides){

        String startcodon = nucleotides.substring(0, 2);
        String stopcodon =  nucleotides.substring(nucleotides.length() - 3);

        //using a if state to check if the sequence is befitting to the protein sequence

        if (startcodon == "ATG") {
            if (stopcodon == "TAG" || stopcodon == "TAA" || stopcodon == "TGA") {
                if (nucleotides.length() >= 15) {
                    return true;
                }
            }
        }
        return false;

    }
}

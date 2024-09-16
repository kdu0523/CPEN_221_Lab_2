import java.util.HashSet;

public class DNA {

    String sequence;
    String DNAsq = sequence;

    public DNA (String sequence) {

        //Leave only ATGC in the new string from raw DNA data
        this.sequence = sequence.replaceAll("[^ATGC]]", "");

    }

    double totalmass (String sequence){

        final double adenine = 135.128;
        final double cytosine = 111.103;
        final double thymine = 125.107;
        final double guanine = 151.128;

        double tempmass = 0;

        for (char nucleotides : sequence.toCharArray()) {
            switch (nucleotides) {
                case 'A':
                    tempmass += adenine;
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

        double junkmass = (DNAsq.length() - sequence.length()) * 100;

        return tempmass + junkmass;

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

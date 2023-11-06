package Tut03;
import java.io.*;

public class Genome {
    public static void main (String[]args) {
        if (args.length == 1) {
            String eingabe = dateiLesen(args[0]);

            //Array wird mit 1/3 der Zeichnlänge initialisiert, um je 3 als Codon zusammengefasste Basen
            //in diesem zu speichern.
            String[] genom = new String[(eingabe.length() - 1) / 3];
            StringBuilder codon = new StringBuilder();
            for (int i = 0; i < ((eingabe.length() - 1) / 3); i++) {
                codon.append(eingabe.charAt(3 * i));
                codon.append(eingabe.charAt(3 * i + 1));
                codon.append(eingabe.charAt(3 * i + 2));
                genom[i] = codon.toString();
                codon.setLength(0);
            }
            /* Während des Iterierens durch das Array codon werden Codons solange zu einem String zusammengesetzt,
            bis eines der Stoppcodon gefunden wird. In diesem Fall wird der String,
            der nun ein Gen beinhaltet, ausgegeben. */
            StringBuilder gen = new StringBuilder();
            boolean startCodonGefunden = false;
            for (int i = 0; i < genom.length; i++) {
                if (genom[i].equals("atg")) {
                    if (!startCodonGefunden) {
                        System.out.print("Gen:");
                    }
                    startCodonGefunden = true;
                }
                if (startCodonGefunden) {
                    gen.append(" ");
                    gen.append(genom[i]);
                }
                if ((genom[i].equals("tag") || genom[i].equals("tga") || genom[i].equals("taa")) && startCodonGefunden) {
                    System.out.println(gen);
                    gen.setLength(0);
                    startCodonGefunden = false;
                }
            }
        }
        else{
            System.err.println("Bitte Datei angeben!");
        }
    }

    public static String dateiLesen(String dateipfad) {
        //Datei wird aus dem angegebenen Pfad gelesen und die erste Zeile als String zurückgegeben
        try {
            File datei = new File(dateipfad);
            FileReader fr = new FileReader(datei);
            BufferedReader br = new BufferedReader(fr);     //ermöglicht effizienteres Lesen durch nutzen eines Puffers
            return br.readLine();
        } catch (IOException fileNotFoundException) {
            System.err.println("Datei konnte nicht gefunden werden!");
        }
        return "";
    }
}
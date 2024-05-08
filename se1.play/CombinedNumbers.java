import java.util.HashMap;
import java.util.Map;

public class CombinedNumbers {
    private static final Map<String, Integer> WORT_ZU_ZAHL = new HashMap<>();

    static {
        // Mapping von geschriebenen Zahlen zu ihren numerischen Werten
        WORT_ZU_ZAHL.put("null", 0);
        WORT_ZU_ZAHL.put("eins", 1);
        WORT_ZU_ZAHL.put("zwei", 2);
        WORT_ZU_ZAHL.put("drei", 3);
        WORT_ZU_ZAHL.put("vier", 4);
        WORT_ZU_ZAHL.put("fuenf", 5);
        WORT_ZU_ZAHL.put("sechs", 6);
        WORT_ZU_ZAHL.put("sieben", 7);
        WORT_ZU_ZAHL.put("acht", 8);
        WORT_ZU_ZAHL.put("neun", 9);
        WORT_ZU_ZAHL.put("zehn", 10);
        WORT_ZU_ZAHL.put("elf", 11);
        WORT_ZU_ZAHL.put("zwoelf", 12);
        WORT_ZU_ZAHL.put("dreizehn", 13);
        WORT_ZU_ZAHL.put("vierzehn", 14);
        WORT_ZU_ZAHL.put("fuenfzehn", 15);
        WORT_ZU_ZAHL.put("sechzehn", 16);
        WORT_ZU_ZAHL.put("siebzehn", 17);
        WORT_ZU_ZAHL.put("achtzehn", 18);
        WORT_ZU_ZAHL.put("neunzehn", 19);
        WORT_ZU_ZAHL.put("zwanzig", 20);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Keine Eingaben gemacht.");
            return;
        }

        int summe = 0;
        String sequence = "";

        for (String arg : args) {
            if (arg.startsWith("sum=")) {
                try {
                    summe = Integer.parseInt(arg.substring(4));
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe für die Summe.");
                    return;
                }
            } else if (arg.startsWith("n=")) {
                String[] parts = arg.substring(2).split(",\\s*");
                for (String part : parts) {
                    int parsedNumber = parseZahl(part);
                    if (parsedNumber != -1) {
                        summe += parsedNumber;
                    } else {
                        sequence += part + " ";
                    }
                }
            } else {
                try {
                    int num = Integer.parseInt(arg);
                    summe += num;
                } catch (NumberFormatException ignored) {
                    int parsedNumber = parseZahl(arg);
                    if (parsedNumber != -1) {
                        summe += parsedNumber;
                    } else {
                        System.out.println("Unbekannter Eingabetyp: " + arg);
                    }
                }
            }
        }



        // Berechne die richtige Summe
        int correctSum = berechneSumme(sequence.split("\\s+"), 0, 0);

        System.out.println("Hello, Numbers (sum args)");
        if (summe == correctSum) {
            System.out.println("--> Ihre Lösung war korrekt");
            System.out.println("--> sum is: " + summe);
        } else {
            System.out.println("--> Ihre Lösung (sum=" + summe + ") war leider falsch");
            System.out.println("--> sum is: " + correctSum);
        }
    }

    private static int berechneSumme(String[] args, int index, int summe) {
        if (index >= args.length) {
            return summe;
        }

        int zahl = parseZahl(args[index]);
        if (zahl != -1) {
            return berechneSumme(args, index + 1, summe + zahl);
        } else {
            try {
                int num = Integer.parseInt(args[index]);
                return berechneSumme(args, index + 1, summe + num);
            } catch (NumberFormatException e) {
                System.out.println("Unbekannter Eingabetyp: " + args[index]);
                return berechneSumme(args, index + 1, summe);
            }
        }
    }

    private static int parseZahl(String wort) {
        Integer zahl = WORT_ZU_ZAHL.get(wort.toLowerCase());
        return zahl != null ? zahl : -1;
    }
}

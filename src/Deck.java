import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dustin on 1/10/14.
 */
public class Deck {
    private ArrayList<String> cards;
    public Deck(){
        cards = new ArrayList<String>();
    }
    public void addCard(String card){
        cards.add(card);
    }
    public void writeToFile(File file){
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<cockatrice_deck version=\"1\">\n" +
                    "    <deckname>PackWar</deckname>\n" +
                    "    <comments></comments>\n" +
                    "    <zone name=\"main\">\n");

            writer.write("<card number=\"3\" price=\"0\" name=\"Mountain\"/>\n" +
                    "        <card number=\"3\" price=\"0\" name=\"Plains\"/>\n" +
                    "        <card number=\"3\" price=\"0\" name=\"Forest\"/>\n" +
                    "        <card number=\"3\" price=\"0\" name=\"Swamp\"/>\n" +
                    "        <card number=\"3\" price=\"0\" name=\"Island\"/>\n");

            for (String e: cards){
                writer.write("<card number=\"1\" price=\"0\" name=\"" + e +"\"/>\n");
            }
            writer.write("    </zone>\n" +
                    "</cockatrice_deck>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}

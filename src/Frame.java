import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Dustin on 1/10/14.
 */
public class Frame extends JFrame{
    private JFileChooser chooser;
    private File rareTxt,uncommonTxt,commonTxt;
    private JButton rare;
    private JButton uncommon;
    private JButton common;
    private JButton save;
    public Frame(){
        super("Pack Wars");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300,100));
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JLabel headerText = new JLabel("Select the text files for each rarity");
        this.add(headerText,BorderLayout.PAGE_START);

        rare = new JButton("Rare/Mythic");
        uncommon = new JButton("Uncommon");
        common = new JButton("Common");
        save = new JButton("Save to .cod");
        save.setEnabled(false);

        this.add(rare,BorderLayout.LINE_START);
        this.add(uncommon,BorderLayout.CENTER);
        this.add(common,BorderLayout.LINE_END);
        this.add(save,BorderLayout.PAGE_END);

        chooser = new JFileChooser();

        rare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rareTxt = getFile();
                checkReady();

            }
        });
        common.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                commonTxt = getFile();
                checkReady();

            }
        });
        uncommon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                uncommonTxt = getFile();
                checkReady();

            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                makeDeck();
            }
        });

    }

    public void makeDeck() {
        File deckFile = null;
        while (deckFile == null){
            int returnValue = chooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION){
                deckFile = chooser.getSelectedFile();
            }
        }
        ArrayList<String> rares = getList(rareTxt);
        ArrayList<String> commons = getList(commonTxt);
        ArrayList<String> uncommons = getList(uncommonTxt);

        Deck deck = new Deck();

        //add land/foil
        switch ((int)(Math.random()*6)){
            case(1):
                deck.addCard("Island");
                break;
            case(2):
                deck.addCard("Mountain");
                break;
            case(3):
                deck.addCard("Swamp");
                break;
            case(4):
                deck.addCard("Plains");
                break;
            case(5):
                deck.addCard("Forest");
                break;
            default:
                int foilType = (int)(Math.random()*3);
                switch(foilType){
                    case(0):
                        deck.addCard(rares.get((int)(Math.random()*rares.size())));
                        break;
                    case(1):
                        deck.addCard(commons.get((int) (Math.random() * commons.size())));
                        break;
                    default:
                        deck.addCard(uncommons.get((int)(Math.random()*uncommons.size())));
                }
        }

        //add rare/mythic
        deck.addCard(rares.get((int)(Math.random()*rares.size())));

        //add uncommons
        for (int i = 0; i < 3; i++){
            deck.addCard(uncommons.get((int)(Math.random()*uncommons.size())));
        }

        //add commmon
        for (int i = 0; i < 9; i++){
            deck.addCard(commons.get((int)(Math.random()*commons.size())));
        }

        deck.writeToFile(deckFile);
    }

    private ArrayList<String> getList(File txt) {
        ArrayList<String> list = new ArrayList<String>();
        Scanner in = null;
        try {
            in = new Scanner(txt);
            while(in.hasNext()){
                list.add(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (in != null){
                in.close();
            }
        }
        return list;
    }

    public void checkReady() {
        if (rareTxt != null && commonTxt != null && uncommonTxt != null){
            save.setEnabled(true);
        }
    }

    public static void main(String[] args){
        Frame frame = new Frame();
        frame.setVisible(true);
    }
    public File getFile(){
        File file = null;
        while (file == null){
            int returnValue = chooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION){
                file = chooser.getSelectedFile();
            }

        }
        return file;
    }
}

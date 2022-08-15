package Ui;

import Interfaces.ConsoleColors;

public class DashBoardIcons implements ConsoleColors {
    

    public void getRatingStar(Double notaOficial) {
        //double rating = (notaOficial) / 2;
        int numberStars = (int) Math.ceil(notaOficial);
        System.out.print("" + "⭐️".repeat(Math.max(0, numberStars)) + ANSI_BLUE + " (" + notaOficial + ")\n");;
    }

    
    public String getEmoticon(int pos) {
        String emoticon = null;
        if(pos > 50) {
            emoticon = "🎥";
        } else if(pos > 9) {
            emoticon = "🍿";
        } else if(pos > 2) {
            emoticon = "🏆🍿";
        } else {
            emoticon = "🍿🏆🍿";
        }
        return emoticon;
    }


}

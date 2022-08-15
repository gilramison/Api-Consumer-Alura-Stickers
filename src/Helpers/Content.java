package Helpers;

import Interfaces.ConsoleColors;

public class Content  implements ConsoleColors{

    private final String titulo;
    private final String urlImagem;
    private final Double notaOficial;
    private Double minhaNota;

    
    public Content(String titulo, String urlImagem, Double notaOficial, Double minhaNota) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
        this.notaOficial = notaOficial;
        this.minhaNota = minhaNota;
    }


    public String getTitulo() {
        return titulo;
    }
    
    public String getUrlImagem() {
        return urlImagem;
    }


    public Double getNotaOficial() {
        return notaOficial;
    }


    public Double getMinhaNota() {
        return minhaNota;
    }

    
    public void getRatingStar() {
        double rating = (this.notaOficial) / 2;
        int numberStars = (int) Math.ceil(rating);
        System.out.println("" + "⭐️".repeat(Math.max(0, numberStars)) + ANSI_BLUE + " (" + rating + ")\n");;
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

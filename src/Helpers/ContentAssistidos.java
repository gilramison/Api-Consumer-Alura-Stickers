package Helpers;

public class ContentAssistidos {
    private final String titulo;
    private final String urlImagem;
    private final Double notaOficial;
    private Double minhaNota;

    
    public ContentAssistidos(String titulo, String urlImagem,Double notaOficial,Double minhaNota) {
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

    
}


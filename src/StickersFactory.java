import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;


import javax.imageio.ImageIO;

public class StickersFactory {

    public void cria(InputStream myInputStream, String fileName) throws Exception{
        
        //faz a leitura da imagem    
        //BufferedImage originalImage = ImageIO.read(new File("assets/filme.jpg")); //teste pegando imagem local
        //testando o inputStream com url fixa
        //InputStream myInputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        
        BufferedImage originalImage = ImageIO.read(myInputStream);//usando url dinamica
        //cria uma nova redimensionada
        int altura = originalImage.getHeight();
        int largura = originalImage.getWidth();
        int novaAltura = altura + 200; //aumentando em 200 pxs 
        BufferedImage imageFinal = new BufferedImage(largura,novaAltura,BufferedImage.TRANSLUCENT);

        //copiar a imagem original para uma nova imagem em memoria
        Graphics2D graphics = (Graphics2D) imageFinal.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null); //desenha uma imagem nova por cima da orginal

        //setar a fonte antes de escrever na imagem
        var fonteLetra = new Font(Font.SANS_SERIF,Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonteLetra);

        //escreve algo na imagem
        graphics.drawString("TopZera", 100, novaAltura - 100);

        //escrever em um arquivo novo
        ImageIO.write(imageFinal, "png", new File("assets/"+fileName+".png")); //usa png pra ser transparente

    }

    //Main para testar a classe individual
    //public static void main(String[] args) throws Exception {
        //StickersFactory geradora = new StickersFactory();
        //geradora.cria();
   // }

    
}

package Helpers;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;
import Interfaces.ConsoleColors;
import java.awt.*;
import java.awt.font.TextLayout;
import java.io.FileInputStream;

public class StickersFactory implements ConsoleColors {

    public void cria(InputStream myInputStream, String fileName, String phrase) throws Exception{

        try{
        BufferedImage originalImage = ImageIO.read(myInputStream);//usando url dinamica
        //cria uma nova redimensionada
        int altura = originalImage.getHeight();
        int largura = originalImage.getWidth();
        //int novaAltura = altura + 200; //aumentando em 200 pxs 
        int novaAltura = (int) (altura + (altura * 0.26));
        BufferedImage imageFinal = new BufferedImage(largura,novaAltura,BufferedImage.TRANSLUCENT);

        //copiar a imagem original para uma nova imagem em memoria
        Graphics2D graphics = (Graphics2D) imageFinal.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null); //desenha uma imagem nova por cima da orginal

        //setar a fonte antes de escrever na imagem
        phrase = phrase.toUpperCase();
        //var fonteLetra = new Font(Font.SANS_SERIF,Font.BOLD, 64);
        int fontSize = altura / 16;
        var fonteLetra = new Font("Comic Sans MS", Font.BOLD, fontSize);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonteLetra);

        //escreve algo na imagem no meio da imagem
        // encontra o meio da imagem (eixo x)
        int posX = (int) ((largura / 2) - (phrase.length() * (fontSize * 0.3)));
        int posY = (int) (novaAltura - (novaAltura * 0.1484));
        graphics.drawString(phrase, posX, posY);
        //graphics.drawString("TopZera", 100, novaAltura - 100);

        // cria imagem/carimbo pessoal que será acrescentado
        int resizeWidth, resizeHeight;
        resizeWidth = resizeHeight = (int) (largura * 0.28);
        InputStream imgStamp = new FileInputStream(new File("assets/hangLoose.png"));
        BufferedImage bufferedImageInput = ImageIO.read(imgStamp);
        graphics.drawImage(bufferedImageInput, largura - resizeWidth, novaAltura - resizeHeight,
                resizeWidth, resizeHeight, null);

         // Faz contorno na imagem
         graphics.setStroke(new BasicStroke((float) (altura *  0.002)));
         var textLayout = new TextLayout(phrase, fonteLetra, graphics.getFontRenderContext());
         var shape = textLayout.getOutline(null);
         graphics.setColor(Color.BLACK);
         graphics.translate(posX, posY);
         graphics.draw(shape);


        //escrever em um arquivo novo
        fileName.concat(".png");
        File newImage = new File("assets/"+fileName + ".png");
        ImageIO.write(imageFinal, "png", newImage);
        //ImageIO.write(imageFinal, "png", new File("assets/"+fileName+".png")); //usa png pra ser transparente

    } catch (IOException e) {
        System.out.println("Não foi possível ler a imagem");
    }
}

    
}

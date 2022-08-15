package Ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import Extractors.ExtractorContent;
import Extractors.ExtractorImdb;
import Helpers.ClientHttp;
import Helpers.Content;
import Helpers.StickersFactory;
import Interfaces.ConsoleColors;
import Model.ItemFilme;
import Model.ServersLink;
import Helpers.*;

public class MenuApp implements ConsoleColors {
        
    Scanner entrada = new Scanner(System.in);

    public EnumUserChoice mensagemInicial() throws Exception{
    
        var options = List.of(EnumUserChoice.values());

        var mensagemInicial = StringUtil.addBlankLeftPad("Aplicação Imersão JAVA ALura:",41);
        System.out.println("\n|------------------------------------------|");
        System.out.println("|\u001b[38;5;214m \u001b[48;5;153m".concat(mensagemInicial).concat("|\u001b[m"));
        System.out.println("|------------------------------------------|");
        
        options.forEach(element -> System.out.println(getOptionTemplate(element)));
        System.out.println("|------------------------------------------|");

        var enumOption = EnumUserChoice.parseByKey(String.valueOf(entrada.next().charAt(0)));

        if (enumOption == null) {
            System.out.println("\n|------------------------------------------|");
            System.out.println("|             OPÇÃO INVÁLIDA!              |");
            System.out.println("|------------------------------------------|");

            enumOption = mensagemInicial();
        }
        return enumOption;
                
    }

    private String getOptionTemplate(EnumUserChoice enumUserChoice) {
        return StringUtil.addBlankLeftPad("| [".concat(enumUserChoice.getKey()).concat("] ")
                .concat(enumUserChoice.getValue()), 45).concat("|");
    }

    public void ExibirFilmesAssistidos(String body,Boolean sitckerCreator){

        System.out.println( "\u001b[38;5;214m \u001b[48;5;153m Filmes assistidos e avaliados: \u001b[m");

        var gson = new Gson();
        ItemFilme[] items  = gson.fromJson(body, ItemFilme[].class);
        List<ItemFilme> listFilm = List.of(items);

        for (int i = 0; i < listFilm.size(); i++)
            {           
            var title =listFilm.get(i).getTitle(); 
            Double minhaNota = listFilm.get(i).getMinhaNota().doubleValue();
            Double notaOficial = listFilm.get(i).getRank().doubleValue();
            DashBoardIcons dashboard = new DashBoardIcons();
            System.out.println(ANSI_BLUE + "FILME AVALIADO: "+(i+1)+") - "+ ANSI_YELLOW+title+dashboard.getEmoticon(i));            
            System.out.print(ANSI_BLUE + "NOTA OFICIAL:");
            dashboard.getRatingStar(notaOficial) ;
            System.out.print(ANSI_BLUE + "MINHA  NOTA :");
            dashboard.getRatingStar(minhaNota) ;
            System.out.println("-------------------");
            }

        if(sitckerCreator){
            System.out.println("\u001b[m Digite o numero de um filme para gerar o sticker: ");
            entrada = new Scanner(System.in);
            int filmeAssistido = entrada.nextInt();
            if(filmeAssistido == 0 || filmeAssistido > listFilm.size()){return;}
            System.out.println("Digite a mensagem para aparecer no sticker: ");
            entrada = new Scanner(System.in);
            String mensagemStick = entrada.nextLine();

            //exibir e manipular os dados      
            var geradora = new StickersFactory();       

            ItemFilme filme = listFilm.get(filmeAssistido-1);
            
            try {                
                String nomeArquivo = filme.getTitle();
                String url = filme.getImage();
                //URL url = new URL ((String) filmeItem.get("image"));
                //URL url = new URL(String.valueOf(filmeSticker.get("image")));
                //InputStream myInputStream = new URL(String.valueOf(filmeSticker.get("image"))).openStream();                                
                InputStream myInputStream = new URL(url).openStream();                                
                //InputStream myInputStream = new URL(filme.getImage()).openStream(); 
                geradora.cria(myInputStream, nomeArquivo,mensagemStick);
                System.out.println(ANSI_YELLOW+"Sticker criado com sucesso! Verifique o diretorio de saída! \uD83D\uDE4F");
                System.out.println("--------------------------");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
            
    }

    public void ExibirTopFilmes(String body) throws Exception{

        //exibir e manipular os dados
        ExtractorContent extractor = new ExtractorImdb();        
        List<Content> contents = extractor.extraiConteudos(body)  ;

       /*  contents.forEach( item -> {System.out.println("\u001b[38;5;214m \u001b[48;5;153m"+item.getTitulo()+ " \u001b[m");
        System.out.println(item.getNotaOficial());
        }); */
    
        for(int i =0; i < contents.size(); i++){
            System.out.print(i+1+") "); //imprime os index da lista contents
            System.out.println(ANSI_BLUE + "TOP " + (i+1) + " - " + contents.get(i).getEmoticon(i));
            String title = contents.get(i).getTitulo();
            System.out.println(ANSI_YELLOW + "Título:  ".concat(title));
            contents.get(i).getRatingStar();
        }

        System.out.println("Digite o numero de um filme que ja assistiu para avalia-lo: ");
        entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        System.out.println("Qual a nota deste filme?(1 ate 10)");
        Double minhaNota = entrada.nextDouble();

        if(opcao > contents.size()) {return;}
        if(opcao < 0) {return;}
        String filmeName = contents.get(opcao-1).getTitulo();
        String urlImagem = contents.get(opcao-1).getUrlImagem();
        Double notaOficial = (contents.get(opcao -1).getNotaOficial());       

        Map<String,Object> filmeAssistido = new HashMap<>();
        filmeAssistido.put("title", filmeName);
        filmeAssistido.put("image", urlImagem);
        filmeAssistido.put("ranking",notaOficial);
        filmeAssistido.put("minhaNota",minhaNota);

        JSONObject payload = new JSONObject(filmeAssistido);//apenas o filme assitido vai ser inserido no mongoDB

        var conexao = new ClientHttp();
        ServersLink srvLink = new ServersLink();
        conexao.inserirDados(srvLink.getURL_ASSISTIDOS(),payload);  
    }

    public void resolveUserChoice(EnumUserChoice userOption) throws Exception {

        while (!userOption.equals(EnumUserChoice.EXIT)) {
        
            switch (userOption) {
                case SHOW_TOP_MOVIES:            
                    var body = ClientHttp.realizaConexao(Integer.valueOf(userOption.getKey()));
                    this.ExibirTopFilmes(body);
                    break;
                case SHOW_MOVIES_VISTOS:
                    var reponse = ClientHttp.realizaConexao(Integer.valueOf(userOption.getKey()));
                    this.ExibirFilmesAssistidos(reponse,false);
                    break;
                case GENERATE_STICKER:
                    var bodySticker = ClientHttp.realizaConexao(Integer.valueOf(userOption.getKey()));
                    this.ExibirFilmesAssistidos(bodySticker,true);            
                    break;        
                case EXIT:
                    break;
            }
        
            userOption = mensagemInicial();            
        }
        System.out.println("Obrigado por usar nossa aplicação! \uD83D\uDE4F");
    }

}

   


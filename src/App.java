import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer a conexao HTTP e buscar os 250 top filmes
        //String url = "https://api.mocki.io/v2/549a5d8b";
        //ExtractorContent extractor = new ExtractorImdb(); //usando ainterface

        //nasa
        //String url= "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //ExtractorNasa extractor = new ExtractorNasa(); //nao usa a interface
        //ExtractorContent extractor = new ExtractorNasa(); //nao usa a interface

        String url = "https://ramobilesoftware-api.herokuapp.com/linguagens";
        ExtractorContent extractor = new ExtractorImdb();

        var conexao = new ClientHttp();
        var bodyFinal = conexao.buscaDados(url);

        
        //exibir e manipular os dados
      List<Content> contents = extractor.extraiConteudos(bodyFinal)  ;
      var geradora = new StickersFactory();

        for(int i = 0; i < contents.size(); i++ ){
            
            Content conteudo = contents.get(i);

            InputStream myInputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() +".png";


            geradora.cria(myInputStream, nomeArquivo);

        }


     
    }
}

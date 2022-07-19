import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer a conexao HTTP e buscar os 250 top filmes
        // https://api.themoviedb.org/3/trending/movie/week?api_key=041b2c7ce549eaa7320467b3de5c9f40
        //String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=041b2c7ce549eaa7320467b3de5c9f40";
        String url = "https://api.mocki.io/v2/549a5d8b";

        URI enderecoUrl = URI.create(url);
        var clientHttp = HttpClient.newHttpClient();
        var requisicao = HttpRequest.newBuilder(enderecoUrl).GET().build();
        HttpResponse<String> response = clientHttp.send(requisicao, BodyHandlers.ofString());
        String bodyFinal = response.body();
        //System.out.println(bodyFinal);

        //extrair os dados: (titulo, imagem, nota de rating)
        var parser = new JsonParser();
        List<Map<String,String>> ListaDeFilmes = parser.parse(bodyFinal);
        
        //exibir e manipular os dados
        System.out.println(ListaDeFilmes.size());
        System.out.println(ListaDeFilmes.get(0));

        //for (Map<String,String> filme : ListaDeFilmes) {
        //    System.out.println(filme.get("title"));
       //     System.out.println(filme.get("image"));
       //     System.out.println(filme.get("imDbRating"));
      //  }

      var geradora = new StickersFactory();


      for (Map<String,String> filme : ListaDeFilmes) {

        String urlImagem = filme.get("image");
        String titulo = filme.get("title");
        InputStream myInputStream = new URL(urlImagem).openStream();
        String nomeArquivo = titulo +".png";

        
        geradora.cria(myInputStream,nomeArquivo);

        //System.out.println(urlImagem);
        System.out.println(filme.get("title"));
       // System.out.println(filme.get("image"));
        //System.out.println(filme.get("imDbRating"));
    }
    }
}

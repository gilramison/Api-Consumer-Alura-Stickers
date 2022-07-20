import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {
    
    public String buscaDados(String url){

        try{
            URI enderecoUrl = URI.create(url);
            var clientHttp = HttpClient.newHttpClient();
            var requisicao = HttpRequest.newBuilder(enderecoUrl).GET().build();
            HttpResponse<String> response = clientHttp.send(requisicao, BodyHandlers.ofString());
            String bodyFinal = response.body();
            return bodyFinal;
        }catch(IOException | InterruptedException ex ){
            throw new RuntimeException(ex);
        
        }
        
    }
}

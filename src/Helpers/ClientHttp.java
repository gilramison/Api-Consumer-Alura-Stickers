package Helpers;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;

import org.json.simple.JSONObject;

import Model.ServersLink;

public class ClientHttp {

    public static String realizaConexao(int opt){                

        var conexao = new ClientHttp();
        ServersLink srvLink = new ServersLink();
        String url = "";
        String bodyFinal = "";

        switch(opt){
            case 1:
            url = srvLink.getURL_IMDB();
            bodyFinal = conexao.buscaDados(url);  
                    break;
            case 2:
            url = srvLink.getURL_ASSISTIDOS();
            bodyFinal = conexao.buscaDados(url);
                    break;
            case 3:
            url = srvLink.getURL_ASSISTIDOS();
            bodyFinal = conexao.buscaDados(url);
                    break;                            
        }
                
        return bodyFinal;
    }
    
    
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

    public void inserirDados(String url2, JSONObject payload) throws FileNotFoundException{
       
            FileWriter writeFile = null;
            var clientHttp = HttpClient.newHttpClient();
            try{
                writeFile = new FileWriter("assets/postfile.json");
                //Escreve no arquivo conteudo do Objeto JSON
                writeFile.write(payload.toJSONString());
                writeFile.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        
            //String FILE_JSON = "assets/postfile.json";
             // criar a requisição
           
                HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url2))
        .timeout(Duration.ofSeconds(20))
        .header("Content-Type", "application/json")
        .POST(BodyPublishers.ofFile(Paths.get("assets/postfile.json")))
        .build();

            //envia assincrona                  
                    clientHttp.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray())            
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();                            

               

                /* //envia pra arquivo pra debugar o erro
                clientHttp.sendAsync(request, HttpResponse.BodyHandlers.ofFile(Paths.get("assets/postfile.json")))            
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();  */  
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractorNasa implements ExtractorContent{
    
public List<Content> extraiConteudos(String jsonTemp) {

    //extrair os dados: (titulo, imagem, nota de rating)
    var parser = new JsonParser();
    List<Map<String,String>> ListaDeAtributos = parser.parse(jsonTemp);

    List<Content> conteudosfinais = new ArrayList<>(); //lista vazia


    for (Map<String,String> atributos : ListaDeAtributos) {

        String urlImagem = atributos.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg"); //refatora a url para pegar a imagem poster
        String titulo = atributos.get("title");
        var content = new Content(titulo, urlImagem);

        conteudosfinais.add(content);
       
    }


    return conteudosfinais;

}

}

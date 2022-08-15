package Extractors;
import java.util.List;
import Helpers.*;

public interface ExtractorContent {

    public List<Content> extraiConteudos(String jsonTemp);
    public List<ContentAssistidos> extraiConteudosAssistidos(String jsonTemp);
    
}

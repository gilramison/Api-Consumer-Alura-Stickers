import Ui.*;


public class App {        
        
    public static void main(String[] args) throws Exception {

        MenuApp menuApp = new MenuApp();
        var userOption = menuApp.mensagemInicial();
        menuApp.resolveUserChoice(userOption);  
        
    }
}

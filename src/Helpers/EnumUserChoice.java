package Helpers;

public enum EnumUserChoice {

    SHOW_TOP_MOVIES("1", "Mostrar TOP Filmes \uD83D\uDE0E"),
    SHOW_MOVIES_VISTOS("2", "Filmes assistidos e Avaliados \uD83D\uDD1D"),
    GENERATE_STICKER("3", "Gerar Sticker de Filme Assistido \uD83D\uDC81"),
    EXIT("4", "Sair \uD83C\uDFC3");

    private final String key, value;

    private EnumUserChoice(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static EnumUserChoice parseByKey(String key) {

        if (key != null && !key.trim().isEmpty()) {
            //System.out.println("entrada if: "+key);
            for (var value : EnumUserChoice.values()) {
                if (value.getKey().equalsIgnoreCase(key)) {
                    return value;
                }
            }
        }

        return null;
    }

}

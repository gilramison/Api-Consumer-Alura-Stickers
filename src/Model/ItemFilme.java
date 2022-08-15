package Model;

public class ItemFilme {
  
    private String id;

    private Double ranking;

    private Double minhaNota;

    public Double getRanking() {
        return ranking;
    }

    public void setRanking(Double ranking) {
        this.ranking = ranking;
    }

    public Double getMinhaNota() {
        return minhaNota;
    }

    public void setMinhaNota(Double minhaNota) {
        this.minhaNota = minhaNota;
    }

    public void setImDbRating(Double imDbRating) {
        this.imDbRating = imDbRating;
    }

    private String title;

    private String fullTitle;
    
    private String year;

    private String image;
    
    private String crew;
    
    private Double imDbRating;
    
    private String imDbRatingCount;

    private Integer userVote = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRank() {
        return ranking;
    }

    public void setRank(Double ranking) {
        this.ranking = ranking;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public Double getImDbRating() {
        return imDbRating;
    }

    /* public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    } */

    public String getImDbRatingCount() {
        return imDbRatingCount;
    }

    public void setImDbRatingCount(String imDbRatingCount) {
        this.imDbRatingCount = imDbRatingCount;
    }
    
    public Integer getUserVote() {
        return userVote;
    }

    public void setUserVote(Integer userVote) {
        this.userVote = userVote;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        
        sb.append("\nNome: ".concat(title));
        sb.append("\nAno de lançamento: ".concat(year));
        //sb.append("\nNota IMDB: ".concat(imDbRating).concat(StringUtil.getStarRating(Double.parseDouble(imDbRating))));
        sb.append("\nVotos Usuário da Aplicação: ".concat(userVote.toString()));

        return sb.toString();
    }

    public void printData() {
        System.out.println(this.toString());
    }

}

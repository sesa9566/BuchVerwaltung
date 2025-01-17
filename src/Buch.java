class Buch {
    private String titel;
    private String autor;
    private int jahr;
    private String genre;

    public Buch(String titel, String autor, int jahr, String genre) {
        this.titel = titel;
        this.autor = autor;
        this.jahr = jahr;
        this.genre = genre;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public int getJahr() {
        return jahr;
    }

    public String getGenre() {
        return genre;
    }
}
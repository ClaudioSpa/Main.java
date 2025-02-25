class Pianta{
    private int etaMorte;                       //eta' in cui la pianta muore
    private int eta;                            //eta' della pianta
    private String nome;                        //Nome pianta (melo, pero, pesca, ...)
    private int codice;                         //Codice pianta
    private int riproduzione;                   //Riproduzione annua della pianta

    //GETTERS e SETTERS
    public int getEtaMorte() {
        return etaMorte;
    }

    public void setEtaMorte(int etaMorte) {
        this.etaMorte = etaMorte;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public int getRiproduzione() {
        return riproduzione;
    }

    public void setRiproduzione(int riproduzione) {
        this.riproduzione = riproduzione;
    }

    //COSTRUTTORE di tutti gli elementi
    public Pianta(int etaMorte, int eta, String nome, int codice, int riproduzione) {
        this.etaMorte = etaMorte;
        this.eta = eta;
        this.nome = nome;
        this.codice = codice;
        this.riproduzione = riproduzione;
    }

    

    
}

package projetopacotao;

public class No {
    private int valor;
    private No direita;
    private No esquerda;


    public int getValor() {
        return valor;
    }


    public void setValor(int valor) {
        this.valor = valor;
    }


    public No getDireita() {
        return direita;
    }


    public void setDireita(No direita) {
        this.direita = direita;
    }


    public No getEsquerda() {
        return esquerda;
    }


    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No() {
        ;
    }

    public No(int valor){
        this.valor = valor;
    }
    
    public No(int valor, No direita, No esquerda) {
        this.valor = valor;
        this.direita = direita;
        this.esquerda = esquerda;
    }

    @Override
    public String toString() {
        return Integer.toString(this.getValor());
    }
    
    public No sucessor(No no)
    {
        No sucessor = no;
        
        sucessor = sucessor.getDireita();
        
        while(sucessor.getEsquerda() != null)
            sucessor = sucessor.getEsquerda();
        
        return sucessor;
    }
    
    public No antecessor(No no)
    {
        No antecessor = no;
        
        antecessor = antecessor.getEsquerda();
        
        while(antecessor.getDireita() != null)
        {
            antecessor = antecessor.getDireita();
        }
        
        return antecessor;
    }
   
    
}

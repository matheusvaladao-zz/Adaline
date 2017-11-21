/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaline;

/**
 *
 * @author matheus
 */
public class Adaline {

    // Obtem os pesos para as duas entradas e o BIAS.
    private double[] pesos = new double[3];
    // Somatório.
    private double soma = 0;
    // Número de épocas.
    private final int maximoEpocas = 30;
    // Contador de épocas durante o treinamento.
    private int contador = 0;
    // Vetor da matriz de aprendizado.
    private int[][] matrizAprendizado = new int[4][3];
    // Valor definido e=do epsilon a ser usado.
    private double epsilon = 0.1;
    // Valor da taxa de aprendizado.
    private double taxaDeAprendizado = 0.5;
    // Erro quadratico médio da época.
    private double erroQuadraticoMedio = 0;
    // Erro quadratico médio da época anterior.
    private double erroQuadraticoMedioAnterior = 0;

    /**
     * Retorna contador de épocas.
     *
     * @return
     */
    public int getContador() {
        return this.contador;
    }

    /**
     * Construtot padrão da classe
     */
    Adaline() {

        // Inicializando valores para treinamento da porta AND.
        this.matrizAprendizado[0][0] = 0;
        this.matrizAprendizado[0][1] = 0;
        this.matrizAprendizado[0][2] = 0;

        this.matrizAprendizado[1][0] = 0;
        this.matrizAprendizado[1][1] = 1;
        this.matrizAprendizado[1][2] = 0;

        this.matrizAprendizado[2][0] = 1;
        this.matrizAprendizado[2][1] = 0;
        this.matrizAprendizado[2][2] = 0;

        this.matrizAprendizado[3][0] = 1;
        this.matrizAprendizado[3][1] = 1;
        this.matrizAprendizado[3][2] = 1;

        //Inicializando pesos: entradas e BIAS.
        pesos[0] = 0;
        pesos[1] = 0;
        pesos[2] = 0;

    }

    /**
     * Método responsável por fazer o somatório e calcular a função de ativação.
     *
     * @param x1
     * @param x2
     * @return
     */
    double executar(int x1, int x2) {

        soma = (x1 * pesos[0]) + (x2 * pesos[1]) + (pesos[2]);

        //Função de ativação da tangente hipebólica.
        return Math.tanh(soma);
    }

    /**
     * Método de treinamento da rede.
     */
    public void treinar() {

        double saida;
        for (int i = 0; i < 4; i++) {
            saida = executar(matrizAprendizado[i][0], matrizAprendizado[i][1]);
            calculaErroQuadraticoMedio(i, saida);
            corrigirPeso(i, saida);
        }

        if ((Math.abs(erroQuadraticoMedio - erroQuadraticoMedioAnterior) > epsilon) && (this.contador < this.maximoEpocas)) {
            erroQuadraticoMedioAnterior = erroQuadraticoMedio;
            erroQuadraticoMedio = 0;
            this.contador++;
            treinar();

        }

    }

    /**
     * Método que calcula o erro quadrático médio de um vetor da matriz em
     * questão.
     *
     * @param i
     * @param resultado
     * @return
     */
    double calculaErroQuadraticoMedio(int i, double resultado) {
        erroQuadraticoMedio = erroQuadraticoMedio + Math.pow(matrizAprendizado[i][2] - resultado, 2);
        erroQuadraticoMedio = erroQuadraticoMedio / (i + 1);
        return erroQuadraticoMedio;
    }

    /**
     * Método que corrige os pesos durante o treinamento para que este se
     * aproxime da função desejada.
     *
     * @param i
     * @param saida
     */
    void corrigirPeso(int i, double saida) {

        pesos[0] = pesos[0] + ((matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0] * taxaDeAprendizado);
        pesos[1] = pesos[1] + ((matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1] * taxaDeAprendizado);
        pesos[2] = pesos[2] + ((matrizAprendizado[i][2] - saida) * taxaDeAprendizado);
    }

}

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
public class Executar {

    /**
     * Método principal da classe.
     *
     * @param args
     */
    public static void main(String[] args) {

        Adaline adaline = new Adaline();

        adaline.treinar();

        System.out.println("Número de épocas de treinamento: " + adaline.getContador());

        System.out.println("0 AND 0 = " + adaline.executar(0, 0));
        System.out.println("0 AND 1 = " + adaline.executar(0, 1));
        System.out.println("1 AND 0 = " + adaline.executar(1, 0));
        System.out.println("0 AND 0 = " + adaline.executar(0, 0));
        System.out.println("1 AND 1 = " + adaline.executar(1, 1));

    }

}

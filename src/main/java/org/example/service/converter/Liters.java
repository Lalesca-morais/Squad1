package org.example.service.converter;

public class Liters implements Converter{
    @Override
    public String converter(double value_to_be_converted, int type_to_be_converted) {

        double result;

        switch (type_to_be_converted){
            case 1: //grama
                result = value_to_be_converted*1000;
                return String.format("%.0f grama(s)", result);

            case 2://xicara
                result =  value_to_be_converted*4.17;
                return String.format("%.0f xícara(s)", result);

            case 3://colher de sopa
                result =  value_to_be_converted*67;
                return String.format("%.0f colher de sopa(s)", result);

            default:
                throw new IllegalArgumentException();
        }
    }
}

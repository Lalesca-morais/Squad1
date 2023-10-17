package org.example.service.converter;

public class Cup implements Converter{

    @Override
    public String converter(double value_to_be_converted, int type_to_be_converted) {

        double result;

        switch (type_to_be_converted){
            case 1: //grama
                result = value_to_be_converted*240;
                return String.format("%.0f grama(s)", result);

            case 2://litro
                result = value_to_be_converted*0.24;
                return String.format("%.0f litro(s)", result);

            case 3://colher de sopa
                result = value_to_be_converted*16;
                return String.format("%.0f colher de sopa(s)", result);

            default:
                throw new IllegalArgumentException();
        }
    }
}

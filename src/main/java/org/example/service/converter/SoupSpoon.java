package org.example.service.converter;
public class SoupSpoon implements Converter{
    @Override
    public String converter(double value_to_be_converted, int type_to_be_converted) {

        double result;

        switch (type_to_be_converted){
            case 1: //grama
                result = value_to_be_converted*15;
                return String.format("%.0f grama(s)", result);

            case 2://litro
                result = value_to_be_converted*0.015;
                return String.format("%.0f litro(s)", result);

            case 3://xicara
                result = value_to_be_converted*0.0625;
                return String.format("%.0f xícara(s)", result);

            default:
                throw new IllegalArgumentException();
        }
    }
}
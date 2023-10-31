package br.com.zup.squad1.service.converter;

import br.com.zup.squad1.exceptions.BadRequestException;
public class Gram implements Converter{
    @Override
    public String converter(double value_to_be_converted, int type_to_be_converted) {

        double result;

        switch (type_to_be_converted) {
            case 1: //litros
                result = value_to_be_converted / 1000;
                return String.format("%.0f litro(s)", result);

            case 2://xicara
                result = value_to_be_converted / 240;
                return String.format("%.0f xícara(s)", result);

            case 3://colher de sopa
                result = value_to_be_converted / 15;
                return String.format("%.0f colher de sopa(s)", result);

            default:
                throw new BadRequestException("Preenchimento inválido");
        }
    }
}
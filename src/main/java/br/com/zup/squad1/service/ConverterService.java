package br.com.zup.squad1.service;

import br.com.zup.squad1.exceptions.BadRequestException;
import br.com.zup.squad1.service.converter.Cup;
import br.com.zup.squad1.service.converter.Gram;
import br.com.zup.squad1.service.converter.Liters;
import br.com.zup.squad1.service.converter.SoupSpoon;
import org.springframework.stereotype.Service;
@Service
public class ConverterService {
    private Gram gram = new Gram();
    private Liters liters = new Liters();
    private Cup cup = new Cup();
    private SoupSpoon soupSpoon = new SoupSpoon();
    public String converterMeasure(double value_to_be_converted, int measure, int type_to_be_converted) {

        switch (measure) {
            case 1: //gramas - measure
                return gram.converter(value_to_be_converted, type_to_be_converted);

            case 2: //litros
                return liters.converter(value_to_be_converted, type_to_be_converted);

            case 3: //xicaras
                return cup.converter(value_to_be_converted,type_to_be_converted);

            case 4: //colher de sopa
                return soupSpoon.converter(value_to_be_converted,type_to_be_converted);

            default:
                throw new BadRequestException("Preenchimento inválido");
        }
    }
}
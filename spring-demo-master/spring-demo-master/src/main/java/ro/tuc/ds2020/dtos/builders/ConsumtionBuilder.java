package ro.tuc.ds2020.dtos.builders;


import ro.tuc.ds2020.dtos.ConsumtionDTO;
import ro.tuc.ds2020.entities.Consumtion;

public class ConsumtionBuilder {

    public ConsumtionBuilder(){

    }

    public static ConsumtionDTO generateDTOFromEntity(Consumtion consumtion){
        return new ConsumtionDTO(
                consumtion.getId(),
                consumtion.getDate(),
                consumtion.getEnergyConsumed(),
                consumtion.getSensor().getId()

        );
    }

    public static Consumtion generateEntityFromDTO(ConsumtionDTO consumtionDTO){

        //vaildate consumtion
        return new Consumtion(
                consumtionDTO.getId(),
                consumtionDTO.getDate(),
                consumtionDTO.getEnergyConsumed()
        );
    }
}


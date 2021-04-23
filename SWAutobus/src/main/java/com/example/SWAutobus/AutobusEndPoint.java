package com.example.SWAutobus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import me.tell.autobus.BuscarViajePorNombreRequest;
import me.tell.autobus.BuscarViajePorNombreResponse;
import me.tell.autobus.BuscarViajeResponse;
import me.tell.autobus.EliminarViajeRequest;
import me.tell.autobus.EliminarViajeResponse;
import me.tell.autobus.IngresarViajeRequest;
import me.tell.autobus.IngresarViajeResponse;
import me.tell.autobus.ModificarViajeRequest;
import me.tell.autobus.ModificarViajeResponse;

@Endpoint
public class AutobusEndPoint {
    @Autowired
    private ViajeCRUD vCRUD;

    @PayloadRoot (namespace = "http://tell.me/autobus", localPart = "IngresarViajeRequest")

    @ResponsePayload
    public IngresarViajeResponse dameViaje(@RequestPayload IngresarViajeRequest peticion){
        IngresarViajeResponse respuesta = new IngresarViajeResponse();
        respuesta.setRespuesta("Usted a ingresado el viaje: "+peticion.getViaje()+" bienvenido");
        Viajes vi = new Viajes();
        vi.setViaje(peticion.getViaje());
        vCRUD.save(vi);
        return respuesta;
    }

    @PayloadRoot (namespace = "http://tell.me/autobus", localPart = "BuscarViajeRequest")

    @ResponsePayload
    public BuscarViajeResponse dameViajes(){
        BuscarViajeResponse resultado = new BuscarViajeResponse();
        Iterable<Viajes>ViajeCRUD = vCRUD.findAll();
        for(Viajes ls : ViajeCRUD){
            BuscarViajeResponse.Viajante viaj = new BuscarViajeResponse.Viajante();
            viaj.setViaje(ls.getViaje());
            viaj.setId(ls.getId());
            resultado.getViajante().add(viaj);
        }
        return resultado;
    }
    
    
    @PayloadRoot (namespace = "http://tell.me/autobus", localPart = "EliminarViajeRequest")

    @ResponsePayload
    public EliminarViajeResponse eliminarViaje(@RequestPayload EliminarViajeRequest peticion){
        EliminarViajeResponse resultado = new EliminarViajeResponse();
        Iterable<Viajes>ViajeCRUD = vCRUD.findAll();
        int validador = 0;
        for(Viajes ls : ViajeCRUD){
            if(ls.getId() == peticion.getId()){
                vCRUD.deleteById(peticion.getId());
                validador=1;
            }
        }
        if(validador == 1 ){
            resultado.setRespuesta("Eliminado");
        }else{
            resultado.setRespuesta("No se pudo eliminar");
        }

        // Optional<Viajes> i = vCRUD.findById(peticion.getId());
        // if(i.isPresent()){
        //     vCRUD.deleteById(peticion.getId());
        //     resultado.setRespuesta("Acualizado");
        // }else{
        //     resultado.setRespuesta("No se encontro ID");
        // }

        return resultado;
    }

    @PayloadRoot (namespace = "http://tell.me/autobus", localPart = "ModificarViajeRequest")

    @ResponsePayload
    public ModificarViajeResponse actualizarSaludo(@RequestPayload ModificarViajeRequest peticion){
        ModificarViajeResponse resultado = new ModificarViajeResponse();
        Iterable<Viajes>ViajeCRUD = vCRUD.findAll();
        int validador = 0;
        for(Viajes ls : ViajeCRUD){
            if(ls.getId() == peticion.getId()){
                Viajes sal = new Viajes();
                sal.setId(peticion.getId());
                sal.setViaje(peticion.getViaje());
                vCRUD.save(sal);
                validador=1;
            }
        }
        if(validador == 1 ){
            resultado.setRespuesta("Actualizado");
        }else{
            resultado.setRespuesta("No se actualizo");
        }
        
        // Optional<Viajes> i = vCRUD.findById(peticion.getId());
        // if(i.isPresent()){
        //     Viajes salu = new Viajes();
        //     salu = i.get();
        //     salu.setNombre(peticion.getNombre());
        //     vCRUD.save(salu);
        //     resultado.setRespuesta("Acualizado");
        // }else{
        //     resultado.setRespuesta("No se encontro ID");
        // }
        return resultado;
    }

    @PayloadRoot (namespace = "http://tell.me/autobus", localPart = "BuscarViajePorNombreRequest")

    @ResponsePayload
    public BuscarViajePorNombreResponse buscarSaludo(@RequestPayload BuscarViajePorNombreRequest peticion){
        BuscarViajePorNombreResponse respuesta = new BuscarViajePorNombreResponse();
        Viajes salu = new Viajes();
        salu = vCRUD.findByViaje(peticion.getViaje());
        respuesta.setId(salu.getId());
        respuesta.setRespuesta(salu.getViaje());
        return respuesta;
    }
    
}

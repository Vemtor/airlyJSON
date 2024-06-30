package org.example;

import org.example.APIconnection.ApiClient;
import org.example.Mapper.JsonMapper;
import org.example.POJO.Station;

import java.io.FileNotFoundException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    try {


        String stationURL = "https://api.gios.gov.pl/pjp-api/rest/station/findAll";
        String installationURL = "https://api.gios.gov.pl/pjp-api/rest/station/sensors/";

        System.out.println("### Wait, we are downloading data. Just give as a couple of seconds... ### \n");
        String stationJson = ApiClient.getJsonResponse(stationURL);
        List<Station> stations = JsonMapper.mapJsonToStations(stationJson, installationURL);

        for(Station station: stations){
            System.out.println(station.toString());
        }

        System.out.println("### All work is done! ###");
    } catch(FileNotFoundException e){
        System.out.println("Your JSON url is not valid, please check it again");
        e.printStackTrace();

    } catch (Exception e){
        e.printStackTrace();
    }


    }

}
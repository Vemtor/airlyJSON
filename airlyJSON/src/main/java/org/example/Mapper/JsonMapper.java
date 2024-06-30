package org.example.Mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.APIconnection.ApiClient;
import org.example.Exceptions.InvalidFormatException;
import org.example.Exceptions.MissingRequiredFieldException;
import org.example.POJO.Installation;
import org.example.POJO.Station;

import java.util.ArrayList;
import java.util.List;

public class JsonMapper {

    //  here it is method to map JSON string to a list of Station objects
    public static List<Station> mapJsonToStations(String stationJson, String installationUrl) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the station JSON into a list of JsonNode objects
        List<JsonNode> stationNodes = objectMapper.readValue(stationJson, new TypeReference<List<JsonNode>>() {});

        List<Station> stations = new ArrayList<>();


        for (JsonNode stationNode : stationNodes) {

            // Check for required fields "id" and "stationName"
            if (!stationNode.has("id") || !stationNode.has("stationName")) {
                throw new MissingRequiredFieldException();
            }
            // Validate the types of "id" and "stationName"
            if (!stationNode.get("id").isInt() || !stationNode.get("stationName").isTextual()) {
                throw new InvalidFormatException();
            }

            int stationId = stationNode.get("id").asInt();
            String stationName = stationNode.get("stationName").asText();


            Station station = new Station();
            station.setId(stationId);
            station.setName(stationName);

            // Fetch and parse the installation JSON associated with the station
            String installationJson = ApiClient.getJsonResponse(installationUrl + stationId);
            List<JsonNode> installationNodes = objectMapper.readValue(installationJson, new TypeReference<List<JsonNode>>() {});

            // Iterate over each installation node
            for (JsonNode installationNode : installationNodes) {
                // Check for required fields "id" and "param"
                if (!installationNode.has("id") || !installationNode.has("param")) {
                    throw new MissingRequiredFieldException();
                }
                // Validate the types of "id" and "param"
                if (!installationNode.get("id").isInt() || !installationNode.get("param").isObject()) {
                    throw new InvalidFormatException();
                }


                int installationId = installationNode.get("id").asInt();

                String paramFormula = installationNode.get("param").get("paramFormula").asText();

                // Create a new Installation object
                Installation installation = new Installation();
                installation.setId(installationId);
                installation.setParamFormula(paramFormula);


                station.addInstalation(installation);
            }
            // Add the station to the list of stations
            stations.add(station);
        }
        return stations;
    }
}

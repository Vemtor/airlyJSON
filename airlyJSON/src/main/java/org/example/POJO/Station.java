package org.example.POJO;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private int id;
    private String name;
    private List<Installation> installations = new ArrayList<>();



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Installation> getInstallations() {
        return installations;
    }

    public void setInstallations(List<Installation> installations) {
        this.installations = installations;
    }

    public void addInstalation(Installation installation){
        installations.add(installation);
    }

    private String installationPrint(){

        StringBuilder toPrint = new StringBuilder();

        for(Installation installation: installations){
            toPrint.append("installation #");

            toPrint.append(installation.getId()).append(":").append("'").append(installation.getParamFormula())
                    .append("'").append(System.lineSeparator());
        }
        return toPrint.toString();
    }

    @Override
    public String toString() {
        return "Station #" + id + "(" + getName() + ")"
                + "\n"
                + installationPrint();

    }
}

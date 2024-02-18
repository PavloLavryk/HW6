package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseQueryService service = new DatabaseQueryService();

        List<ResultRow> maxProjectCountClients = service.findMaxProjectsClient();
        System.out.println("Max Project Count Clients:");
        for (ResultRow row : maxProjectCountClients) {
            System.out.println("Name: " + row.getName() + ", Project Count: " + row.getValue());
        }

        List<ResultRow> maxSalaryWorkers = service.findMaxSalaryWorker();
        System.out.println("\nMax Salary Workers:");
        for (ResultRow row : maxSalaryWorkers) {
            System.out.println("Name: " + row.getName() + ", Salary: " + row.getValue());
        }

        List<ResultRow> longestProjects = service.findLongestProject();
        System.out.println("\nLongest Projects:");
        for (ResultRow row : longestProjects) {
            System.out.println("ID: " + row.getName() + ", Duration: " + row.getValue());
        }

        List<YoungestEldestWorker> youngestEldestWorkers = service.findYoungestEldestWorkers();
        System.out.println("\nYoungest and Eldest Workers:");
        for (YoungestEldestWorker worker : youngestEldestWorkers) {
            System.out.println("Type: " + worker.getType() + ", Name: " + worker.getName() + ", Birthday: " + worker.getBirthday());
        }

        List<ResultRow> projectPrices = service.printProjectPrices();
        System.out.println("\nProject Prices:");
        for (ResultRow row : projectPrices) {
            System.out.println("ID: " + row.getName() + ", Price: " + row.getValue());
        }
    }
}

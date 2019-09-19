package edu.olezha.jsandbox.hollywoodprinciple;

public class App {

    public static void main(String[] args) {
        AgentFinder agentFinder = new SpreadsheetAgentFinder();
        System.out.println(HollywoodService.getFriendlyAgents(agentFinder));
    }
}
